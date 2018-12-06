package model;

import java.sql.SQLException;
import java.util.ArrayList;

import persistence.EmprestimoDAO;

public class EmprestimoNegocio {

	public void deletaCliente(int id) {
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
		emprestimoDAO.deletaCliente(id);
	}

	public ArrayList<Emprestimo> buscarClientes(String condicao) throws SQLException {

		ArrayList<Emprestimo> arrayList = new ArrayList<>();
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
		arrayList = emprestimoDAO.buscaClientes(condicao);
		return arrayList;
	}

	public void inserirCliente(Emprestimo emprestimo) {
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
		emprestimoDAO.inserirCliente(emprestimo);

	}

	public void editarCliente(Emprestimo emprestimo) {
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
		emprestimoDAO.atualizaCliente(emprestimo);
	}

	public ArrayList<Emprestimo> buscarClientePorCpf(Emprestimo emprestimo) throws SQLException {
		ArrayList<Emprestimo> arrayList = new ArrayList<>();
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
		arrayList = emprestimoDAO.buscaClientePorCpf(emprestimo);
		return arrayList;
	}

}
