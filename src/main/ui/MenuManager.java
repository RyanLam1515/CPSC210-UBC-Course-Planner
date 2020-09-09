package ui;


import model.exceptions.CourseNotAvailableException;
import model.exceptions.OverMaxCourseCreditException;
import model.model.Course;
import model.exceptions.DidNotCompleteCourseException;
import model.exceptions.NotRegisteredInCourseException;

import java.io.IOException;

import static model.databases.CourseDataBase.*;
import static ui.DisplayMessage.display;
import static ui.Main.coursePlanner;
import static ui.Main.courseDataBase;

public class MenuManager {



    public MenuManager() {
    }

    public void getCourseDetails(String courseStatus) {
        if (courseStatus.equals("Campus")) {
            CPSC110.getCost();
            CPSC110.getCourseDetails();
        }
        if (courseStatus.equals("Online")) {
            ATSC113.getCost();
            ATSC113.getCourseDetails();
        }

    }

    public void requestAddCourse(String courseName) {
        Course actualCourse = courseDataBase.coursesOffered.get(courseName);
        try {
            coursePlanner.addCourse(actualCourse);
        } catch (CourseNotAvailableException e) {
            display("Error Message:","You are already registered for that course.");
        } catch (OverMaxCourseCreditException e) {
            display("Error Message:","You have exceeded the maximum number of credits you can take during a semester.");
        }

    }

    public void requestCompleteCourse(String courseName) {
        Course actualCourse = null;
        for (Course c : coursePlanner.getCoursesTaking()) {
            if (courseName.equals(c.getCourseName())) {
                actualCourse = c;
                break;
            }
        }
        try {
            coursePlanner.completeCourse(actualCourse);
        } catch (DidNotCompleteCourseException e) {
            System.out.println("You did not complete this course.");
        }

    }

    public void requestDropCourse(String courseName) {
        Course actualCourse = null;
        for (Course c : coursePlanner.getCoursesTaking()) {
            if (courseName.equals(c.getCourseName())) {
                actualCourse = c;
                break;
            }
        }
        try {
            coursePlanner.dropCourse(actualCourse);
        } catch (NotRegisteredInCourseException e) {
            System.out.println("You were not registered for this course.");
        }

    }

    public void requestPrintCourses(String listToPrint) {
        if (listToPrint.equals("coursesCompleted")) {
            System.out.println("Courses completed: ");
            for (Course c: coursePlanner.getCoursesCompleted()) {
                System.out.println(c);
            }
        }
        if (listToPrint.equals("coursesOffered")) {
            System.out.println("Courses offered: ");
            System.out.println(coursesOffered.keySet());
        }
        if (listToPrint.equals("coursesTaking")) {
            System.out.println("Courses taking: ");
            for (Course c: coursePlanner.getCoursesTaking()) {
                System.out.println(c);
            }
        }


    }

    public void requestSaveCourses() throws IOException {
        System.out.println("Thank you for using Ryan's Course Planner. Your courses will be saved.");
        coursePlanner.saveCourses();

    }

    public void requestLoadCourses() throws IOException {
        System.out.println("Your courses will be loaded in a second.");
        coursePlanner.loadCourses();
    }

    public void requestCheckCredits() {
        System.out.println("You are currently taking " + coursePlanner.getCurrentCourseCredits()
                + " credits in the upcoming semester.");
        System.out.println("You have completed " + coursePlanner.getCompletedCourseCredits() + " credits so far.");
    }

}
