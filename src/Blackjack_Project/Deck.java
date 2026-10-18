package Blackjack_Project;

import java.util.ArrayList;
import java.util.Collections;

// ======= Deck Class =======
// Creates and manages a standard 52-card deck.
public class Deck {
	
	// ArrayList of "Card"
	private ArrayList<Card> cards;
	
	// Constructor
	public Deck() {
		
		// Creates ArrayList + Arrays
		cards = new ArrayList<>();
		String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
		String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		
		// Fill up cards
		for (String currentSuit : suits) {
			for (String currentRank : ranks) {
				Card currentCard = new Card(currentRank, currentSuit);
				cards.add(currentCard);
			}
		}
	}
	
	// Randomizes the card order
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	// Removes and returns one card
	public Card dealCard() {
		Card dealtCard = cards.remove(0);
		return dealtCard;
	}
	
	// Returns how many cards remain
	public int getRemainingCards() {
		return cards.size();
	}
}