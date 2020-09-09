package ui;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static ui.Main.mainStage;

public class LogInController implements Initializable {

    // Student Information
    public Button logInButton;
    public static TextField logInName;



    public static void main(String[] args) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void handleLogInButtonClick() throws IOException {
        Parent mainMenuRoot = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        mainStage.setScene(new Scene(mainMenuRoot,800,600));
    }
}
