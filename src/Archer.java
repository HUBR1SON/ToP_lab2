
public class Archer extends Warrior{
	
	public Archer(int x, int y, int type, char name) {
		super(x, y, type, name);
		if (type == 1)
			this.damageRange = 3;  
		else
			this.damageRange = 4;  
		this.hp = 10;
		this.arm = 3;
		this.damage = 5;
		this.walkRange = 2;
		this.hp += this.arm;
	}

	

}
