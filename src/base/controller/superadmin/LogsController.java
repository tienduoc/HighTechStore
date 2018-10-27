package base.controller.superadmin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import base.entities.*;
import base.service.*;

@Controller
@RequestMapping("superadmin/logs")
public class LogsController {

	@Autowired
	private LogsService logsService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap, HttpServletRequest request) {
		List<Logs> logs = logsService.findAll();
		PagedListHolder pagedListHolder = new PagedListHolder(logs);
		int page = ServletRequestUtils.getIntParameter(request, "ph", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		modelMap.put("pagedListHolder", pagedListHolder);
		return "logs.index";
	}
	
	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
		modelMap.put("logs", logsService.find(id));
		return "logs.detail";
	}
	
	@RequestMapping(value = "search", method = RequestMethod.POST)
	public String search(ModelMap modelMap, HttpServletRequest request) {
		String keyword = request.getParameter("keyword");
		List<Logs> logs = logsService.search(keyword);
		PagedListHolder pagedListHolder = new PagedListHolder(logs);
		PagedListHolder pagedListHolder2 = new PagedListHolder(logsService.findAll());
		int page = ServletRequestUtils.getIntParameter(request, "ph", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		modelMap.put("keyword", keyword);
		if(logs.isEmpty()) {			
			modelMap.put("result", 1);
			modelMap.put("pagedListHolder", pagedListHolder2);		
		} else {
			modelMap.put("result", 2);
			modelMap.put("pagedListHolder", pagedListHolder);
		}		
		return "logs.index";
	}
}
