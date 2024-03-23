
public class Rider extends Warrior {
	int hp = 15; int damage = 5; int damageRange; int walkRange = 3;
	
	public Rider(int x, int y, int type, char name) {
		super(x, y, type, name);
		if (type == 1)
			this.damageRange = 3;  // 2^2 + 2^2
		else
			this.damageRange = 4;  // 3^2 + 3^2
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
