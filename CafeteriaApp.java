import java.util.Scanner;

public class CafeteriaApp {
    public StudentManager studentManager;
    public StaffManager staffManager;
    public MenuManager menuManager;
    public OrderManager orderManager;
    public LoyaltyManager loyaltyManager;
    public AuthenticationService authService;
    public OrderService orderService;
    public ReportService reportService;
    private final Scanner scanner;

    public CafeteriaApp() {
        studentManager = new StudentManager();
        staffManager = new StaffManager();
        menuManager = new MenuManager();
        orderManager = new OrderManager();
        loyaltyManager = new LoyaltyManager();
        authService = new AuthenticationService(studentManager, staffManager);
        orderService = new OrderService(orderManager, menuManager, loyaltyManager);
        reportService = new ReportService(orderManager, loyaltyManager);
        scanner = new Scanner(System.in);
        initializeData();
    }

    private void initializeData() {
        menuManager.addMenuItem(new MenuItem("Cheeseburger", "Beef patty with cheese, lettuce, and tomato", 45.0, "Main Course", 70));
        menuManager.addMenuItem(new MenuItem("Chicken Sandwich", "Grilled chicken with mayo and veggies", 40.0, "Main Course", 65));
        menuManager.addMenuItem(new MenuItem("Margherita Pizza", "Classic cheese and tomato pizza", 60.0, "Main Course", 85));
        menuManager.addMenuItem(new MenuItem("French Fries", "Crispy golden fries", 15.0, "Side", 50));
        menuManager.addMenuItem(new MenuItem("Onion Rings", "Breaded and fried onion rings", 18.0, "Side", 55));
        menuManager.addMenuItem(new MenuItem("Greek Salad", "Fresh vegetables with feta cheese", 25.0, "Salad", 60));
        menuManager.addMenuItem(new MenuItem("Caesar Salad", "Romaine lettuce with Caesar dressing", 28.0, "Salad", 65));
        menuManager.addMenuItem(new MenuItem("Cola", "Carbonated soft drink", 12.0, "Drink", 50));
        menuManager.addMenuItem(new MenuItem("Orange Juice", "Freshly squeezed orange juice", 15.0, "Drink", 55));
        menuManager.addMenuItem(new MenuItem("Coffee", "Hot brewed coffee", 10.0, "Drink", 50));
        menuManager.addMenuItem(new MenuItem("Chocolate Cake", "Rich chocolate dessert", 20.0, "Dessert", 60));
        menuManager.addMenuItem(new MenuItem("Apple Pie", "Warm apple pie with cinnamon", 18.0, "Dessert", 55));

        staffManager.registerStaff("admin", "Admin User", "admin@university.edu", "admin123", "Administrator");
    }

    public void run() {
        while (true) {
            System.out.println("\n=== University Cafeteria System ===");
            System.out.println("1. Student");
            System.out.println("2. Staff");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            switch (getIntInput()) {
                case 1 -> studentMenu();
                case 2 -> staffAccessMenu();
                case 3 -> {
                    System.out.println("Thank you for using the system!");
                    return;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private void studentMenu() {
        while (true) {
            System.out.println("\n=== Student Menu ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose option: ");

            switch (getIntInput()) {
                case 1 -> registerStudent();
                case 2 -> {
                    Student student = authService.studentLogin();
                    if (student != null) studentOperations(student);
                }
                case 3 -> { return; }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private void staffAccessMenu() {
        while (true) {
            System.out.println("\n=== Staff Access ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose option: ");

            switch (getIntInput()) {
                case 1 -> registerStaff();
                case 2 -> {
                    Staff staff = authService.staffLogin();
                    if (staff != null) staffOperations(staff);
                }
                case 3 -> { return; }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private void registerStudent() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine().trim();
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (studentManager.registerStudent(studentId, name, email, password))
            System.out.println("Registration successful!");
        else
            System.out.println("Registration failed. Student ID or email may already exist, or a field is empty.");
    }

    private void registerStaff() {
        System.out.print("Enter staff ID: ");
        String staffId = scanner.nextLine().trim();
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role: ");
        String role = scanner.nextLine().trim();

        if (staffManager.registerStaff(staffId, name, email, password, role))
            System.out.println("Staff registration successful!");
        else
            System.out.println("Registration failed. Staff ID or email may already exist, or a field is empty.");
    }

    private void studentOperations(Student student) {
        while (true) {
            System.out.println("\n=== Student Operations ===");
            System.out.println("1. View Menu");
            System.out.println("2. Place Order");
            System.out.println("3. View Loyalty Points");
            System.out.println("4. Redeem Points");
            System.out.println("5. Logout");
            System.out.print("Choose option: ");

            switch (getIntInput()) {
                case 1 -> menuManager.displayMenu();
                case 2 -> placeOrders(student);
                case 3 -> System.out.println("Your loyalty points: " + student.getLoyaltyPoints());
                case 4 -> redeemPoints(student);
                case 5 -> { return; }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private void placeOrders(Student student) {
        boolean placeAnotherOrder = true;
        while (placeAnotherOrder) {
            StudentOrder order = orderService.placeOrder(student);
            if (order != null) {
                System.out.println("Order placed successfully! Order ID: " + order.getOrderId());
                System.out.printf("Total: EGP %.2f%n", order.getTotalPrice());
            } else {
                System.out.println("No items selected.");
            }
            System.out.print("Would you like to place another order? (yes/no): ");
            placeAnotherOrder = scanner.nextLine().trim().equalsIgnoreCase("yes");
        }
    }

    private void redeemPoints(Student student) {
        System.out.println("Your points: " + student.getLoyaltyPoints());
        System.out.println("1. Free Coffee (100 points)");
        System.out.println("2. EGP 10 discount (50 points)");
        System.out.print("Choose reward: ");

        if (loyaltyManager.redeemPoints(student, getIntInput()))
            System.out.println("Reward redeemed successfully!");
        else
            System.out.println("Not enough points or invalid choice!");
    }

    private void staffOperations(Staff staff) {
        while (true) {
            System.out.println("\n=== Staff Menu ===");
            System.out.println("1. View Orders");
            System.out.println("2. Update Order Status");
            System.out.println("3. Add Menu Item");
            System.out.println("4. View Reports");
            System.out.println("5. Logout");
            System.out.print("Choose option: ");

            switch (getIntInput()) {
                case 1 -> orderManager.displayOrders();
                case 2 -> updateOrderStatus();
                case 3 -> addMenuItem();
                case 4 -> reportService.displayReport();
                case 5 -> { return; }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private void updateOrderStatus() {
        orderManager.displayOrders();
        System.out.print("Enter order ID to update: ");
        String orderId = scanner.nextLine().trim();
        System.out.println("1. Preparing");
        System.out.println("2. Ready for Pickup");
        System.out.println("3. Completed");
        System.out.print("Choose new status: ");

        int status = getIntInput();
        String statusText = switch (status) {
            case 1 -> "Preparing";
            case 2 -> "Ready for Pickup";
            case 3 -> "Completed";
            default -> null;
        };

        if (statusText == null) {
            System.out.println("Invalid status!");
            return;
        }

        if (orderManager.updateOrderStatus(orderId, statusText))
            System.out.println("Order status updated!");
        else
            System.out.println("Order not found!");
    }

    private void addMenuItem() {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = getDoubleInput();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter points (50-100): ");
        int points = getIntInput();

        if (price <= 0 || points < 50 || points > 100) {
            System.out.println("Invalid price or points. Item was not added.");
            return;
        }

        menuManager.addMenuItem(new MenuItem(name, description, price, category, points));
        System.out.println("Menu item added!");
    }

    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    private double getDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}
