package com.musala.task.utils;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Component
public class ImageUtils {
    public static String encodeFileToBase64Binary(File file) throws IOException {
        String encodedFile;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);
            encodedFile = Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

        return encodedFile;
    }

    public static void saveMedicationImageToPath(String base64Image, String imageName, String path) throws IOException {
        Files.createDirectories(Paths.get(path));
        try (OutputStream stream = Files.newOutputStream(Paths.get(path + imageName + ".jpg"))) {
            byte[] imageByte = org.apache.tomcat.util.codec.binary.Base64.decodeBase64(base64Image);
            stream.write(imageByte);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
