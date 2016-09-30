package groupFiles;

public class Sports implements Topic {
	private boolean inSportsLoop;
	private String sportsResponse;   
	
	// create more responses  
	private static String[] sport = {"I love sports.", "What sport do you like?", "Did you watch last night's game?"}; // add more responses
	private static String[] basketball = {"Who do you like more? Lebron or Kobe?", ""}; 
	private static String[] football = {"Tom brady should be kicked out of the NFL", "the jets are not going to make playoffs this season."};
	private static String[] baseball = {"Mets are number 1 in the wild card race.", "Hopefully they won't screw up the world series again"};
	
	public void talk() {
		inSportsLoop = true;
		while(inSportsLoop){
			printResponse();
			sportsResponse = David.getInput();
			if(!isTriggered(sportsResponse)){
				inSportsLoop = false;
				David.talkForever();
			}
		}
	}

	private void printResponse() {
		int sIndex = (int) (Math.random() * sport.length);
		David.print(sport[sIndex]);
		
	}

	public boolean isTriggered(String userInput) {
		if(David.findKeyword(userInput, "sports", 0) >= 0){
			return true;
		}
		if(David.findKeyword(userInput, "basketball", 0) >= 0){
			return true;
		}
		if(David.findKeyword(userInput, "baseball", 0) >= 0){
			return true;
		}
		if(David.findKeyword(userInput, "football", 0) >= 0){
			return true;
		}
		return false;
	}

}
