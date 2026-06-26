package university.entities;
import university.enums.Grade;
import university.interfaces.Payable;

public class Enrollment implements Payable {
    private Student student;
    private Course course;
    private String semester;
    private Grade grade;
    private boolean isPaid;

    public Enrollment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
        this.grade = Grade.NA;
        this.isPaid = false;
    }

    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public String getSemester() { return semester; }
    public Grade getGrade() { return grade; }
    public void setGrade(Grade grade) { this.grade = grade; }

    @Override
    public void pay() { this.isPaid = true; }

    @Override
    public boolean isPaid() { return isPaid; }

    @Override
    public String toString() {
        return "Зарахування [Студент: " + student.getName() + ", Курс: " + course.getName() + 
               ", Семестр: " + semester + ", Оцінка: " + grade + ", Оплачено: " + isPaid + "]";
    }
}