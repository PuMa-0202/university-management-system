package university.util;
import university.entities.Enrollment;
import java.util.List;

public class GPAUtils {
    public static double calculateGPA(List<Enrollment> enrollments) {
        if (enrollments.isEmpty()) return 0.0;
        
        double totalPoints = 0;
        int totalCredits = 0;
        
        for (Enrollment e : enrollments) {
            if (e.getGrade() != university.enums.Grade.NA) {
                totalPoints += e.getGrade().getPoint() * e.getCourse().getCredits();
                totalCredits += e.getCourse().getCredits();
            }
        }
        return totalCredits == 0 ? 0.0 : totalPoints / totalCredits;
    }
}