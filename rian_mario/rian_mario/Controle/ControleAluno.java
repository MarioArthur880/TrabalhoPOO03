package rian_mario.Controle;

import rian_mario.Dados.RepositorioAluno;

// Responsavel regras de negocio
public class ControleAluno {
	private RepositorioAluno repoAluno;

	public ControleAluno() {
		repoAluno = new RepositorioAluno();
	}

	public boolean add(Aluno a) {
		if (repoAluno.buscarPorNome(a))
			return false;
		else
			return repoAluno.add(a);
	}

	public boolean buscarPorNome(Aluno a) {
		return false;
	}

	public boolean excluir(int numMatricula) {
		return true;
	}

	
	
	
	public boolean verificarCodigo(int aaux) {
		return repoAluno.verificarCodigo(aaux);
	}

	public boolean verificarNome(String nome) {
		return repoAluno.verificarNome(nome);
	}

	 public int getProxCodigo() {
		return repoAluno.getProxCodigo();
	}

    public  Aluno[] getListaAluno() {
        return repoAluno.getListaAluno();
    }

	public boolean alterar(int i, Aluno auxA) {
		return repoAluno.alterar(i, auxA);
	}

   

	public Aluno getAluno(int codigoAluno) {
		return repoAluno.getAluno(codigoAluno);
	}

	public void setListaAluno(Aluno[] alunos) {
		repoAluno.setListaAluno(alunos);
	}

    public Aluno getInstance(int cd, String nome, String cpf) {
        return Aluno.getInstance(cd, nome, cpf);
    }

	public boolean alterarNome(int i, String nmaluno) {
		return repoAluno.alterarNome(i, nmaluno);
	}
	public boolean alterarCpf(int i, String cpf) {
		return repoAluno.alterarCpf(i, cpf);
	}
	public Aluno[] listarAlunosNota() {
		return repoAluno.getListaAlunoNota();
	}

}
