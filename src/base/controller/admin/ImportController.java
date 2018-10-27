package base.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
@RequestMapping("admin/import")
public class ImportController {

	@Autowired
	private ImportDetailService importDetailService;
		
	@Autowired
	private ImportService importService;
	
	@Autowired
	private SupplierService supplierService;

	@Autowired
	private ProductService productService;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private LogsService logsService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap, HttpServletRequest request) {
		PagedListHolder pagedListHolder = new PagedListHolder(importService.findAll());
		int page = ServletRequestUtils.getIntParameter(request, "ph", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		modelMap.put("pagedListHolder", pagedListHolder);
		return "import.index";
	}
	
	@RequestMapping(value = "temp", method = RequestMethod.GET)
	public String temp(ModelMap modelMap) {
		modelMap.put("supplier", supplierService.findAll());
		return "import.temp";
	}	
	

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "imports/{id}", method = RequestMethod.GET)
	public String imports(@PathVariable("id") Integer id, HttpSession session) {

		if (session.getAttribute("imports") == null) {
			List<Item> imports = new ArrayList<Item>();
			imports.add(new Item(productService.find(id), 1));
			session.setAttribute("imports", imports);
		} else {
			List<Item> imports = (List<Item>) session.getAttribute("imports");
			int index = isExits(id, session);
			if (index == -1) {
				imports.add(new Item(productService.find(id), 1));
			} else {
				int quantity = imports.get(index).getQuantity();
				int pq = imports.get(index).getProduct().getQuantity();
				if (quantity >= pq) {
					quantity = pq;
				} else {
					quantity++;
				}
				imports.get(index).setQuantity(quantity);
			}
			session.setAttribute("imports", imports);
		}
		return "redirect:/admin/import/temp.html";
	}

	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
		modelMap.put("detail", importDetailService.find(id));
		return "import.detail";
	}	

	@SuppressWarnings("unchecked")
	private int isExits(int id, HttpSession session) {
		List<Item> imports = (List<Item>) session.getAttribute("imports");
		for (int i = 0; i < imports.size(); i++) {
			if (imports.get(i).getProduct().getId() == id) {
				return i;
			}
		}
		return -1;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String Save(HttpSession session, HttpServletRequest request) {
		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		}
		if (acc == null) {
			return "home.login";
		} else {
			// Luu hoa don
			Imports imports = new Imports();
			imports.setAccount(accountService.find(acc));
			imports.setTimeCreation(new Date());
			importService.create(imports);
			
			Logs logs = new Logs();
			logs.setDescription(acc + " import with ID " + imports.getId() + " at "+ new Date().toString());
			logsService.create(logs);
			
			List<Item> imp = (List<Item>) session.getAttribute("imports");
			for (int i = 0; i < imp.size(); i++) {
				ImportDetail importDetail = new ImportDetail();
				importDetail.setId(new ImportDetailId(imports.getId(), imp.get(i).getProduct().getId()));
				importDetail.setImports(imports);
				importDetail.setPrice(imp.get(i).getProduct().getPrice());
				importDetail.setQuantity(imp.get(i).getQuantity());
				importDetail.setProduct(imp.get(i).getProduct());
				importDetail.setSupplier(supplierService.findByName(request.getParameter("supplierName")));
				importDetailService.create(importDetail);

				Product product = productService.find(imp.get(i).getProduct().getId());
				product.setQuantity(imp.get(i).getProduct().getQuantity() + imp.get(i).getQuantity());
				productService.update(product);
			}
			session.removeAttribute("imports");

			return "redirect:/admin/import.html";
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "deleteTemp/{id}", method = RequestMethod.GET)
	public String deleteTemp(@PathVariable("id") Integer id, HttpSession session) {
		List<Item> imports = (List<Item>) session.getAttribute("imports");
		int index = isExits(id, session);
		imports.remove(index);
		session.setAttribute("imports", imports);
		return "redirect:/import/index.html";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "updateTemp", method = RequestMethod.POST)
	public String updateTemp(HttpServletRequest request, HttpSession session) {
		List<Item> imports = (List<Item>) session.getAttribute("imports");
		String[] quantity = request.getParameterValues("quantity");
		for (int i = 0; i < imports.size(); i++) {
			imports.get(i).setQuantity(Integer.parseInt(quantity[i]));
		}
		session.setAttribute("imports", imports);
		return "redirect:/admin/import/temp.html";
	}
}
