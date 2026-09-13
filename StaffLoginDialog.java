import javax.swing.*;
import java.awt.*;

public class StaffLoginDialog extends JDialog {
    private final CafeteriaApp app;
    private final JTextField idField = UITheme.textField("Staff ID");
    private final JPasswordField passwordField = UITheme.passwordField("Password");

    public StaffLoginDialog(Frame owner, CafeteriaApp app) {
        super(owner, "Staff Portal", true);
        this.app = app;
        buildUI();
    }

    private void buildUI() {
        setSize(500, 500);
        setLocationRelativeTo(getOwner());
        setResizable(false);
        UITheme.GradientPanel root = new UITheme.GradientPanel(new Color(220,238,255), new Color(226,250,243));
        root.setLayout(new GridBagLayout());
        UITheme.RoundedPanel card = UITheme.card();
        card.setPreferredSize(new Dimension(390, 400));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel icon = new JLabel("👨‍💼"); icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 42)); icon.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel title = UITheme.title("Staff Login", 25); title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel sub = UITheme.subtitle("Manage orders, menu items, and cafeteria reports."); sub.setAlignmentX(Component.CENTER_ALIGNMENT);
        idField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        UITheme.RoundedButton login = UITheme.button("Sign In", UITheme.BLUE);
        UITheme.RoundedButton register = UITheme.button("Create Staff Account", UITheme.MINT);
        UITheme.RoundedButton cancel = UITheme.button("Back", new Color(145,145,160));
        login.setMaximumSize(new Dimension(Integer.MAX_VALUE, 52)); register.setMaximumSize(new Dimension(Integer.MAX_VALUE,48)); cancel.setMaximumSize(new Dimension(Integer.MAX_VALUE,45));

        card.add(icon); card.add(Box.createVerticalStrut(5)); card.add(title); card.add(Box.createVerticalStrut(5)); card.add(sub);
        card.add(Box.createVerticalStrut(24)); card.add(idField); card.add(Box.createVerticalStrut(12)); card.add(passwordField);
        card.add(Box.createVerticalStrut(18)); card.add(login); card.add(Box.createVerticalStrut(10)); card.add(register); card.add(Box.createVerticalStrut(8)); card.add(cancel);

        login.addActionListener(e -> login()); register.addActionListener(e -> register()); cancel.addActionListener(e -> dispose()); passwordField.addActionListener(e -> login());
        root.add(card); setContentPane(root);
    }

    private void login() {
        String id = idField.getText().trim(); String password = new String(passwordField.getPassword());
        Staff staff = app.staffManager.findStaffById(id);
        if (staff != null && staff.getPassword().equals(password)) {
            dispose(); setOwnerVisible(false); new StaffDashboard((LoginFrame)getOwner(), app, staff).setVisible(true);
        } else JOptionPane.showMessageDialog(this, "Invalid staff ID or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
    }

    private void register() {
        JTextField id = UITheme.textField("Staff ID"); JTextField name = UITheme.textField("Full Name"); JTextField email = UITheme.textField("Email");
        JPasswordField pass = UITheme.passwordField("Password"); JTextField role = UITheme.textField("Role");
        Object[] fields = {"Staff ID",id,"Full Name",name,"Email",email,"Password",pass,"Role",role};
        int result = JOptionPane.showConfirmDialog(this, fields, "Create Staff Account", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if (app.staffManager.registerStaff(id.getText().trim(),name.getText().trim(),email.getText().trim(),new String(pass.getPassword()),role.getText().trim()))
                JOptionPane.showMessageDialog(this,"Staff account created successfully.","Success",JOptionPane.INFORMATION_MESSAGE);
            else JOptionPane.showMessageDialog(this,"Registration failed. Check the fields or use a different ID/email.","Registration Failed",JOptionPane.ERROR_MESSAGE);
        }
    }
    private void setOwnerVisible(boolean visible) { if (getOwner()!=null) getOwner().setVisible(visible); }
}
