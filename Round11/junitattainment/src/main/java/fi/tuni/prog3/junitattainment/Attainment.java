package fi.tuni.prog3.junitattainment;

public class Attainment implements Comparable<Attainment> {

    private String courseCode;
    private String studentNumber;
    private int grade;

    Attainment(String courseCode, String studentNumber, int grade) throws IllegalArgumentException {
        if (courseCode == null || studentNumber == null || grade < 0 || grade > 5) {
            throw new IllegalArgumentException("One or more invalid parameter.");
        }
        this.courseCode = courseCode;
        this.studentNumber = studentNumber;
        this.grade = grade;
    }

    public int compareTo(Attainment other) {
        int sNumberComparator = this.studentNumber.compareTo(other.studentNumber);
        if (sNumberComparator != 0) {
            return sNumberComparator;
        } else {
            return this.courseCode.compareTo(other.courseCode);
        }
    }

    public String toString() {
        return String.format("%s %s %d",this.courseCode,this.studentNumber, this.grade);
    }

    public String getStudentNumber() {
        return this.studentNumber;
    }

    public String getCourseCode() {
        return this.courseCode;
    }

    public int getGrade() {
        return this.grade;
    }
}
