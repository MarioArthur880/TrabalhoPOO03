package rian_mario.Controle;

public class Notas {
    private Disciplina disciplina;
    private Matricula matricula;
    private double valor;

    public Notas(Disciplina disciplina, Matricula matricula, double valor) {
        this.disciplina = disciplina;
        this.matricula = matricula;
        this.valor = valor;
    }
    public Notas(Disciplina disciplina, Matricula matricula) {
        this.disciplina = disciplina;
        this.matricula = matricula;
        this.valor = 0.0;
    }

    public Notas(Notas notas) {
        this.disciplina = notas.getDisciplina();
        this.matricula = notas.getMatricula();
        this.valor = notas.getValor();
    }
    public Disciplina getDisciplina() {
        return disciplina;
    }
    public Matricula getMatricula() {
        return matricula;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    public static Notas getInstance(Disciplina disciplina2, Matricula matricula2, double novaNota) {
        if (disciplina2 == null || matricula2 == null) {
        }
        return new Notas(disciplina2, matricula2, novaNota);
    }

    
}
