import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
public class A1Q4{
  private static String[] computerDeck, playerDeck, deck;
  private static Random generator = new Random();
  private static Scanner sc = new Scanner(System.in);
  private static int sizeComputerDeck, sizeplayerDeck, sizeDeck;
  //constants
  private static final char[] SUITS = {'\u2660', '\u2661', '\u2662', '\u2663'};
  private static final String[] RANKS = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
  
  private static void makeDeck(){
    deck = new String[SUITS.length*RANKS.length];
    int i = 0;
    for (String rank : RANKS){
      for(char suit : SUITS){
        deck[i] = rank+suit;
        i++;
      }
    }
    shuffle(deck);
  }
  private static void printDeck(String[] deckOfCards){
    for (String card : deckOfCards){
      System.out.print(card+" ");
    }
  }
  private static void shuffle(String[] deckOfCards){
    for (int i = deckOfCards.length; i > 0; i--){
      int toSwap = generator.nextInt(i-1);
      String tmp = deckOfCards[i];
      deckOfCards[i] = deckOfCards[toSwap];
    }
  }
  private static String[] removePairs(String[] deckOfCards, int currentSize){
    Arrays.sort(deckOfCards);
    return deckOfCards;
  }
  public static void main(String [] args){
    makeDeck();
    printDeck(deck);
  }
}