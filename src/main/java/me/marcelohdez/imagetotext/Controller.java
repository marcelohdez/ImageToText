package me.marcelohdez.imagetotext;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Optional;

public class Controller implements WindowListener {
    private static final String CHARACTERS = //" .:-=+*#%@";
            " .'`^\",:;Il!i><~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$";

    private final StartView startView = new StartView();
    private final ITTModel model;
    private final ITTView view;
    private BufferedImage sourceImage;

    public Controller(ITTModel model, ITTView view) {
        this.model = model;
        this.view = view;

        startView.getFileButton().addActionListener(e -> chooseFile());
        view.getCopyButton().addActionListener(e -> copyText());

        ChangeListener al = e -> showText(convertSourceToText());
        view.getScaleSlider().addChangeListener(al);
        view.getFontSizeSpinner().addChangeListener(al);

        view.getFontSizeSpinner().addChangeListener(e ->
                view.getTextArea().setFont(view.getTextArea().getFont()
                        .deriveFont((float) model.getFontSize()))
        );
        view.addWindowListener(this);

        startView.setVisible(true);
    }

    private void chooseFile() {
        var result = startView.getFileChooser().showOpenDialog(startView);

        if (result == JFileChooser.APPROVE_OPTION) {
            var maybeImage = imageFromFile(startView.getFileChooser().getSelectedFile());

            if (maybeImage.isEmpty()) {
                showText("Could not read file as image!");
                return;
            }

            sourceImage = maybeImage.get();
            showText(convertSourceToText());
        }
    }

    private void showText(String text) {
        var textArea = view.getTextArea();
        boolean firstTime = (textArea.getText().isEmpty());

        textArea.setText(text);
        if (firstTime) {
            view.pack();
            view.setLocationRelativeTo(startView);
        }

        startView.setVisible(false);
        view.setVisible(true);
    }

    private void copyText() {
        var selection = new StringSelection(view.getTextArea().getText());
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
    }

    private String convertSourceToText() {
        var scale = model.getScaleModel().getValue();
        var ratio = getAspectRatio(sourceImage);

        var height = 10 * scale;
        var width = (int) (height * ratio);
        var image = toProperSize(sourceImage, width, height);

        // start a string builder with the size we need. (width + newline character and height of image)
        StringBuilder sb = new StringBuilder((image.getWidth() + 1) * image.getHeight());
        for (int y = 0; y < height; y += 2) {
            for (int x = 0; x < width; x++) {
                var fill = getFullness(image, x, y);
                var index = (int) (fill * (CHARACTERS.length() - 1));
                sb.append(CHARACTERS.charAt(index));
            }
            sb.append('\n');
        }

        return sb.toString();
    }

    private Optional<BufferedImage> imageFromFile(File f) {
        try {
            return Optional.ofNullable(ImageIO.read(f)); // Get image in proper size
        } catch (Exception ignored) {
            return Optional.empty();
        }
    }

    private double getAspectRatio(BufferedImage image) {
        var width = (double) image.getWidth();
        var height = (double) image.getHeight();

        return width / height;
    }

    /**
     * Resize a BufferedImage into a new BufferedImage, as getScaledInstance returns
     * a type which cannot be cast into a BufferedImage.
     * From <a href="https://stackoverflow.com/a/9417836">this solution.</a>
     */
    private BufferedImage toProperSize(BufferedImage image, int width, int height) {
        var resizedImage = image.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
        var newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = newImage.createGraphics();
        g.drawImage(resizedImage, 0, 0, null);
        g.dispose();

        return newImage;
    }

    /** Get the "fullness" of the given pixel in the given image */
    private double getFullness(BufferedImage image, int x, int y) {
        var rgb = image.getRGB(x, y);

        var c = new Color(
                (rgb & 0xff0000) >> 16, // red
                (rgb & 0xff00) >> 8, // green
                rgb & 0xff, // blue
                (rgb & 0xff000000) >>> 24 // alpha
        );

        final var MAX_COLOR = 255 + 255 + 255;
        final var MAX_FULLNESS = MAX_COLOR * 255;

        double fullness = (MAX_COLOR - (c.getRed() + c.getGreen() + c.getBlue())) * c.getAlpha();
        return fullness / MAX_FULLNESS;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (e.getSource() == view) { // We are closing the text view
            startView.setLocationRelativeTo(view);
            startView.setVisible(true);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosed(WindowEvent e) {}
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}
}
