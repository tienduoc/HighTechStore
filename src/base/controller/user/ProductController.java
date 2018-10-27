package base.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import base.entities.*;
import base.service.*;

@Controller
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	private ProducerService producerService;

	@Autowired
	private CategoryService categoryService; 
	
	@Autowired
	private ProductService productService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.put("product", productService.findActive());
		return "product.list";
	}
	
	@RequestMapping(value = "search", method = RequestMethod.POST)
	public String search(ModelMap modelMap, HttpServletRequest request) {
		String keyword = request.getParameter("keyword");
		modelMap.put("keyword", keyword);
		modelMap.put("product", productService.search(keyword));
		return "product.search";
	}
	
	@RequestMapping(value = "category/{id}", method = RequestMethod.GET)
	public String category(@PathVariable("id") Integer id, 
			ModelMap modelMap) {
		Category category = categoryService.find(id);
		modelMap.put("category", category);
		modelMap.put("products", category.getProducts());
		return "product.category";
	}
	
	@RequestMapping(value = "rcategory/{id}", method = RequestMethod.GET)
	public String rcategory(@PathVariable("id") Integer id, 
			ModelMap modelMap) {
		Category category = categoryService.find(id);
		modelMap.put("category", category);
		modelMap.put("products", productService.findByParentCategory(id));
		return "product.category";
	}
	
	
	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Integer id, 
			ModelMap modelMap) {
		Product product = productService.find(id);
		modelMap.put("title", "Product Detail");
		modelMap.put("product", productService.find(id));
		modelMap.put("parent", categoryService.getParent());
		modelMap.put("related", productService.findRelated(product));
		return "product.detail";
	}
	
	@RequestMapping(value = "producer/{id}", method = RequestMethod.GET)
	public String category(@PathVariable("id") int id, 
			ModelMap modelMap) {
		Producer producer = producerService.find(id);
		modelMap.put("producer", producer);
		modelMap.put("products", productService.findByProducer(id));
		return "product.producer";
	}
	
}
