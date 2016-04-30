
public class Player extends Character {
	
	private int totExp;
	
	public Player(String n, int m, int i, int t, int c) {
		super(n, m, i, c);
		totExp=t;
	}
	
	public int getTotExp (){
		return totExp;
	}
	
	public void addExp (int t){
		totExp=totExp+t;
	}
	
	public void setExp (int t){
		totExp=t;
	}
	
}
