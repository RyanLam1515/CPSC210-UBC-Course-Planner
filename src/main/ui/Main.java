package ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.databases.CourseDataBase;
import model.model.CoursePlanner;


public class Main extends Application {
    public static Stage mainStage;
    public static CoursePlanner coursePlanner;
    public static CourseDataBase courseDataBase;
    public static MenuManager menuManager;

    @Override
    public void start(Stage primaryStage) throws Exception {
        coursePlanner = new CoursePlanner();
        courseDataBase = new CourseDataBase();
        menuManager = new MenuManager();

        mainStage = primaryStage;

        Main.mainStage.setOnCloseRequest(e ->  {
            e.consume();
            closeProgram();
        });

        Parent logInRoot = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        Main.mainStage.setTitle("Course Planner Application");
        Main.mainStage.setScene(new Scene(logInRoot, 330, 370));
        Main.mainStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void closeProgram() {
        Boolean answer = ConfirmExitBox.display("Confirmation", "Are you sure you want to exit?");
        if (answer) {
            Platform.exit();
        }
    }


}
