package ooup.lab3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CustomComponent extends JComponent implements KeyListener {

    public CustomComponent() {
        setPreferredSize(new Dimension(200, 200));
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
        g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
        g.setColor(Color.BLACK);
        g.drawString("Prvi redak teksta", 10, getHeight() / 2 - 10);
        g.drawString("Drugi redak teksta", 10, getHeight() / 2 + 10);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Moja komponenta");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            CustomComponent CustomComponent = new CustomComponent();
            frame.getContentPane().add(CustomComponent);

            frame.pack();
            frame.setLocation(700,300);
            frame.setVisible(true);
        });
    }
}
