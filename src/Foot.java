
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

	
	public int get_walkRange()
	{
		return this.walkRange;
	}
	
	public int get_hp()
	{
		return this.hp;
	}
	
	public void set_hp(int new_hp)
	{
		this.hp = new_hp;
	}
	
	public int get_arm()
	{
		return this.arm;
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
