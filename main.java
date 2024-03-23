
public class main {
	public static void main(String args[]) {
		Game game = new Game();
		game.game_greeting();
		game.print_field();
		while (!game.isGameover())
		{
			game.make_move();
			game.update_field();
			game.print_field();
		}
	}
}
