package model;

import java.sql.SQLException;
import java.util.ArrayList;

import persistence.EmprestimoDAO;

public class EmprestimoNegocio {

	//A partir de um id vindo  da camada de controle, aciona o a classe EmprestimoDAO e faz a exclus�o de um cliente
	public void deletaCliente(int id) {
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
		emprestimoDAO.deletaCliente(id);
	}

	//A partir de uma condi��o (nome ou cpf) vindo da camada de controle, aciona o a classe EmprestimoDAO e faz a busca 
	public ArrayList<Emprestimo> buscarClientes(String condicao) throws SQLException {
		ArrayList<Emprestimo> arrayList = new ArrayList<>();
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
		arrayList = emprestimoDAO.buscaClientes(condicao);
		return arrayList;
	}

	//A partir de um obejeto Emprestimo vindo  da camada de controle, aciona o a classe EmprestimoDAO e faz a inser��o de um cliente
	public void inserirCliente(Emprestimo emprestimo) {
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
		emprestimoDAO.inserirCliente(emprestimo);

	}

	//A partir de um obejeto Emprestimo vindo  da camada de controle, aciona o a classe EmprestimoDAO e faz a edi��o de um cliente
	public void editarCliente(Emprestimo emprestimo) {
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
		emprestimoDAO.atualizaCliente(emprestimo);
	}

	//Atrav�s de um CPF  vindo  da camada de controle, aciona o a classe EmprestimoDAO e faz a busca de um cliente
	public ArrayList<Emprestimo> buscarClientePorCpf(Emprestimo emprestimo) throws SQLException {
		ArrayList<Emprestimo> arrayList = new ArrayList<>();
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
		arrayList = emprestimoDAO.buscaClientePorCpf(emprestimo);
		return arrayList;
	}

}
