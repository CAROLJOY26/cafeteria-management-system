import javax.swing.*;
import java.awt.*;

public class StudentLoginDialog extends JDialog {
    private final CafeteriaApp app;
    private final JTextField idField = UITheme.textField("Student ID");
    private final JPasswordField passwordField = UITheme.passwordField("Password");

    public StudentLoginDialog(Frame owner, CafeteriaApp app) {
        super(owner, "Student Portal", true);
        this.app = app;
        buildUI();
    }

    private void buildUI() {
        setSize(500, 500);
        setLocationRelativeTo(getOwner());
        setResizable(false);

        UITheme.GradientPanel root = new UITheme.GradientPanel(new Color(236,225,255), new Color(221,241,255));
        root.setLayout(new GridBagLayout());

        UITheme.RoundedPanel card = UITheme.card();
        card.setPreferredSize(new Dimension(390, 400));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel icon = new JLabel("🎓"); icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 42)); icon.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel title = UITheme.title("Student Login", 25); title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel sub = UITheme.subtitle("Welcome back! Sign in to order your favorite food."); sub.setAlignmentX(Component.CENTER_ALIGNMENT);

        idField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        UITheme.RoundedButton login = UITheme.button("Sign In", UITheme.PURPLE);
        UITheme.RoundedButton register = UITheme.button("Create Student Account", UITheme.PINK);
        UITheme.RoundedButton cancel = UITheme.button("Back", new Color(145,145,160));
        login.setMaximumSize(new Dimension(Integer.MAX_VALUE, 52));
        register.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        cancel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));

        card.add(icon); card.add(Box.createVerticalStrut(5)); card.add(title); card.add(Box.createVerticalStrut(5)); card.add(sub);
        card.add(Box.createVerticalStrut(24)); card.add(idField); card.add(Box.createVerticalStrut(12)); card.add(passwordField);
        card.add(Box.createVerticalStrut(18)); card.add(login); card.add(Box.createVerticalStrut(10)); card.add(register); card.add(Box.createVerticalStrut(8)); card.add(cancel);

        login.addActionListener(e -> login());
        register.addActionListener(e -> register());
        cancel.addActionListener(e -> dispose());
        passwordField.addActionListener(e -> login());
        root.add(card);
        setContentPane(root);
    }

    private void login() {
        String id = idField.getText().trim();
        String password = new String(passwordField.getPassword());
        Student student = app.studentManager.findStudentById(id);
        if (student != null && student.getPassword().equals(password)) {
            dispose();
            setOwnerVisible(false);
            new StudentDashboard((LoginFrame)getOwner(), app, student).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid student ID or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void register() {
        JTextField id = UITheme.textField("Student ID");
        JTextField name = UITheme.textField("Full Name");
        JTextField email = UITheme.textField("Email");
        JPasswordField pass = UITheme.passwordField("Password");
        Object[] fields = {"Student ID", id, "Full Name", name, "Email", email, "Password", pass};
        int result = JOptionPane.showConfirmDialog(this, fields, "Create Student Account", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if (app.studentManager.registerStudent(id.getText().trim(), name.getText().trim(), email.getText().trim(), new String(pass.getPassword()))) {
                JOptionPane.showMessageDialog(this, "Account created successfully! You can now sign in.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed. Check the fields or use a different ID/email.", "Registration Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void setOwnerVisible(boolean visible) { if (getOwner() != null) getOwner().setVisible(visible); }
}
