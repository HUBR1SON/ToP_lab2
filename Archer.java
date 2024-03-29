
public class Archer extends Warrior{
	int hp = 10; int arm = 3; int damage = 5; int damageRange; int walkRange = 2;
	
	public Archer(int x, int y, int type, char name) {
		super(x, y, type, name);
		if (type == 1)
			this.damageRange = 3;  // 2^2 + 2^2
		else
			this.damageRange = 4;  // 3^2 + 3^2
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
