package evanssaxxmlparser;

// @author Riley Evans

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.xml.sax.Attributes;

public class XMLNode {
    String name = "";
    String content = "";
    ArrayList<XMLNode> children = new ArrayList<>();
    Map<String, String> attributes = new HashMap<>();

    void setName(String name){
        this.name = name;
    }
    void setContent(String content){
        this.content = this.content.concat(content);
    }
    void setAttributes(Map attributes){
        this.attributes = attributes;
    }
    void addChild(XMLNode node) {
        children.add(node);
    }
    
    String getName() {
        return name;
    }
    String getContent() {
        return content;
    }
    ArrayList<XMLNode> getChildren() {
        return children;
    }
    Map getAttributes() {
        return attributes;
    }
    
}
