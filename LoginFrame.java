import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class LoginFrame extends JFrame {
    private final CafeteriaApp app;

    public LoginFrame(CafeteriaApp app) {
        this.app = app;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("University Cafeteria System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 620);
        setMinimumSize(new Dimension(820, 560));
        setLocationRelativeTo(null);

        BubbleBackground root = new BubbleBackground();
        root.setLayout(new GridBagLayout());

        UITheme.RoundedPanel card = new UITheme.RoundedPanel(34, new Color(255,255,255,242));
        card.setPreferredSize(new Dimension(720, 460));
        card.setBorder(BorderFactory.createEmptyBorder(35, 55, 35, 55));

        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JLabel icon = new JLabel("🍽");
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 46));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = UITheme.title("University Cafeteria", 30);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel title2 = UITheme.title("Management System", 30);
        title2.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel sub = UITheme.subtitle("Fresh food • Easy ordering • Happy students");
        sub.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(icon);
        content.add(Box.createVerticalStrut(6));
        content.add(title);
        content.add(title2);
        content.add(Box.createVerticalStrut(8));
        content.add(sub);
        content.add(Box.createVerticalStrut(30));

        UITheme.RoundedButton student = UITheme.button("👨‍🎓  Student Portal", UITheme.PURPLE);
        UITheme.RoundedButton staff = UITheme.button("👨‍💼  Staff Portal", UITheme.BLUE);
        UITheme.RoundedButton exit = UITheme.button("✕  Exit Application", new Color(125,125,142));
        student.setAlignmentX(Component.CENTER_ALIGNMENT);
        staff.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        student.setMaximumSize(new Dimension(420, 58));
        staff.setMaximumSize(new Dimension(420, 58));
        exit.setMaximumSize(new Dimension(420, 52));

        content.add(student);
        content.add(Box.createVerticalStrut(13));
        content.add(staff);
        content.add(Box.createVerticalStrut(13));
        content.add(exit);
        content.add(Box.createVerticalStrut(22));

        JLabel footer = UITheme.subtitle("Secure • Simple • Convenient");
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(footer);

        card.setLayout(new BorderLayout());
        card.add(content, BorderLayout.CENTER);

        student.addActionListener(e -> openStudentLogin());
        staff.addActionListener(e -> openStaffLogin());
        exit.addActionListener(e -> System.exit(0));

        root.add(card);
        setContentPane(root);
    }

    private void openStudentLogin() { new StudentLoginDialog(this, app).setVisible(true); }
    private void openStaffLogin() { new StaffLoginDialog(this, app).setVisible(true); }

    static class BubbleBackground extends JPanel {
        BubbleBackground() { setOpaque(false); }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setPaint(new GradientPaint(0,0,new Color(236,225,255),getWidth(),getHeight(),new Color(218,241,255)));
            g2.fillRect(0,0,getWidth(),getHeight());
            drawBubble(g2, -60, 40, 210, new Color(245,126,182,75));
            drawBubble(g2, getWidth()-180, 30, 240, new Color(116,91,255,70));
            drawBubble(g2, getWidth()-120, getHeight()-170, 250, new Color(93,202,169,70));
            drawBubble(g2, 80, getHeight()-130, 170, new Color(247,190,82,65));
            for (int i=0;i<14;i++) {
                int x=(i*83+40)%Math.max(1,getWidth());
                int y=(i*137+20)%Math.max(1,getHeight());
                drawBubble(g2,x,y,12+(i%4)*7,new Color(255,255,255,80));
            }
            g2.dispose();
            super.paintComponent(g);
        }
        private void drawBubble(Graphics2D g2,int x,int y,int size,Color color){
            g2.setColor(color); g2.fill(new Ellipse2D.Double(x,y,size,size));
        }
    }
}
