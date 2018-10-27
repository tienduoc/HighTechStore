package base.tags;

import javax.servlet.jsp.JspWriter;

import org.springframework.beans.factory.annotation.*;
import org.springframework.beans.factory.config.*;
import org.springframework.web.context.*;
import org.springframework.web.servlet.tags.*;
import base.entities.*;
import base.service.ProducerService;

@SuppressWarnings("serial")
public class producerTag extends RequestContextAwareTag {
	
	@Autowired
	private ProducerService producerService;
	
	@Override
	protected int doStartTagInternal() throws Exception {
		if(producerService == null) {
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
			for(Producer producer : producerService.findAll()) {
				writer.print("<div class=\"item\">\r\n" + 
						"\r\n" + 
						"						<a href=\""+pageContext.getRequest().getServletContext().getContextPath()+"/product/producer/"+producer.getId()+".html\">\r\n" + 
						"\r\n" + 
						"							<figure>\r\n" + 
						"								<figcaption class=\"text-overlay\">\r\n" + 
						"									<div class=\"info\">\r\n" + 
						"										<h4>"+producer.getName()+"</h4>\r\n" + 
						"									</div>\r\n" + 
						"									<!-- /.info -->\r\n" + 
						"								</figcaption>\r\n" + 
						"\r\n" + 
						"								<img\r\n" + 
						"									src=\"${pageContext.request.contextPath }/assets/user/images/blank.gif\"\r\n" + 
						"									data-echo=\""+pageContext.getRequest().getServletContext().getContextPath()+"/assets/user/images/producer/"+producer.getPhoto()+"\"\r\n" + 
						"									class=\"img-responsive\" alt=\"\">\r\n" + 
						"\r\n" + 
						"							</figure>\r\n" + 
						"						</a>\r\n" + 
						"					</div>");
			}
		} catch (Exception e) {
		}
		
	}

	
	
}
