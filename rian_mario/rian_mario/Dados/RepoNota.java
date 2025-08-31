package rian_mario.Dados;

import rian_mario.Controle.Disciplina;
import rian_mario.Controle.Matricula;
import rian_mario.Controle.Turma;

public class RepoNota {
    private Notas[] notas;
    private int qttNotas;

    public RepositorioNota() {
        notas = new Notas[20];
        qttNotas = 0;
    }

    private static boolean mesmaTurma(Matricula m, Turma t) {
        if (m == null || t == null || m.getTurma() == null)
            return false;
        return m.getTurma().getCodTurma() == t.getCodTurma();
    }

    private static boolean mesmaMatricula(Matricula a, Matricula b) {
        if (a == null || b == null || a.getAluno() == null || b.getAluno() == null)
            return false;
        int codAlunoA = a.getAluno().getCodigo();
        int codAlunoB = b.getAluno().getCodigo();
        int codTurmaA = (a.getTurma() != null) ? a.getTurma().getCodTurma() : Integer.MIN_VALUE;
        int codTurmaB = (b.getTurma() != null) ? b.getTurma().getCodTurma() : Integer.MIN_VALUE;
        return codAlunoA == codAlunoB && codTurmaA == codTurmaB;
    }

    private static boolean mesmaDisciplina(Notas n, int codDisc) {
        return n != null && n.getDisciplina() != null && n.getDisciplina().getCddisc() == codDisc;
    }

    private void compactar() {
        shiftNotas();
        contarNotas();
    }

    public boolean removerNota(int codigoDisciplina) {
        boolean removido = false;
        Notas[] novasNotas = new Notas[notas.length];
        int novaQtt = 0;

        for (int i = 0; i < qttNotas; i++) {

            if (!mesmaDisciplina(notas[i], codigoDisciplina)) {
                novasNotas[novaQtt] = notas[i];
                novaQtt++;
            } else {
                removido = true;
            }
        }

        notas = novasNotas;
        qttNotas = novaQtt;

        return removido;
    }

    public boolean removerNota(Turma turma) {
        boolean removido = false;
        for (int i = 0; i < notas.length; i++) {
            Notas n = notas[i];
            if (n != null && mesmaTurma(n.getMatricula(), turma)) {
                notas[i] = null;
                removido = true;
            }
        }
        if (removido)
            compactar();
        return removido;
    }

    public boolean removerNota2(Turma turma, int codigoDisciplina) {
        boolean removido = false;
        for (Matricula matricula : turma.copiaMatriculas()) {
            for (int i = 0; i < notas.length; i++) {
                if (notas[i] != null
                        && notas[i].getMatricula() != null
                        && notas[i].getMatricula().equals(matricula)
                        && notas[i].getDisciplina() != null
                        && notas[i].getDisciplina().getCddisc() == codigoDisciplina) {
                    notas[i] = null;
                    removido = true;
                }
            }
        }

        if (removido)
            compactar();
        return removido;
    }

    public boolean removerNota4(Matricula matricula) {
        boolean removido = false;
        for (int i = 0; i < notas.length; i++) {
            Notas n = notas[i];
            if (n != null && mesmaMatricula(n.getMatricula(), matricula)) {
                notas[i] = null;
                removido = true;
            }
        }
        if (removido)
            compactar();
        return removido;
    }

    public boolean alterarNota(Notas nota) {
        boolean alterou = false;
        if (nota == null || nota.getDisciplina() == null || nota.getMatricula() == null)
            return false;
        int codDisc = nota.getDisciplina().getCddisc();
        Matricula mAlvo = nota.getMatricula();
        for (int i = 0; i < notas.length; i++) {
            Notas n = notas[i];
            if (n != null && mesmaDisciplina(n, codDisc) && mesmaMatricula(n.getMatricula(), mAlvo)) {
                n.setValor(nota.getValor());
                alterou = true;
            }
        }
        return alterou;
    }

    public boolean add(Notas nota) {
        if (nota == null) {
            return false;
        }

        for (int i = 0; i < qttNotas; i++) {
            Notas n = notas[i];
            if (n != null &&
                    n.getDisciplina().getCddisc() == nota.getDisciplina().getCddisc() &&
                    n.getMatricula().equals(nota.getMatricula())) {
                return false;
            }
        }

        if (notas.length == qttNotas) {
            aumentarVetorNotas();
        }

        notas[qttNotas] = nota;
        qttNotas++;
        return true;
    }

    private void shiftNotas() {
        int pos = 0;
        Notas[] vaux = new Notas[notas.length];
        for (int i = 0; i < notas.length; i++) {
            if (notas[i] != null) {
                vaux[pos++] = notas[i];
            }
        }
        notas = vaux;
    }

    private void contarNotas() {
        qttNotas = 0;
        for (int i = 0; i < notas.length; i++) {
            if (notas[i] != null)
                qttNotas++;
        }
    }

    private boolean aumentarVetorNotas() {
        int j = notas.length * 2;
        Notas[] vaux = new Notas[j];
        for (int i = 0; i < notas.length; i++) {
            vaux[i] = notas[i];
        }
        notas = vaux;
        return true;
    }

    public Notas[] getNotas() {
        Notas[] notasAtuais = new Notas[qttNotas];
        for (int i = 0; i < qttNotas; i++) {
            if (notas[i] != null)
                notasAtuais[i] = new Notas(notas[i]);
        }
        return notasAtuais;
    }

    public Notas getNota(Disciplina disciplina, Matricula matricula) {
        int codDisc = (disciplina != null) ? disciplina.getCddisc() : Integer.MIN_VALUE;
        for (Notas n : notas) {
            if (n != null && mesmaDisciplina(n, codDisc) && mesmaMatricula(n.getMatricula(), matricula)) {
                return n;
            }
        }
        return null;
    }
}
