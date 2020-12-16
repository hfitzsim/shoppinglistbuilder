import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class ModifyItemsScene {

    private Scene scene;
    private TableView<GroceryItem> tableView;
    private Button go_shopping_btn;
    private UserSessionVariables sessionVariables;
    private ShoppingActions nextAction;
    private Button back_button;

    public ModifyItemsScene(UserSessionVariables sessionVariables) {

//REQ. OBJECTS
        this.sessionVariables = sessionVariables;
        nextAction = new ShoppingActions();

//TABLE SET UP
        //item name column
        TableColumn itemNameCol = new TableColumn("Item");
        itemNameCol.setMinWidth(150);
        itemNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        //item quantity column
        TableColumn itemQuantityCol = new TableColumn("Quantity");
        itemQuantityCol.setMinWidth(50);
        itemQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        //priority number column
        TableColumn itemPriorityCol = new TableColumn("Priority");
        itemPriorityCol.setMinWidth(50);
        itemPriorityCol.setCellValueFactory(new PropertyValueFactory<>("priority"));

        tableView = new TableView<>();
        tableView.setPrefSize(400, 400);
        tableView.getColumns().addAll(itemNameCol, itemQuantityCol, itemPriorityCol);

        TableView.TableViewSelectionModel selectionModel = tableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

//LABELS
        Label title = new Label("Select item in table to modify");
        title.setFont(new Font("Arial", 16));

        Label move_on = new Label("Click Go Shopping > to confirm cart.");
        move_on.setWrapText(true);
        move_on.setFont(new Font("Arial", 12));

        Label item_label = new Label("Edit name: ");
        item_label.setPrefSize(100,20);
        item_label.setFont(new Font("Arial", 14));

        Label qty_label = new Label("Edit quantity: ");
        qty_label.setPrefSize(100, 20);
        qty_label.setFont(new Font("Arial", 14));

        Label priority_label = new Label("Edit priority: ");
        priority_label.setPrefSize(100, 20);
        priority_label.setFont(new Font("Arial", 14));

//TEXTFIELDS
        TextField itemField = new TextField();
        itemField.setMinSize(75, 20);
        itemField.setPrefSize(100, 30);
        itemField.setMaxSize(125, 40);

        TextField quantityField = new TextField();
        quantityField.setMinSize(75, 20);
        quantityField.setPrefSize(100, 30);
        quantityField.setMaxSize(125, 40);

        TextField priorityField = new TextField();
        priorityField.setMinSize(75, 20);
        priorityField.setPrefSize(100, 30);
        priorityField.setMaxSize(125, 40);

//BUTTONS
        Button update_name = new Button("Update Name");
        update_name.setMinSize(100, 20);
        update_name.setPrefSize(150, 30);
        update_name.setMaxSize(200, 40);

        Button update_quantity = new Button("Update Qty");
        update_quantity.setMinSize(100, 20);
        update_quantity.setPrefSize(150, 30);
        update_quantity.setMaxSize(200, 40);

        Button update_priority = new Button("Update Priority");
        update_priority.setMinSize(100, 20);
        update_priority.setPrefSize(150, 30);
        update_priority.setMaxSize(200, 40);

        Button delete_button = new Button("DELETE ITEM");
        delete_button.setMinSize(100, 20);
        delete_button.setPrefSize(150, 30);
        delete_button.setMaxSize(200, 40);
        delete_button.setStyle("-fx-background-color: #d10f0f; -fx-font-weight: bold; -fx-text-fill: #ffffff");

        go_shopping_btn = new Button("Go Shopping >");
        go_shopping_btn.setMinSize(50, 30);
        go_shopping_btn.setPrefSize(100, 40);
        go_shopping_btn.setMaxSize(150, 50);
        go_shopping_btn.setStyle("-fx-background-color: #28b807; -fx-font-weight: bold; -fx-text-fill: #ffffff");

        back_button = new Button("< Back");
        back_button.setMinSize(50, 15);
        back_button.setPrefSize(75, 25);
        back_button.setMaxSize(100, 35);

//FORMATTING
        VBox.setMargin(title, new Insets(0, 0, 0, 0));
        VBox.setMargin(tableView, new Insets(0, 0, 10, 0));
        VBox.setMargin(item_label, new Insets(5, 0, 5, 0));
        VBox.setMargin(qty_label, new Insets(5, 0, 5, 0));
        VBox.setMargin(priority_label, new Insets(5, 5, 0, 0));
        VBox.setMargin(itemField, new Insets(0, 0, 0, 0));
        VBox.setMargin(quantityField, new Insets(0, 0, 0, 0));
        VBox.setMargin(priorityField, new Insets(0, 0, 0, 0));
        VBox.setMargin(update_name, new Insets(0, 0, 0, 0));
        VBox.setMargin(update_quantity, new Insets(0, 0, 0, 0));
        VBox.setMargin(update_priority, new Insets(0, 0, 0, 0));
        VBox.setMargin(go_shopping_btn, new Insets(5, 0, 0, 0));

//SET UP GRID PANE
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(25);
        grid.setVgap(25);
        grid.setGridLinesVisible(false);

//CREATE ROWS & COLUMNS
        VBox row0 = new VBox();
        row0.setSpacing(10);
        VBox row1 = new VBox();
        row1.setSpacing(10);
        VBox row2 = new VBox();
        row2.setSpacing(10);
        VBox row3 = new VBox();
        row3.setSpacing(10);
        VBox row4 = new VBox();
        row4.setSpacing(10);

        VBox labelsCol = new VBox();
        labelsCol.setSpacing(10);
        labelsCol.setAlignment(Pos.TOP_LEFT);

        VBox textFieldsCol = new VBox();
        textFieldsCol.setSpacing(10);
        textFieldsCol.setAlignment(Pos.TOP_LEFT);

        VBox buttonsCol = new VBox();
        buttonsCol.setSpacing(10);
        buttonsCol.setAlignment(Pos.CENTER_RIGHT);

//CREATE OWNERSHIP OF ELEMENTS
        labelsCol.getChildren().addAll(item_label, qty_label, priority_label, move_on);
        textFieldsCol.getChildren().addAll(itemField, quantityField, priorityField);
        buttonsCol.getChildren().addAll(update_name, update_quantity, update_priority);

        row0.getChildren().addAll(back_button, title);
        row1.getChildren().add(tableView);
        row1.setAlignment(Pos.CENTER);
        row4.getChildren().add(move_on);
        row4.setAlignment(Pos.BOTTOM_RIGHT);

//CONFIGURE GRID LAYOUT
        grid.add(row0, 0, 0, 3, 1);
        grid.add(row1, 0, 1, 3, 1);

        grid.setHalignment(move_on, HPos.RIGHT);
        grid.add(move_on, 1, 3);

        grid.setHalignment(labelsCol, HPos.LEFT);
        grid.add(labelsCol, 0, 2);

        grid.setHalignment(textFieldsCol, HPos.CENTER);
        grid.add(textFieldsCol, 1, 2);

        grid.setHalignment(buttonsCol, HPos.RIGHT);
        grid.add(buttonsCol, 2, 2);

        grid.setHalignment(delete_button, HPos.RIGHT);
        grid.add(delete_button, 3, 1);

        grid.setHalignment(go_shopping_btn, HPos.RIGHT);
        grid.add(go_shopping_btn, 2, 3);

//BUTTON LISTENERS
        update_name.setOnAction(e -> {
            nextAction.editName(sessionVariables.getShoppingList(), itemField.getText(), tableView.getSelectionModel().getSelectedItem());
            itemField.setText("");
            tableView.refresh();
        });

        update_quantity.setOnAction(e -> {
            nextAction.editQuantities(sessionVariables.getShoppingList(), quantityField.getText(), tableView.getSelectionModel().getSelectedItem());
            quantityField.setText("");
            tableView.refresh();
        });

        update_priority.setOnAction(e -> {
            nextAction.editPriorities(sessionVariables.getShoppingList(), priorityField.getText(), tableView.getSelectionModel().getSelectedItem());
            priorityField.setText("");
            tableView.refresh();
        });

        delete_button.setOnAction(e -> {
            nextAction.deleteItem(sessionVariables.getShoppingList(), tableView.getSelectionModel().getSelectedItem());
            tableView.refresh();
        });

//ASSIGN SCENE TO GRID
        scene = new Scene(grid, 750, 775);
    }

    public Scene getScene() {
        return scene;
    }

    //This method is required because everytime the list backing the tableview is updated, the table also has to be reset
    //or else the selection breaks, so we need to provide a way for the button that's loading this table to refresh it
    public void refreshTable() {
        this.sessionVariables = sessionVariables;
        ObservableList<GroceryItem> shoppingList = FXCollections.observableList(sessionVariables.getShoppingList());
        tableView.setItems(shoppingList);
    }

    public void setNextSceneAction(Stage primarystage, ShoppingScene shoppingScene) {
        go_shopping_btn.setOnAction(e -> {
            nextAction.goShopping(sessionVariables);
            nextAction.writeNotPurchased(sessionVariables);
            shoppingScene.refreshTable();
            shoppingScene.refreshLabels();
            primarystage.setScene(shoppingScene.getScene());
        });
    }

    public void setLastSceneAction(Stage primarystage, AddItemsScene addItemsScene){
        back_button.setOnAction(e -> {
            primarystage.setScene(addItemsScene.getScene());
            addItemsScene.refreshTable();
        });
    }
}
