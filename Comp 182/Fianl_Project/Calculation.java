package Component;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
import java.io.IOException;

public class Calculation {
	static ArrayList<Integer> tCost = new ArrayList<Integer>();
	static ArrayList<String> cChosen = new ArrayList<String>();
	
	public void totalCost() {
		int totalC = 0;
		for(int i = 0; i <= tCost.size()-1; i++) {
			totalC += tCost.get(i);
		}
		System.out.println(totalC + "$");
	}

  	public void currentChosen() {
  		for(int j = 0; j<=cChosen.size()-1; j++) {
  			System.out.println("["+ j + "]" + cChosen.get(j));
  		}
  		System.out.println("");
  		System.out.println("going back to list.....");
  	}
  	
  	public void addList(String list) {
  		cChosen.add(list);
  	}
  	
  	public void addCost(int k) {
  		tCost.add(k);
  	}
  	
  	public void removeAll() {
  		for(int l = cChosen.size()-1; l >=0; l--) {
  			cChosen.remove(l);
  			tCost.remove(l);
  		}
  	}
  	
  	public void remove() {
  		Scanner myScannerTwo = new Scanner(System.in);
  		boolean selective;
  		int remove = 0;
  		do {
  			try {
  				remove = Integer.parseInt(myScannerTwo.nextLine());
  				selective = false;
  			}
  			catch(Exception e) {
  				System.out.println("please enter the number!");
  				selective = true;
  			}
  		}while(selective);
  		if(cChosen.size()!=0&&tCost.size()!=0) {
  		System.out.println("you chose to remove " + remove);
  		tCost.remove(remove);
  		cChosen.remove(remove);
  		System.out.println("going back to list.....");
  		}
  		else {
  			System.out.println("since your list size is "+ cChosen.size() + " system is saying that your list is empty");
  			System.out.println("for checking when we ask is it empty, it will say " +cChosen.isEmpty());
  			System.out.println("since your cost size is " + tCost.size()+ " System is saying that your cost is empty");
  			System.out.println("for checking when we ask is it empty, it will say " +tCost.isEmpty());
  		}
  		
  	}
}
