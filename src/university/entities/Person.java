package university.entities;

public abstract class Person {
    private int id;
    private String name;
    private String email;

    public Person(int id, String name, String email) {
        this.id = id;
        this.name = name;
        setEmail(email);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Неправильний формат email: " + email);
        }
        this.email = email;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Ім'я: " + name + ", Email: " + email;
    }
}