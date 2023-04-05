package me.marcelohdez.imagetotext;

import javax.swing.*;

public class ITTModel {
    public final static int DEFAULT_FONT_SIZE = 11;
    private final static int MIN_FONT_SIZE = 6;
    private final static int MAX_FONT_SIZE = 20;

    public final static int DEFAULT_SCALE = 4;
    private final static int MIN_SCALE = 1;
    private final static int MAX_SCALE = 20;

    public final static String SHADING_OPTION_LESS = "Default";
    public final static String SHADING_OPTION_MORE = "More";
    private final static String[] SHADING_OPTIONS = {SHADING_OPTION_LESS, SHADING_OPTION_MORE};

    private final DefaultBoundedRangeModel scaleModel =
            new DefaultBoundedRangeModel(DEFAULT_SCALE, 0, MIN_SCALE, MAX_SCALE);
    private final DefaultBoundedRangeModel fontSizeModel =
            new DefaultBoundedRangeModel(DEFAULT_FONT_SIZE, 0, MIN_FONT_SIZE, MAX_FONT_SIZE);
    private final DefaultComboBoxModel<String> shadesComboBoxModel = new DefaultComboBoxModel<>(SHADING_OPTIONS);

    public DefaultBoundedRangeModel getScaleModel() {
        return scaleModel;
    }

    public DefaultBoundedRangeModel getFontSizeModel() {
        return fontSizeModel;
    }

    public DefaultComboBoxModel<String> getShadesComboBoxModel() {
        return shadesComboBoxModel;
    }

    public int getFontSize() {
        return fontSizeModel.getValue();
    }
}
