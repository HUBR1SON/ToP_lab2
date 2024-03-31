import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Warrior{
	private int[] coords = new int[2];
	private int type;
	private char name;
	private int hp;
	private int arm;
	private int damage;
	private int damageRange;
	private int walkRange;
	private int effectedMoves = 0;
	
	Scanner keyb = new Scanner(System.in);
	
	public Warrior(int x, int y, int type, char name)
	{
		this.coords[0] = x;
		this.coords[1] = y;
		this.type = type;
		this.name = name;
	}
	
	public void compWalk(char[][] field)
	{
		int[] dir = new int[2];
		dir[0] = this.getCoords()[0] - (int)(Math.random() * (this.getWalkRange()+1) );
		dir[1] = this.getCoords()[1] + (int)(Math.random() * (2*this.getWalkRange()+1))-this.getWalkRange();
		while (!(dir[0] >= 0 && dir[0] <= 8 && dir[1] <= 5 && dir[1] >= 0 && (field[dir[0]][dir[1]] == '*' || field[dir[0]][dir[1]] == '@')))
		{
			dir[0] = this.getCoords()[0] - (int)(Math.random() * (this.getWalkRange()+1) );
			dir[1] = this.getCoords()[1] + (int)(Math.random() * (2*this.getWalkRange()+1))-this.getWalkRange();
		}
		System.out.println(this.getName()+" moved to "+dir[0]+" "+dir[1]);
		
		this.effectedMoves -= 1;
		if (field[dir[0]][dir[1]] == '@')
		{
			this.setWalkRange(this.getWalkRange()-1);
			this.effectedMoves = 1;
		}
		if (this.effectedMoves == 0)
		{
			this.setWalkRange(this.getWalkRange()+1);
		}
		field[this.getCoords()[0]][this.getCoords()[1]] = '*';
		this.coords = dir.clone();
		field[this.getCoords()[0]][this.getCoords()[1]] = this.name;
	}
	
	public void walk(char[][] field)
	{
		
		int[] dir = new int[2];
		System.out.print("Координаты куда " + this.getName() + " пойдёт: ");
		dir[0] = keyb.nextInt(); dir[1] = keyb.nextInt();
		while (!(dir[0] < 9 && dir[1] < 6 && Math.abs(this.getCoords()[0]-dir[0]) <= this.getWalkRange() && Math.abs(this.getCoords()[1]-dir[1]) <= this.getWalkRange() && (field[dir[0]][dir[1]] == '*' || field[dir[0]][dir[1]] == '@')))
		{
			System.out.println("Рекрут не может идти туда, милорд!");
			if (this.getWalkRange() == 0)
			{
				this.setWalkRange(1);
				return;
			}
			System.out.print("Координаты куда " + this.getName() + " пойдёт: ");
			dir[0] = keyb.nextInt(); dir[1] = keyb.nextInt();
		}

		this.effectedMoves -= 1;
		if (field[dir[0]][dir[1]] == '@')
		{
			this.setWalkRange(this.getWalkRange()-1);
			this.effectedMoves = 1;
		}
		if (this.effectedMoves == 0)
		{
			this.setWalkRange(this.getWalkRange()+1);
		}
		field[this.coords[0]][this.coords[1]] = '*';
		this.coords = dir.clone();
		field[this.coords[0]][this.coords[1]] = this.name;
	}
	
	public void attack(char[][] field, Warrior enemy)
	{
		if (Math.abs(this.getCoords()[0] - enemy.getCoords()[0]) <= this.getDamageRange() && Math.abs(this.getCoords()[1] - enemy.getCoords()[1]) <= this.getDamageRange())
		{
			enemy.setHp(enemy.getHp() - this.getDamage());
			System.out.println(enemy.getName()+" get damaged for "+this.getDamage()+" DPM");
		}
		else
		System.out.println("Рекрут "+this.getName()+" промахнулся! Слишком далеко!");
	}
	
	public char getName()
	{
		return this.name;
	}
	
	public int[] getCoords()
	{
		return this.coords;
	}
	
	public void setCoords(int x, int y)
	{
		this.coords[0] = x;
		this.coords[1] = y;
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
