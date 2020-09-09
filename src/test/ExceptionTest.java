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

import static model.databases.CourseDataBase.CPSC110;
import static org.junit.jupiter.api.Assertions.fail;

public class ExceptionTest {
    private CoursePlanner coursePlanner;
    private Course CPSC911;
    private CourseDataBase courseDataBase;

    @BeforeEach
    void beforeEachTest() {
        courseDataBase = new CourseDataBase();
        coursePlanner = new CoursePlanner();
        courseDataBase.initializeFirstYearCourses();
    }

//    @Test
//    void testAddCourseNotAvailableCourseExceptionExpected() {
//        Course CPSC911 = new OnCampusCourse("CPSC911",0);
//        try {
//            coursePlanner.addCourse(CPSC911);
//            fail("Exception not expected");
//        } catch (CourseNotAvailableException e) {
//            System.out.println("test passed");
//        } catch (OverMaxCourseCreditException e) {
//            fail("Exception not expected");
//        }
//    }

    @Test
    void testAddCourseNoExceptionsExpected() {
        try {
            coursePlanner.addCourse(CPSC110);
        } catch (CourseNotAvailableException e) {
            fail("Exception not expected");
        } catch (OverMaxCourseCreditException e) {
            fail("Exception not expected");
        }
    }


    @Test
    void testAddCourseWithDulpicateCoursesNotAvailableCourseExceptionExpected() {
        try {
            coursePlanner.addCourse(CPSC110);
            coursePlanner.addCourse(CPSC110);
            fail("Exception not expected");
        } catch (CourseNotAvailableException e) {
            System.out.println("test passed");
        } catch (OverMaxCourseCreditException e) {
            fail("Exception not expected");
        }
    }

    @Test
    void testDropCourseWithFakeCourseNotRegisteredInCourseExceptionNotExpected() {
        try {
            coursePlanner.addCourse(CPSC110);
            coursePlanner.dropCourse(CPSC911);
        } catch (CourseNotAvailableException e) {
            fail("Exception not expected");
        } catch (NotRegisteredInCourseException e) {
            System.out.println("test passed");
        } catch (OverMaxCourseCreditException e) {
            fail("Exception not expected");
        }
    }

    @Test
    void testDropCourseTwiceNotRegisteredInCourseExceptionExpected() {
        try {
            coursePlanner.addCourse(CPSC110);
            coursePlanner.dropCourse(CPSC110);
            coursePlanner.dropCourse(CPSC110);
        } catch (CourseNotAvailableException e) {
            fail("Exception not expected");
        } catch (NotRegisteredInCourseException e) {
            System.out.println("test passed");
        } catch (OverMaxCourseCreditException e) {
            fail("Exception not expected");
        }
    }

    @Test
    void testCompleteCourseWithFakeCourseDidNotCompleteCourseExceptionExpected() {
        try {
            coursePlanner.addCourse(CPSC110);
            coursePlanner.completeCourse(CPSC911);
        } catch (CourseNotAvailableException e) {
            fail("Exception not expected");
        } catch (DidNotCompleteCourseException e) {
            System.out.println("test passed");
        } catch (OverMaxCourseCreditException e) {
            fail("Exception not expected");
        }
    }

    @Test
    void testCompleteCourseTwiceDidNotCompleteCourseExceptionWithDuplicateExpected() {
        try {
            coursePlanner.addCourse(CPSC110);
            coursePlanner.completeCourse(CPSC110);
            coursePlanner.completeCourse(CPSC110);
        } catch (CourseNotAvailableException e) {
            fail("Exception not expected");
        } catch (DidNotCompleteCourseException e) {
            System.out.println("test passed");
        } catch (OverMaxCourseCreditException e) {
            fail("Exception not expected");
        }
    }
}


