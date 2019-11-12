package pl.stamp.image;

import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.*;
import pl.stamp.cursor.ImageViewCursor;
import pl.stamp.pixel.PixelCopy;

import java.util.ArrayList;
import java.util.List;

public class ImageViewMarker extends ImageViewCursor {

    private ImageView selectedAreaImageView;

    private List<Integer> copiedPixels = new ArrayList<>();

    public ImageViewMarker(ImageView imageView) {
        super(imageView);
    }

    public void setSelectedAreaImageView(ImageView selectedAreaImageView) {
        this.selectedAreaImageView = selectedAreaImageView;
    }

    @Override
    public void addEventListener() {
        this.imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            int selectedAreaCenterX = (int)event.getX();
            int selectedAreaCenterY = (int)event.getY();

            System.out.println("addEventListener secondary 0 " + radius);


            if (event.getButton().equals(MouseButton.SECONDARY)) {
                System.out.println("addEventListener secondary 1 " + radius);

                copiedPixels.clear();
                System.out.println("addEventListener secondary 2 " + radius);

                clearSelectedAreaImageView();

                System.out.println("addEventListener secondary 3 " + radius);

                copiedPixels = PixelCopy
                        .getCopiedPixelsList(
                                this.imageView.getImage().getPixelReader(),
                                radius,
                                selectedAreaCenterX,
                                selectedAreaCenterY);

                WritableImage dest = PixelCopy.getImageWithCopiedPixels(copiedPixels, radius);
                this.selectedAreaImageView.setImage(dest);
            }

            if (!copiedPixels.isEmpty()) {
                WritableImage dest =
                        PixelCopy.putSelectedAreaToImage(
                                this.imageView.getImage(),
                                copiedPixels,
                                radius,
                                selectedAreaCenterX,
                                selectedAreaCenterY);

                this.imageView.setImage(dest);
            }
        });
    }

    private void clearSelectedAreaImageView() {
        this.selectedAreaImageView.setImage(null);
        this.selectedAreaImageView.setFitWidth((int)(radius * 2));
        this.selectedAreaImageView.setFitHeight((int)(radius * 2));
    }
}
