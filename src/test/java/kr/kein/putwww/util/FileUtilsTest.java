package kr.kein.putwww.util;

import kr.kein.putwww.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FileUtilsTest {

	@Test
	public void testFileReadTest() throws Exception{
		String fileName = "wan.txt";
		String filePath = "e:\\upload\\" + fileName;

		List<MultipartFile> files = new ArrayList<MultipartFile>();
		MultipartFile file = FileUtils.convertFileToMultipart(filePath,fileName);
		files.add(file);

		File inFile = new File(filePath);
		FileInputStream fileStream = new FileInputStream(inFile);
		String detectCharset = DetectEncoding.guessEncoding(fileStream);
		System.out.println("#Result orig: charset::"+detectCharset);

		byte[] result = FileUtils.readUploadedFiles(files);

		String detCharset2 = DetectEncoding.detectUniversalDetector(result);
		System.out.println("#Result orig: detectJchardet2::"+detCharset2);


		String str = new String (result);
		System.out.println("#Result orig: byte::"+result+" | size:"+str.length()+" | str:"+str.substring(0,40));

		String strUtf8 = FileUtils.convertByteToString(result, "utf-8", 40);
		System.out.println("#Result utf8 str:"+strUtf8);

		boolean isCrack = strUtf8.contains("�");
		System.out.println("#Result utf8 is_CRACK?:"+isCrack);

		String strCp949 = FileUtils.convertByteToString(result, "ms949", 40);
		System.out.println("#Result cp949 str:"+strCp949);
	}
}
