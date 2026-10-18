package Blackjack_Project;

// ======= Card Class =======
// Represents an individual card
public class Card {
	
	// Variables
	private String rank;
	private String suit;
	
	// Constructor
	public Card (String rankInput, String suitInput) {
		rank = rankInput;
		suit = suitInput;
	}
	
	// Getters
	public String getRank() { return rank; }
	public String getSuit() { return suit; }
	public int getValue() {
		if (rank.equals("A")) {
			return 11;
		}
		if (rank.equals("K") || rank.equals("Q") || rank.equals("J")) {
			return 10;
		}
		else {
			return Integer.parseInt(rank);
		}
	}
	
	// Override display of card through toString()
	@Override
	public String toString() {
		String suitSymbol = "";
		if (suit.equals("Hearts")) {
			suitSymbol = "♥";
		}
		else if (suit.equals("Diamonds")) {
			suitSymbol = "♦";
		}
		else if (suit.equals("Clubs")) {
			suitSymbol = "♣";
		}
		else { // "Spades" 
			suitSymbol = "♠";
		}
		return ("[" + rank + suitSymbol + "]");
	}
}