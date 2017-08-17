package kr.kein.putwww.controller;

import kr.kein.putwww.model.UploadModel;
import kr.kein.putwww.service.TestService;
import kr.kein.putwww.util.DetectEncoding;
import kr.kein.putwww.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MovieController {
	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private TestService testService;

	@RequestMapping("/mv")
	public String welcome(Map<String, Object> model) {

		logger.debug("Movie {}", "testing");

		//String r1 = testService.getTest();
		String r2 = testService.getTest();
		System.out.println("#DB result:"+r2);
		model.put("message", "Upload Test");
		return "movie";
	}

}