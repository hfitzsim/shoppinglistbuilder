import java.util.ArrayList;

public interface Actions {

    void addItem(String itemName, String quantity, String priority, ArrayList<GroceryItem> shoppingList);

    void editName(ArrayList<GroceryItem> shoppingList, String s, GroceryItem groceryItem);

    void editQuantities(ArrayList<GroceryItem> shoppingList, String s, GroceryItem groceryItem);

    void editPriorities(ArrayList<GroceryItem> shoppingList, String s, GroceryItem groceryItem);


    void goShopping(UserSessionVariables sessionVariables);

    void writeNotPurchased(UserSessionVariables sessionVariables);

}
