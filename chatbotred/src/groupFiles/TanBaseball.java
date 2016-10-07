//kristy tan

package groupFiles;

public class TanBaseball implements Topic {

	private boolean inBaseballLoop1;
	private boolean nameRight;
	private String bResponse;
	private int spaces;
	private static String[] bQuestions = {"Who is on first base", "Who just hit a grand slam?", "Who is pitching today?", "Who is batting right now?", 
										 "Who threw the ball to 3rd base?", "Who just got banned from MLB?", "Who got a home run?", "Who struck out?", 
							   			 "Who is the ace pitcher?", "Who lead their team to victory in the World Series?"};
	private static String[] randomAnswer = {"Correct.", "Wrong.", "Yes.", "No."};
	
	public void talk() {
		inBaseballLoop1 = true;
		while(inBaseballLoop1){
			printResponse();
			bResponse = David.getInput();
			isName(bResponse);

		}
	}

	private void printResponse() { 
		int ranIndex = (int) (Math.random() * randomAnswer.length);
		int responseIndex = (int)(Math.random() * bQuestions.length);
		
		if(nameRight == false){
			if(spaces > 1){
				David.print("I do not understand, Please just give me a name.");
				bResponse = David.getInput();
				isName(bResponse);
			}
		}
		else{
			if(randomAnswer[ranIndex] == "Correct." || randomAnswer[ranIndex] == "Yes."){
				David.print(randomAnswer[ranIndex] + bResponse + " did");
			}
			else{
				David.print(randomAnswer[ranIndex] + bResponse + " did not.");
			}	
		}
		David.print(bQuestions[responseIndex]);
	}
		
	
	public boolean isName(String userInput){
		
		for(int s = 0; s < userInput.length(); s++){
			if(userInput.charAt(s) == ' '){
				spaces++;	
			}
		}
		
		if(spaces > 1){
			nameRight = false;
			System.out.println("false");
		}
		else{
			nameRight = true;
			System.out.println("true");
		}
		return nameRight;
	}
	
//	public void isName(String userInput){
//		String[] randomAnswer = {"Correct.", "Wrong."};
//		int ranIndex = (int) (Math.random() * randomAnswer.length);
//		int spaces = 0;
//	
//		for(int s = 0; s < userInput.length(); s++){
//			if(userInput.charAt(s) == ' '){
//				spaces++;
//			}
//		}
//		
//		if(spaces > 2){
//			David.print("I do not understand, Please just give me a name.");
//			bResponse = David.getInput();
//			isName(bResponse);
//		}
//		else{
//			if(randomAnswer[ranIndex] == "Correct." || randomAnswer[ranIndex] == "Yes."){
//				David.print(randomAnswer[ranIndex] + bResponse + " did");
//			}
//			else{
//				David.print(randomAnswer[ranIndex] + bResponse + " did not.");
//					}	
//		}
//		
//	}
	
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
