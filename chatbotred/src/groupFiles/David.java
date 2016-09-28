package groupFiles;

import java.util.Scanner;

public class David {

	static Scanner input;
	static String user;
	static boolean inLoop;
	static String response;
	
	//declare group's classes
	//static Topic school;
	
	static int lineCount;
	public static void main(String[] args){
		createTopics();
		lineCount = 0;
		//demonstrateStringMethods();
		promptName();
		//promptInput();
		talkForever();
	}
	
	private static void promptName() {
		// TODO Auto-generated method stub
		cleanPrint("Hello, human! I am a board coverd with semi-conductors and other such electronic components. What is your name?");
		user = input.nextLine();
		cleanPrint("I will call you " + user  + " until you terminate me.");
		
	}
	
	public static String getInput(){
		return input.nextLine();
	}

	public static void print(String args){
		lineCount++;
		System.out.println("Line #" + lineCount + ": " + args);
	}
	
	public static void cleanPrint(String args){
		// create multi-line String
		String printString = "";
		int cutOff = 35;
		// check if there are words to add.
		// is the len of args is > 0;
		
		while(args.length() > 0){
			String currentLine = "";
			String nextWord = "";
			// while the currentLine and the next word are less then cutoff do the following loop.
			// first thing, add the next word.
			while(currentLine.length() + nextWord.length() <= cutOff && args.length() > 0){
				currentLine += nextWord;
				// then remove that word.
				args = args.substring(nextWord.length());
				// get the following word.
				int endOfWord = args.indexOf(" ");
				if(endOfWord == -1){
					endOfWord = args.length() - 1;
				}
				nextWord = args.substring(0,endOfWord + 1);
			}
			printString += currentLine + "\n";
			
		}
		
		System.out.println(printString);
		
	}
	
	public static void talkForever(){
		inLoop = true;
		while(inLoop){
			cleanPrint("Greetings, " + user + " , how are you?");
			response = getInput();
			if(findKeyword(response,"good",0) >= 0){
				cleanPrint("I'm so happy you are happy");
			}
//			else if(findKeyword(response,"school",0) >= 0){
//				inLoop = false;
//				school.talk();
//				}
			// that was only for the school topic.
			else{
				cleanPrint("I'm sorry I dont understand you.");
			}
			
		}
	}

	public static int findKeyword(String searchString, String key, int startIndex) {
		// TODO Auto-generated method stub
		
		// delete whitespace
		String phrase = searchString.trim();
		
		//set all letters to lower case
		phrase = phrase.toLowerCase();
		key = key.toLowerCase();
		
//		cleanPrint("The phrase is " + phrase);
//		cleanPrint("The key is " + key);
		
		// find position of key
		int psn = phrase.indexOf(key);
		
		//cleanPrint("The position found is " + psn);
		
		// keep looking for the word until you find the correct context
		while(psn >= 0){
			
			String before = " ";
			String after = " ";
			
			// check to see if the phrase does not end with this word 
			if(psn + key.length() < phrase.length()){
				after = phrase.substring(psn + key.length(), psn + key.length() + 1);
				//cleanPrint("The character before " + key + " is " + before);
				
				// if the phrase does not begin with this word
				if(psn > 0){
					before = phrase.substring(psn - 1,psn).toLowerCase();
					//cleanPrint("The character before " + key + " is " + before);
				}
				
				if(before.compareTo("a") < 0 && after.compareTo("a") < 0){
					//cleanPrint(key + " was found at " + psn);
					if(noNegations(phrase,psn)){
						return psn;
					}
				}
				
				// in case the keyword was not found yet, check the rest of the string
				psn = phrase.indexOf(key,psn + 1);
				//cleanPrint(key + " was not found. " + "checking " + psn);
			}
			//return false;
		}
				
		return -1;
	}

	private static boolean noNegations(String phrase, int index){
		// check for word "NO" (3 characters)
		// check to see if there is space for the word
		// "NO " to be in front of the index
		
		if(index - 3 >= 0 && phrase.substring(index - 3, index).equals("no ")){
			return false;
		}
		
		// check for "NOT"
		if(index - 4 >= 0 && phrase.substring(index - 4,index).equals("not ")){
			return false;
		}
		
		// check for "NEVER"
		if(index - 6 >= 0 && phrase.substring(index - 6,index).equals("never ")){
			return false;
		}
		
		// check for "n't "
		if(index - 4 >= 0 && phrase.substring(index - 4, index).equals("n't ")){
			return false;
		}
		return true;
		
	}
	public static void promptInput() {
		// TODO Auto-generated method stub
		print(user + " ,try inputing a String");
		String userInput = input.nextLine();
		System.out.println("You typed " + userInput);
	}

	public static void createTopics() {
		input = new Scanner(System.in);
//		school = new School();
		//initialize group glasses
	}

}
