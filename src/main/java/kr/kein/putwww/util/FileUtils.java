package kr.kein.putwww.util;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileUtils {

    //save file
    public static void saveUploadedFiles(List<MultipartFile> files, String path) throws IOException {

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //next pls
            }

            byte[] bytes = file.getBytes();
            Path path2 = Paths.get(path + file.getOriginalFilename());
            Files.write(path2, bytes);

        }

    }

    public static byte[] readUploadedFiles(List<MultipartFile> files) throws IOException {
        byte[] bytes = null;

        if (files != null && files.size() > 0) {
            //for (MultipartFile file : files) {
            //if (file.isEmpty()) {
            //	continue; //next pls
            //}

            bytes = files.get(0).getBytes();
            //while ((count = fis.read(b)) != -1) {
            //	for (int i = 0; i < count; i++) {
            //		System.out.print((char) b[i]);
            //	}
            //}
        }
        //}
        return bytes;
    }

    public static String convertByteToString(byte[] req, String charset, int size) {
        String result = "";
        try {
            if (req != null) {
                result = new String(req, charset);
                if (result.length() > size) result = result.substring(0,size);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static MultipartFile convertFileToMultipart(String pathStr, String filename) {
        //Path path = Paths.get("/path/to/the/file.txt");
        //String name = "file.txt";
        //String originalFileName = "file.txt";
        Path path = Paths.get(pathStr);
        String originalFileName = filename;
        String contentType = "text/plain";

        byte[] content = null;
        MultipartFile result = null;

        try {
            content = Files.readAllBytes(path);
            result = new MockMultipartFile(filename,
                    originalFileName, contentType, content);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File convFile = new File( multipart.getOriginalFilename());
        multipart.transferTo(convFile);
        return convFile;
    }
}
