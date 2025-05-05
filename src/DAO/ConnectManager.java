package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectManager {
    private Connection connect;
    private static String url = "jdbc:sqlserver://localhost:1433;databaseName=SieuThiMini;encrypt=true;trustServerCertificate=true";
    private static String user = "sa";            // Tài khoản SQL Server
    private static String password = "123456";    // Mật khẩu
    public Connection getConnection() {
        try {
            if(connect !=null && !connect.isClosed())
                return connect;
        } catch (SQLException e) {
                e.printStackTrace();              
        }
        return null;
    }

    public void openConnection(){
        try {
            this.connect = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            if(connect != null && !connect.isClosed())
               connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
