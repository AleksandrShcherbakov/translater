package com.example.translater;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Александр on 07.04.2019.
 */
public class XmlFb2 {
    public static Set<String> createStringSet(String path){
        File fb2File = new File(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(fb2File);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("p");
            Set<String> setStrings = new TreeSet<>();
            for (int i=0; i<nodeList.getLength(); i++){
                String string= nodeList.item(i).getTextContent();
                string = string.replaceAll("[\",.:?!;-]","");
                String[] strings = string.split(" ");
                for (String s : strings){
                    setStrings.add(s.toLowerCase());
                }
            }
            return setStrings;
        }
        catch (Exception e){
            return null;
        }
    }


}
