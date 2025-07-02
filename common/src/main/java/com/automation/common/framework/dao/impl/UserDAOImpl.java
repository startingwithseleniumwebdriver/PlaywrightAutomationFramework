package com.automation.common.framework.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.automation.common.framework.dao.UserDAO;
import com.automation.common.framework.dto.UserDTO;

public class UserDAOImpl implements UserDAO {
    private final Connection connection;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public UserDTO getUserById(String userId) {
        UserDTO dto = new UserDTO();
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                dto.setUserId(rs.getString("id"));
                dto.setFirstName(rs.getString("first_name"));
                dto.setLastName(rs.getString("last_name"));
                dto.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
}

