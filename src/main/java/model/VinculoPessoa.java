package model;

public enum VinculoPessoa {
    SERVIDOR("Servidor"),
    ALUNO("Aluno"),
    TERCEIRIZADO("Terceirizado"),
    VISITANTE("Visitante");

    private final String descricao;

    VinculoPessoa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
