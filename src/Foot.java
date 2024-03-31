
public class Foot extends Warrior{
	int hp = 10; int arm; int damage; int damageRange = 2; int walkRange = 1;
	
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
