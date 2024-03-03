package com.company.DB;

import com.company.model.Note;
import com.company.model.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class getData {

    static Connection con = jdbcConnection.getConnection();

    public static List<Note> getNotesByUserId(int userId) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM note WHERE user_id = ?");
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Note> notes = new ArrayList<>();
        while (resultSet.next()) {
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            LocalDate date = resultSet.getDate("date").toLocalDate();
            Note note = new Note(title, description, date);
            notes.add(note);
        }
        return notes;
    }

    public static User getUser(int id) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM user WHERE user_id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String username = resultSet.getString("username");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            return new User(username, email, password);
        }
        return null;
    }

    public static int getUserIdByUsernameAndPassword(String userName, String password) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("SELECT id FROM user WHERE username = ? AND password = ?");
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
//            System.out.println(id);
            return id;
        }
        return -1;
    }

    public static Note getNoteById(int id) throws SQLException {
        Statement statement = con.createStatement();
        PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM note WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String title = resultSet.getNString("title");
            String description = resultSet.getString("description");
            LocalDate date = resultSet.getDate("date").toLocalDate();
            int userId = resultSet.getInt("user_id");
            return new Note(title, description, date);
        }
        return null;
    }
}
