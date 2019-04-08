package com.example.translater;

import com.sun.org.apache.xml.internal.serialize.LineSeparator;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.TreeSet;


/**
 * Created by Александр on 06.04.2019.
 */
public class Helper {

    private static String stringSelection = System.lineSeparator();

    public static String createTranscriptionAndTranslate(String word) throws IOException {
        Connection connection = Jsoup.connect("https://wooordhunt.ru/word/" + word);
        connection.timeout(10*1000);
        Document doc = connection.get();
        Element element1 = doc.getElementById("word_forms");
        String adition="";
        if (element1 != null) {
            if (element1.text().startsWith("irregular")) {
                adition = element1.text();
            } else {
                String[] strings = element1.text().split("[  ]");
                return createTranscriptionAndTranslate(strings[strings.length - 1]);
            }
        }
        Elements transcription = doc.getElementsByClass("transcription");
        Elements translate = doc.getElementsByClass("t_inline_en");
        try {
            if (!adition.equals("")){
                return word + " " + transcription.get(1).text() + " " + translate.get(0).text() + stringSelection + adition;
            }
            else return word + " " + transcription.get(1).text() + " " + translate.get(0).text();
        }
        catch (Exception e){

        }
    return "";
    }

    public static void writeFile(Set<String> set, String path) throws IOException {
        File file = new File(path);
        Set<String> res = new TreeSet<>();

        for (String s: set){
            res.add(Helper.createTranscriptionAndTranslate(s));
        }
        if (file.exists()){
            file.delete();
        }
        Files.createFile(Paths.get(path));

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file, true));
        for (String s :res){
            outputStreamWriter.write(s+stringSelection);
        }
        outputStreamWriter.close();
    }

}
