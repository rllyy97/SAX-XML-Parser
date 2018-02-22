package evanssaxxmlparser;

// @author Riley Evans

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EvansSAXXmlParser extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("EvansSAXXmlParserView.fxml"));
        Parent root = fxmlloader.load();
        EvansSAXXmlParserController controller = (EvansSAXXmlParserController) fxmlloader.getController();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        controller.start(stage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
