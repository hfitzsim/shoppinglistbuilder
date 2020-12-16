import javafx.scene.control.TextField;

import java.util.ArrayList;

public class UserSessionVariables {
    private String username;
    private double budget;
    private double subtotal;
    private double remainingBudget;
    private ArrayList<GroceryItem> shoppingList = new ArrayList<GroceryItem>();
    private ArrayList<GroceryItem> purchased = new ArrayList<GroceryItem>();
    private ArrayList<GroceryItem> notPurchased = new ArrayList<GroceryItem>();



    public void setUsername(TextField nameInput){
        this.username = nameInput.getText();
    }

    public String getUsername() {
        return username;
    }

    public void setBudget(double d) {
        this.budget = d;
    }

    public double getBudget() {
        return budget;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getRemainingBudget() {
        return remainingBudget;
    }

    public void setRemainingBudget(double remainingBudget) {
        this.remainingBudget = remainingBudget;
    }

    public ArrayList<GroceryItem> getShoppingList() {
        return shoppingList;
    }

    public ArrayList<GroceryItem> getPurchased() {
        return purchased;
    }

    public void setPurchased(ArrayList<GroceryItem> purchased) {
        this.purchased = purchased;
    }

    public ArrayList<GroceryItem> getNotPurchased() {
        return notPurchased;
    }

    public void setNotPurchased(ArrayList<GroceryItem> notPurchased) {
        this.notPurchased = notPurchased;
    }
}
