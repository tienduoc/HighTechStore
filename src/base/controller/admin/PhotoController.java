package base.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
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
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import base.entities.*;
import base.service.*;

@Controller
@RequestMapping("admin/photo")
public class PhotoController implements ServletContextAware {
	private ServletContext servletContext;

	@Autowired
	private PhotoService photoService;

	@Autowired
	private ProductService productService;

	@Autowired
	private LogsService logsService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap, HttpServletRequest request) {
		PagedListHolder pagedListHolder = new PagedListHolder(photoService.findAll());
		int page = ServletRequestUtils.getIntParameter(request, "ph", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		modelMap.put("pagedListHolder", pagedListHolder);
		return "photo.index";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(ModelMap modelMap) {
		modelMap.put("product", productService.findAll());
		return "photo.create";
	}

	@RequestMapping(value = "chooseProduct", method = RequestMethod.POST)
	public String choose(@RequestParam(value = "productId") Integer id, ModelMap modelMap) {
		modelMap.put("product", productService.findAll());
		Product p = productService.find(id);
		modelMap.put("p", p);
		return "photo.create";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@RequestParam(value = "image", required = false) MultipartFile image,
			@RequestParam(value = "main", defaultValue = "false") Boolean main,
			@RequestParam(value = "productId") Integer id, ModelMap modelMap) {
		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		}

		Photo photo = new Photo();
		Product product = productService.find(id);
		photo.setProduct(product);
		photo.setUrl(saveImage(image));
		photo.setMain(main);
		photoService.create(photo);

		Logs logs = new Logs();
		logs.setDescription(acc + " create photo " + photo.getUrl() + " of product " + photo.getProduct().getName()
				+ " with main = " + photo.isMain() + " at " + new Date().toString());
		logsService.create(logs);

		return "redirect:/admin/photo.html";
	}

	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
		modelMap.put("photo", photoService.find(id));
		return "photo.detail";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, ModelMap modelMap) {
		modelMap.put("photo", photoService.find(id));
		modelMap.put("product", productService.findAll());
		return "photo.update";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String edit(HttpServletRequest request, @RequestParam(value = "image", required = false) MultipartFile image,
			@RequestParam(value = "main", defaultValue = "false") Boolean main,
			@RequestParam(value = "photoID", required = true) Integer id,
			@RequestParam(value = "productName", required = true) String products, ModelMap modelMap) {
		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		}

		Photo photo = photoService.find(id);
		Product product = productService.findByName(products);

		photo.setProduct(product);
		if (image.isEmpty()) {
			photo.setUrl(photo.getUrl());

			Logs logs = new Logs();
			logs.setDescription(acc + " update photo " + photo.getUrl() + " of product " + photo.getProduct().getName()
					+ " with main = " + photo.isMain() + " to " + main.toString() + " at " + new Date().toString());
			logsService.create(logs);
		} else {
			File file = new File(servletContext.getRealPath("/") + "/assets/user/images/products/" + photo.getUrl());
			file.delete();

			Logs logs = new Logs();
			logs.setDescription(acc + " update photo " + photo.getUrl() + " of product " + photo.getProduct().getName()
					+ " with main = " + photo.isMain() + " to " + saveImage(image) + " with main = " + main.toString()
					+ " at " + new Date().toString());
			logsService.create(logs);

			photo.setUrl(saveImage(image));
		}
		photo.setMain(main);
		photoService.update(photo);

		return "redirect:/admin/photo.html";
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Integer id) {
		Photo photo = photoService.find(id);
		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		}

		File file = new File(servletContext.getRealPath("/") + "/assets/user/images/products/" + photo.getUrl());
		file.delete();

		photoService.delete(photo);

		Logs logs = new Logs();
		logs.setDescription(acc + " delete photo " + photo.getUrl() + " of product " + photo.getProduct().getName()
				+ " at " + new Date().toString());
		logsService.create(logs);

		return "redirect:/admin/photo.html";
	}

	private String saveImage(MultipartFile image) {
		try {
			File file = new File(
					servletContext.getRealPath("/") + "/assets/user/images/products/" + image.getOriginalFilename());
			FileUtils.writeByteArrayToFile(file, image.getBytes());
			return image.getOriginalFilename();
		} catch (IOException e) {
			return null;
		}
	}

	@RequestMapping(value = "search", method = RequestMethod.POST)
	public String search(ModelMap modelMap, HttpServletRequest request) {
		String keyword = request.getParameter("keyword");
		List<Photo> photo = photoService.search(keyword);
		PagedListHolder pagedListHolder = new PagedListHolder(photo);
		PagedListHolder pagedListHolder2 = new PagedListHolder(photoService.findAll());
		int page = ServletRequestUtils.getIntParameter(request, "ph", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		modelMap.put("keyword", keyword);
		if (photo.isEmpty()) {
			modelMap.put("result", 1);
			modelMap.put("pagedListHolder", pagedListHolder2);
		} else {
			modelMap.put("result", 2);
			modelMap.put("pagedListHolder", pagedListHolder);
		}
		return "photo.index";
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}