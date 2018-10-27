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
@RequestMapping("admin/product")
public class ProductsController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProducerService producerService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private LogsService logsService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap, HttpServletRequest request) {
		PagedListHolder pagedListHolder = new PagedListHolder(productService.findAll());
		int page = ServletRequestUtils.getIntParameter(request, "ph", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		modelMap.put("pagedListHolder", pagedListHolder);
		return "product.index";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(ModelMap modelMap) {
		modelMap.put("product", new Product());
		modelMap.put("producer", producerService.findAll());
		modelMap.put("category", categoryService.getChild());
		return "product.create";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "status", defaultValue = "false") boolean status) {
		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		}

		Product ExtPrd = productService.findByName(request.getParameter("name"));

		if (ExtPrd == null) {
			Product product = new Product();
			product.setQuantity(0);
			product.setDescription(request.getParameter("description"));
			product.setShortDescription(request.getParameter("shortDescription"));
			product.setSpecification(request.getParameter("specification"));
			product.setCategory(categoryService.findByName(request.getParameter("categoryName")));
			product.setProducer(producerService.findByName(request.getParameter("producerName")));
			product.setName(request.getParameter("name"));
			product.setStatus(status);
			product.setPrice(Double.parseDouble(request.getParameter("price")));
			productService.create(product);

			Logs logs = new Logs();
			logs.setDescription(acc + " create product " + product.getName() + " at " + new Date().toString());
			logsService.create(logs);
			return "redirect:/admin/product.html";
		} else {
			modelMap.put("used", "This product name is already");
			modelMap.put("product", new Product());
			modelMap.put("producer", producerService.findAll());
			modelMap.put("category", categoryService.getChild());
			return "product.create";
		}
	}

	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
		modelMap.put("product", productService.find(id));
		return "products.detail";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, ModelMap modelMap) {
		modelMap.put("product", productService.find(id));
		modelMap.put("producer", producerService.findAll());
		modelMap.put("category", categoryService.getChild());
		return "product.update";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String edit(HttpServletRequest request, ModelMap modelMap, @ModelAttribute("product") Product product,
			@RequestParam(value = "status", defaultValue = "false") boolean status) {
		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		}

		Product ExtPrd = productService.findByName(product.getName());
		Product ext = productService.find(product.getId());
		String a = null;
		if(ExtPrd != null) {
			a = ExtPrd.getName();
		}		
		String b = ext.getName();
		
		if (a == null || a.equalsIgnoreCase(b) == true) {

			product.setCategory(categoryService.findByName(request.getParameter("categoryName")));
			product.setProducer(producerService.findByName(request.getParameter("producerName")));
			product.setName(request.getParameter("name"));
			product.setStatus(status);
			product.setPrice(Double.parseDouble(request.getParameter("price")));
			productService.update(product);

			Logs logs = new Logs();
			logs.setDescription(acc + " update product " + ext.getName() + " , price " + ext.getPrice() + " , producer "
					+ ext.getProducer() + " ,category " + ext.getCategory() + " , status" + ext.isStatus()
					+ " ,description " + ext.getDescription() + " ,short description " + ext.getShortDescription()
					+ " , specification" + ext.getSpecification() + " to " + product.getName() + " , price "
					+ product.getPrice() + " , producer " + product.getProducer() + " ,category "
					+ product.getCategory() + " , status" + product.isStatus() + " ,description "
					+ product.getDescription() + " ,short description " + product.getShortDescription()
					+ " , specification" + product.getSpecification() + " at " + new Date().toString());
			logsService.create(logs);

			return "redirect:/admin/product.html";
		} else {
			modelMap.put("used", "This product name is already");
			modelMap.put("product", productService.find(product.getId()));
			modelMap.put("producer", producerService.findAll());
			modelMap.put("category", categoryService.getChild());
			return "product.update";
		}
	}

	@RequestMapping(value = "search", method = RequestMethod.POST)
	public String search(ModelMap modelMap, HttpServletRequest request) {
		String keyword = request.getParameter("keyword");
		List<Product> products = productService.search(keyword);
		PagedListHolder pagedListHolder2 = new PagedListHolder(products);
		PagedListHolder pagedListHolder = new PagedListHolder(productService.findAll());
		int page = ServletRequestUtils.getIntParameter(request, "ph", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		modelMap.put("keyword", keyword);
		if (products.isEmpty()) {
			modelMap.put("result", 1);
			modelMap.put("pagedListHolder", pagedListHolder);
		} else {
			modelMap.put("result", 2);
			modelMap.put("pagedListHolder", pagedListHolder2);
		}
		return "product.index";
	}
}
