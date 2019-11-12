package pl.stamp.image;

import javafx.scene.image.ImageView;

public abstract class ImageViews {

    protected ImageView imageView;

    protected static double radius;

    protected ImageViews(ImageView imageView) {
        this.imageView = imageView;
    }

    protected void radiusValueAssigment(Double tmp){
        radius = tmp;
        System.out.println("radiusValueAssigment " + radius);
    }

    public abstract void addEventListener();
}
