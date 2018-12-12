package controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Emprestimo;
import model.EmprestimoNegocio;
import utilitarios.ValidaCPF;

public class EmprestimoController implements Initializable {

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtEmail;

	@FXML
	private DatePicker txtDate;

	@FXML
	private TextField txtCpf;

	@FXML
	private TextField txtSalario;

	@FXML
	private TextField txtTelefone;

	@FXML
	private TextField txtValor;

	@FXML
	private TextField txtAtraso;

	@FXML
	private CheckBox isHomem;

	@FXML
	private CheckBox isMulher;

	@FXML
	private TableView<Emprestimo> tabela;

	@FXML
	private TableColumn<Emprestimo, String> colunaNome;

	@FXML
	private TableColumn<Emprestimo, String> colunaCpf;

	@FXML
	private TableColumn<Emprestimo, String> colunaDivida;

	@FXML
	private TableColumn<Emprestimo, Boolean> colunaTelefone;

	@FXML
	private TextField pesquisa;

	@FXML
	private TextField txtIdForm;

	@FXML
	private TextField txtSexo;

	@FXML // capturando evento do mouse
	void clickItem(MouseEvent event) {

		if (event.getClickCount() == 1)// verificando quantidade de cliques do mouse sobre tableview
		{

			if (tabela.getItems().size() > 0) { // verificando se existe ao menos um registro na tableview
				Emprestimo emprestimo = new Emprestimo();
				emprestimo.setId(tabela.getSelectionModel().getSelectedItem().getId());
				emprestimo.setCpf(tabela.getSelectionModel().getSelectedItem().getCpf());
				emprestimo.setNome(tabela.getSelectionModel().getSelectedItem().getNome());
				emprestimo.setEmail(tabela.getSelectionModel().getSelectedItem().getEmail());
				emprestimo.setSalario(tabela.getSelectionModel().getSelectedItem().getSalario());
				emprestimo.setTelefone(tabela.getSelectionModel().getSelectedItem().getTelefone());
				emprestimo.setValor(tabela.getSelectionModel().getSelectedItem().getValor());
				emprestimo.setDiasAtraso(tabela.getSelectionModel().getSelectedItem().getDiasAtraso());
				emprestimo.setSexo(tabela.getSelectionModel().getSelectedItem().getSexo());
				emprestimo.setDataNascimento(tabela.getSelectionModel().getSelectedItem().getDataNascimento());
				preencherFormulario(emprestimo);
			}
		}

	}

	@FXML // capturando clique do checkbox sexo Feminino
	void onCliqueF(MouseEvent event) {
		isHomem.setSelected(false);
		isMulher.setSelected(true);
		txtSexo.setText("F");
	}

	@FXML // capturando clique do checkbox sexo masculino
	void onCliqueM(MouseEvent event) {
		isHomem.setSelected(true);
		isMulher.setSelected(false);
		txtSexo.setText("M");
	}

	@FXML // a��o de excluir um cliente selecionado
	void onExcluir(ActionEvent event) throws SQLException {
		Alert alert = new Alert(AlertType.INFORMATION);

		if (txtIdForm.getText() == null && !"".equals(txtIdForm.getText())) {
			alert.setContentText("Selecione um registro para que possa ser exclu�do!");
			alert.showAndWait();

		} else {
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Ci�ncia de opera��o");
			alert.setContentText(
					"Deseja realmente excluir a d�vida do(a) " + tabela.getSelectionModel().getSelectedItem().getNome()
							+ " portador do CPF " + tabela.getSelectionModel().getSelectedItem().getCpf() + " ?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				EmprestimoNegocio emprestimoNegocio = new EmprestimoNegocio();
				emprestimoNegocio.deletaCliente(tabela.getSelectionModel().getSelectedItem().getId());
				listarClientes();
			} else {
				alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("Opera��o cancelada com sucesso!");
				alert.showAndWait();
			}
		}
	}

	@FXML // limpar o formulario e setar foco do cursos para o campo Nome
	void onInserirNovo(ActionEvent event) {
		limparFormulario();
		txtNome.requestFocus();
	}

	// Obejto onde ficam os registros obtidos na consulta de listar e mantidos em
	// arraylist, dados da tableview
	ObservableList<Emprestimo> oblist = FXCollections.observableArrayList();

	@FXML // realizar pesquisas baseada em nome ou cpf
	void onPesquisar(KeyEvent event) {
		try {
			listarClientes();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML // setando mascara do cpf no momento em que o usu�rio est� digitando
	void onReleasedCpf(KeyEvent event) {
		if (txtCpf.getText() != null && !"".equals(txtCpf.getText())) {
			utilitarios.Util tff = new utilitarios.Util();
			tff.setMask("###-###-###.##");
			tff.setCaracteresValidos("0123456789");
			tff.setTf(txtCpf);
			tff.formatter();
		}
	}

	@FXML
	void onReleasedSalario(KeyEvent event) {
//		utilitarios.Util tff = new utilitarios.Util();
//		tff.setCaracteresValidos("0123456789");
//		tff.setMask("##,###.##");
//		tff.setTf(txtSalario);
//		tff.formatter();

	}

	@FXML // setando mascara do telefone no momento em que o usu�rio est� digitando
	void onReleasedTelefone(KeyEvent event) {
		if (txtTelefone.getText() != null && !"".equals(txtTelefone.getText())) {
			utilitarios.Util tff = new utilitarios.Util();
			tff.setMask("(##)#####-####");
			tff.setCaracteresValidos("0123456789");
			tff.setTf(txtTelefone);
			tff.formatter();
		}
	}

	@FXML
	void onReleasedValor(KeyEvent event) {
//		utilitarios.Util tff = new utilitarios.Util();
//		tff.setCaracteresValidos("0123456789");
//		tff.setMask("##,###.##");
//		tff.setTf(txtValor);
//		tff.formatter();
	}

	@FXML // acionado atrav�s do bot�o salvar na tela, ao clicar faz as devidas valida��es
			// para verificar se e pertinente inserir as informa��es, na sequ�ncia valida se
			// o cpf j� existe
	// caso sim apenas realiza altera��o no registro, caso n�o insere o novo
	// registro
	void onSalvar(ActionEvent event) throws SQLException {
		if (validaFormulario()) {
			if (isNomeValido()) {
				if (isEmailValido()) {
					if (validaCPF()) {
						if (isDiasAtrasoValido()) {
							if (isSexoValido()) {
								if (isTelefone()) {
									Alert alert;
									EmprestimoNegocio emprestimoNegocio = new EmprestimoNegocio();
									Emprestimo emprestimo = new Emprestimo();
									ArrayList<Emprestimo> arrayList = new ArrayList<Emprestimo>();
									alert = new Alert(AlertType.CONFIRMATION);
									java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(txtDate.getValue());
									emprestimo.setCpf(retiraCaracteresEspeciais(txtCpf.getText()));
									emprestimo.setNome(txtNome.getText());
									emprestimo.setEmail(txtEmail.getText());
									emprestimo.setDataNascimento(gettedDatePickerDate);
									emprestimo.setSalario(Float.valueOf(txtSalario.getText().replaceAll(",", "")));
									emprestimo.setTelefone(retiraCaracteresEspeciais(txtTelefone.getText()));
									emprestimo.setValor(Float.valueOf(txtValor.getText().replaceAll(",", "")));
									emprestimo.setSexo(txtSexo.getText());
									emprestimo.setDiasAtraso(Integer.valueOf(txtAtraso.getText()));

									arrayList = emprestimoNegocio.buscarClientePorCpf(emprestimo); // realizando
																									// consulta do cpf
																									// informado

									if (arrayList.isEmpty()) {
										emprestimoNegocio.inserirCliente(emprestimo); // registro novo, realizando
																						// inser��o

									} else {
										emprestimo.setId(Integer.parseInt(txtIdForm.getText()));

										alert.setTitle("Ci�ncia de opera��o");
										alert.setContentText(
												"O CPF informado j� existe em nossa base de dados, deseja atualizar o cadastro?");

										Optional<ButtonType> result = alert.showAndWait();
										if (result.get() == ButtonType.OK) {
											emprestimoNegocio.editarCliente(emprestimo); // registro j� existente,
																							// atualizando os dados
																							// atrav�s das novas
																							// informa��es
											alert = new Alert(AlertType.INFORMATION);
											alert.setContentText("Cadastro atualizado com sucesso!");
											alert.showAndWait();
										} else {
											alert = new Alert(AlertType.INFORMATION);
											alert.setContentText("Opera��o cancelada com sucesso!");
											alert.showAndWait();
										}
									}

									listarClientes();
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) { // metodo necess�rio para que seja poss�vel iniciar
																		// a aplica��o com a tableview j� carregada
		txtDate.setEditable(false); // desabilitado para for�ar o usu�rio a utilizar o calend�rio, que por sua vez
									// valida a data

		try {
			listarClientes();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// respons�vel por listar as informa��es do banco de dados
	public void listarClientes() throws SQLException {
		tabela.getItems().clear();
		limparFormulario();

		String condicao = pesquisa.getText().toString();
		ArrayList<Emprestimo> arrayList = new ArrayList<Emprestimo>();
		EmprestimoNegocio emprestimoNegocio = new EmprestimoNegocio();

		arrayList = emprestimoNegocio.buscarClientes(condicao);

		for (int i = 0; i < arrayList.size(); i++) {
			Emprestimo emprestimo = new Emprestimo();
			emprestimo.setId(arrayList.get(i).getId());
			emprestimo.setNome(arrayList.get(i).getNome());
			emprestimo.setCpf(arrayList.get(i).getCpf());
			emprestimo.setValor(arrayList.get(i).getValor());
			emprestimo.setEmail(arrayList.get(i).getEmail());
			emprestimo.setTelefone(arrayList.get(i).getTelefone());
			emprestimo.setSalario(arrayList.get(i).getSalario());
			emprestimo.setSexo(arrayList.get(i).getSexo());
			emprestimo.setDiasAtraso(arrayList.get(i).getDiasAtraso());
			emprestimo.setDataNascimento(arrayList.get(i).getDataNascimento());
			oblist.add(emprestimo);
			tabela.setItems(oblist);
		}

		tabela.getSelectionModel().selectFirst();
		colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		colunaDivida.setCellValueFactory(new PropertyValueFactory<>("valor"));

	}

	// valida��o se todos os campos do formul�rio est�o preenchidos
	public boolean validaFormulario() {
		if (

		(txtNome.getText() == null && "".equals(txtNome.getText()))
				|| (txtEmail.getText() == null && !"".equals(txtNome.getText()))
				|| (txtCpf.getText() == null && !"".equals(txtNome.getText()))
				|| (txtSalario.getText() == null && !"".equals(txtNome.getText()))
				|| (txtSalario.getText() == null && !"".equals(txtNome.getText()))
				|| (txtTelefone.getText() == null && !"".equals(txtNome.getText()))
				|| (txtValor.getText() == null && !"".equals(txtNome.getText()))
				|| (txtAtraso.getText() == null && !"".equals(txtNome.getText()))
				|| (txtDate.getValue() == null && !"".equals(txtNome.getText()))
				|| (!isHomem.isSelected() && (!isMulher.isSelected()))

		) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Todos os Campos devem estar devidamente preenchidos!");
			alert.showAndWait();
			return false;
		} else {
			return true;
		}

	}

	// m�todo de iinserir dados no formulario
	public void preencherFormulario(Emprestimo emprestimo) {
		utilitarios.Util tff = new utilitarios.Util();
		tff.setCaracteresValidos("0123456789");

		String data = emprestimo.getDataNascimento().toString();
		int ano, mes, dia;
		ano = Integer.parseInt(data.substring(0, 4));
		mes = Integer.parseInt(data.substring(5, 7));
		dia = Integer.parseInt(data.substring(8, 10));

		txtIdForm.setText(String.valueOf(emprestimo.getId()));
		txtNome.setText(emprestimo.getNome());
		txtEmail.setText(emprestimo.getEmail());
		txtCpf.setText(emprestimo.getCpf());
		txtDate.setValue(LocalDate.of(ano, mes, dia));
		txtSalario.setText(String.valueOf(emprestimo.getSalario()));
		txtTelefone.setText(emprestimo.getTelefone());
		txtValor.setText(String.valueOf(emprestimo.getValor()));
		txtAtraso.setText(String.valueOf(emprestimo.getDiasAtraso()));
		verificaSexo(emprestimo.getSexo().toString());

		tff.setMask("###-###-###.##");
		tff.setTf(txtCpf);
		tff.formatter();

		tff.setMask("(##)#####-#####");
		tff.setTf(txtTelefone);
		tff.formatter();
	}

	// limpando o formulario
	public void limparFormulario() {
		txtNome.setText(null);
		txtEmail.setText(null);
		txtCpf.setText(null);
		txtDate.setValue(null);
		txtSalario.setText(null);
		txtTelefone.setText(null);
		txtValor.setText(null);
		txtAtraso.setText(null);
		txtIdForm.setText(null);
		txtSexo.setText(null);
		isHomem.setSelected(false);
		isMulher.setSelected(false);
	}

	// verifica��o e preenchimento do checkbox adequado
	public void verificaSexo(String sexo) {

		if (sexo.equals("M")) {
			isHomem.setSelected(true);
			isMulher.setSelected(false);
			txtSexo.setText("M");

		} else {
			isMulher.setSelected(true);
			isHomem.setSelected(false);
			txtSexo.setText("F");
		}
	}

	// remover caracteres especiais
	public String retiraCaracteresEspeciais(String text) {
		text = text.replaceAll("([^a-zZ-Z0-9 ])", "");
		text = text.replaceAll(" ", "");
		return text;
	}

	// validando cpf
	public boolean validaCPF() {
		if (ValidaCPF.isCPF(retiraCaracteresEspeciais(txtCpf.getText()))) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Cpf Invalido!");
			alert.showAndWait();
			return false;
		}
	}

	// validando telefone
	public boolean isTelefone() {
		String numeroTelefone = txtTelefone.getText().toString();
		numeroTelefone = numeroTelefone.replaceAll(" ", "");
		if (numeroTelefone.matches("\\(\\d{2}\\)\\d{4,5}-\\d{4}")) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Telefone Invalido!");
			alert.showAndWait();
			return false;
		}
	}

//	public boolean isValorDecimal() {
//		String renda = txtSalario.getText().toString();
//		String valor = txtValor.getText().toString();
//		renda = renda.replaceAll(",", "");
//		valor = valor.replaceAll(",", "");
//		if (renda.length() == 8 && valor.length() == 8) {
//			return true;
//		} else {
//			Alert alert = new Alert(AlertType.ERROR);
//			alert.setContentText("Valor da renda ou d�vida fora do padr�o, por gentileza adeque #####.## !");
//			alert.showAndWait();
//			return false;
//		}
//	}

	// verificando se s� existem letras e espa�os no campo nome do formul�rio
	boolean isNomeValido() {
		if (txtNome.getText().matches("[a-zA-Z\\s]+")) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("O Nome s� pode conter letras  !");
			alert.showAndWait();
			return false;
		}
	}

	// validando se o campo atraso do formu�rio foi informado corretamente como
	// inteiro
	boolean isDiasAtrasoValido() {
		try {
			Integer.parseInt((txtAtraso.getText()));
			return true;
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Quantidade de dias de atraso e um numero n�o decimal!");
			alert.showAndWait();
			return false;
		}
	}

	// verificando se um dos checkbox - homem / mulher est�o selecionados
	boolean isSexoValido() {
		if (!isHomem.isSelected() && (!isMulher.isSelected())) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Quantidade de dias de atraso e um numero n�o decimal!");
			alert.showAndWait();
			return false;
		} else {
			return true;
		}
	}

	// 1- N�o possuir espa�os.
	// 2- Possuir o @.
	// 3- Possuir algum caracter ap�s o @.
	// 4- Possuir pelo menos um ponto ap�s o @.
	boolean isEmailValido() {
		Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,3}$");
		Matcher m = p.matcher(txtEmail.getText().trim());
		if (!m.find()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Email inv�lido!");
			alert.showAndWait();
			return false;
		} else {
			return true;
		}

	}

}
