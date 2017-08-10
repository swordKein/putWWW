package kr.kein.putwww;

import java.util.Map;

import kr.kein.putwww.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController {

	private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

	// inject via application.properties.bk
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@Autowired
	private TestService testService;

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {

		logger.debug("Welcome {}", "testing");
		logger.debug("Welcome {}", "testing2:"+this.message);

		//String r1 = testService.getTest();
		String r2 = testService.getTest();
		System.out.println("#DB result:"+r2);
		model.put("message", this.message);
		return "welcome";
	}

	@RequestMapping("/hello")
	public @ResponseBody String hello(Map<String, Object> model) {

		logger.debug("Hello!");
		return "Hello!";
	}

}