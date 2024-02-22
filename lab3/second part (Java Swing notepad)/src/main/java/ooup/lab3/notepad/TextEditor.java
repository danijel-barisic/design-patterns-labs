package ooup.lab3.notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TextEditor extends JComponent implements KeyListener{

    private TextEditorModel model;
    private int lineHeight;
    private int charWidth;

    public TextEditor(TextEditorModel model) {
        this.model = model;
        setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        lineHeight = getFontMetrics(getFont()).getHeight();
        charWidth = getFontMetrics(getFont()).charWidth('w');

        setPreferredSize(new Dimension(800, 600));
        addKeyListener(this);
        setFocusable(true);

        model.addCursorObserver(loc -> repaint());
        model.addTextObserver(()->{});
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (model.getSelectionRange() != null) { // Provjeravamo postoji li označen dio teksta
            int selectionStartPixelX = model.getSelectionRange().getStart().getCol() * charWidth;
            int selectionStartPixelY = model.getSelectionRange().getStart().getRow() * lineHeight;
            int selectionEndPixelX = model.getSelectionRange().getEnd().getCol() * charWidth;
            int selectionEndPixelY = model.getSelectionRange().getEnd().getRow() * lineHeight;

            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(selectionStartPixelX, selectionStartPixelY, selectionEndPixelX - selectionStartPixelX, selectionEndPixelY);
        }

        g.setColor(Color.BLACK);
        int y = lineHeight;
        for (String line : model.getLines()) {
            g.drawString(line, 0, y);
            y += lineHeight;
        }

        int cursorPixelX = model.getCursorLocation().getCol() * charWidth;
        int cursorPixelY = model.getCursorLocation().getRow() * lineHeight;
        g.drawLine(cursorPixelX, cursorPixelY, cursorPixelX, cursorPixelY + lineHeight);


    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        boolean shiftPressed = e.isShiftDown();

        if (keyCode == KeyEvent.VK_LEFT) {
            if (shiftPressed) {
                model.moveCursorLeftWithSelection();
            } else {
                model.moveCursorLeft();
                model.setSelectionRange(null);
            }
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            if (shiftPressed) {
                model.moveCursorRightWithSelection();
            } else {
                model.moveCursorRight();
                model.setSelectionRange(null);
            }
        } else if (keyCode == KeyEvent.VK_UP) {
            if (shiftPressed) {
                model.moveCursorUpWithSelection();
            } else {
                model.moveCursorUp();
                model.setSelectionRange(null);
            }
        } else if (keyCode == KeyEvent.VK_DOWN) {
            if (shiftPressed) {
                model.moveCursorDownWithSelection();
            } else {
                model.moveCursorDown();
                model.setSelectionRange(null);
            }
        } else if (keyCode == KeyEvent.VK_BACK_SPACE) {
            if (model.getSelectionRange()!= null) {
                model.deleteRange(model.getSelectionRange());
                model.setSelectionRange(null);
            } else {
                model.deleteBefore();
            }
        } else if (keyCode == KeyEvent.VK_DELETE) {
            if (model.getSelectionRange() != null) {
                model.deleteRange(model.getSelectionRange());
                model.setSelectionRange(null);
            } else {
                model.deleteAfter();
            }
        } else if (keyCode == KeyEvent.VK_ENTER) {
            model.addNewLine();
        } else if(keyCode!=KeyEvent.VK_SHIFT){
            char keyChar = e.getKeyChar();
            if (model.getSelectionRange() != null) {
                model.deleteRange(model.getSelectionRange());
                model.setSelectionRange(null);
            }
            model.insertCharacter(keyChar);
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            TextEditorModel model = new TextEditorModel("Prvi redak\nDrugi redak\nTreci redak");
//            TextEditor textEditor = new TextEditor(model);
//
//            JFrame frame = new JFrame("Text Editor");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.getContentPane().add(textEditor);
//            frame.pack();
//            frame.setLocationRelativeTo(null);
//            frame.setVisible(true);
//        });
//    }

//    @Override
//    public void updateCursorLocation(Location loc) {
//        repaint(); // or empty method
//    }
}
