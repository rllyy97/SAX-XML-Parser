package evanssaxxmlparser;

// @author Riley Evans

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.paint.Color;


public class EvansSAXXmlParserController implements Initializable {

    EvansSAXXmlParserModel model;
    
    Stage stage;
    
    @FXML Button fileChooseButton;
    @FXML Button parseButton;
    
    @FXML Label fileChooseWarning;
    @FXML Label parseWarning;
    

    
    void start(Stage stage) {
        this.stage = stage;
    }
    
    @FXML
    private void handleFileChoose(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File xmlSource = fileChooser.showOpenDialog(stage);
        if(!"xml".equals(getFileType(xmlSource))){
            fileChooseWarning.setText("File not accepted.  Please select an XML file.");
            fileChooseWarning.setTextFill(Color.RED);
            parseButton.setDisable(true);

        }else{
            model.setXML(xmlSource);
            fileChooseWarning.setText("File accepted.");
            fileChooseWarning.setTextFill(Color.GREEN);
            parseButton.setDisable(false);

        }
    }
    
    @FXML
    private void handleParse(ActionEvent event) {
        if(model.getXML() == null) {
            parseButton.setDisable(true);
            parseWarning.setText("XML file not loaded.  Please choose XML file.");
            parseWarning.setTextFill(Color.RED);
            return;
        }
        parseWarning.setText("Started parsing.");
        parseWarning.setTextFill(Color.BLUE);


        // Do the parsing
        System.out.println("hey");
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new EvansSAXXmlParserModel();
        parseButton.setDisable(true);
    }
    
    private String getFileType(File input) {
        String name = input.getName();
        return name.substring(name.lastIndexOf(".")+1);
    }
}
