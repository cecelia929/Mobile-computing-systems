 import java.util.Random;
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
public class NimAIPlayer extends NimPlayer implements Testable {
	/**
	 * extends the constructor of NimPlayer
	 * 
	 * @param userName
	 * @param family_name
	 * @param given_name
	 * @param numOfGames
	 * @param numOfWins
	 */
	
	public NimAIPlayer(String userName, String family_name, String given_name, int numOfGames, int numOfWins) {
		super(userName, family_name, given_name, numOfGames, numOfWins);

	}

	/**
	 * return the number of remove in common game, and two victory strategy
	 */
	public int removeStone(int whoseTurn, int numberToRemove, int upperBound, int currentStone, int initialStones,
			Scanner scanner) {
		int m = 0;
		int temp = 0;
		int MINMOVE = 1;
		if (upperBound <= currentStone) {
			m = upperBound;
		} else if (upperBound > currentStone) {
			m = currentStone;
		}
		if (currentStone == MINMOVE) {
			numberToRemove = MINMOVE;
		} else {
			if (whoseTurn == 0 && (((initialStones - 1) % (m + 1)) != 0)) {
				for (temp = 1; temp <= m; temp++) {
					if (((currentStone - temp - 1) % (m + 1)) == 0) {
						numberToRemove = temp;
					}
				}
			} else if (whoseTurn == 1 && (((initialStones - 1) % (m + 1)) == 0)) {
				for (temp = 1; temp <= m; temp++) {
					if (((currentStone - temp - 1) % (m + 1)) == 0) {
						numberToRemove = temp;
					}
				}
			} else {
				numberToRemove = new Random().nextInt(m) + 1;
			}
		}
		return numberToRemove;
	}

	// Testable
	public NimAIPlayer() {

	}

	/**
	 * this method is used to return the value of mvoe which is a victory strategy
	 * in adcanced game
	 */
	public String advancedMove(boolean[] available, String lastMove) {
		// the implementation of the victory
		// guaranteed strategy designed by you
		int position;
		String move = "";
		int MAXMOVE = 2;
		int MINMOVE = 1;
		if (available.length % 2 == 0) {
			if (available[(available.length) / 2]) {
				position = (available.length) / 2;
				move = position + " " + MAXMOVE;
			} else {
				String[] moveInfo = lastMove.split(" ");
				position = Integer.parseInt(moveInfo[0]);
				int numOfMove = Integer.parseInt(moveInfo[1]);
				position = (available.length + 1) - position;
				if (Integer.parseInt(moveInfo[1]) == MAXMOVE) {
					move = position - 1 + " " + numOfMove;
				} else if (Integer.parseInt(moveInfo[1]) == MINMOVE) {
					move = position + " " + numOfMove;
				}
			}
		} else if (available.length % 2 != 0) {
			if (available[(available.length -1) / 2]) {
				position = (available.length + 1) / 2;
				move = position + " " + MINMOVE;
			} 
			else {
				String[] moveInfo = lastMove.split(" ");
				position = Integer.parseInt(moveInfo[0]);
				int numOfMove = Integer.parseInt(moveInfo[1]);
				position = (available.length + 1) - position;
				if (Integer.parseInt(moveInfo[1]) == MAXMOVE) {
					move = position - 1 + " " + numOfMove;
				} else if (Integer.parseInt(moveInfo[1]) == MINMOVE) {
					move = position + " " + numOfMove;
				}
			}
		}

		return move;

	}

	/**
	 * this method is used to return the value of move in advanced game including
	 * the victory strategy and the common situation
	 */
	public String whichToMove(boolean[] available, String lastMove, int whoseTurn, Scanner scanner) {
		String move = "";
		int MINMOVE = 1;
		int position = 0;
		if (whoseTurn == 0) {
			move = advancedMove(available, lastMove);
		}
		if (whoseTurn == 1) {
			for (int i = 0; i < available.length; i++) {
				if (available[i]) {
					position = i + 1;
				}
			}
			move = position + " " + MINMOVE;
		}
		return move;
	}

}
