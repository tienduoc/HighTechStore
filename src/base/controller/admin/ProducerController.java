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
@RequestMapping("admin/producer")
public class ProducerController implements ServletContextAware {
	private ServletContext servletContext;

	@Autowired
	private ProducerService producerService;

	@Autowired
	private LogsService logsService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap, HttpServletRequest request) {
		PagedListHolder pagedListHolder = new PagedListHolder(producerService.findAll());
		int page = ServletRequestUtils.getIntParameter(request, "ph", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		modelMap.put("pagedListHolder", pagedListHolder);
		return "producer.index";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(ModelMap modelMap) {
		modelMap.put("producer", new Producer());
		return "producer.create";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@ModelAttribute("producer") Producer producer,
			@RequestParam(value = "image", required = false) MultipartFile image, ModelMap modelMap) {
		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		}
		Producer ExtPdr = producerService.findByName(producer.getName());

		if (ExtPdr == null) {

			producer.setPhoto(saveImage(image));
			producerService.create(producer);

			Logs logs = new Logs();
			logs.setDescription(acc + " create producer " + producer.getName() + " at " + new Date().toString());
			logsService.create(logs);

			return "redirect:/admin/producer.html";
		} else {
			modelMap.put("used", "This producer name is already");
			modelMap.put("producer", new Producer());
			return "producer.create";
		}
	}

	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
		modelMap.put("producer", producerService.find(id));
		return "producer.detail";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, ModelMap modelMap) {
		modelMap.put("producer", producerService.find(id));
		return "producer.update";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String edit(@ModelAttribute("producer") Producer producer,
			@RequestParam(value = "image", required = false) MultipartFile image, ModelMap modelMap) {
		Producer ext = producerService.find(producer.getId());

		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		}

		Producer ExtPdr = producerService.findByName(producer.getName());

		String a = null;
		if (ExtPdr != null) {
			a = ExtPdr.getName();
		}
		String b = ext.getName();

		if (a == null || a.equalsIgnoreCase(b) == true) {

			if (image.isEmpty()) {
				producer.setPhoto(ext.getPhoto());
			} else {
				File file = new File(
						servletContext.getRealPath("/") + "/assets/user/images/producer/" + producer.getPhoto());
				file.delete();
				producer.setPhoto(saveImage(image));
			}
			producerService.update(producer);

			Logs logs = new Logs();
			logs.setDescription(acc + " update producer " + ext.getName() + " with address" + ext.getAddress()
					+ ", phone number " + ext.getPhoneNumber() + ", photo" + ext.getPhoto() + " to "
					+ producer.getName() + " with address" + producer.getAddress() + ", phone number "
					+ producer.getPhoneNumber() + ", photo" + producer.getPhoto() + " at " + new Date().toString());
			logsService.create(logs);

			return "redirect:/admin/producer.html";
		} else {
			modelMap.put("used", "This producer name is already");
			modelMap.put("producer", new Producer());
			return "producer.create";
		}
	}

	@RequestMapping(value = "search", method = RequestMethod.POST)
	public String search(ModelMap modelMap, HttpServletRequest request) {
		String keyword = request.getParameter("keyword");
		List<Producer> producer = producerService.search(keyword);
		PagedListHolder pagedListHolder = new PagedListHolder(producer);
		PagedListHolder pagedListHolder2 = new PagedListHolder(producerService.findAll());
		int page = ServletRequestUtils.getIntParameter(request, "ph", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		modelMap.put("keyword", keyword);
		if (producer.isEmpty()) {
			modelMap.put("result", 1);
			modelMap.put("pagedListHolder", pagedListHolder2);
		} else {
			modelMap.put("result", 2);
			modelMap.put("pagedListHolder", pagedListHolder);
		}
		return "producer.index";
	}

	private String saveImage(MultipartFile image) {
		try {
			File file = new File(
					servletContext.getRealPath("/") + "/assets/user/images/producer/" + image.getOriginalFilename());
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
