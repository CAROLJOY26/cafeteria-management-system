import java.util.Scanner;

public class AuthenticationService {
    private final StudentManager studentManager;
    private final StaffManager staffManager;
    private final Scanner scanner;

    public AuthenticationService(StudentManager studentManager, StaffManager staffManager) {
        this.studentManager = studentManager;
        this.staffManager = staffManager;
        this.scanner = new Scanner(System.in);
    }

    public Student studentLogin() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine().trim();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Student student = studentManager.findStudentById(studentId);
        if (student != null && student.getPassword().equals(password)) {
            System.out.println("Login successful! Welcome " + student.getName());
            return student;
        }

        System.out.println("Invalid student ID or password!");
        return null;
    }

    public Staff staffLogin() {
        System.out.print("Enter staff ID: ");
        String staffId = scanner.nextLine().trim();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Staff staff = staffManager.findStaffById(staffId);
        if (staff != null && staff.getPassword().equals(password)) {
            System.out.println("Login successful! Welcome " + staff.getName());
            return staff;
        }

        System.out.println("Invalid staff ID or password!");
        return null;
    }
}
