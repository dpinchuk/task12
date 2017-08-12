import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.*;

public class WorkerDBTest {

    private String url = "jdbc:mysql://127.0.0.1:3306";
    private String db = "auction_db";
    private String user = "dpinchuk";
    private String pass = "dmss111278";

    @Test
    public void testIsValid() throws SQLException {
        Connection connection = DriverManager.getConnection(url + "/" + db, user, pass);
        assertTrue(connection.isValid(1000));
        connection.close();
    }

    @Test
    public void testIsClosedPositive() throws SQLException {
        Connection connection = DriverManager.getConnection(url + "/" + db, user, pass);
        connection.close();
        assertTrue(connection.isClosed());
    }

    @Test
    public void testIsClosedNegative() throws SQLException {
        Connection connection = DriverManager.getConnection(url + "/" + db, user, pass);
        assertFalse(connection.isClosed());
        connection.close();
    }

    @Test
    public void testConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url + "/" + db, user, pass);
        connection.close();
        assertFalse(connection.isValid(100));
    }

    @Test(expected = SQLException.class)
    public void testFakeDB() throws SQLException {
        Connection connection = DriverManager.getConnection(url + "/" + "fake_db", user, pass);
    }

    @Test(expected = SQLException.class)
    public void testFakeUser() throws SQLException {
        Connection connection = DriverManager.getConnection(url + "/" + db, "fakeUser", pass);
    }

    @Test(expected = SQLException.class)
    public void testFakePass() throws SQLException {
        Connection connection = DriverManager.getConnection(url + "/" + db, user, "fakePassword");
    }

    @Test(expected = SQLException.class)
    public void testFake() throws SQLException {
        Connection connection = DriverManager.getConnection(null, null, null);
    }

    @Test
    public void testTableProduct() throws SQLException {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultQuery;
        List listQuery = new ArrayList();
        String string;

        connection = DriverManager.getConnection(url + "/" + db, user, pass);
        String query = "SELECT * FROM products WHERE product_id > ? AND product_id < ?";
        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, 0);
        preparedStatement.setInt(2, 2);

        resultQuery = preparedStatement.executeQuery();
        int columnCount = resultQuery.getMetaData().getColumnCount();

        string = "";
        for (int i = 1; i <= columnCount; i++) {
            string += resultQuery.getMetaData().getColumnName(i) + "|";
        }
        assertEquals(5, columnCount);
        assertEquals("product_id|product_name|product_start_price|product_sale_price|seller_id|", string);

        while (resultQuery.next()) {
            string = "";
            for (int i = 1; i <= columnCount; i++) {
                string += resultQuery.getString(i) + "|";
            }
            listQuery.add(string);
        }
        assertEquals("1|Table-1890|2000|null|1|", listQuery.get(0));

        resultQuery.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testTableSelers() throws SQLException {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultQuery;
        List listQuery = new ArrayList();
        List testListQuery = Arrays.asList("1|Dima|Pinchuk|","2|Evgeniy|Tyapunoff|");
        String string;

        connection = DriverManager.getConnection(url + "/" + db, user, pass);
        String query = "SELECT * FROM sellers";
        preparedStatement = connection.prepareStatement(query);

        resultQuery = preparedStatement.executeQuery();
        int columnCount = resultQuery.getMetaData().getColumnCount();

        string = "";
        for (int i = 1; i <= columnCount; i++) {
            string += resultQuery.getMetaData().getColumnName(i) + "|";
        }
        assertEquals(3, columnCount);
        assertEquals("seller_id|seller_name|seller_lastname|", string);

        while (resultQuery.next()) {
            string = "";
            for (int i = 1; i <= columnCount; i++) {
                string += resultQuery.getString(i) + "|";
            }
            listQuery.add(string);
        }
        assertEquals(testListQuery, listQuery);

        resultQuery.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testTableBuyers() throws SQLException {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultQuery;
        List listQuery = new ArrayList();
        List testListQuery = Arrays.asList("Iosiph|", "Vladimir|", "Lev|", "Adolf|");
        String string;

        connection = DriverManager.getConnection(url + "/" + db, user, pass);
        String query = "SELECT buyer_name FROM buyers";
        preparedStatement = connection.prepareStatement(query);

        resultQuery = preparedStatement.executeQuery();
        int columnCount = resultQuery.getMetaData().getColumnCount();

        string = "";
        for (int i = 1; i <= columnCount; i++) {
            string += resultQuery.getMetaData().getColumnName(i) + "|";
        }
        assertEquals(1, columnCount);
        assertEquals("buyer_name|", string);

        while (resultQuery.next()) {
            string = "";
            for (int i = 1; i <= columnCount; i++) {
                string += resultQuery.getString(i) + "|";
            }
            listQuery.add(string);
        }
        assertEquals(testListQuery, listQuery);

        resultQuery.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testTableBids() throws SQLException {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultQuery;
        List listQuery = new ArrayList();
        List testListQuery = Arrays.asList("1|100|2200|4|1|", "2|50|1250|1|2|",
                "3|1000|12000|3|7|", "4|100|2200|4|4|", "5|100|4000|2|3|");
        String string;

        connection = DriverManager.getConnection(url + "/" + db, user, pass);
        String query = "SELECT * FROM bids";
        preparedStatement = connection.prepareStatement(query);

        resultQuery = preparedStatement.executeQuery();
        int columnCount = resultQuery.getMetaData().getColumnCount();

        string = "";
        for (int i = 1; i <= columnCount; i++) {
            string += resultQuery.getMetaData().getColumnName(i) + "|";
        }
        assertEquals(5, columnCount);
        assertEquals("bid_id|bid_step|bid_current|buyer_id|product_id|", string);

        while (resultQuery.next()) {
            string = "";
            for (int i = 1; i <= columnCount; i++) {
                string += resultQuery.getString(i) + "|";
            }
            listQuery.add(string);
        }
        assertEquals(testListQuery, listQuery);

        resultQuery.close();
        preparedStatement.close();
        connection.close();
    }

}