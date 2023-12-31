package poov.cadastrovacina.model;

import java.time.LocalDate;

public class Aplicacao {

    private Long codigo; // Identificador único para a aplicação
    private LocalDate data; // Data da aplicação
    private Pessoa pessoa; // Pessoa que recebeu a aplicação
    private Vacina vacina; // Vacina aplicada
    private Situacao situacao; // Situação da aplicação


    // Construtor padrão
    public Aplicacao() {
        pessoa = new Pessoa(); // Pessoa padrão
        vacina = new Vacina(); // Vacina padrão
        situacao = Situacao.ATIVO; // Situação padrão é ATIVO
    }

    // Construtor com parâmetros de data, pessoa, vacina e situação
    public Aplicacao(LocalDate data, Pessoa pessoa, Vacina vacina, Situacao situacao) {
        this.data = data;
        this.pessoa = pessoa;
        this.vacina = vacina;
        this.situacao = situacao;
    }

    // Construtor com parâmetros de código, data, pessoa, vacina e situação
    public Aplicacao(Long codigo, LocalDate data, Pessoa pessoa, Vacina vacina, Situacao situacao) {
        this.codigo = codigo;
        this.data = data;
        this.pessoa = pessoa;
        this.vacina = vacina;
        this.situacao = situacao;
    }

    // getters and setters
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public LocalDate getData() {
        return this.data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Pessoa getPessoa() {
        return this.pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Vacina getVacina() {
        return this.vacina;
    }

    public void setVacina(Vacina vacina) {
        this.vacina = vacina;
    }

    public Situacao getSituacao() {
        return this.situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    // equals, hashCode and toString
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
        result = prime * result + ((vacina == null) ? 0 : vacina.hashCode());
        result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Aplicacao other = (Aplicacao) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (pessoa == null) {
            if (other.pessoa != null)
                return false;
        } else if (!pessoa.equals(other.pessoa))
            return false;
        if (vacina == null) {
            if (other.vacina != null)
                return false;
        } else if (!vacina.equals(other.vacina))
            return false;
        if (situacao != other.situacao)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "codigo: " + codigo + "\ndata: " + data + "\npessoa: " + pessoa + "\nvacina: " + vacina
                + "\nsituacao: " + situacao;
    }
}