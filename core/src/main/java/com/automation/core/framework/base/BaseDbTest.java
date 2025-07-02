package com.automation.core.framework.base;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.automation.core.framework.utils.ConfigHelper;

public class BaseDbTest {
	protected Connection connection;

	public void connect() throws SQLException {
		connection = DriverManager.getConnection(ConfigHelper.getMySqlUrl(), ConfigHelper.getMySqlUser(), ConfigHelper.getMySqlPassword());
	}

	public void disconnect() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
	}
}
