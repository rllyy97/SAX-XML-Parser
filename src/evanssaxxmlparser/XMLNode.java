package evanssaxxmlparser;

// @author Riley Evans

import java.util.ArrayList;
import org.xml.sax.Attributes;

public class XMLNode {
    String name = "";
    String content = "";
    ArrayList<XMLNode> children = new ArrayList<>();
    Attributes attributes = null;

    void setName(String name){
        this.name = name;
    }
    void setContent(String content){
        this.content = content;
    }
    void setAttributes(Attributes attributes){
        this.attributes = attributes;
    }
    void addChild(XMLNode node) {
        children.add(node);
    }
    
}
