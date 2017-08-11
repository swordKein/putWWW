package kr.kein.putwww.util;

import com.glaforge.i18n.io.CharsetToolkit;
import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;
import org.mozilla.universalchardet.UniversalDetector;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DetectEncoding {
    public static String guessEncoding(InputStream input) throws Exception {
        // Load input data
        long count = 0;
        int n = 0, EOF = -1;
        byte[] buffer = new byte[4096];
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        while ((EOF != (n = input.read(buffer))) && (count <= Integer.MAX_VALUE)) {
            output.write(buffer, 0, n);
            count += n;
        }

        if (count > Integer.MAX_VALUE) {
            throw new RuntimeException("Inputstream too large.");
        }

        byte[] data = output.toByteArray();

        // Detect encoding
        Map<String, int[]> encodingsScores = new HashMap<>();

        // * GuessEncoding
        updateEncodingsScores(encodingsScores, new CharsetToolkit(data).guessEncoding().displayName());

        // * ICU4j
        CharsetDetector charsetDetector = new CharsetDetector();
        charsetDetector.setText(data);
        charsetDetector.enableInputFilter(true);
        CharsetMatch cm = charsetDetector.detect();
        if (cm != null) {
            updateEncodingsScores(encodingsScores, cm.getName());
        }

        // * juniversalchardset
        UniversalDetector universalDetector = new UniversalDetector(null);
        universalDetector.handleData(data, 0, data.length);
        universalDetector.dataEnd();
        String encodingName = universalDetector.getDetectedCharset();
        if (encodingName != null) {
            updateEncodingsScores(encodingsScores, encodingName);
        }

        // Find winning encoding
        Map.Entry<String, int[]> maxEntry = null;
        for (Map.Entry<String, int[]> e : encodingsScores.entrySet()) {
            if (maxEntry == null || (e.getValue()[0] > maxEntry.getValue()[0])) {
                maxEntry = e;
            }
        }

        String winningEncoding = maxEntry.getKey();
        //dumpEncodingsScores(encodingsScores);
        return winningEncoding;
    }

    private static void updateEncodingsScores(Map<String, int[]> encodingsScores, String encoding) {
        String encodingName = encoding.toLowerCase();
        int[] encodingScore = encodingsScores.get(encodingName);

        if (encodingScore == null) {
            encodingsScores.put(encodingName, new int[] { 1 });
        } else {
            encodingScore[0]++;
        }
    }

    private static void dumpEncodingsScores(Map<String, int[]> encodingsScores) {
        System.out.println(toString(encodingsScores));
    }

    private static String toString(Map<String, int[]> encodingsScores) {
        String GLUE = ", ";
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, int[]> e : encodingsScores.entrySet()) {
            sb.append(e.getKey() + ":" + e.getValue()[0] + GLUE);
        }
        int len = sb.length();
        sb.delete(len - GLUE.length(), len);

        return "{ " + sb.toString() + " }";
    }
/*
    public static String detectJchardet(FileInputStream fis) {
        String encoding = "";
        byte[] buf = new byte[4096];
        UniversalDetector detector = new UniversalDetector(null);

        try {
            int nread = 0;
            while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
                detector.handleData(buf, 0, nread);
            }
            encoding = detector.getDetectedCharset();
            if (encoding != null && !"".equals(encoding)) {
                System.out.println("Detected encoding = " + encoding);
            } else {
                System.out.println("No encoding detected.");
            }

            detector.dataEnd();
            detector.reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encoding;
    }
*/

    public static String detectUniversalDetector(byte[] bytes) {
        UniversalDetector detector = new UniversalDetector(null);
        detector.handleData(bytes, 0, bytes.length);
        detector.dataEnd();
        String detectedCharset = detector.getDetectedCharset();
        if (detectedCharset != null && detector.isDone()
                //&& Charset.isSupported(detectedCharset)
                ) {
            return detectedCharset;
        }
        return detectedCharset;
    }
}
