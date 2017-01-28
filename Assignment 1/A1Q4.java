import java.util.Scanner;
import java.util.Random;

/**
 * The class <b>A1Q4</b> is an implementation of the
 * ``Old Maid'' card game, based on the Python implementation
 * given in ITI1020
 *
 * @author gvj (gvj@eecs.uottawa.ca)
 *
 */

public class A1Q4{
  
  /**
   * Array used to store the full deck of cards,
   */
  private static String[] deck;
  
  /**
   * The current number of cards in the full deck of cards
   */
  private static int sizeDeck;
  
  /**
   * Array used to store the player's deck of cards
   */
  private static String[] playerDeck;
  
  /**
   * The current number of cards in the player's deck of cards
   */
  private static int sizePlayerDeck;
  
  /**
   * Array used to store the computer's deck of cards
   */
  private static String[] computerDeck;
  
  /**
   * The current number of cards in the computer's deck of cards
   */
  private static int sizeComputerDeck;
  
  
  /**
   * An instance of java.util.Scanner, which will get input from the
   * standard input
   */
  private static Scanner sc;
  
  /**
   * An instance of java.util.Random, to generate random numbers
   */
  private static Random generator;
  /**
   * Because the english langauge is annoying
   */
  private static final String[] ENDINGS={"st", "nd", "rd", "th"};
  /** 
   * Constructor of the class. Creates the full deck of cards
   */
  
  public  A1Q4(){
    
    sc = new Scanner(System.in);
    generator = new Random();
    
    String[] suits = {"\u2660", "\u2661", "\u2662", "\u2663"};
    String[] ranks = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    sizeDeck = suits.length*ranks.length - 1;
    deck = new String[sizeDeck];
    int index = 0;
    for(int i =0 ; i < ranks.length; i++){
      for(int j =0 ; j < suits.length; j++){
        if(!(ranks[i]=="Q" && suits[j]=="\u2663")){
          deck[index++]= ranks[i] + " of " + suits[j];
        }
      }
    }
  }
  
  /** 
   * Waits for user input
   */
  private static void waitForUserInput(){
    sc.nextLine();
  }
  
  /**
   *  Deals the cards, taking sizeDeck cards out of deck, and deals them
   *  into playerDeck and computerDeck, starting with playerDeck
   */
  private static void dealCards(){
    playerDeck = new String[26];
    computerDeck = new String[25]; 
    while(sizeDeck > 0) {
      if (sizeDeck > 0){
        String card = deck[0];
        sizePlayerDeck = ArrayStringsTools.appendItem(playerDeck,sizePlayerDeck,card);
        sizeDeck = ArrayStringsTools.removeItemByIndex(deck,sizeDeck,0);
      }
      if (sizeDeck > 0){
        String card = deck[0];
        sizeComputerDeck = ArrayStringsTools.appendItem(computerDeck,sizeComputerDeck,card);
        sizeDeck = ArrayStringsTools.removeItemByIndex(deck,sizeDeck,0);
      }
    }
  }
  
  /**
   *  Removes all the pairs of cards from the array deckOfCards, that currently
   * contains currentSize cards. deckOfCards is unsorted. It should also not
   * be sorted once the method terminates. 
   *
   *   @param deckOfCards the array of Strings representing the deck of cards
   *   @param currentSize the number of strings in the arrayOfStrings,
   *   stored from arrayOfStrings[0] to arrayOfStrings[currentSize-1] 
   *   @return the number of cards in deckOfCards once the pair are removed
   */
  private static int removePairs(String[] deckOfCards, int currentSize){
    ArrayStringsTools.sortArray(deckOfCards,currentSize);
    
    int i = 0;
    while(i < currentSize-1){
      if(deckOfCards[i].charAt(0) == deckOfCards[i+1].charAt(0)){
        currentSize = ArrayStringsTools.removeItemByIndex(deckOfCards,currentSize,i);
        currentSize = ArrayStringsTools.removeItemByIndex(deckOfCards,currentSize,i);
      }
      else i++;
    }
    return currentSize;
  }
  
  /**
   *  Get a valid index of a card to be removed from computerDeck.
   * Note: this method does not check that the input is indeed an integer and
   * will NOT crash if something else is provided.
   *  @return the valid input.
   */
  private static int getValidInput(){
    int input;
    System.out.println("I have "+sizeComputerDeck+" cards. If 1 stands for my first card and ");
    System.out.println(sizeComputerDeck+" for my last card, which of my cards would you like?");
    System.out.println("Give me an integer between 1 and "+sizeComputerDeck+": ");
    try{
      input = Integer.parseInt(sc.nextLine());
    }catch(NumberFormatException e){
      System.err.println("Enter an integer");
      return getValidInput();
    }
    if (!((0<input) && ( input < sizeComputerDeck))){
      System.err.println("Enter an integer between 1 and "+sizeComputerDeck);
      return getValidInput();
    }
    else 
      return input;
  }
  
  
  /**
   *  The actual game, as per the Python implementation
   */
  public static void playGame(){
    ArrayStringsTools.shuffleArray(deck,sizeDeck);
    dealCards();
    System.out.println("Hello. My name is Robot and I am the dealer.");
    System.out.println("Welcome to my card game!");
    System.out.println("Your current deck of cards is:");
    ArrayStringsTools.printArray(playerDeck,sizePlayerDeck);
    System.out.println("Do not worry. I cannot see the order of your cards");
    
    System.out.println("Now discard all the pairs from your deck. I will do the same.");
    waitForUserInput();
    
    sizePlayerDeck = removePairs(playerDeck,sizePlayerDeck);
    sizeComputerDeck = removePairs(computerDeck,sizeComputerDeck);
    
    boolean turn = true;
    while((sizeComputerDeck != 0) && (sizePlayerDeck != 0)){
      if(turn) {// player turn
        System.out.println("***********************************************************");
        System.out.println("Your turn.");
        System.out.println("\nYour current deck of cards is:");
        ArrayStringsTools.printArray(playerDeck,sizePlayerDeck);
        
        int choice = getValidInput();
        String card = computerDeck[choice-1];
        sizeComputerDeck = ArrayStringsTools.removeItemByIndex(computerDeck,sizeComputerDeck,choice-1);
        //handled the four endings of ordinals in english
        int ord_index;
        if (choice>3)
          ord_index=3;
        else
          ord_index=choice-1;
        
        System.out.println("You asked for my "+choice+ENDINGS[ord_index]+" card.");
        
        System.out.println("Here it is. It is "+card);
        
        sizePlayerDeck = ArrayStringsTools.appendItem(playerDeck,sizePlayerDeck,card);
        System.out.println("\nWith "+card+ " added, your current deck of cards is:");
        ArrayStringsTools.printArray(playerDeck,sizePlayerDeck);
        
        System.out.println("And after discarding pairs and shuffling, your deck is:");
        sizePlayerDeck = removePairs(playerDeck,sizePlayerDeck);
        ArrayStringsTools.printArray(playerDeck,sizePlayerDeck);
        
        waitForUserInput();
        turn=false;
      }
      else{//computer turn
        System.out.println("***********************************************************");
        System.out.println("My turn.\n");
        int choice =generator.nextInt(sizePlayerDeck);
        String card =playerDeck[choice];
        
        sizePlayerDeck = ArrayStringsTools.removeItemByIndex(playerDeck,sizePlayerDeck,choice);
        sizeComputerDeck = ArrayStringsTools.appendItem(computerDeck,sizeComputerDeck,card);
        sizeComputerDeck = removePairs(computerDeck,sizeComputerDeck);
        
        int ord_index;
        if (choice>2)
          ord_index=2;
        else
          ord_index=choice;
        
        System.out.println("I took your "+(choice+1)+ENDINGS[ord_index]+" card.");
        
        waitForUserInput();
        turn=true;
      }
    }
    
    if (sizePlayerDeck == 0){
      System.out.println("Ups. I do not have any more cards");
      System.out.println("You lost! I, Robot, win");
    }
    else{
      System.out.println("***********************************************************");
      System.out.println("Ups. You do not have any more cards");
      System.out.println("Congratulations! You, Human, win");
    }
  }
  
  
  /**
   * The main method of this program. Creates the game object
   * and calls the playGame method on it.
   * @param args ignored
   */    
  
  
  public static void main(String[] args){
    
    A1Q4 game = new A1Q4();  
    
    game.playGame();
    
    
  }
}

