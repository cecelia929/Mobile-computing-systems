
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

public class NimGame {

	// private NimPlayer nimPlayer;
	private static int currentStone;// this variale stands for current number of stones
	private static int currentAdvancedStone;

	/**
	 * constructor
	 */
	public NimGame() {

	}

	/**
	 * start game
	 * 
	 * @param initialStones
	 * @param upperBound
	 * @param userName1
	 * @param userName2
	 * @param scanner
	 */
	public void startgame(int initialStones, int upperBound, NimPlayer player1, NimPlayer player2, Scanner scanner) {
		// this method is used to start common game
		int whoseTurn = 0;// A variable to judge it is whose turn to move stone
		currentStone = initialStones;// at the start of the game, the number of current stones equals the number of
										// initial stones
		System.out.println();
		System.out.println("Initial stone count: " + initialStones);
		System.out.println("Maximum stone removal: " + upperBound);
		System.out.println("Player 1: " + player1.getgiven_name() + " " + player1.getfamily_name());
		player1.setnumOfGames(player1.getnumOfGames() + 1);// every time when the game start, the number of game of
															// these two players add 1
		System.out.println("Player 2: " + player2.getgiven_name() + " " + player2.getfamily_name());
		player2.setnumOfGames(player2.getnumOfGames() + 1);
		System.out.println();

		/**
		 * this while loop is used to judge whether the game is over or not
		 */
		while (currentStone > 0) {
			int numberToRemove = 0;
			/**
			 * this "if-else if" part aims to judge it is who's turn to remove stones
			 */
			if (whoseTurn == 0) {
				System.out.print(currentStone + " stones left:");
				printStones(currentStone);// print the current stone with *
				System.out.println(player1.getgiven_name() + "'s turn - remove how many?");

				/**
				 * this part is used to check valid move by comparing the move to the smaller
				 * one between current number of stones and the upper bound for one move
				 */
				try {
					numberToRemove = player1.removeStone(whoseTurn, numberToRemove, upperBound, currentStone,
							initialStones, scanner);
					System.out.print("\n");

					if (numberToRemove >= 1 && numberToRemove <= upperBound && numberToRemove <= currentStone) {
						currentStone = currentStone(initialStones, numberToRemove, upperBound);// get the number of
																								// current stones
						whoseTurn = 1;
					} else if (numberToRemove <= 0 || numberToRemove > upperBound || numberToRemove > currentStone)
						throw new Exception();

				} catch (Exception e) {
					if (currentStone >= upperBound) {
						System.out.println("Invalid move. You must remove between 1 and " + upperBound + " stones");
						System.out.print("\n");
						whoseTurn = 0;// if there is an exception, the move turn turn should be back to who make this
										// exception
					}

					if (currentStone < upperBound) {

						System.out.println("Invalid move. You must remove between 1 and " + currentStone + " stones");
						System.out.print("\n");
						whoseTurn = 0;
					}
					scanner.nextLine();
				}

			}
			/**
			 * the aim of this part is same as whoseTurn == 0, it stands for the player2's
			 * behavior during the game
			 */
			else if (whoseTurn == 1) {
				System.out.print(currentStone + " stones left:");
				printStones(currentStone);
				System.out.println(player2.getgiven_name() + "'s turn - remove how many?");
				/**
				 * this part is used to check valid move by comparing the move to the smaller
				 * one between current number of stones and the upper bound for one move
				 */
				try {
					numberToRemove = player2.removeStone(whoseTurn, numberToRemove, upperBound, currentStone,
							initialStones, scanner);
					System.out.print("\n");
					if (numberToRemove >= 1 && numberToRemove <= upperBound && numberToRemove <= currentStone) {
						currentStone = currentStone(initialStones, numberToRemove, upperBound);// get the number of
																								// stones
						whoseTurn = 0;
					} else

					if (numberToRemove <= 0 || numberToRemove > upperBound || numberToRemove > currentStone)
						throw new Exception();

				} catch (Exception e) {
					if (currentStone >= upperBound) {
						System.out.println("Invalid move. You must remove between 1 and " + upperBound + " stones");
						System.out.print("\n");
						whoseTurn = 1;// if there is an exception, the move turn turn should be back to who make this
										// exception
					}

					if (currentStone < upperBound) {

						System.out.println("Invalid move. You must remove between 1 and " + currentStone + " stones");
						System.out.print("\n");
						whoseTurn = 1;
					}
					scanner.nextLine();
				}

			}
		}

		System.out.println("Game Over");

		/**
		 * this "if-else if" aims to judge who is the winner
		 */
		if (whoseTurn == 0) {
			int numOfWins1 = player1.getnumOfWins();

			System.out.println(player1.getgiven_name() + " " + player1.getfamily_name() + " wins!");
			// numOfWins++;
			player1.setnumOfWins(numOfWins1 + 1);// if this player wins, then his/her/its number of wins should add 1

		} else if (whoseTurn == 1) {
			int numOfWins2 = player2.getnumOfWins();

			System.out.println(player2.getgiven_name() + " " + player2.getfamily_name() + " wins!");
			player2.setnumOfWins(numOfWins2 + 1);

		}
	}

	@SuppressWarnings("null")
	public void startadvancedgame(int initialStones, NimPlayer player1, NimPlayer player2, Scanner scanner) {
		// this method is used to start the advanced game
		boolean[] available = new boolean[initialStones];// this boolean array is uesd to store whether the stone in
															// position i+1 is available or not
		int whoseTurn = 0;// A variable to judge it is whose turn to move stone
		int MAXMOVE = 2;
		int MINMOVE = 1;
		String lastMove = "";
		String move = "";
		currentAdvancedStone = initialStones;
		System.out.println();
		System.out.println("Initialstone count: " + initialStones);
		for (int i = 0; i < initialStones; i++) {
			available[i] = true;// at the beginning, the value of this array is all true
		}
		System.out.print("Stones display:");
		printAdvancedStones(available);// print advanced stones
		System.out.println("Player 1: " + player1.getgiven_name() + " " + player1.getfamily_name());
		player1.setnumOfGames(player1.getnumOfGames() + 1);
		System.out.println("Player 2: " + player2.getgiven_name() + " " + player2.getfamily_name());
		player2.setnumOfGames(player2.getnumOfGames() + 1);
		System.out.println();

		/**
		 * this while loop is used to judge whether the game is over or not
		 */

		while (currentAdvancedStone > 0) {

			/**
			 * this "if-else if" part aims to judge it is who's turn to remove stones
			 */

			if (whoseTurn == 0) {
				try {
					System.out.print(currentAdvancedStone + " stones left:");
					printAdvancedStones(available);
					System.out.println(player1.getgiven_name() + "'s turn - which to remove?");
					move = player1.whichToMove(available, lastMove, whoseTurn, scanner);
					// use player to call the method in NimAIPlayer or NimHumanPlayer to get the
					// value of move
					String[] moveInfo = move.split(" ");
					if (Integer.parseInt(moveInfo[1]) != MAXMOVE && Integer.parseInt(moveInfo[1]) != MINMOVE) {
						try {
							throw new Exception();
						} catch (Exception e) {
							System.out.println("Invalid Input: the number of stones should be either 1 or 2");
							System.out.print("\n");
							whoseTurn = 0;
						}
					}

					else if (Integer.parseInt(moveInfo[0]) < 1 || Integer.parseInt(moveInfo[0]) > available.length) {
						try {
							throw new Exception();
						} catch (Exception e) {
							System.out.println(
									"Invalid Input: the number of position should form 1 to " + available.length);
							System.out.print("\n");
							whoseTurn = 0;
							// if there is an exception, the turn to move stone has to be return who makes
							// the exception
							
						}
					} else if (Integer.parseInt(moveInfo[1]) == MINMOVE
							&& (!available[Integer.parseInt(moveInfo[0]) - 1])) {
						try {
							throw new Exception();
						} catch (Exception e) {
							System.out.println("Invalid Input: the stone at position " + Integer.parseInt(moveInfo[0])
									+ " have already been removed");
							System.out.print("\n");
							whoseTurn = 0;
							//scanner.nextLine();
						}
					}

					else if (Integer.parseInt(moveInfo[1]) == MAXMOVE
							&& ((!available[Integer.parseInt(moveInfo[0]) - 1])
									|| (!available[Integer.parseInt(moveInfo[0])]))) {
						try {
							throw new Exception();
						} catch (Exception e) {
							System.out.println("Invalid Input: the stone(s) have already been removed");
							System.out.print("\n");
							whoseTurn = 0;
							//scanner.nextLine();
						}
					} else {
						// else :everything goes well, make the game continue
						if (Integer.parseInt(moveInfo[1]) == MINMOVE) {
							available[Integer.parseInt(moveInfo[0]) - 1] = false;
						}
						if (Integer.parseInt(moveInfo[1]) == MAXMOVE) {
							available[Integer.parseInt(moveInfo[0]) - 1] = false;
							available[Integer.parseInt(moveInfo[0])] = false;
						}
						currentAdvancedStone = currentAdvancedStone(available, initialStones);
						lastMove = move;
						System.out.print("\n");
						whoseTurn = 1;
					}

				} catch (ArrayIndexOutOfBoundsException e1) {
					System.out.println("Invalid Input: the input should be 'position numberToMove'");
					System.out.print("\n");
					whoseTurn = 0;
					//scanner.nextLine();
				}
			}
			/**
			 * the aim of this part is same as whoseTurn == 0
			 */
			else if (whoseTurn == 1) {

				try {
					System.out.print(currentAdvancedStone + " stones left:");
					printAdvancedStones(available);
					System.out.println(player2.getgiven_name() + "'s turn - which to remove?");
					move = player2.whichToMove(available, lastMove, whoseTurn, scanner);
					String[] moveInfo = move.split(" ");

					if (Integer.parseInt(moveInfo[1]) != MAXMOVE && Integer.parseInt(moveInfo[1]) != MINMOVE) {
						try {
							throw new Exception();
						} catch (Exception e) {
							System.out.println("Invalid Input: the number of stones should be either 1 or 2");
							System.out.print("\n");
							whoseTurn = 1;
						}
					}

					else if (Integer.parseInt(moveInfo[0]) < 1 || Integer.parseInt(moveInfo[0]) > available.length) {
						try {
							throw new Exception();
						} catch (Exception e) {
							System.out.println(
									"Invalid Input: the number of position should form 1 to " + available.length);
							System.out.print("\n");
							whoseTurn = 1;
						}
					}

					else if (Integer.parseInt(moveInfo[1]) == MINMOVE
							&& (!available[Integer.parseInt(moveInfo[0]) - 1])) {
						try {
							throw new Exception();
						} catch (Exception e) {
							System.out.println("Invalid Input: the stone at position " + Integer.parseInt(moveInfo[0])
									+ " have already been removed");
							System.out.print("\n");
							whoseTurn = 1;
						}
					} else if (Integer.parseInt(moveInfo[1]) == MAXMOVE
							&& ((!available[Integer.parseInt(moveInfo[0]) - 1])
									|| (!available[Integer.parseInt(moveInfo[0])]))) {
						try {
							throw new Exception();

						} catch (Exception e) {
							System.out.println("Invalid Input: the stone(s) have already been removed");
							System.out.print("\n");
							whoseTurn = 1;
						}
					} else {
						if (Integer.parseInt(moveInfo[1]) == MINMOVE) {
							available[Integer.parseInt(moveInfo[0]) - 1] = false;
						}
						if (Integer.parseInt(moveInfo[1]) == MAXMOVE) {
							available[Integer.parseInt(moveInfo[0]) - 1] = false;
							available[Integer.parseInt(moveInfo[0])] = false;
						}
						currentAdvancedStone = currentAdvancedStone(available, initialStones);
						//at the end of this players operation, the current stone should be calculated
						lastMove = move;
						System.out.print("\n");
						whoseTurn = 0;
					}

				} catch (Exception e1) {
					System.out.println("Invalid Input: the input should be 'position numberToMove'");
					System.out.print("\n");
					whoseTurn = 1;
					//scanner.nextLine();
				}
			}
		}

		System.out.println("Game Over");

		/**
		 * this "if-else if" aims to judge who is the winner
		 */
		if (whoseTurn == 1) {
			int numOfWins1 = player1.getnumOfWins();

			System.out.println(player1.getgiven_name() + " " + player1.getfamily_name() + " wins!");
			player1.setnumOfWins(numOfWins1 + 1);

		} else if (whoseTurn == 0) {
			int numOfWins2 = player2.getnumOfWins();

			System.out.println(player2.getgiven_name() + " " + player2.getfamily_name() + " wins!");
			player2.setnumOfWins(numOfWins2 + 1);

		}

	}

	/**
	 * this method is used to print the stars which stands for the number of stones
	 * 
	 * @param initialStones
	 */
	public void printStones(int currentStone) {
		int i;
		for (i = 1; i <= currentStone; i++) {
			System.out.print(" *");
		}
		System.out.print("\n");
	}

	/**
	 * this methods aims to print the stones with the format of <position,*> or
	 * <position,x> in the process of advanced game
	 * 
	 * @param available
	 */
	public void printAdvancedStones(boolean[] available) {
		int i = 0;
		int position = 0;
		for (i = 0; i < available.length; i++) {
			position = i + 1;
			if (available[i]) {
				System.out.print(" <" + position + ",*>");
			} else {
				System.out.print(" <" + position + ",x>");
			}

		}
		System.out.print("\n");
	}

	/**
	 * this method aims to record the current number of stones
	 * 
	 * @param initialStones
	 * @param numberToRemove
	 * @param upperBound
	 * @return
	 */
	public static int currentStone(int initialStones, int numberToRemove, int upperBound) {

		if (numberToRemove >= 1 && numberToRemove <= upperBound) {
			currentStone -= numberToRemove;// get the number of current stones
		}
		return currentStone;
	}

	/**
	 * this method is used to return the number of current stones in the process of
	 * advanced game
	 * 
	 * @param available
	 * @param initialStones
	 * @return
	 */
	public static int currentAdvancedStone(boolean[] available, int initialStones) {
		int count = 0;

		for (int i = 0; i < available.length; i++) {
			if (!available[i]) {
				count++;
			}
		}
		currentAdvancedStone = initialStones - count;
		return currentAdvancedStone;
	}

}
