package rian_mario.Dados;

import rian_mario.Controle.Disciplina;

public class RepositorioDisciplina {

    private Disciplina[] discs = new Disciplina[3];
    private int quantidadeDisc;
    private int proxCodigo;

    public boolean add(Disciplina disc) {
        if (disc == null) return false;

        contarDisc();
        shiftDisciplinas();

        if (quantidadeDisc == discs.length) {
            aumentarVetorDisciplina();
        }

        discs[quantidadeDisc++] = disc;
        return true;
    }

    private void contarDisc() {
        quantidadeDisc = 0;
        for (Disciplina d : discs) {
            if (d != null) {
                quantidadeDisc++;
            }
        }
    }

    private void aumentarVetorDisciplina() {
        Disciplina[] aux = new Disciplina[discs.length * 2];
        for (int i = 0; i < discs.length; i++) {
            aux[i] = discs[i];
        }
        discs = aux;
    }

    private void shiftDisciplinas() {
        Disciplina[] aux = new Disciplina[discs.length];
        int pos = 0;
        for (Disciplina d : discs) {
            if (d != null) {
                aux[pos++] = d;
            }
        }
        discs = aux;
    }

    public Disciplina[] listar() {
        Disciplina[] copia = new Disciplina[quantidadeDisc];
        int index = 0;
        for (Disciplina d : discs) {
            if (d != null) {
                copia[index++] = new Disciplina(d);
            }
        }
        return copia;
    }

    public int proximoCodigo() {
        return proxCodigo++;
    }

    public boolean remover(int codigo) {
        for (int i = 0; i < discs.length; i++) {
            if (discs[i] != null && discs[i].getcodigoDisc() == codigo) {
                discs[i] = null;
                quantidadeDisc--;
                shiftDisciplinas();
                return true;
            }
        }
        return false;
    }

    public Disciplina alterarNome(int codigo, String novoNome) {
    if (novoNome == null) return null;
    for (Disciplina d : discs) {
        if (d != null && d.getcodigoDisc() == codigo) {
            d.setNmdisc(novoNome);
            return d;
        }
    }
    return null;
}

public Disciplina alterarProfessor(int codigo, String novoProfessor) {
    if (novoProfessor == null) return null;
    for (Disciplina d : discs) {
        if (d != null && d.getcodigoDisc() == codigo) {
            d.setNmprof(novoProfessor);
            return d;
        }
    }
    return null;
}

}

