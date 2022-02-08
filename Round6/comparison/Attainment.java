
import java.util.Comparator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Olli
 */
public class Attainment  implements Comparable<Attainment>{
    
    private String courseCode;
    private String studentNumber;
    private int grade;
    public static final Comparator<Attainment> CODE_STUDENT_CMP = new Comparator<>(){
        @Override
        public int compare(Attainment a, Attainment b){
        int cmp = a.getCourseCode().compareTo(b.getCourseCode());
        if (cmp == 0){
            cmp = a.getStudentNumber().compareTo(b.getStudentNumber());
        }
        return cmp;
        }
    };
    public static final Comparator<Attainment> CODE_GRADE_CMP = new Comparator<>(){
        @Override
        public int compare(Attainment a, Attainment b){
        int cmp = a.getCourseCode().compareTo(b.getCourseCode());
        if (cmp == 0) {
            cmp = Integer.compare(b.getGrade(),a.getGrade());
        }
        return cmp;
        }
    };
    
    Attainment(String courseCode, String studentNumber, int grade){
        this.courseCode = courseCode;
        this.studentNumber = studentNumber;
        this.grade = grade;
    }
    
    public String getCourseCode(){
        return courseCode;
    }
    
    public String getStudentNumber(){
        return studentNumber;
    }
    
    public int getGrade(){
        return grade;
    }
    
    @Override
    public String toString(){
        return String.format("%s %s %d%n", courseCode, studentNumber, grade);
    }
    
    @Override
    public int compareTo(Attainment other){
        int cmp = this.getStudentNumber().compareTo(other.getStudentNumber());
        if (cmp == 0) {
            cmp = this.getCourseCode().compareTo(other.getCourseCode());
        }
        return cmp;
    }

}
