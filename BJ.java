import java.util.*;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.String;

public class blackJack {
    int rc = 52;
    Random r = new Random();//this will be used to pick random cards from the deck mentioned above
    List<String> p1 = new ArrayList<String>();//this is the player's hand
    List<String> dealer = new ArrayList<String>();//this is the dealer's hand
    static final ArrayList deck = new ArrayList();
    Scanner s = new Scanner(System.in);
    int p1Total = 0;
    int aceChoice = 0;
    int dealerTotal = 0;
    boolean BJ;
    boolean bust;


    public blackJack(){
        super();
        setDeck(deck);
    }

    public ArrayList setDeck(List<String> deck){
        //sets deck of all non numerical cards
        deck.add("Ace of Clubs");
        deck.add("Ace of Spades");
        deck.add("Ace of Hearts");
        deck.add("Ace of Diamonds");
        deck.add("J of Clubs");
        deck.add("J of Spades");
        deck.add("J of Hearts");
        deck.add("J of Diamonds");
        deck.add("Q of Clubs");
        deck.add("Q of Spades");
        deck.add("Q of Hearts");
        deck.add("Q of Diamonds");
        deck.add("K of Clubs");
        deck.add("K of Spades");
        deck.add("K of Diamond");
        deck.add("K of Hearts");
        //sets deck of all numerical cards
        int dc = 2;
        for (int i = 16; i <= 24; i++) {
            deck.add(dc + " of Clubs");
            dc++;
        }
        dc = 2;
        for (int i = 25; i <= 33; i++) {
            deck.add(dc + " of Spades");
            dc++;
        }
        dc = 2;
        for (int i = 34; i <= 42; i++) {
            deck.add(dc + " of Hearts");
            dc++;
        }
        dc = 2;
        for (int i = 43; i <= 51; i++) {
            deck.add(dc + " of Diamonds");
            dc++;
        }
        return null;
    }

    public boolean deal() {
        int c1 = r.nextInt(rc);
        rc--;
        int c2 = r.nextInt(rc);
        rc--;
        int c3 = r.nextInt(rc);
        rc--;
        int c4 = r.nextInt(rc);
        rc--;
        p1.add((String) deck.get(c1));
        deck.remove(c1);
        p1.add((String) deck.get(c2));
        deck.remove(c2);
        dealer.add((String) deck.get(c3));
        deck.remove(c3);
        dealer.add((String) deck.get(c4));
        deck.remove(c4);
        System.out.println("The dealer's cards: " + dealer.get(0));
        System.out.println("Your hand: " + p1.get(0) + ", " + p1.get(1));
        for (String h : p1) {
            if (h.startsWith("A")){
                AC();
            }
        }
        return isBJ();
    }
    public String dealer() {
        System.out.println("Enter");
        converter9000(dealer);
        String dc;
        if (dealerTotal <= 15) {
            int h1 = r.nextInt(rc);
            rc--;
            dealer.add((String) deck.get(h1));
            deck.remove(h1);
            dc = "The dealer chose to hit";
            System.out.println(dealer);
        } else if (dealerTotal >= 16) {
            dc = "The dealer chose to stay";
        } else {
            dc = "There is an error";
        }
        System.out.println(dc);
        winloose();
        return dc;
    }

    public boolean isBust() {
        if (p1Total > 21) {
            System.out.println("You Busted. Dealer wins");
            bust = true;
            System.exit(69);
        }else{
            bust = false;
        }
        return bust;
    }

    public boolean isBJ() {
        if(p1Total == 21){
            BJ = true;
            System.out.println("You Win!");
            System.exit(69);
        }else{
            BJ = false;
        }
        return BJ;
    }


    public void hitStay() {
        System.out.println("hit (h) or stay (s)");
        String hs = s.nextLine();
        if (hs.equalsIgnoreCase("h")) {
            int h1 = r.nextInt(rc);
            rc--;
            p1.add((String) deck.get(h1));
            deck.remove(h1);
            System.out.println(p1);
            converter9000(p1);
            isBJ();
            isBust();
            hitStay();
        } else if (hs.equalsIgnoreCase("s")) {
            //show hand
            System.out.print("Your hand: ");
            for (int i = 0; i < p1.size(); i++) {
                System.out.print(p1.get(i) + ",");
            }
            dealer();
        } else {
            System.out.println("Invalid input.");
            hitStay();
        }
    }
    //converter
    public int converter9000 (List<String> hand){
        int totalIn = 0;
        for (String card : hand) {
            if (card.startsWith("K")) {
                totalIn = totalIn + 10;
            } else if (card.startsWith("Q")) {
                totalIn = totalIn + 10;
            } else if (card.startsWith("J")) {
                totalIn = totalIn + 10;
            } else if (card.startsWith("1")) {
                totalIn = totalIn + 10;
            } else if (card.startsWith("9")) {
                totalIn = totalIn + 9;
            } else if (card.startsWith("8")) {
                totalIn = totalIn + 8;
            } else if (card.startsWith("7")) {
                totalIn = totalIn + 7;
            } else if (card.startsWith("6")) {
                totalIn = totalIn + 6;
            } else if (card.startsWith("5")) {
                totalIn = totalIn + 5;
            } else if (card.startsWith("4")) {
                totalIn = totalIn + 4;
            } else if (card.startsWith("3")) {
                totalIn = totalIn + 3;
            } else if (card.startsWith("2")) {
                totalIn = totalIn + 2;
            } else if (card.startsWith("A")) {
                totalIn = totalIn + AC();
            }
        }
        if(hand == p1){
            System.out.println("Players Total: " + totalIn);
            p1Total = totalIn;
        }else{
            System.out.println("Dealers Total: " + totalIn);
            dealerTotal = totalIn;
        }

        return totalIn;
    }
    public String winloose(){
        boolean endgame = false;
        if (p1Total>dealerTotal){
            endgame = true;
        }
        if(endgame){
            System.out.println("GG! You won");
        }else if (!endgame){
            System.out.println("HAHA! you lost");
        }else{
            System.out.println("System Error");
        }
        return null;
    }
    public int AC(){
        System.out.println("Do you want your ace to have the value of 1 or 11?");
        int sbChoice = s.nextInt();
        if (sbChoice == 1){
            aceChoice = 1;
        }else if (sbChoice == 11){
            aceChoice = 11;
        }else{
            System.out.println("Invalid input dummy.");
        }
        return aceChoice;
    }
}
