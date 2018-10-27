package base.tags;

import javax.servlet.jsp.JspWriter;

import org.springframework.beans.factory.annotation.*;
import org.springframework.beans.factory.config.*;
import org.springframework.web.context.*;
import org.springframework.web.servlet.tags.*;
import base.entities.*;
import base.service.CategoryService;

@SuppressWarnings("serial")
public class categoryTag extends RequestContextAwareTag {
	
	@Autowired
	private CategoryService categoryService;
	
	@Override
	protected int doStartTagInternal() throws Exception {
		if(categoryService == null) {
			WebApplicationContext wac = getRequestContext()
					.getWebApplicationContext();
			AutowireCapableBeanFactory acbf = wac
					.getAutowireCapableBeanFactory();
			acbf.autowireBean(this);
		}
		return SKIP_BODY;
	}

	@Override
	public void doFinally() {
		JspWriter writer = pageContext.getOut();
		try {
			for(Category categories : this.categoryService.getParent()) {
				writer.print("<li class=\"menu-item\">");
				writer.print("<a title=\"category\"\r\n" + 
				"href=\""+pageContext.getRequest().getServletContext().getContextPath()+"/product/rcategory/"+categories.getId()+".html\">"+ 
						categories.getName() +"</a>");						
				writer.print("</li>");
			}
		} catch (Exception e) {
		}
		
	}

	
	
}
