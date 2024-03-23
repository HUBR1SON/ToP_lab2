import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;


public class Game 
{
	public Scanner keyb = new Scanner(System.in);
	private boolean gameover = false;
	ArrayList<Warrior> units = new ArrayList<Warrior>();
	public char[][] field = {{'*','*','*','*','*','*'},
					   		 {'*','*','*','*','#','#'},
							 {'*','*','*','*','#','*'},
							 {'*','*','*','*','*','*'},
							 {'*','*','*','*','*','*'},
							 {'*','*','*','#','#','*'},
							 {'*','*','*','*','*','*'},
							 {'*','*','*','*','*','*'},
							 {'*','*','*','*','*','*'}};
	
	
	public boolean isGameover()
	{
		return this.gameover;
	}
	
	public void game_greeting()
	{
		System.out.println("Приветствую тебя, государь! На твою землю ступили чужестранцы!");
		System.out.println("Защити свой народ, свою землю и честь любой ценой!");
		System.out.println("В казне осталось 50 золотых. Цены на войнов таковы:");
		System.out.println("мечник - 10 зол.(10HP/5 5D)		топорщик - 15 зол.(10HP/5 10Dam)");
		System.out.println("лучник - 15	зол.(10HP/5 5Dam)	арбалетчик - 20");
		System.out.println("всадник (меч) - 20		всадник (лук) - 25");
		this.buy_units();
		this.spawn_enemies();
		this.update_field();
	}
	
	public void buy_units()
	{
		int gold = 50;
		String unit_name;
		int [] coords = new int[2];
		for (int i = 0; i < 3; i++)
		{
			System.out.print("Каких рекрутов нанимаем, милорд? (f1, f2, a1, a2, r1, r2): ");
			unit_name = keyb.next();
			switch (unit_name.charAt(0))
			{
			case 'f': 
				if((unit_name.equals("f1") && gold >= 10) || (unit_name.equals("f2") && gold >= 15))
				{
					System.out.print("Введите координаты вашего война: ");
					coords[0] = keyb.nextInt();
					coords[1] = keyb.nextInt();
					this.units.add(new Foot(coords[0], coords[1], Character.getNumericValue(unit_name.charAt(1)), (char)(i+49)));
				}
				break;
				
			case 'a': 
				if((unit_name.equals("a1") && gold >= 10) || (unit_name.equals("a2") && gold >= 15))
				{
					System.out.print("Введите координаты вашего война: ");
					coords[0] = keyb.nextInt();
					coords[1] = keyb.nextInt();
					this.units.add(new Archer(coords[0], coords[1], Character.getNumericValue(unit_name.charAt(1)), (char)(i+49)));
				}
				break;
				
			case 'r': 
				if((unit_name.equals("r1") && gold >= 10) || (unit_name.equals("r2") && gold >= 15))
				{
					System.out.print("Введите координаты вашего война: ");
					coords[0] = keyb.nextInt();
					coords[1] = keyb.nextInt();
					this.units.add(new Rider(coords[0], coords[1], Character.getNumericValue(unit_name.charAt(1)), (char)(i+49)));
				}
				break;
			}
		}
	}
	
	public void spawn_enemies()
	{
		int [] coords = new int[2];
		int type;
		char[] names = {'a', 'b', 'c'};
		for (int i = 0; i < 3; i++)
		{
			type = (int)(Math.random() * 2) + 1;
			coords[0] = (int)(Math.random() * 3) + 5;
			coords[1] = (int)(Math.random() * 6);
			switch ((int)(Math.random() * 3) + 1)
			{
			case 1:
				this.units.add(new Foot(coords[0], coords[1], type, names[i]));
				break;
			case 2:
				this.units.add(new Archer(coords[0], coords[1], type, names[i]));
				break;
			case 3:
				this.units.add(new Rider(coords[0], coords[1], type, names[i]));
				break;
			}
		}
	}
	
	public void update_field()
	{	
		Warrior unit;
		int [] coords;
		for (int i = 0; i < this.units.size(); i++)
		{
			unit = this.units.get(i);
			coords = unit.get_coords();
			this.field[coords[0]][coords[1]] = unit.get_name();
		}
	}
	
	public void print_field()
	{
		for (int i = 0; i < this.field.length; i++)
		{
			System.out.print(i);
			System.out.print(" | ");
			for (int j = 0; j < this.field[i].length; j++)
			{
				System.out.print(this.field[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("  ‾‾‾‾‾‾‾‾‾‾‾‾‾");
		System.out.println("    0 1 2 3 4 5");
	}
	
	public void make_move()
	{
		int unit;
		char action;
		boolean isMoved = false;
		char enemy;
		
		System.out.print("Действие рекрута [a - attack][w - walk][i - info]: ");
		action = keyb.next().charAt(0);
		while ((action != 'a' && action != 'w') || action == 'i')  
		{
			if (action == 'i')
				this.show_info();
			System.out.print("Корректное действие рекрута [a - attack][w - walk][i - info]: ");
			action = keyb.next().charAt(0);
		}
		this.print_field();
		System.out.print("Имя рекрута для приказа: ");
		unit = keyb.nextInt();
		
		if (action == 'w')
			this.units.get(unit-1).walk(this.field);
	}
	
	public void show_info()
	{
		Warrior unit;
		for (int i = 0; i < this.units.size(); i++)
		{
			unit = this.units.get(i);
			System.out.println(unit.get_name()+": "+unit.get_hp()+"HP "+unit.get_damage()+"DPM "+unit.get_damageRange()+"DFor "+unit.get_walkRange()+"WFor");
		}
	}
}
