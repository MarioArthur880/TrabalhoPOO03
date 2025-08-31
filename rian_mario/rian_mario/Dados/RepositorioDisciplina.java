package rian_mario.Dados;

import rian_mario.Controle.Disciplina;

public class RepositorioDisciplina {
    private Disciplina[] discs = new Disciplina[3];
    private int qttDisc;
    private int proxId;

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

    public int getProxId() {
        return proxId++;
    }

    public boolean remover(int id) {
        for (int i = 0; i < discs.length; i++) {
            if (discs[i] != null && discs[i].getIdDisc() == id) {
                discs[i] = null;
                qttDisc--;
                shiftDisciplinas();
                return true;
            }
        }
        return false;
    }

    public Disciplina buscar(int id) {
        for (int i = 0; i < discs.length; i++) {
            if (discs[i] != null && discs[i].getIdDisc() == id) {
                return discs[i];
            }
        }
        return null;
    }

    public Disciplina alterarNome(int id, String novoNome) {
        for (int i = 0; i < discs.length; i++) {
            if (discs[i] != null && discs[i].getIdDisc() == id) {
                discs[i].setNomeDisc(novoNome);
                return discs[i];
            }
        }
        return null;
    }

    public Disciplina alterarProfessor(int id, String novoProfessor) {
        for (int i = 0; i < discs.length; i++) {
            if (discs[i] != null && discs[i].getIdDisc() == id) {
                discs[i].setProf(novoProfessor);
                return discs[i];
            }
        }
        return null;
    }

    public boolean verificarId(int id) {
        for (int i = 0; i < discs.length; i++) {
            if (discs[i] != null && discs[i].getIdDisc() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean verificarNome(String nome) {
        if (nome == null) return false;
        
        for (int i = 0; i < discs.length; i++) {
            if (discs[i] != null && discs[i].getNomeDisc() != null) {
                if (discs[i].getNomeDisc().equals(nome)) {
                    return false; // Nome já existe
                }
            }
        }
        return true; // Nome não existe
    }

    public Disciplina getDisciplina(int id) {
        for (int i = 0; i < discs.length; i++) {
            if (discs[i] != null && discs[i].getIdDisc() == id) {
                return discs[i];
            }
        }
        return null;
    }

    public void setListaDisciplina(Disciplina[] disciplinas) {
        discs = disciplinas;
    }
}
