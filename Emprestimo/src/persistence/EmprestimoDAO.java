package persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EmprestimoDAO {

    private String login = "fellype";
    private String senha = "fellype";
    private String host = "localhost:3306";
    private String dbName = "emprestimo";
    private String url = "jdbc:mysql://" + host + "/" + dbName + "?useTimezone=true&serverTimezone=UTC";

    public Connection conexao = null;

    public EmprestimoDAO() {
    }

    public Connection abreConexaoBD() {
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                return null;
            }
            try {
            	this.conexao = (Connection)DriverManager.getConnection(url, login, senha );
                System.out.println("funfou");
            } catch (SQLException ex) {
                System.out.println("não funfou " + ex);
                return null;
            }
            return this.conexao;
        } catch (Exception e) {
            return null;
        }
    }

}
