package com.automation.core.framework.utils;

import com.automation.core.framework.config.ConfigFactorySingleton;
import com.automation.core.framework.config.FrameworkConfig;

public final class ConfigHelper {

	private static final FrameworkConfig config = ConfigFactorySingleton.getConfig();
	private static final String env = config.env().toLowerCase();

	private ConfigHelper() {
	}

	// API URL
	public static String getApiUrl() {
		switch (env) {
		case "qa":
			return config.apiUrlQa();
		case "dev":
			return config.apiUrlDev();
		case "prod":
			return config.apiUrlProd();
		default:
			throw new IllegalArgumentException("Unsupported env for API application: " + env);
		}
	}

	// Web URL
	public static String getWebUrl() {
		switch (env) {
		case "qa":
			return config.webUrlQa();
		case "dev":
			return config.webUrlDev();
		case "prod":
			return config.webUrlProd();
		default:
			throw new IllegalArgumentException("Unsupported env for Web application " + env);
		}
	}

	// MySQL
	public static String getMySqlUrl() {
		switch (env) {
		case "qa":
			return config.mysqlUrlQa();
		case "dev":
			return config.mysqlUrlDev();
		case "prod":
			return config.mysqlUrlProd();
		default:
			throw new IllegalArgumentException("Unsupported env for Mysql DB URL " + env);
		}
	}

	public static String getMySqlUser() {
		switch (env) {
		case "qa":
			return config.mysqlUserQa();
		case "dev":
			return config.mysqlUserDev();
		case "prod":
			return config.mysqlUserProd();
		default:
			throw new IllegalArgumentException("Unsupported env for Mysql DB User " + env);
		}
	}

	public static String getMySqlPassword() {
		switch (env) {
		case "qa":
			return config.mysqlPassQa();
		case "dev":
			return config.mysqlPassDev();
		case "prod":
			return config.mysqlPassProd();
		default:
			throw new IllegalArgumentException("Unsupported env for MySql DB Password: " + env);
		}
	}

	// PostgreSQL
	public static String getPostgresUrl() {
		switch (env) {
		case "qa":
			return config.postgresUrlQa();
		case "dev":
			return config.postgresUrlDev();
		case "prod":
			return config.postgresUrlProd();
		default:
			throw new IllegalArgumentException("Unsupported env for Postgres DB URL: " + env);
		}
	}

	public static String getPostgresUser() {
		switch (env) {
		case "qa":
			return config.postgresUserQa();
		case "dev":
			return config.postgresUserDev();
		case "prod":
			return config.postgresUserProd();
		default:
			throw new IllegalArgumentException("Unsupported env for Postgres DB User: " + env);
		}
	}

	public static String getPostgresPassword() {
		switch (env) {
		case "qa":
			return config.postgresPassQa();
		case "dev":
			return config.postgresPassDev();
		case "prod":
			return config.postgresPassProd();
		default:
			throw new IllegalArgumentException("Unsupported env for Postgres DB Password: " + env);
		}
	}

	// Oracle
	public static String getOracleUrl() {
		switch (env) {
		case "qa":
			return config.oracleUrlQa();
		case "dev":
			return config.oracleUrlDev();
		case "prod":
			return config.oracleUrlProd();
		default:
			throw new IllegalArgumentException("Unsupported env for Oracle DB URL: " + env);
		}
	}

	public static String getOracleUser() {
		switch (env) {
		case "qa":
			return config.oracleUserQa();
		case "dev":
			return config.oracleUserDev();
		case "prod":
			return config.oracleUserProd();
		default:
			throw new IllegalArgumentException("Unsupported env for Oracle DB User: " + env);
		}
	}

	public static String getOraclePassword() {
		switch (env) {
		case "qa":
			return config.oraclePassQa();
		case "dev":
			return config.oraclePassDev();
		case "prod":
			return config.oraclePassProd();
		default:
			throw new IllegalArgumentException("Unsupported env for Oracle DB Password: " + env);
		}
	}

	// MongoDB
	public static String getMongoUrl() {
		switch (env) {
		case "qa":
			return config.mongoUrlQa();
		case "dev":
			return config.mongoUrlDev();
		case "prod":
			return config.mongoUrlProd();
		default:
			throw new IllegalArgumentException("Unsupported env for Mongo DB URL: " + env);
		}
	}

	// CosmosDB
	public static String getCosmosEndpoint() {
		switch (env) {
		case "qa":
			return config.cosmosEndpointQa();
		case "dev":
			return config.cosmosEndpointDev();
		case "prod":
			return config.cosmosEndpointProd();
		default:
			throw new IllegalArgumentException("Unsupported env for Cosmos DB EndPoint: " + env);
		}
	}

	public static String getCosmosKey() {
		switch (env) {
		case "qa":
			return config.cosmosKeyQa();
		case "dev":
			return config.cosmosKeyDev();
		case "prod":
			return config.cosmosKeyProd();
		default:
			throw new IllegalArgumentException("Unsupported env for Cosmos DB Key: " + env);
		}
	}
}
