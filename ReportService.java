public class ReportService {
    private final OrderManager orderManager;
    private final LoyaltyManager loyaltyManager;

    public ReportService(OrderManager orderManager, LoyaltyManager loyaltyManager) {
        this.orderManager = orderManager;
        this.loyaltyManager = loyaltyManager;
    }

    public int getTotalOrders() { return orderManager.getTotalOrders(); }
    public double getTotalRevenue() { return orderManager.getTotalRevenue(); }
    public int getPointsAwarded() { return loyaltyManager.getTotalPointsAwarded(); }
    public int getPointsRedeemed() { return loyaltyManager.getTotalPointsRedeemed(); }

    public void displayReport() {
        System.out.println("\n=== Cafeteria Report ===");
        System.out.println("Total Orders: " + orderManager.getTotalOrders());
        System.out.printf("Total Revenue: EGP %.2f%n", orderManager.getTotalRevenue());
        System.out.println("Total Loyalty Points Awarded: " + loyaltyManager.getTotalPointsAwarded());
        System.out.println("Total Loyalty Points Redeemed: " + loyaltyManager.getTotalPointsRedeemed());
    }
}
