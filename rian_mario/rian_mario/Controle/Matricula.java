package rian_mario.Controle;

import java.time.LocalDateTime;

public class Matricula {

    private LocalDateTime data;
    private String turma;
    private Aluno aluno;
    
    // Construtor padrão
    public Matricula() {
    }
    
    // Construtor com parâmetros
    public Matricula(LocalDateTime data, String turma, Aluno aluno) {
        this.data = data;
        this.turma = turma;
        this.aluno = aluno;
    }
    
    // Construtor de cópia
    public Matricula(Matricula outra) {
        this.data = outra.data;
        this.turma = outra.turma;
        this.aluno = outra.aluno;
    }
    
    // Método fábrica
    public static Matricula criarMatricula(LocalDateTime data, String turma, Aluno aluno) {
        if (data != null && turma != null && aluno != null) {
            return new Matricula(data, turma, aluno);
        }
        return null;
    }
    
    // Método fábrica alternativo para criar cópia
    public static Matricula criarCopia(Matricula original) {
        if (original != null) {
            return new Matricula(original);
        }
        return null;
    }
    
    // Getters
    public LocalDateTime getData() {
        return data;
    }
    
    public String getTurma() {
        return turma;
    }
    
    public Aluno getAluno() {
        return aluno;
    }
    
    // Setters
    public void setData(LocalDateTime data) {
        this.data = data;
    }
    
    public void setTurma(String turma) {
        this.turma = turma;
    }
    
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}