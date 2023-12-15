package org.nhutanh.api.dao;

import org.nhutanh.api.models.LoginHistory;
import org.nhutanh.api.models.User;

import java.util.Date;
import java.util.List;

public interface LoginHistoryDao {
    void addHistory(LoginHistory loginHistory);
    List<LoginHistory> findHistoryByUserId(int userId);
    List<User> findAllUsersOnLoginDay(Date loginDate);
}

