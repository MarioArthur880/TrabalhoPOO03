package rian_mario.Controle;

public class Aluno {

    private int id;
    private String nomeAluno;
    private String cpf;
    
    // Construtor com parâmetros
    public Aluno(int id, String nomeAluno, String cpf) {
        this.id = id;
        this.nomeAluno = nomeAluno;
        this.cpf = cpf;
    }
    
    // Construtor de cópia
    public Aluno(Aluno outro) {
        this.id = outro.id;
        this.nomeAluno = outro.nomeAluno;
        this.cpf = outro.cpf;
    }
    
    // Método fábrica
    public static Aluno criarAluno(int id, String nomeAluno, String cpf) {
        if (nomeAluno != null && cpf != null) {
            return new Aluno(id, nomeAluno, cpf);
        }
        return null;
    }
    
    // Método fábrica alternativo para criar cópia
    public static Aluno criarCopia(Aluno original) {
        if (original != null) {
            return new Aluno(original);
        }
        return null;
    }
    
    // Getters
    public int getId() {
        return id;
    }
    
    public String getNomeAluno() {
        return nomeAluno;
    }
    
    public String getCpf() {
        return cpf;
    }
    
    // Setters
    public void setId(int id) {
        this.id = id;
    }
    
    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}