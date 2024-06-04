package org.casyu.tinynotepad.settings;


import javafx.scene.paint.Color;

import java.io.*;

public class Settings {

    private static Settings instance;

    private boolean wrapText;
    private String fontType;
    private int fontSize;
    private Color fontColor;

    private Settings() {
        // 默认设置
        this.wrapText = false;
        this.fontType = "Arial";
        this.fontSize = 12;
        this.fontColor = Color.BLACK;
        loadSettings();
    }

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    public boolean isWrapText() {
        return wrapText;
    }

    public void setWrapText(boolean wrapText) {
        this.wrapText = wrapText;
    }

    public String getFontType() {
        return fontType;
    }

    public void setFontType(String fontType) {
        this.fontType = fontType;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    public void loadSettings() {
        // 从文件或其他来源加载设置
        String filePath = "settings.txt";
        if (!new File(filePath).exists()) {
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            Settings loadedSettings = (Settings) ois.readObject();
            this.wrapText = loadedSettings.wrapText;
            this.fontType = loadedSettings.fontType;
            this.fontSize = loadedSettings.fontSize;
            this.fontColor = loadedSettings.fontColor;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveSettings() {
        // 保存设置到文件或其他来源
        String filePath = "settings.txt";
        if (!new File(filePath).exists()) {
            try {
                new File(filePath).createNewFile();
            } catch (IOException e) {
                return;
            }
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
