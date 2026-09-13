import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDashboard extends JFrame {
    private final LoginFrame loginFrame;
    private final CafeteriaApp app;
    private final Student student;
    private final JPanel content = new JPanel(new BorderLayout());
    private final JLabel pointsLabel = new JLabel();
    private final JLabel welcomeLabel = new JLabel();

    public StudentDashboard(LoginFrame loginFrame, CafeteriaApp app, Student student) {
        super("Student Dashboard • University Cafeteria");
        this.loginFrame = loginFrame; this.app = app; this.student = student;
        buildUI(); showHome();
    }

    private void buildUI() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); setSize(1120,700); setMinimumSize(new Dimension(950,620)); setLocationRelativeTo(null);
        JPanel root = new JPanel(new BorderLayout()); root.setBackground(UITheme.BG);
        root.add(sidebar(), BorderLayout.WEST); root.add(content, BorderLayout.CENTER); setContentPane(root);
        addWindowListener(new java.awt.event.WindowAdapter(){@Override public void windowClosing(java.awt.event.WindowEvent e){logout();}});
    }

    private JPanel sidebar() {
        JPanel side = new JPanel(); side.setPreferredSize(new Dimension(245,0)); side.setBackground(new Color(67,55,126)); side.setBorder(new EmptyBorder(28,18,24,18));
        side.setLayout(new BoxLayout(side,BoxLayout.Y_AXIS));
        JLabel logo = new JLabel("🍽  Cafeteria"); logo.setForeground(Color.WHITE); logo.setFont(new Font("SansSerif",Font.BOLD,24)); logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel small = new JLabel("STUDENT PORTAL"); small.setForeground(new Color(214,207,255)); small.setFont(new Font("SansSerif",Font.BOLD,11)); small.setAlignmentX(Component.CENTER_ALIGNMENT);
        side.add(logo); side.add(Box.createVerticalStrut(5)); side.add(small); side.add(Box.createVerticalStrut(35));
        addNav(side,"⌂  Home",UITheme.PURPLE,e->showHome()); addNav(side,"🍔  Browse Menu",UITheme.PINK,e->showMenu()); addNav(side,"🧾  My Orders",UITheme.BLUE,e->showOrders()); addNav(side,"⭐  Loyalty",UITheme.YELLOW,e->showLoyalty());
        side.add(Box.createVerticalGlue());
        UITheme.RoundedButton logout=UITheme.button("↪  Logout",new Color(235,91,91)); logout.setAlignmentX(Component.CENTER_ALIGNMENT); logout.setMaximumSize(new Dimension(205,48)); logout.addActionListener(e->logout()); side.add(logout);
        return side;
    }
    private void addNav(JPanel side,String text,Color color,java.awt.event.ActionListener action){UITheme.RoundedButton b=UITheme.button(text,color); b.setAlignmentX(Component.CENTER_ALIGNMENT); b.setMaximumSize(new Dimension(205,48)); b.addActionListener(action); side.add(b); side.add(Box.createVerticalStrut(12));}

    private void prepare(String title,String subtitle){
        content.removeAll(); JPanel header=new JPanel(new BorderLayout()); header.setOpaque(false); header.setBorder(new EmptyBorder(28,34,18,34));
        JPanel texts=new JPanel(); texts.setOpaque(false); texts.setLayout(new BoxLayout(texts,BoxLayout.Y_AXIS)); JLabel t=UITheme.title(title,27); JLabel s=UITheme.subtitle(subtitle); texts.add(t); texts.add(Box.createVerticalStrut(5)); texts.add(s); header.add(texts,BorderLayout.WEST); content.add(header,BorderLayout.NORTH);
        content.revalidate(); content.repaint();
    }

    private void showHome(){
        prepare("Hello, "+student.getName()+"! 👋","What would you like to have today?");
        JPanel body=new JPanel(new GridBagLayout()); body.setOpaque(false); body.setBorder(new EmptyBorder(0,34,30,34));
        GridBagConstraints g=new GridBagConstraints(); g.insets=new Insets(10,10,10,10); g.fill=GridBagConstraints.BOTH; g.weightx=1; g.weighty=1;
        g.gridx=0;g.gridy=0; body.add(statCard("⭐","Loyalty Points",String.valueOf(student.getLoyaltyPoints()),UITheme.YELLOW),g);
        g.gridx=1; body.add(statCard("🧾","My Orders",String.valueOf(myOrders().size()),UITheme.BLUE),g);
        g.gridx=2; body.add(statCard("🍴","Menu Items",String.valueOf(app.menuManager.getAllMenuItems().size()),UITheme.PINK),g);
        g.gridx=0;g.gridy=1;g.gridwidth=3;g.weighty=2; UITheme.RoundedPanel quick=UITheme.card(); quick.setLayout(new BorderLayout());
        JPanel q=new JPanel();q.setOpaque(false);q.setLayout(new BoxLayout(q,BoxLayout.Y_AXIS)); JLabel qt=UITheme.title("Quick actions",20); q.add(qt);q.add(Box.createVerticalStrut(15));
        JPanel buttons=new JPanel(new FlowLayout(FlowLayout.LEFT,12,0));buttons.setOpaque(false); UITheme.RoundedButton menu=UITheme.button("🍔 Browse Menu",UITheme.PURPLE);UITheme.RoundedButton orders=UITheme.button("🧾 View Orders",UITheme.BLUE);UITheme.RoundedButton loyalty=UITheme.button("⭐ My Rewards",UITheme.YELLOW);buttons.add(menu);buttons.add(orders);buttons.add(loyalty);q.add(buttons); quick.add(q,BorderLayout.CENTER);body.add(quick,g);
        menu.addActionListener(e->showMenu());orders.addActionListener(e->showOrders());loyalty.addActionListener(e->showLoyalty()); content.add(body,BorderLayout.CENTER); content.revalidate();
    }

    private JPanel statCard(String icon,String label,String value,Color color){UITheme.RoundedPanel p=new UITheme.RoundedPanel(24,new Color(255,255,255));p.setLayout(new BorderLayout());p.setBorder(new EmptyBorder(18,20,18,20));JLabel i=new JLabel(icon);i.setFont(new Font("Segoe UI Emoji",Font.PLAIN,32));JLabel l=UITheme.subtitle(label);JLabel v=UITheme.title(value,25);v.setForeground(color);JPanel tx=new JPanel();tx.setOpaque(false);tx.setLayout(new BoxLayout(tx,BoxLayout.Y_AXIS));tx.add(l);tx.add(Box.createVerticalStrut(4));tx.add(v);p.add(i,BorderLayout.WEST);p.add(tx,BorderLayout.CENTER);return p;}

    private void showMenu(){
        prepare("Cafeteria Menu 🍔","Choose items, set quantities, and place your order.");
        JPanel wrapper=new JPanel(new BorderLayout(15,15)); wrapper.setOpaque(false); wrapper.setBorder(new EmptyBorder(0,34,25,34));
        JPanel list=new JPanel(); list.setOpaque(false); list.setLayout(new BoxLayout(list,BoxLayout.Y_AXIS));
        List<JSpinner> spinners=new ArrayList<>(); List<MenuItem> items=app.menuManager.getAllMenuItems();
        for(MenuItem item:items){UITheme.RoundedPanel card=UITheme.card();card.setMaximumSize(new Dimension(Integer.MAX_VALUE,95));card.setLayout(new BorderLayout(15,5));
            JPanel info=new JPanel();info.setOpaque(false);info.setLayout(new BoxLayout(info,BoxLayout.Y_AXIS));JLabel name=UITheme.title(item.getName(),17);JLabel desc=UITheme.subtitle(item.getDescription()+"  •  "+item.getCategory());JLabel price=new JLabel(String.format("EGP %.2f  •  +%d points",item.getPrice(),item.getPoints()));price.setForeground(UITheme.PURPLE_DARK);price.setFont(new Font("SansSerif",Font.BOLD,13));info.add(name);info.add(Box.createVerticalStrut(3));info.add(desc);info.add(Box.createVerticalStrut(4));info.add(price);
            JSpinner qty=new JSpinner(new SpinnerNumberModel(0,0,20,1));qty.setPreferredSize(new Dimension(70,42));qty.setFont(new Font("SansSerif",Font.BOLD,15));spinners.add(qty);card.add(info,BorderLayout.CENTER);card.add(qty,BorderLayout.EAST);list.add(card);list.add(Box.createVerticalStrut(9));}
        JScrollPane scroll=new JScrollPane(list);scroll.setBorder(null);scroll.getVerticalScrollBar().setUnitIncrement(16);wrapper.add(scroll,BorderLayout.CENTER);
        UITheme.RoundedButton place=UITheme.button("🛒 Place Order",UITheme.PURPLE);place.addActionListener(e->placeOrder(items,spinners)); wrapper.add(place,BorderLayout.SOUTH); content.add(wrapper,BorderLayout.CENTER);content.revalidate();
    }

    private void placeOrder(List<MenuItem> items,List<JSpinner> spinners){List<OrderItem> selected=new ArrayList<>();for(int i=0;i<items.size();i++){int q=(Integer)spinners.get(i).getValue();if(q>0)selected.add(new OrderItem(items.get(i),q));}if(selected.isEmpty()){JOptionPane.showMessageDialog(this,"Please choose at least one item.","No Items",JOptionPane.WARNING_MESSAGE);return;}StudentOrder order=app.orderManager.createOrder(student.getStudentId(),selected);int points=app.loyaltyManager.calculatePoints(selected);app.loyaltyManager.awardPoints(student,points);JOptionPane.showMessageDialog(this,"Order "+order.getOrderId()+" placed!\nTotal: EGP "+String.format("%.2f",order.getTotalPrice())+"\nPoints earned: "+points,"Order Confirmed 🎉",JOptionPane.INFORMATION_MESSAGE);showOrders();}

    private List<StudentOrder> myOrders(){List<StudentOrder> result=new ArrayList<>();for(StudentOrder o:app.orderManager.getAllOrders())if(o.getStudentId().equals(student.getStudentId()))result.add(o);return result;}

    private void showOrders(){prepare("My Orders 🧾","Track your cafeteria orders and their status.");JPanel list=new JPanel();list.setOpaque(false);list.setLayout(new BoxLayout(list,BoxLayout.Y_AXIS));for(StudentOrder o:myOrders()){UITheme.RoundedPanel card=UITheme.card();card.setMaximumSize(new Dimension(Integer.MAX_VALUE,115));card.setLayout(new BorderLayout());JPanel info=new JPanel();info.setOpaque(false);info.setLayout(new BoxLayout(info,BoxLayout.Y_AXIS));JLabel id=UITheme.title(o.getOrderId(),18);JLabel status=new JLabel("●  "+o.getStatus());status.setFont(new Font("SansSerif",Font.BOLD,13));status.setForeground(statusColor(o.getStatus()));JLabel detail=UITheme.subtitle(itemsText(o));info.add(id);info.add(Box.createVerticalStrut(5));info.add(status);info.add(Box.createVerticalStrut(4));info.add(detail);JLabel total=new JLabel(String.format("EGP %.2f",o.getTotalPrice()));total.setFont(new Font("SansSerif",Font.BOLD,19));total.setForeground(UITheme.PURPLE_DARK);card.add(info,BorderLayout.CENTER);card.add(total,BorderLayout.EAST);list.add(card);list.add(Box.createVerticalStrut(10));}if(myOrders().isEmpty()){UITheme.RoundedPanel empty=UITheme.card();empty.add(new JLabel("No orders yet. Visit the menu to place your first order! 🍔"));list.add(empty);}JScrollPane scroll=new JScrollPane(list);scroll.setBorder(null);scroll.getVerticalScrollBar().setUnitIncrement(16);JPanel wrapper=new JPanel(new BorderLayout());wrapper.setOpaque(false);wrapper.setBorder(new EmptyBorder(0,34,25,34));wrapper.add(scroll);content.add(wrapper,BorderLayout.CENTER);content.revalidate();}
    private String itemsText(StudentOrder o){StringBuilder s=new StringBuilder();for(OrderItem i:o.getItems()){if(s.length()>0)s.append("  •  ");s.append(i.getMenuItem().getName()).append(" x").append(i.getQuantity());}return s.toString();}
    private Color statusColor(String status){return switch(status){case "Completed"->UITheme.MINT;case "Ready for Pickup"->UITheme.BLUE;case "Preparing"->UITheme.YELLOW;default->UITheme.PURPLE;};}

    private void showLoyalty(){prepare("Loyalty Rewards ⭐","Earn points with every order and redeem rewards.");JPanel body=new JPanel(new GridBagLayout());body.setOpaque(false);body.setBorder(new EmptyBorder(0,34,30,34));GridBagConstraints g=new GridBagConstraints();g.insets=new Insets(12,12,12,12);g.fill=GridBagConstraints.BOTH;g.weightx=1;g.weighty=1;
        g.gridx=0;g.gridy=0;g.gridwidth=2;UITheme.RoundedPanel hero=new UITheme.RoundedPanel(28,new Color(255,249,226));hero.setLayout(new BorderLayout());hero.setBorder(new EmptyBorder(25,28,25,28));JLabel big=UITheme.title("⭐  "+student.getLoyaltyPoints()+" points",32);big.setForeground(new Color(185,132,25));hero.add(big,BorderLayout.WEST);JLabel hint=UITheme.subtitle("Keep ordering to unlock more rewards!");hero.add(hint,BorderLayout.SOUTH);body.add(hero,g);
        g.gridwidth=1;g.gridy=1;g.gridx=0;body.add(rewardCard("☕","Free Coffee","100 points",100,1),g);g.gridx=1;body.add(rewardCard("💰","EGP 10 Discount","50 points",50,2),g);content.add(body,BorderLayout.CENTER);content.revalidate();}
    private JPanel rewardCard(String icon,String name,String cost,int required,int choice){UITheme.RoundedPanel p=UITheme.card();p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));JLabel i=new JLabel(icon);i.setFont(new Font("Segoe UI Emoji",Font.PLAIN,36));i.setAlignmentX(Component.CENTER_ALIGNMENT);JLabel n=UITheme.title(name,19);n.setAlignmentX(Component.CENTER_ALIGNMENT);JLabel c=UITheme.subtitle(cost);c.setAlignmentX(Component.CENTER_ALIGNMENT);UITheme.RoundedButton b=UITheme.button("Redeem",UITheme.PURPLE);b.setAlignmentX(Component.CENTER_ALIGNMENT);b.addActionListener(e->{if(app.loyaltyManager.redeemPoints(student,choice)){JOptionPane.showMessageDialog(this,"Reward redeemed successfully! 🎉","Reward",JOptionPane.INFORMATION_MESSAGE);showLoyalty();}else JOptionPane.showMessageDialog(this,"You need at least "+required+" points.","Not Enough Points",JOptionPane.WARNING_MESSAGE);});p.add(i);p.add(Box.createVerticalStrut(8));p.add(n);p.add(Box.createVerticalStrut(5));p.add(c);p.add(Box.createVerticalStrut(18));p.add(b);return p;}

    private void logout(){dispose();loginFrame.setVisible(true);}
}
