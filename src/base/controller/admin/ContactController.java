package base.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;

import base.entities.Contact;
import base.entities.Logs;
import base.service.ContactService;
import base.service.LogsService;

@Controller
@RequestMapping("admin/contact")
public class ContactController {

	@Autowired
	private ContactService contactService;

	@Autowired
	private LogsService logsService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap, HttpServletRequest request) {
		PagedListHolder pagedListHolder = new PagedListHolder(contactService.findAll());
		int page = ServletRequestUtils.getIntParameter(request, "ph", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		modelMap.put("pagedListHolder", pagedListHolder);
		return "contact.index";
	}

	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
		modelMap.put("contact", contactService.find(id));
		return "contact.detail";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, ModelMap modelMap) {
		modelMap.put("contact", contactService.find(id));
		return "contact.update";
	}


	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String edit(@RequestParam(value = "id", required = true) Integer id, @RequestParam(value = "seen", defaultValue= "false") Boolean seen,
		ModelMap modelMap) {
		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		}
		Contact contact = contactService.find(id);

		Logs logs = new Logs();
		logs.setDescription(acc + " update contact of customer have email:" + contact.getEmail() + " from " + contact.isSeen() + " to " + seen.toString() + " at "+ new Date().toString());
		logsService.create(logs);

		contact.setSeen(seen);
		contactService.update(contact);

			return "redirect:/admin/contact.html";
	}

	@RequestMapping(value = "search", method = RequestMethod.POST)
	public String search(ModelMap modelMap, HttpServletRequest request) {
		String keyword = request.getParameter("keyword");
		List<Contact> contacts = contactService.search(keyword);
		PagedListHolder pagedListHolder = new PagedListHolder(contacts);
		PagedListHolder pagedListHolder2 = new PagedListHolder(contactService.findAll());
		int page = ServletRequestUtils.getIntParameter(request, "ph", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		modelMap.put("keyword", keyword);
		if (contacts.isEmpty()) {
			modelMap.put("result", 1);
			modelMap.put("pagedListHolder", pagedListHolder2);
		} else {
			modelMap.put("result", 2 );
			modelMap.put("pagedListHolder", pagedListHolder);
		}
		return "contact.index";
	}

}
