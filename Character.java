
public abstract class Character implements Comparable<Character>{

	private String name;
	private int hp;
	private int maxhp;
	private int init;
	private int counter;
	
	public Character(String n, int m, int i, int c){
		name=n;
		maxhp=m;
		hp=m;
		init=i;
		counter=c;
	}
	
	public String toString(){
		return " " + init + "\t- " + name + " ID: " + counter +" ("+hp+"/"+maxhp+")";
		
	}
	public int compareTo(Character c){
		return c.getInit()-getInit();
	}
	
	public void changehp (int c) {
		hp+=c;
	}
	
	public void changeInit (int i) {
		init+=i;
	}
	
	public String getName (){
		return name;
	}
	
	public int getHP (){
		return hp;
	}
	
	public int getMaxHP (){
		return maxhp;
	}
	
	public int getInit (){
		return init;
	}
	public int getCounter (){
		return counter;
	}
	
}
