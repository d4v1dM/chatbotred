/**********
 * Made by David Medina
 * This topic is College, mainly fetching college information and then serving it to the user.
 */

package groupFiles;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.nodes.*;
import org.jsoup.select.*;
import org.jsoup.*;

public class College implements Topic {
	private String[] infoChoices = {"General Information", "Tuition, Fees, and Estimated Student Expenses", "Financial Aid", "Admissions"};
	private int[] SAT = new int[3];
	private int[] ACT = new int[5];
	
	@Override
	public void talk() {
		Scanner userInput = new Scanner(System.in);
		ask("Do you want to get colleges based on your stats or just information about college?");
		ask("0. Colleges based on stats" + "\n" + "1. Information about colleges.");
		int reply = userInput.nextInt();
		switch(reply){
		case 0:
			collegeStats();
			break;
		case 1:
			collegeInformation();
			break;
		default:
			David.print("Invalid answer!");
		}
		David.inLoop = false;
		userInput.close();
	}
	
	public void collegeInformation(){
		Scanner cInput = new Scanner(System.in);
		int collegeNum;
		String URL = "http://nces.ed.gov/collegenavigator/?q=";
		
		System.out.println("What is the name of the college you want information on?");
		URL += toUrl(cInput.nextLine());
		
		Document site;
		try {
			site = Jsoup.connect(URL).get();
			ArrayList<String[]> results = getResults(site);
			
			System.out.println("Which college is it?(number)");
			for(int i = 0; i < results.size(); i++){
				System.out.println(i + ": " + getCollegeName(results.get(i)));
			}
			collegeNum = cInput.nextInt();
			
			site = Jsoup.connect(getCollegeLink(results.get(collegeNum))).get();
			
			System.out.println("What information do you want? (number)");
			for(int i = 0; i < infoChoices.length; i++){
				System.out.println(i + ": " + infoChoices[i]);
			}
			collegeNum = cInput.nextInt();
			
			switch(collegeNum){
			case 0: 
				getCollegeGeneral(site);
				break;
			case 1: 
				getCollegeTuition(site);
				break;
			case 2: 
				getCollegeAid(site);
				break;
			case 3: 
				getCollegeAdmissions(site);
				break;
			default: 
				System.out.println("That is not an option!");
			}
			cInput.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void collegeStats(){
		Scanner userChoice = new Scanner(System.in);
		ask("Do you want to use SAT scores or ACT scores?" + "\n" + "0: SAT" + "\n" + "1: ACT");
		int reply = userChoice.nextInt();
		
		switch(reply){
		case 0:
			matchSAT();
			break;
		case 1:
			matchACT();
			break;
		default:
			ask("INVALID INPUTs make me VERY MAD!");
			break;
		}
		userChoice.close();
	}
	
	public void matchSAT(){
		try {
			setSAT();
			String query = "http://www.collegesimply.com/search/" + "sat-" + SAT[2];
			Document doc = Jsoup.connect(query).get();
			Elements tags = doc.getElementsByClass("table").select("tbody").get(0).children();
			ask("Based on your scores, the schools are either reach, avg, low, or good.");
			for(Element tag: tags){
				ask(""); // create space.
				for(Element row: tag.children()){
					ask(row.text()); // get text of rows.
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void matchACT(){
		try {
			setACT();
			String query = "http://www.collegesimply.com/search/" + "act-" + ACT[4];
			Document doc = Jsoup.connect(query).get();
			Elements tags = doc.getElementsByClass("table").select("tbody").get(0).children();
			ask("Based on your scores, the schools are either reach, avg, low, or good.");
			for(Element tag: tags){
				ask(""); // create space.
				for(Element row: tag.children()){
					ask(row.text()); // get text of rows.
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean isTriggered(String userInput) {
		if(David.findKeyword(userInput, "college", 0) >= 0 || David.findKeyword(userInput, "colleges", 0) >= 0){
			return true;
		}
		return false;
	}
	public static void getCollegeTuition(Document site){
		Elements finAid = site.getElementById("expenses").children();
		String title1 = finAid.get(1).getElementsByClass("tabconstraint").get(0).child(0).text();
		String title2 = finAid.get(1).getElementsByClass("tabconstraint").get(0).child(1).text();
		
		finAid = finAid.get(1).child(0).child(2).children();
		
		System.out.println(title1 + "\n" + title2 + "\n");
		
		Elements finTop = finAid.get(1).child(0).children();
		
		for(Element top: finTop){
			System.out.println(top.text());
		}
		System.out.println("\n");
		
		
		finAid = finAid.select("tbody").get(0).children();
		Elements iter_parent;
		for(Element fin: finAid){
			iter_parent = fin.children();
			for(Element iter_child:iter_parent){
				if(iter_child.hasText()){
					System.out.println(iter_child.text());
				}
			}
			System.out.println("\n");
		}
	}
	
	public static void getCollegeAdmissions(Document site){
		Elements tables = site.getElementById("admsns").child(1).getElementsByClass("tabular").get(4).children();
		for(Element tab: tables.get(1).children().get(0).children()){
			System.out.println(tab.text());
		}
		
		for(Element tab: tables.select("tbody").select("tr")){
			for(Element row: tab.children()){
				System.out.println(row.text());
			}
		}
	}
	public static void getCollegeGeneral(Document site){
		Elements general = site.getElementsByClass("layouttab").select("tbody").get(0).children();
		Element info = site.getElementsByClass("collegedash").get(0).child(1).child(1);
		System.out.println(info.text());
		for(Element gen: general){
			System.out.println(gen.child(0).text() + gen.child(1).text());
		}
	}
	
	public static void getCollegeAid(Document site){
		Elements tableEles = site.getElementById("finaid").children();
		Elements mainRow = tableEles.select("thead").get(0).children().get(0).children();
		for(Element child:mainRow){
			System.out.println(child.text());
		}
		System.out.println("\n" + tableEles.get(1).getElementsByClass("tablenames").text());
		tableEles = tableEles.select("table").get(0).children();
		Elements iter_children;
		for(Element tableEle: tableEles.select("tbody").get(0).children()){
			//System.out.println("Parent: " + tableEle);
			iter_children = tableEle.children();
			for(Element iter_child:iter_children){
				System.out.println(iter_child.text());
			}
			System.out.println("");
		}
	}
	
	public static String toUrl(String query){
		String newUrl = "";
		query = query.toLowerCase();
		for(int i = 0; i < query.length(); i++){
			if(query.charAt(i) == ' '){
				newUrl += " ";
			}
			else newUrl += query.charAt(i);
		}
		return newUrl;
	}
	
	public static ArrayList<String[]> getResults(Document site){
		ArrayList<String[]> results = new ArrayList<String[]>();
		Elements colleges = site.getElementsByClass("resultsTable").select("a[href]");
		for(Element college: colleges){
			if(college.hasText()){
				String[] collegeData = {college.getElementsByTag("strong").text(), college.attr("href")};
				results.add(collegeData);
			}
		}
		return results;
	}
	
	public static String getCollegeName(String[] collegeData){
		return collegeData[0];
	}
	public static String getCollegeLink(String[] collegeData){
		return "http://nces.ed.gov/collegenavigator/" + collegeData[1];
	}
	public void ask(String quest){
		System.out.println(quest);
	}
	public void setACT(){
		Scanner input = new Scanner(System.in);
		ask("Please input your scores for the ACT.");
		
		ask("For the Math section: ");
		ACT[0] = input.nextInt();
		
		ask("For the English section: ");
		ACT[1] = input.nextInt();
		
		ask("For the Reading section: ");
		ACT[2] = input.nextInt();
		
		ask("For the Science section");
		ACT[3] = input.nextInt();
		
		ask("Your composite score: ");
		ACT[4] = input.nextInt();
		input.close();
		
	}
	public void setSAT(){
		Scanner input = new Scanner(System.in);
		ask("Please input your SATs scores.");
		
		ask("For the math section: ");
		SAT[0] = input.nextInt();
		
		ask("For the Reading section: ");
		SAT[1] = input.nextInt();
		
		ask("Your overall score: ");
		SAT[2] = input.nextInt();
		
		input.close();
	}

}
