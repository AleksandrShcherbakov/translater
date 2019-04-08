package com.example.translater;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Set;

@SpringBootApplication
public class TranslaterApplication {

	public static void main(String[] args) throws IOException, SocketTimeoutException {
		//SpringApplication.run(TranslaterApplication.class, args);
		//System.out.println(Helper.createTranscriptionAndTranslate("warms"));
		Set<String> stringSet = XmlFb2.createStringSet("C://111.fb2");
		Helper.writeFile(stringSet,"C://words.txt");
	}

}
