import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class Utility {

    private DecimalFormat df = new DecimalFormat("#.00");

    public Utility() {
    }

    public ArrayList<GroceryItem> bubbleSort(ArrayList<GroceryItem> shoppingList) {
        Utility tool = new Utility();

        while (tool.isOutOfOrder(shoppingList)) {
            for (int i = 0; i < shoppingList.size() - 1; i++) {
                if (shoppingList.get(i).getPriority() > shoppingList.get(i + 1).getPriority()) {
                    GroceryItem x = shoppingList.get(i + 1);
                    shoppingList.set(i + 1, shoppingList.get(i));
                    shoppingList.set(i, x);
                }
            }
        }
        return shoppingList;
    }

    private boolean isOutOfOrder(ArrayList<GroceryItem> shoppingList) {
        for (int i = 0; i < shoppingList.size() - 1; i++) {
            if (shoppingList.get(i).getPriority() > shoppingList.get(i + 1).getPriority()) {
                return true;
            }
        }
        return false;
    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int getNextInt(String s) {
        int num = 0;

            try {
                if (isInteger(s)) {
                    num = Integer.parseInt(s);
                } else {
                    throw new BadInputException("The input was not a number.");
                }
            } catch (BadInputException paimon) {
                System.out.println(paimon.getMessage());
                Stage error = new Stage();
                Label err = new Label("The input was not a number. \n Try entering a number.");
                GridPane grid = new GridPane();
                grid.add(err, 0, 0);
                grid.setHalignment(err, HPos.CENTER);
                Scene message = new Scene(grid);
                error.setScene(message);
                err.setPrefSize(200, 100);
                error.show();
            }
        return num;

    }
//
    public double getNextDouble(String s) {
        double num = 0.0;

        try {
            if (isDouble(s)) {
                num = Double.parseDouble(s);
            } else {
                throw new BadInputException("input not numerical");
            }
        } catch (BadInputException paimon) {
            System.out.println(paimon.getMessage());
            Stage error = new Stage();
            Label err = new Label("The input was not a number. \n Try entering a number.");
            GridPane grid = new GridPane();
            grid.add(err, 0, 0);
            grid.setHalignment(err, HPos.CENTER);
            Scene message = new Scene(grid);
            error.setScene(message);
            err.setPrefSize(200, 100);
            error.show();
        }
        return num;
    }

}





