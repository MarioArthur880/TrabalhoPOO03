package rian_mario.Controle;

import rian_mario.Dados.RepoMatricula;

public class ControleMatricula {
    private RepoMatricula repositorio;

    public ControleMatricula() {
        repositorio = new RepoMatricula();
    }

    public boolean add(Matricula matricula) {
        return repositorio.add(matricula);
    }

    public Matricula[] listar() {
        return repositorio.listar();
    }

    public boolean remover(int idAluno) {
        return repositorio.remover(idAluno);
    }

    public boolean removerMatriculaTurma(String turma) {
        return repositorio.removerMatriculaTurma(turma);
    }

    public void alterarAlunos(Aluno[] alunos) {
        repositorio.alterarAlunos(alunos);
    }

    public Matricula alterarAluno(Aluno aluno) {
        return repositorio.alterarAluno(aluno);
    }
}
