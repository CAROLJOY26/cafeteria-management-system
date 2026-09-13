import java.util.ArrayList;
import java.util.List;

public class MenuManager {
    private final List<MenuItem> menuItems;

    public MenuManager() { menuItems = new ArrayList<>(); }

    public void addMenuItem(MenuItem item) {
        if (item != null) menuItems.add(item);
    }

    public MenuItem getMenuItem(int index) {
        if (index >= 0 && index < menuItems.size()) return menuItems.get(index);
        return null;
    }

    public List<MenuItem> getAllMenuItems() { return new ArrayList<>(menuItems); }

    public void displayMenu() {
        System.out.println("\n=== Menu ===");
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println((i + 1) + ". " + menuItems.get(i));
        }
    }
}
