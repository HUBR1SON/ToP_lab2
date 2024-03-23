import java.util.Scanner;

public class Warrior{
	private int[] coords = new int[2];
	private int type;
	private char name;
	private int hp;
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
	
	public void walk(char[][] field)
	{
		int[] dir = new int[2];
		System.out.print("Координаты куда " + this.get_name() + " пойдёт: ");
		dir[0] = keyb.nextInt(); dir[1] = keyb.nextInt();
		while (!(dir[0] < 9 && dir[1] < 6 && Math.abs(this.get_coords()[0]-dir[0]) <= this.get_walkRange() && Math.abs(this.get_coords()[1]-dir[1]) <= this.get_walkRange()))
		{
			System.out.println("Рекрут не может идти туда, милорд!");
			System.out.print("Координаты куда " + this.get_name() + " пойдёт: ");
			dir[0] = keyb.nextInt(); dir[1] = keyb.nextInt();
		}
		field[this.coords[0]][this.coords[1]] = '*';
		this.coords = dir.clone();
		field[this.coords[0]][this.coords[1]] = this.name;
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
	
	public int get_damage()
	{
		return this.damage;
	}
	
	public int get_damageRange()
	{
		return this.damageRange;
	}
}
