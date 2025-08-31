package rian_mario.Controle;

import rian_mario.Dados.RepoAluno;

public class ControleAluno {
    private RepoAluno repoAluno;

    public ControleAluno() {
        repoAluno = new RepoAluno();
    }

    public boolean add(Aluno a) {
        // regra de negócio: não pode existir 2 alunos com mesmo nome
        if (repoAluno.buscarPorNome(a)) {
            return false;
        } else {
            return repoAluno.add(a);
        }
    }

    public boolean buscarPorNome(Aluno a) {
        return repoAluno.buscarPorNome(a);
    }

    public boolean excluir(int idAluno) {
        return repoAluno.excluir(idAluno);
    }

    public boolean verificarId(int id) {
        return repoAluno.verificarId(id);
    }

    public boolean verificarNome(String nome) {
        return repoAluno.verificarNome(nome);
    }

    public Aluno[] getListaAluno() {
        return repoAluno.getListaAluno();
    }

    public boolean alterar(int idAluno, Aluno novoAluno) {
        return repoAluno.alterar(idAluno, novoAluno);
    }

    public Aluno getAluno(int idAluno) {
        return repoAluno.getAluno(idAluno);
    }

    public void setListaAluno(Aluno[] alunos) {
        repoAluno.setListaAluno(alunos);
    }

    public Aluno getInstance(int id, String nome, String cpf) {
        return Aluno.criarAluno(id, nome, cpf);
    }

    public boolean alterarNome(int idAluno, String novoNome) {
        return repoAluno.alterarNome(idAluno, novoNome);
    }

    public boolean alterarCpf(int idAluno, String novoCpf) {
        return repoAluno.alterarCpf(idAluno, novoCpf);
    }
}