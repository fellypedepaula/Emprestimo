package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import persistence.EmprestimoDAO;

public class Emprestimo {

	EmprestimoDAO emprestimoDao = new EmprestimoDAO();

	String nome, valor, email, dataNascimento, cpf, telefone, selected;

	int id, idade, diasAtraso;
	float salario;
	boolean isHomem, isMulher;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDiasAtraso() {
		return diasAtraso;
	}

	public void setDiasAtraso(int diasAtraso) {
		this.diasAtraso = diasAtraso;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public boolean isHomem() {
		return isHomem;
	}

	public void setHomem(boolean isHomem) {
		this.isHomem = isHomem;
	}

	public boolean isMulher() {
		return isMulher;
	}

	public void setMulher(boolean isMulher) {
		this.isMulher = isMulher;
	}

//	public ArrayList<Emprestimo> buscaClientes(String condicao) throws SQLException {
//
//		Connection conn;
//		ArrayList<Emprestimo> arrayList = new ArrayList<Emprestimo>();
//		conn = emprestimoDao.abreConexaoBD();
//
//		String query;
//		query = "SELECT * FROM EMPRESTIMO WHERE  (NOME LIKE '%" + condicao + "%' OR CPF LIKE '%" + condicao + "%')";
//		System.out.println("tentando executar" + query);
//
//		ResultSet rs = conn.createStatement().executeQuery(query);
//
//		while (rs.next()) {
//			Emprestimo emprestimo = new Emprestimo();
//			emprestimo.setId(rs.getInt("ID"));
//			emprestimo.setNome(rs.getString("NOME"));
//			emprestimo.setCpf(rs.getString("CPF"));
//			emprestimo.setValor(rs.getString("SALARIO"));
//			arrayList.add(emprestimo);
//		}
//		emprestimoDao.fechaConexaoBD();
//		return arrayList;
//
//	}

//	public ArrayList<Emprestimo> buscaClientePorId(int id) throws SQLException {
//		Connection conn;
//		ArrayList<Emprestimo> arrayList = new ArrayList<Emprestimo>();
//		conn = emprestimoDao.abreConexaoBD();
//
//		String query;
//		query = "SELECT * FROM EMPRESTIMO WHERE ID =" + id + ";";
//		System.out.println("tentando executar" + query);
//
//		ResultSet rs = conn.createStatement().executeQuery(query);
//
//		while (rs.next()) {
//			Emprestimo emprestimo = new Emprestimo();
//			emprestimo.setId(rs.getInt("ID"));
//			emprestimo.setNome(rs.getString("NOME"));
//			emprestimo.setCpf(rs.getString("CPF"));
//			emprestimo.setValor(rs.getString("SALARIO"));
//			arrayList.add(emprestimo);
//		}
//		emprestimoDao.fechaConexaoBD();
//		return arrayList;
//	}




}