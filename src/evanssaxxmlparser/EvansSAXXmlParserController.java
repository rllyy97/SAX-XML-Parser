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
import javafx.scene.shape.Circle;


public class EvansSAXXmlParserController implements Initializable {

    EvansSAXXmlParserModel model;
    
    Stage stage;
    
    @FXML Button fileChooseButton;
    @FXML Button parseButton;
    
    @FXML Label warningText;
    @FXML Circle infoCircle;
    
    Color accent = Color.web("#aaa");
    
    void start(Stage stage) {
        this.stage = stage;
        warningText.setTextFill(accent);
        infoCircle.setFill(accent);
    }
    
    @FXML
    private void handleFileChoose(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File xmlSource = fileChooser.showOpenDialog(stage);
        if(!"xml".equals(getFileType(xmlSource))){
            warningText.setText("File \""+xmlSource.getName()+"\" not accepted.  Please select an XML file.");
            accent = Color.web("#F44336");
            warningText.setTextFill(accent);
            infoCircle.setFill(accent);
            parseButton.setDisable(true);

        }else{
            model.setXML(xmlSource);
            warningText.setText("File \""+xmlSource.getName()+"\" accepted.");
            accent = Color.web("#4CAF50");
            warningText.setTextFill(accent);
            infoCircle.setFill(accent);
            parseButton.setDisable(false);

        }
    }
    
    @FXML
    private void handleParse(ActionEvent event) {
        if(model.getXML() == null) {
            parseButton.setDisable(true);
            warningText.setText("XML file not loaded.  Please choose XML file.");
            accent = Color.web("#F44336");
            warningText.setTextFill(accent);
            infoCircle.setFill(accent);
            return;
        }
        warningText.setText("Started parsing.");
        accent = Color.web("#3F51B5");
        warningText.setTextFill(accent);
        infoCircle.setFill(accent);


        // Do the parsing
        System.out.println("hey");
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new EvansSAXXmlParserModel();
        parseButton.setDisable(true);
        warningText.setTextFill(accent);
    }
    
    private String getFileType(File input) {
        String name = input.getName();
        return name.substring(name.lastIndexOf(".")+1);
    }
}
