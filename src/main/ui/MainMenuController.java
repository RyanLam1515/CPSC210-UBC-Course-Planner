package ui;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.databases.CourseDataBase;
import model.model.Course;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static model.databases.CourseDataBase.coursesOffered;
import static model.model.CoursePlanner.FILENAME;
import static ui.DisplayMessage.display;
import static ui.Main.*;

public class MainMenuController implements Initializable {

    public Button addCourseButton;
    public Button dropCourseButton;
    public Button completeCourseButton;
    public Button saveCoursesButton;
    public Button loadCoursesButton;
    public Button checkCreditsButton;
    public Button costInformationButton;
    public ComboBox<String> addCourseComboBox = new ComboBox<>();
    public ComboBox<String> dropCourseComboBox = new ComboBox<>();
    public ComboBox<String> completeCourseComboBox = new ComboBox<>();
    public ComboBox<String> preReqCheckComboBox = new ComboBox<>();
    private CourseDataBase courseDataBase = new CourseDataBase();
    private ArrayList<String> addCoursesOptions = new ArrayList<>();
    private ArrayList<String> dropCoursesOptions = new ArrayList<>();
    public TableView<Course> registeredCoursesTableView = new TableView<>();
    public TextArea mainMenuTextArea = new TextArea();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        courseDataBase.initializeFirstYearCourses();
        courseDataBase.initializeSecondYearCourses();
        courseDataBase.initializeThirdYearCourses();
        initializeCoursesOffered();
        mainMenuTextArea.setText("Welcome, user."  + "\n"
                + "Thank you for using Ryan's Course Planner.");
        tableViewSetUp();
    }

    public void handleAddCourseButtonClick() throws IOException {
        menuManager.requestAddCourse(addCourseComboBox.getValue());
        if (!dropCoursesOptions.contains(addCourseComboBox.getValue())) {
            dropCoursesOptions.add(addCourseComboBox.getValue());
        }
        dropCourseComboBox.setItems(FXCollections.observableList(dropCoursesOptions));
        completeCourseComboBox.setItems(FXCollections.observableList(dropCoursesOptions));
        registeredCoursesTableView.setItems(FXCollections.observableList(coursePlanner.getCoursesTaking()));
        registeredCoursesTableView.refresh();
    }

    public void handleDropCourseButtonClick() {
        menuManager.requestDropCourse(dropCourseComboBox.getValue());
        dropCoursesOptions.remove(dropCourseComboBox.getValue());
        dropCourseComboBox.setItems(FXCollections.observableList(dropCoursesOptions));
        completeCourseComboBox.setItems(FXCollections.observableList(dropCoursesOptions));
        registeredCoursesTableView.setItems(FXCollections.observableList(coursePlanner.getCoursesTaking()));
        registeredCoursesTableView.refresh();
    }

    public void handleCompleteCourseButtonClick() {
        menuManager.requestCompleteCourse(completeCourseComboBox.getValue());
        dropCoursesOptions.remove(completeCourseComboBox.getValue());
        dropCourseComboBox.setItems(FXCollections.observableList(dropCoursesOptions));
        completeCourseComboBox.setItems(FXCollections.observableList(dropCoursesOptions));
        registeredCoursesTableView.setItems(FXCollections.observableList(coursePlanner.getCoursesTaking()));
        registeredCoursesTableView.refresh();
    }

    public void handleSaveCoursesButtonClick() throws IOException {
        menuManager.requestSaveCourses();
        mainMenuTextArea.setText("Your courses will be saved in savedcourses.txt" + "\n");
        mainMenuTextArea.appendText("Saved courses: \n");
        for (Course c: coursePlanner.getCoursesTaking()) {
            mainMenuTextArea.appendText(c.getCourseName() + "\n");
        }
    }

    public void handleLoadCoursesButtonClick() throws IOException {
        menuManager.requestLoadCourses();
        List<String> lines = Files.readAllLines(Paths.get(FILENAME));

        mainMenuTextArea.setText("Here are the courses I found:" + "\n");

        for (String line : lines) {
            mainMenuTextArea.appendText("Loaded course: " + line + " credits." + "\n");
        }
    }

    public void initializeCoursesOffered() {
        for (String courseKey: coursesOffered.keySet()) {
            addCoursesOptions.add(courseKey);
        }
        addCourseComboBox.setItems(FXCollections.observableList(addCoursesOptions));
        preReqCheckComboBox.setItems(FXCollections.observableList(addCoursesOptions));
    }

    public void handleCheckCreditsButtonClick() {
        display("Check Credits Display", "You are currently taking " + coursePlanner.getCurrentCourseCredits()
                + " credits in the upcoming semester. \n"
                + "You have completed " + coursePlanner.getCompletedCourseCredits() + " credits so far.");

    }

    public void handleCostInformationButtonClick() {
        display("Course Cost Information Display", "The average cost for an on campus course at UBC is $600. \n"
                +  " Additional fees may be charged if the course has a lab or has a course credit > 3. \n"
                + "Online courses have an additional fee of $50 in addition to the regular cost of a course.");

    }

    public boolean handlePreReqCheckButtonClick() {
        String courseName = preReqCheckComboBox.getValue();
        Course course = courseDataBase.coursesOffered.get(courseName);
        if (course.getPreReqs() == null) {
            display("Pre-Requisite Check Display", "You are eligible to take " + course.getCourseName());
            return true;
        }
        for (Course c: course.getPreReqs()) {
            if (coursePlanner.getCoursesCompleted().contains(c)) {
                display("Pre-Requisite Check Display", "You are eligible to take " + course.getCourseName() + ".");
                return true;
            }
        }
        display("PreReq Check Display", "You do not have the pre-requisites to take " + course.getCourseName());

        mainMenuTextArea.setText("The pre-requisites for " + course.getCourseName() + " are:" + "\n");

        for (Course c: course.getPreReqs()) {
            mainMenuTextArea.appendText(c.getCourseName() + "\n");
        }
        return false;
    }


    public void menuBarCloseProgram() {
        closeProgram();
    }

    public void menuBarAbout() {
        display("\"About Program","Course Planner Application version 1.0 created by Ryan Lam. "
                + " This application was created to help students keep track of the courses they are going to take "
                + "and the amount of credits they are registered for prior to the registration date.");
    }

    public void menuBarClearList() {
        registeredCoursesTableView.setItems(null);
        registeredCoursesTableView.refresh();
        coursePlanner.getCoursesTaking().clear();
        coursePlanner.getCoursesCompleted().clear();
        coursePlanner.setCurrentCourseCredits(0);
        coursePlanner.setCompletedCourseCredits(0);
    }

    public void tableViewSetUp() {
        TableColumn<Course,String> nameColumn = new TableColumn<>("Course Name");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));

        TableColumn<Course,Integer> creditsColumn = new TableColumn<>("Course Credit");
        creditsColumn.setMinWidth(90);
        creditsColumn.setCellValueFactory(new PropertyValueFactory<>("courseCredit"));

        TableColumn<Course, ArrayList<Course>> preReqColumn = new TableColumn<>("Pre-Requisites");
        preReqColumn.setMinWidth(250);
        preReqColumn.setCellValueFactory(new PropertyValueFactory<>("preReqs"));

        registeredCoursesTableView.getColumns().addAll(nameColumn,creditsColumn,preReqColumn);
        registeredCoursesTableView.setItems(FXCollections.observableList(coursePlanner.getCoursesTaking()));
    }


}








