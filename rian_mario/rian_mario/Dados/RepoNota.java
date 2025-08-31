package rian_mario.Dados;

import rian_mario.Controle.Nota;
import rian_mario.Controle.Disciplina;
import rian_mario.Controle.Matricula;

public class RepoNota {
    private Nota[] notas = new Nota[10];
    private int qttNotas;

    public RepoNota() {
        qttNotas = 0;
    }

    public boolean add(Nota nota) {
        contarNotas();
        shiftNotas();
        if (nota != null) {
            if (notas.length == qttNotas) {
                aumentarVetorNotas();
            }
            notas[qttNotas] = nota;
            qttNotas++;
            return true;
        }
        return false;
    }

    private void contarNotas() {
        qttNotas = 0;
        for (int i = 0; i < notas.length; i++) {
            if (notas[i] != null) {
                qttNotas++;
            }
        }
    }

    private void aumentarVetorNotas() {
        int j = notas.length * 2;
        Nota[] vaux = new Nota[j];
        for (int i = 0; i < notas.length; i++) {
            vaux[i] = notas[i];
        }
        notas = vaux;
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

    public Nota[] getNotas() {
        contarNotas();
        if (qttNotas == 0) {
            return new Nota[0];
        }
        
        Nota[] copia = new Nota[qttNotas];
        int pos = 0;
        
        for (Nota n : notas) {
            if (n != null) {
                copia[pos++] = new Nota(n);
            }
        }
        return copia;
    }

    public boolean alterarNota(Nota notaAlterada) {
        if (notaAlterada != null) {
            for (int i = 0; i < notas.length; i++) {
                if (notas[i] != null && 
                    notas[i].getDisciplina().getIdDisc() == notaAlterada.getDisciplina().getIdDisc() &&
                    notas[i].getMatricula().getAluno().getId() == notaAlterada.getMatricula().getAluno().getId()) {
                    notas[i].setValor(notaAlterada.getValor());
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removerNotaDisciplina(int idDisc) {
        boolean removed = false;
        for (int i = 0; i < notas.length; i++) {
            if (notas[i] != null && notas[i].getDisciplina().getIdDisc() == idDisc) {
                notas[i] = null;
                qttNotas--;
                removed = true;
            }
        }
        if (removed) {
            shiftNotas();
        }
        return removed;
    }

    public boolean removerNotaMatricula(int idAluno) {
        boolean removed = false;
        for (int i = 0; i < notas.length; i++) {
            if (notas[i] != null && notas[i].getMatricula().getAluno().getId() == idAluno) {
                notas[i] = null;
                qttNotas--;
                removed = true;
            }
        }
        if (removed) {
            shiftNotas();
        }
        return removed;
    }

    public Nota getNota(Disciplina disciplina, Matricula matricula) {
        if (disciplina != null && matricula != null) {
            for (int i = 0; i < notas.length; i++) {
                if (notas[i] != null &&
                    notas[i].getDisciplina().getIdDisc() == disciplina.getIdDisc() &&
                    notas[i].getMatricula().getAluno().getId() == matricula.getAluno().getId()) {
                    return notas[i];
                }
            }
        }
        return null;
    }
}