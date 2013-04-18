package net.enib.mymorse;

import java.util.Hashtable;
import java.util.Map;

public class LatinMorseConverter {
	
	private Map<String,String> latinAlphabet;
	private Map<String,String> morseAlphabet;
	
	public LatinMorseConverter(){
		String alphabetTable[] = {
				"a", "._",
				"b", "_...",
				"c", "_._.",
				"d", "_..",
				"e", ".",
				"f", ".._.",
				"g", "__.",
				"h", "....",
				"i", "..",
				"j", ".___",
				"k", "_._",
				"l", "._..",
				"m", "__",
				"n", "_.",
				"o", "___",
				"p", ".__.",
				"q", "__._",
				"r", "._.",
				"s", "...",
				"t", "_",
				"u", ".._",
				"v", "..._",
				"w", ".__",
				"x", "_.._",
				"y", "_.__",
				"z", "__..",
				
				"0", "_____",
				"1", ".____",
				"2", "..___",
				"3", "...__",
				"4", "...._",
				"5", ".....",
				"6", "_....",
				"7", "__...",
				"8", "___..",
				"9", "____."};
		
		latinAlphabet = new Hashtable<String,String>();
		morseAlphabet = new Hashtable<String,String>();
		
		for (int i = 0 ; i < alphabetTable.length ;i+=2){
			latinAlphabet.put(alphabetTable[i], alphabetTable[i+1]);
			morseAlphabet.put(alphabetTable[i+1], alphabetTable[i]);
		}
	}
	
	private String latinToMorseChar(Character c){
		c = Character.toLowerCase(c);
		String key = c.toString();
		String mc = "";
		if(latinAlphabet.containsKey(key)){
			mc = latinAlphabet.get(key);
		}
		return mc.trim();
	}
	
	public String latinToMorseString(String s){
		String result = "";
		for (int i = 0 ; i < s.length() ;i++){
			char c = s.charAt(i);
			if(((Character)c).toString().matches("[a-zA-Z0-9]")){
			    result += latinToMorseChar(c);
			    if(i < s.length()){
			    	result += " ";
			    }
			} else if(((Character)c).toString().matches(" ")){
				result += "/ ";
			}
		}
		return result.trim();
	}
	
	public String morseToLatinString(String s){
		String[] cutString = s.split(" ");
		String result = "";
		for (int i = 0 ; i < cutString.length ;i++){
			if(cutString[i].matches("[_.]+") && morseAlphabet.containsKey(cutString[i])){
				result += morseAlphabet.get(cutString[i]);
			} else if(cutString[i].matches("/")){
				result += " ";
			}
		}
		return result.trim();
	}
}
