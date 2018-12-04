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

	public void atualizaCliente(Emprestimo emprestimo, int id) {
	}
	

	public void inserirCliente(Emprestimo emprestimo) {
		this.conexao = abreConexaoBD();
		String query, virgula = ",", isString = "'";
		query = "INSERT INTO EMPRESTIMO (NOME, CPF, SALARIO, EMAIL, DT_NASCIMENTO, VALOR, TELEFONE, SEXO) VALUES("
				+ isString + emprestimo.getNome() + isString + virgula + isString + emprestimo.getCpf() + isString
				+ virgula + emprestimo.getSalario() + virgula + isString + emprestimo.getEmail() + isString + virgula
				+ "'20180212'" + virgula + emprestimo.getValor() + virgula + isString + emprestimo.getTelefone()
				+ isString + virgula + isString + emprestimo.getSexo() + isString + ");";
		System.out.println("tentativa de inclusão" + query);

		try {
			this.conexao.createStatement().executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
			emprestimo.setValor(Float.valueOf(rs.getString("VALOR")));
			emprestimo.setSalario(Float.valueOf(rs.getString("SALARIO")));
			emprestimo.setDataNascimento(rs.getString("DT_NASCIMENTO"));
			emprestimo.setEmail(rs.getString("EMAIL"));
			emprestimo.setTelefone(rs.getString("TELEFONE"));
			emprestimo.setSexo(rs.getString("SEXO"));
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
//			emprestimo.setValor(rs.getString("SALARIO"));
			emprestimo.setDataNascimento(rs.getString("DT_NASCIMENTO"));
			emprestimo.setEmail(rs.getString("EMAIL"));
			emprestimo.setTelefone(rs.getString("TELEFONE"));
			arrayList.add(emprestimo);
		}
		this.conexao = fechaConexaoBD();
		return arrayList;
	}

}
