package com.company.DB;

import com.company.model.Note;
import com.company.model.User;

import java.sql.*;

public class EditData {
    static Connection con = jdbcConnection.getConnection();;

    public EditData() {
    }

    public static boolean addNote(Note note, int userId) throws SQLException {
        Statement statement = con.createStatement();
        String query = String.format("INSERT INTO note (title, description, user_id) VALUES ('%s', '%s', %d)", note.getTitle(), note.getDescription(), userId);
        int rowsAffected = statement.executeUpdate(query);
        return rowsAffected > 0;
    }

    public static boolean deleteNote(int noteId) throws SQLException {
        Statement statement = con.createStatement();
        String query = String.format("DELETE FROM note WHERE note_id = %d", noteId);
        int rowsAffected = statement.executeUpdate(query);
        return rowsAffected > 0;
    }

    public static Note editNote(Note note, int id) throws SQLException {
        Statement statement = con.createStatement();
        String query = String.format("UPDATE note SET title = '%s', description = '%s' WHERE id = %d",
                note.getTitle(), note.getDescription(), id);
        int rowsAffected = statement.executeUpdate(query);
        return rowsAffected > 0? note : null;
    }

    public static boolean addUser(User user) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO user (username, email, password) VALUES (?, ?, ?)");
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getPassword());

        int rowsAffected = preparedStatement.executeUpdate();

        return rowsAffected > 0;
    }


}