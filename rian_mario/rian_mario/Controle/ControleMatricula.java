package rian_mario.Controle;

import rian_mario.Dados.RepositorioMatriculas;

public class ControleMatricula {
    private RepositorioMatriculas repositorio;

    public ControleMatricula() {
        repositorio = new RepositorioMatriculas();
    }

    public boolean add(Matricula matricula) {
        return repositorio.add(matricula);
    }

    public Matricula[] listar() {
        return repositorio.listar();
    }

    public boolean remover(Matricula matricula) {
        return repositorio.remover(matricula);
    }

    public boolean removerMatriculaTurma(Turma turma) {
        return repositorio.removerMatriculaTurma(turma);
    }

    public void AlterarMatriculas(Aluno[] alunos) {
        repositorio.AlterarMatriculas(alunos);
    }

    public Matricula alterarAluno(Aluno aluno) {
        return repositorio.alterarAluno(aluno);
    }

}
