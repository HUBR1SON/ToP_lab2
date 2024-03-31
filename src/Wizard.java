import java.util.ArrayList;

public class Wizard extends Warrior{
	private int walkRange = 1;
	private int damage = 1;
	private int damageRange = 3;
	private int [] damageList = new int[6];
	
	public Wizard(int x, int y)
	{
		super(x, y, -1, 'w');
		
	}
	
	public void wizardWalk(char field[][])
	{
		int[] dir = new int[2];
		dir[0] = this.getCoords()[0] + (int)(Math.random() * (2*this.getWalkRange()+1))-this.getWalkRange();
		dir[1] = this.getCoords()[1] + (int)(Math.random() * (2*this.getWalkRange()+1))-this.getWalkRange();
		while (!(dir[0] >= 0 && dir[0] <= 8 && dir[1] <= 5 && dir[1] >= 0))
		{
			dir[0] = this.getCoords()[0] + (int)(Math.random() * (2*this.getWalkRange()+1))-this.getWalkRange();
			dir[1] = this.getCoords()[1] + (int)(Math.random() * (2*this.getWalkRange()+1))-this.getWalkRange();
		}
		System.out.println(this.getName()+" moved to "+dir[0]+" "+dir[1]);
		
		field[this.getCoords()[0]][this.getCoords()[1]] = '*';
		this.setCoords(dir[0], dir[1]);
		field[this.getCoords()[0]][this.getCoords()[1]] = this.getName();
	}
	
	public void wizardThrow(ArrayList<Warrior> units)
	{
		int x = (int)(Math.random()*7)+1;
		int y = (int)(Math.random()*4)+1;
		Warrior unit;
		System.out.println("Wizard has thrown the potion to "+x+" "+y+" "+this.getDamageRange()+"x"+this.getDamageRange());
		for (int i = 0; i < units.size(); i++)
		{
			unit = units.get(i);
			if ((unit.getCoords()[0] <= x+this.getDamageRange()) && (unit.getCoords()[0] >= x-this.getDamageRange()) && (unit.getCoords()[1] <= y+this.getDamageRange()) && (unit.getCoords()[1] >= y-this.getDamageRange()))
			{
				if (unit.getHp() > 0)
				{
					System.out.println(unit.getName()+" has been poisoned");
					this.damageList[i] += 3;
				}
				else
				{
					System.out.println(unit.getName()+" has been revieved");
					unit.setHp(10);
				}
			}
		}
	}
	
	public void poisonDamage(ArrayList<Warrior> units)
	{
		for (int i = 0; i < this.damageList.length; i++)
		{
			if (this.damageList[i] > 0)
			{
				units.get(i).setHp(units.get(i).getHp()-this.getDamage());
				this.damageList[i]--;
			}
		}
	}
	
	public int getWalkRange()
	{
		return this.walkRange;
	}
	
	public int getDamageRange()
	{
		return this.damageRange;
	}
	
	public int getDamage()
	{
		return this.damage;
	}
}

