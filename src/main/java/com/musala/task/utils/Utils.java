package com.musala.task.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Component
public class Utils {

    static String imagePath2 = "D:/musala_task/images/";
    //TODO:check why it's not working as static
    @Value("${image.path}")
    String imagePath;

    public static String encodeFileToBase64Binary(File file) {
        String encodedFile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);
            encodedFile = Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return encodedFile;
    }

    public static void saveMedicationImageToPath(String base64Image, String imageName) throws IOException {
        Files.createDirectories(Paths.get(imagePath2));
        try (OutputStream stream = Files.newOutputStream(Paths.get(imagePath2 + imageName + ".jpg"))) {
            byte[] imageByte = org.apache.tomcat.util.codec.binary.Base64.decodeBase64(base64Image);
            stream.write(imageByte);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
