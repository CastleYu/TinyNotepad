module org.casyu.tinynotepad {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens org.casyu.tinynotepad to javafx.fxml;
    exports org.casyu.tinynotepad;
}