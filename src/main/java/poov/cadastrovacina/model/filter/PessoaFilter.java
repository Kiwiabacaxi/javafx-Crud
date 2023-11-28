package poov.cadastrovacina.model.filter;

import java.time.LocalDate;
import poov.cadastrovacina.model.Situacao;

public class PessoaFilter {
    /*
     * Filtro para a classe Pessoa
     */
    private Long codigo;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private Situacao situacao;

    public PessoaFilter() {
    }

    public PessoaFilter(Long codigo, String nome, String cpf, LocalDate dataNascimento, Situacao situacao) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.situacao = situacao;
    }

    // getters and setters...
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    @Override
    public String toString() {
        return "PessoaFilter{" + "codigo=" + codigo + ", nome=" + nome + ", cpf=" + cpf + ", dataNascimento="
                + dataNascimento + ", situacao=" + situacao + '}';
    }
}