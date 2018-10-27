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

import base.entities.*;
import base.service.*;

@Controller
@RequestMapping("admin/supplier")
public class SupplierController {

	@Autowired
	private SupplierService supplierService;

	@Autowired
	private LogsService logsService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap, HttpServletRequest request) {
		PagedListHolder pagedListHolder = new PagedListHolder(supplierService.findAll());
		int page = ServletRequestUtils.getIntParameter(request, "ph", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		modelMap.put("pagedListHolder", pagedListHolder);
		return "supplier.index";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(ModelMap modelMap) {
		modelMap.put("supplier", new Supplier());
		return "supplier.create";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@ModelAttribute("supplier") Supplier supplier, ModelMap modelMap) {
		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		}

		Supplier ExtSpl = supplierService.findByName(supplier.getName());

		if (ExtSpl == null ) {
			supplierService.create(supplier);

			Logs logs = new Logs();
			logs.setDescription(acc + " create supplier " + supplier.getName() + " at " + new Date().toString());
			logsService.create(logs);

			return "redirect:/admin/supplier.html";
		} else {
			modelMap.put("used", "This supplier name is already");
			modelMap.put("supplier", new Supplier());
			return "supplier.create";
		}
	}

	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
		modelMap.put("supplier", supplierService.find(id));
		return "supplier.detail";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, ModelMap modelMap) {
		modelMap.put("supplier", supplierService.find(id));
		return "supplier.update";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String edit(@ModelAttribute("supplier") Supplier supplier, ModelMap modelMap) {
		Supplier ext = supplierService.find(supplier.getId());
		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		}	
		
		Supplier ExtSpl = supplierService.findByName(supplier.getName());
		String a = null;
		if(ExtSpl != null) {
			a = ExtSpl.getName();
		}
		String b = ext.getName();
		
		if (a == null || a.equalsIgnoreCase(b) == true) {
		Logs logs = new Logs();
		logs.setDescription(
				acc + " update supplier " + ext.getName() + " with address" + ext.getAddress() + ", phone number "
						+ ext.getPhoneNumber() + " to " + supplier.getName() + " with address" + supplier.getAddress()
						+ ", phone number " + supplier.getPhoneNumber() + " at " + new Date().toString());
		logsService.create(logs);

		supplierService.update(supplier);
		return "redirect:/admin/supplier.html";}
		else {
			modelMap.put("used", "This supplier name is already");
			modelMap.put("supplier", supplierService.find(supplier.getId()));
			return "supplier.update";
		}
	}

	@RequestMapping(value = "search", method = RequestMethod.POST)
	public String search(ModelMap modelMap, HttpServletRequest request) {
		String keyword = request.getParameter("keyword");
		List<Supplier> suppliers = supplierService.search(keyword);
		PagedListHolder pagedListHolder2 = new PagedListHolder(suppliers);
		PagedListHolder pagedListHolder = new PagedListHolder(supplierService.findAll());
		int page = ServletRequestUtils.getIntParameter(request, "ph", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		modelMap.put("keyword", keyword);
		if (suppliers.isEmpty()) {
			modelMap.put("result", 1);
			modelMap.put("pagedListHolder", pagedListHolder);
		} else {
			modelMap.put("result", 2);
			modelMap.put("pagedListHolder", pagedListHolder2);
		}
		return "supplier.index";
	}
}
