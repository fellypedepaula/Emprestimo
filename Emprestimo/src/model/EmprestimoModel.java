package model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class EmprestimoModel {
	  private final SimpleBooleanProperty selected;
	    private final SimpleStringProperty nome;
	    private final SimpleIntegerProperty idade;
	    private final SimpleStringProperty endereco;

	    public EmprestimoModel(String nome, Integer idade, String endereco) {
	        this.selected = new SimpleBooleanProperty(false);
	        this.nome = new SimpleStringProperty(nome);
	        this.idade = new SimpleIntegerProperty(idade);
	        this.endereco = new SimpleStringProperty(endereco);
	    }

	    public boolean isSelected() {
	        return selected.get();
	    }

	    public SimpleBooleanProperty selectedProperty() {
	        return selected;
	    }

	    public void setSelected(boolean selected) {
	        this.selected.set(selected);
	    }

	    public String getNome() {
	        return nome.get();
	    }

	    public SimpleStringProperty nomeProperty() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome.set(nome);
	    }

	    public int getIdade() {
	        return idade.get();
	    }

	    public SimpleIntegerProperty idadeProperty() {
	        return idade;
	    }

	    public void setIdade(int idade) {
	        this.idade.set(idade);
	    }

	    public String getEndereco() {
	        return endereco.get();
	    }

	    public SimpleStringProperty enderecoProperty() {
	        return endereco;
	    }

	    public void setEndereco(String endereco) {
	        this.endereco.set(endereco);
	    }
}
