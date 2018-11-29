package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Emprestimo;

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
				this.conexao = (Connection) DriverManager.getConnection(url, login, senha);
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

	public Connection fechaConexaoBD() {
		this.conexao = null;
		return null;

	}
	
	public void deletaCliente(int id) {
		this.conexao = abreConexaoBD();
		String query;
		query = "DELETE FROM EMPRESTIMO WHERE ID =" + id + ";";
		System.out.println("tentando executar" + query);

		try {
			this.conexao.createStatement().executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void atualizaCliente(int id) {

	}

	public void inserirCliente() {

	}
	
	
	
	public ArrayList<Emprestimo> buscaClientes(String condicao) throws SQLException {
		ArrayList<Emprestimo> arrayList = new ArrayList<Emprestimo>();
		this.conexao = abreConexaoBD();

		String query;
		query = "SELECT * FROM EMPRESTIMO WHERE  (NOME LIKE '%" + condicao + "%' OR CPF LIKE '%" + condicao + "%')";
		System.out.println("tentando executar" + query);

		ResultSet rs = this.conexao.createStatement().executeQuery(query);

		while (rs.next()) {
			Emprestimo emprestimo = new Emprestimo();
			emprestimo.setId(rs.getInt("ID"));
			emprestimo.setNome(rs.getString("NOME"));
			emprestimo.setCpf(rs.getString("CPF"));
			emprestimo.setValor(rs.getString("SALARIO"));
			arrayList.add(emprestimo);
		}
		this.conexao = fechaConexaoBD();
		return arrayList;

	}
	
	public ArrayList<Emprestimo> buscaClientePorId(int id) throws SQLException {
		ArrayList<Emprestimo> arrayList = new ArrayList<Emprestimo>();
		this.conexao = abreConexaoBD();

		String query;
		query = "SELECT * FROM EMPRESTIMO WHERE ID =" + id + ";";
		System.out.println("tentando executar" + query);

		ResultSet rs = this.conexao.createStatement().executeQuery(query);

		while (rs.next()) {
			Emprestimo emprestimo = new Emprestimo();
			emprestimo.setId(rs.getInt("ID"));
			emprestimo.setNome(rs.getString("NOME"));
			emprestimo.setCpf(rs.getString("CPF"));
			emprestimo.setValor(rs.getString("SALARIO"));
			arrayList.add(emprestimo);
		}
		this.conexao = fechaConexaoBD();
		return arrayList;
	}

}
