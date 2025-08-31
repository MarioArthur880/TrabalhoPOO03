package rian_mario.Controle;

public class Turma {

    private int ano;
    private int vagas;
    private Disciplina[] disciplinas;
    private Matricula[] matriculas;
    
    // Construtor padrão
    public Turma() {
    }
    
    // Construtor com parâmetros
    public Turma(int ano, int vagas, Disciplina[] disciplinas, Matricula[] matriculas) {
        this.ano = ano;
        this.vagas = vagas;
        this.disciplinas = disciplinas;
        this.matriculas = matriculas;
    }
    
    // Construtor de cópia
    public Turma(Turma outra) {
        this.ano = outra.ano;
        this.vagas = outra.vagas;
        this.disciplinas = outra.disciplinas;
        this.matriculas = outra.matriculas;
    }
    
    // Método fábrica
    public static Turma criarTurma(int ano, int vagas, Disciplina[] disciplinas, Matricula[] matriculas) {
        if (disciplinas != null && matriculas != null) {
            return new Turma(ano, vagas, disciplinas, matriculas);
        }
        return null;
    }
    
    // Método fábrica alternativo para criar cópia
    public static Turma criarCopia(Turma original) {
        if (original != null) {
            return new Turma(original);
        }
        return null;
    }
    
    // Getters
    public int getAno() {
        return ano;
    }
    
    public int getVagas() {
        return vagas;
    }
    
    public Disciplina[] getDisciplinas() {
        return disciplinas;
    }
    
    public Matricula[] getMatriculas() {
        return matriculas;
    }
    
    // Setters
    public void setAno(int ano) {
        this.ano = ano;
    }
    
    public void setVagas(int vagas) {
        this.vagas = vagas;
    }
    
    public void setDisciplinas(Disciplina[] disciplinas) {
        this.disciplinas = disciplinas;
    }
    
    public void setMatriculas(Matricula[] matriculas) {
        this.matriculas = matriculas;
    }
    
}
