
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
/**
 * The university of Melbourne
 * COMP90041 Programming and Software Development
 * Project C
 * Major: Master of Information Technology - computing
 * Student Name: Yajing Huang
 * Student Number: 896243
 */

import java.util.Scanner;

public class Nimsys {
	private static NimPlayer[] person;// this is an array of classes
	private static int n;
	private static File file = new File("Player.dat");

	/**
	 * constructor
	 */
	public Nimsys() {

		int MAX = 100;
		person = new NimPlayer[MAX];
		n = 0;

	}
	

	public static void main(String[] args) {
		int upperBound;
		int initialStones;
		Nimsys nimSys = new Nimsys();
		NimGame nimGame = new NimGame();
		Scanner scanner = new Scanner(System.in);
		
		// Nimsys nimSys = new Nimsys();
		// NimGame nimGame = new NimGame();
		nimSys.readPerson();
		System.out.println("Welcome to Nim");
		System.out.println();
		System.out.print("$");
		/**
		 * this do-while loop aims to run the code until users input the "exit" command
		 */
		do {

			String command = scanner.nextLine();// command is the whole string of the users' input
			String[] comInfo = command.split(" ");// use "split" method to devide the command to many useful subcommand
			String command1 = comInfo[0];// command1 is the first word in the whole command
			// InvalidCommandException InvalidCommandException = new
			// InvalidCommandException(command1);

			/**
			 * add player
			 */
			try {

				if (command1.equals("addplayer")) {
					String command2 = comInfo[1];
					String[] userInfo = command2.split(",");

					if (!nimSys.find(userInfo[0])) {
						nimSys.insertHuman(userInfo[0], userInfo[1], userInfo[2], 0, 0);
					} else {
						System.out.println("The player already exists.");
					}

				}

				else if (command1.equals("addaiplayer")) {
					String command2 = comInfo[1];
					String[] userInfo = command2.split(",");

					if (!nimSys.find(userInfo[0])) {
						nimSys.insertAI(userInfo[0], userInfo[1], userInfo[2], 0, 0);
					} else {
						System.out.println("The player already exists.");
					}

				}
				/**
				 * remove player
				 */
				else if (command1.equals("removeplayer")) {
					if (command.equals(command1)) {
						System.out.println("Are you sure you want to remove all players? (y/n)");
						String whetherRemoveAll = scanner.next();
						if (whetherRemoveAll.equals("y")) {
							nimSys.removeall();
						}
						scanner.nextLine();
					} else {
						/**
						 * the return type of method "remove" is boolean, when this return value is
						 * true(this user exists), then call this method this is same as "find" method
						 */
						if (nimSys.remove(comInfo[1])) {
							nimSys.remove(comInfo[1]);
						} else {
							System.out.println("The player does not exist.");
						}
					}
				}
				/**
				 * edit player
				 */
				else if (command1.equals("editplayer")) {
					String command2 = comInfo[1];
					String[] userInfo = command2.split(",");
					if (nimSys.find(userInfo[0])) {
						nimSys.edit(userInfo[0], userInfo[1], userInfo[2]);
					} else {
						System.out.println("The player does not exist.");
					}
				}

				/**
				 * reset player
				 */
				else if (command1.equals("resetstats")) {
					// if the input is the single word without username,then reset all players
					if (command.equals(command1)) {
						System.out.println("Are you sure you want to reset all players? (y/n)");
						String whetherResetAll = scanner.next();
						if (whetherResetAll.equals("y")) {
							nimSys.resetall(0, 0);
						}
						scanner.nextLine();
					} else {
						String command2 = comInfo[1];
						String[] userInfo = command2.split(",");
						if (nimSys.find(userInfo[0])) {
							nimSys.reset(userInfo[0], 0, 0);
						} else {
							System.out.println("The player does not exist.");
						}
					}
				}
				/**
				 * display player
				 */
				else if (command1.equals("displayplayer")) {

					// if the input is the single word without username,then display all players
					if (command.equals(command1)) {
						nimSys.displayall();
					} else {

						if (nimSys.find(comInfo[1])) {
							nimSys.display(comInfo[1]);
						} else {
							System.out.println("The player does not exist.");
						}
					}
				}

				/**
				 * ranking
				 */
				else if (command1.equals("rankings")) {
					int i = 0;
					int m = 0;

					/**
					 * this array including 3 lines of values: win rate, username and the pointer
					 * when the username is same in array "person[]"
					 */
					String winInfo[][] = new String[100][3];
					for (i = 0; i < n; i++) {
						if (person[i].getnumOfGames() == 0) {

							winInfo[i][0] = String.valueOf(0);
						} else if (person[i].getnumOfGames() != 0) {
							DecimalFormat df = new DecimalFormat("#");
							winInfo[i][0] = (df.format(new BigDecimal(
									(person[i].getnumOfWins() * (100.00)) / (person[i].getnumOfGames()))));
						}

						winInfo[i][1] = person[i].getuserName();
						winInfo[i][2] = String.valueOf(i);
						// this value is used to find the correct value in array "person[]"
					}
					// different conmmand has different order fo sorting
					if (command.equals(command1) || comInfo[1].equals("desc")) {
						nimSys.sortArrayDescendingly(winInfo);
					}
					if ((!command.equals(command1)) && comInfo[1].equals("asc")) {
						nimSys.sortArrayAscendingly(winInfo);
					}

					for (i = 0; i < n; i++) {

						// the value of m is used to limit that only 10 lines need to be ranking when
						// the number of user is more than 10
						if (m <= 10) {
							System.out.printf("%-5s| %02d games | %s %s\n", winInfo[i][0] + "%",
									person[Integer.parseInt((winInfo[i][2]))].getnumOfGames(),
									person[Integer.parseInt((winInfo[i][2]))].getgiven_name(),
									person[Integer.parseInt((winInfo[i][2]))].getfamily_name());
							m++;
						}
					}
				}

				/**
				 * startgame
				 */
				else if (command1.equals("startgame")) {
					// try {
					String command2 = comInfo[1];
					String[] userInfo = command2.split(",");
					int k = 0, l = 0;

					if (nimSys.find(userInfo[2]) && nimSys.find(userInfo[3])) {
						initialStones = Integer.parseInt(userInfo[0]);
						upperBound = Integer.parseInt(userInfo[1]);
						int i, j;
						for (i = 0; i < n; i++) {

							if (person[i].getuserName().equals(userInfo[2])) {
								k = i;// get player1's information
							}
						}
						for (j = 0; j < n; j++) {
							if (person[j].getuserName().equals(userInfo[3])) {
								l = j;// get player2's information
							}
						}

						nimGame.startgame(initialStones, upperBound, person[k], person[l], scanner);
						scanner.nextLine();
					} else {
						System.out.println("One of the players does not exist.");

					}
				}
				/**
				 * start advanced game
				 */

				else if (command1.equals("startadvancedgame")) {
					// try {
					String command2 = comInfo[1];
					String[] userInfo = command2.split(",");
					int k = 0, l = 0;

					if (nimSys.find(userInfo[1]) && nimSys.find(userInfo[2])) {
						initialStones = Integer.parseInt(userInfo[0]);
						int i, j;

						// these two 'for-loop' aim to find the information of player1 and player2
						for (i = 0; i < n; i++) {
							if (person[i].getuserName().equals(userInfo[1])) {
								k = i;
							}
						}
						for (j = 0; j < n; j++) {
							if (person[j].getuserName().equals(userInfo[2])) {
								l = j;
							}
						}
						// call the method in NimGame
						nimGame.startadvancedgame(initialStones, person[k], person[l], scanner);

					} else {
						System.out.println("One of the players does not exist.");

					}
				}
				/**
				 * exit game
				 */
				else if (command1.equals("exit")) {
					// before exit the programm, store all information of players in a file
					nimSys.writePerson();
					System.out.println();
					System.exit(0);
				} else {
					throw new Exception();
				}

			} catch (ArrayIndexOutOfBoundsException e1) {
				System.out.println("Incorrect number of arguments supplied to command.");

			} catch (Exception e) {
				System.out.println("'" + command1 + "' is not a valid command.");
			}
			System.out.println();
			System.out.print("$");
		} while (true);

	}

	/**
	 * insert human user information
	 * 
	 * @param userName
	 * @param family_name
	 * @param given_name
	 * @param numOfGames
	 * @param numOfWins
	 */
	public void insertHuman(String userName, String family_name, String given_name, int numOfGames, int numOfWins) {
		if (n < 100) {
			// up cast
			person[n] = new NimHumanPlayer(userName, family_name, given_name, numOfGames, numOfWins);
			n++;
		} else {
			System.out.println("Sorry, the number of players has reached the upper bound, please try the game later.");

		}
	}

	/**
	 * insert AI user information
	 * 
	 * @param userName
	 * @param family_name
	 * @param given_name
	 * @param numOfGames
	 * @param numOfWins
	 */
	public void insertAI(String userName, String family_name, String given_name, int numOfGames, int numOfWins) {
		if (n < 100) {
			// up cast
			person[n] = new NimAIPlayer(userName, family_name, given_name, numOfGames, numOfWins);
			n++;
		} else {
			System.out.println("Sorry, the number of players has reached the upper bound, please try the game later.");

		}
	}

	/**
	 * edit the user's personal information
	 * 
	 * @param userName
	 * @param family_name
	 * @param given_name
	 */
	public void edit(String userName, String family_name, String given_name) {
		int i = 0;
		if (person[i].getuserName().equals(userName)) {
			person[i].setfamily_name(family_name);
			person[i].setgiven_name(given_name);
		}
	}

	/**
	 * reset all users' information related to games
	 * 
	 * @param numOfGames
	 * @param numOfWins
	 */
	public void resetall(int numOfGames, int numOfWins) {
		int i;

		for (i = 0; i < n; i++) {

			person[i].setnumOfGames(numOfGames);
			person[i].setnumOfWins(numOfWins);
		}
	}

	/**
	 * reset single user's infomation related to games
	 * 
	 * @param userName
	 * @param numOfGames
	 * @param numOfWins
	 */
	public void reset(String userName, int numOfGames, int numOfWins) {
		int i = 0;
		if (person[i].getuserName().equals(userName)) {
			person[i].setnumOfGames(numOfGames);
			person[i].setnumOfGames(numOfWins);
		}
	}

	/**
	 * remove all players
	 */
	public void removeall() {
		for (int i = 0; i < n; i++) {
			person[i] = null;
		}
		n = 0;
	}

	/**
	 * remove on player
	 * 
	 * @param userName
	 * @return
	 */
	public boolean remove(String userName) {
		int i;
		for (i = 0; i < n; i++) {
			if (person[i].getuserName().equals(userName))
				break;
		}
		if (i == n) {
			return false;
		} else {
			for (int j = i; j < n; j++) {
				person[j] = person[j + 1];
			}
			n--;
			return true;
		}
	}

	/**
	 * find user whether exists or not
	 * 
	 * @param userName
	 * @return
	 */
	public boolean find(String userName) {
		int i;
		for (i = 0; i < n; i++) {
			if (person[i].getuserName().equals(userName))
				break;
		}
		if (i == n) {
			return false;
		} else {
			return true;

		}

	}

	/**
	 * ranking two different sorting order for different command(asc and desc)
	 * 
	 * @param array
	 */
	public void sortArrayDescendingly(String[][] array) {
		String winRateTemp;
		String userNameTemp;
		String pointerTemp;
		int index;
		int i, j;
		for (i = 0; i < n - 1; i++) {
			// Find the i^th max value
			winRateTemp = array[i][0];
			userNameTemp = array[i][1];
			pointerTemp = array[i][2];
			index = i;
			for (j = i + 1; j < n; j++) {

				// convert string type to int which is used to compare the value more easily and
				// corretly
				if (Integer.parseInt(array[i][0]) < Integer.parseInt(array[j][0])) {
					index = j;
				}
			}

			// Swap elements
			if (index != i) {

				array[i][0] = array[index][0];
				array[index][0] = winRateTemp;
				array[i][1] = array[index][1];
				array[index][1] = userNameTemp;
				array[i][2] = array[index][2];
				array[index][2] = pointerTemp;

			}
		}

		// when the win rate is same, use the username to be sort
		for (i = 0; i < n - 1; i++) {
			for (j = n - 1; j > i; j--) {
				if ((Integer.parseInt(array[i][0]) == Integer.parseInt(array[j][0])) && i != j) {

					// Find the i^th max
					winRateTemp = array[i][0];
					userNameTemp = array[i][1];
					pointerTemp = array[i][2];
					index = i;

					if (array[i][1].compareTo(array[j][1]) > 0) {
						index = j;
					}

					// Swap elements
					array[i][0] = array[index][0];
					array[index][0] = winRateTemp;
					array[i][1] = array[index][1];
					array[index][1] = userNameTemp;
					array[i][2] = array[index][2];
					array[index][2] = pointerTemp;
				}
			}
		}
	}

	public void sortArrayAscendingly(String[][] array) {
		String winRateTemp;
		String userNameTemp;
		String pointerTemp;
		int index;
		int i, j;
		// for (i = n - 1; i > 0; i--) {
		for (i = 0; i < n - 1; i++) {
			// Find the i^th max value
			index = i;
			// for (j = i - 1; j > 0; j--) {
			for (j = n - 1; j > i; j--) {

				// convert string type to int which is used to compare the value more easily and
				// corretly
				if (Integer.parseInt(array[index][0]) > Integer.parseInt(array[j][0])) {
					index = j;
				}
			}

			// Swap elements
			if (i != index) {
				winRateTemp = array[i][0];
				userNameTemp = array[i][1];
				pointerTemp = array[i][2];

				array[i][0] = array[index][0];
				array[index][0] = winRateTemp;
				array[i][1] = array[index][1];
				array[index][1] = userNameTemp;
				array[i][2] = array[index][2];
				array[index][2] = pointerTemp;

			}
		}

		// when the win rate is same, use the username to be sort
		for (i = 0; i < n - 1; i++) {
			for (j = n - 1; j > i; j--) {
				if ((Integer.parseInt(array[i][0]) == Integer.parseInt(array[j][0])) && i != j) {

					// Find the i^th max
					winRateTemp = array[i][0];
					userNameTemp = array[i][1];
					pointerTemp = array[i][2];
					index = i;

					if (array[i][1].compareTo(array[j][1]) > 0) {
						index = j;
					}

					// Swap elements
					array[i][0] = array[index][0];
					array[index][0] = winRateTemp;
					array[i][1] = array[index][1];
					array[index][1] = userNameTemp;
					array[i][2] = array[index][2];
					array[index][2] = pointerTemp;
				}
			}
		}
	}

	/**
	 * display one player
	 */
	public void display(String userName) {
		for (int i = 0; i < n; i++) {
			if (person[i].getuserName().equals(userName)) {

				System.out.println(
						person[i].getuserName() + "," + person[i].getgiven_name() + "," + person[i].getfamily_name()
								+ "," + person[i].getnumOfGames() + " games," + person[i].getnumOfWins() + " wins");
			}
		}
	}

	/**
	 * display all players
	 */
	public void displayall() {
		String userName[] = new String[n];
		for (int i = 0; i < n; i++) {
			userName[i] = person[i].getuserName();
		}
		Arrays.sort(userName);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (userName[i].equals(person[j].getuserName())) {
					System.out.println(
							person[j].getuserName() + "," + person[j].getgiven_name() + "," + person[j].getfamily_name()
									+ "," + person[j].getnumOfGames() + " games," + person[j].getnumOfWins() + " wins");
				}
			}
		}
	}

	/**
	 * store the information of players in a file
	 */
	public void writePerson() {
		// considering of there may be some changes of player's information in every
		// process of the program running, it is better to delete all and then store
		// again
		file.delete();
		try {
			if (!file.exists()) {
				@SuppressWarnings("unused")
				File file = new File("Player.dat");
			}
			// use buffer writer to write the array to file
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));

			int type = 0;
			for (int i = 0; i < n; i++) {
				if (person[i] instanceof NimAIPlayer) {
					type = 0;
				}
				if (person[i] instanceof NimHumanPlayer) {
					type = 1;
				}
				bw.write(type + " " + person[i].getuserName() + " " + person[i].getfamily_name() + " "
						+ person[i].getgiven_name() + " " + person[i].getnumOfGames() + " " + person[i].getnumOfWins()
						+ "\n");

			}
			bw.close();
		} catch (IOException e) {
			System.exit(0);
		}

	}

	/**
	 * read the information of players from file to array
	 * 
	 * @return
	 */
	public NimPlayer[] readPerson() {

		try {
			int TYPEAI = 0;// this number stands for the type of player is AI
			int TYPEHUMAN = 1;// this number stands for the type of player is human
			@SuppressWarnings("resource")
			// use buffer reader to read the file
			BufferedReader br = new BufferedReader(new FileReader(file));
			String temp = "";
			while ((temp = br.readLine()) != null) {
				String[] fac = temp.trim().split(" ");
				if (n < 100) {
					if (Integer.parseInt(fac[0]) == TYPEAI) {
						person[n] = new NimAIPlayer(fac[1], fac[2], fac[3], Integer.parseInt(fac[4]),
								Integer.parseInt(fac[5]));
						n++;
					}
					if (Integer.parseInt(fac[0]) == TYPEHUMAN) {
						person[n] = new NimHumanPlayer(fac[1], fac[2], fac[3], Integer.parseInt(fac[4]),
								Integer.parseInt(fac[5]));
						n++;
					}

				}
			}
		} catch (FileNotFoundException e) {
			// File file = new File("Player.dat");
		} catch (IOException e) {
			// e.printStackTrace();
		}

		return person;// return the array
	}
}
