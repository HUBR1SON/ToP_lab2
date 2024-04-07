
public class Rider extends Warrior {
	
	public Rider(int x, int y, int type, char name) {
		super(x, y, type, name);
		if (type == 1)
			this.damageRange = 3;  
		else
			this.damageRange = 4; 
		this.hp = 15;
		this.arm = 3;
		this.damage = 5;
		this.walkRange = 3;
		this.hp += this.arm;
	}
	
	
}
