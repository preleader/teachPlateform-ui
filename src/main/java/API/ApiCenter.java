package API;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import API.repo.Config;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/api")
public class ApiCenter {
	private static RestTemplate restTemplate;
	@Autowired
	private Config config;
	static {
		restTemplate = new RestTemplate();
	}
	@RequestMapping(method = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
	public String remote(HttpServletRequest request) {		
		String address = request.getParameter("address"),URI=config.getRemoteurl(),result=null;
		Map<String,Object> reqMap = getParams(request);
		URI += address+"?";
		for(String key:reqMap.keySet()) {
			URI += key+"={"+key+"}&";
		}
		URI = URI.substring(0,URI.length()-1);
		String method = request.getMethod();
		JSONObject json = new JSONObject();
		switch(method) {
			case "POST":
				HttpHeaders headers = new HttpHeaders();
		        String data = new String();
		        HttpEntity<String> formEntity = new HttpEntity<String>(data, headers);
				result = restTemplate.postForObject(URI, formEntity, String.class,reqMap);
				break;
			case "DELETE":
				try {
					restTemplate.delete(URI, reqMap);
					json.put("success", true);
					json.put("msg", "删除成功");
				}catch (Exception e) {
					json.put("success", false);
					json.put("msg", "删除失败");
				}
				result = json.toString();
				break;
			case "PUT":
				try {
					restTemplate.put(URI, reqMap);
					json.put("success", true);
					json.put("msg", "更新成功");
				}catch (Exception e) {
					json.put("success", false);
					json.put("msg", "更新失败");
				}
				result = json.toString();
				break;
			case "GET":
				result = restTemplate.getForObject(URI, String.class, reqMap);
				break;
			default:
				result = "不支持的请求类型";
				break;
		}
		return result;
	}

	private Map<String, Object> getParams(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration<String> keys = request.getParameterNames();
		while(keys.hasMoreElements()) {
			String key = keys.nextElement();
			if(!key.contains("address"))
				map.put(key, request.getParameter(key));
		}
		return map;
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}
}
