import javax.swing.SwingUtilities;

public class CafeteriaSystemGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CafeteriaApp app = new CafeteriaApp();
            LoginFrame loginFrame = new LoginFrame(app);
            loginFrame.setVisible(true);
        });
    }
}
