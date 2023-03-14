package com.musala.task.utils;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

@Component
public class Utils {

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

    @Scheduled(fixedRate = 1000)
    public void scheduleBatteryChecking() {
        Random randI = new Random();
        int batteryCapacity = randI.nextInt(100);
        batteryCapacity = batteryCapacity + 1;
    }
}
