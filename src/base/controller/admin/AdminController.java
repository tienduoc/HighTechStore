package base.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import base.entities.Account;
import base.service.*;

@Controller
@RequestMapping("admin/index")
public class AdminController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ContactService contactService;

	@Autowired
	AccountService accountService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap, HttpSession session) {
		modelMap.put("account", orderService.findAll());
		modelMap.put("orders", orderService.findAll());
		modelMap.put("products", productService.findActive());
		modelMap.put("contacts", contactService.findAll());
		
		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		}
		
		Account account = accountService.find(acc);
		session.setAttribute("acc", account);
		return "admin.index";
	}
}
