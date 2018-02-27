package evanssaxxmlparser;

// @author Riley Evans

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.Attributes;
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
    
    @FXML TextArea displayBox;
    
    void start(Stage stage) {
        this.stage = stage;
        updateWarning(defualt,"Choose a file to begin");
    }
    
    @FXML
    private void handleFileChoose(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File xmlSource = fileChooser.showOpenDialog(stage);
        if(xmlSource == null) {
            return;
        } else if(!"xml".equals(getFileType(xmlSource))){
            updateWarning(red,"File \""+xmlSource.getName()+"\" not accepted.  Please select an XML file.");
            parseButton.setDisable(true);
        } else {
            model.setXML(xmlSource);
            updateWarning(green,"File \""+xmlSource.getName()+"\" accepted.");
            parseButton.setDisable(false);
            displayBox.clear();
        }
    }
    
    @FXML
    private void handleParse(ActionEvent event) throws ParserConfigurationException, SAXException, IOException {
        displayBox.clear();
        if(model.getXML() == null) {
            parseButton.setDisable(true);
            updateWarning(red,"XML file not loaded.  Please choose XML file.");
            return;
        }
        updateWarning(blue,"Started Parsing.");

        root = model.parse(model.getXML());
        
        if(root == null){
            updateWarning(red,"An error occured.  File may be empty. Try another file.");
            return;
        }
        
        printTree(root,0);
        
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
    
    public void printTree(XMLNode root,int step) {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }
        try {
            for(int i = step;i>0;i--)displayBox.appendText("\t");
            displayBox.appendText(root.getName());
            if(!root.getContent().equals(""))
                displayBox.appendText(" - " + root.getContent());
//            System.out.println(root.getName() + " - " + root.getContent());
            
            for (int i = 0; i < root.attributes.size(); i++) {
                displayBox.appendText(
                    "\t -> \t" + root.attributes.keySet() + " : " + root.attributes.values());
            }
            displayBox.appendText("\n");
            ArrayList<XMLNode> children = root.getChildren();
            for (int i = 0; i < children.size(); i++) {
                XMLNode node = children.get(i);
                printTree(node,step+1);
            }
        } catch (Throwable e) {
            displayBox.appendText("Unable to print.");
        }
    }
}
