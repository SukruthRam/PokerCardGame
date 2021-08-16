import java.util.ArrayList;
import java.util.Collections;

public class Rank {

    int rank;
    ArrayList<Integer> values;
    private String name;

    public Rank(int rank, ArrayList<Integer> values, String name)
    {
        this.rank = rank;
        Collections.sort(values, Collections.reverseOrder());
        this.values = values;
        this.name = name;
    }


    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public ArrayList<Integer> getValues() {
        return values;
    }

    public void setValues(ArrayList<Integer> values) {
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
