import javafx.scene.control.Button;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ShoppingActions implements Actions {


    private final Utility toolbox = new Utility();
    private final DecimalFormat df = new DecimalFormat("#.00");

    public ShoppingActions() {

    }

    @Override
    public void addItem(String itemName, String quantity, String priority, ArrayList<GroceryItem> shoppingList) {
        int q = toolbox.getNextInt(quantity);
        int p = toolbox.getNextInt(priority);
        GroceryItem e = new GroceryItem(itemName, q, p, false, toolbox.round((10.29 + (10 * Math.random())), 2));
        shoppingList.add(e);
    }

    @Override
    public void editName(ArrayList<GroceryItem> shoppingList, String s, GroceryItem groceryItem) {
        if (!s.equals(null) && shoppingList.contains(groceryItem)) {
            groceryItem.setName(s);
        }
    }

    @Override
    public void editQuantities(ArrayList<GroceryItem> shoppingList, String s, GroceryItem groceryItem) {
        int q = toolbox.getNextInt(s);
        if (!s.equals(null) && shoppingList.contains(groceryItem)) {
            groceryItem.setQuantity(q);
        } else {
            groceryItem.setQuantity(groceryItem.getQuantity());
        }
    }

    @Override
    public void editPriorities(ArrayList<GroceryItem> shoppingList, String s, GroceryItem groceryItem) {
        int p = toolbox.getNextInt(s);
        if (!s.equals(null) && shoppingList.contains(groceryItem)) {
            groceryItem.setPriority(p);
        } else {
            groceryItem.setPriority(groceryItem.getPriority());
        }
        toolbox.bubbleSort(shoppingList);
    }

    public void deleteItem(ArrayList<GroceryItem> shoppingList, GroceryItem groceryItem) {
        shoppingList.remove(groceryItem);
    }

    @Override
    public void goShopping(UserSessionVariables sessionVariables) {

        double subtotal = 0.0;
        double initialBudget = sessionVariables.getBudget();
        double remainingBudget = initialBudget;

        ArrayList<GroceryItem> purchased = new ArrayList<GroceryItem>();
        ArrayList<GroceryItem> notPurchased = new ArrayList<GroceryItem>(sessionVariables.getShoppingList());

        GroceryItem nextItemToBuy = notPurchased.get(0);

        //shopping list already ordered by priority
        while (notPurchased.size() > 0 && remainingBudget >= nextItemToBuy.getPrice()) {

            GroceryItem purchasedItem = nextItemToBuy.copy();
            purchasedItem.setQuantity(0);
            //buy as many of nextitemtobuy as possible
            //and add them to the purchased list
            //deplete the budget based on how many you bought

            while (nextItemToBuy.getQuantity() > 0 && remainingBudget >= nextItemToBuy.getPrice()) {
                //in this while loop we want to deplete the budget by the price of the next item
                nextItemToBuy.setQuantity(nextItemToBuy.getQuantity() - 1);
                subtotal = subtotal + nextItemToBuy.getPrice();
                purchasedItem.setQuantity(purchasedItem.getQuantity() + 1);
                remainingBudget = remainingBudget - purchasedItem.getPrice();

            }
            purchased.add(purchasedItem);
            if (nextItemToBuy.getQuantity() == 0) {
                notPurchased.remove(0);
            }

            if (notPurchased.size() > 0) {
                nextItemToBuy = notPurchased.get(0);
            }

        }
        sessionVariables.setPurchased(purchased);
        sessionVariables.setNotPurchased(notPurchased);
        sessionVariables.setSubtotal(toolbox.round(subtotal, 2));
        sessionVariables.setRemainingBudget(toolbox.round(remainingBudget, 2));
    }

    @Override
    public void writeNotPurchased(UserSessionVariables sessionVariables) {
        String fileName = "out.txt";
        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the file " + fileName);
            System.exit(0);
        }
        for (GroceryItem i : sessionVariables.getNotPurchased()) {
            outputStream.println(i.getName());
        }
        outputStream.close();
        if (sessionVariables.getNotPurchased().size() > 0) {
            System.out.println("Items not purchased were written to " + fileName);
        }
    }
}

