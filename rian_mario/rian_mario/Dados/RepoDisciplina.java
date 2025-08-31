package rian_mario.Dados;

import rian_mario.Controle.Disciplina;

public class RepoDisciplina {
    private Disciplina[] discs = new Disciplina[3];
    private int qttDisc;
    private int proxCodigo;

    public boolean add(Disciplina disc) {
        contarDisc();
        shiftDisciplinas();
        if (disc != null) {
            if (discs.length == qttDisc) {
                aumentarVetorDisciplina();
            }
            discs[qttDisc++] = disc;
            return true;
        }
        return false;
    }

    private void contarDisc() {
        qttDisc = 0;
        for (Disciplina d : discs) {
            if (d != null) qttDisc++;
        }
    }

    private void aumentarVetorDisciplina() {
        Disciplina[] vaux = new Disciplina[discs.length * 2];
        for (int i = 0; i < discs.length; i++) {
            vaux[i] = discs[i];
        }
        discs = vaux;
    }

    private void shiftDisciplinas() {
        int pos = 0;
        Disciplina[] vaux = new Disciplina[discs.length];
        for (Disciplina d : discs) {
            if (d != null) vaux[pos++] = d;
        }
        discs = vaux;
    }

    public Disciplina[] listar() {
        Disciplina[] copia = new Disciplina[qttDisc];
        int index = 0;
        for (Disciplina d : discs) {
            if (d != null) copia[index++] = new Disciplina(d);
        }
        return copia;
    }

    public int getProxCodigo() {
        return proxCodigo++;
    }

    public boolean remover(int codigo) {
        for (int i = 0; i < discs.length; i++) {
            if (discs[i] != null && discs[i].getCddisc() == codigo) {
                discs[i] = null;
                qttDisc--;
                shiftDisciplinas();
                return true;
            }
        }
        return false;
    }

    

    public Disciplina alterarNome(int codigo, String novoNome) {
        for (int i = 0; i < discs.length; i++) {
            if (discs[i] != null && discs[i].getCddisc() == codigo) {
                discs[i].setNmdisc(novoNome);
                return discs[i];
            }
        }
        return null;
    }

    public Disciplina alterarProfessor(int codigo, String novoProfessor) {
        for (int i = 0; i < discs.length; i++) {
            if (discs[i] != null && discs[i].getCddisc() == codigo) {
                discs[i].setNmprof(novoProfessor);
                return discs[i];
            }
        }
        return null;
    }

    public Disciplina alterarEtapa(int codigo, int novaEtapa) {
        for (int i = 0; i < discs.length; i++) {
            if (discs[i] != null && discs[i].getCddisc() == codigo) {
                discs[i].setEtapa(novaEtapa);
                return discs[i];
            }
        }
        return null;
    }
}
