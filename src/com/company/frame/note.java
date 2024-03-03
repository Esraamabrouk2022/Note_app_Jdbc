package com.company.frame;

import com.company.DB.AppPreferences;
import com.company.DB.EditData;
import com.company.model.Note;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class note extends JFrame {
    home home = new home();
    public note(Note note,home home) {
        setTitle("Note Details");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel detailsPanel = new JPanel(new GridLayout(3, 2));

        detailsPanel.add(new JLabel("Title:"));
        detailsPanel.add(new JLabel(note.getTitle()));

        detailsPanel.add(new JLabel("Description:"));
        detailsPanel.add(new JLabel(note.getDescription()));

        detailsPanel.add(new JLabel("Date:"));
        detailsPanel.add(new JLabel(note.getDate().toString()));

        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    editNote(note,home);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your delete button logic here
                JOptionPane.showMessageDialog(note.this, "Delete button clicked!");
            }
        });

        setLayout(new BorderLayout());
        add(detailsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    private void editNote( Note selectedNote,home home) throws SQLException {

        if (selectedNote != null) {
            int noteId=selectedNote.getId();
            int selectedIndex = home.notes.indexOf(selectedNote);
            String newTitle = JOptionPane.showInputDialog(home, "Enter new note title:", selectedNote.getTitle());
            String newDescription = JOptionPane.showInputDialog(home, "Enter new note description:", selectedNote.getDescription());

            if (newTitle != null && !newTitle.isEmpty()) {
                Note updatedNote = new Note(newTitle, newDescription, LocalDate.now());

                home.notes.set(selectedIndex, updatedNote);

                home.notesListModel.setElementAt(updatedNote, selectedIndex);
                EditData.editNote(updatedNote, noteId);
            }
        } else {
            JOptionPane.showMessageDialog(home, "Please select a note to edit.");
        }
    }

}
