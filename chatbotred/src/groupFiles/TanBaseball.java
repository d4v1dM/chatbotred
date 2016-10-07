//Kristy Tan

package groupFiles;

import java.io.IOException;

public class TanBaseball implements Topic {

	private boolean inBaseballLoop1;
	private String bResponse;
	private boolean nameRight;
	private int start;
	private static String[] bQuestions = {"Who got a 2 run homer?", "Who got a base hit?", "Who made the diving catch?", "Who is on first base?", "Who just hit a grand slam?", "Who is pitching today?", "Who is at bat right now?", "Who threw the ball to 3rd base?"};
	private static String[] randomAnswer = {"Correct.", "Yes.", "No.", "Wrong."};
	private static String[] people = {"Marshall", "Cespedes", "Crawford", "Fernandez", "Pagan", "Rizzo", "Rutgers", "Murphy", "Kansas", "Smith", "Antonio", "Dallas", "Ortiz", "Posey", "Turner"};
	
	public void talk() throws IOException {
		inBaseballLoop1 = true;
		start = 1;
		while(inBaseballLoop1){
			printResponse();
			bResponse = David.getInput();
			if(David.findKeyword(bResponse, "bye", 0) >= 0 || David.findKeyword(bResponse, "quit", 0) >= 0 || David.findKeyword(bResponse, "college", 0) >= 0 || David.findKeyword(bResponse, "goodbye", 0) >= 0){
				inBaseballLoop1 = false;
				David.talkForever();
			}
			else{
				isName(bResponse);
			}
		} 
	}

	private void printResponse() {
		int ranIndex = (int) (Math.random() * randomAnswer.length);
		int responseIndex = (int)(Math.random() * bQuestions.length);
		int pIndex = (int)(Math.random() * people.length);
		
		if(start == 1){
			David.print(bQuestions[responseIndex]);
			start++;
		}
		else{
			if(nameRight == false){
				David.print("I do not understand. Please just give me a name.");
			}
			else{
				if(randomAnswer[ranIndex] == "Yes." || randomAnswer[ranIndex] == "Correct."){
					David.print(randomAnswer[ranIndex] + bResponse + " did");
					David.print(bQuestions[responseIndex]);
				}
				else{
					David.print(randomAnswer[ranIndex] + " " + bResponse + " did not. " + people[pIndex] + " did.");
					David.print(bQuestions[responseIndex]);
				}	
			}
		}
		
	}
	
	public void isName(String userInput){
		int spaces = 0;
		
		for(int s = 0; s < userInput.length(); s++){
			if(userInput.charAt(s) == ' '){
				spaces++;
			}
		}
		
		if(spaces > 1){
			nameRight = false;
		}
		else{
			nameRight=true;
		}
	}
	
	public boolean isTriggered(String userInput) {
		if(David.findKeyword(userInput, "sports", 0) >= 0){
			return true;
		}
		if(David.findKeyword(userInput, "baseball", 0) >= 0){
			return true;
		}
		if(David.findKeyword(userInput, "baseballs", 0) >= 0){
			return true;
		}
		if(David.findKeyword(userInput, "sports", 0) >= 0){
			return true;
		}
		return false;
	}
}
