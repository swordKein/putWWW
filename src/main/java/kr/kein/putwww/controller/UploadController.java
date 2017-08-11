package kr.kein.putwww.controller;

import kr.kein.putwww.model.UploadModel;
import kr.kein.putwww.service.TestService;
import kr.kein.putwww.util.DetectEncoding;
import kr.kein.putwww.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	@Autowired
	private TestService testService;

	private static String UPLOADED_FOLDER = "e://upload//";

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {

		logger.debug("Upload {}", "testing");

		//String r1 = testService.getTest();
		String r2 = testService.getTest();
		System.out.println("#DB result:"+r2);
		model.put("message", "Upload Test");
		return "upload";
	}


	@PostMapping("/api/upload")
	public ResponseEntity<?> uploadFile(
			@RequestParam("file") MultipartFile uploadfile) {

		logger.debug("Single file upload!");

		if (uploadfile.isEmpty()) {
			return new ResponseEntity("please select a file!", HttpStatus.OK);
		}

		try {

			FileUtils.saveUploadedFiles(Arrays.asList(uploadfile),UPLOADED_FOLDER);

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity("Successfully uploaded - " +
				uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);

	}

	@PostMapping("/api/upload/multi")
	public ResponseEntity<?> uploadFileMulti(
			@RequestParam("extraField") String extraField,
			@RequestParam("files") MultipartFile[] uploadfiles) {

		logger.debug("Multiple file upload!");

		String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
				.filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

		if (StringUtils.isEmpty(uploadedFileName)) {
			return new ResponseEntity("please select a file!", HttpStatus.OK);
		}

		try {

			FileUtils.saveUploadedFiles(Arrays.asList(uploadfiles),UPLOADED_FOLDER);

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity("Successfully uploaded - "
				+ uploadedFileName, HttpStatus.OK);

	}

	// maps html form to a Model
	@PostMapping("/api/upload/multi/model")
	public ResponseEntity<?> multiUploadFileModel(@ModelAttribute UploadModel model) {

		logger.debug("Multiple file upload! With UploadModel");

		try {

			FileUtils.saveUploadedFiles(Arrays.asList(model.getFiles()),UPLOADED_FOLDER);

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);

	}

	@PostMapping("/api/detect")
	public ResponseEntity<?> detectFileCharset(
			@RequestParam("file") MultipartFile uploadfile) {

		logger.debug("Single file upload!");

		if (uploadfile.isEmpty()) {
			return new ResponseEntity("please select a file!", HttpStatus.OK);
		}

		try {

			FileUtils.saveUploadedFiles(Arrays.asList(uploadfile), UPLOADED_FOLDER);

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity("Successfully uploaded - " +
				uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);

	}



	@PostMapping("/api/read")
	@ResponseBody
	public HashMap<String, Object> readFile(
			@RequestParam("files") MultipartFile uploadfile) {
		logger.debug("Single file Read start!");

		byte[] readByte = null;
		String result_utf8 = "";
		String result_ms949 = "";
		String detCharset = "";

		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		if (uploadfile.isEmpty()) {
			//return new ResponseEntity("please select a file!", HttpStatus.OK);
			return null;
		}

		try {
			readByte = FileUtils.readUploadedFiles(Arrays.asList(uploadfile));
			int byteSize = readByte.length;
			int viewSize = 0;

			System.out.println("#byteSize::"+byteSize);

			result_utf8 = FileUtils.convertByteToString(readByte, "utf-8", 30);
			result_ms949 = FileUtils.convertByteToString(readByte, "ms949", 30);

			InputStream fis = new ByteArrayInputStream(readByte);
			//FileInputStream fis = new FileInputStream(uploadfile.getOriginalFilename());
			detCharset = DetectEncoding.guessEncoding(fis);
			System.out.println("# DetectEncoding.detectJchardet result::"+detCharset);

		} catch (Exception e) {
			//return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}

		//return new ResponseEntity("Successfully uploaded - " +
		//		uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);

		resultMap.put("readbyte", readByte);
		resultMap.put("strutf8", result_utf8);
		resultMap.put("strms949", result_ms949);
		resultMap.put("detCharset", detCharset);
		return resultMap;
	}
}