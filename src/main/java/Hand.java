import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.*;

public class Hand {

    private String playerName;
    private Card[] playerCard;
    private ArrayList<String> suits;
    private ArrayList<Integer> values;
    private int highestCard;
    private ArrayList<Integer> cardValues;
    private int rank;

    public String getRankString() {
        return rankString;
    }

    public void setRankString(String rankString) {
        this.rankString = rankString;
    }

    private String rankString;

    Hand(String hand, String name) {
        String[] cards = hand.split(" ");
        suits = new ArrayList<>();
        values = new ArrayList<>();
        this.playerCard = new Card[cards.length];
        for (int i=0;i<this.playerCard.length;i++){
            this.playerCard[i] = new Card(cards[i], suits, values);
        }
        this.playerName = name;
    }



    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Card[] getPlayerCard() {
        return playerCard;
    }

    public void setPlayerCard(Card[] playerCard) {
        this.playerCard = playerCard;
    }

    public ArrayList<String> getSuits() {
        return suits;
    }

    public void setSuits(ArrayList<String> suits) {
        this.suits = suits;
    }

    public ArrayList<Integer> getValues() {
        return values;
    }

    public void setValues(ArrayList<Integer> values) {
        this.values = values;
    }

    public int getHighestCard() {
        return highestCard;
    }

    public void setHighestCard(int highestCard) {
        this.highestCard = highestCard;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public ArrayList<String> getRanking(Hand playerHand) {
        ArrayList<String> ranking = new ArrayList<>();
        //Card[] cards = player1Hand.getPlayerCard();
        ArrayList<Integer> valuesList = values;
        Collections.sort(valuesList, Collections.reverseOrder());
        setHighestCard(valuesList.get(0));
        cardValues = new ArrayList<>();
        setCardValues(cardValues,playerHand);
        if(checkForRoyalFlush(playerHand))
        {
            ranking.add("royal flush");
            setRank(10+13+130);
            return ranking;
        }
        if(checkForStraightFlush(playerHand))
        {
            ranking.add("straight flush:"+suits.get(0));
            setRank(9+13+117);
            return ranking;
        }
        int fourOfKind = checkForFourOfAKind(playerHand);
        if( fourOfKind != -1)
        {
            ranking.add("four of a kind:"+fourOfKind);
            setRank(8+fourOfKind+104);
            return ranking;
        }
        int forFullHouse = checkForFullHouse(playerHand);
        if(forFullHouse != -1)
        {
            ranking.add("full house with three:"+forFullHouse);
            setRank(7+forFullHouse+91);
            return ranking;
        }
        if(checkForFlush(playerHand))
        {
            ranking.add("flush:"+suits.get(0));
            setRank(6+13+78);
            return ranking;
        }
        if(checkForStraight(playerHand))
        {
            ranking.add("straight");
            setRank(5+13+65);
            return ranking;
        }
        int threeOfAKind = checkForThreeOfAKind(playerHand);
        if(threeOfAKind != -1)
        {
            ranking.add("three of kind:"+threeOfAKind);
            setRank(4+threeOfAKind+52);
            return ranking;
        }
        ArrayList<Integer> twoOfPair = checkForTwoPairs(playerHand);
        if(twoOfPair.size() > 1)
        {
            ranking.add("two pairs:"+twoOfPair.get(0)+" "+twoOfPair.get(1));
            setRank(3+twoOfPair.get(0)+twoOfPair.get(1)+26);
            return ranking;
        }
        int onePair = checkForOnePair(playerHand);
        if(onePair!=-1)
        {
            ranking.add("one pairs:"+onePair);
            setRank(2+onePair+13);
            return ranking;
        }
        if(ranking.isEmpty())
        {
            setRank(highestCard+0);
        }

        return ranking;
    }

    private void setCardValues(ArrayList<Integer> cardValues, Hand playerHand) {

        int numberOfPairs = 0;
        Card[] cards = playerHand.getPlayerCard();
        for(int i=0; i<13; i++)
        {
            cardValues.add(0);
        }
        for(int i = 0; i < cards.length; i++){

            int val = cardValues.get(cards[i].getValue());
            val++;
            cardValues.set(cards[i].getValue(), val);


        }

    }

    private int checkForOnePair(Hand playerHand) {

        ArrayList<Integer> addValues = new ArrayList<>();
        for(int i = 0; i < cardValues.size(); i++){

            if(cardValues.get(i) == 2){

               // if(!checkForTwoPairs(playerHand) && !checkForFullHouse(playerHand))

                {
                    addValues.add(i);
                }

            }

        }
        if(!addValues.isEmpty())
        {
            return addValues.get(0);
        }
        return -1;

    }

    private ArrayList<Integer> checkForTwoPairs(Hand playerHand) {

        ArrayList<Integer> addValues = new ArrayList<>();
        int numberOfPairs = 0;
        for(int i = 0; i < cardValues.size(); i++){

            if(cardValues.get(i) == 2){

                addValues.add(i);
                numberOfPairs++;

            }

        }

        if(numberOfPairs == 2){
            return addValues;
        }

        return addValues;
    }

    private boolean checkForStraight(Hand playerHand) {
        Card[] cards = playerHand.getPlayerCard();
        ArrayList<Integer> valuesList = values;
        Collections.sort(valuesList);
        if(checkForConsecutive(valuesList))
        {
            if(Collections.frequency(suits, suits.get(0)) != suits.size())
            {
                return true;
            }

        }

        return false;
    }

    private boolean checkForFlush(Hand playerHand) {
        ArrayList<String> suits = playerHand.getSuits();
        if(Collections.frequency(suits, suits.get(0)) == suits.size())
        {
            return true;
        }

        return false;
    }

    private int checkForFullHouse(Hand playerHand) {

        ArrayList<Integer> addValues = new ArrayList<>();
        boolean hasThreeOfAKind = false;
        boolean hasAPair = false;


        for(int i = 0; i < cardValues.size(); i++){

            if(cardValues.get(i) == 3){

                addValues.add(i);
                //hasThreeOfAKind = true;

            }

            if(cardValues.get(i) == 2){

                hasAPair = true;

            }

        }

        if(hasAPair && !addValues.isEmpty()){

            return addValues.get(0);

        }

        return -1;
    }



    private int checkForThreeOfAKind(Hand playerHand) {

        ArrayList<Integer> addValues = new ArrayList<>();

        for(int i = 0; i < cardValues.size(); i++){
            if(cardValues.get(i) == 3){
                if(checkForFullHouse(playerHand) == -1)
                {
                    addValues.add(i);

                }
            }
        }
        if(!addValues.isEmpty()) {
            Collections.sort(addValues, Collections.reverseOrder());
            return addValues.get(0);
        }

        return -1;

    }

    private int checkForFourOfAKind(Hand playerHand) {

        ArrayList<Integer> addValues = new ArrayList<>();
        for(int i = 0; i < cardValues.size(); i++){

            if(cardValues.get(i) == 4){
                addValues.add(i);
            }

        }

        if(!addValues.isEmpty()) {
            return addValues.get(0);
        }

        return -1;

    }

    private boolean checkForStraightFlush(Hand playerHand) {

        ArrayList<String> suits = playerHand.getSuits();
        ArrayList<Integer> valuesList = values;
        Collections.sort(valuesList);
        if(checkForConsecutive(valuesList))
        {

                if(Collections.frequency(suits, suits.get(0)) == suits.size())
                {
                    return true;
                }


        }

        return false;
    }

    private boolean checkForConsecutive(ArrayList<Integer> valuesList) {
        int max = valuesList.get(4);
        int min = valuesList.get(0);
        int n = valuesList.size();
        if (max - min + 1 == n)
        {
            /* Create a temp array to hold visited flag of all elements.
               Note that, calloc is used here so that all values are initialized
               as false */
            boolean visited[] = new boolean[n];
            int i;
            for (i = 0; i < n; i++)
            {
                /* If we see an element again, then return false */
                if (visited[valuesList.get(i) - min] != false)
                    return false;

                /* If visited first time, then mark the element as visited */
                visited[valuesList.get(i) - min] = true;
            }

            /* If all elements occur once, then return true */
            return true;
        }
        return false;
    }

    private boolean checkForRoyalFlush(Hand playerHand) {

        Card[] cards = playerHand.getPlayerCard();
        ArrayList<Integer> values = playerHand.getValues();
        ArrayList<String> suits = playerHand.getSuits();


            if(Collections.frequency(suits, suits.get(0)) == suits.size())
            {
                if(values.contains(8) && values.contains(9) && values.contains(10)
                        && values.contains(11) && values.contains(12))
                {
                    return true;
                }
            }


        return false;

    }
}
