package me.marcelohdez.imagetotext;

import javax.swing.*;
import java.awt.*;

public class ITTView extends JFrame {
    private final JTextArea textArea = new JTextArea();
    private final JSlider scaleSlider = new JSlider();
    private final JSlider fontSizeSlider = new JSlider();
    private final JButton copyButton = new JButton("Copy");
    private final JComboBox<String> shadesComboBox = new JComboBox<>();

    public ITTView() {
        setTitle("Image to Text");

        textArea.setEditable(false);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, ITTModel.DEFAULT_FONT_SIZE));

        add(new JScrollPane(textArea));
        addBottomPanel();
        setLocationRelativeTo(null);
    }

    private void addBottomPanel() {
        var shadesAndCopyRow = new JPanel();
        shadesAndCopyRow.add(new JLabel("Shades: "));
        shadesAndCopyRow.add(shadesComboBox);
        shadesAndCopyRow.add(copyButton);

        var sliderPanel = new JPanel();
        sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));
        sliderPanel.add(createSliderRow(scaleSlider, "Scale", ITTModel.DEFAULT_SCALE));
        sliderPanel.add(createSliderRow(fontSizeSlider, "Font Size", ITTModel.DEFAULT_FONT_SIZE));
        sliderPanel.add(shadesAndCopyRow);

        add(new JScrollPane(sliderPanel), BorderLayout.SOUTH);
    }

    private JPanel createSliderRow(JSlider slider, String text, int def) {
        var row = new JPanel();
        var label = new JLabel(text + ": " + def);
        slider.addChangeListener(e -> label.setText(text + ": " + slider.getValue()));

        row.add(label);
        row.add(slider);

        return row;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JButton getCopyButton() {
        return copyButton;
    }

    public JSlider getScaleSlider() {
        return scaleSlider;
    }

    public JSlider getFontSizeSpinner() {
        return fontSizeSlider;
    }

    public JComboBox<String> getShadesComboBox() {
        return shadesComboBox;
    }
}
