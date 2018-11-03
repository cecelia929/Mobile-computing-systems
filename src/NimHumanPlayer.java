import java.util.Scanner;

/**
 * The university of Melbourne
 * 
 * COMP90041 Programming and Software Development
 * 
 * Project C
 * 
 * Major: Master of Information Technology - computing
 * 
 * Student Name: Yajing Huang
 * 
 * Student Number: 896243
 */
public class NimHumanPlayer extends NimPlayer {
	/**
	 * extends the constructor of NimPlayer
	 * @param userName
	 * @param family_name
	 * @param given_name
	 * @param numOfGames
	 * @param numOfWins
	 */
	public NimHumanPlayer(String userName, String family_name, String given_name, int numOfGames, int numOfWins) {

		super(userName, family_name, given_name, numOfGames, numOfWins);
	}

	/**
	 * return the number of remove in common game
	 */
	public int removeStone(int whoseTurn, int numberToRemove, int upperBound, int currentStone, int initialStones,
			Scanner scanner) {

		numberToRemove = scanner.nextInt();// get the number to remove by scanner

		return numberToRemove;
	}

	/**
	 * return the value of move in advanced game
	 */
	public String whichToMove(boolean[] available, String lastMove, int whoseTurn, Scanner scanner) {
		String move = scanner.nextLine();// get the value of move by scanner

		return move;

	}
}
