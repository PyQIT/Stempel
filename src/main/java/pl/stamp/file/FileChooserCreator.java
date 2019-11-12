package pl.stamp.file;

import javafx.stage.FileChooser;

import java.io.File;

public class FileChooserCreator {

    public static FileChooser getFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik w typie bmp (Bitmap)");
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("BMP File", "*.bmp"));

        return fileChooser;
    }
}
