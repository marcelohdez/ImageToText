package me.marcelohdez.imagetotext;

public class ImageToText {
    public static void main(String[] args) {
        var model = new ITTModel();
        var view = new ITTView(model);
        new Controller(model, view);
    }
}
