import java.util.ArrayList;
import java.util.List;

public class StudentOrder implements Orderable {
    private final String orderId;
    private final String studentId;
    private final List<OrderItem> items;
    private String status;

    public StudentOrder(String orderId, String studentId, List<OrderItem> items) {
        this.orderId = orderId;
        this.studentId = studentId;
        this.items = new ArrayList<>(items);
        this.status = "Pending";
    }

    @Override
    public String getOrderId() { return orderId; }
    public String getStudentId() { return studentId; }
    public List<OrderItem> getItems() { return new ArrayList<>(items); }

    @Override
    public double getTotalPrice() {
        return items.stream().mapToDouble(OrderItem::getItemTotal).sum();
    }

    @Override
    public String getStatus() { return status; }

    @Override
    public void setStatus(String status) {
        if (status != null && !status.trim().isEmpty()) this.status = status.trim();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Order ID: ").append(orderId)
              .append(" | Student ID: ").append(studentId)
              .append(" | Status: ").append(status)
              .append(" | Total: EGP ").append(String.format("%.2f", getTotalPrice()))
              .append("\n");
        for (OrderItem item : items) {
            result.append("   - ").append(item.getMenuItem().getName())
                  .append(" x ").append(item.getQuantity())
                  .append(" = EGP ").append(String.format("%.2f", item.getItemTotal()))
                  .append("\n");
        }
        return result.toString().trim();
    }
}
