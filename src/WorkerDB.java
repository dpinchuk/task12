import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkerDB {

    private PreparedStatement preparedStatement;
    private ResultSet resultQuery;
    private String string;

    public List<String> getPreparedStatementQuery(String query, int a, int b, Connection connection) throws SQLException {

        List listQuery = new ArrayList();
        //this.connection = DriverManager.getConnection(this.url + "/" + this.db, this.user, this.pass);

        this.preparedStatement = connection.prepareStatement(query);
        this.preparedStatement.setInt(1, a);
        this.preparedStatement.setInt(2, b);
        this.resultQuery = preparedStatement.executeQuery();

        int columnCount = resultQuery.getMetaData().getColumnCount();

        string = "";
        for (int i = 1; i <= columnCount; i++) {
            string += this.resultQuery.getMetaData().getColumnName(i) + "|";
        }
        listQuery.add(string);

        while (this.resultQuery.next()) {
            string = "";
            for (int i = 1; i <= columnCount; i++) {
                string += this.resultQuery.getString(i) + "|";
            }
            listQuery.add(string);
        }

        resultQuery.close();
        preparedStatement.close();
        //connection.close();

        return listQuery;
    }

}