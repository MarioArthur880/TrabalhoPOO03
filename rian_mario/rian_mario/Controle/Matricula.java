package rian_mario.Controle;

import java.time.LocalDateTime;


public class Matricula {
    private Turma turma;
    private Aluno aluno;
    private LocalDateTime data;
    private int codigo;

    private Matricula(Turma turma, Aluno aluno) {
        this.turma = turma;
        this.aluno = aluno;
        this.data = LocalDateTime.now();
    }

    public Matricula(Matricula m) {
        this.turma = m.getTurma();
        this.aluno = new Aluno(m.getAluno());
        this.data = m.getData();
    }

    public static Matricula getInstance(Turma turma, Aluno aluno) {
        if (turma != null && aluno != null) {
            return new Matricula(turma, aluno);
        }
        return null;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setAluno(Aluno aluno2) {
        this.aluno = aluno2;
    }

    public void setCodigo(int i) {
        this.codigo = i;
    }


    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Matricula that = (Matricula) o;
        return this.getAluno() != null && that.getAluno() != null
                && this.getTurma() != null && that.getTurma() != null
                && this.getAluno().getCodigo() == that.getAluno().getCodigo()
                && this.getTurma().getCodTurma() == that.getTurma().getCodTurma();
    }

}
