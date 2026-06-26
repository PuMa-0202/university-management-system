package university.entities;
import university.enums.StudentStatus;

public class Student extends Person {
    private StudentStatus status;
    private int year;

    public Student(int id, String name, String email, int year) {
        super(id, name, email);
        setYear(year);
        this.status = StudentStatus.ACTIVE;
    }

    public StudentStatus getStatus() { return status; }
    public void setStatus(StudentStatus status) { this.status = status; }
    public int getYear() { return year; }

    public void setYear(int year) {
        if (year < 1 || year > 6) {
            throw new IllegalArgumentException("Рік навчання повинен бути від 1 до 6.");
        }
        this.year = year;
    }

    @Override
    public String toString() {
        return "Студент [" + super.toString() + ", Статус: " + status + ", Рік: " + year + "]";
    }
}