package base.controller.user;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;
import javax.servlet.ServletContext;

import base.entities.*;
import base.service.*;

@Controller
@RequestMapping("home")
public class HomeController implements ServletContextAware {
	private ServletContext servletContext;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private OrderDetailService orderDetailService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ContactService contactService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.put("lasted", productService.latest(20));
		modelMap.put("cate4", productService.findByParentCategory(4));
		modelMap.put("cate1", productService.findByParentCategory(2));
		modelMap.put("cate3", productService.findByParentCategory(3));
		modelMap.put("cate2", productService.findByParentCategory(1));

		return "home.index";
	}

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String test(ModelMap modelMap) {
		modelMap.put("find", productService.find(1));
		return "home.test";
	}

	@RequestMapping(value = "productList", method = RequestMethod.GET)
	public String productList(ModelMap modelMap) {
		return "home.productList";
	}

	@RequestMapping(value = "cart", method = RequestMethod.GET)
	public String cart(ModelMap modelMap) {
		return "home.cart";
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "home.login";
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register() {
		return "home.register";
	}

	@RequestMapping(value = "checkout", method = RequestMethod.GET)
	public String checkout() {
		return "home.checkout";
	}

	@RequestMapping(value = "product", method = RequestMethod.GET)
	public String product() {
		return "home.product";
	}

	@RequestMapping(value = "contact", method = RequestMethod.GET)
	public String contact(ModelMap modelMap) {
		modelMap.put("contact", new Contact());
		return "home.contact";
	}

	@RequestMapping(value = "contact", method = RequestMethod.POST)
	public String contact(@ModelAttribute("contact") Contact contact) {
		contact.setSeen(false);
		contactService.create(contact);
		return "home.contact";
	}

	@RequestMapping(value = "myAccount", method = RequestMethod.GET)
	public String myAccount(ModelMap modelMap, HttpSession session) {
		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		}
		if (accountService.find(acc) == null) {
			return "redirect:/logins.html";
		} else {
			modelMap.put("account", accountService.find(acc.toString()));
			modelMap.put("order", orderService.findByUserName(acc));
			return "home.myAccount";
		}
	}

	@RequestMapping(value = "myAccount", method = RequestMethod.POST)
	public String myAccount(HttpServletRequest request,ModelMap modelMap,
			@RequestParam(value = "image", required = false) MultipartFile image) {
		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		}
		Account account = accountService.find(acc);

		account.setName(request.getParameter("name"));
		account.setEmail(request.getParameter("email"));
		account.setPhoneNumber(request.getParameter("phoneNumber"));
		account.setAddress(request.getParameter("address"));
		if (image.isEmpty()) {
			account.setPhoto(account.getPhoto());
		} else {
			File file = new File(servletContext.getRealPath("/") + "/assets/user/images/account/" + account.getPhoto());
			file.delete();
			account.setPhoto(saveImage(image));
		}
		accountService.update(account);
		modelMap.put("account", accountService.find(acc.toString()));
		modelMap.put("order", orderService.findByUserName(acc));
		return "home.myAccount";
	}

	@RequestMapping(value = "changePassword", method = RequestMethod.GET)
	public String changePassword(ModelMap modelMap, HttpSession session) {
		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		}
		if (accountService.find(acc) == null) {
			return "redirect:/logins.html";
		} else {
			modelMap.put("account", accountService.find(acc.toString()));
			modelMap.put("order", orderService.findByUserName(acc));
			return "home.changePassword";
		}
	}

	@RequestMapping(value = "changePassword", method = RequestMethod.POST)
	public String changePassword(HttpServletRequest request,
			@RequestParam(value = "password", required = false) String password) {
		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		}
		Account account = accountService.find(acc);
		account.setPassword(passwordEncoder.encode(request.getParameter("password")));
		accountService.update(account);
		return "direct:/home/myAccount";
	}

	@RequestMapping(value = "orderDetail/{id}", method = RequestMethod.GET)
	public String orderDetail(@PathVariable("id") Integer id,
			ModelMap modelMap) {
		modelMap.put("detail", orderDetailService.find(id));
		modelMap.put("order", orderService.find(id));
		return "home.orderDetail";
	}

	@RequestMapping(value = "invoice/{id}", method = RequestMethod.GET)
	public String invoice(@PathVariable("id") Integer id,
			ModelMap modelMap) {
		modelMap.put("detail", orderDetailService.find(id));
		modelMap.put("order", orderService.find(id));
		return "home.invoice";
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
