
public class SellersImpl implements Methods {

    private final String sellerId;
    private final String sellertName;
    private final String sellertLastname;

    private final String[] listNote;

    public SellersImpl (String string) {
        listNote = getData(string);
        this.sellerId = listNote[0];
        this.sellertName = listNote[1];
        this.sellertLastname = listNote[2];
    }

    public void print() {
        System.out.printf("%s %20s %20s",
                this.sellerId,
                this.sellertName,
                this.sellertLastname + "\n"
        );
    }

}