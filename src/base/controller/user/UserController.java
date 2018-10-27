package base.controller.user;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import base.entities.*;
import base.service.*;

@Controller
@RequestMapping("/user")
public class UserController implements ServletContextAware {

	private ServletContext servletContext;
	@Autowired
	private AccountService accountService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserRoleService userRoleService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.put("account", new Account());
		modelMap.put("role", roleService.findAll());
		return "user.register";
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register(ModelMap modelMap) {
		modelMap.put("account", new Account());
		modelMap.put("role", roleService.findAll());
		return "user.register";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(ModelMap modelMap, @ModelAttribute("account") Account account, @RequestParam(value = "image", required = false) MultipartFile image
			,HttpServletRequest request) {
		Account ExtAcc = accountService.find(account.getUserName());
		if(ExtAcc == null) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		account.setEnabled(true);
		account.setJoinTime(new Date());
		if (image.isEmpty()) {
			account.setPhoto("UnknownProfile.png");
		} else {
			account.setPhoto(saveImage(image));
		}		
		accountService.create(account);

		UserRole userRole = new UserRole();
		userRole.setEnabled(true);
		userRole.setId(new UserRoleId(account.getUserName(), 3));

		userRoleService.create(userRole);
		return "redirect:/logins.html";}
		else {
			modelMap.put("used", "This username was used by other customer, please choose another username");
			return "user.register";
		}
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
