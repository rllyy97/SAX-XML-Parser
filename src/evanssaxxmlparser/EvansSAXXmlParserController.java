package evanssaxxmlparser;

// @author Riley Evans

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.paint.Color;


public class EvansSAXXmlParserController implements Initializable {

    EvansSAXXmlParserModel model;
    
    Stage stage;
    
    @FXML Label fileChooserWarning;
    
    void start(Stage stage) {
        this.stage = stage;
    }
    
    @FXML
    private void handleFileChoose(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File xmlSource = fileChooser.showOpenDialog(stage);
        if(!"xml".equals(getFileType(xmlSource))){
            fileChooserWarning.setText("File not accepted.  Please Select an XML file.");
            fileChooserWarning.setTextFill(Color.RED);
        }else{
            model.setXML(xmlSource);
            fileChooserWarning.setText("File accepted.");
            fileChooserWarning.setTextFill(Color.GREEN);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new EvansSAXXmlParserModel();
    }
    
    private String getFileType(File input) {
        String name = input.getName();
        return name.substring(name.lastIndexOf(".")+1);
    }
}
