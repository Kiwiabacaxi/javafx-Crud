package poov.cadastrovacina.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import poov.cadastrovacina.App;
import poov.cadastrovacina.dao.ConexaoFactoryPostgreSQL;
import poov.cadastrovacina.dao.PessoaDAO;
import poov.cadastrovacina.dao.VacinaDAO;
import poov.cadastrovacina.dao.core.ConnectionFactory;
import poov.cadastrovacina.dao.core.DAOFactory;
import poov.cadastrovacina.model.Pessoa;
import poov.cadastrovacina.model.Situacao;
import poov.cadastrovacina.model.Vacina;
import poov.cadastrovacina.model.filter.PessoaFilter;
import poov.cadastrovacina.model.filter.VacinaFilter;

public class CrudVacinaController implements Initializable {

    @FXML
    private TableView<Vacina> vacinaTableView;

    @FXML
    private TableColumn<Vacina, Long> codigoTableColumn;

    @FXML
    private TableColumn<Vacina, String> nomeTableColumn;

    @FXML
    private TableColumn<Vacina, String> descricaoTableColumn;

    @FXML
    private TextField codigoTextField;

    @FXML
    private TextField descricaoTextField;

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField codigoTextFieldPessoa;

    @FXML
    private TextField nomeTextFieldPessoa;

    @FXML
    private TextField cpfTextFieldPessoa;

    @FXML
    private Button editarButton;

    @FXML
    private Button fecharButton;

    @FXML
    private Button novaButton;

    @FXML
    private Button pesquisarButton;

    @FXML
    private Button pesquisarPessoaButton;

    @FXML
    private Button limparButton;

    @FXML
    private Button removerButton;

    @FXML
    private DatePicker outputDatePicker1;

    @FXML
    private DatePicker inputDatePicker1;

    // buttons de pessoa

    @FXML
    private TableView<Pessoa> pessoaTableView;

    @FXML
    private TableColumn<Pessoa, Long> codigoTableColumnPessoa;

    @FXML
    private TableColumn<Pessoa, String> nomeTableColumnPessoa;

    @FXML
    private TableColumn<Pessoa, String> cpfTableColumnPessoa;

    @FXML
    private TableColumn<Pessoa, String> dataNascimentoTableColumnPessoa;

    private Stage stageCadastro;
    private Stage stageAlterar;
    private TelaAlterarVacinaController telaAlterarVacinaController;

    private DAOFactory factory;

    public CrudVacinaController() {
        ConnectionFactory conexaoFactory = new ConexaoFactoryPostgreSQL("localhost:5432/crud", "kiwi", "admin");
        factory = new DAOFactory(conexaoFactory);
    }

    @FXML
    void editarButtonClicado(ActionEvent event) throws SQLException {

        // Verifica se tem algum item selecionado
        if (vacinaTableView.getSelectionModel().getSelectedIndex() != -1) { // -1 significa que não tem nada selecionado

            // Abre a tela de alteração
            Vacina vacina = vacinaTableView.getSelectionModel().getSelectedItem();
            telaAlterarVacinaController.setVacina(vacina);

            // Abre a tela de alteração
            if (stageAlterar.getOwner() == null) {
                stageAlterar.initOwner(((Button) event.getSource()).getScene().getWindow());
            }

            // Atualizar a tela para povoar
            stageAlterar.showAndWait();
            pesquisarButtonClicado(event);

        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Alteração");
            alert.setHeaderText("Alteração de Vacina");
            alert.setContentText("Selecione uma vacina na tabela para alterar!");
            alert.showAndWait();
        }
    }

    @FXML
    void limparButtonClicado(ActionEvent event) {
        codigoTextField.setText("");
        nomeTextField.setText("");
        descricaoTextField.setText("");

        // Chamar a função de busca de vacinas para repovoar a lista
        try {
            pesquisarButtonClicado(null);
        } catch (SQLException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("Erro");
            alert.setContentText("Erro ao limpar a tabela!");
            alert.showAndWait();
        }
    }

    @FXML
    void fecharButtonClicado(ActionEvent event) {
        // Kill the application
        Platform.exit();
    }

    @FXML
    void novaButtonClicado(ActionEvent event) {
        if (stageCadastro.getOwner() == null) {
            stageCadastro.initOwner(((Button) event.getSource()).getScene().getWindow());
        }
        stageCadastro.showAndWait();
    }

    @FXML
    void pesquisarButtonClicado(ActionEvent event) throws SQLException {

        // Filtro
        VacinaFilter filtro = new VacinaFilter();

        // Se o campo de texto do código não estiver vazio
        if (!codigoTextField.getText().isBlank()) {
            // Converte o texto do campo de código para Long e define o código do filtro
            filtro.setCodigo(Long.parseLong(codigoTextField.getText()));
        }

        // Se o campo de texto do nome não estiver vazio
        if (!nomeTextField.getText().isBlank()) {
            // Define o nome do filtro como o texto do campo de nome
            filtro.setNome(nomeTextField.getText());
        }

        // Se o campo de texto da descrição não estiver vazio
        if (!descricaoTextField.getText().isBlank()) {
            // Define a descrição do filtro como o texto do campo de descrição
            filtro.setDescricao(descricaoTextField.getText());
        }

        try {
            // Abre a conexão
            factory.abrirConexao();

            // Pesquisa
            VacinaDAO dao = factory.getDAO(VacinaDAO.class);
            List<Vacina> vacinas = dao.pesquisar(filtro);

            // Preenche a tabela
            vacinaTableView.getItems().clear();
            vacinaTableView.getItems().addAll(vacinas);
        } finally {
            factory.fecharConexao();
        }

    }

    @FXML
    void pesquisarPessoaButtonClicado(ActionEvent event) throws SQLException {
        // Filtro
        PessoaFilter filtro = new PessoaFilter();

        // Se o campo de texto do código não estiver vazio
        if (!codigoTextFieldPessoa.getText().isBlank()) {
            // Converte o texto do campo de código para Long e define o código do filtro
            filtro.setCodigo(Long.parseLong(codigoTextFieldPessoa.getText()));
        }

        // Se o campo de texto do nome não estiver vazio
        if (!nomeTextFieldPessoa.getText().isBlank()) {
            // Define o nome do filtro como o texto do campo de nome
            filtro.setNome(nomeTextFieldPessoa.getText());
        }

        // Se o campo de texto da descrição não estiver vazio
        if (!cpfTextFieldPessoa.getText().isBlank()) {
            // Define a descrição do filtro como o texto do campo de descrição
            filtro.setCpf(cpfTextFieldPessoa.getText());
        }

        try {
            // Abre a conexão
            factory.abrirConexao();

            // Pesquisa
            PessoaDAO dao = factory.getDAO(PessoaDAO.class);
            List<Pessoa> pessoas = dao.pesquisar(filtro);

            // Preenche a tabela
            pessoaTableView.getItems().clear();
            pessoaTableView.getItems().addAll(pessoas);
        } finally {
            factory.fecharConexao();
        }
    }

    @FXML
    void removerButtonClicado(ActionEvent event) throws SQLException {
        if (vacinaTableView.getSelectionModel().getSelectedIndex() != -1) {
            Vacina vacina = vacinaTableView.getSelectionModel().getSelectedItem();
            ButtonType sim = new ButtonType("Sim", ButtonBar.ButtonData.OK_DONE);
            ButtonType nao = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(AlertType.CONFIRMATION,
                    "Você tem certeza que quer remover a vacina " + vacina.getNome() + "?",
                    sim, nao);
            alert.setTitle("Remoção");
            alert.setHeaderText("Remoção de Vacina");

            Optional<ButtonType> option = alert.showAndWait();
            System.out.println(option);
            if (option.get().equals(sim)) {
                try {
                    factory.abrirConexao();
                    VacinaDAO dao = factory.getDAO(VacinaDAO.class);
                    vacina.setSituacao(Situacao.INATIVO);
                    dao.update(vacina);

                } finally {
                    factory.fecharConexao();
                }
            }
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Remoção");
            alert.setHeaderText("Remoção de Vacina");
            alert.setContentText("Selecione uma vacina na tabela para remover!");
            alert.showAndWait();
        }
        pesquisarButtonClicado(event);
    }

    // Filtra o texto do campo de código para permitir apenas números
    private TextFormatter<String> createNumberOnlyTextFormatter() {
        return new TextFormatter<>(change -> {
            if (!change.isContentChange()) {
                return change;
            }
            String text = change.getControlNewText();
            if (text.length() == 0) {
                // permite campo vazio
                return change;
            } else {
                // verifica se o texto, com a mudança, é um long válido
                try {
                    Long.parseLong(text);
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            return change;
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Inicializa a tabela de vacina
        codigoTableColumn.setCellValueFactory(new PropertyValueFactory<Vacina, Long>("codigo"));
        nomeTableColumn.setCellValueFactory(new PropertyValueFactory<Vacina, String>("nome"));
        descricaoTableColumn.setCellValueFactory(new PropertyValueFactory<Vacina, String>("descricao"));
        vacinaTableView.setPlaceholder(new Label("Não existem Vacinas para serem exibidas."));

        // Inicializa a tabela de pessoa
        codigoTableColumnPessoa.setCellValueFactory(new PropertyValueFactory<Pessoa, Long>("codigo"));
        nomeTableColumnPessoa.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("nome"));
        cpfTableColumnPessoa.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("cpf"));
        dataNascimentoTableColumnPessoa.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("dataNascimento"));
        pessoaTableView.setPlaceholder(new Label("Não existem Pessoas para serem exibidas."));

        // codigoTextField.setTextFormatter(formatterOnlyNumbers);
        codigoTextField.setTextFormatter(createNumberOnlyTextFormatter());
        codigoTextFieldPessoa.setTextFormatter(createNumberOnlyTextFormatter());

        // trocar o formato das datas para dd/MM/yyyy
        inputDatePicker1.setConverter(App.datePickerFormatter);

        Parent parent;
        FXMLLoader fxmlLoader;
        try {

            // Atualizar a tela para povoar a tabela de vacinas
            pesquisarButtonClicado(null);

            // Atualizar a tela para povoar a tabela de pessoas
            pesquisarPessoaButtonClicado(null);

            stageCadastro = new Stage();
            fxmlLoader = new FXMLLoader(App.class.getResource("/poov/cadastrovacina/TelaCadastroVacina.fxml"));
            parent = fxmlLoader.load();
            Scene sceneCadastro = new Scene(parent);
            stageCadastro.setScene(sceneCadastro);
            stageCadastro.setTitle("Cadastro de Vacina");
            stageCadastro.setResizable(false);
            stageCadastro.initModality(Modality.WINDOW_MODAL);

            stageAlterar = new Stage();
            fxmlLoader = new FXMLLoader(App.class.getResource("/poov/cadastrovacina/TelaAlterarVacina.fxml"));
            parent = fxmlLoader.load();
            telaAlterarVacinaController = fxmlLoader.getController();
            telaAlterarVacinaController.setDAOFactory(factory);
            Scene sceneAlterar = new Scene(parent);
            stageAlterar.setScene(sceneAlterar);
            stageAlterar.setTitle("Alteração de Vacina");
            stageAlterar.setResizable(false);
            stageAlterar.initModality(Modality.WINDOW_MODAL);

        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initModality(Modality.WINDOW_MODAL);
            alert.setTitle("ERRO");
            alert.setHeaderText("Erro");
            alert.setContentText("Erro carregando a aplicação!");
            alert.showAndWait();
            // closes the javafx application
            Platform.exit();
        }

    }

}
