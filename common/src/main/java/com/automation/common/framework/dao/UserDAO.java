package com.automation.common.framework.dao;

import com.automation.common.framework.dto.UserDTO;

public interface UserDAO {
    UserDTO getUserById(String userId);
}

