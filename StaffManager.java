import java.util.ArrayList;
import java.util.List;

public class StaffManager {
    private final List<Staff> staffMembers;

    public StaffManager() {
        staffMembers = new ArrayList<>();
    }

    public void addStaff(Staff staff) {
        if (staff != null) staffMembers.add(staff);
    }

    public boolean registerStaff(String staffId, String name, String email, String password, String role) {
        if (isBlank(staffId) || isBlank(name) || isBlank(email) || isBlank(password) || isBlank(role)) return false;
        if (findStaffById(staffId) != null || findStaffByEmail(email) != null) return false;
        addStaff(new Staff(staffId.trim(), name.trim(), email.trim(), password, role.trim()));
        return true;
    }

    public Staff findStaffById(String staffId) {
        if (staffId == null) return null;
        for (Staff staff : staffMembers) {
            if (staff.getStaffId().equals(staffId)) return staff;
        }
        return null;
    }

    public Staff findStaffByEmail(String email) {
        if (email == null) return null;
        for (Staff staff : staffMembers) {
            if (staff.getEmail().equalsIgnoreCase(email)) return staff;
        }
        return null;
    }

    public boolean authenticate(String email, String password) {
        Staff staff = findStaffByEmail(email);
        return staff != null && staff.getPassword().equals(password);
    }

    public boolean authenticateById(String staffId, String password) {
        Staff staff = findStaffById(staffId);
        return staff != null && staff.getPassword().equals(password);
    }

    public boolean removeStaff(String staffId) {
        Staff staff = findStaffById(staffId);
        return staff != null && staffMembers.remove(staff);
    }

    public List<Staff> getAllStaff() { return new ArrayList<>(staffMembers); }
    public int getStaffCount() { return staffMembers.size(); }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
