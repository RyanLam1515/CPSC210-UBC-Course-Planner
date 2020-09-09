package model.databases;

import model.model.Course;
import model.model.OnCampusCourse;
import model.model.OnlineCourse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CourseDataBase {
    public static final Course CPSC110 = new OnCampusCourse("CPSC110", 4);
    public static final Course ATSC113 = new OnlineCourse("ATSC113", 3);
    private static ArrayList<Course> cpsc121preReqs = new ArrayList<>();
    public static final Course CPSC121 = new OnCampusCourse("CPSC121", 4, cpsc121preReqs);
    public static final Course MATH100 = new OnCampusCourse("MATH100",3);
    private static  ArrayList<Course> math101preReqs = new ArrayList<>();
    public static final Course MATH101 = new OnCampusCourse("MATH101",3,math101preReqs);
    private static ArrayList<Course> math221PreReqs = new ArrayList<>();
    public static final Course MATH221 = new OnCampusCourse("MATH221",3, math221PreReqs);
    private static ArrayList<Course> math200PreReqs = new ArrayList<>();
    public static final Course MATH200 = new OnCampusCourse("MATH200",3,math200PreReqs);
    private static ArrayList<Course> cpsc210preReqs = new ArrayList<>();
    public static final Course CPSC210 = new OnCampusCourse("CPSC210", 4, cpsc210preReqs);
    private static ArrayList<Course> cpsc213preReqs = new ArrayList<>();
    public static final Course CPSC213 = new OnCampusCourse("CPSC213", 4, cpsc213preReqs);
    private static ArrayList<Course> cpsc221preReqs = new ArrayList<>();
    public static final Course CPSC221 = new OnCampusCourse("CPSC221", 4, cpsc221preReqs);
    private static  ArrayList<Course> cpsc310preReqs = new ArrayList<>();
    public static final Course CPSC310 = new OnCampusCourse("CPSC310",4,cpsc310preReqs);
    private static ArrayList<Course> cpsc313preReqs = new ArrayList<>();
    public static final Course CPSC313 = new OnCampusCourse("CPSC313",3,cpsc313preReqs);
    private static ArrayList<Course> cpsc320preReqs = new ArrayList<>();
    public static final Course CPSC320 = new OnCampusCourse("CPSC320",3,cpsc320preReqs);
    public static Map<String,Course> coursesOffered;


    public CourseDataBase() {

        this.coursesOffered = new HashMap<>();
    }

    public void initializeFirstYearCourses() {
        cpsc121preReqs.add(CPSC110);
        math101preReqs.add(MATH100);
        coursesOffered.put("CPSC110",CPSC110);
        coursesOffered.put("ATSC113",ATSC113);
        coursesOffered.put("CPSC121",CPSC121);
        coursesOffered.put("MATH100",MATH100);
        coursesOffered.put("MATH101",MATH101);

    }

    public void initializeSecondYearCourses() {
        cpsc210preReqs.add(CPSC110);
        cpsc213preReqs.add(CPSC210);
        cpsc213preReqs.add(CPSC121);
        cpsc221preReqs.add(CPSC210);
        cpsc221preReqs.add(CPSC121);
        math221PreReqs.add(MATH100);
        math221PreReqs.add(MATH101);
        math200PreReqs.add(MATH101);
        coursesOffered.put("CPSC210",CPSC210);
        coursesOffered.put("CPSC213",CPSC213);
        coursesOffered.put("CPSC221",CPSC221);
        coursesOffered.put("MATH221",MATH221);
        coursesOffered.put("MATH200",MATH200);
    }

    public void initializeThirdYearCourses() {
        cpsc310preReqs.add(CPSC210);
        cpsc313preReqs.add(CPSC213);
        cpsc313preReqs.add(CPSC221);
        cpsc320preReqs.add(CPSC221);
        coursesOffered.put("CPSC310",CPSC310);
        coursesOffered.put("CPSC313",CPSC313);
        coursesOffered.put("CPSC320",CPSC320);
    }


//    public ArrayList<Course> getCoursesOffered() {
//
//        return coursesOffered;
//    }
}