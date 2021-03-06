/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evanssaxxmlparser;

import java.util.Arrays;
import java.util.Stack;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author riley
 */
public class EvansHandler extends DefaultHandler {
    
    XMLNode root;
    XMLNode currentNode = null;
    Stack<XMLNode> stack = new Stack<>();

    String currentNodeName;
    String currentNodeContent;
    XMLNode node = null;

    XMLNode getRoot() {
        return root;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//        System.out.println(uri);
//        System.out.println(localName);
//        System.out.println(qName);
//        System.out.println(attributes);
        
        node = new XMLNode();
        node.setName(qName);
        for(int i = 0; i < attributes.getLength(); i++) {
            node.attributes.put(attributes.getQName(i),attributes.getValue(i));
        }
        stack.push(node);

        if(currentNode != null)
            currentNode.addChild(node);
        currentNode = node;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(stack == null)
            return;
        XMLNode poppedNode = stack.pop();
        if(poppedNode != null){
            if(stack.isEmpty()){
                root = poppedNode;
                currentNode = null;
            } else {
                currentNode = stack.lastElement();
            }
        }             
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        String content = new String(ch,start,length);
        if(!content.matches(".*\\w.*")){
            content = "";
        }
        currentNode.setContent(content);
    }
    
}
