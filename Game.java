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
	
	
	public int isGameover()
	{
		int count = 0;
		for(int i = 0; i < 3; i++)
			if (this.units.get(i).get_hp() <= 0)
				count += 1;
		if (count == 3)
			return -1;  // player lose
		
		count = 0;
		for(int i = 3; i < 6; i++)
			if (this.units.get(i).get_hp() <= 0)
				count += 1;
		if (count == 3)
			return 1;  // computer lose
		return 0;  // game is not over;
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
	}
	
	public void buy_units()
	{
		Map<String,Integer> unit_costs = new HashMap<String,Integer>();
		unit_costs.put("f1", 10); unit_costs.put("f2", 15); unit_costs.put("a1", 15);
		unit_costs.put("a2", 20); unit_costs.put("r1", 20); unit_costs.put("r2", 25);
		int gold = 50;
		int i = 0;
		String unit_name;
		int [] coords = new int[2];
		while (i < 3)
		{
			System.out.print("Казна: "+gold+" Каких рекрутов нанимаем, милорд? (f1, f2, a1, a2, r1, r2): ");
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
				if((unit_name.equals("a1") && gold >= 15) || (unit_name.equals("a2") && gold >= 20))
				{
					System.out.print("Введите координаты вашего война: ");
					coords[0] = keyb.nextInt();
					coords[1] = keyb.nextInt();
					this.units.add(new Archer(coords[0], coords[1], Character.getNumericValue(unit_name.charAt(1)), (char)(i+49)));
				}
				break;
				
			case 'r': 
				if((unit_name.equals("r1") && gold >= 20) || (unit_name.equals("r2") && gold >= 25))
				{
					System.out.print("Введите координаты вашего война: ");
					coords[0] = keyb.nextInt();
					coords[1] = keyb.nextInt();
					this.units.add(new Rider(coords[0], coords[1], Character.getNumericValue(unit_name.charAt(1)), (char)(i+49)));
				}
				break;
			}
			if (this.units.size() == i+1)  // if success purchase
			{
				i += 1;
				gold -= unit_costs.get(unit_name);
			}
		}
	}
	
	
	public void computer_move()
	{
		Warrior unit = this.units.get((int)(Math.random() * 3) + 3);
		while (unit.get_hp() <= 0)
			unit = this.units.get((int)(Math.random() * 3) + 3);
		int typeOfMove = (int)(Math.random() * 2);
		if (typeOfMove == 1)
			unit.walk(this.field, false);
		else
			unit.attack(this.field, this.units.get((int)(Math.random() * 3)));
	}
	
	public void spawn_enemies()
	{
		int [] coords = new int[2];
		int type;
		int i = 0;
		char[] names = {'a', 'b', 'c'};
		boolean pos_is_occuped = true;
		while(i < 3)
		{
			type = (int)(Math.random() * 2) + 1;
			while (pos_is_occuped)
			{
				coords[0] = (int)(Math.random() * 3) + 5;
				coords[1] = (int)(Math.random() * 6);
				pos_is_occuped = false;
				for (int j = 3; j < this.units.size(); j++)
				{
					if (this.units.get(j).get_coords()[0] == coords[0] && this.units.get(j).get_coords()[1] == coords[1])
						pos_is_occuped = true;
				}
			}
			pos_is_occuped = true;
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
			if (this.units.size()-3 == i+1)
			{
				i += 1;
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
			if (unit.get_hp() > 0)
				this.field[coords[0]][coords[1]] = unit.get_name();
			else
				this.field[coords[0]][coords[1]] = '*';
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
		Map<Character,Integer> units = new HashMap<Character,Integer>();
		units.put('1', 0); units.put('2', 1); units.put('3', 2); units.put('a', 3); units.put('b', 4); units.put('c', 5); 
		int unit;
		char action;
		boolean isMoved = false;
		char enemyName;
		
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
		while (unit != 1 && unit != 2 && unit != 3)
		{
			System.out.print("Корректное имя рекрута для приказа: ");
			unit = keyb.nextInt();
		}
		while (this.units.get(unit-1).get_hp() <= 0)
		{
			System.out.print("Корректное имя рекрута для приказа: ");
			unit = keyb.nextInt();
		}
		if (action == 'w')
			this.units.get(unit-1).walk(this.field, true);
		if (action == 'a')
		{	
			System.out.print("Имя цели для атаки: ");
			enemyName = keyb.next().charAt(0);
			this.units.get(unit-1).attack(this.field, this.units.get(units.getOrDefault(enemyName, 1)));
		}
	}
	
	public void show_info()
	{
		Warrior unit;
		for (int i = 0; i < this.units.size(); i++)
		{
			unit = this.units.get(i);
			if (unit.get_hp() > 0)
			System.out.println(unit.get_name()+": "+unit.get_hp()+"HP+ARM "+unit.get_damage()+"DPM "+unit.get_damageRange()+"DFor "+unit.get_walkRange()+"WFor");
			else
			System.out.println(unit.get_name()+" is DEAD");
		}
	}
}
