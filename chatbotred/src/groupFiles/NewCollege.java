package groupFiles;

import java.io.IOException;

// David Medina
// this is the redone project.
public class NewCollege implements Topic {
	private int[] SAT = new int[2];
	private int[] ACT = new int[4];
	private int satComp,actComp;
	private boolean tookSat = false, tookAct = false;
	private double GPA;
	@Override
	public void talk() throws IOException {
		// TODO Auto-generated method stub

		setScores();
		endCollege();
		David.talkForever();
	}

	@Override
	public boolean isTriggered(String userInput) {
		if(foundKeyword(userInput, "college")|| foundKeyword(userInput, "colleges")){
			return true;
		}
		return false;
	}
	public static int getInt(){
		return David.input.nextInt();
	}
	public static double getDouble(){
		return David.input.nextDouble();
	}
	public int getComp(int[] scores){
		// this will calculate the ACT composition score.
		// which is the average of all act scores.
		double avg = 0;
		for(int i = 0; i < scores.length;i++){
			avg += (double) scores[i];
		}
		return (int) Math.round(avg/scores.length); // the scores will be rounded!
	}
	public void setACT(){
		//setter for ACT scores.
		System.out.println("Enter your scores.");
		
		David.print("What is your Math score?");
		ACT[0] = getInt();
		David.print("What is your English score?");
		ACT[1] = getInt();
		David.print("What is your Reading score?");
		ACT[2] = getInt();
		David.print("What is your Science score?");
		ACT[3] = getInt();
		actComp = getComp(ACT);
		
		David.getInput();
	}
	public void setSAT(){
		// setter for SAT scores.
		System.out.println("Enter your scores.");
		
		David.print("What is your Math score?");
		SAT[0] = getInt();
		David.print("What is your Reading score?");
		SAT[1] = getInt();
		satComp = (SAT[0] + SAT[1]);
		
		David.getInput();
	}
	
	public boolean foundKeyword(String line, String keyword){
		return (David.findKeyword(line, keyword, 0) >= 0); // helper method using findKeyword.
	}
	public void setScores(){
		String resp = "";
		David.print("First, let's get your Standarized test scores!");
		
		David.print("Did you take the SAT?");
		resp = David.getInput();
		if(foundKeyword(resp,"yes") || foundKeyword(resp,"y") || foundKeyword(resp,"yeah")){
			setSAT();
			tookSat = true;
		}
		
		David.print("Did you take the ACT?");
		resp = David.getInput();
		if(foundKeyword(resp,"yes") || foundKeyword(resp,"y") || foundKeyword(resp,"yeah")){
			setACT();
			tookAct = true;
		}
		
		David.print("What is your GPA?");
		GPA = getDouble();
	}
	public void rangeScore(){
		if((tookSat && satComp >= 2000) || (tookAct && actComp >= 30)){
			David.print("Based on your scores, you should have a decent chance in getting accepeted to top-tier colleges!");
		} else{
			if((tookSat && satComp >= 1800) || (tookAct && actComp >= 27)){
				David.print("Based on your scores, you should have a decent chance in getting accepted to state colleges!");
			} else{
				if((tookSat && satComp >= 1550) || (tookAct && actComp >= 25)){
					David.print("Based on your scores, you should have somewhat of an average chance in getting accepted to state colleges!");
				}else{
					David.print("Your scores are pretty low. You should practice some more, instead of talking to me!");
				}
			}
		}
	}
	public void whichField(){
		// tells what field user should get into.
		if((tookSat && SAT[0] > SAT[1]) || (tookAct && ACT[0] > ACT[1]) ){
			David.print("You should major in a STEM related field!");
		}
		else David.print("You should major in a non-STEM related field!");
	}
	public void endCollege(){
		rangeScore();
		whichField();
		David.print("There's always room for improvement, so keep working hard!");
	}
}
