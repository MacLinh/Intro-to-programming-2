import java.util.Scanner;
import java.util.Random;

public class A1Q4 {

	private static String[] deck, playerDeck, computerDeck;

	private static int sizeDeck, sizePlayerDeck, sizeComputerDeck;

	private static Scanner sc = new Scanner(System.in);

	private static Random generator = new Random();

	private static final String[] SUITS = {"\u2660", "\u2661", "\u2662", "\u2663"};
	private static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

	private static void makeDeck() {
		sizeDeck = (SUITS.length*RANKS.length)-1;
		deck = new String[sizeDeck];
		int index = 0;
		for (String rank : RANKS) {
			for (String suit: SUITS) {
				if (!(rank.equals("Q") && suit=="\u2663")) {
					deck[index++] = rank+suit;
				}
			}
		}
	}

	private static void waitForUserInput() {
		sc.nextLine();
	}

	private static void dealCards() {

		for (int i = 0; i<sizeDeck; i+=2) {
			ArrayStringTools.appendItem(playerDeck, sizePlayerDeck+1, deck[i]);
			ArrayStringTools.appendItem(computerDeck, sizeComputerDeck+1, deck[i+1]);
		}
	}

//*******************
	private static int removePairs(String[] deckOfCards, int currentSize) {
		String[] noPairs;

		ArrayStringTools.sortArray(deckOfCards, currentSize);

		for (int x = 0; x<currentSize; x++) {
			for (int y = 0; y<currentSize; y++) {
				if ((x != y) && (deckOfCards[x][:-1] == deckOfCards[y][:-1])) {
					ArrayStringTools.removeItemByIndex(deckOfCards, currentSize, deckOfCards[y]);
				}
			}
		}

		ArrayStringTools.shuffleArray(deckOfCards, currentSize);

		for (int x = 0; x<currentSize; x++) {
			String[] pairs;
			int last = -1;

			for (int y=0; y<currentSize; y++) {
				if ((x != y) && (deckOfCards[x][:-1] == deckOfCards[y][:-1])) {
					
					if (pairs.count(x) == 0) {
						ArrayStringTools.appendItem(pairs, pairs.length, x);
					}
					if (pairs.count(y) == 0) {
						ArrayStringTools.appendItem(pairs, pairs.length, y);
					}
					
				}
				}

			if ((pairs.length > 0) && (pairs.length % 2 == 1)) {
				ArrayStringTools.sortArray(pairs, pairs.length);
				last = pairs[pairs.length-1];
			}

			if (pairs.length == 0) {
				ArrayStringTools.appendItem(noPairs, noPairs.length, deckOfCards[x]);
			} else if (last == x) {
				ArrayStringTools.appendItem(noPairs, noPairs.length, deckOfCards[last]);
			}
			}

			RETURN INT??

	}

	private static int getValidInput(int size) {
		int validInput;
		validInput = sc.nextInt();

		while (validInput < 1 || validInput > size) {
			System.out.print(" Invalid number. Please enter integer between 1 and " + size + ": ");
			validInput = sc.nextInt();
		}

		return validInput;
	}

	public static void playGame() {
		makeDeck();
		ArrayStringTools.shuffleArray(deck, sizeDeck);
		dealCards();
		//tmp??

		// make deck, shuffle, deal
		System.out.println("Hello. My Name is Robot and I am the dealer.");
		System.out.println("Welcome to my card game!");
		System.out.println("Your current deck of cards is: ");
		ArrayStringTools.printArray(playerDeck, sizePlayerDeck);

		//PRINT HUMAN DECK
		ArrayStringTools.printArray(playerDeck, sizePlayerDeck);
		System.out.println("Do not worry, I cannot see the order of your cards");

		System.out.print("Now discard all the pairs from your deck. I will do the same.");
		waitForUserInput();
//***************
		dealer = removePairs(computerDeck);
		human = removePairs(playerDeck);

		int wanted_card, computer_wanted_card;
		String super_script, c_super_script;

		while (sizeComputerDeck >= 0 && sizePlayerDeck >= 0) {
			System.out.println("***********************************************************");

			System.out.println("Your turn.");
			System.out.println("Your current deck of cards is: ");
			ArrayStringTools.printArray(playerDeck, sizePlayerDeck);

			System.out.println("I have " + sizeComputerDeck + " cards. If 1 stands for my first card and " + sizeComputerDeck + " for my last card, which of my cards would you like?");
			wanted_card = getValidInput(sizeComputerDeck);

			if (wanted_card == 1) {
				super_script = "st";
			} else if (wanted_card == 2) {
				super_script = "nd";
			} else if (wanted_card == 3) {
				super_script = "rd";
			} else {
				super_script = "th";
			}

			System.out.println("You asked for my " + wanted_card + super_script + " card.");
			System.out.println("Here it is. It is " + computerDeck[wanted_card-1]);

			System.out.println("\nWith " + computerDeck[wanted_card-1] + " added, your current deck of cards is: ");
			ArrayStringTools.appendItem(playerDeck, sizePlayerDeck+1, computerDeck[wanted_card-1]);
			ArrayStringTools.removeItemByIndex(computerDeck, sizeComputerDeck-1, wanted_card-1);
			ArrayStringTools.printArray(playerDeck, sizePlayerDeck);

			System.out.println("And after discarding pairs and shuffling, your deck is: ");
			removePairs(playerDeck);
			ArrayStringTools.printArray(playerDeck, sizePlayerDeck);

			waitForUserInput();

			System.out.println("***********************************************************");

			if (sizeComputerDeck == 0) {
				System.out.println("Ups. I do not have any more cards");
				System.out.println("You lost! I, Robot, win");
				return;
			}

			System.out.println("My turn.");
			computer_wanted_card = generator.nextInt(sizePlayerDeck) + 1;

			if (computer_wanted_card == 1) {
				c_super_script = "st";
			} else if (computer_wanted_card == 2) {
				c_super_script = "nd";
			} else if (computer_wanted_card == 3) {
				c_super_script = "rd";
			} else {
				c_super_script = "th";
			}

			System.out.println("I took your " + computer_wanted_card + c_super_script + " card.");
			ArrayStringTools.appendItem(computerDeck, sizeComputerDeck+1, playerDeck[computer_wanted_card-1]);
			ArrayStringTools.removeItemByIndex(playerDeck, sizePlayerDeck-1, computer_wanted_card-1);

			removePairs(computerDeck);

			waitForUserInput();

			if (sizePlayerDeck == 0) {
				System.out.println("Ups. You do not have any more cards");
				System.out.println("Congratulations! You, Human, win");
				return;
			}

		}

	}

	public static void main(String[] args) {
		makeDeck();
		ArrayStringTools.printArray(deck, sizeDeck);
		ArrayStringTools.shuffleArray(deck, sizeDeck);
		ArrayStringTools.printArray(deck, sizeDeck);
		dealCards();
		ArrayStringTools.printArray(computerDeck, sizeComputerDeck);
		ArrayStringTools.printArray(playerDeck, sizePlayerDeck);

		A1Q4 game = new A1Q4();

		game.playGame();
	}
}


