package API;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/resource")
public class ResourceCenter {
	
	@RequestMapping
	public String resource(HttpServletRequest request) {
		String location = request.getParameter("location");
		return location;
	}
}
