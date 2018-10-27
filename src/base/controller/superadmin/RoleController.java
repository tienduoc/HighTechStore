package base.controller.superadmin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import base.entities.Logs;
import base.entities.Role;
import base.service.LogsService;
import base.service.RoleService;

@Controller
@RequestMapping("superadmin/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private LogsService logsService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.put("listRole", roleService.findAll());
		return "role.index";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(ModelMap modelMap) {
		modelMap.put("role", new Role());
		return "role.create";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@ModelAttribute("role") Role role, ModelMap modelMap) {
		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		}

		Role ExtRole = roleService.findByName(role.getName());
		if (ExtRole == null) {
			roleService.create(role);

			Logs logs = new Logs();
			logs.setDescription(acc + " create role " + role.getName() + " at " + new Date().toString());
			logsService.create(logs);
			return "redirect:/superadmin/role.html";
		} else {
			modelMap.put("used", "This role name is already");
			modelMap.put("role", new Role());
			return "role.create";
		}
	}

	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
		modelMap.put("role", roleService.find(id));
		return "role.detail";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, ModelMap modelMap) {
		modelMap.put("role", roleService.find(id));
		return "role.update";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String edit(@ModelAttribute("role") Role role, ModelMap modelMap) {
		Role ext = roleService.find(role.getId());
		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		}

		Role ExtRole = roleService.findByName(role.getName());
		String a = null;
		if(ExtRole != null) {
			a = ExtRole.getName();
		}
		String b = ext.getName();
		
		if (a == null || a.equalsIgnoreCase(b) == true) {
			roleService.update(role);

			Logs logs = new Logs();
			logs.setDescription(
					acc + " update role " + ext.getName() + " to " + role.getName() + " at " + new Date().toString());
			logsService.create(logs);
			return "redirect:/superadmin/role.html";
		} else {
			modelMap.put("used", "This role name is already");
			modelMap.put("role", roleService.find(role.getId()));
			return "role.update";
		}
	}
}
