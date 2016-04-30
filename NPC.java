
public class NPC extends Character {

	private int exp;
	private String gear;
	
	public NPC(String n, int m, int i, int e, String g, int c) {
		super(n, m, i, c);
		exp=e;
		gear=g;
	}
	
	public int getExp (){
		return exp;
	}
	
	public String getGear (){
		return gear;
	}
}
