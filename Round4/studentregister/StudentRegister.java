
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Olli
 */
public class StudentRegister {
    
    private ArrayList<Course> courses;
    private ArrayList<Student> students;
    private ArrayList<Attainment> attainments;

    public StudentRegister() {
        this.courses = new ArrayList<Course>();
        this.students =  new ArrayList<Student>();
        this.attainments = new ArrayList<Attainment>();
    }
    
    public ArrayList<Student> getStudents() {
        return this.students;
    }
    
    public ArrayList<Course> getCourses() {
        return this.courses;
    }
    
    public void addStudent(Student student) {
        this.students.add(student);
    }
    
    public void addCourse(Course course) {
        this.courses.add(course);
    }
    
    public void addAttainment(Attainment att) {
        this.attainments.add(att);
    }
    
    public void printStudentAttainments(String studentNumber, String order) {
        System.out.format("We preted that this is sorted with oder: %s%n", order);
        ArrayList<Attainment> tempCopy = new ArrayList<>();
        tempCopy.addAll(this.attainments);
        sortAttainments(order);
        printStudentAttainments(studentNumber);
        this.attainments = tempCopy;
    }
    
    public void printStudentAttainments(String studentNumber) {
        int index = studentInRegister(studentNumber);
        if (index == -1) {
            System.out.format("Unknown student number: %s%n", studentNumber);
        }
        else {
            
            Student st = this.students.get(index);
            System.out.format("%s (%s):%n", st.getName(), st.getStudentNumber());
            
            for (Attainment a : this.attainments) {
                
                if (a.getStudentNumber().equals(studentNumber)) {
                    
                    String cName = fetchCourseName(a.getCourseCode());
                    
                    if (cName != null) {
                        System.out.format("  %s %s: %d%n",a.getCourseCode(), cName, a.getGrade());   
                    }
                    else {
                        System.out.format("  Unkown course code: %s%n", a.getCourseCode());
                    }
                }
            }
        }
    }
    
    private int studentInRegister(String studentNumber) {
        int index = 0;
        for (Student s : this.students) {
            if (s.getStudentNumber().equals(studentNumber)) {
                return index;
            }
            index++;
        }
        return -1;
    } 
    
    private String fetchCourseName(String courseCode) {
        for (Course c : this.courses) {
            if (c.getCode().equals(courseCode)) {
                return c.getName();
            }
        }
        return null;
    }
    
    private  void sortAttainments(String order) {
        if (order.equals("by name")){
            this.attainments.sort((a,b) -> (
                    fetchCourseName( a.getCourseCode() ).compareTo( fetchCourseName( b.getCourseCode() ) )
                    ));
        }
        else if (order.equals("by order")) {
            this.attainments.sort((a,b) -> (b.getCourseCode().compareTo(a.getCourseCode())));
        }
    }
    
}
