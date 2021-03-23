package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
//	private List<String> dizionario;
	 List<String> dizionario;
	//Si occupa di caricare il dizionario. passo la lingua della quale voglio caricare il diizonario
	public void loadDictionary(String lenguage) {
		dizionario=new LinkedList<String>();
		this.dizionario.clear();
		if(lenguage.equals("English")) {
			
			try {
				FileReader fr=new FileReader("src/main/resources/English.txt");
				BufferedReader br=new BufferedReader(fr);
				String word;
				while((word= br.readLine())!=null) {
					dizionario.add(word);
				}
				br.close();
			} catch (IOException e){
				System.out.println("Errore nella lettura file");
			}
		} else if (lenguage.equals("Italian")) {
			try {
				FileReader fr=new FileReader("src/main/resources/Italian.txt");
				BufferedReader br=new BufferedReader(fr);
				String word;
				while((word= br.readLine())!=null) {
					dizionario.add(word);
				}
				br.close();
			} catch (IOException e){
				System.out.println("Errore nella lettura file");
			}
		}
	}
	
	public List<RichWord> spellCheckText(List<String> imputTextList){
		List<RichWord> tmp= new LinkedList<RichWord>();
		
		for(String s : imputTextList) {
			if (dizionario.contains(s)==true) {
				RichWord rc=new RichWord(s,true);
				tmp.add(rc);
			} else {
				RichWord rc=new RichWord(s,false);
				tmp.add(rc);
			}
		}
		return tmp;
	}
}

