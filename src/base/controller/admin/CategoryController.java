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
@RequestMapping("admin/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private LogsService logsService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap, HttpServletRequest request) {
		PagedListHolder pagedListHolder = new PagedListHolder(categoryService.findAll());
		int page = ServletRequestUtils.getIntParameter(request, "ph", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		modelMap.put("pagedListHolder", pagedListHolder);
		return "category.index";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(ModelMap modelMap) {
		modelMap.put("pr", categoryService.getParent());
		return "category.create";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(ModelMap modelMap, @RequestParam(value = "parent", required = false) Integer parent,
			@RequestParam(value = "status", defaultValue = "false") Boolean status, HttpServletRequest request) {
		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		}

		Category ExtCate = categoryService.findByName(request.getParameter("Name"));
		if (ExtCate == null) {
			Category category = new Category();
			category.setCategory(categoryService.find(parent));
			category.setName(request.getParameter("Name"));
			category.setStatus(status);
			categoryService.create(category);

			Logs logs = new Logs();
			logs.setDescription(acc + " create category " + category.getName() + " at " + new Date().toString());
			logsService.create(logs);

			return "redirect:/admin/category.html";
		} else {
			modelMap.put("used", "This category name is already");
			modelMap.put("pr", categoryService.getParent());
			return "category.create";
		}
	}

	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
		modelMap.put("category", categoryService.find(id));
		return "category.detail";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, ModelMap modelMap) {
		modelMap.put("parent", categoryService.getParent());
		modelMap.put("category", categoryService.find(id));
		return "category.update";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String edit(ModelMap modelMap, HttpServletRequest request,
			@RequestParam(value = "parent", required = false) Integer parent,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "status", defaultValue = "false") Boolean status) {
		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		}
		Category parents = categoryService.find(parent);

		Category ExtCate = categoryService.findByName(request.getParameter("Name"));
		Category category = categoryService.find(id);
		String a = null;
		if (ExtCate != null) {
			a = ExtCate.getName();
		}
		String b = category.getName();

		if ((a == null) || a.equalsIgnoreCase(b) == true) {

			category.setCategory(parents);
			category.setName(request.getParameter("Name"));
			category.setStatus(status);
			categoryService.update(category);

			Logs logs = new Logs();
			if (parents != null) {
				logs.setDescription(acc + " update category from name " + category.getName() + " to "
						+ request.getParameter("Name") + " and from parent " + category.getCategory().getName() + " to "
						+ parents.getName() + " status " + category.isStatus() + " at " + new Date().toString());
				logsService.create(logs);
			} else {

				logs.setDescription(acc + " update category name " + category.getName() + " to "
						+ request.getParameter("Name") + " status " + category.isStatus()
						+ " at " + new Date().toString());
				logsService.create(logs);
			}

			return "redirect:/admin/category.html";
		} else {
			modelMap.put("used", "This category namme is already");
			modelMap.put("parent", categoryService.getParent());
			modelMap.put("category", categoryService.find(id));
			return "category.update";
		}
	}

	@RequestMapping(value = "search", method = RequestMethod.POST)
	public String search(ModelMap modelMap, HttpServletRequest request) {
		String keyword = request.getParameter("keyword");
		List<Category> categories = categoryService.search(keyword);
		PagedListHolder pagedListHolder = new PagedListHolder(categories);
		PagedListHolder pagedListHolder2 = new PagedListHolder(categoryService.findAll());
		int page = ServletRequestUtils.getIntParameter(request, "ph", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		modelMap.put("keyword", keyword);
		if (categories.isEmpty()) {
			modelMap.put("result", 1);
			modelMap.put("pagedListHolder", pagedListHolder2);
		} else {
			modelMap.put("result", 2);
			modelMap.put("pagedListHolder", pagedListHolder);
		}
		return "category.index";
	}

}
