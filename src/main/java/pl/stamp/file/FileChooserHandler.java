package pl.stamp.file;

import javafx.stage.FileChooser;

import java.io.File;
import java.util.Optional;

public class FileChooserHandler {

    public static Optional<File> trySelectFile() {
        FileChooser fileChooser = FileChooserCreator.getFileChooser();
        File result = fileChooser.showOpenDialog(null);
        return Optional.of(result);
    }
}
