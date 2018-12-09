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
			} catch (SQLException ex) {
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
		query = "DELETE FROM EMPRESTIMO WHERE ID = " + id + ";";
		System.out.println("tentando deletar " + query);

		try {
			this.conexao.createStatement().executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void atualizaCliente(Emprestimo emprestimo) {
		this.conexao = abreConexaoBD();

		String query, isString = "'", virgula = ",";
		;

		query = "UPDATE EMPRESTIMO SET NOME = " + isString + emprestimo.getNome() + isString + virgula + "EMAIL = "
				+ isString + emprestimo.getEmail() + isString + virgula + "CPF = " + isString + emprestimo.getCpf()
				+ isString + virgula + "DT_NASCIMENTO = " + isString + emprestimo.getDataNascimento() + isString
				+ virgula + "TELEFONE = " + isString + emprestimo.getTelefone() + isString + virgula + "SEXO = "
				+ isString + emprestimo.getSexo() + isString + virgula + "SALARIO = " + emprestimo.getSalario()
				+ virgula + "VALOR = " + emprestimo.getValor() + virgula + "ATRASO = " + emprestimo.getDiasAtraso()
				+  " WHERE ID = " + emprestimo.getId();

		System.out.println("tentativa de editar" + query);

		try {
			this.conexao.createStatement().executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.conexao = fechaConexaoBD();

	}

	public void inserirCliente(Emprestimo emprestimo) {
		this.conexao = abreConexaoBD();
		String query, virgula = ",", isString = "'";
		query = "INSERT INTO EMPRESTIMO (NOME, CPF, SALARIO, EMAIL, DT_NASCIMENTO, VALOR, TELEFONE, SEXO, ATRASO) VALUES("
				+ isString + emprestimo.getNome() + isString + virgula + isString + emprestimo.getCpf() + isString
				+ virgula + emprestimo.getSalario() + virgula + isString + emprestimo.getEmail() + isString + virgula
				+ isString + emprestimo.getDataNascimento() + isString + virgula + emprestimo.getValor() + virgula
				+ isString + emprestimo.getTelefone() + isString + virgula + isString + emprestimo.getSexo() + isString
				+ virgula + emprestimo.getDiasAtraso() + ");";
		System.out.println("tentativa de inclusão" + query);

		try {
			this.conexao.createStatement().executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.conexao = fechaConexaoBD();

	}

	public ArrayList<Emprestimo> buscaClientes(String condicao) throws SQLException {
		ArrayList<Emprestimo> arrayList = new ArrayList<Emprestimo>();
		this.conexao = abreConexaoBD();

		String query;
		query = "SELECT * FROM EMPRESTIMO WHERE  (NOME LIKE '%" + condicao + "%' OR CPF LIKE '%" + condicao
				+ "%') ORDER BY NOME";
		System.out.println("tentando executar" + query);

		ResultSet rs = this.conexao.createStatement().executeQuery(query);
		while (rs.next()) {
			Emprestimo emprestimo = new Emprestimo();
			emprestimo.setId(rs.getInt("ID"));
			emprestimo.setNome(rs.getString("NOME"));
			emprestimo.setCpf(rs.getString("CPF"));
			emprestimo.setValor(rs.getFloat("VALOR"));
			emprestimo.setSalario(rs.getFloat("SALARIO"));
			emprestimo.setDataNascimento(rs.getDate("DT_NASCIMENTO"));
			emprestimo.setEmail(rs.getString("EMAIL"));
			emprestimo.setTelefone(rs.getString("TELEFONE"));
			emprestimo.setSexo(rs.getString("SEXO"));
			emprestimo.setDiasAtraso(rs.getInt("ATRASO"));

			arrayList.add(emprestimo);
		}
		this.conexao = fechaConexaoBD();
		return arrayList;

	}

	public ArrayList<Emprestimo> buscaClientePorCpf(Emprestimo emprestimoN) throws SQLException {
		ArrayList<Emprestimo> arrayList = new ArrayList<Emprestimo>();
		this.conexao = abreConexaoBD();

		String query, isString = "'";

		query = "SELECT * FROM EMPRESTIMO WHERE  CPF = " + isString + emprestimoN.getCpf() + isString + " AND ID = "
				+ emprestimoN.getId() + ";";
		System.out.println("tentando executar" + query);

		ResultSet rs = this.conexao.createStatement().executeQuery(query);
		while (rs.next()) {
			Emprestimo emprestimo = new Emprestimo();
			emprestimo.setId(rs.getInt("ID"));
			emprestimo.setNome(rs.getString("NOME"));
			emprestimo.setCpf(rs.getString("CPF"));
			emprestimo.setValor(rs.getFloat("VALOR"));
			emprestimo.setSalario(rs.getFloat("SALARIO"));
			emprestimo.setDataNascimento(rs.getDate("DT_NASCIMENTO"));
			emprestimo.setEmail(rs.getString("EMAIL"));
			emprestimo.setTelefone(rs.getString("TELEFONE"));
			emprestimo.setSexo(rs.getString("SEXO"));
			emprestimo.setDiasAtraso(rs.getInt("ATRASO"));
			arrayList.add(emprestimo);
		}
		this.conexao = fechaConexaoBD();
		return arrayList;

	}

}
