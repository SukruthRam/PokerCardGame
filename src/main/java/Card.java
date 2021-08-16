import java.util.ArrayList;
import java.util.List;

public class Card {

    private int value;
    private String suit;

    public Card(String str, List<String> suits, List<Integer> values){

        if(!str.contains("10"))
        {
            value = checkForRanking(String.valueOf(str.charAt(0)));
            //value = String.valueOf(str.charAt(0));
            suit = String.valueOf(str.charAt(1));
            suits.add(suit);
            values.add(value);
        }
        else if(str.contains("10"))
        {
            value = 8;
            //value = String.valueOf(str.charAt(0));
            suit = String.valueOf(str.charAt(2));
            suits.add(suit);
            values.add(value);
        }

    }

    private int checkForRanking(String valueOf) {

        if(valueOf.equals("T"))
            return 8;
        else if(valueOf.equals("J"))
            return 9;
        else if(valueOf.equals("Q"))
            return 10;
        else if(valueOf.equals("K"))
            return 11;
        else if(valueOf.equals("A"))
            return 12;
        else
        {
            return Integer.parseInt(valueOf) - 2;
        }

    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }
}
