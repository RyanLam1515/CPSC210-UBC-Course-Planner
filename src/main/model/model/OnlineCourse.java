package model.model;

import java.util.ArrayList;

public class OnlineCourse extends Course {
    public OnlineCourse(String courseName, int courseCredit, ArrayList<Course> preReqs) {
        super(courseName, courseCredit, preReqs);
    }

    public OnlineCourse(String courseName, int courseCredit) {
        super(courseName,courseCredit);
    }

    @Override
    public int getCost() {
        return 650;
    }

    @Override
    public void getCourseDetails() {
        System.out.println("The average cost for an online course at UBC is $650. "
                + "There will be no face to face meetings for most of these courses. All course material "
                + "will be provided online on the courses websites. "
                + "All test except the final will be written online so a working laptop is a necessity.");
    }
}
