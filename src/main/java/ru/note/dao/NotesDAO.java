package ru.note.dao;

import ru.note.entities.Note;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotesDAO {

    private Connection connection;

    private String jdbcUsername;
    private String jdbcPassword;
    private String jdbcURL;

    public NotesDAO(String jdbcUsername, String jdbcPassword, String jdbcURL) {
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
        this.jdbcURL = jdbcURL;
    }

    protected void connect() throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

    }

    protected void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public void insertNote(Note note) throws SQLException {
        String query = "INSERT INTO notes (title, textbody) VALUES (?, ?)";
        connect();

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, note.getTitle());
        statement.setString(2, note.getTextbody());
        statement.executeUpdate();
        statement.close();

        disconnect();
    }

    public boolean deleteNote(Note note) throws SQLException {
        String query = "DELETE FROM notes where id = ?";
        connect();

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, note.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public Note find(int id) throws SQLException {
        Note note = null;
        String query = "SELECT * FROM notes WHERE id = ?";
        connect();

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);

        statement.execute();

        ResultSet resultSet = statement.getResultSet();
        if (resultSet.next()) {
            int noteId = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String textbody = resultSet.getString("textbody");
            note = new Note(noteId, title, textbody);
        }
        resultSet.close();
        statement.close();

        disconnect();
        return note;
    }

    public List<Note> getNotes() throws SQLException {
        String query = "SELECT * FROM notes ORDER BY id DESC";
        connect();

        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery(query);

        List<Note> notes = getNotesArrayFromResults(results);

        results.close();
        statement.close();

        disconnect();

        return notes;
    }

    public List<Note> getNotesByStringMatch(String match) throws SQLException {
        String query = "SELECT * FROM" +
                " notes WHERE " +
                "title LIKE '%" + match + "%' OR " +
                "textbody LIKE '%" + match + "%'";
        connect();

        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery(query);

        List<Note> notes = getNotesArrayFromResults(results);

        disconnect();

        return notes;
    }


    private List<Note> getNotesArrayFromResults(ResultSet results) throws SQLException {
        List<Note> notes = new ArrayList<>();
        while (results.next()) {
            int id = results.getInt("id");
            String title = results.getString("title");
            String textbody = results.getString("textbody");

            Note note = new Note(id, title, textbody);
            notes.add(note);
        }
        return notes;
    }
}
