package me.marcelohdez.imagetotext;

import javax.swing.*;
import java.awt.*;

public class ITTView extends JFrame {
    private final JTextArea textArea = new JTextArea();
    private final JSlider scaleSlider = new JSlider();
    private final JSlider fontSizeSlider = new JSlider();
    private final JButton copyButton = new JButton("Copy");

    public ITTView(ITTModel model) {
        setTitle("Image to Text");

        textArea.setEditable(false);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, model.getFontSize()));

        add(new JScrollPane(textArea));
        addBottomPanel(model);
        setLocationRelativeTo(null);
    }

    private void addBottomPanel(ITTModel model) {
        scaleSlider.setModel(model.getScaleModel());
        fontSizeSlider.setModel(model.getFontSizeModel());

        var sliderPanel = new JPanel();
        sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));

        var scaleRow = createSliderRow(scaleSlider, "Scale");
        var fontSizeRow = createSliderRow(fontSizeSlider, "Font Size");

        var buttonRow = new JPanel();
        buttonRow.add(copyButton);

        sliderPanel.add(scaleRow);
        sliderPanel.add(fontSizeRow);
        sliderPanel.add(buttonRow);

        add(new JScrollPane(sliderPanel), BorderLayout.SOUTH);
    }

    private JPanel createSliderRow(JSlider slider, String text) {
        var row = new JPanel();
        var label = new JLabel(text + ": " + slider.getValue());
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
}
