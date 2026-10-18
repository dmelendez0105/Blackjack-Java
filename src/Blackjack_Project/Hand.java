package Blackjack_Project;

import java.util.ArrayList;

// ======= Hand Class =======
// Stores the cards held by either the player or the dealer and calculates their total value.
public class Hand {
	
	// ArrayList of "Card"
	private ArrayList<Card> hand;
	
	// Constructor
	public Hand() {
		
		// Creates ArrayList + Arrays
		hand = new ArrayList<>();
	}
	
	// Adds a card to player's hand
	public void addCard(Card card) {
		hand.add(card);
	}
	
	// Gets cumulative value from hand
	public int getTotalValue() {
		int totalValue = 0;
		int aceCount = 0;
		for (Card currentCard : hand) {
			if (currentCard.getRank().equals("A")) {
				aceCount++;
			}
			totalValue += currentCard.getValue();
		}
		while (totalValue > 21 && aceCount > 0) { // Makes sure "Ace" cards can count as 1 as well
			totalValue -= 10;
			aceCount--;
		}
		return totalValue;
	}
	
	// Checks if the player has 22 or over
	public boolean isBust() {
		int currentValue = getTotalValue();
		if (currentValue > 21) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// Checks if the player has a natural 21
	public boolean isBlackjack() {
		int currentValue = getTotalValue();
		if (currentValue == 21 && hand.size() == 2) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// Allows for other classes to see player's Hand and not modify it
	public ArrayList<Card> getHand() {
		return new ArrayList<>(hand);
	}
}