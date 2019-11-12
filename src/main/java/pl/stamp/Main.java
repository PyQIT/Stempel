package pl.stamp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.FileInputStream;

public class Main extends Application {

    private final static String TITLE = "Stempel";
    final static String rootFXMLPath = "/root.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource(rootFXMLPath));
        final Controller controller = new Controller();
        loader.setController(controller);
        Pane root = loader.load();
        primaryStage.setTitle(TITLE);
        // create a input stream
        FileInputStream inputBackground = new FileInputStream("C:\\Users\\Asus\\Documents\\Projekt grafika\\Projekt\\stamp-javafx\\background.jpg");

        // create a image
        Image image = new Image(inputBackground);

        // create a background image
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        // create Background
        Background background = new Background(backgroundimage);

        // set background
        root.setBackground(background);
        primaryStage.setScene(new Scene(root));
        primaryStage.addEventHandler(WindowEvent.WINDOW_SHOWN, (e) -> Platform.runLater(controller::handleWindowShownEvent));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static String getTITLE() {
        return TITLE;
    }
}
