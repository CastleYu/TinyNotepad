package org.casyu.tinynotepad.settings;


public class Settings {

    private static Settings instance;

    private Settings() {
        // 私有构造函数
    }

    public static Settings getInstance() {
        if (instance == null) {
            //noinspection InstantiationOfUtilityClass
            instance = new Settings();
        }
        return instance;
    }
}
