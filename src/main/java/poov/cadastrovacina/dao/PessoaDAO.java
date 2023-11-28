package poov.cadastrovacina.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import poov.cadastrovacina.dao.core.GenericJDBCDAO;
import poov.cadastrovacina.model.Pessoa;
import poov.cadastrovacina.model.Situacao;
import poov.cadastrovacina.model.filter.PessoaFilter;

public class PessoaDAO extends GenericJDBCDAO<Pessoa, Long> {

    public PessoaDAO(Connection conexao) {
        super(conexao);
    }

    private static final String FIND_ALL_QUERY = "SELECT codigo, nome, cpf, dataNascimento, situacao FROM pessoa WHERE situacao = 'ATIVO' ";
    private static final String FIND_BY_KEY_QUERY = FIND_ALL_QUERY + "AND codigo=? ";
    private static final String UPDATE_QUERY = "UPDATE pessoa SET nome=?, cpf=?, dataNascimento=?, situacao=? WHERE codigo=?";
    private static final String CREATE_QUERY = "INSERT INTO pessoa (nome, cpf, dataNascimento, situacao) VALUES (?, ?, ?, ?)";
    private static final String REMOVE_QUERY = "DELETE FROM pessoa WHERE codigo=?";

    @Override
    protected Pessoa toEntity(ResultSet resultSet) throws SQLException {
        Pessoa pessoa = new Pessoa();
        pessoa.setCodigo(resultSet.getLong("codigo"));
        pessoa.setNome(resultSet.getString("nome"));
        pessoa.setCpf(resultSet.getString("cpf"));
        pessoa.setDataNascimento(resultSet.getDate("dataNascimento").toLocalDate());
        pessoa.setSituacao(Situacao.valueOf(resultSet.getString("situacao")));
        return pessoa;
    }

    @Override
    protected void addParameters(PreparedStatement statement, Pessoa entity) throws SQLException {
        statement.setString(1, entity.getNome());
        statement.setString(2, entity.getCpf());
        statement.setDate(3, java.sql.Date.valueOf(entity.getDataNascimento()));
        statement.setString(4, entity.getSituacao().toString());
        if (entity.getCodigo() != null) {
            statement.setLong(5, entity.getCodigo());
        }
    }

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

    @Override
    protected void setKeyInStatementFromEntity(PreparedStatement statement, Pessoa entity) throws SQLException {
        statement.setLong(1, entity.getCodigo());
    }

    @Override
    protected void setKeyInStatement(PreparedStatement statement, Long key) throws SQLException {
        statement.setLong(1, key);
    }

    @Override
    protected void setKeyInEntity(ResultSet rs, Pessoa entity) throws SQLException {
        entity.setCodigo(rs.getLong(1));
    }

    public List<Pessoa> pesquisar(PessoaFilter filtro) {
        int parametro = 1;
        String query = "SELECT codigo, nome, cpf, dataNascimento, situacao FROM pessoa WHERE situacao = 'ATIVO' ";

        if (filtro.getCodigo() != null) {
            query += "AND codigo = ?";
        }
        if (filtro.getNome() != null) {
            query += " AND LOWER(nome) like ?";
        }
        if (filtro.getCpf() != null) {
            query += " AND LOWER(cpf) like ?";
        }
        if (filtro.getDataNascimento() != null) {
            query += " AND dataNascimento = ?";
        }
        if (filtro.getSituacao() != null) {
            query += " AND situacao = ?";
        }

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            if (filtro.getCodigo() != null) {
                statement.setLong(parametro++, filtro.getCodigo());
            }
            if (filtro.getNome() != null) {
                statement.setString(parametro++, "%" + filtro.getNome().toLowerCase() + "%");
            }
            if (filtro.getCpf() != null) {
                statement.setString(parametro++, "%" + filtro.getCpf().toLowerCase() + "%");
            }
            if (filtro.getDataNascimento() != null) {
                statement.setObject(parametro++, filtro.getDataNascimento());
            }
            if (filtro.getSituacao() != null) {
                statement.setString(parametro++, filtro.getSituacao().toString());
            }

            ResultSet resultSet = statement.executeQuery();
            List<Pessoa> pessoas = new ArrayList<>();
            while (resultSet.next()) {
                pessoas.add(this.toEntity(resultSet));
            }
            return pessoas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}