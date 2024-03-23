
public class Foot extends Warrior{
	int hp = 10; int damage; int damageRange = 2; int walkRange = 1;
	
	public Foot(int x, int y, int type, char name) {
		super(x, y, type, name);
		if (type == 1)
			this.damage = 5;
		else
			this.damage = 10;
	}

	
	public int get_walkRange()
	{
		return this.walkRange;
	}
	
	public int get_hp()
	{
		return this.hp;
	}
	
	public int get_damage()
	{
		return this.damage;
	}
	
	public int get_damageRange()
	{
		return this.damageRange;
	}


}
