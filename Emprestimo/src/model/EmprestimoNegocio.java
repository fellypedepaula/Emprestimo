package model;


import persistence.EmprestimoDAO;

public class EmprestimoNegocio {
	
	public void deletaCliente(int id) {
		EmprestimoDAO emprestimoDAO =  new EmprestimoDAO();
		emprestimoDAO.deletaCliente(id);
	}

}
