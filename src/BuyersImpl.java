
public class BuyersImpl implements Methods {

    private final String buyerId;
    private final String buyertName;
    private final String buyertLastname;

    private final String[] listNote;

    public BuyersImpl (String string) {
        listNote = getData(string);
        this.buyerId = listNote[0];
        this.buyertName = listNote[1];
        this.buyertLastname = listNote[2];
    }

    public void print() {
        System.out.printf("%10s %20s %20s",
                        this.buyerId,
                        this.buyertName,
                        this.buyertLastname + "\n"
        );
    }

}