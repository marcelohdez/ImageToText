package me.marcelohdez.imagetotext;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

public class StartView extends JFrame {
    private static final EmptyBorder PADDING = new EmptyBorder(6, 6, 6, 6);

    private final JButton fileButton = new JButton("Choose Image...");
    private final JFileChooser fileChooser = new JFileChooser();

    public StartView() {
        setTitle("Image to Text");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "png", "jpeg", "jpg"));

        var label = new JLabel("Welcome! Please select an image file to begin.");
        label.setBorder(PADDING);
        add(label, BorderLayout.NORTH);
        add(fileButton, BorderLayout.CENTER);

        // Add some padding to window
        ((JPanel) getContentPane()).setBorder(PADDING);

        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public JButton getFileButton() {
        return fileButton;
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }
}
