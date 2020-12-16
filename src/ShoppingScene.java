import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ShoppingScene {

    private Scene scene;
    private Label title;
    private Utility toolbox;
    private UserSessionVariables sessionVariables;
    private TableView purchasedTable;
    private TableView notPurchasedTable;
    private Label initialBudget_label;
    private Label subtotal_label;
    private Label remainingBudget_label;
    private Button back_button;
    private Label notification_label;
    private Button open_file_button;


    public ShoppingScene(UserSessionVariables sessionVariables) {

//REQ. OBJECTS
        this.sessionVariables = sessionVariables;
        toolbox = new Utility();

//BUTTONS
        back_button = new Button("< Back");
        back_button.setMinSize(50, 15);
        back_button.setPrefSize(75, 25);
        back_button.setMaxSize(100, 35);

        open_file_button = new Button("Open File");
        open_file_button.setMinSize(50, 20);
        open_file_button.setPrefSize(100, 30);
        open_file_button.setMaxSize(150, 40);
        open_file_button.setVisible(false);

//LABELS
        title = new Label("Shopping Summary");
        title.setFont(Font.font("Arial", 18));
        title.setAlignment(Pos.CENTER);

        initialBudget_label = new Label("You set your budget as " + sessionVariables.getBudget());
        initialBudget_label.setFont(new Font("Arial", 16));
        VBox.setMargin(initialBudget_label, new Insets(0, 0, 0, 20));
        initialBudget_label.setAlignment(Pos.BASELINE_LEFT);

        subtotal_label = new Label("The total cost of what you bought was " + sessionVariables.getSubtotal());
        subtotal_label.setFont(new Font("Arial", 16));
        VBox.setMargin(subtotal_label, new Insets(0, 0, 0, 20));
        subtotal_label.setAlignment(Pos.BASELINE_LEFT);

        remainingBudget_label = new Label("Your cash left over is " + sessionVariables.getRemainingBudget());
        remainingBudget_label.setFont(new Font("Arial", 16));
        VBox.setMargin(remainingBudget_label, new Insets(0, 0, 0, 20));
        remainingBudget_label.setAlignment(Pos.BASELINE_LEFT);

        notification_label = new Label("Items not purchased were written to out.txt");
        if (sessionVariables.getNotPurchased().size() == 0) {
            notification_label.setVisible(false);
        }
        notification_label.setFont(new Font("Arial", 16));
        VBox.setMargin(notification_label, new Insets(0, 0, 0, 20));
        notification_label.setAlignment(Pos.TOP_RIGHT);

//LEFT & RIGHT TABLES SET UP
        //purchased item name column
        TableColumn itemNameCol = new TableColumn("Purchased");
        itemNameCol.setMinWidth(150);
        itemNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        //purchased item quantity column
        TableColumn itemQuantityCol = new TableColumn("Qty");
        itemQuantityCol.setMinWidth(50);
        itemQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        //purchased item price column
        TableColumn itemPriceCol = new TableColumn("price per unit");
        itemPriceCol.setMinWidth(75);
        itemPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //NOT purchased item name column
        TableColumn itemNameCol2 = new TableColumn("Not Purchased");
        itemNameCol2.setMinWidth(150);
        itemNameCol2.setCellValueFactory(new PropertyValueFactory<>("name"));

        //NOT purchased item quantity column
        TableColumn itemQuantityCol2 = new TableColumn("Qty");
        itemQuantityCol2.setMinWidth(50);
        itemQuantityCol2.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        //NOT purchased item price column
        TableColumn itemPriceCol2 = new TableColumn("price per unit");
        itemPriceCol2.setMinWidth(75);
        itemPriceCol2.setCellValueFactory(new PropertyValueFactory<>("price"));

//LEFT TABLE
        purchasedTable = new TableView<>();
        purchasedTable.getColumns().addAll(itemNameCol, itemQuantityCol, itemPriceCol);
        VBox.setMargin(purchasedTable, new Insets(0, 0, 10, 0));

//RIGHT TABLE
        notPurchasedTable = new TableView<>();
        notPurchasedTable.getColumns().addAll(itemNameCol2, itemQuantityCol2, itemPriceCol2);
        VBox.setMargin(notPurchasedTable, new Insets(0, 0, 10, 0));

//GRIDPANE SET UP
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(25);
        grid.setVgap(15);
        grid.setGridLinesVisible(false);


        VBox row0 = new VBox();
        row0.setSpacing(10);

        VBox row1 = new VBox();
        row1.setSpacing(10);

        VBox row3 = new VBox();
        row3.setSpacing(10);
        row3.setAlignment(Pos.BOTTOM_LEFT);

        row0.getChildren().add(back_button);
        row1.getChildren().add(title);

        grid.setHalignment(purchasedTable, HPos.LEFT);
        grid.add(purchasedTable, 0, 2);

        grid.setHalignment(notPurchasedTable, HPos.RIGHT);
        grid.add(notPurchasedTable, 1, 2);
        row3.getChildren().addAll(initialBudget_label, subtotal_label, remainingBudget_label);

        ObservableList<GroceryItem> purchased = FXCollections.observableList(sessionVariables.getPurchased());
        ObservableList<GroceryItem> notPurchased = FXCollections.observableList(sessionVariables.getNotPurchased());
        purchasedTable.setItems(purchased);
        notPurchasedTable.setItems(notPurchased);

        grid.add(row0, 0, 0);
        grid.add(row1, 0, 1, 2, 1);
        grid.add(row3, 0, 3, 2, 3);
        grid.add(notification_label, 1, 3);
        grid.add(open_file_button, 1, 4);

//ASSIGN SCENE TO GRID
        scene = new Scene(grid, 800, 700);
    }

    //This method is required for the same reason as the refreshTable(); method
    //The label data needs to be refreshed prior to going to the shoppingScene
    public void refreshLabels() {
        initialBudget_label.setText("You set your budget as $" + toolbox.round(sessionVariables.getBudget(), 2));
        subtotal_label.setText("You spent $" + toolbox.round(sessionVariables.getSubtotal(), 2));
        remainingBudget_label.setText("Your change is $" + toolbox.round(sessionVariables.getRemainingBudget(), 2));
        if (sessionVariables.getNotPurchased().size() > 0) {
            notification_label.setVisible(true);
            open_file_button.setVisible(true);
        }
        if (!sessionVariables.getUsername().equals(null)) {
            title.setText(sessionVariables.getUsername() + "'s Shopping Summary");
        }
    }


    public Scene getScene() {
        return scene;
    }

    public void refreshTable() {
        ObservableList<GroceryItem> purchased = FXCollections.observableList(sessionVariables.getPurchased());
        ObservableList<GroceryItem> notPurchased = FXCollections.observableList(sessionVariables.getNotPurchased());
        purchasedTable.setItems(purchased);
        notPurchasedTable.setItems(notPurchased);
    }

    public void setLastSceneAction(Stage primarystage, ModifyItemsScene modifyItemsScene) {
        back_button.setOnAction(e -> {
            primarystage.setScene(modifyItemsScene.getScene());
            modifyItemsScene.refreshTable();
        });
    }

    public void showReminderFile() {
        open_file_button.setOnAction(e -> {
            Stage pre = new Stage();
            pre.setTitle("out.txt - Reminder");
            Label label = new Label(sessionVariables.getUsername() + ", the following items were not purchased:");
            label.setFont(Font.font("Arial", 16));

            TextArea text = new TextArea();
            VBox root = new VBox();
            VBox.setMargin(label, new Insets(10, 10, 10, 10));
            Scene scene = new Scene(root, 450, 200);

            File file = new File("out.txt");
            StringBuilder str = new StringBuilder();
            try {
                Scanner input = new Scanner(file);
                while (input.hasNext()) {
                    str.append(input.nextLine() + "\n");
                }
                text.setText(str.toString());
            } catch (FileNotFoundException paimon) {
                System.out.println("no file found :(");
            }
            root.getChildren().addAll(label, text);
            pre.setScene(scene);
            pre.setX(290);
            pre.setY(540);
            pre.showAndWait();
        });
    }
}





