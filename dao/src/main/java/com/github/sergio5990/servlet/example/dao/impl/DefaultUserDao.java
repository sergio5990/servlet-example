package com.github.sergio5990.servlet.example.dao.impl;

import com.github.sergio5990.servlet.example.dao.DataSource;
import com.github.sergio5990.servlet.example.dao.UserDao;
import com.github.sergio5990.servlet.example.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultUserDao implements UserDao {

    private static class SingletonHolder {
        static final UserDao HOLDER_INSTANCE = new DefaultUserDao();
    }

    public static UserDao getInstance() {
        return DefaultUserDao.SingletonHolder.HOLDER_INSTANCE;
    }


    @Override
    public List<User> getStudents() {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from user");
             ResultSet rs = ps.executeQuery()) {
            final ArrayList<User> result = new ArrayList<>();
            while (rs.next()) {
                final User user = new User(
                        rs.getLong("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone"),
                        rs.getString("email"));
                result.add(user);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long save(User user) {
        final String sql = "insert into user(first_name, last_name, phone, email) values(?,?,?,?)";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getEmail());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                keys.next();
                return keys.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
