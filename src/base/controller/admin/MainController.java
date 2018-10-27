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
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("logins")
public class MainController {

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "home.login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value = "error", required = false) String error, HttpSession session,
			@RequestParam(value = "logout", required = false) String logout, ModelMap modelMap) {
		if (error != null) {
			modelMap.addAttribute("msg", "Invalid username or password! Do you have account?");
		}
		if (logout != null) {
			modelMap.addAttribute("msg", "You've been logged out successfully.");
		}

		return "home.login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("account");
		return "home.login";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accesssDenied(ModelMap modelMap) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			modelMap.addAttribute("username", userDetail.getUsername());
		}
		return "login.403";
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome() {
		return "home.index";
	}

}
