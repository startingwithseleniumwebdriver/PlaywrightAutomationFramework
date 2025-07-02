package com.automation.core.framework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import io.qameta.allure.Allure;

public class AllureAttachmentUtil {

    private AllureAttachmentUtil() {}

    public static void attachLogToAllure(String name, Path logPath) {
        if (logPath != null && Files.exists(logPath)) {
            try (InputStream in = Files.newInputStream(logPath)) {
                Allure.addAttachment(name, "text/plain", in, ".log");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
