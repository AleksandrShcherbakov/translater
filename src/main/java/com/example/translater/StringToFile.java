package com.example.translater;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by a.shcherbakov on 08.04.2019.
 */
public class StringToFile {
    public static Set<String> createStringSet(String string){
        Set<String> setStrings = new TreeSet<>();
        string = string.replaceAll("[\",.:?!;-]","");
        String[] strings = string.split(" ");
        for (String s : strings){
            setStrings.add(s.toLowerCase());
        }
        return setStrings;
    }

    public static String readTextFromFile(String path) throws IOException {
        File file = new File(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        StringBuilder stringBuilder = new StringBuilder();
        while (reader.ready()){
            stringBuilder.append(reader.readLine());
        }
        reader.close();
        return stringBuilder.toString();

    }

}
