package Blackjack_Project;

// ======= BlackjackGame Class =======
// Creates the deck and two empty hands.
public class BlackjackGame {
	
	private Hand playerHand;
	private Hand dealerHand;
	private Deck the52;
	
	// Constructor
	public BlackjackGame() {
		
		// Creates Player's hand and Dealer's hand
		playerHand = new Hand();
		dealerHand = new Hand();
		
		// Creates the Deck and shuffles it
		the52 = new Deck();
		the52.shuffle();
		
	}
	
	// Deals first two cards
	public void startRound() {
		
		// 1st Card Each
		playerHand.addCard(the52.dealCard());
		dealerHand.addCard(the52.dealCard());
		
		// 2nd Card Each
		playerHand.addCard(the52.dealCard());
		dealerHand.addCard(the52.dealCard());
	}
	
	// The player wants another card
	public void playerHit() {
		playerHand.addCard(the52.dealCard());
	}
	
	// The dealer automatically plays
	public void dealerTurn() {
		while (dealerHand.getTotalValue() < 17) { dealerHand.addCard(the52.dealCard()); }
	}
	
	// Getters
	public Hand getPlayerHand() { return playerHand; }
	public Hand getDealerHand() { return dealerHand; }
	
	// Winner
	public String getWinner() {
		
		// 1) Player has a bust hand
		if (playerHand.isBust()) { return "Dealer"; }
		
		// 2) Dealer has a bust hand
		else if (dealerHand.isBust()) { return "Player"; }
		
		// 3) Check if it's a push (tie) w/ Natural Blackjacks
		else if (playerHand.isBlackjack() && dealerHand.isBlackjack()) { 
			return "Push";
			}
		
		// 4) Player wins w/ Natural Blackjack
		else if (playerHand.isBlackjack()) { 
			return "Player";
			}
		
		// 5) Dealer wins w/ Natural Blackjack
		else if (dealerHand.isBlackjack()) { 
			return "Dealer";
			}
		
		// 6) Push (tie)
		else if (playerHand.getTotalValue() == dealerHand.getTotalValue()) { 
			return "Push";
			}
		
		// 7) Dealer has a higher hand than Player
		else if (dealerHand.getTotalValue() > playerHand.getTotalValue()) {
			return "Dealer";
		}
		
		// 8) else = After every other condition, Player Wins
		else { return "Player"; }
	}
}