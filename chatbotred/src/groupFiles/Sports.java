//Kristy Tan
// idea: Whos on first on base, ask names. Check to see if its one word only. And answer back with user input.

package groupFiles;

import java.io.IOException;

public class Sports implements Topic {
	private boolean inSportsLoop;
	private boolean inBaseballLoop;
	private boolean inFootballLoop;
	private String sportsResponse;  
	private boolean inTeamLoop;
	
	private static String[] football = {"Tom brady should be kicked out of the NFL", "the jets are not going to make playoffs this season.", "Broncos are doing well this season. Don't you think?", "I hope the NY Giants will win the superbowl again.", "Did you know that Lady Gaga is performing at this year's superbowl halftime?"};
	private static String[] baseball = {"Mets are number 1 in the wild card race.", "Hopefully the Mets won't screw up the world series again", "Chase Utley should be banned from the MLB for breaking Tejada's leg"};
	private static String[] bteam = {"New York Mets", "San Fransico Giants", "Cleveland Cavaliers", "Miami Heat"};
	private static String[] fteam = {"New York Jets", "New York Giants", "Denver Broncos", "New England Patriots", "Miami Dolphins"};
	public void talk() {
		inSportsLoop = true;
		while(inSportsLoop || inBaseballLoop || inFootballLoop){
			printResponse();
			sportsResponse = David.getInput();
			
			if(!isTriggered(sportsResponse)){
				inSportsLoop = false;
				inBaseballLoop = false;
				inFootballLoop = false;
				try {
					David.talkForever();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void printResponse() {
		int bIndex = (int) (Math.random() * baseball.length);
		int fIndex = (int) (Math.random() * football.length);
		int btIndex = (int) (Math.random() * bteam.length);
		int ftIndex = (int) (Math.random() * fteam.length);
		if(inBaseballLoop){
			if(inTeamLoop){
				David.print("My favorite team is " + bteam[btIndex] + ".");
			}
			else{
				David.print(baseball[bIndex]);
			}
		}
		else{
			if(inFootballLoop){
				if(inTeamLoop){
					David.print("My favorite team is " + fteam[ftIndex] + ".");
				}
				else{
					David.print(football[fIndex]);
				}
			}
		}
	}

	public boolean isTriggered(String userInput) {
		if(David.findKeyword(userInput, "sports", 0) >= 0){
			return inSportsLoop = true;
		}
		if(inSportsLoop && David.findKeyword(userInput, "baseball", 0) >= 0){
			inFootballLoop = false;
			return inBaseballLoop = true;
		}
		if(inSportsLoop && David.findKeyword(userInput, "football", 0) >= 0){
			inBaseballLoop = false; 
			return inFootballLoop = true;
			
		}
		if(inSportsLoop && David.findKeyword(userInput, "team", 0) >= 0){
			return inTeamLoop = true;
		}
		return false;
	}
}
