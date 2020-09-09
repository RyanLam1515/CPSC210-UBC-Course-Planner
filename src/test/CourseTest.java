package test;


import model.databases.CourseDataBase;
import model.model.Course;
import model.model.OnlineCourse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static model.databases.CourseDataBase.*;
import static org.junit.jupiter.api.Assertions.*;

public class CourseTest  {
    private CourseDataBase courseDataBase;

    @BeforeEach
    void beforeEachTest() {
        CourseDataBase courseDataBase = new CourseDataBase();
        courseDataBase.initializeFirstYearCourses();
        courseDataBase.initializeSecondYearCourses();
    }

    @Test
    public void testConstructor() {
        ArrayList<Course> cpsc315preReqs = new ArrayList<>();
        cpsc315preReqs.add(CPSC210);
        cpsc315preReqs.add(CPSC213);
        Course CPSC315 = new OnlineCourse("CPSC315",4,cpsc315preReqs);
    }


    @Test
    public void testGetCourseName() {
        assertEquals(CPSC110.getCourseName(),"CPSC110");
    }

    @Test
    public void testGetCourseCredit() {
        assertEquals(CPSC121.getCourseCredit(),4);
    }

    @Test
    public void testGetPreReqs() {
        assertTrue(CPSC210.getPreReqs().contains(CPSC110));
    }

    @Test
    public void testGetCostOnlineCourseCost() {
        assertEquals(650, (ATSC113.getCost()));
        ATSC113.getCourseDetails();
    }

    @Test
    public void testGetCostOnCampusCourse() {
        assertEquals(600,CPSC110.getCost());
        CPSC110.getCourseDetails();
    }




}

