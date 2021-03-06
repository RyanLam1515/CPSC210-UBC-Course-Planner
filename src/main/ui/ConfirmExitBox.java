package ui;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmExitBox {
    private static Button yesButton = new Button("Yes");
    private static Button noButton = new Button("No");
    private static Stage window = new Stage();

    static boolean answer;

    public static boolean display(String title, String message) {
   //     window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(message);

        yesButtonSetUp();

        noButtonSetUp();

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10,10,10,10));
        layout.getChildren().addAll(label, yesButton,noButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }

    public static void yesButtonSetUp() {
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
    }

    public static void noButtonSetUp() {
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });
    }
}
