package poov.cadastrovacina.model.filter;

import java.time.LocalDate;
import poov.cadastrovacina.model.Situacao;

public class PessoaFilter {
    /*
     * Filtro para a classe Pessoa
     */
    private Long codigo; // Código da pessoa para filtrar
    private String nome; // Nome da pessoa para filtrar
    private String cpf; // CPF da pessoa para filtrar
    private LocalDate dataNascimento; // Data de nascimento da pessoa para filtrar
    private LocalDate dataNascimentoAte; // Data de nascimento até a qual filtrar
    private Situacao situacao; // Situação da pessoa para filtrar

    // Construtor padrão
    public PessoaFilter() {
    }

    // Construtor com parâmetros de código, nome, cpf, data de nascimento e situação
    public PessoaFilter(Long codigo, String nome, String cpf, LocalDate dataNascimento, Situacao situacao) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.situacao = situacao;
    }

    // Getters e setters para os atributos
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

    public LocalDate getDataNascimentoAte() {
        return dataNascimentoAte;
    }

    public void setDataNascimentoAte(LocalDate dataNascimentoAte) {
        this.dataNascimentoAte = dataNascimentoAte;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    // Método toString para representar o objeto como uma string
    @Override
    public String toString() {
        return "PessoaFilter{" + "codigo=" + codigo + ", nome=" + nome + ", cpf=" + cpf + ", dataNascimento="
                + dataNascimento + ", situacao=" + situacao + '}';
    }
}