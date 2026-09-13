import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private final List<StudentOrder> orders;
    private int orderCounter;

    public OrderManager() {
        orders = new ArrayList<>();
        orderCounter = 1;
    }

    public StudentOrder createOrder(String studentId, List<OrderItem> items) {
        String orderId = "ORD" + orderCounter++;
        StudentOrder order = new StudentOrder(orderId, studentId, items);
        orders.add(order);
        return order;
    }

    public boolean updateOrderStatus(String orderId, String status) {
        for (StudentOrder order : orders) {
            if (order.getOrderId().equals(orderId)) {
                order.setStatus(status);
                return true;
            }
        }
        return false;
    }

    public List<StudentOrder> getAllOrders() { return new ArrayList<>(orders); }

    public void displayOrders() {
        System.out.println("\n=== Orders ===");
        if (orders.isEmpty()) System.out.println("No orders found.");
        else orders.forEach(System.out::println);
    }

    public int getTotalOrders() { return orders.size(); }

    public double getTotalRevenue() {
        return orders.stream().mapToDouble(StudentOrder::getTotalPrice).sum();
    }
}
