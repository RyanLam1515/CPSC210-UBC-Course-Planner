package test;


import model.databases.CourseDataBase;
import model.exceptions.CourseNotAvailableException;
import model.exceptions.OverMaxCourseCreditException;
import model.model.CoursePlanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;


import static model.databases.CourseDataBase.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaveAndLoadTest {
    private CoursePlanner coursePlanner;
    private CourseDataBase courseDataBase;

    @BeforeEach
    void beforeEachTest() throws IOException {
        coursePlanner = new CoursePlanner();
        courseDataBase = new CourseDataBase();
        courseDataBase.initializeFirstYearCourses();
        courseDataBase.initializeSecondYearCourses();

    }

    @Test
    public void testLoad() throws IOException, CourseNotAvailableException, OverMaxCourseCreditException {
        coursePlanner.addCourse(CPSC110);
        coursePlanner.addCourse(CPSC121);
        coursePlanner.saveCourses();
        assertEquals(2,coursePlanner.size("coursesTaking"));
    }

    @Test
    public void testSave() throws IOException, CourseNotAvailableException, OverMaxCourseCreditException {
        coursePlanner.addCourse(CPSC110);
        coursePlanner.addCourse(CPSC121);
        coursePlanner.saveCourses();
        assertEquals(2,coursePlanner.size("coursesTaking"));
        coursePlanner.loadCourses();
    }

    @Test
    public void testAddAfterSave() throws IOException, CourseNotAvailableException, OverMaxCourseCreditException {
        coursePlanner.addCourse(CPSC110);
        coursePlanner.addCourse(CPSC121);
        coursePlanner.saveCourses();
        assertEquals(2,coursePlanner.size("coursesTaking"));
        coursePlanner.addCourse(CPSC210);
        coursePlanner.saveCourses();
        coursePlanner.loadCourses();
    }
}

