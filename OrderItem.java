public class OrderItem {
    private final MenuItem menuItem;
    private final int quantity;

    public OrderItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public MenuItem getMenuItem() { return menuItem; }
    public int getQuantity() { return quantity; }
    public double getItemTotal() { return menuItem.getPrice() * quantity; }
    public int getPointsEarned() { return menuItem.getPoints() * quantity; }
}
