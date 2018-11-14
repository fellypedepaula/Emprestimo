package persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EmprestimoDAO {

    private String login = "root";
    private String senha = "";
    private String host = "localhost:3306";
    private String dbName = "emprestimo";
    private String url = "jdbc:mysql://" + host + "/" + dbName;

    public Connection conexao = null;

    public EmprestimoDAO() {
    }

    public Connection abreConexaoBD() {
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                return null;
            }
            try {
                this.conexao = (Connection) DriverManager.getConnection(url, login, senha);
                System.out.println("funfou");
            } catch (SQLException ex) {
                System.out.println("n�o funfou" + ex);
                return null;
            }
            return this.conexao;
        } catch (Exception e) {
            return null;
        }
    }

}
