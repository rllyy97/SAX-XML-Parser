package evanssaxxmlparser;

// @author Riley Evans

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;


public class EvansSAXXmlParserModel {
    
    File xmlSource;
    
    
    
    File getXML(){
        return xmlSource;
    }
    
    void setXML(File xmlSource){
        this.xmlSource = xmlSource;
    }
    
    public static XMLNode parse(File xmlSource) throws ParserConfigurationException, SAXException, IOException {
        
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        
        EvansHandler handler = new EvansHandler();
        
        saxParser.parse(xmlSource, handler);
        return handler.getRoot();
    }
}


