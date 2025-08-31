package rian_mario.Controle;

import rian_mario.Dados.RepoDisciplina;

public class ControleDisciplina {
    private RepoDisciplina repoDisciplina;

    public ControleDisciplina() {
        repoDisciplina = new RepoDisciplina();
    }

    public boolean add(Disciplina disc) {
        return repoDisciplina.add(disc);
    }

    public Disciplina[] listar() {
        return repoDisciplina.listar();
    }

    public boolean remover(int idDisc) {
        return repoDisciplina.remover(idDisc);
    }

    public Disciplina getInstance(int idDisc, String nomeDisc, String prof) {
        return Disciplina.criarDisciplina(idDisc, nomeDisc, prof);
    }

    public boolean alterarNome(int idDisc, String novoNome) {
        return repoDisciplina.alterarNome(idDisc, novoNome);
    }

    public boolean alterarProfessor(int idDisc, String novoProfessor) {
        return repoDisciplina.alterarProfessor(idDisc, novoProfessor);
    }
}
