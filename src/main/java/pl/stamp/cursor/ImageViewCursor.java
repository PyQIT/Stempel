package pl.stamp.cursor;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import pl.stamp.image.ImageViews;

import java.util.ArrayList;

public class ImageViewCursor extends ImageViews {

    public ImageViewCursor(ImageView imageView) {
        super(imageView);
    }
    ArrayList predefinedData = new ArrayList<>();
    private Double tmp;

    @Override
    public void addEventListener() {
        this.imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            if (event.getButton().equals(MouseButton.SECONDARY)){
                this.imageView.getScene().setCursor(CursorImage.getCircleCursorImage(radius));
            }
        });

        this.imageView.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
            if (event.getButton().equals(MouseButton.SECONDARY)) {
                this.imageView.getScene().setCursor(CursorImage.getCircleCursorImage(radius));
            } else {
                this.setDefaultCursor(this.imageView.getScene());
            }
        });

        this.imageView.addEventHandler(MouseEvent.MOUSE_EXITED, event -> this.setDefaultCursor(this.imageView.getScene()));
    }

    public void setChoiceBoxData() {

        predefinedData.add(20.0);
        predefinedData.add(40.0);
        predefinedData.add(60.0);
        predefinedData.add(80.0);
    }

    public void addChoiceBoxListener(Integer choiceBoxIndex){
        tmp = (Double)predefinedData.get(choiceBoxIndex);
        System.out.println(predefinedData.get(choiceBoxIndex));
        radiusValueAssigment(tmp);
    }

    private void setDefaultCursor(Scene scene) {
        scene.setCursor(Cursor.DEFAULT);
    }

}
