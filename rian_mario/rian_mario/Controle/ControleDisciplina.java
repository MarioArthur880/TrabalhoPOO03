package rian_mario.Controle;

import rian_mario.Dados.RepositorioDisciplina;

public class ControleDisciplina {
	private RepositorioDisciplina repoDisciplina;

	public ControleDisciplina() {
		repoDisciplina = new RepositorioDisciplina();
	}

	public boolean add(Disciplina disc){
		return repoDisciplina.add(disc);
	}

	
	public Disciplina[] listar (){
		return repoDisciplina.listar();

	}

	public int getProxCodigo() {
		return repoDisciplina.getProxCodigo();
	}

    public boolean remover(int codigo) {
        return repoDisciplina.remover(codigo);
    }

	

    public Disciplina getInstance(int cd, String nome, String nomeProfessor, String sigla) {
        return Disciplina.getInstance(cd, nome, nomeProfessor, sigla);
    }

    public Disciplina alterarNome(int codigo, String novoNome) {
        return repoDisciplina.alterarNome(codigo, novoNome);
    }

	public Disciplina alterarProfessor(int codigo, String novoProfessor) {
		return repoDisciplina.alterarProfessor(codigo, novoProfessor);
	}

}
