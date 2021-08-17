import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {
    public static void main(String args[]){

       // String[] input = args[0].split(" ");



        String player1 = args[0];
        String player2 = args[1];
        String player3 = args[2];

        System.out.println(player1);
        System.out.println(player2);
        System.out.println(player3);


        HashMap<String, String> suitValues = new HashMap<>();
        HashMap<String, String> cardValues = new HashMap<>();
        initializeCardValues(cardValues);
        initializeSuitValues(suitValues);
        Hand player1Hand = null;
        player1Hand = new Hand(player1,"player1");
        ArrayList<String> player1Rank = player1Hand.getRanking(player1Hand);
        if(player1Rank.isEmpty())
        {
            player1Rank.add("highest card:"+player1Hand.getHighestCard());
        }
        computeRanking(player1Hand,player1Rank,cardValues,suitValues);
        Hand player2Hand = new Hand(player2,"player2");
        ArrayList<String> player2Rank = player2Hand.getRanking(player2Hand);
        if(player2Rank.isEmpty())
        {
            player2Rank.add("highest card:"+player2Hand.getHighestCard());
        }
        computeRanking(player2Hand,player2Rank,cardValues,suitValues);
        Hand player3Hand = new Hand(player3,"player3");
        ArrayList<String> player3Rank = player3Hand.getRanking(player3Hand);
        if(player3Rank.isEmpty())
        {
            player3Rank.add("highest card:"+player3Hand.getHighestCard());
        }
        computeRanking(player3Hand,player3Rank,cardValues,suitValues);
        compareRanking(player1Hand, player2Hand, player3Hand, player1, player2, player3);

    }

    private static void initializeSuitValues(HashMap<String, String> suitValues) {
        suitValues.put("C", "Clubs");
        suitValues.put("D", "Diamonds");
        suitValues.put("H", "Hearts");
        suitValues.put("S", "Spades");
    }

    private static void initializeCardValues(HashMap<String, String> cardNumber) {

        cardNumber.put("0", "Twos");
        cardNumber.put("1", "Threes");
        cardNumber.put("2", "Fours");
        cardNumber.put("3", "Fives");
        cardNumber.put("4", "Sixes");
        cardNumber.put("5", "Sevens");
        cardNumber.put("6", "Eights");
        cardNumber.put("7", "Nines");
        cardNumber.put("8", "Tens");
        cardNumber.put("9", "Jack");
        cardNumber.put("10", "Queen");
        cardNumber.put("11", "King");
        cardNumber.put("12", "Ace");
    }

    private static void computeRanking(Hand playerHand,
                                       ArrayList<String> playerRank,
                                       HashMap<String, String> cardValues,
                                       HashMap<String, String> suitValues) {

        if(playerRank.get(0).contains("royal flush"))
        {
            playerHand.setRankString("Royal Flush");
        }
        else if(playerRank.get(0).contains("straight flush:"))
        {
            String[] values = playerRank.get(0).split(":");
            String mapValue = suitValues.get(values[1]);
            playerHand.setRankString("Straight Flush"+", "+mapValue);
        }
        else if(playerRank.get(0).contains("four of a kind:"))
        {
            String[] values = playerRank.get(0).split(":");
            String mapValue = cardValues.get(values[1]);
            playerHand.setRankString("Four of a Kind"+", "+mapValue);
        }
        else if(playerRank.get(0).contains("full house with three:"))
        {
            String[] values = playerRank.get(0).split(":");
            String mapValue = cardValues.get(values[1]);
            playerHand.setRankString("Full House With Three"+", "+mapValue);
        }
        else if(playerRank.get(0).contains("flush:"))
        {
            String[] values = playerRank.get(0).split(":");
            String mapValue = suitValues.get(values[1]);
            playerHand.setRankString("Flush"+", "+mapValue);
        }
        else if(playerRank.get(0).contains("straight"))
        {
            playerHand.setRankString("Straight");
        }
        else if(playerRank.get(0).contains("three of kind:"))
        {
            String[] values = playerRank.get(0).split(":");
            String mapValue = cardValues.get(values[1]);
            playerHand.setRankString("Three of a Kind"+", "+mapValue);
        }
        else if(playerRank.get(0).contains("two pairs:"))
        {
            String[] values = playerRank.get(0).split(":");
            String[] mapValue = values[1].split(" ");
            playerHand.setRankString("Two Pairs"+", "+cardValues.get((mapValue[0]))+" "+
                    cardValues.get((mapValue[1])));
        }
        else if(playerRank.get(0).contains("one pairs:"))
        {
            String[] values = playerRank.get(0).split(":");
            String mapValue = cardValues.get(values[1]);
            playerHand.setRankString("Pair of "+", "+mapValue);
        }
        else if(playerRank.get(0).contains("highest card:"))
        {
            String[] values = playerRank.get(0).split(":");
            String mapValue = cardValues.get(values[1]);
            playerHand.setRankString("Highest Card"+", "+mapValue);
        }

    }

    private static void compareRanking(Hand player1Hand,
                                       Hand player2Hand, Hand player3Hand, String player1, String player2, String player3) {

        Rank rankPlayer1 = new Rank(player1Hand.getRank(), player1Hand.getValues(), player1Hand.getPlayerName());
        Rank rankPlayer2 = new Rank(player2Hand.getRank(), player2Hand.getValues(), player2Hand.getPlayerName());
        Rank rankPlayer3 = new Rank(player3Hand.getRank(), player3Hand.getValues(), player3Hand.getPlayerName());
        HashMap<Integer, String> resultValues = new HashMap<>();
        String insert1 = "Player 1 \t"+player1+" \t "+player1Hand.getRankString();
        String insert2 = "Player 2 \t"+player2+" \t "+player2Hand.getRankString();
        String insert3 = "Player 3 \t"+player3+" \t "+player3Hand.getRankString();
        resultValues.put(player1Hand.getRank(), insert1);
        resultValues.put(player2Hand.getRank(), insert2);
        resultValues.put(player3Hand.getRank(), insert3);
        boolean isTie = true;
        HashMap<String, String> groupResult = new HashMap<>();
        createMap(rankPlayer1, rankPlayer2, rankPlayer3, groupResult, resultValues,
                insert1, insert2, insert3);
        if(rankPlayer1.getRank() != rankPlayer2.getRank() &&
                rankPlayer1.getRank() != rankPlayer3.getRank() &&
                rankPlayer2.getRank() != rankPlayer3.getRank())
        {
            compareValues(rankPlayer1.getRank(), rankPlayer2.getRank(), rankPlayer3.getRank(), resultValues);
        }

        else if(rankPlayer1.getRank() == rankPlayer2.getRank() &&
                rankPlayer1.getRank() != rankPlayer3.getRank())
        {
            System.out.println("Ranking:");
            if(rankPlayer1.getRank() < rankPlayer3.getRank())
            {
                System.out.println("\t 1 \t"+groupResult.get(rankPlayer3.getName()));
                isTie = printResults(rankPlayer1, rankPlayer2, groupResult, 2, rankPlayer3);
                if(isTie)
                {
                    System.out.println("Pot is shared between Player 1 and Player 2");
                    System.out.println(groupResult.get(rankPlayer3.getName()).split(" ")[0]+" "+
                            groupResult.get(rankPlayer3.getName()).split(" ")[1]+" wins");
                }
            }
            else {
                isTie = printResults(rankPlayer1, rankPlayer2, groupResult, 1, rankPlayer3);
                if(isTie)
                {
                    System.out.println("For First Ranking Pot is shared between Player 1 and Player 2");
                    System.out.println("\t 3 \t"+groupResult.get(rankPlayer3.getName()));

                }
            }
        }
        else if(rankPlayer1.getRank() == rankPlayer3.getRank() &&
                rankPlayer1.getRank() != rankPlayer2.getRank())
        {
            System.out.println("Ranking:");
            if(rankPlayer1.getRank() < rankPlayer2.getRank())
            {
                System.out.println("\t 1 \t"+groupResult.get(rankPlayer2.getName()));
                isTie = printResults(rankPlayer1, rankPlayer3, groupResult, 2, rankPlayer2);
                if(isTie)
                {
                    System.out.println("Pot is shared between Player 1 and Player 3");
                    System.out.println(groupResult.get(rankPlayer2.getName()).split(" ")[0]+" "+
                            groupResult.get(rankPlayer2.getName()).split(" ")[1]+" wins");
                }
            }
            else {
                isTie = printResults(rankPlayer1, rankPlayer3, groupResult, 1, rankPlayer2);
                if(isTie)
                {
                    System.out.println("For First Ranking Pot is shared between Player 1 and Player 3");
                    System.out.println("\t 3 \t"+groupResult.get(rankPlayer2.getName()));
                }

            }
        }
        else if(rankPlayer2.getRank() == rankPlayer3.getRank() &&
                rankPlayer1.getRank() != rankPlayer2.getRank())
        {
            System.out.println("Ranking:");
            if(rankPlayer2.getRank() < rankPlayer1.getRank())
            {
                System.out.println("\t 1 \t"+groupResult.get(rankPlayer1.getName()));
                isTie = printResults(rankPlayer2, rankPlayer3, groupResult, 2, rankPlayer1);
                if(isTie)
                {
                    System.out.println("Pot is shared between Player 2 and Player 3");
                    System.out.println(groupResult.get(rankPlayer1.getName()).split(" ")[0]+" "+
                            groupResult.get(rankPlayer1.getName()).split(" ")[1]+" wins");
                }
            }
            else {
                isTie = printResults(rankPlayer2, rankPlayer3, groupResult, 1, rankPlayer1);
                if(isTie)
                {
                    System.out.println("For First Ranking Pot is shared between Player 2 and Player 3");
                    System.out.println("\t 3 \t"+groupResult.get(rankPlayer1.getName()));
                }
            }
        }
        else if(rankPlayer1.getRank() == rankPlayer2.getRank() &&
                rankPlayer1.getRank() == rankPlayer3.getRank() &&
                rankPlayer2.getRank() == rankPlayer3.getRank())
        {
            printResultsForAllEqual(rankPlayer1, rankPlayer2, rankPlayer3, groupResult);

        }

    }

    private static void createMap(Rank rankPlayer1, Rank rankPlayer2, Rank rankPlayer3,
                                  HashMap<String, String> groupResult, HashMap<Integer, String> resultValues,
                                  String insert1, String insert2, String insert3) {
        if(resultValues.keySet().size() <3)
        {
            groupResult.put(rankPlayer1.getName(), insert1);
            groupResult.put(rankPlayer2.getName(), insert2);
            groupResult.put(rankPlayer3.getName(), insert3);
        }


    }

    private static void printResultsForAllEqual(Rank rankPlayer1, Rank rankPlayer2, Rank rankPlayer3, HashMap<String, String> resultValues) {

        int x, y, z;

        boolean istie = true;
        System.out.println("Ranking:");
        int i = 0;
        while(i<5) {
            x = rankPlayer1.getValues().get(i);
            y = rankPlayer2.getValues().get(i);
            z = rankPlayer3.getValues().get(i);

            if (x > y && x > z && y!=z) {
                System.out.println("\t 1 \t"+resultValues.get(rankPlayer1.getName()));
                if(y>z)
                {
                    System.out.println("\t 2 \t"+resultValues.get(rankPlayer2.getName()));
                    System.out.println("\t 3 \t"+resultValues.get(rankPlayer3.getName()));
                    System.out.println(resultValues.get(rankPlayer1.getName()).split(" ")[0]+" "+
                            resultValues.get(rankPlayer1.getName()).split(" ")[1]+" wins");
                }
                else if(z>y)
                {
                    System.out.println("\t 2 \t"+resultValues.get(rankPlayer3.getName()));
                    System.out.println("\t 3 \t"+resultValues.get(rankPlayer2.getName()));
                    System.out.println(resultValues.get(rankPlayer1.getName()).split(" ")[0]+" "+
                            resultValues.get(rankPlayer1.getName()).split(" ")[1]+" wins");
                }
                istie = false;
            }
            else if (y > x && y > z && x!=z)
            {
                System.out.println("\t 1 \t"+resultValues.get(rankPlayer2.getName()));
                if(x>z)
                {
                    System.out.println("\t 2 \t"+resultValues.get(rankPlayer1.getName()));
                    System.out.println("\t 3 \t"+resultValues.get(rankPlayer3.getName()));
                    System.out.println(resultValues.get(rankPlayer2.getName()).split(" ")[0]+" "+
                            resultValues.get(rankPlayer2.getName()).split(" ")[1]+" wins");
                }
                else if(z>x)
                {
                    System.out.println("\t 2 \t"+resultValues.get(rankPlayer3.getName()));
                    System.out.println("\t 3 \t"+resultValues.get(rankPlayer1.getName()));
                    System.out.println(resultValues.get(rankPlayer2.getName()).split(" ")[0]+" "+
                            resultValues.get(rankPlayer2.getName()).split(" ")[1]+" wins");
                }
                istie = false;
            }
            else if (z > x && z > y && x!=y)
            {
                System.out.println("\t 1 \t"+resultValues.get(rankPlayer3.getName()));
                if(x>y)
                {
                    System.out.println("\t 2 \t"+resultValues.get(rankPlayer1.getName()));
                    System.out.println("\t 3 \t"+resultValues.get(rankPlayer2.getName()));
                    System.out.println(resultValues.get(rankPlayer3.getName()).split(" ")[0]+" "+
                            resultValues.get(rankPlayer3.getName()).split(" ")[1]+" wins");
                }
                else if(y>x)
                {
                    System.out.println("\t 2 \t"+resultValues.get(rankPlayer2.getName()));
                    System.out.println("\t 3 \t"+resultValues.get(rankPlayer1.getName()));
                    System.out.println(resultValues.get(rankPlayer3.getName()).split(" ")[0]+" "+
                            resultValues.get(rankPlayer3.getName()).split(" ")[1]+" wins");
                }
                istie = false;
            }
            else if(x==y && x!=z)
            {
                if(z>x)
                {
                    System.out.println("\t 1 "+resultValues.get(rankPlayer3.getName()));
                    istie = printResults(rankPlayer1, rankPlayer2, resultValues, 2, rankPlayer3);
                    if(istie)
                    {
                        System.out.println("Pot is shared between Player 1 and Player 2");
                        System.out.println(resultValues.get(rankPlayer3.getName()).split(" ")[0]+" "+
                                resultValues.get(rankPlayer3.getRank()).split(" ")[1]+" wins");
                    }
                }
                else if(x>z)
                {
                    istie = printResults(rankPlayer1, rankPlayer2, resultValues, 1, rankPlayer3);

                    if(istie)
                    {
                        System.out.println("For First Ranking Pot is shared between Player 1 and Player 2");
                        System.out.println("\t 3 "+resultValues.get(rankPlayer3.getName()));
                    }
                }
                istie = false;
            }
            else if(x==z && x!=y)
            {
                if(y>x)
                {
                    System.out.println("\t 1 "+resultValues.get(rankPlayer2.getName()));
                    istie = printResults(rankPlayer1, rankPlayer3, resultValues, 2, rankPlayer2);
                    if(istie)
                    {
                        System.out.println("Pot is shared between Player 1 and Player 3");
                        System.out.println(resultValues.get(rankPlayer2.getName()).split(" ")[0]+" "+
                                resultValues.get(rankPlayer2.getName()).split(" ")[1]+" wins");
                    }
                }
                else if(x>y)
                {
                    istie = printResults(rankPlayer1, rankPlayer3, resultValues, 1, rankPlayer2);

                    if(istie)
                    {
                        System.out.println("For First Ranking Pot is shared between Player 1 and Player 3");
                        System.out.println("\t 3 "+resultValues.get(rankPlayer2.getName()));
                    }
                }
                istie = false;
            }
            else if(y==z & y!=x)
            {
                if(x>y)
                {
                    System.out.println("\t 1 "+resultValues.get(rankPlayer1.getName()));
                    istie = printResults(rankPlayer2, rankPlayer3, resultValues, 2, rankPlayer1);
                    if(istie)
                    {
                        System.out.println("Pot is shared between Player 2 and Player 3");
                        System.out.println(resultValues.get(rankPlayer1.getName()).split(" ")[0]+" "+
                                resultValues.get(rankPlayer1.getName()).split(" ")[1]+" wins");
                    }
                }
                else if(y>x)
                {
                    istie = printResults(rankPlayer2, rankPlayer3, resultValues, 1, rankPlayer1);

                    if(istie)
                    {
                        System.out.println("For First Ranking Pot is shared between Player 2 and Player 3");
                        System.out.println("\t 3 "+resultValues.get(rankPlayer1.getName()));
                    }
                }
                istie = false;
            }

            if (istie) {
                i++;
            }
            if(!istie)
            {
                break;
            }

        }
        if(istie)
        {
            System.out.println("Pot is shared between Player 1, Player 2 and Player 3");
        }


    }

    private static void compareValues(int rank, int rank1, int rank2, HashMap<Integer, String> result) {

        ArrayList<Integer> resultValues = new ArrayList<>();
        for(int key: result.keySet())
        {
            resultValues.add(key);
        }
        Collections.sort(resultValues, Collections.reverseOrder());
        System.out.println("Ranking:");
        int place = 0;
        for(int i=0; i<3; i++)
        {
            place = i+1;
            System.out.println("\t "+place+"\t "+result.get(resultValues.get(i)));
        }
        System.out.println(result.get(resultValues.get(0)).split(" ")[0]+" "+
                result.get(resultValues.get(0)).split(" ")[1]+" wins");

    }

    public static boolean printResults(Rank rankPlayer1, Rank rankPlayer2, HashMap<String, String> result, int check,
                                       Rank thirdRank)
    {
        boolean player1 = false;
        boolean player2 = false;
        for(int i=0; i<5; i++)
        {
            if(rankPlayer1.getValues().get(i) > rankPlayer2.getValues().get(i))
            {
                if(check == 1)
                {
                    System.out.println("\t 1 \t"+result.get(rankPlayer1.getName()));
                    player1 = true;
                    break;
                }
                else if(check == 2)
                {
                    System.out.println("\t 2 \t"+result.get(rankPlayer1.getName()));
                    player1 = true;
                    break;
                }

            }
            else if(rankPlayer1.getValues().get(i) < rankPlayer2.getValues().get(i))
            {
                if(check == 1) {
                    System.out.println("\t 1 \t" + result.get(rankPlayer2.getName()));
                    player2 = true;
                    break;
                }
                else if(check == 2)
                {
                    System.out.println("\t 2 \t" + result.get(rankPlayer2.getName()));
                    player2 = true;
                    break;
                }
            }
        }
        if(player1 && !player2)
        {
            if(check == 2)
            {
            System.out.println("\t 3 \t"+result.get(rankPlayer2.getName()));
            System.out.println(result.get(thirdRank.getName()).split(" ")[0]+" "+
                        result.get(thirdRank.getName()).split(" ")[1]+" wins");
            return false;
            }
            else if(check == 1)
            {
                System.out.println("\t 2 \t "+result.get(rankPlayer2.getName()));
                System.out.println("\t 3 \t "+result.get(thirdRank.getName()));
                System.out.println(result.get(rankPlayer1.getName()).split(" ")[0]+" "+
                        result.get(rankPlayer1.getName()).split(" ")[1]+" wins");

                return false;
            }

        }
        else if(!player1 && player2)
        {
            if(check == 2) {
                System.out.println("\t 3 \t" + result.get(rankPlayer1.getName()));
                System.out.println(result.get(thirdRank.getName()).split(" ")[0]+" "+
                        result.get(thirdRank.getName()).split(" ")[1]+" wins");
                return false;
            }
            else if(check == 1)
            {
                System.out.println("\t 2 \t" +result.get(rankPlayer1.getName()));
                System.out.println("\t 3 \t "+result.get(thirdRank.getName()));
                System.out.println(result.get(rankPlayer2.getName()).split(" ")[0]+" "+
                        result.get(rankPlayer2.getName()).split(" ")[1]+" wins");
                return false;
            }

        }

            return true;
    }

}
