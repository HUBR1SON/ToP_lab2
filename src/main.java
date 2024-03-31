
public class main {
	public static void main(String args[]) {
		Game game = new Game();
		game.gameGreeting();
		game.updateField();
		game.printField();
		while (true)
		{
			game.makeMove();
			if (game.isGameOver() == 1)
			{
				System.out.println("You Win!");
				break;
			}
			game.computerMove();
			game.updateField();
			game.wizardMove();
			game.updateField();
			game.printField();
			if (game.isGameOver() == -1)
			{
				System.out.println("You Lose!");
				break;
			}
		}
	}
}
