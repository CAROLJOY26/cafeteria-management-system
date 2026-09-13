import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderService {
    private final OrderManager orderManager;
    private final MenuManager menuManager;
    private final LoyaltyManager loyaltyManager;
    private final Scanner scanner;

    public OrderService(OrderManager orderManager, MenuManager menuManager, LoyaltyManager loyaltyManager) {
        this.orderManager = orderManager;
        this.menuManager = menuManager;
        this.loyaltyManager = loyaltyManager;
        this.scanner = new Scanner(System.in);
    }

    public StudentOrder placeOrder(Student student) {
        if (student == null) return null;

        List<OrderItem> items = new ArrayList<>();
        menuManager.displayMenu();

        while (true) {
            System.out.print("Enter item number to add (0 to finish): ");
            int itemNumber = getIntInput();

            if (itemNumber == 0) break;

            MenuItem menuItem = menuManager.getMenuItem(itemNumber - 1);
            if (menuItem == null) {
                System.out.println("Invalid item number!");
                continue;
            }

            System.out.print("Enter quantity: ");
            int quantity = getIntInput();
            if (quantity <= 0) {
                System.out.println("Quantity must be greater than 0.");
                continue;
            }

            items.add(new OrderItem(menuItem, quantity));
            System.out.println("Added " + quantity + " x " + menuItem.getName());
        }

        if (items.isEmpty()) return null;

        StudentOrder order = orderManager.createOrder(student.getStudentId(), items);
        int points = loyaltyManager.calculatePoints(items);
        loyaltyManager.awardPoints(student, points);
        return order;
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
}
