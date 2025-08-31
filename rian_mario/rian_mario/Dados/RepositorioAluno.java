package rian_mario.Dados;

import rian_mario.Controle.Aluno;


// responsavel pelo armazenamento (vetor, arraylist, arquivo, BD,...)
public class RepositorioAluno {
	private Aluno alunos[] = new Aluno[3];
	
	private int qttAluno;
	private int proxCodigo;

	public boolean add(Aluno a) {
		contarAluno();
		shiftAlunos();
		if (a != null) {
			if (alunos.length == qttAluno) {
				aumentarVetorAluno();
				
				alunos[qttAluno] = a;
				return true;
			}
			alunos[qttAluno] = a;
			return true;
		} else {
			return false;
		}
	}

	private void contarAluno() {
		qttAluno = 0;
		for (int i = 0; i < alunos.length; i++) {

			if (alunos[i] != null) {
				qttAluno++;

			}

		}
	}

	private boolean aumentarVetorAluno() {

		int j = alunos.length * 2;
		Aluno[] vaux = new Aluno[j];
		for (int i = 0; i < alunos.length; i++) {
			vaux[i] = alunos[i];
		}

		alunos = vaux;
		return true;
	}

	public boolean excluir(int numMatricula) {
		return true;
	}

	public boolean alterar(int a, Aluno auxA) {
		alunos[a] = auxA;
		return true;
	}

	

	public Aluno buscar(int numMatricula) {
		return null;
	}

	public Aluno[] getListaAluno() {

		Aluno copia[] = new Aluno[alunos.length];

		int i = 0;
		for (Aluno a : alunos) {
			if (a != null)
				copia[i] = new Aluno(a); 
			i++;
		}

		return copia;

	}

	public Aluno[] getListaAlunoNota() {

		Aluno copia[] = new Aluno[alunos.length];

		int i = 0;
		for (Aluno a : alunos) {
			if (a != null)
				copia[i] = new Aluno(a); 
			i++;
		}
for (int x = 0; x < copia.length - 1; x++) {
			for (int y = x + 1; y < copia.length; y++) {
				if (copia[x] != null && copia[y] != null) {
					if (copia[x].getMedia() < copia[y].getMedia()) {
						Aluno aux = copia[x];
						copia[x] = copia[y];
						copia[y] = aux;
					}
				}
			}
		}


		return copia;

	}

	public boolean verificarCodigo(int aaux) {
		boolean k = false;
		for (int i = 0; i < alunos.length; i++) {
			if (alunos[i] != null && alunos[i].getCodigo() == aaux) {
				k = true;
			}
		}

		return k;
	}

	public boolean verificarNome(String nome) {
		boolean k = false;
		String nm = "";
		contarAluno();

		for (int i = 0; i < qttAluno; i++) {
			nm = alunos[i].getNmaluno();
			if (nm.equals(nome)) {
				k = true;
			}
		}

		if (k == true) {
			return false;
		} else {
			return true;
		}
	}

	private void shiftAlunos() {
    int pos = 0;
    Aluno[] vaux = new Aluno[alunos.length];

    for (int i = 0; i < alunos.length; i++) {
        if (alunos[i] != null) {
            vaux[pos++] = alunos[i];
        }
    }

    alunos = vaux;
}

	public boolean buscarPorNome(Aluno a) {
		for (int i = 0; i < alunos.length; i++) {
			if (alunos[i] != null && alunos[i].getNmaluno().equals(a.getNmaluno())) {
				return true;
			}
		}
		return false;
	}

	public int getProxCodigo() {
		return proxCodigo++;
	}

    public Aluno getAluno(int codigoAluno) {
        for (int i = 0; i < alunos.length; i++) {
			if (alunos[i] != null && alunos[i].getCodigo() == codigoAluno) {
				return alunos[i];
			}
		}
		return null;
    }

	public void setListaAluno(Aluno[] alunos2) {
		
		alunos = alunos2;
	}

    public boolean alterarNome(int i, String nmaluno) {
        if (nmaluno != null) {
			for (int j = 0; j < alunos.length; j++) {
				if (alunos[j] != null && alunos[j].getCodigo() == i) {
					alunos[j].setNmaluno(nmaluno);
					return true;
				}
			}
		} 
			return false;
		
    }

	public boolean alterarCpf(int i, String cpf) {
		if (cpf != null) {
			for (int j = 0; j < alunos.length; j++) {
				if (alunos[j] != null && alunos[j].getCodigo() == i) {
					alunos[j].setCpf(cpf);
					return true;
				}
			}
		}
		return false;
	}

}
