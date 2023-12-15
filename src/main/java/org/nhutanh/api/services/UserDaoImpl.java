package org.nhutanh.api.services;

import org.nhutanh.api.DatabaseConnection;

import org.nhutanh.api.dao.UserDao;
import org.nhutanh.api.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public User getUserById(int id) {
        User user = null;
        String sql = "SELECT * FROM user WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user = mapRowToUser(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception properly
        }
        return user;
    }
    @Override
    public User getUserByUsername(String username) {
        User user = null;
        String sql = "SELECT * FROM user WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user = mapRowToUser(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception properly
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                users.add(mapRowToUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception properly
        }
        return users;
    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO user (full_name, username, password, phone_number, status, image_url, age, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setPreparedStatementFromUser(pstmt, user);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception properly
        }
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE user SET full_name = ?, username = ?, password = ?, phone_number = ?, status = ?, image_url = ?, age = ?, role = ? WHERE id = ?";

        User inDataUser = this.getUserById(user.getId());
        if (user.getFullName()!=null){
            inDataUser.setFullName(user.getFullName());
        }
        if (user.getAge()!=0){
            inDataUser.setAge(user.getAge());
        }
        if (user.getImageUrl()!=null){
            inDataUser.setImageUrl(user.getImageUrl());
        }
        if (user.getPhoneNumber()!=null){
            inDataUser.setPhoneNumber(user.getPhoneNumber());
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setPreparedStatementFromUser(pstmt, inDataUser);
            pstmt.setInt(9, user.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception properly
        }
    }

    @Override
    public void deleteUser(int id) {
        String sql = "DELETE FROM user WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception properly
        }
    }

    private User mapRowToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setFullName(rs.getString("full_name"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setStatus(rs.getBoolean("status"));
        user.setImageUrl(rs.getString("image_url"));
        user.setAge(rs.getInt("age"));
        user.setRole(rs.getString("role"));
        // Note: loginHistories is not handled here
        return user;
    }

    private void setPreparedStatementFromUser(PreparedStatement pstmt, User user) throws SQLException {
        pstmt.setString(1, user.getFullName());
        pstmt.setString(2, user.getUsername());
        pstmt.setString(3, user.getPassword());
        pstmt.setString(4, user.getPhoneNumber());
        pstmt.setBoolean(5, user.isStatus());
        pstmt.setString(6, user.getImageUrl());
        pstmt.setInt(7, user.getAge());
        pstmt.setString(8, user.getRole());
    }
}
