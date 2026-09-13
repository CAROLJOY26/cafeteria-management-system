public class MenuItem {
    private final String name;
    private final String description;
    private final double price;
    private final String category;
    private final int points;

    public MenuItem(String name, String description, double price, String category, int points) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.points = points;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public int getPoints() { return points; }

    @Override
    public String toString() {
        return name + " - " + description + " - EGP " + price +
                " (Earns " + points + " points) - (" + category + ")";
    }
}
