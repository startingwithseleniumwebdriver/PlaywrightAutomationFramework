package com.automation.core.framework.logging;

import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;

import com.automation.common.framework.exceptions.FrameworkException;
import com.automation.core.framework.config.ConfigFactorySingleton;
import com.automation.core.framework.config.FrameworkConfig;
import com.automation.core.framework.utils.PathResolver;
import com.automation.core.framework.utils.ThreadSafeContext;

public final class LoggerManager {

    private static final ThreadLocal<Logger> threadLogger = new ThreadLocal<>();
    private static final ThreadLocal<String> loggerNameThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Path> threadLogFile = new ThreadLocal<>();
    private static boolean initialized = false;

    private LoggerManager() {}

    private static synchronized void initializeSystemProps() {
        if (initialized) return;
        FrameworkConfig config = ConfigFactorySingleton.getConfig();
        System.setProperty("log.level", config.logLevel());
        System.setProperty("log.dir", config.logDir());
        System.setProperty("log.pattern", config.logPattern());
        initialized = true;
    }

    public static Logger getLogger() {
        String testId = ThreadSafeContext.getTestId();
        if (threadLogger.get() != null) return threadLogger.get();
        if (testId == null) throw new IllegalStateException("Test ID not set in ThreadSafeContext");
        return initLogger(testId);
    }

    public static Logger getLogger(String testId) {
        ThreadSafeContext.setTestId(testId);
        return initLogger(testId);
    }

    private static Logger initLogger(String testId) {
        initializeSystemProps();

        try {
            long threadId = Thread.currentThread().getId();
            String logLevel = System.getProperty("log.level", "INFO");
            String logPattern = System.getProperty("log.pattern", "%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n");

            Path logFilePath = PathResolver.resolveLogPath(testId, threadId);
            Files.createDirectories(logFilePath.getParent());
            threadLogFile.set(logFilePath);

            LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
            Configuration config = ctx.getConfiguration();

            PatternLayout layout = PatternLayout.newBuilder()
                    .withPattern(logPattern)
                    .withConfiguration(config)
                    .build();

            FileAppender fileAppender = FileAppender.newBuilder()
                    .withFileName(logFilePath.toString())
                    .setName("FileAppender-" + threadId)
                    .withAppend(true)
                    .setLayout(layout)
                    .setConfiguration(config)
                    .withLocking(false)
                    .setImmediateFlush(true)
                    .build();
            fileAppender.start();

            String loggerName = "Logger-" + threadId;
            LoggerConfig loggerConfig = new LoggerConfig(loggerName, Level.toLevel(logLevel), true); // true = inherit root's console

            loggerConfig.addAppender(fileAppender, null, null);

            config.addLogger(loggerName, loggerConfig);
            ctx.updateLoggers();

            Logger logger = LogManager.getLogger(loggerName);
            threadLogger.set(logger);
            loggerNameThreadLocal.set(loggerName);
            return logger;

        } catch (Exception e) {
            throw new FrameworkException("Failed to initialize logger", e);
        }
    }

    public static Path getLogFilePath() {
        return threadLogFile.get();
    }

    public static void removeLogger() {
        String loggerName = loggerNameThreadLocal.get();
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        Configuration config = ctx.getConfiguration();

        if (loggerName != null) {
            config.removeLogger(loggerName);
            ctx.updateLoggers();
        }

        threadLogger.remove();
        loggerNameThreadLocal.remove();
        threadLogFile.remove();
    }
}