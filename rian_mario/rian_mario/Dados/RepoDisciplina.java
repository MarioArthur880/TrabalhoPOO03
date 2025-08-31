package rian_mario.Dados;

import rian_mario.Controle.Disciplina;

public class RepoDisciplina {
    private Disciplina[] disciplinas = new Disciplina[10];
    private int qttDisciplinas;

    public RepoDisciplina() {
        qttDisciplinas = 0;
    }

    public boolean add(Disciplina disciplina) {
        contarDisciplinas();
        shiftDisciplinas();
        if (disciplina != null) {
            if (disciplinas.length == qttDisciplinas) {
                aumentarVetorDisciplinas();
            }
            disciplinas[qttDisciplinas] = disciplina;
            qttDisciplinas++;
            return true;
        }
        return false;
    }

    private void contarDisciplinas() {
        qttDisciplinas = 0;
        for (int i = 0; i < disciplinas.length; i++) {
            if (disciplinas[i] != null) {
                qttDisciplinas++;
            }
        }
    }

    private void aumentarVetorDisciplinas() {
        int j = disciplinas.length * 2;
        Disciplina[] vaux = new Disciplina[j];
        for (int i = 0; i < disciplinas.length; i++) {
            vaux[i] = disciplinas[i];
        }
        disciplinas = vaux;
    }

    private void shiftDisciplinas() {
        int pos = 0;
        Disciplina[] vaux = new Disciplina[disciplinas.length];

        for (int i = 0; i < disciplinas.length; i++) {
            if (disciplinas[i] != null) {
                vaux[pos++] = disciplinas[i];
            }
        }
        disciplinas = vaux;
    }

    public Disciplina[] listar() {
        contarDisciplinas();
        if (qttDisciplinas == 0) {
            return new Disciplina[0];
        }
        
        Disciplina[] copia = new Disciplina[qttDisciplinas];
        int pos = 0;
        
        for (Disciplina d : disciplinas) {
            if (d != null) {
                copia[pos++] = new Disciplina(d);
            }
        }
        return copia;
    }

    public boolean remover(int idDisc) {
        for (int i = 0; i < disciplinas.length; i++) {
            if (disciplinas[i] != null && disciplinas[i].getIdDisc() == idDisc) {
                disciplinas[i] = null;
                qttDisciplinas--;
                shiftDisciplinas();
                return true;
            }
        }
        return false;
    }

    public boolean alterarNome(int idDisc, String novoNome) {
        if (novoNome != null) {
            for (int i = 0; i < disciplinas.length; i++) {
                if (disciplinas[i] != null && disciplinas[i].getIdDisc() == idDisc) {
                    disciplinas[i].setNomeDisc(novoNome);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean alterarProfessor(int idDisc, String novoProfessor) {
        if (novoProfessor != null) {
            for (int i = 0; i < disciplinas.length; i++) {
                if (disciplinas[i] != null && disciplinas[i].getIdDisc() == idDisc) {
                    disciplinas[i].setProf(novoProfessor);
                    return true;
                }
            }
        }
        return false;
    }
}
