package evanssaxxmlparser;

// @author Riley Evans

import java.io.File;
import java.io.IOException;
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
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;


public class EvansSAXXmlParserController implements Initializable {

    EvansSAXXmlParserModel model;
    XMLNode root;
    
    Color blue    = Color.web("#2196F3");
    Color green   = Color.web("#4CAF50");
    Color red     = Color.web("#F44336");
    Color defualt = Color.web("#aaaaaa");
    
    Stage stage;
    
    @FXML Button fileChooseButton;
    @FXML Button parseButton;
    
    @FXML Label warningText;
    @FXML Circle infoCircle;
    
    void start(Stage stage) {
        this.stage = stage;
        updateWarning(defualt,"Choose a file to begin");
    }
    
    @FXML
    private void handleFileChoose(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File xmlSource = fileChooser.showOpenDialog(stage);
        if(!"xml".equals(getFileType(xmlSource))){
            updateWarning(red,"File \""+xmlSource.getName()+"\" not accepted.  Please select an XML file.");
            parseButton.setDisable(true);
        } else {
            model.setXML(xmlSource);
            updateWarning(green,"File \""+xmlSource.getName()+"\" accepted.");
            parseButton.setDisable(false);
        }
    }
    
    @FXML
    private void handleParse(ActionEvent event) throws ParserConfigurationException, SAXException, IOException {
        if(model.getXML() == null) {
            parseButton.setDisable(true);
            updateWarning(red,"XML file not loaded.  Please choose XML file.");
            return;
        }
        updateWarning(blue,"Started Parsing.");

        root = model.parse(model.getXML());
        
        if(root == null) System.out.println("Oh no");
        else System.out.println("GOTTEM");
        
        updateWarning(blue,"Finished Parsing \""+model.xmlSource.getName()+"\".");
        
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
    
    private void updateWarning(Color color, String message){
        warningText.setText(message);
        warningText.setTextFill(color);
        infoCircle.setFill(color);
    }
}
