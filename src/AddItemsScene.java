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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class AddItemsScene {

    private Scene scene;
    private Button next_button;
    private TableView<GroceryItem> tableView;
    private Utility toolbox;
    private Button back_button;
    private UserSessionVariables sessionVariables;



    public AddItemsScene(UserSessionVariables sessionVariables) {

//REQ. OBJECTS
        toolbox = new Utility();
        ShoppingActions nextAction = new ShoppingActions();
        this.sessionVariables = sessionVariables;

//LABELS
        Label title = new Label("Create Shopping List");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFont(Font.font("Arial", 18));
        Label item_label = new Label("Item: ");
        item_label.setFont(new Font("Arial", 14));
        Label qty_label = new Label("Qty: ");
        qty_label.setFont(new Font("Arial", 14));
        Label priority_label = new Label("Priority: ");
        priority_label.setFont(new Font("Arial", 14));

//TEXTFIELDS
        TextField itemField = new TextField();
        itemField.setMinSize(50, 20);
        itemField.setPrefSize(75, 30);
        itemField.setMaxSize(100, 40);

        TextField quantityField = new TextField();
        quantityField.setMinSize(50, 20);
        quantityField.setPrefSize(75, 30);
        quantityField.setMaxSize(100, 40);

        TextField priorityField = new TextField();
        priorityField.setMinSize(50, 20);
        priorityField.setPrefSize(75, 30);
        priorityField.setMaxSize(100, 40);

//BUTTONS
        Button add_button = new Button("Add Item");
        add_button.setMinSize(50, 20);
        add_button.setPrefSize(100, 30);
        add_button.setMaxSize(150, 40);
        add_button.setStyle("-fx-background-color: #2f7ed4; -fx-font-weight: bold; -fx-text-fill: #ffffff");


        next_button = new Button("Next >");
        next_button.setMinSize(50, 20);
        next_button.setPrefSize(100, 30);
        next_button.setMaxSize(150, 40);
        next_button.setStyle("-fx-background-color: #28b807; -fx-font-weight: bold; -fx-text-fill: #ffffff");


        back_button = new Button("< Back");
        back_button.setMinSize(50, 15);
        back_button.setPrefSize(75, 25);
        back_button.setMaxSize(100, 35);

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
        ObservableList<GroceryItem> shoppingList = FXCollections.observableList(sessionVariables.getShoppingList());
        tableView.getColumns().addAll(itemNameCol, itemQuantityCol, itemPriorityCol);
        tableView.setItems(shoppingList);

//FORMATTING
        VBox.setMargin(title, new Insets(10,0,20,0));
        VBox.setMargin(tableView, new Insets(20, 0, 10, 0));
        VBox.setMargin(item_label, new Insets(50, 20, 0, 20));
        VBox.setMargin(qty_label, new Insets(0, 20, 0, 20));
        VBox.setMargin(priority_label, new Insets(0, 20, 0, 20));
        VBox.setMargin(itemField, new Insets(50, 10, 0, 10));
        VBox.setMargin(quantityField, new Insets(10, 20, 0, 10));
        VBox.setMargin(priorityField, new Insets(10, 20, 0, 10));
        VBox.setMargin(add_button, new Insets(40, 10, 0, 0));
        VBox.setMargin(next_button, new Insets(0, 0, 0, 25));


//GRIDPANE SETUP
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setGridLinesVisible(false);

        VBox labels_col = new VBox();
        labels_col.setSpacing(30);
        VBox fields_col = new VBox();
        fields_col.setSpacing(5);

        labels_col.getChildren().addAll(item_label, qty_label, priority_label);
        fields_col.getChildren().addAll(itemField, quantityField, priorityField, add_button);


//LAYOUT
        grid.add(back_button, 0, 0);
        grid.setHalignment(title, HPos.CENTER);
        grid.add(title, 1, 1, 4, 1);
        grid.setHalignment(tableView, HPos.RIGHT);
        grid.add(tableView, 2, 2);
        grid.setHalignment(labels_col, HPos.LEFT);
        grid.add(labels_col, 0, 2);
        grid.setHalignment(fields_col, HPos.CENTER);
        grid.add(fields_col, 1, 2);
        grid.setHalignment(next_button, HPos.RIGHT);
        grid.add(next_button, 3, 2);

//BUTTON LISTENER
        add_button.setOnAction(e -> {
            if (!itemField.getText().equals("") ) {
                nextAction.addItem(itemField.getText(), quantityField.getText(), priorityField.getText(), sessionVariables.getShoppingList());
            }
            toolbox.bubbleSort(sessionVariables.getShoppingList());
            tableView.refresh();
            itemField.setText("");
            quantityField.setText("");
            priorityField.setText("");
        });

//ASSIGN SCENE TO GRID
        scene = new Scene(grid, 750, 700);
    }

    public Scene getScene() {
        return scene;
    }

    public void refreshTable() {
        this.sessionVariables = sessionVariables;
        ObservableList<GroceryItem> shoppingList = FXCollections.observableList(sessionVariables.getShoppingList());
        tableView.setItems(shoppingList);
    }

    public void setNextSceneAction(Stage primarystage, ModifyItemsScene modifyItemsScene){
        next_button.setOnAction(e -> {
            primarystage.setScene(modifyItemsScene.getScene());
            modifyItemsScene.refreshTable();
        });
    }

    public void setLastSceneAction(Stage primarystage, StartUpScene startUpScene){
        back_button.setOnAction(e -> {
            primarystage.setScene(startUpScene.getScene());
        });

    }
}

