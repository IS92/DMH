import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Björn
 *
 */
public class Menu {
	private static final String SUPERMENU="\nType the digit responding to what you want to do.\n\n"+
	"1 - Create new NPC\n" +
	"2 - Add Player\n" + 
	"3 - Change HP\n" +
	"0 - Exit\n";
	private static final String WELCOMETEXT="Welcome to the Dungeon Masters little helper.\n" +
	"Press Enter to begin!";
	private static final int NOOFCHOICES=3;
	private Scanner scanner;
	private ArrayList<Character> charlist;
	private int counter;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Menu start=new Menu();
	}

	public Menu() {
		scanner = new Scanner(System.in);
		charlist = new ArrayList<Character> ();
		printWelcome();
		counter=1;
		printMenu();
		scanner.close();
	}
	
	/**
	 * Sets up a menu, listens to choice and runs appropriate method.
	 * If choice == 0, quit.
	 */
	private void printMenu() {
		System.out.println();
		printList();
		System.out.println(SUPERMENU);
		int choice = 0;
		try {
			choice = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Not a number. Please try again.\n");
			printMenu();
		}
		switch(choice) {
		case 1:
			createNPC();
			break;
		case 2:
			addPlayer();
			break;
		case 3:
			changeHP();
			break;
		case 0:
			System.exit(0);
		default:
			System.out.println("Incorrect number. Please try again.\n");
		}
		printMenu();

	}

	private void printList() {
		for (int i=0; i<charlist.size(); i++){
			System.out.println(charlist.get(i));
		}
		System.out.println();
	}

	private void createNPC() {
		System.out.println();
		System.out.println("What is the name of the NPC you wish to create?");
		String name=scanner.next();
		System.out.println();
		System.out.println("The NPC has been given the name "+name+" and the ID "+counter+".");
		int hp=askHP(name);
		int init=askInit(name);
		int exp=askExp(name);
		System.out.println();
		System.out.println("What is the gear of " + name + "?");
		String gear=scanner.next();
		System.out.println();
		System.out.println(name + " has been given "+gear+".");
		NPC npc = new NPC(name, hp, init, exp, gear, counter);
		charlist.add(npc);
		sortList(charlist);
		counter=counter+1;
	}

	private void addPlayer() {
		System.out.println();
		System.out.println("What is the name of the Player you wish to create?");
		String name=scanner.next();
		System.out.println();
		System.out.println("The Player has been given the name "+name+" and the ID "+counter+".");
		int hp=askHP(name);
		int init=askInit(name);
		int totExp=askTotExp(name);
		Player player = new Player(name, hp, init, totExp, counter);
		charlist.add(player);
		sortList(charlist);
		counter=counter+1;
	}
	
	/**
	 * 
	 * @param name The name of the character
	 * @return The hp to give the character
	 */
	private int askHP (String name) {
		int choice = 0;
		while (choice<=0){
			System.out.println();
			System.out.println("What is the HP of "+name+"?");
			try {
				choice = scanner.nextInt();
				if (choice>0) {
					System.out.println();
					System.out.println(name+" has been given "+choice+" as HP.");
				}
				else {
					System.out.println();
					System.out.println(name+" is apparently dead. You should give "+name+" a positive number.");
				}
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("Not a number. Please try again.\n");
				scanner.next();
			}
		}
		return choice;
	}
	
	private int askInit (String name) {
		int choice = 0;
		while (choice<=0){
			System.out.println();
			System.out.println("What is the initiative of "+name+"?");
			try {
				choice = scanner.nextInt();
				if (choice>0) {
					System.out.println();
					System.out.println(name+" has been given "+choice+" as initiative.");
				}
				else {
					System.out.println();
					System.out.println(name+" is frozen solid. You should give "+name+" a positive number or consult a doctor.");
				}
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("Not a number. Please try again.\n");
				scanner.next();
			}
		}
		return choice;
	}
	
	private int askExp (String name) {
		int choice = -1;
		while (choice<0){
			System.out.println();
			System.out.println("What is the experience reward of "+name+"?");
			try {
				choice = scanner.nextInt();
				if (choice>=0) {
					System.out.println();
					System.out.println(name+" has been given "+choice+" as its experience reward.");
				}
				else {
					System.out.println();
					System.out.println("Killing "+name+" made you stupider. You should give "+name+" a positive number or 0.");
				}
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("Not a number. Please try again.\n");
				scanner.next();
			}
		}
		return choice;
	}
	
	private int askTotExp (String name) {
		int choice = -1;
		while (choice<0){
			System.out.println();
			System.out.println("What is the current total experience of "+name+"?");
			try {
				choice = scanner.nextInt();
				if (choice>=0){
					System.out.println();
					System.out.println(name+" has been given "+choice+" as its current total experience.");
				}
				else {
					System.out.println();
					System.out.println(name+" is apparently not yet born. You should give "+name+" a positive number or 0.");
				}
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("Not a number. Please try again.\n");
				scanner.next();
			}
		}
		return choice;
	}
	private Character readID () {
		int choice = 0;
		while (true){
			try {
				choice = scanner.nextInt();
				for (int i=0; i<charlist.size(); i++){
					System.out.println();
					if (choice==(charlist.get(i).getCounter())){
						return charlist.get(i);
					}
				}
				System.out.println();
				System.out.println("Invalid ID. Please enter the ID again. You know you want to.\n");
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("Invalid ID. Please enter the ID again. You know you want to.\n");
				scanner.next();
			}
		}
	}
	private void changeHP () {
		String choice= "";
		while (choice.equals("")){
			System.out.println();
			System.out.println("Do you wish to deal damage (type D) or heal (type H)?");
			choice = scanner.next().trim().toUpperCase();
			if (choice.equals("D")){
				System.out.println();
				System.out.println("Who would you like to deal damage to (enter the ID of the character)?");
				Character theChosenOne=readID();
				int damage=-1;
				while (damage == -1) {
					System.out.println();
					System.out.println("How much damage do you wish to deal?");
					try {
						damage=scanner.nextInt();
						if (damage < 0) {
							damage = -1;
							System.out.println("Can't enter negative numbers.");
						}
					} catch (InputMismatchException e) {
						System.out.println();
						System.out.println("Not a number. Please try again.\n");
						scanner.next();
					}
				}
				theChosenOne.changehp(-damage);
				
			}
			else if (choice.equals("H")){
				System.out.println();
				System.out.println("Who would you like to heal (enter the ID of the character)?");
				Character theChosenOne=readID();
				int heal=-1;
				while (heal == -1) {
					System.out.println();
					System.out.println("How much do you wish to heal?");
					try {
						heal=scanner.nextInt();
						if (heal < 0) {
							heal = -1;
							System.out.println("Can't enter negative numbers.");
						}
					} catch (InputMismatchException e) {
						System.out.println();
						System.out.println("Not a number. Please try again.\n");
						scanner.next();
					}
				}
				theChosenOne.changehp(heal);
			}
			else{
				System.out.println();
				System.out.println("Invalid input. Silly you.");
				choice= "";
			}
		}
	}

	private void printWelcome(){
		System.out.println(WELCOMETEXT);
		try {
			System.in.read();
		} catch (IOException e) {
			System.err.println("Nåt sket sig.");
			System.exit(-1);
		}
	}
	
	private void sortList (ArrayList<Character> sortListChar){
		Collections.sort(sortListChar);
	
	}
}
