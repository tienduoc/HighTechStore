package base.controller.superadmin;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import base.conversion.DateEditor;
import base.entities.*;
import base.service.*;

@Controller
@RequestMapping("superadmin/account")
public class AccountController implements ServletContextAware {

	private ServletContext servletContext;

	@Autowired
	private AccountService accountService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private LogsService logsService;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Date.class, new DateEditor());
	}

	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap, HttpServletRequest request) {
		PagedListHolder pagedListHolder = new PagedListHolder(accountService.findAll());
		int page = ServletRequestUtils.getIntParameter(request, "ph", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		modelMap.put("pagedListHolder", pagedListHolder);
		return "account.index";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(ModelMap modelMap) {
		modelMap.put("account", new Account());
		modelMap.put("role", roleService.findAll());
		return "account.create";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(ModelMap modelMap, @ModelAttribute("account") Account account,
			@RequestParam(value = "roleName", required = true) Integer role,
			@RequestParam(value = "image", required = false) MultipartFile image, HttpServletRequest request) {
		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		}
		Account ExtAcc = accountService.find(account.getUserName());
		if (ExtAcc == null) {
			account.setPassword(passwordEncoder.encode(account.getPassword()));
			account.setJoinTime(new Date());
			if (image.isEmpty()) {
				account.setPhoto("UnknownProfile.png");
			} else {
				account.setPhoto(saveImage(image));
			}
			accountService.create(account);

			UserRole userRole = new UserRole();
			userRole.setEnabled(true);
			userRole.setId(new UserRoleId(account.getUserName(), role));

			userRoleService.create(userRole);

			Logs logs = new Logs();
			logs.setDescription(acc + " create account " + account.getUserName() + " with role " + role + " at "
					+ new Date().toString());
			logsService.create(logs);

			return "redirect:/superadmin/account.html";
		} else {
			modelMap.put("used", "This username is already");
			modelMap.put("account", new Account());
			modelMap.put("role", roleService.findAll());
			return "account.create";
		}
	}

	@RequestMapping(value = "detail/{username}", method = RequestMethod.GET)
	public String detail(@PathVariable("username") String userName, ModelMap modelMap) {
		modelMap.put("account", accountService.find(userName));
		return "account.detail";
	}

	@RequestMapping(value = "update/{userName}", method = RequestMethod.GET)
	public String edit(@PathVariable("userName") String userName, ModelMap modelMap) {
		modelMap.put("account", accountService.find(userName));
		modelMap.put("roles", roleService.findAll());
		return "account.update";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String edit(@ModelAttribute("account") Account account, 
			@RequestParam(value = "roleId", required = true) Integer role,
			@RequestParam(value = "image", required = false) MultipartFile image) {
		
		Account ext = accountService.find(account.getUserName());
		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		}

		if (image.isEmpty()) {
			account.setPhoto(account.getPhoto());
		} else {
			File file = new File(servletContext.getRealPath("/") + "/assets/user/images/account/" + account.getPhoto());
			file.delete();
			account.setPhoto(saveImage(image));
		}
		account.setJoinTime(ext.getJoinTime());
		account.setPassword(ext.getPassword());

		accountService.update(account);

		UserRole extUserRole = userRoleService.find(account.getUserName());
		userRoleService.delete(extUserRole);

		UserRole userRole = new UserRole();
		userRole.setId(new UserRoleId(account.getUserName(), role));
		userRole.setEnabled(true);
		userRoleService.create(userRole);

		Logs logs = new Logs();
		logs.setDescription(acc + " update account " + ext.getUserName() + " from name " + ext.getName() + " , address "
				+ ext.getAddress() + " ,phone number " + ext.getPhoneNumber() + " , photo" + ext.getPhoto() + " ,email "
				+ ext.getEmail() + ", status " + ext.isEnabled() + ", role " + extUserRole.getRole().getId()+ " to " + account.getName() + " , address "
				+ account.getAddress() + " ,phone number " + account.getPhoneNumber() + " , photo" + account.getPhoto()
				+ " ,email " + account.getEmail() + ", status " + account.isEnabled() + ", role " + role + " at "
				+ new Date().toString());
		logsService.create(logs);

		return "redirect:/superadmin/account.html";

	}

	@RequestMapping(value = "changePassword/{userName}", method = RequestMethod.GET)
	public String changePassword(@PathVariable("userName") String userName, ModelMap modelMap) {
		modelMap.put("account", accountService.find(userName));
		modelMap.put("roles", roleService.findAll());
		return "account.changePassword";
	}

	@RequestMapping(value = "changePassword", method = RequestMethod.POST)
	public String changePassword(@RequestParam("userName") String userName, @RequestParam("password") String password) {
		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		}

		Account account = new Account();
		account = accountService.find(userName);
		account.setPassword(passwordEncoder.encode(password));
		accountService.update(account);

		Logs logs = new Logs();
		logs.setDescription(acc + " change password account " + account.getName() + " at " + new Date().toString());
		logsService.create(logs);
		return "redirect:/superadmin/account.html";
	}

	@RequestMapping(value = "search", method = RequestMethod.POST)
	public String search(ModelMap modelMap, HttpServletRequest request) {
		String keyword = request.getParameter("keyword");
		List<Account> accounts = accountService.search(keyword);
		PagedListHolder pagedListHolder2 = new PagedListHolder(accounts);
		PagedListHolder pagedListHolder = new PagedListHolder(accountService.findAll());
		int page = ServletRequestUtils.getIntParameter(request, "ph", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		modelMap.put("keyword", keyword);
		if (accounts.isEmpty()) {
			modelMap.put("result", 1);
			modelMap.put("pagedListHolder", pagedListHolder);
		} else {
			modelMap.put("result", 2);
			modelMap.put("pagedListHolder", pagedListHolder2);
		}
		return "account.index";
	}

	private String saveImage(MultipartFile image) {
		try {
			File file = new File(
					servletContext.getRealPath("/") + "/assets/user/images/account/" + image.getOriginalFilename());
			FileUtils.writeByteArrayToFile(file, image.getBytes());
			return image.getOriginalFilename();
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
