# Blackjack — Java Console Game

A text-based Blackjack game developed in Java using object-oriented programming. Players compete against an automated dealer using a standard 52-card deck, following traditional Blackjack rules.

## Features

- **52-Card Deck:** Generates and shuffles a standard deck of playing cards.
- **Interactive Gameplay:** Players can choose to Hit or Stand through the console.
- **Automated Dealer:** The dealer draws cards until reaching a hand value of at least 17.
- **Dynamic Ace Values:** Aces count as 11 or 1 depending on the hand's total.
- **Game Outcomes:** Detects natural Blackjacks, busts, wins, losses, and ties.
- **Input Validation:** Handles invalid numerical and non-numerical input.
- **Multiple Rounds:** Allows players to restart with a newly shuffled deck.
- **Game Statistics:** Tracks wins, ties, and losses throughout the session.
- **Console Formatting:** Uses colored text and playing-card suit symbols.

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Java Collections Framework (`ArrayList`, `Collections.shuffle`)
- Eclipse IDE
- JDK 27

## Project Structure

The application consists of five Java classes:

- `Main.java` — Handles console interaction, user input, and the game loop.
- `BlackjackGame.java` — Coordinates gameplay and determines the winner.
- `Card.java` — Represents individual cards, ranks, suits, and values.
- `Deck.java` — Creates, shuffles, and deals cards.
- `Hand.java` — Manages cards in a hand and calculates hand values.

## How to Run

1. Install Java JDK 27 or another compatible JDK.
2. Download or clone this repository.
3. Open the project in Eclipse or another Java IDE.
4. Locate `src/Blackjack_Project/Main.java`.
5. Run `Main.java` as a Java application.
6. Follow the console prompts to play Blackjack.

## Author

Daniel Melendez Menendez
