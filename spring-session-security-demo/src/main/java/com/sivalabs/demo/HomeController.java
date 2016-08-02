/**
 * 
 */
package com.sivalabs.demo;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Siva
 *
 */
@Controller
public class HomeController
{
	
	private static final AtomicInteger UserId = new AtomicInteger(0);
	
	@RequestMapping("/")
	public String home(Model model)
	{
		return "index";
	}
	
	@RequestMapping("/add-simple-attrs")
	public String handleSimpleSessionAttributes(HttpServletRequest req, HttpServletResponse resp)
	{
		String attributeName = req.getParameter("attributeName");
		String attributeValue = req.getParameter("attributeValue");
		req.getSession().setAttribute(attributeName, attributeValue);		
		return "redirect:/";
	}
	
	@RequestMapping("/add-object-attrs")
	public String handleObjectSessionAttributes(HttpServletRequest req, HttpServletResponse resp)
	{
		String name = req.getParameter("name");
		User user = new User();
		user.setId(UserId.incrementAndGet());
		user.setName(name);
		req.getSession().setAttribute("USER_ID_"+user.getId(), user);
		return "redirect:/";
	}
}
