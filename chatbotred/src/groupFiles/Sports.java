package groupFiles;

public class Sports implements Topic {
	private boolean inSportsLoop;
	private boolean inBaseballLoop;
	private boolean inFootballLoop;
	private String sportsResponse;   
	
	// create more responses  
	private static String[] sport = {"I love sports.", "What sport do you like more? Football or Baseball?", "I'm not really a fan."}; // add more responses
	private static String[] football = {"Tom brady should be kicked out of the NFL", "the jets are not going to make playoffs this season.", "Broncos are doing well this season. Don't you think?", "I hope the NY Giants will win the superbowl again.", "Did you know that Lady Gaga is performing at this year's superbowl halftime?"};
	private static String[] baseball = {"Mets are number 1 in the wild card race.", "Hopefully the Mets won't screw up the world series again", "Chase Utley should be banned from the MLB for breaking Tejada's leg"};
	
	public void talk() {
		inSportsLoop = true;
		while(inSportsLoop){
			printResponse();
			sportsResponse = David.getInput();
		
			if(!isTriggered(sportsResponse)){
				inSportsLoop = false;
				inBaseballLoop = false;
				inFootballLoop = false;
				David.talkForever();
			}
		}
	}

	private void printResponse() {
		int sIndex = (int) (Math.random() * sport.length);
		int bIndex = (int) (Math.random() * baseball.length);
		int fIndex = (int) (Math.random() * football.length);
		if(inBaseballLoop && inSportsLoop){
			David.print(baseball[bIndex]);
		}
		else
		if(inFootballLoop && inSportsLoop){
			David.print(football[fIndex]);
		}
		else{
			David.print(sport[sIndex]);
		}
	}

	public boolean isTriggered(String userInput) {
		if(David.findKeyword(userInput, "sports", 0) >= 0){
			return true;
		}
		if(David.findKeyword(userInput, "baseball", 0) >= 0){
			inBaseballLoop = true;
			inSportsLoop = true;
		}
		if(David.findKeyword(userInput, "football", 0) >= 0){
			inFootballLoop = true;
			inSportsLoop = true;
		}
		return false;
	}

}
