import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://127.0.0.1:3306";
        String db = "auction_db";
        String user = "dpinchuk";
        String pass = "dmss111278";

        List<ProductsImpl> products = new ArrayList<>();
        List<SellersImpl> sellers = new ArrayList<>();
        List<BuyersImpl> buyers = new ArrayList<>();
        List<BidsImpl> bids = new ArrayList<>();

        WorkerDB workerDB = new WorkerDB();

        Connection connection = DriverManager.getConnection(url + "/" + db, user, pass);
        System.out.println(connection.getWarnings());

        System.out.println("Table [Products] ----------------------------------------------------------------------------");
        workerDB.getPreparedStatementQuery("SELECT * FROM products WHERE product_id > ? AND product_id < ?",
                0, 2, connection).
                forEach(str -> products.add(new ProductsImpl(str)));
        products.forEach(item -> item.print());
        System.out.println();

        System.out.println("Table [Sellers] -----------------------------------------------------------------------------");
        workerDB.getPreparedStatementQuery("SELECT * FROM sellers WHERE seller_id > ? AND seller_id < ?",
                0, 4, connection).
                forEach(str -> sellers.add(new SellersImpl(str)));
        sellers.forEach(item -> item.print());
        System.out.println();

        System.out.println("Table [Buyers] ------------------------------------------------------------------------------");
        workerDB.getPreparedStatementQuery("SELECT * FROM buyers WHERE buyer_id > ? AND buyer_id < ?",
                0, 5, connection).
                forEach(str -> buyers.add(new BuyersImpl(str)));
        buyers.forEach(item -> item.print());
        System.out.println();

        System.out.println("Table [Bids] --------------------------------------------------------------------------------");
        workerDB.getPreparedStatementQuery("SELECT * FROM bids WHERE bid_id > ? AND bid_id < ?",
                0, 5, connection).
                forEach(str -> bids.add(new BidsImpl(str)));
        bids.forEach(item -> item.print());

        connection.close();
    }

}