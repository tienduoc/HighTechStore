package base.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import base.entities.*;
import base.service.*;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("cart")
public class CartController {

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
		return "cart.index";
	}

	@RequestMapping(value = "beforeCheckOut", method = RequestMethod.GET)
	public String beforeCheckOut(ModelMap modelMap, HttpSession session) {
		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			modelMap.addAttribute("account", accountService.find(userDetail.getUsername()));
			acc = accountService.find(userDetail.getUsername()).toString();
		}
		if (acc == null) {
			return "home.login";
		} else {
			return "cart.beforeCheckOut";
		}
	}



	@SuppressWarnings("unchecked")
	@RequestMapping(value = "buy/{id}", method = RequestMethod.GET)
	public String buy(@PathVariable("id") Integer id, HttpSession session) {

		if (session.getAttribute("cart") == null) {
			List<Item> cart = new ArrayList<Item>();
			cart.add(new Item(productService.find(id), 1));
			session.setAttribute("cart", cart);
		} else {
			List<Item> cart = (List<Item>) session.getAttribute("cart");
			int index = isExits(id, session);
			if (index == -1) {
				cart.add(new Item(productService.find(id), 1));
			} else {
				int quantity = cart.get(index).getQuantity();
				int pq = cart.get(index).getProduct().getQuantity();
				if (quantity >= pq) {
					quantity = pq;
				} else {
					quantity++;
				}
				cart.get(index).setQuantity(quantity);
			}
			session.setAttribute("cart", cart);
		}
		return "redirect:/cart/index.html";
	}

	@RequestMapping(value = "check", method = RequestMethod.GET)
	public String repairPurchase(HttpServletRequest request, HttpSession session, ModelMap modelMap) {
		String acc = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			acc = userDetail.getUsername().toString();
		}
		modelMap.put("acc", accountService.find(acc));

		PayPalTemp temp = new PayPalTemp();
		temp.setCustomerNote(request.getParameter("customerNote"));
		temp.setAddress(request.getParameter("address"));
		temp.setTotal(Double.parseDouble(request.getParameter("total")));
		session.setAttribute("temp", temp);

		return "cart.check";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "COD", method = RequestMethod.POST)
	public String COD(HttpSession session) {
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
			Orders order = new Orders();
			PayPalTemp temp = (PayPalTemp) session.getAttribute("temp");
			order.setCustomerNote(temp.getCustomerNote());
			order.setAddress(temp.getAddress());
			order.setTotal(temp.getTotal());
			order.setAccountByCheckMan(accountService.find("system"));
			order.setOrderStatus(orderStatusService.find(1));
			order.setPayment(paymentService.find(1));
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

	@SuppressWarnings("unchecked")
	private int isExits(int id, HttpSession session) {
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getProduct().getId() == id) {
				return i;
			}
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Integer id, HttpSession session) {
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		int index = isExits(id, session);
		cart.remove(index);
		session.setAttribute("cart", cart);
		return "redirect:/cart/index.html";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, HttpSession session) {
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		String[] quantity = request.getParameterValues("quantity");
		for (int i = 0; i < cart.size(); i++) {
			cart.get(i).setQuantity(Integer.parseInt(quantity[i]));
		}
		session.setAttribute("cart", cart);
		return "redirect:/cart/index.html";
	}

}
