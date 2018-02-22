package evanssaxxmlparser;

// @author Riley Evans

import java.io.File;


public class EvansSAXXmlParserModel {
    
    File xmlSource;
    
    File getXML(){
        return xmlSource;
    }
    
    void setXML(File xmlSource){
        this.xmlSource = xmlSource;
    }
    
}
