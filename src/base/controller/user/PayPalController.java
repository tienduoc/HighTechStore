package base.controller.user;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import base.entities.*;
import base.paypal.PayPalSucess;
import base.service.*;

@Controller
@RequestMapping(value = "paypal")
public class PayPalController {

	@Autowired
	private ProductService productService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderStatusService orderStatusService;

	@Autowired
	private OrderDetailService ordersDetailService;

	@Autowired
	private AccountService accountService;

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@SuppressWarnings({ "static-access", "unchecked" })
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String success(ModelMap modelMap, HttpServletRequest request, HttpSession session) {

		PayPalSucess ps = new PayPalSucess();
		modelMap.put("result", ps.getPayPal(request));

		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		} 
		
		// Luu hoa don
		Orders order = new Orders();
		PayPalTemp temp = (PayPalTemp) session.getAttribute("temp");
		order.setCustomerNote(temp.getCustomerNote());
		order.setAddress(temp.getAddress());
		order.setTotal(temp.getTotal());
		order.setAccountByCheckMan(accountService.find("system"));
		order.setOrderStatus(orderStatusService.find(1));
		order.setPayment(paymentService.find(2));
		order.setTimeCreation(new Date());
		order.setAccountByCustomer(accountService.find(acc));
		orderService.save(order);

		// Luu chi tiet hoa don
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		for (int i = 0; i < cart.size(); i++) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setId(new OrderDetailId(order.getId(), cart.get(i).getProduct().getId()));
			orderDetail.setOrders(order);
			orderDetail.setPrice(cart.get(i).getProduct().getPrice());
			orderDetail.setQuantity(cart.get(i).getQuantity());
			orderDetail.setProduct(cart.get(i).getProduct());
			ordersDetailService.save(orderDetail);

			Product product = productService.find(cart.get(i).getProduct().getId());
			product.setQuantity(cart.get(i).getProduct().getQuantity() - cart.get(i).getQuantity());
			productService.update(product);
		}
		session.removeAttribute("cart");

		return "cart.thanks";
	}
}
