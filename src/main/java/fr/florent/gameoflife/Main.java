package fr.florent.gameoflife;

import fr.florent.gameoflife.process.Process;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

public class Main extends Application {

    private static Logger LOGGER = Logger.getLogger(Main.class.getName());

    private int width = 100;
    private int height = 100;

    private Canvas canvas;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {


        stage.setTitle("Game of life");

        Pane root = initScreen();

        Rectangle2D rectangle2D = Screen.getPrimary().getBounds();
        stage.setScene(new Scene(root, rectangle2D.getWidth(), rectangle2D.getHeight() - 100));
        stage.show();


        launchProcess();
    }

    private Pane initScreen() {

        AnchorPane root = new AnchorPane();

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(vBox);

        canvas = new Canvas();
        canvas.setWidth(width * 4);
        canvas.setHeight(height * 4);

        hBox.getChildren().add(canvas);

        AnchorPane.setTopAnchor(hBox, 0d);
        AnchorPane.setLeftAnchor(hBox, 0d);
        AnchorPane.setRightAnchor(hBox, 0d);
        AnchorPane.setBottomAnchor(hBox, 0d);
        root.getChildren().add(hBox);

        return root;

    }

    private void launchProcess() {
        Process process = new Process(canvas);
        // process.initRandomState();
        //process.initAshForestState();
        //process.initForestState();
        process.initOldForestState();
        process.paintStates();

        new Thread(() -> {
            try {
                run(process);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


    }

    private void run(Process process) throws InterruptedException {
        while (true) {
            process.recalculateState();
            Platform.runLater(() -> process.paintStates());
            Thread.sleep(10);
        }
    }


}
