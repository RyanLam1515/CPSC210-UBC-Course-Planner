package model.observer;

import model.model.CoursePlanner;

import java.util.Observable;
import java.util.Observer;

public class CoursePlannerObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        CoursePlanner cp = (CoursePlanner) arg;
//        System.out.println("Currently selecting: " + arg);
//        numCourses = numCourses + 1;
//        numCredits = numCredits + cp.getCourseCredit();
//        System.out.println();
//        System.out.println("You are currently taking " + numCourses + " courses for a total of " + numCredits
//                + " credits in the upcoming semester.");
        System.out.println("You are currently taking " + cp.getCurrentCourseCredits() + " credits.");
        System.out.println(" ");
        System.out.println("You have completed " + cp.getCompletedCourseCredits() + " credits so far.");
    }
}
