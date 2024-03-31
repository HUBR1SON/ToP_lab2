
public class Archer extends Warrior{
	int hp = 10; int arm = 3; int damage = 5; int damageRange; int walkRange = 2;
	
	public Archer(int x, int y, int type, char name) {
		super(x, y, type, name);
		if (type == 1)
			this.damageRange = 3;  
		else
			this.damageRange = 4;  
		this.hp += this.arm;

	}

	public int getWalkRange()
	{
		return this.walkRange;
	}
	
	public void setWalkRange(int new_walkRange)
	{
		this.walkRange = new_walkRange;
	}
	
	public int getHp()
	{
		return this.hp;
	}
	
	public void setHp(int new_hp)
	{
		this.hp = new_hp;
	}
	
	public int getArm()
	{
		return this.arm;
	}
	
	public int getDamage()
	{
		return this.damage;
	}
	
	public int getDamageRange()
	{
		return this.damageRange;
	}
	
}
