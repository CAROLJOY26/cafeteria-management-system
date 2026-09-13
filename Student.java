public class Student {
    private String studentId;
    private String name;
    private String email;
    private String password;
    private int loyaltyPoints;

    public Student(String studentId, String name, String email, String password) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.loyaltyPoints = 0;
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public int getLoyaltyPoints() { return loyaltyPoints; }

    public void addLoyaltyPoints(int points) {
        if (points > 0) loyaltyPoints += points;
    }

    public boolean redeemPoints(int points) {
        if (points > 0 && loyaltyPoints >= points) {
            loyaltyPoints -= points;
            return true;
        }
        return false;
    }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", loyaltyPoints=" + loyaltyPoints +
                '}';
    }
}
