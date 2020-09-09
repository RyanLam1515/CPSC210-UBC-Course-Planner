package model.model;

import java.util.ArrayList;

public class OnCampusCourse extends Course {

    //EFFECTS: constructor OnCampusCourse with preReqs
    public OnCampusCourse(String courseName, int courseCredit, ArrayList<Course> preReqs) {
        super(courseName, courseCredit, preReqs);
    }

    //EFFECTS: constructor for OnCampusCourse without preReqs
    public OnCampusCourse(String courseName, int courseCredit) {
        super(courseName,courseCredit);
    }

    @Override
    public int getCost() {
        return 600;
    }

    @Override
    public void getCourseDetails() {
        System.out.println("The average cost for an on campus course at UBC is $600. "
                + "Additional fees may be charged if the course has a lab or has a course credit > 3. "
                + "All test including the final will be written in class");

    }
}
