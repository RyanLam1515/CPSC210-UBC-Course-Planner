package model.model;

import model.observer.CoursePlannerObserver;
import model.exceptions.CourseNotAvailableException;
import model.exceptions.NotRegisteredInCourseException;
import model.exceptions.OverMaxCourseCreditException;
import model.exceptions.DidNotCompleteCourseException;
import model.interfaces.Loadable;
import model.interfaces.Saveable;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import static model.databases.CourseDataBase.coursesOffered;
import static ui.Main.coursePlanner;

public class CoursePlanner extends Observable implements Loadable, Saveable {
    private static ArrayList<Course> coursesTaking;
    private static ArrayList<Course> coursesCompleted;
    private static int currentCourseCredits;
    private static int completedCourseCredits;
    public static String FILENAME = "savedcourses.txt";
    static String SPLIT_CHAR = "\t";
    private static List<CoursePlannerObserver> observers = new ArrayList<>();

    private static final int MAX_COURSE_CREDITS = 30;

    public CoursePlanner() {
        this.coursesTaking = new ArrayList<Course>();
        this.coursesCompleted = new ArrayList<Course>();
        this.currentCourseCredits = 0;
        this.completedCourseCredits = 0;
        addObserver(new CoursePlannerObserver());

    }


    @Override
    public void loadCourses() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(FILENAME));

        System.out.println("Here are the courses I found:");

        for (String line : lines) {
            String[] parts = split(line);
            System.out.println("Loaded course: " + line + " credits");

        }
    }

    public static String[] split(String line) {
        return line.split(SPLIT_CHAR);
    }

    @Override
    public void saveCourses() throws IOException {
        PrintWriter writer = new PrintWriter(FILENAME,"UTF-8");

        for (Course c : coursesTaking) {
            String line = c.getCourseName() + " " + c.getCourseCredit();
            writer.println(line);
        }

        writer.close();
        System.out.println("Courses saved: ");
        for (Course c: coursesTaking) {
            System.out.println(c.getCourseName());
        }
        System.out.println("You are currently registered for: " + getCurrentCourseCredits() + " credits.");
    }

    public ArrayList<Course> getCoursesTaking() {
        return coursesTaking;
    }

    public ArrayList<Course> getCoursesCompleted() {
        return coursesCompleted;
    }

    public int getCurrentCourseCredits() {
        return currentCourseCredits;
    }

    public int getCompletedCourseCredits() {
        return completedCourseCredits;
    }

    public void setCurrentCourseCredits(int newCurrentCourseCredits) {
        this.currentCourseCredits = newCurrentCourseCredits;
    }

    public void setCompletedCourseCredits(int newCompletedCourseCredits) {
        this.completedCourseCredits = newCompletedCourseCredits;
    }

    //REQUIRES: desired course must be in coursesOffered prior
    //MODIFIES: this
    //EFFECTS: removes a course from coursesOffered and adds it to coursesTaking unless
    //         it is already in coursesTaking in which case it does nothing
    public void addCourse(Course course) throws CourseNotAvailableException, OverMaxCourseCreditException {
        if (!coursePlanner.coursesTaking.contains(course)) {
            if ((currentCourseCredits + course.getCourseCredit()) > MAX_COURSE_CREDITS) {
                throw new OverMaxCourseCreditException();
            }
            coursesTaking.add(course);
            currentCourseCredits = currentCourseCredits + course.getCourseCredit();
            System.out.println("You have successfully registered for " + course.getCourseName() + "!");
            setChanged();
            notifyObservers(this);
        } else {
            throw new CourseNotAvailableException();
        }
    }

    //REQUIRES: desired course must be in coursesTaking prior
    //MODIFIES: this
    //EFFECTS: removes a course from coursesTaking and adds it to coursesCompleted unless
    //         it is already in coursesCompleted in which case it does nothing
    public void completeCourse(Course course) throws DidNotCompleteCourseException {
        if (!coursesCompleted.contains(course) && (coursesTaking.contains(course))) {
            coursesTaking.remove(course);
            coursesCompleted.add(course);
            currentCourseCredits = currentCourseCredits - course.getCourseCredit();
            completedCourseCredits = completedCourseCredits + course.getCourseCredit();
            System.out.println("Congratulations on completing " + course.getCourseName() + "!");
            setChanged();
            notifyObservers(this);
        } else {
            throw new DidNotCompleteCourseException();
        }
    }

    //REQUIRES: desired course must be in coursesTaking prior
    //MODIFIES: this
    //EFFECTS: removes a course from coursesTaking and adds it to coursesOffered unless
    //         it is already in coursesOffered in which case it does nothing
    public void dropCourse(Course course) throws NotRegisteredInCourseException {
        if  (coursesTaking.contains(course)) {
            coursesTaking.remove(course);
//            coursesOffered.put(course.getCourseName(),course);
            currentCourseCredits = currentCourseCredits - course.getCourseCredit();
            System.out.println("You have successfully dropped " + course.getCourseName() + "!");
            setChanged();
            notifyObservers(this);
        } else {
            throw new NotRegisteredInCourseException();
        }
    }

    public int size(String courseListName) {
        if (courseListName.equals("coursesOffered")) {
            return coursesOffered.size();
        }
        if (courseListName.equals("coursesTaking")) {
            return coursesTaking.size();
        }
        if (courseListName.equals("coursesCompleted")) {
            return coursesCompleted.size();
        }
        return -1;
    }

    public boolean contains(Course course, String courseListName) {
        if (courseListName.equals("coursesOffered")) {
            System.out.println(coursesOffered);
            return coursesOffered.containsValue(course);
        }
        if (courseListName.equals("coursesTaking")) {
            System.out.println(coursesTaking);
            return coursesTaking.contains(course);
        }
        if (courseListName.equals("coursesCompleted")) {
            System.out.println(coursesCompleted);
            return coursesCompleted.contains(course);
        }
        return false;
    }

    //    public boolean checkPreReqs(Course course) {
//        for (Course c: course.getPreReqs()) {
//           if (coursesCompleted.contains(c)) {
//               System.out.println("You are eligible to take " + course.getCourseName());
//               return true;
//           }
//        }
//        System.out.println("You do not have the pre-requisites to take " + course.getCourseName());
//            return false;
//        }
}




