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
	Wizard wizard = new Wizard((int)(Math.random()*9), (int)(Math.random()*6));
	public char[][] field = {{'*','*','*','*','*','*'},
					   		 {'*','*','*','*','#','#'},
							 {'@','*','*','*','#','*'},
							 {'*','*','*','*','*','*'},
							 {'@','@','@','@','@','@'},
							 {'*','*','*','#','#','*'},
							 {'*','*','*','*','*','*'},
							 {'*','*','*','@','*','*'},
							 {'*','*','*','*','*','*'}};
	
	
	public int isGameOver()
	{
		int count = 0;
		for(int i = 0; i < 3; i++)
			if (this.units.get(i).getHp() <= 0)
				count += 1;
		if (count == 3)
			return -1;  // player lose
		
		count = 0;
		for(int i = 3; i < 6; i++)
			if (this.units.get(i).getHp() <= 0)
				count += 1;
		if (count == 3)
			return 1;  // computer lose
		return 0;  // game is not over
	}
	
	public void gameGreeting()
	{
		System.out.println("Приветствую тебя, государь! На твою землю ступили чужестранцы!");
		System.out.println("Защити свой народ, свою землю и честь любой ценой!");
		System.out.println("В казне осталось 50 золотых. Цены на войнов таковы:");
		System.out.println("мечник - 10 зол.(10HP/5 5D/2ran 1Wran)		топорщик - 15 зол.(10HP/5 10Dam/2ran 1Wran)");
		System.out.println("лучник - 15	зол.(10HP/5 5Dam/3ran 2Wran)	арбалетчик - 20 зол.(10HP/5 5Dam/4ran 2Wran");
		System.out.println("всадник (меч) - 20 зол.(15HP/3 5Dam/3ran 4Wran)		всадник (лук) - 25 зол.(15HP/3 5Dam/4ran 4Wran)	");
		this.buyUnits();
		this.spawnEnemies();
	}
	
	public void buyUnits()
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
	
	
	public void computerMove()
	{
		Warrior unit = this.units.get((int)(Math.random() * 3) + 3);
		Warrior unitToAttack;
		int minDistance;
		int minDistId = -1;
		while (unit.getHp() <= 0)
			unit = this.units.get((int)(Math.random() * 3) + 3);

		minDistance = (int)(Math.pow(this.field.length, 2) + Math.pow(this.field.length, 2));
		for (int i = 0; i < 3; i++)
		{
			if (this.units.get(i).getHp() <= 0)
				continue;
			if (minDistance > (int)(Math.pow(this.units.get(i).getCoords()[0] - unit.getCoords()[0], 2) + Math.pow(this.units.get(i).getCoords()[1] - unit.getCoords()[1], 2)))
			{
				minDistId = i;
				minDistance = (int)(Math.pow(this.units.get(i).getCoords()[0] - unit.getCoords()[0], 2) + Math.pow(this.units.get(i).getCoords()[1] - unit.getCoords()[1], 2));
			}
		}
		if ((this.units.get(minDistId).getCoords()[0] <= unit.getCoords()[0]+unit.getDamageRange()) && (this.units.get(minDistId).getCoords()[0] >= unit.getCoords()[0]-unit.getDamageRange()) && (this.units.get(minDistId).getCoords()[1] <= unit.getCoords()[1]+unit.getDamageRange()) && (this.units.get(minDistId).getCoords()[1] >= unit.getCoords()[1]-unit.getDamageRange()))
		{
			unitToAttack = this.units.get(minDistId);
			unit.attack(this.field, unitToAttack);
		}
		else
			unit.compWalk(this.field);

	}
	
	public void wizardMove()
	{
		switch ((int)(Math.random()*2))
		{
		case 0:
			wizard.wizardWalk(field);
			break;
		case 1:
			wizard.wizardThrow(this.units);
			break;
		}
		wizard.poisonDamage(units);
	}
	
	public void spawnEnemies()
	{
		int [] coords = new int[2];
		int type;
		int i = 0;
		char[] names = {'a', 'b', 'c'};
		boolean posIsOccuped = true;
		while(i < 3)
		{
			type = (int)(Math.random() * 2) + 1;
			while (posIsOccuped)
			{
				coords[0] = (int)(Math.random() * 3) + 5;
				coords[1] = (int)(Math.random() * 6);
				posIsOccuped = false;
				for (int j = 3; j < this.units.size(); j++)
				{
					if (this.units.get(j).getCoords()[0] == coords[0] && this.units.get(j).getCoords()[1] == coords[1])
						posIsOccuped = true;
				}
			}
			posIsOccuped = true;
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
	
	public void updateField()
	{	
		Warrior unit;
		int [] coords;
		for (int i = 0; i < this.units.size(); i++)
		{
			unit = this.units.get(i);
			coords = unit.getCoords();
			if (unit.getHp() > 0)
				this.field[coords[0]][coords[1]] = unit.getName();
			else
				this.field[coords[0]][coords[1]] = 'x';
		}
		unit = this.wizard;
		coords = unit.getCoords();
		this.field[coords[0]][coords[1]] = unit.getName();
	}
	
	public void printField()
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
	
	public void makeMove()
	{
		Map<Character,Integer> units = new HashMap<Character,Integer>();
		units.put('1', 0); units.put('2', 1); units.put('3', 2); units.put('a', 3); units.put('b', 4); units.put('c', 5); 
		int unit;
		char action;
		char enemyName;
		
		System.out.print("Действие рекрута [a - attack][w - walk][i - info]: ");
		action = keyb.next().charAt(0);
		while ((action != 'a' && action != 'w') || action == 'i')
		{
			if (action == 'i')
				this.showInfo();
			System.out.print("Корректное действие рекрута [a - attack][w - walk][i - info]: ");
			action = keyb.next().charAt(0);
		}
		this.printField();
		System.out.print("Имя рекрута для приказа: ");
		unit = keyb.nextInt();
		while (unit != 1 && unit != 2 && unit != 3)
		{
			System.out.print("Корректное имя рекрута для приказа: ");
			unit = keyb.nextInt();
		}
		while (this.units.get(unit-1).getHp() <= 0)
		{
			System.out.print("Корректное имя рекрута для приказа: ");
			unit = keyb.nextInt();
		}
		if (action == 'w')
			this.units.get(unit-1).walk(this.field);
		if (action == 'a')
		{	
			System.out.print("Имя цели для атаки: ");
			enemyName = keyb.next().charAt(0);
			this.units.get(unit-1).attack(this.field, this.units.get(units.getOrDefault(enemyName, 1)));
		}
	}
	
	public void showInfo()
	{
		Warrior unit;
		for (int i = 0; i < this.units.size(); i++)
		{
			unit = this.units.get(i);
			if (unit.getHp() > 0)
			System.out.println(unit.getName()+": "+unit.getHp()+"HP+ARM "+unit.getDamage()+"DPM "+unit.getDamageRange()+"DFor "+unit.getWalkRange()+"WFor");
			else
			System.out.println(unit.getName()+" is DEAD");
		}
	}
}
