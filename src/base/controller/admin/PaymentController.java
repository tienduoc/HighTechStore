package base.controller.admin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import base.entities.*;
import base.service.*;

@Controller
@RequestMapping("admin/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private LogsService logsService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.put("listPayment", paymentService.findAll());
		return "payment.index";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(ModelMap modelMap) {
		modelMap.put("payment", new Payment());
		return "payment.create";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(ModelMap modelMap, @ModelAttribute("payment") Payment payment) {
		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		}
		
		Payment ExtPm = paymentService.findByName(payment.getPayment());
		if (ExtPm == null) {

			paymentService.create(payment);

			Logs logs = new Logs();
			logs.setDescription(acc + " create payment " + payment.getPayment() + " at " + new Date().toString());
			logsService.create(logs);

			return "redirect:/admin/payment.html";
		} else {
			modelMap.put("used", "This payment name is already");
			modelMap.put("payment", new Payment());
			return "payment.create";
		}
	}

	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
		modelMap.put("payment", paymentService.find(id));
		return "payment.detail";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, ModelMap modelMap) {
		modelMap.put("payment", paymentService.find(id));
		return "payment.update";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String edit(@ModelAttribute("payment") Payment payment, ModelMap modelMap) {
		Payment ExtPm = paymentService.findByName(payment.getPayment());
		Payment ext = paymentService.find(payment.getId());

		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		}
		String a = null;
		if(ExtPm != null) {
			a = ExtPm.getPayment();
		}
		String b = payment.getPayment();

		if ((a == null) || a.equalsIgnoreCase(b) == true) {	
			paymentService.update(payment);

			Logs logs = new Logs();
			logs.setDescription(acc + " update payment name: " + ext.getPayment() + " to " + payment.getPayment()
					+ " at " + new Date().toString());
			logsService.create(logs);
			return "redirect:/admin/payment.html";
		} else {
			modelMap.put("used", "This payment name is already");
			modelMap.put("payment", new Payment());
			return "payment.create";
		}
	}
}
