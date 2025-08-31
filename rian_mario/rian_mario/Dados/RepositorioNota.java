package rian_mario.Dados;

import rian_mario.Controle.Disciplina;
import rian_mario.Controle.Matricula;
import rian_mario.Controle.Nota;
import rian_mario.Controle.Turma;

public class RepositorioNota {
    private Nota[] notas;
    private int qttNotas;

    public RepositorioNota() {
        notas = new Nota[20];
        qttNotas = 0;
    }

    private static boolean mesmaTurma(Matricula m, String turma) {
        if (m == null || turma == null || m.getTurma() == null)
            return false;
        return m.getTurma().equals(turma);
    }

    private static boolean mesmaMatricula(Matricula a, Matricula b) {
        if (a == null || b == null || a.getAluno() == null || b.getAluno() == null)
            return false;
        int idAlunoA = a.getAluno().getId();
        int idAlunoB = b.getAluno().getId();
        String turmaA = (a.getTurma() != null) ? a.getTurma() : "";
        String turmaB = (b.getTurma() != null) ? b.getTurma() : "";
        return idAlunoA == idAlunoB && turmaA.equals(turmaB);
    }

    private static boolean mesmaDisciplina(Nota n, int idDisc) {
        return n != null && n.getDisciplina() != null && n.getDisciplina().getIdDisc() == idDisc;
    }

    private void compactar() {
        shiftNotas();
        contarNotas();
    }

    public boolean removerNota(int idDisciplina) {
        boolean removido = false;
        Nota[] novasNotas = new Nota[notas.length];
        int novaQtt = 0;

        for (int i = 0; i < qttNotas; i++) {
            if (!mesmaDisciplina(notas[i], idDisciplina)) {
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

    public boolean removerNota(String turma) {
        boolean removido = false;
        for (int i = 0; i < notas.length; i++) {
            Nota n = notas[i];
            if (n != null && mesmaTurma(n.getMatricula(), turma)) {
                notas[i] = null;
                removido = true;
            }
        }
        if (removido)
            compactar();
        return removido;
    }

    public boolean removerNota2(String turma, int idDisciplina) {
        boolean removido = false;
        for (int i = 0; i < notas.length; i++) {
            if (notas[i] != null
                    && notas[i].getMatricula() != null
                    && mesmaTurma(notas[i].getMatricula(), turma)
                    && notas[i].getDisciplina() != null
                    && notas[i].getDisciplina().getIdDisc() == idDisciplina) {
                notas[i] = null;
                removido = true;
            }
        }

        if (removido)
            compactar();
        return removido;
    }

    public boolean removerNota4(Matricula matricula) {
        boolean removido = false;
        for (int i = 0; i < notas.length; i++) {
            Nota n = notas[i];
            if (n != null && mesmaMatricula(n.getMatricula(), matricula)) {
                notas[i] = null;
                removido = true;
            }
        }
        if (removido)
            compactar();
        return removido;
    }

    public boolean alterarNota(Nota nota) {
        boolean alterou = false;
        if (nota == null || nota.getDisciplina() == null || nota.getMatricula() == null)
            return false;
        int idDisc = nota.getDisciplina().getIdDisc();
        Matricula mAlvo = nota.getMatricula();
        for (int i = 0; i < notas.length; i++) {
            Nota n = notas[i];
            if (n != null && mesmaDisciplina(n, idDisc) && mesmaMatricula(n.getMatricula(), mAlvo)) {
                n.setValor(nota.getValor());
                alterou = true;
            }
        }
        return alterou;
    }

    public boolean add(Nota nota) {
        if (nota == null) {
            return false;
        }

        for (int i = 0; i < qttNotas; i++) {
            Nota n = notas[i];
            if (n != null &&
                    n.getDisciplina().getIdDisc() == nota.getDisciplina().getIdDisc() &&
                    mesmaMatricula(n.getMatricula(), nota.getMatricula())) {
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
        Nota[] vaux = new Nota[notas.length];
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
        Nota[] vaux = new Nota[j];
        for (int i = 0; i < notas.length; i++) {
            vaux[i] = notas[i];
        }
        notas = vaux;
        return true;
    }

    public Nota[] getNotas() {
        Nota[] notasAtuais = new Nota[qttNotas];
        for (int i = 0; i < qttNotas; i++) {
            if (notas[i] != null)
                notasAtuais[i] = new Nota(notas[i]);
        }
        return notasAtuais;
    }

    public Nota getNota(Disciplina disciplina, Matricula matricula) {
        int idDisc = (disciplina != null) ? disciplina.getIdDisc() : Integer.MIN_VALUE;
        for (Nota n : notas) {
            if (n != null && mesmaDisciplina(n, idDisc) && mesmaMatricula(n.getMatricula(), matricula)) {
                return n;
            }
        }
        return null;
    }

    public Nota[] getNotasPorTurma(String turma) {
        Nota[] resultado = new Nota[qttNotas];
        int count = 0;
        
        for (int i = 0; i < qttNotas; i++) {
            if (notas[i] != null && mesmaTurma(notas[i].getMatricula(), turma)) {
                resultado[count++] = new Nota(notas[i]);
            }
        }
        
        // Redimensionar array para o tamanho correto
        Nota[] retorno = new Nota[count];
        for (int i = 0; i < count; i++) {
            retorno[i] = resultado[i];
        }
        
        return retorno;
    }

    public Nota[] getNotasPorAluno(int idAluno) {
        Nota[] resultado = new Nota[qttNotas];
        int count = 0;
        
        for (int i = 0; i < qttNotas; i++) {
            if (notas[i] != null && 
                notas[i].getMatricula() != null && 
                notas[i].getMatricula().getAluno() != null &&
                notas[i].getMatricula().getAluno().getId() == idAluno) {
                resultado[count++] = new Nota(notas[i]);
            }
        }
        
        // Redimensionar array para o tamanho correto
        Nota[] retorno = new Nota[count];
        for (int i = 0; i < count; i++) {
            retorno[i] = resultado[i];
        }
        
        return retorno;
    }
}