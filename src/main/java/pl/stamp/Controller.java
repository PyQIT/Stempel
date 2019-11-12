package pl.stamp;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import pl.stamp.file.FileChooserHandler;
import pl.stamp.cursor.ImageViewCursor;
import pl.stamp.image.ImageViewMarker;
import pl.stamp.selectedimages.SelectedImage;

import java.io.File;

public class Controller {

    /**
     * Main template of GUI
     */
    @FXML
    private BorderPane rootBorderPane;

    /**
     * Label for showing application title
     */
    @FXML
    private Label titleLabel;

    /**
     * Main image view for showing selected image
     */
    @FXML
    private ImageView imageView;

    /**
     * File chooser button
     */
    @FXML
    private Button fileChooserButton;

    /**
     * Checkbox in which user can choose stamp size
     */
    @FXML
    private ChoiceBox stempleSizeChoiceBox;

    /**
     * Image view which showing selected area from main image
     */
    @FXML
    private ImageView selectedAreaImageView;

    ImageViewCursor imageViewCursorController;

    @FXML
    public void initialize(){
        stempleSizeChoiceBox.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                System.out.println(stempleSizeChoiceBox.getSelectionModel().getSelectedIndex());
                imageViewCursorController.addChoiceBoxListener(stempleSizeChoiceBox.getSelectionModel().getSelectedIndex());
            }
        });
    }

    /**
     * Selected image
     */
    private ObservableValue<File> selectedImage = new SimpleObjectProperty<>();

    /**
     * Method called on window show
     */
    public void handleWindowShownEvent() {
        this.setTitleLabelText();
        this.setFileChooserManagement();
        this.setImageViewManagement();
    }

    /**
     * Adding title text to label on top application and centered them
     */
    private void setTitleLabelText() {
        this.titleLabel.setText(Main.getTITLE());
    }

    /**
     * Managing choosing file
     */
    private void setFileChooserManagement() {
        SelectedImage selectedImageListener = new SelectedImage(this.imageView);
        this.fileChooserButton.addEventFilter(MouseEvent.MOUSE_PRESSED,
                event -> {
                    selectedImage = new SimpleObjectProperty<>(FileChooserHandler.trySelectFile().orElse(selectedImage.getValue()));
                    if (selectedImage.getValue() != null)
                        selectedImageListener.addSelectedImageToView(selectedImage.getValue());
                });
    }
    /**
     * Managing operations on ImageView node
     */
    private void setImageViewManagement() {
        imageViewCursorController = new ImageViewCursor(this.imageView);
        imageViewCursorController.setChoiceBoxData();
        stempleSizeChoiceBox.getSelectionModel().selectFirst();
        imageViewCursorController.addEventListener();
        ImageViewMarker imageViewMarkerController = new ImageViewMarker(this.imageView);
        imageViewMarkerController.addEventListener();
        imageViewMarkerController.setSelectedAreaImageView(this.selectedAreaImageView);
    }
}
