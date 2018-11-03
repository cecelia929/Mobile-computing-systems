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
public abstract class NimPlayer {

	private String userName;
	private String family_name;
	private String given_name;
	private int numOfGames;
	private int numOfWins;

	/**
	 * constructor
	 * 
	 * @param userName
	 * @param family_name
	 * @param given_name
	 * @param numOfGames
	 * @param numOfWins
	 */
	public NimPlayer(String userName, String family_name, String given_name, int numOfGames, int numOfWins) {

		this.userName = userName;
		this.family_name = family_name;
		this.given_name = given_name;
		this.numOfGames = numOfGames;
		this.numOfWins = numOfWins;
	}

	public NimPlayer() {

	}

	/**
	 * this part aims to defined mutator or accessor methods
	 * 
	 * @return
	 */
	public String getgiven_name() {
		return given_name;
	}

	public void setgiven_name(String given_name) {
		this.given_name = given_name;
	}

	public String getuserName() {
		return userName;
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	public String getfamily_name() {
		return family_name;
	}

	public void setfamily_name(String family_name) {
		this.family_name = family_name;
	}

	public int getnumOfGames() {
		return numOfGames;
	}

	public void setnumOfGames(int numOfGames) {
		this.numOfGames = numOfGames;
	}

	public int getnumOfWins() {
		return numOfWins;
	}

	public void setnumOfWins(int numOfWins) {
		this.numOfWins = numOfWins;
	}

	/**
	 * an abstract method to return the number of remove stone in common game
	 * @param whoseTurn
	 * @param numberToRemove
	 * @param upperBound
	 * @param currentStone
	 * @param initialStones
	 * @param scanner
	 * @return
	 */
	public abstract int removeStone(int whoseTurn, int numberToRemove, int upperBound, int currentStone,
			int initialStones, Scanner scanner);

	/**
	 * an abstract method to return the value of which to move in advanced game  
	 * @param available
	 * @param lastMove
	 * @param whoseTurn
	 * @param scanner
	 * @return
	 */
	public abstract String whichToMove(boolean[] available, String lastMove, int whoseTurn, Scanner scanner);
}
