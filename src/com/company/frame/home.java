package com.company.frame;

import com.company.DB.AppPreferences;
import com.company.DB.EditData;
import com.company.DB.getData;
import com.company.model.Note;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.prefs.BackingStoreException;

public class home extends JFrame {
    public DefaultListModel<Note> notesListModel;
    public JList<Note> notesList;
    public List<Note> notes;

    public home() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        notesListModel = new DefaultListModel<>();

        try {
            notes = getData.getNotesByUserId(AppPreferences.getUserId());
            notesListModel.addAll(notes);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        notesList = new JList<>(notesListModel);
        notesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        notesList.addListSelectionListener(e -> {
            new note(notesList.getSelectedValue(),home.this);
        });

        setTitle("Note App");
        setLocationRelativeTo(null);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton addNoteButton = new JButton("Add Note");
        JButton LogoutButton = new JButton("logout");
        LogoutButton.addActionListener(e -> {
            try {
                AppPreferences.deleteUserId();
                new welcome();
                dispose();
            } catch (BackingStoreException ex) {
                throw new RuntimeException(ex);
            }

        });
        addNoteButton.addActionListener(e -> {
            try {
                addNote();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

//        notesList = new JList<>(notesListModel);
        notesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(notesList);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addNoteButton);
        buttonPanel.add(LogoutButton);

        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }


    private void addNote() throws SQLException {
        String title = JOptionPane.showInputDialog(this, "Enter note title:");
        String description = JOptionPane.showInputDialog(this, "Enter note description:");
        if (title != null && !title.isEmpty()) {
            Note note = new Note(title, description, LocalDate.now());
            notes.add(note);
            notesListModel.addElement(note);
            EditData.addNote(new Note(title, description, LocalDate.now()), AppPreferences.getUserId());
        }
    }


}
