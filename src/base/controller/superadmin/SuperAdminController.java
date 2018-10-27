package base.controller.superadmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import base.service.*;

@Controller
@RequestMapping("superadmin")
public class SuperAdminController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ContactService contactService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.put("orders", orderService.findAll());
		modelMap.put("products", productService.findActive());
		modelMap.put("contacts", contactService.findAll());
		return "superadmin.index";
	}
}
