package fi.tuni.prog3.junitattainment;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;


public class AttainmentTest {

    @Test
    public void courseCodeMatchesGivenLegalParameter() {
        Attainment attainment = new Attainment("courseCode", "studentNumber", 4);
        assertEquals("courseCode", attainment.getCourseCode());
    }

    @Test
    public void studentNumberMatchesGivenLegalParameter() {
        Attainment attainment = new Attainment("courseCode", "studentNumber", 4);
        assertEquals("studentNumber", attainment.getStudentNumber());
    }

    @ParameterizedTest
    @MethodSource("intProvider")
    public void gradeMatchesGivenLegalParameter(int grade) {
        Attainment attainment = new Attainment("courseCode", "studentNumber", grade);
        assertEquals(grade, attainment.getGrade());
    }

    @Test
    public void illegalArgumentExceptionIsThrownWithNullCourseCode() {
        assertThrows(IllegalArgumentException.class, () -> new Attainment(null, "studentNumber", 4));
    }

    @Test
    public void illegalArgumentExceptionIsThrownWithNullStudentNumber() {
        assertThrows(IllegalArgumentException.class, () -> new Attainment("courseCode", null, 4));
    }

    @Test
    public void illegalArgumentExceptionIsThrownWithNegativeGrade() {
        assertThrows(IllegalArgumentException.class, () -> new Attainment("courseCode", "studentNumber", -1));
    }

    @Test
    public void illegalArgumentExceptionIsThrownWithSixGrade() {
        assertThrows(IllegalArgumentException.class, () -> new Attainment("courseCode", "studentNumber", 6));
    }

    @Test
    public void toStringProducesExpectedOutput() {
        Attainment attainment = new Attainment("courseCode", "studentNumber", 4);
        assertEquals("courseCode studentNumber 4", attainment.toString());
    }

    @Test
    public void compareToSameCourseCodes() {
        Attainment attainment1 = new Attainment("courseCode", "studentNumber1", 4);
        Attainment attainment2 = new Attainment("courseCode", "studentNumber2", 4);

        assertEquals(-1, attainment1.compareTo(attainment2));
        assertEquals(1, attainment2.compareTo(attainment1));
    }

    @Test
    public void compareToSameStudentNumbers() {
        Attainment attainment1 = new Attainment("courseCode1", "studentNumber", 4);
        Attainment attainment2 = new Attainment("courseCode2", "studentNumber", 4);

        assertEquals(-1, attainment1.compareTo(attainment2));
        assertEquals(1, attainment2.compareTo(attainment1));
    }

    @Test
    public void compareToIdenticalCourseCodesAndStudentNumbers() {
        Attainment attainment1 = new Attainment("courseCode", "studentNumber", 4);
        Attainment attainment2 = new Attainment("courseCode", "studentNumber", 3);

        assertEquals(0, attainment1.compareTo(attainment2));
    }

    static IntStream intProvider() {
        return IntStream.rangeClosed(0,5);
    }
}
