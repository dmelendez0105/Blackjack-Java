/*
 * ===== BLACKJACK =====
 * (Text-Based Java Game)
 * 
 * Purpose: This project recreates the classic game of Blackjack using Java.
 * The player competes against a computer-controlled dealer, with a shuffled
 * 52-card deck, card values, and standard Blackjack rules. The game allows
 * players to Hit or Stand, determines the winner of each round, and offers
 * the option to play again. The project uses object-oriented programming
 * to organize cards, decks, hands, and game logic into separate classes.
 */

package Blackjack_Project;

import java.util.Scanner;
import java.util.ArrayList;

// ======= Main/Driver =======
// Starts the game and handles console interaction
public class Main {
	public static void main(String args[]) {
		
		// ANSI Escape Codes for Text Color + Style/Format
		String YELLOW = "\u001B[33m";
		String GREEN = "\u001B[32m";
		String RED = "\u001B[31m";
		String RESET = "\u001B[0m";
		
		String BOLD = "\u001B[1m";
		
		// Win:Tie:Loss Ratio Variables
		int win = 0;
		int loss = 0;
		int tie = 0;
		
		// Necessities/Variables
		Scanner userInput = new Scanner(System.in);
		boolean gameRestart = true; 
		
		// Game Loop
		while (gameRestart == true) {
			
			// Title Display
			System.out.println(BOLD + YELLOW + "=========================================" + RESET);
			System.out.println(BOLD + YELLOW + "=============== BLACKJACK ===============" + RESET);
			System.out.println(BOLD + YELLOW + "=========================================" + RESET);
			System.out.println("");
			
			// Creates a New Blackjack Game
			BlackjackGame myGame = new BlackjackGame();
			
			// ======= Game Process [UTILIZES OTHER CLASSES] =======
			myGame.startRound();
			
			// Creates Duplicated Hands for Display Purposes
			ArrayList<Card> dealerCards = myGame.getDealerHand().getHand();
			ArrayList<Card> playerCards = myGame.getPlayerHand().getHand();
			Card drawnCard = playerCards.get(playerCards.size() - 1);
			
			// Important Variables + "Hit" Loop
			int hitStand = 0;
			boolean repeatInput = true;
			boolean actionEntered = false;
			while (repeatInput) {
				
				// Displays what card the Player has drawn
				if (hitStand == 1) {
					System.out.println("\n" + "You drew: " + drawnCard); // Maybe + "\n"
				}
				
				// Display Player's Hand
				System.out.println("Your Hand:");
				for (Card currentCard : myGame.getPlayerHand().getHand()) { System.out.print(currentCard); }
				System.out.println("");
				System.out.print("Total: ");
				System.out.println(myGame.getPlayerHand().getTotalValue());
				System.out.println("");
				
				// Display Dealer's Hand
				System.out.println("Dealer's Hand:");
				System.out.println(dealerCards.get(0) + "[??]"); 
				System.out.println("");
				
				// Checks if either the Player or Dealer have a natural Blackjack
				if (myGame.getPlayerHand().isBlackjack() || myGame.getDealerHand().isBlackjack()) {
					break;
				}
				
				// Player has to choose Hit or Stand + Checks for valid inputs
				actionEntered = true;
				System.out.println("Choose an action:" + "\n" + "1. Hit" + "\n" + "2. Stand");
				boolean incorrectInput = true;
				while (incorrectInput) {
				    System.out.print("> ");
				    
				    if (userInput.hasNextInt()) {
				        hitStand = userInput.nextInt();

				        if (hitStand == 1 || hitStand == 2) {
				            incorrectInput = false;
				        }
				        else {
				            System.out.println("\n" + "[INVALID NUMBER]: Choose 1 or 2.");
				        }
				    }
				    else {
				        System.out.println("\n" + "[INVALID INPUT]: Choose 1 or 2.");
				        userInput.next();
				    }
				}
				
				// If player chooses valid input:
				if (hitStand == 1) {
					myGame.playerHit();
					playerCards = myGame.getPlayerHand().getHand();
					drawnCard = playerCards.get(playerCards.size() - 1);
					if (myGame.getPlayerHand().isBust() || myGame.getPlayerHand().getTotalValue() == 21) {
						System.out.println("");
						clearConsole();
						break;
					}
					
					System.out.println("");
					clearConsole();
				}
				else { 
					repeatInput = false;
					System.out.println("");
				}
			}
			
			// Clears leftover newline from [1] or [2] input
			if (actionEntered) { userInput.nextLine(); }
			
			// ======= Determines who is the winner and displays results =======
			
			// Makes sure the Dealer doesn't play if either have a
			// Natural Blackjack or if the Player exceeds 21.
			if (!myGame.getPlayerHand().isBust() && !(myGame.getPlayerHand().isBlackjack() || myGame.getDealerHand().isBlackjack())) {
				myGame.dealerTurn();
			}
			
			String winner = myGame.getWinner();
			
			// Matches Duplicated Display Hands to Real Hands
			dealerCards = myGame.getDealerHand().getHand();
			playerCards = myGame.getPlayerHand().getHand();
			
			// Results Display
			System.out.println(YELLOW + BOLD + "================ RESULTS ================" + RESET + "\n");
			
			// Win: Player Blackjack
			if (winner.equals("Player") && myGame.getPlayerHand().isBlackjack()) {
				System.out.println("Your Hand:");
				for (Card currentCard : myGame.getPlayerHand().getHand()) { System.out.print(currentCard); }
				System.out.println("");
				System.out.println("Blackjack!" + "\n");
				
				System.out.println("Dealer reveals:");
				for (Card currentCard : myGame.getDealerHand().getHand()) { System.out.print(currentCard); }
				System.out.println("");
				System.out.println("Dealer Total: " + myGame.getDealerHand().getTotalValue() + "\n");
				
				win++;
				System.out.println(BOLD + GREEN + "YOU WIN!" + RESET);
				System.out.println("Your Total:    " + myGame.getPlayerHand().getTotalValue());
				System.out.println("Dealer Total:  " + myGame.getDealerHand().getTotalValue());
			}
			
			// Win: Dealer Bust
			else if (winner.equals("Player") && myGame.getDealerHand().isBust()) {
				System.out.println("Your Hand:");
				for (Card currentCard : myGame.getPlayerHand().getHand()) { System.out.print(currentCard); }
				System.out.println("");
				System.out.println("You stand at: " + myGame.getPlayerHand().getTotalValue() + "\n");
				
				System.out.println("Dealer reveals:");
				for (int i = 0; i < dealerCards.size(); i++) {
					if (i < 2) {
						System.out.print(dealerCards.get(i));
					}
					else {
						System.out.print(" + " + dealerCards.get(i));
					}
				}
				System.out.println("");
				System.out.println("Dealer Total: " + myGame.getDealerHand().getTotalValue() + "\n");
				
				win++;
				System.out.println(BOLD + GREEN + "YOU WIN!" + RESET);
				System.out.println("Your Total:    " + myGame.getPlayerHand().getTotalValue());
				System.out.println("Dealer Total:  " + myGame.getDealerHand().getTotalValue());
			}
			
			// Win: Player > Dealer
			else if (winner.equals("Player") && (myGame.getPlayerHand().getTotalValue() > myGame.getDealerHand().getTotalValue())) {
				System.out.println("Your Hand:");
				for (Card currentCard : myGame.getPlayerHand().getHand()) { System.out.print(currentCard); }
				System.out.println("");
				System.out.println("You stand at: " + myGame.getPlayerHand().getTotalValue() + "\n");
				
				System.out.println("Dealer reveals:");
				for (int i = 0; i < dealerCards.size(); i++) {
					if (i < 2) {
						System.out.print(dealerCards.get(i));
					}
					else {
						System.out.print(" + " + dealerCards.get(i));
					}
				}
				System.out.println("");
				System.out.println("Dealer Total: " + myGame.getDealerHand().getTotalValue() + "\n");
				
				win++;
				System.out.println(BOLD + GREEN + "YOU WIN!" + RESET);
				System.out.println("Your Total:    " + myGame.getPlayerHand().getTotalValue());
				System.out.println("Dealer Total:  " + myGame.getDealerHand().getTotalValue());
			}
			
			// Loss: Dealer Blackjack
			else if (winner.equals("Dealer") && myGame.getDealerHand().isBlackjack()) {
				System.out.println("Your Hand:");
				for (Card currentCard : myGame.getPlayerHand().getHand()) { System.out.print(currentCard); }
				System.out.println("");
				System.out.println("You stand at: " + myGame.getPlayerHand().getTotalValue() + "\n");
				
				System.out.println("Dealer reveals:");
				for (Card currentCard : myGame.getDealerHand().getHand()) { System.out.print(currentCard); }
				System.out.println("");
				System.out.println("Dealer Total: " + myGame.getDealerHand().getTotalValue() + "\n");
				
				loss++;
				System.out.println(BOLD + RED + "YOU LOSE | Dealer Blackjack" + RESET);
				System.out.println("Your Total:    " + myGame.getPlayerHand().getTotalValue());
				System.out.println("Dealer Total:  " + myGame.getDealerHand().getTotalValue());
			}
			
			// Loss: Player Bust
			else if (winner.equals("Dealer") && myGame.getPlayerHand().isBust()) {
				System.out.println("Your Hand:");
				for (int i = 0; i < playerCards.size(); i++) {
					if (i < 2) {
						System.out.print(playerCards.get(i));
					}
					else {
						System.out.print(" + " + playerCards.get(i));
					}
				}
				System.out.println("");
				System.out.println("You exceeded 21 at: " + myGame.getPlayerHand().getTotalValue() + "\n");
				
				System.out.println("Dealer reveals:");
				for (Card currentCard : myGame.getDealerHand().getHand()) { System.out.print(currentCard); }
				System.out.println("");
				System.out.println("Dealer Total: " + myGame.getDealerHand().getTotalValue() + "\n");
				
				loss++;
				System.out.println(BOLD + RED + "YOU LOSE | You exceeded 21" + RESET);
				System.out.println("Your Total:    " + myGame.getPlayerHand().getTotalValue());
				System.out.println("Dealer Total:  " + myGame.getDealerHand().getTotalValue());
			}
			
			// Loss: Dealer > Player
			else if (winner.equals("Dealer") && (myGame.getDealerHand().getTotalValue() > myGame.getPlayerHand().getTotalValue())) {
				System.out.println("Your Hand:");
				for (Card currentCard : myGame.getPlayerHand().getHand()) { System.out.print(currentCard); }
				System.out.println("");
				System.out.println("You stand at: " + myGame.getPlayerHand().getTotalValue() + "\n");
				
				System.out.println("Dealer reveals:");
				for (int i = 0; i < dealerCards.size(); i++) {
					if (i < 2) {
						System.out.print(dealerCards.get(i));
					}
					else {
						System.out.print(" + " + dealerCards.get(i));
					}
				}
				System.out.println("");
				System.out.println("Dealer Total: " + myGame.getDealerHand().getTotalValue() + "\n");
				
				loss++;
				System.out.println(BOLD + RED + "YOU LOSE | Dealer Won" + RESET);
				System.out.println("Your Total:    " + myGame.getPlayerHand().getTotalValue());
				System.out.println("Dealer Total:  " + myGame.getDealerHand().getTotalValue());
			}
			
			// Push: Blackjack Tie
			else if (winner.equals("Push") && myGame.getPlayerHand().isBlackjack() && myGame.getDealerHand().isBlackjack()) {
				System.out.println("Your Hand:");
				for (Card currentCard : myGame.getPlayerHand().getHand()) { System.out.print(currentCard); }
				System.out.println("");
				System.out.println("Blackjack!" + "\n");
				
				System.out.println("Dealer reveals:");
				for (Card currentCard : myGame.getDealerHand().getHand()) { System.out.print(currentCard); }
				System.out.println("");
				System.out.println("Dealer Total: " + myGame.getDealerHand().getTotalValue() + "\n");
				
				tie++;
				System.out.println(BOLD + YELLOW + "PUSH | You tied with the Dealer" + RESET);
				System.out.println("Your Total:    " + myGame.getPlayerHand().getTotalValue());
				System.out.println("Dealer Total:  " + myGame.getDealerHand().getTotalValue());
			}
			
			// Push: Tied during play
			else {
				System.out.println("Your Hand:");
				for (Card currentCard : myGame.getPlayerHand().getHand()) { System.out.print(currentCard); }
				System.out.println("");
				System.out.println("You stand at: " + myGame.getPlayerHand().getTotalValue() + "\n");
				
				System.out.println("Dealer reveals:");
				for (int i = 0; i < dealerCards.size(); i++) {
					if (i < 2) {
						System.out.print(dealerCards.get(i));
					}
					else {
						System.out.print(" + " + dealerCards.get(i));
					}
				}
				System.out.println("");
				System.out.println("Dealer Total: " + myGame.getDealerHand().getTotalValue() + "\n");
				
				tie++;
				System.out.println(BOLD + YELLOW + "PUSH | You tied with the Dealer" + RESET);
				System.out.println("Your Total:    " + myGame.getPlayerHand().getTotalValue());
				System.out.println("Dealer Total:  " + myGame.getDealerHand().getTotalValue());
			}
			
			// ======= W/T/L + Ask if Replay + Checks for acceptable input =======
			System.out.println("");
			System.out.println("[Win : Tie : Loss] Ratio:");
			System.out.println(GREEN + win + RESET +" : " + YELLOW + tie + RESET + " : " + RED + loss + RESET + "\n");
			
			System.out.println("Play Again? (Y/N): ");
			String userRestart = userInput.nextLine();
			while (!userRestart.equalsIgnoreCase("Y") && !userRestart.equalsIgnoreCase("N")) {
				System.out.println("[INVALID INPUT]: Play Again? (Y/N): ");
				userRestart = userInput.nextLine();
			}
			if (userRestart.equalsIgnoreCase("N")) { // Ends the game if 'N' or 'n'
				gameRestart = false;
			}
				
			System.out.println("");
		}
		
		// Conclusion
		System.out.println("Thank you for playing!");
		
		// Closes scanner
		userInput.close();
	}
	
	// ======= clearConsole Method =======
	// Commented: Clears the console for display purposes (Simulation for now)
	// For now it just acts as a spacer between Player Hits
	public static void clearConsole() {
		
		// System.out.print("\u001B[0m\033[H\033[2J");
		// System.out.flush();
		
		System.out.println("=========================================");
	}
}