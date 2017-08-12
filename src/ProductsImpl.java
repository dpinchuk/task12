
public class ProductsImpl implements Methods {

    private final String productId;
    private final String productName;
    private final String productStartPrice;
    private final String productSalePrice;
    private final String idSeller;

    private final String[] listNote;

    public ProductsImpl (String string) {
        listNote = getData(string);
        this.productId = listNote[0];
        this.productName = listNote[1];
        this.productStartPrice = listNote[2];
        this.productSalePrice = listNote[3];
        this.idSeller = listNote[4];
    }

    public void print() {
        System.out.printf("%10s %20s %20s %20s %20s",
                        this.productId,
                        this.productName,
                        this.productStartPrice,
                        this.productSalePrice,
                        this.idSeller + "\n"
        );
    }

}