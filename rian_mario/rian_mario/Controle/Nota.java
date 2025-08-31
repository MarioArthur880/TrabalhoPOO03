package rian_mario.Controle;

public class Nota {
    
    private Disciplina disciplina;
    private Matricula matricula;
    private double valor;
    
    // Construtor padrão
    public Nota() {
    }
    
    // Construtor com parâmetros
    public Nota(Disciplina disciplina, Matricula matricula, double valor) {
        this.disciplina = disciplina;
        this.matricula = matricula;
        this.valor = valor;
    }
    
    // Construtor de cópia
    public Nota(Nota outra) {
        this.disciplina = outra.disciplina;
        this.matricula = outra.matricula;
        this.valor = outra.valor;
    }
    
    // Método fábrica
    public static Nota criarNota(Disciplina disciplina, Matricula matricula, double valor) {
        if (disciplina != null && matricula != null) {
            return new Nota(disciplina, matricula, valor);
        }
        return null;
    }
    
    // Método fábrica alternativo para criar cópia
    public static Nota criarCopia(Nota original) {
        if (original != null) {
            return new Nota(original);
        }
        return null;
    }
    
    // Getters
    public Disciplina getDisciplina() {
        return disciplina;
    }
    
    public Matricula getMatricula() {
        return matricula;
    }
    
    public double getValor() {
        return valor;
    }
    
    // Setters
    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
    
    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
}
