package university;
import university.enums.*;
import university.services.UniversityService;

public class Main {
  public static void main(String[] args) {
    // 1. Примусово встановлюємо UTF-8 для виводу тексту в консоль
    System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));
    
    UniversityService service = new UniversityService();
    
    // 2. Вказуємо Сканнеру читати ввід теж у форматі UTF-8
    java.util.Scanner scanner = new java.util.Scanner(System.in, java.nio.charset.StandardCharsets.UTF_8);

        while (true) {
            System.out.println("\n СИСТЕМА УПРАВЛІННЯ УНІВЕРСИТЕТОМ");
            System.out.println("1. Студенти");
            System.out.println("2. Викладачі");
            System.out.println("3. Курси");
            System.out.println("4. Зарахування (Оцінки, Оплата)");
            System.out.println("5. Звіти / Пошук");
            System.out.println("0. Вихід");
            System.out.print("Оберіть опцію: ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        System.out.println("1. Додати | 2. Показати всіх | 3. Змінити статус | 4. Сортувати (Bubble Sort)");
                        String opt1 = scanner.nextLine();
                        if (opt1.equals("1")) {
                            System.out.print("Ім'я: "); String name = scanner.nextLine();
                            System.out.print("Email: "); String email = scanner.nextLine();
                            System.out.print("Рік навчання (1-6): "); int year = Integer.parseInt(scanner.nextLine());
                            service.addStudent(name, email, year);
                            System.out.println("Студента додано.");
                        } else if (opt1.equals("2")) {
                            service.showAllStudents();
                        } else if (opt1.equals("3")) {
                            System.out.print("ID студента: "); int id = Integer.parseInt(scanner.nextLine());
                            System.out.print("Новий статус (ACTIVE, ON_LEAVE, EXPELLED, GRADUATED): ");
                            StudentStatus status = StudentStatus.valueOf(scanner.nextLine().toUpperCase());
                            service.updateStudentStatus(id, status);
                            System.out.println("Статус оновлено.");
                        } else if (opt1.equals("4")) {
                            service.showStudentsSortedByName();
                        }
                        break;
                    case "2":
                        System.out.println("1. Додати | 2. Показати всіх");
                        String opt2 = scanner.nextLine();
                        if (opt2.equals("1")) {
                            System.out.print("Ім'я: "); String name = scanner.nextLine();
                            System.out.print("Email: "); String email = scanner.nextLine();
                            System.out.print("Посада (ASSISTANT, LECTURER, PROFESSOR): ");
                            TeacherPosition pos = TeacherPosition.valueOf(scanner.nextLine().toUpperCase());
                            service.addTeacher(name, email, pos);
                            System.out.println("Викладача додано.");
                        } else if (opt2.equals("2")) {
                            service.showAllTeachers();
                        }
                        break;
                    case "3":
                        System.out.println("1. Додати | 2. Показати всіх");
                        String opt3 = scanner.nextLine();
                        if (opt3.equals("1")) {
                            System.out.print("Назва курсу: "); String name = scanner.nextLine();
                            System.out.print("Кількість кредитів: "); int credits = Integer.parseInt(scanner.nextLine());
                            System.out.print("ID викладача: "); int tId = Integer.parseInt(scanner.nextLine());
                            service.addCourse(name, credits, tId);
                            System.out.println("Курс додано.");
                        } else if (opt3.equals("2")) {
                            service.showAllCourses();
                        }
                        break;
                    case "4":
                        System.out.println("1. Зарахувати студента | 2. Поставити оцінку | 3. Оплатити курс");
                        String opt4 = scanner.nextLine();
                        if (opt4.equals("1")) {
                            System.out.print("ID студента: "); int sId = Integer.parseInt(scanner.nextLine());
                            System.out.print("ID курсу: "); int cId = Integer.parseInt(scanner.nextLine());
                            System.out.print("Семестр (напр. Fall 2026): "); String sem = scanner.nextLine();
                            service.enrollStudent(sId, cId, sem);
                            System.out.println("Студента зараховано.");
                        } else if (opt4.equals("2")) {
                            System.out.print("ID студента: "); int sId = Integer.parseInt(scanner.nextLine());
                            System.out.print("ID курсу: "); int cId = Integer.parseInt(scanner.nextLine());
                            System.out.print("Оцінка (A, B, C, D, F, NA): ");
                            Grade grade = Grade.valueOf(scanner.nextLine().toUpperCase());
                            service.setGrade(sId, cId, grade);
                            System.out.println("Оцінку виставлено.");
                        } else if (opt4.equals("3")) {
                            System.out.print("ID студента: "); int sId = Integer.parseInt(scanner.nextLine());
                            System.out.print("ID курсу: "); int cId = Integer.parseInt(scanner.nextLine());
                            service.payForCourse(sId, cId);
                            System.out.println("Курс оплачено.");
                        }
                        break;
                    case "5":
                        System.out.println("1. Транскрипт студента (з GPA) | 2. Неоплачені курси");
                        String opt5 = scanner.nextLine();
                        if (opt5.equals("1")) {
                            System.out.print("ID студента: "); int sId = Integer.parseInt(scanner.nextLine());
                            service.showStudentTranscript(sId);
                        } else if (opt5.equals("2")) {
                            service.showUnpaidEnrollments();
                        }
                        break;
                    case "0":
                        System.out.println("Вихід із системи...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Невідома команда.");
                }
            } catch (IllegalArgumentException e) {
                // Перехоплення винятків та виведення зрозумілого повідомлення
                System.out.println("Помилка: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Системна помилка: перевірте правильність введених даних.");
            }
        }
    }
}