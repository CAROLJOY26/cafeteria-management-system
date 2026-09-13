import java.util.List;

public class LoyaltyManager {
    private int totalPointsAwarded;
    private int totalPointsRedeemed;

    public LoyaltyManager() {
        totalPointsAwarded = 0;
        totalPointsRedeemed = 0;
    }

    public int calculatePoints(List<OrderItem> items) {
        if (items == null) return 0;
        return items.stream().mapToInt(OrderItem::getPointsEarned).sum();
    }

    public void awardPoints(Student student, int points) {
        if (student == null || points <= 0) return;
        student.addLoyaltyPoints(points);
        totalPointsAwarded += points;
        System.out.println("Awarded " + points + " loyalty points!");
    }

    public boolean redeemPoints(Student student, int rewardChoice) {
        if (student == null) return false;

        int requiredPoints;
        if (rewardChoice == 1) requiredPoints = 100;
        else if (rewardChoice == 2) requiredPoints = 50;
        else return false;

        if (student.redeemPoints(requiredPoints)) {
            totalPointsRedeemed += requiredPoints;
            return true;
        }
        return false;
    }

    public int getTotalPointsAwarded() { return totalPointsAwarded; }
    public int getTotalPointsRedeemed() { return totalPointsRedeemed; }
}
