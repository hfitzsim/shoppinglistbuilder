import javafx.application.Application;
import javafx.stage.Stage;


public class UserInterface extends Application {

    @Override
    public void init() throws Exception {
        System.out.println("Loading application...");
    }

    @Override
    public void start(Stage primarystage) throws Exception {

        primarystage.setTitle("Shopping List Builder");

        UserSessionVariables sessionVariables = new UserSessionVariables();

//initialize all the scenes
        StartUpScene startUpScene = new StartUpScene(sessionVariables);
        AddItemsScene addItemsScene = new AddItemsScene(sessionVariables);
        ModifyItemsScene modifyItemsScene = new ModifyItemsScene(sessionVariables);
        ShoppingScene shoppingScene = new ShoppingScene(sessionVariables);

//link all the scenes together
        startUpScene.setNextSceneAction(primarystage, addItemsScene, sessionVariables);
        addItemsScene.setNextSceneAction(primarystage, modifyItemsScene);
        modifyItemsScene.setNextSceneAction(primarystage, shoppingScene);

//link all the scenes back buttons
        addItemsScene.setLastSceneAction(primarystage, startUpScene);
        modifyItemsScene.setLastSceneAction(primarystage, addItemsScene);
        shoppingScene.setLastSceneAction(primarystage, modifyItemsScene);
        shoppingScene.showReminderFile();

        primarystage.setScene(startUpScene.getScene());
        primarystage.setX(850);
        primarystage.setY(175);

        primarystage.show();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Application closed");
        System.exit(0);
    }
}
