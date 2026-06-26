package university.services;
import university.entities.*;
import university.enums.*;
import university.util.GPAUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UniversityService {
    private List<Student> students = new ArrayList<>();
    private List<Teacher> teachers = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<Enrollment> enrollments = new ArrayList<>();
    private int studentIdCounter = 1;
    private int teacherIdCounter = 1;
    private int courseIdCounter = 1;

    // --- СТУДЕНТИ ---
    public void addStudent(String name, String email, int year) {
        students.add(new Student(studentIdCounter++, name, email, year));
    }

    public void showAllStudents() {
        if (students.isEmpty()) System.out.println("Студентів немає.");
        for (Student s : students) System.out.println(s);
    }

    public Student getStudentById(int id) {
        return students.stream().filter(s -> s.getId() == id).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Студента з таким ID не знайдено."));
    }

    public void updateStudentStatus(int id, StudentStatus newStatus) {
        getStudentById(id).setStatus(newStatus);
    }

    // Сортування Bubble Sort (за вимогою)
    public void showStudentsSortedByName() {
        Student[] arr = students.toArray(new Student[0]);
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j].getName().compareToIgnoreCase(arr[j + 1].getName()) > 0) {
                    Student temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        for (Student s : arr) System.out.println(s);
    }

    // --- ВИКЛАДАЧІ ---
    public void addTeacher(String name, String email, TeacherPosition position) {
        teachers.add(new Teacher(teacherIdCounter++, name, email, position));
    }

    public void showAllTeachers() {
        if (teachers.isEmpty()) System.out.println("Викладачів немає.");
        for (Teacher t : teachers) System.out.println(t);
    }

    public Teacher getTeacherById(int id) {
        return teachers.stream().filter(t -> t.getId() == id).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Викладача не знайдено."));
    }

    // --- КУРСИ ---
    public void addCourse(String name, int credits, int teacherId) {
        Teacher teacher = getTeacherById(teacherId);
        courses.add(new Course(courseIdCounter++, name, credits, teacher));
    }

    public void showAllCourses() {
        if (courses.isEmpty()) System.out.println("Курсів немає.");
        for (Course c : courses) System.out.println(c);
    }

    public Course getCourseById(int id) {
        return courses.stream().filter(c -> c.getId() == id).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Курс не знайдено."));
    }

    // --- ЗАРАХУВАННЯ ---
    public void enrollStudent(int studentId, int courseId, String semester) {
        Student s = getStudentById(studentId);
        Course c = getCourseById(courseId);
        enrollments.add(new Enrollment(s, c, semester));
    }

    public void setGrade(int studentId, int courseId, Grade grade) {
        for (Enrollment e : enrollments) {
            if (e.getStudent().getId() == studentId && e.getCourse().getId() == courseId) {
                e.setGrade(grade);
                return;
            }
        }
        throw new IllegalArgumentException("Зарахування не знайдено.");
    }

    public void payForCourse(int studentId, int courseId) {
        for (Enrollment e : enrollments) {
            if (e.getStudent().getId() == studentId && e.getCourse().getId() == courseId) {
                e.pay();
                return;
            }
        }
        throw new IllegalArgumentException("Зарахування не знайдено.");
    }

    public void showUnpaidEnrollments() {
        List<Enrollment> unpaid = enrollments.stream().filter(e -> !e.isPaid()).collect(Collectors.toList());
        if (unpaid.isEmpty()) System.out.println("Неоплачених курсів немає.");
        for (Enrollment e : unpaid) System.out.println(e);
    }

    public void showStudentTranscript(int studentId) {
        Student s = getStudentById(studentId);
        List<Enrollment> studentEnrollments = enrollments.stream()
                .filter(e -> e.getStudent().getId() == studentId)
                .collect(Collectors.toList());
        
        System.out.println("--- Транскрипт студента: " + s.getName() + " ---");
        for (Enrollment e : studentEnrollments) {
            System.out.println(e.getCourse().getName() + " | Оцінка: " + e.getGrade());
        }
        System.out.println("GPA: " + GPAUtils.calculateGPA(studentEnrollments));
    }
    
    public void initTestData() {
        addTeacher("Іван Петренко", "ivan@uni.edu", TeacherPosition.PROFESSOR);
        addStudent("Олексій", "olexiy@gmail.com", 2);
        addStudent("Марія", "maria@gmail.com", 1);
        addCourse("Java Programming", 5, 1);
        enrollStudent(1, 1, "Осінь 2026");
        System.out.println("Тестові дані завантажено.");
    }
}