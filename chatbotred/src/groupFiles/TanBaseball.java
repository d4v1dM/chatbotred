//kristy tan

package groupFiles;

public class TanBaseball implements Topic {

	private boolean inBaseballLoop1;
	private String bResponse;
	private static String[] bQuestions = {"Who is on first base", "Who just hit a grand slam?", "Who is pitching today?", "Who is batting right now?", "Who threw the ball to 3rd base?"};
	
	public void talk() {
		inBaseballLoop1 = true;
		while(inBaseballLoop1){
			printResponse();
			bResponse = David.getInput();
			isName(bResponse);
//			if(isName(bResponse)){
//				printResponse();
//				bResponse = David.getInput(); 
//			}
//			
//			else if(!isTriggered(bResponse)){
//				inBaseballLoop1 = false;
//				David.talkForever();
//			}
		}
	}

	private void printResponse() { 
		int responseIndex = (int)(Math.random() * bQuestions.length);
		David.print(bQuestions[responseIndex]);
		
	}
	
	public void isName(String userInput){
		String[] randomAnswer = {"Correct.", "Wrong."};
		int ranIndex = (int) (Math.random() * randomAnswer.length);
		int spaces = 0;
	
		for(int s = 0; s < userInput.length(); s++){
			if(userInput.charAt(s) == ' '){
				spaces++;
			}
		}
		
		if(spaces > 2){
			David.print("I do not understand, Please just give me a name.");
			bResponse = David.getInput();
			isName(bResponse);
		}
		else{
			if(randomAnswer[ranIndex] == "Correct."){
				David.print(randomAnswer[ranIndex] + bResponse + " did");
			}
			else{
				David.print(randomAnswer[ranIndex] + bResponse + " did not.");
			}	
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
