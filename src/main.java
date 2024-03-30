
public class main {
	public static void main(String args[]) {
		Game game = new Game();
		game.game_greeting();
		game.update_field();
		game.print_field();
		while (true)
		{
			game.make_move();
			if (game.isGameover() == 1)
			{
				System.out.println("You Win!");
				break;
			}
			game.computer_move();
			game.update_field();
			game.print_field();
			if (game.isGameover() == -1)
			{
				System.out.println("You Lose!");
				break;
			}
		}
	}
}
