
public class BidsImpl implements Methods {

    private final String bidId;
    private final String bidStep;
    private final String bidCurrent;
    private final String buyerId;
    private final String productId;

    private final String[] listNote;

    public BidsImpl(String string) {
        listNote = getData(string);
        this.bidId = listNote[0];
        this.bidStep = listNote[1];
        this.bidCurrent = listNote[2];
        this.buyerId = listNote[3];
        this.productId = listNote[4];
    }

    public void print() {
        System.out.printf("%10s %20s %20s %20s %20s",
                this.bidId,
                this.bidStep,
                this.bidCurrent,
                this.buyerId,
                this.productId + "\n"
        );

    }

}