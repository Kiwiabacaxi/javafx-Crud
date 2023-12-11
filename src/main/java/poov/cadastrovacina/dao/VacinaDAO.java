package poov.cadastrovacina.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import poov.cadastrovacina.dao.core.GenericJDBCDAO;
import poov.cadastrovacina.model.Situacao;
import poov.cadastrovacina.model.Vacina;
import poov.cadastrovacina.model.filter.VacinaFilter;

public class VacinaDAO extends GenericJDBCDAO<Vacina, Long> {

    public VacinaDAO(Connection conexao) {
        super(conexao);
    }

    // Definição das queries SQL
    private static final String FIND_ALL_QUERY = "SELECT codigo, nome, descricao, situacao FROM vacina WHERE situacao = 'ATIVO' ";
    private static final String FIND_BY_KEY_QUERY = FIND_ALL_QUERY + "AND codigo=? ";
    private static final String FIND_BY_NAME_LIKE_QUERY = FIND_ALL_QUERY + "AND upper(nome) like upper(?)";
    private static final String UPDATE_QUERY = "UPDATE vacina SET nome=?, descricao=?, situacao=? WHERE codigo=?";
    private static final String CREATE_QUERY = "INSERT INTO vacina (nome, descricao, situacao) VALUES (?, ?, ?)";
    private static final String REMOVE_QUERY = "DELETE FROM vacina WHERE codigo=?";

    @Override
    protected Vacina toEntity(ResultSet resultSet) throws SQLException {
        // Converte o ResultSet em uma entidade Vacina
        Vacina vacina = new Vacina();
        vacina.setCodigo(resultSet.getLong("codigo"));
        vacina.setNome(resultSet.getString("nome"));
        vacina.setDescricao(resultSet.getString("descricao"));
        if (resultSet.getString("situacao").equals("ATIVO")) {
            vacina.setSituacao(Situacao.ATIVO);
        } else {
            vacina.setSituacao(Situacao.INATIVO);
        }
        return vacina;
    }

    @Override
    protected void addParameters(PreparedStatement resultSet, Vacina entity) throws SQLException {
        // Adiciona os parâmetros ao PreparedStatement
        resultSet.setString(1, entity.getNome());
        resultSet.setString(2, entity.getDescricao());
        resultSet.setString(3, entity.getSituacao().toString());
        if (entity.getCodigo() != null) {
            resultSet.setLong(4, entity.getCodigo());
        }
    }

    // Métodos que retornam as queries SQL
    @Override
    protected String findByKeyQuery() {
        return FIND_BY_KEY_QUERY;
    }

    @Override
    protected String findAllQuery() {
        return FIND_ALL_QUERY;
    }

    @Override
    protected String updateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected String createQuery() {
        return CREATE_QUERY;
    }

    @Override
    protected String removeQuery() {
        return REMOVE_QUERY;
    }

    public List<Vacina> findByNameLike(String nome) {
        // Busca vacinas pelo nome
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_NAME_LIKE_QUERY);
            statement.setString(1, "%" + nome + "%");
            ResultSet resultSet = statement.executeQuery();
            return toEntityList(resultSet);
        } catch (SQLException e) {
            showSQLException(e);
        }
        return new ArrayList<Vacina>();
    }

    @Override
    protected void setKeyInStatementFromEntity(PreparedStatement statement, Vacina entity) throws SQLException {
        // Define a chave no PreparedStatement a partir da entidade
        statement.setLong(1, entity.getCodigo());
    }
    
    @Override
    protected void setKeyInStatement(PreparedStatement statement, Long key) throws SQLException {
        // Define a chave no PreparedStatement
        statement.setLong(1, key);
    }

    @Override
    protected void setKeyInEntity(ResultSet rs, Vacina entity) throws SQLException {
        // Define a chave na entidade
        entity.setCodigo(rs.getLong(1));
    }

    public List<Vacina> pesquisar(VacinaFilter filtro) {
        // Pesquisa vacinas com base no filtro
        int parametro = 1;
        String query = "SELECT codigo, nome, descricao, situacao FROM vacina WHERE situacao = 'ATIVO' ";
    
        if (filtro.getCodigo() != null) {
            query += "AND codigo = ?";
        }
        if (filtro.getNome() != null) {
            query += " AND LOWER(nome) like ?";
        }
        if (filtro.getDescricao() != null) {
            query += " AND LOWER(descricao) like ?";
        }
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            if (filtro.getCodigo() != null) {
                statement.setLong(1, filtro.getCodigo());
                parametro++;
            }
            if (filtro.getNome() != null) {
                statement.setString(parametro, "%" + filtro.getNome().toLowerCase() + "%");
                parametro++;
            }
            if (filtro.getDescricao() != null) {
                statement.setString(parametro, "%" + filtro.getDescricao().toLowerCase() + "%");
            }
            // Printa a query SQL no console - PARA FINS DE DEGUG
            System.out.println(statement.toString());
            ResultSet resultSet = statement.executeQuery();
            return toEntityList(resultSet);
        } catch (SQLException e) {
            showSQLException(e);
        }
        return new ArrayList<Vacina>();
    }

}
