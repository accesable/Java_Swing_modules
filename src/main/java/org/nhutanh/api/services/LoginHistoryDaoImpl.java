package org.nhutanh.api.services;

import org.nhutanh.api.DatabaseConnection;
import org.nhutanh.api.dao.LoginHistoryDao;
import org.nhutanh.api.models.LoginHistory;
import org.nhutanh.api.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoginHistoryDaoImpl implements LoginHistoryDao {

    @Override
    public void addHistory(LoginHistory loginHistory) {
        String sql = "INSERT INTO login_history (login_time, user_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setTimestamp(1, new Timestamp(loginHistory.getLoginTime().getTime()));
            pstmt.setLong(2, loginHistory.getUser().getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Proper exception handling should be implemented
        }
    }

    @Override
    public List<LoginHistory> findHistoryByUserId(int userId) {
        List<LoginHistory> histories = new ArrayList<>();
        String sql = "SELECT * FROM login_history WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    LoginHistory history = new LoginHistory();
                    history.setId(rs.getLong("id"));
                    history.setLoginTime(rs.getTimestamp("login_time"));
                    // Set the user object here if necessary
                    histories.add(history);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Proper exception handling should be implemented
        }
        return histories;
    }

    @Override
    public List<User> findAllUsersOnLoginDay(Date loginDate) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT DISTINCT user.* FROM user JOIN login_history ON user.id = login_history.user_id WHERE DATE(login_history.loginTime) = DATE(?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, new java.sql.Date(loginDate.getTime()));
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    // Map the user fields from ResultSet
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Proper exception handling should be implemented
        }
        return users;
    }


}

