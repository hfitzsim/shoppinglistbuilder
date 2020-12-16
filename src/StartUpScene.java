import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StartUpScene {
    private Scene scene;
    private Button next_screen;
    private TextField nameInput;
    private TextField budgetField;
    private Utility toolbox;
    private UserSessionVariables sessionVariables;



    public StartUpScene(UserSessionVariables sessionVariables) {

        this.sessionVariables = sessionVariables;
        toolbox = new Utility();
//LABELS
        Label description = new Label("Hello! I am your shopping list builder.  \nPlease enter your name in the text box below.");
        description.setAlignment(Pos.CENTER);
        description.setFont(Font.font("Arial", 16));
        Label name = new Label("Name: ");
        name.setFont(Font.font("Arial", 12));
        Label budget = new Label("Budget: ");
        budget.setFont(Font.font("Arial", 12));
        budget.setVisible(false);
        Label description2 = new Label("Please, enter your budget for this shopping trip. \nPress Next > to confirm.");
        description2.setAlignment(Pos.CENTER);
        description2.setFont(Font.font("Arial", 16));
        description2.setVisible(false);

//TEXTFIELDS
        nameInput = new TextField();
        nameInput.setMinSize(100, 20);
        nameInput.setPrefSize(125, 30);
        nameInput.setMaxSize(150, 40);

        budgetField = new TextField();
        budgetField.setVisible(false);
        budgetField.setMinSize(50, 20);
        budgetField.setPrefSize(75, 30);
        budgetField.setMaxSize(100, 40);

//BUTTONS
        Button submit = new Button("Submit Name");
        submit.setMinSize(50, 20);
        submit.setPrefSize(100, 30);
        submit.setMaxSize(150, 40);

        next_screen = new Button("Next >");
        next_screen.setMinSize(50, 20);
        next_screen.setPrefSize(100, 30);
        next_screen.setMaxSize(150, 40);
        next_screen.setVisible(false);

//FORMATTING
        VBox.setMargin(description, new Insets(20, 20, 0, 0));
        VBox.setMargin(nameInput, new Insets(0, 20, 0, 20));
        VBox.setMargin(description2, new Insets(20, 20, 0, 0));
        VBox.setMargin(budgetField, new Insets(0, 20, 5, 20));
        VBox.setMargin(submit, new Insets(0, 20, 0, 20));
        VBox.setMargin(next_screen, new Insets(0, 20, 0, 20));


//CREATE GRIDPANE
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(100, 50, 50, 100));
        gridPane.setHgap(25);
        gridPane.setVgap(15);
        gridPane.setGridLinesVisible(false);

//CREATE ROWS & COLUMNS
        VBox row1 = new VBox();
        row1.setSpacing(10);
        VBox row2 = new VBox();
        row1.setSpacing(10);
        VBox row3 = new VBox();
        row1.setSpacing(10);

        HBox h1 = new HBox();
        h1.setSpacing(10);
        HBox h2 = new HBox();
        h2.setSpacing(10);

//CONFIGURE ROWS & COLUMNS
        row1.getChildren().addAll(description);
        h1.getChildren().addAll(name, nameInput, submit);
        row2.getChildren().addAll(h1, description2);
        h2.getChildren().addAll(budget, budgetField, next_screen);
        row3.getChildren().addAll(h2);

//CONFIG LAYOUT
        gridPane.add(row1, 0, 1);
        gridPane.add(row2, 0, 2);
        gridPane.add(row3, 0, 3);

//BUTTON LISTENER
        submit.setOnAction(e -> {
            sessionVariables.setUsername(nameInput);
            description.setText("Thank you, " + sessionVariables.getUsername() + "! \n You can modify your name here if need be.");
            submit.setText("Change Name");
            budget.setVisible(true);
            budgetField.setVisible(true);
            description2.setVisible(true);
            nameInput.setText("");
            next_screen.setVisible(true);
        });
//ASSIGN SCENE TO GRID
        scene = new Scene(gridPane, 700, 700);
    }

    public Scene getScene() {
        return scene;
    }

    public void setNextSceneAction(Stage primarystage, AddItemsScene addItemsScene, UserSessionVariables sessionVariables) {
        next_screen.setOnAction(e -> {
            if (!budgetField.getText().isEmpty()) {
                double d = toolbox.getNextDouble(budgetField.getText());
                sessionVariables.setBudget(d);
            }
            primarystage.setScene(addItemsScene.getScene());

            Stage pre = new Stage();
            pre.setTitle("out.txt - Reminder");
            Label label = new Label("Reminder: the following items were not purchased last time: ");
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
            pre.setY(270);
            pre.showAndWait();
        });
    }
}
