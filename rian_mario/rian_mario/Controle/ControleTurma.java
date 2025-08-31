package rian_mario.Controle;

import rian_mario.Dados.RepositorioTurma;

public class ControleTurma {
    private RepositorioTurma repoTurma;
    
    public ControleTurma() {
        repoTurma = new RepositorioTurma();
    }

    public boolean add(Turma turma) {
        return repoTurma.add(turma);
    }

    public Turma[] listar() {
        return repoTurma.listar();
    }

    public boolean removerTurma(int ano) {
        return repoTurma.remover(ano);
    }

    public boolean alterar(int ano, Turma novaTurma) {
        return repoTurma.alterar(ano, novaTurma);
    }

    public Turma getInstance(int ano, int vagas, Disciplina[] disciplinas, Matricula[] matriculas) {
        return Turma.criarTurma(ano, vagas, disciplinas, matriculas);
    }
}