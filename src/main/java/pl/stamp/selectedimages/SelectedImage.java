package pl.stamp.selectedimages;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SelectedImage {

    private ImageView imageView;

    public SelectedImage(ImageView imageView) {
        this.imageView = imageView;
    }

    /**
     * Adding selectedImage to image view node
     * @param selectedImage selected image
     */
    public void addSelectedImageToView(File selectedImage) {
        try {
            Image image = new Image(new FileInputStream(selectedImage));
            this.imageView.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
