package poov.cadastrovacina.model;

public enum Situacao {
    
    ATIVO("Ativo"), // Enum para situação ativa
    INATIVO("Inativo"); // Enum para situação inativa

    private String descricao; // Descrição da situação

    // Construtor privado para definir a descrição da situação
    private Situacao(String descricao) {
        this.descricao = descricao;
    }

    // Getter para a descrição da situação
    public String getDescricao() {
        return descricao;
    }
}