package evanssaxxmlparser;

// @author Riley Evans

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EvansSAXXmlParserController implements Initializable {

    Stage stage;
    
    void start(Stage stage) {
        this.stage = stage;
    }
    
    @FXML
    private void handleFileChoose(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(stage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
