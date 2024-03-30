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
	
	Scanner keyb = new Scanner(System.in);
	
	public Warrior(int x, int y, int type, char name)
	{
		this.coords[0] = x;
		this.coords[1] = y;
		this.type = type;
		this.name = name;
	}
	
	public void walk(char[][] field, boolean isUsersMove)
	{
		int[] dir = new int[2];
		if (isUsersMove)
		{
			System.out.print("Координаты куда " + this.get_name() + " пойдёт: ");
			dir[0] = keyb.nextInt(); dir[1] = keyb.nextInt();
			while (!(dir[0] < 9 && dir[1] < 6 && Math.abs(this.get_coords()[0]-dir[0]) <= this.get_walkRange() && Math.abs(this.get_coords()[1]-dir[1]) <= this.get_walkRange() && field[dir[0]][dir[1]] == '*'))
			{
				System.out.println("Рекрут не может идти туда, милорд!");
				System.out.print("Координаты куда " + this.get_name() + " пойдёт: ");
				dir[0] = keyb.nextInt(); dir[1] = keyb.nextInt();
			}
		}
		else  // computer's move
		{
			dir[0] = this.get_coords()[0] - (int)(Math.random() * (this.get_walkRange()+1) );
			dir[1] = this.get_coords()[1] + (int)(Math.random() * (2*this.get_walkRange()+1))-this.get_walkRange();
			while (!(dir[0] >= 0 && dir[0] <= 8 && dir[1] <= 5 && dir[1] >= 0 && (field[dir[0]][dir[1]] == '*')))
			{
				dir[0] = this.get_coords()[0] - (int)(Math.random() * (this.get_walkRange()+1) );
				dir[1] = this.get_coords()[1] + (int)(Math.random() * (2*this.get_walkRange()+1))-this.get_walkRange();
			}
			System.out.println(this.get_name()+" moved to "+dir[0]+" "+dir[1]);
		}
		field[this.coords[0]][this.coords[1]] = '*';
		this.coords = dir.clone();
		field[this.coords[0]][this.coords[1]] = this.name;
	}
	
	public void attack(char[][] field, Warrior enemy)
	{
		if (Math.abs(this.get_coords()[0] - enemy.get_coords()[0]) <= this.get_damageRange() && Math.abs(this.get_coords()[1] - enemy.get_coords()[1]) <= this.get_damageRange())
		{
			enemy.set_hp(enemy.get_hp() - this.get_damage());
			System.out.println(enemy.get_name()+" get damaged for "+this.get_damage()+" DPM");
		}
		else
		System.out.println("Рекрут "+this.get_name()+" промахнулся! Слишком далеко!");
	}
	
	public char get_name()
	{
		return this.name;
	}
	
	public int[] get_coords()
	{
		return this.coords;
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
