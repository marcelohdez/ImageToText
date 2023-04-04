package me.marcelohdez.imagetotext;

import javax.swing.*;

public class ITTModel {
    private final static int DEFAULT_SCALE = 4;
    private final static int DEFAULT_FONT_SIZE = 11;
    private final static int MIN_SCALE = 1;
    private final static int MIN_FONT_SIZE = 6;
    private final static int MAX_SCALE = 20;
    private final static int MAX_FONT_SIZE = 20;

    private final DefaultBoundedRangeModel scaleModel =
            new DefaultBoundedRangeModel(DEFAULT_SCALE, 0, MIN_SCALE, MAX_SCALE);
    private final DefaultBoundedRangeModel fontSizeModel =
            new DefaultBoundedRangeModel(DEFAULT_FONT_SIZE, 0, MIN_FONT_SIZE, MAX_FONT_SIZE);

    public DefaultBoundedRangeModel getScaleModel() {
        return scaleModel;
    }

    public DefaultBoundedRangeModel getFontSizeModel() {
        return fontSizeModel;
    }

    public int getFontSize() {
        return fontSizeModel.getValue();
    }
}
