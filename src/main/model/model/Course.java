package model.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Course {
    private String courseName;
    private int courseCredit;
    private ArrayList<Course> preReqs;


    // EFFECTS: constructor for course
    public Course(String courseName, int courseCredit, ArrayList<Course> preReqs) {
        this.courseName = courseName;
        this.courseCredit = courseCredit;
        this.preReqs = preReqs;
    }

    //EFFECTS: constructor for course without preReqs
    public Course(String courseName, int courseCredit) {
        this.courseName = courseName;
        this.courseCredit = courseCredit;
    }

    // EFFECTS: returns the courseName of the course
    public String getCourseName() {
        return courseName;
    }

    // EFFECTS: returns the courseCredit of the course
    public int getCourseCredit() {
        return courseCredit;
    }

    // EFFECTS: returns the preReqs necessary to take the course
    public ArrayList<Course> getPreReqs() {
        return preReqs;
    }


    public abstract int getCost();

    @Override
    public String toString() {
        return "Course{"
                + "courseName='" + courseName + '\''
                + ", courseCredit=" + courseCredit
                + ", preReqs='" + preReqs + '\''
                + '}';
    }

    public abstract void getCourseDetails();

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Course)) return false;
//        Course course = (Course) o;
//        return courseName.equals(course.courseName);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(courseName);
//    }
//
//    public void addAssociatedSpecialization(Specialization specialization) {
//        if (!associatedSpecializations.contains(specialization)) {
//            associatedSpecializations.add(specialization);
//            specialization.addCourse(this);
//        }
//    }

}
