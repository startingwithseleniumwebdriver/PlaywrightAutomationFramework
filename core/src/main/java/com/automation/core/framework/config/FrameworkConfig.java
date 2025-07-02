package com.automation.core.framework.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(Config.LoadType.MERGE)
@Sources({ "system:properties", "classpath:framework.properties" })
public interface FrameworkConfig extends Config {

	// General
	@Key("device.profile")
	@DefaultValue("DESKTOP")
	String deviceProfile();

	@Key("env")
	@DefaultValue("qa")
	String env();

	@Key("browser")
	@DefaultValue("CHROMIUM")
	String browser();

	@Key("headless")
	@DefaultValue("false")
	boolean headless();

	@Key("retryCount")
	@DefaultValue("2")
	int retryCount();

	@Key("wait.timeout")
	@DefaultValue("10")
	int waitTimeout();

	@Key("log.level")
	@DefaultValue("INFO")
	String logLevel();
	
	@Key("log.dir")
	@DefaultValue("target/logs")
	String logDir();
	
	@Key("log.pattern")
	@DefaultValue("%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n")
	String logPattern();

	@Key("artifact.zip.dir")
	@DefaultValue("target/artifacts")
	String artifactZipDir();

	// URLs
	@Key("api.url.qa")
	String apiUrlQa();

	@Key("api.url.dev")
	String apiUrlDev();

	@Key("api.url.prod")
	String apiUrlProd();

	@Key("web.url.qa")
	String webUrlQa();

	@Key("web.url.dev")
	String webUrlDev();

	@Key("web.url.prod")
	String webUrlProd();

	// Screenshot/Tracing
	@Key("screenshot.mode")
	@DefaultValue("FAILURE_ONLY")
	String screenshotMode();

	@Key("screenshot.fullPage")
	@DefaultValue("true")
	boolean screenshotFullPage();

	@Key("tracing.dir")
	@DefaultValue("target/traces")
	String tracingDir();

	@Key("screenshots.dir")
	@DefaultValue("target/screenshots")
	String screenshotsDir();

	@Key("videos.dir")
	@DefaultValue("target/videos")
	String videosDir();
	
	// Mobile
	@Key("mobile.emulation")
	@DefaultValue("false")
	boolean mobileEmulation();

	@Key("mobile.userAgent")
	String mobileUserAgent();

	@Key("mobile.viewport.width")
	@DefaultValue("375")
	int mobileViewportWidth();

	@Key("mobile.viewport.height")
	@DefaultValue("812")
	int mobileViewportHeight();

	@Key("mobile.devicePixelRatio")
	@DefaultValue("2")
	int mobileDevicePixelRatio();

	@Key("mobile.landscape")
	@DefaultValue("false")
	boolean mobileLandscape();

	// Excel
	@Key("excel.supported.types")
	String excelSupportedTypes();

	@Key("excel.mode")
	@DefaultValue("threadsafe")
	String excelMode();

	// Chromium
	@Key("browser.args.chromium")
	String chromiumArgs();

	@Key("browser.downloads.enabled.chromium")
	boolean chromiumDownloads();

	@Key("browser.tracing.qa.chromium")
	boolean tracingQaChromium();

	@Key("browser.tracing.dev.chromium")
	boolean tracingDevChromium();

	@Key("browser.tracing.prod.chromium")
	boolean tracingProdChromium();

	@Key("browser.video.qa.chromium")
	boolean videoQaChromium();

	@Key("browser.video.dev.chromium")
	boolean videoDevChromium();

	@Key("browser.video.prod.chromium")
	boolean videoProdChromium();

	// Firefox
	@Key("browser.args.firefox")
	String firefoxArgs();

	@Key("browser.downloads.enabled.firefox")
	boolean firefoxDownloads();

	@Key("browser.tracing.qa.firefox")
	boolean tracingQaFirefox();

	@Key("browser.tracing.dev.firefox")
	boolean tracingDevFirefox();

	@Key("browser.tracing.prod.firefox")
	boolean tracingProdFirefox();

	@Key("browser.video.qa.firefox")
	boolean videoQaFirefox();

	@Key("browser.video.dev.firefox")
	boolean videoDevFirefox();

	@Key("browser.video.prod.firefox")
	boolean videoProdFirefox();

	// WebKit
	@Key("browser.args.webkit")
	String webkitArgs();

	@Key("browser.downloads.enabled.webkit")
	boolean webkitDownloads();

	@Key("browser.tracing.qa.webkit")
	boolean tracingQaWebkit();

	@Key("browser.tracing.dev.webkit")
	boolean tracingDevWebkit();

	@Key("browser.tracing.prod.webkit")
	boolean tracingProdWebkit();

	@Key("browser.video.qa.webkit")
	boolean videoQaWebkit();

	@Key("browser.video.dev.webkit")
	boolean videoDevWebkit();

	@Key("browser.video.prod.webkit")
	boolean videoProdWebkit();

	// Edge
	@Key("browser.args.edge")
	String edgeArgs();

	@Key("browser.downloads.enabled.edge")
	boolean edgeDownloads();

	@Key("browser.tracing.qa.edge")
	boolean tracingQaEdge();

	@Key("browser.tracing.dev.edge")
	boolean tracingDevEdge();

	@Key("browser.tracing.prod.edge")
	boolean tracingProdEdge();

	@Key("browser.video.qa.edge")
	boolean videoQaEdge();

	@Key("browser.video.dev.edge")
	boolean videoDevEdge();

	@Key("browser.video.prod.edge")
	boolean videoProdEdge();

	@Key("tracing.enabled")
	@DefaultValue("true")
	boolean tracingEnabled();

	// MySQL
	@Key("mysql.url.qa")
	String mysqlUrlQa();

	@Key("mysql.url.dev")
	String mysqlUrlDev();

	@Key("mysql.url.prod")
	String mysqlUrlProd();

	@Key("mysql.user.qa")
	String mysqlUserQa();

	@Key("mysql.user.dev")
	String mysqlUserDev();

	@Key("mysql.user.prod")
	String mysqlUserProd();

	@Key("mysql.pass.qa")
	String mysqlPassQa();

	@Key("mysql.pass.dev")
	String mysqlPassDev();

	@Key("mysql.pass.prod")
	String mysqlPassProd();

	// PostgreSQL
	@Key("postgres.url.qa")
	String postgresUrlQa();

	@Key("postgres.url.dev")
	String postgresUrlDev();

	@Key("postgres.url.prod")
	String postgresUrlProd();

	@Key("postgres.user.qa")
	String postgresUserQa();

	@Key("postgres.user.dev")
	String postgresUserDev();

	@Key("postgres.user.prod")
	String postgresUserProd();

	@Key("postgres.pass.qa")
	String postgresPassQa();

	@Key("postgres.pass.dev")
	String postgresPassDev();

	@Key("postgres.pass.prod")
	String postgresPassProd();

	// Oracle
	@Key("oracle.url.qa")
	String oracleUrlQa();

	@Key("oracle.url.dev")
	String oracleUrlDev();

	@Key("oracle.url.prod")
	String oracleUrlProd();

	@Key("oracle.user.qa")
	String oracleUserQa();

	@Key("oracle.user.dev")
	String oracleUserDev();

	@Key("oracle.user.prod")
	String oracleUserProd();

	@Key("oracle.pass.qa")
	String oraclePassQa();

	@Key("oracle.pass.dev")
	String oraclePassDev();

	@Key("oracle.pass.prod")
	String oraclePassProd();

	// MongoDB
	@Key("mongo.url.qa")
	String mongoUrlQa();

	@Key("mongo.url.dev")
	String mongoUrlDev();

	@Key("mongo.url.prod")
	String mongoUrlProd();

	// CosmosDB
	@Key("cosmos.endpoint.qa")
	String cosmosEndpointQa();

	@Key("cosmos.endpoint.dev")
	String cosmosEndpointDev();

	@Key("cosmos.endpoint.prod")
	String cosmosEndpointProd();

	@Key("cosmos.key.qa")
	String cosmosKeyQa();

	@Key("cosmos.key.dev")
	String cosmosKeyDev();

	@Key("cosmos.key.prod")
	String cosmosKeyProd();
}
