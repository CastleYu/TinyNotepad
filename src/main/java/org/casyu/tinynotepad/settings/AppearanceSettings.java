package org.casyu.tinynotepad.settings;

import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AppearanceSettings {

    private final Settings settings;
    private final TextArea textArea;

    public AppearanceSettings(TextArea textArea) {
        this.settings = Settings.getInstance();
        this.textArea = textArea;
    }

    public boolean checkWrap(){
        settings.setWrapText(textArea.isWrapText());
        return textArea.isWrapText();
    }
    public void applyAllSettings() {
        applyFontSettings();
        applyColorSettings();
        applySizeSettings();
        textArea.setWrapText(settings.isWrapText());
    }

    public void applyFontSettings() {
        String fontType = settings.getFontType();
        int fontSize = settings.getFontSize();
        Font font = new Font(fontType, fontSize);
        System.out.println(font);
        textArea.setFont(font);
    }

    public void applyColorSettings() {
        Color fontColor = settings.getFontColor();
        textArea.setStyle("-fx-text-fill: " + toRgbString(fontColor) + ";");
    }

    public void applySizeSettings() {
        int fontSize = settings.getFontSize();
        Font currentFont = textArea.getFont();
        Font newFont = new Font(currentFont.getFamily(), fontSize);
        textArea.setFont(newFont);
    }

    private String toRgbString(Color color) {
        int r = (int) (color.getRed() * 255);
        int g = (int) (color.getGreen() * 255);
        int b = (int) (color.getBlue() * 255);
        return "rgb(" + r + "," + g + "," + b + ")";
    }
}
