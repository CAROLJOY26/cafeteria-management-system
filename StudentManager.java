import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    private final List<Student> students;
    private final MongoCollection<Document> collection;

    public StudentManager() {
        students = new ArrayList<>();

        MongoDatabase database = MongoDBConnection.getDatabase();
        collection = database.getCollection("students");

        loadStudentsFromDatabase();
    }

    private void loadStudentsFromDatabase() {

        students.clear();

        for (Document document : collection.find()) {

            String studentId = document.getString("studentId");
            String name = document.getString("name");
            String email = document.getString("email");
            String password = document.getString("password");

            Student student = new Student(
                    studentId,
                    name,
                    email,
                    password
            );

            students.add(student);
        }
    }

    public void addStudent(Student student) {

        if (student == null) {
            return;
        }

        students.add(student);

        Document document = new Document()
                .append("studentId", student.getStudentId())
                .append("name", student.getName())
                .append("email", student.getEmail())
                .append("password", student.getPassword())
                .append("loyaltyPoints", student.getLoyaltyPoints());

        collection.insertOne(document);
    }

    public boolean registerStudent(
            String studentId,
            String name,
            String email,
            String password) {

        if (isBlank(studentId)
                || isBlank(name)
                || isBlank(email)
                || isBlank(password)) {

            return false;
        }

        if (findStudentById(studentId) != null
                || findStudentByEmail(email) != null) {

            return false;
        }

        Student student = new Student(
                studentId.trim(),
                name.trim(),
                email.trim(),
                password
        );

        addStudent(student);

        return true;
    }

    public Student findStudentById(String studentId) {

        if (studentId == null) {
            return null;
        }

        for (Student student : students) {

            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }

        return null;
    }

    public Student findStudentByEmail(String email) {

        if (email == null) {
            return null;
        }

        for (Student student : students) {

            if (student.getEmail().equalsIgnoreCase(email)) {
                return student;
            }
        }

        return null;
    }

    public boolean authenticate(String email, String password) {

        Student student = findStudentByEmail(email);

        return student != null
                && student.getPassword().equals(password);
    }

    public boolean authenticateById(String studentId, String password) {

        Student student = findStudentById(studentId);

        return student != null
                && student.getPassword().equals(password);
    }

    public boolean removeStudent(String studentId) {

        Student student = findStudentById(studentId);

        if (student == null) {
            return false;
        }

        students.remove(student);

        collection.deleteOne(
                new Document("studentId", studentId)
        );

        return true;
    }

    public List<Student> getAllStudents() {

        return new ArrayList<>(students);
    }

    public int getStudentCount() {

        return students.size();
    }

    private boolean isBlank(String value) {

        return value == null || value.trim().isEmpty();
    }
}