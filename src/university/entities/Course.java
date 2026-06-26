package university.entities;

public class Course {
    private int id;
    private String name;
    private int credits;
    private Teacher teacher;

    public Course(int id, String name, int credits, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.teacher = teacher;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getCredits() { return credits; }
    public Teacher getTeacher() { return teacher; }

    @Override
    public String toString() {
        return "Курс [ID: " + id + ", Назва: " + name + ", Кредити: " + credits + ", Викладач: " + teacher.getName() + "]";
    }
}