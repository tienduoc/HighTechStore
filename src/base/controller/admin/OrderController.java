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
@RequestMapping("admin/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private OrderStatusService orderStatusService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private LogsService logsService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap, HttpServletRequest request) {
		PagedListHolder pagedListHolder = new PagedListHolder(orderService.findAll());
		int page = ServletRequestUtils.getIntParameter(request, "ph", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		modelMap.put("pagedListHolder", pagedListHolder);
		return "orders.index";
	}


	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
		modelMap.put("orders", orderDetailService.find(id));
		return "orders.detail";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, ModelMap modelMap) {
		modelMap.put("orders", orderService.find(id));
		modelMap.put("status", orderStatusService.findAll());
		return "orders.update";
	}


	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String edit(HttpServletRequest request,ModelMap modelMap) {
		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		}
		Orders orders = orderService.find(Integer.parseInt(request.getParameter("id")));
		
		Logs logs = new Logs();
		logs.setDescription(acc + " update status of order " + orders.getId() + " from " + orders.getOrderStatus() +" to " + request.getParameter("sttOption")+ " at "+ new Date().toString());
		logsService.create(logs);
		
		orders.setAccountByCheckMan(accountService.find(acc));
		orders.setOrderStatus(orderStatusService.findByName(request.getParameter("sttOption")));
		orderService.update(orders);
			return "redirect:/admin/orders.html";		
	}	
	
	@RequestMapping(value = "search", method = RequestMethod.POST)
	public String search(ModelMap modelMap, HttpServletRequest request) {
		String keyword = request.getParameter("username");
		List<Orders> orders = orderService.search(keyword);
		PagedListHolder pagedListHolder = new PagedListHolder(orders);
		PagedListHolder pagedListHolder2 = new PagedListHolder(orderService.findAll());
		int page = ServletRequestUtils.getIntParameter(request, "ph", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		modelMap.put("keyword", keyword);
		if (orders.isEmpty()) {
			modelMap.put("result", 1);
			modelMap.put("pagedListHolder", pagedListHolder2);
		} else {
			modelMap.put("result", 2 );
			modelMap.put("pagedListHolder", pagedListHolder);
		}		
		return "orders.index";
	}
	
}

