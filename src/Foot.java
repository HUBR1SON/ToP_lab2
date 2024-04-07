
public class Foot extends Warrior{
	
	public Foot(int x, int y, int type, char name) {
		super(x, y, type, name);
		if (type == 1)
		{
			this.damage = 5;
			this.arm = 5;
		}
		else
		{
			this.damage = 8;
			this.arm = 2;
		}
		this.hp = 10;
		this.damageRange = 2;
		this.walkRange = 1;
		this.hp += this.arm;
	}

}
