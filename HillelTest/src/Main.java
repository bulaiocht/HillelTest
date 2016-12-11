
/**
* by Kocherga Vitalii 2016.12.10
*/

import java.util.concurrent.locks.*;
import java.util.*;
import java.io.*;
import java.util.regex.*;

public class Main{
	public static void main(String[] args){
		WordFinder wf = new WordFinder("storage/emulated/0/test"); //change to correct one
		System.out.println(wf.getWords().toString());
	}
}

/*3. Given the following class:
 public class IncrementSynchronize {
 private int value = 0;
 //getNextValue()
 }
 Write three different method options for getNextValue()
 that will return 'value++', each method
 needs to be synchronized in a different way.
 */
 
class IncrementSincronize {
	
	private int value = 0;
	
	Object lock1 = new Object();
	
	Lock lock2 = new ReentrantLock();
	
	public synchronized int getNextValueOne(){
		return value++;
	}
	
	public int getNextValueTwo(){
		synchronized(lock1){
			return value++;
		}
	}
	
	public int getNextValueThree(){
		
		lock2.lock();
		try{
			return value++;
			}
			finally{
				lock2.unlock();
				}
	}
}

class ArrayCopier{
	
	/*2 Write a generic method that takes an array
	 of objects and a collection, and puts all objects
	 in the array into the collection.*/
	 
	public static <E> void arrayCopier (E [] array, Collection <E> collection){
		for(E element : array){
			collection.add(element);
		}
	}
}

/*1. Write a program to find all distinct words
 from a text file. Ignore chars like
 ".,/-;:" and Ignore case sensitivity.*/
 
class WordFinder{
	private String filePath;
	private String text;
	private final String REGEXP = "(?ui)([\\w]+)";
	private StringBuilder builder = new StringBuilder();
	private ArrayList<String> list = new ArrayList<>();
	public WordFinder(String file){
		this.filePath = file;
	}
	
	private void readFile (){
		try{
			InputStream in = new FileInputStream(filePath);
			while(true){
				int data = in.read();
				if(data!=-1){
					builder.append((char)data);
				}else{
					in.close();
					break;}
			}
			}
			catch (IOException e){
			}
			text = builder.toString();
			analizeText();
	}
	
	public ArrayList<String> getWords(){
		readFile();
		analizeText();
		return list;
	}
	
	private void analizeText(){
		Pattern pattern = Pattern.compile(REGEXP);
		Matcher matcher = pattern.matcher(text);
		while(true){
			if(matcher.find()){
				list.add(text.substring(matcher.start(),matcher.end()));
			}else{
				break;
				}
		}
	}
}
