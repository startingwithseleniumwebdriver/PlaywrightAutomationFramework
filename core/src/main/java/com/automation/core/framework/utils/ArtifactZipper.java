package com.automation.core.framework.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.logging.log4j.Logger;

import com.automation.core.framework.config.ConfigFactorySingleton;
import com.automation.core.framework.logging.LoggerManager;

import io.qameta.allure.Allure;

public class ArtifactZipper {

    private static final Logger log = LoggerManager.getLogger();

    private ArtifactZipper() {}

    public static void zipAndAttachArtifacts() {
        String testId = ThreadSafeContext.getTestId();
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String baseZipDir = ConfigFactorySingleton.getConfig().artifactZipDir();
        Path zipFilePath = Paths.get(System.getProperty("user.dir"),baseZipDir, "artifacts-" + FileNameSanitizer.sanitize(testId) + "-" + timestamp + ".zip");

        List<String> artifactDirs = Arrays.asList(
            ConfigFactorySingleton.getConfig().logDir(),
            ConfigFactorySingleton.getConfig().screenshotsDir(),
            ConfigFactorySingleton.getConfig().tracingDir(),
            ConfigFactorySingleton.getConfig().videosDir()
        );

        try {
            Files.createDirectories(zipFilePath.getParent());
            try (FileOutputStream fos = new FileOutputStream(zipFilePath.toFile());
                 ZipOutputStream zos = new ZipOutputStream(fos)) {

                for (String dir : artifactDirs) {
                    zipDir(Paths.get(System.getProperty("user.dir"),dir), zos);
                }
            }

            log.info("Artifacts zipped to {}", zipFilePath);
            attachToAllure(zipFilePath);

        } catch (Exception e) {
            log.error("Error zipping or attaching artifacts", e);
        }
    }

    private static void zipDir(Path folder, ZipOutputStream zos) throws IOException {
        if (!Files.exists(folder)) return;

        Files.walk(folder)
             .filter(Files::isRegularFile)
             .forEach(path -> {
                 try (InputStream is = Files.newInputStream(path)) {
                     ZipEntry entry = new ZipEntry(folder.relativize(path).toString());
                     zos.putNextEntry(entry);
                     byte[] buffer = new byte[1024];
                     int length;
                     while ((length = is.read(buffer)) > 0) {
                         zos.write(buffer, 0, length);
                     }
                     zos.closeEntry();
                 } catch (IOException e) {
                     log.warn("Failed to zip {}", path, e);
                 }
             });
    }

    private static void attachToAllure(Path zipPath) {
        try (InputStream is = Files.newInputStream(zipPath)) {
            Allure.addAttachment("Test Artifacts", "application/zip", is, ".zip");
            log.info("Attached artifact zip to Allure: {}", zipPath);
        } catch (IOException e) {
            log.warn("Failed to attach zip to Allure", e);
        }
    }
}
