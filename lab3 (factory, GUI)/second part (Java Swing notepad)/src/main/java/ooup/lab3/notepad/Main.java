package ooup.lab3.notepad;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TextEditorModel model = new TextEditorModel("Prvi redak\nDrugi redak\nTreci redak");
            TextEditor textEditor = new TextEditor(model);

            // Create the main frame
            JFrame frame = new JFrame("Text Editor");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // Create the menu toolbar
            JMenuBar menuBar = new JMenuBar();
            JMenu fileMenu = new JMenu("File");
            fileMenu.add(new JMenuItem("New"));
            fileMenu.add(new JMenuItem("Open"));
            fileMenu.add(new JMenuItem("Save"));
            fileMenu.add(new JMenuItem("Save As"));
            fileMenu.addSeparator();
            fileMenu.add(new JMenuItem("Exit"));
            menuBar.add(fileMenu);
            frame.setJMenuBar(menuBar);

            // Create the status bar
            JPanel statusBar = new JPanel();
            statusBar.setBorder(BorderFactory.createEtchedBorder());
            JLabel statusLabel = new JLabel("Ready");
            statusBar.add(statusLabel);

            // Add the components to the frame
            frame.add(textEditor, BorderLayout.CENTER);
            frame.add(statusBar, BorderLayout.SOUTH);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}