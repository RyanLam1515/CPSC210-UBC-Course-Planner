package test;


import model.databases.CourseDataBase;
import model.exceptions.CourseNotAvailableException;
import model.exceptions.DidNotCompleteCourseException;
import model.exceptions.NotRegisteredInCourseException;
import model.exceptions.OverMaxCourseCreditException;
import model.model.Course;
import model.model.CoursePlanner;
import model.model.OnCampusCourse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static model.databases.CourseDataBase.*;
import static org.junit.jupiter.api.Assertions.*;


public class CoursePlannerTest {

    private static CoursePlanner coursePlanner;
    private static CourseDataBase courseDataBase;

    @BeforeEach
    void beforeEachTest() throws IOException {
        coursePlanner = new CoursePlanner();
        courseDataBase = new CourseDataBase();
        courseDataBase.initializeFirstYearCourses();
        courseDataBase.initializeSecondYearCourses();
        courseDataBase.initializeThirdYearCourses();



    }

    @Test
    public void testAddOne() {
        try {
            coursePlanner.addCourse(CPSC110);
        } catch (CourseNotAvailableException e) {
            fail("Exception not expected");
        } catch (OverMaxCourseCreditException e) {
            fail("Exception not expected");
        }
        assertTrue(coursePlanner.contains(CPSC110,"coursesTaking"));
        assertEquals(1, coursePlanner.size("coursesTaking"));
        assertEquals(4,coursePlanner.getCurrentCourseCredits());
    }

    @Test
    public void testAddDuplicate() {
        try {
            coursePlanner.addCourse(CPSC110);
            coursePlanner.addCourse(CPSC110);
        } catch (CourseNotAvailableException e) {
            System.out.println("test passed");
        } catch (OverMaxCourseCreditException e) {
            fail("Exception not expected");
        }
        assertTrue(coursePlanner.contains(CPSC110,"coursesTaking"));
        assertEquals(1,coursePlanner.size("coursesTaking"));
        assertEquals(4,coursePlanner.getCurrentCourseCredits());

    }

    @Test
    public void testAddOver31CreditsOverMaxCourseCreditExceptionExpected() {
        Course CPSC900 = new OnCampusCourse("CPSC900",31);
        coursesOffered.put("CPSC900",CPSC900);
        try {
            coursePlanner.addCourse(CPSC900);
        } catch (CourseNotAvailableException e) {
            fail("Exception not expected");
        } catch (OverMaxCourseCreditException e) {
            System.out.println("test passed");
        }
        assertFalse(coursePlanner.contains(CPSC900,"coursesTaking"));
        assertEquals(0,coursePlanner.size("coursesTaking"));
        assertEquals(0,coursePlanner.getCurrentCourseCredits());
    }

    @Test
    public void testAddTwo() {
        try {
            coursePlanner.addCourse(CPSC110);
            coursePlanner.addCourse(CPSC121);
        } catch (CourseNotAvailableException e) {
            fail("Exception not expected");
        } catch (OverMaxCourseCreditException e) {
            fail("Exception not expected");
        }
        assertTrue(coursePlanner.contains(CPSC110, "coursesTaking"));
        assertTrue(coursePlanner.contains(CPSC121, "coursesTaking"));
        assertEquals(2, coursePlanner.size("coursesTaking"));
        assertEquals(8,coursePlanner.getCurrentCourseCredits());

    }

    @Test
    public void testDropOne() {
        try {
            coursePlanner.addCourse(CPSC110);
            coursePlanner.dropCourse(CPSC110);
        } catch (CourseNotAvailableException e) {
            fail("Exception not expected");
        } catch (OverMaxCourseCreditException e) {
            fail("Exception not expected");
        } catch (NotRegisteredInCourseException e) {
            fail("Exception not expected");
        }
        assertTrue(coursesOffered.containsValue(CPSC110));
        assertFalse(coursePlanner.getCoursesTaking().contains(CPSC110));
        assertEquals(0, coursePlanner.size("coursesTaking"));
        assertEquals(0, coursePlanner.getCurrentCourseCredits());
    }

    @Test
    public void testDropOneDuplicate() {

        try {
            coursePlanner.addCourse(CPSC110);
            coursePlanner.dropCourse(CPSC110);
            coursePlanner.dropCourse(CPSC110);
        } catch (NotRegisteredInCourseException e) {
            System.out.println("test passed");
        } catch (CourseNotAvailableException e) {
            fail("Exception not expected");
        } catch (OverMaxCourseCreditException e) {
            fail("Exception not expected");
        }
        assertTrue(coursesOffered.containsValue(CPSC110));
        assertEquals(0, coursePlanner.size("coursesTaking"));
        assertEquals(0,coursePlanner.getCurrentCourseCredits());
    }

    @Test
    public void testDropTwo() {
        try {
            coursePlanner.addCourse(CPSC110);
            coursePlanner.addCourse(CPSC121);
            coursePlanner.dropCourse(CPSC110);
            coursePlanner.dropCourse(CPSC121);
        } catch (CourseNotAvailableException e) {
            fail("Exception not expected");
        } catch (OverMaxCourseCreditException e) {
            fail("Exception not expected");
        } catch (NotRegisteredInCourseException e) {
            fail("Exception not expected");
        }
        assertTrue(coursesOffered.containsValue(CPSC110));
        assertTrue(coursesOffered.containsValue(CPSC121));
        assertEquals(0, coursePlanner.size("coursesTaking"));
        assertEquals(0,coursePlanner.getCurrentCourseCredits());
    }

    @Test
    public void testCompleteOne() {
        try {
            coursePlanner.addCourse(CPSC110);
            coursePlanner.completeCourse(CPSC110);
        } catch (CourseNotAvailableException e) {
            fail("Exception not expected");
        } catch (OverMaxCourseCreditException e) {
            fail("Exception not expected");
        } catch (DidNotCompleteCourseException e) {
            fail("Exception not expected");
        }
        assertTrue(coursePlanner.contains(CPSC110, "coursesCompleted"));
        assertEquals(1, coursePlanner.size("coursesCompleted"));
        assertEquals(4,coursePlanner.getCompletedCourseCredits());
        assertTrue(coursePlanner.getCoursesCompleted().contains(CPSC110));
    }

    @Test
    public void testCompleteOneDuplicate() {
        try {
            coursePlanner.addCourse(CPSC110);
            coursePlanner.completeCourse(CPSC110);
            coursePlanner.completeCourse(CPSC110);
        } catch (DidNotCompleteCourseException e) {
            System.out.println("test passed");
        } catch (CourseNotAvailableException e) {
            fail("Exception not expected");
        } catch (OverMaxCourseCreditException e) {
            fail("Exception not expected");
        }
        assertTrue(coursePlanner.contains(CPSC110, "coursesCompleted"));
        assertEquals(1, coursePlanner.size("coursesCompleted"));
        assertEquals(4,coursePlanner.getCompletedCourseCredits());
        assertTrue(coursePlanner.getCoursesCompleted().contains(CPSC110));
    }

    @Test
    public void testCompleteTwo() {
        try {
            coursePlanner.addCourse(CPSC110);
            coursePlanner.addCourse(CPSC121);
            coursePlanner.completeCourse(CPSC110);
            coursePlanner.completeCourse(CPSC121);
        } catch (CourseNotAvailableException e) {
            fail("Exception not expected");
        } catch (OverMaxCourseCreditException e) {
            fail("Exception not expected");
        } catch (DidNotCompleteCourseException e) {
            fail("Exception not expected");
        }
        assertTrue(coursePlanner.contains(CPSC110, "coursesCompleted"));
        assertTrue(coursePlanner.contains(CPSC121, "coursesCompleted"));
        assertEquals(2, coursePlanner.size("coursesCompleted"));
        assertEquals(0,coursePlanner.getCurrentCourseCredits());
        assertEquals(8,coursePlanner.getCompletedCourseCredits());
    }

    @Test
    public void testContainsWithCoursesTaking() {
        assertFalse(coursePlanner.contains(CPSC110,"coursesTaking"));
        try {
            coursePlanner.addCourse(CPSC110);
        } catch (CourseNotAvailableException e) {
            fail("Exception not expected");
        } catch (OverMaxCourseCreditException e) {
            fail("Exception not expected");
        }
        assertTrue(coursePlanner.contains(CPSC110,"coursesTaking"));
    }

    @Test
    public void testContainsWithCoursesOffered() {
        assertTrue(coursePlanner.contains(CPSC110, "coursesOffered"));
    }

    @Test
    public void testContainsWithFakeCourseList() {
        assertFalse(coursePlanner.contains(CPSC110,"fake"));
    }

    @Test
    public void testSizeCoursesTaking() {
        assertEquals(0,coursePlanner.size("coursesTaking"));
        try {
            coursePlanner.addCourse(CPSC110);
            coursePlanner.addCourse(CPSC121);
            coursePlanner.addCourse(CPSC210);
        } catch (CourseNotAvailableException e) {
            fail("Exception not expected");
        } catch (OverMaxCourseCreditException e) {
            fail("Exception not expected");
        }
        assertEquals(3,coursePlanner.size("coursesTaking"));
    }

    @Test
    public void testSizeCoursesOffered() {
        assertEquals(13,coursePlanner.size("coursesOffered"));
    }

    @Test
    public void testSizeWithFakeList() {
        assertEquals(-1,coursePlanner.size("fake"));
    }

    @Test
    public void testSetCurrentCourseCredits() {
        assertEquals(0,coursePlanner.getCurrentCourseCredits());
        coursePlanner.setCurrentCourseCredits(15);
        assertEquals(15,coursePlanner.getCurrentCourseCredits());
    }

    @Test
    public void testSetCompleteCourseCredits() {
        assertEquals(0,coursePlanner.getCompletedCourseCredits());
        coursePlanner.setCompletedCourseCredits(15);
        assertEquals(15,coursePlanner.getCompletedCourseCredits());
    }

    @Test
    public void testSetCompleteCourseCredits() {
        assertEquals(0,coursePlanner.getCompletedCourseCredits());
        coursePlanner.setCompletedCourseCredits(15);
        assertEquals(15,coursePlanner.getCompletedCourseCredits());
    }

}

