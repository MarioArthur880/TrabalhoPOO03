package rian_mario.Controle;



// FACHADA (CONTROLÃO: CONTROLE DOS CONTROLES)
public class Sistema {
	private ControleNotas cNota;
	private ControleDisciplina cDisciplina;
	private ControleAluno cAluno;
	private ControleTurma cTurma;
	private ControleMatricula cMatricula;

	private static Sistema instance; // vai referenciar o unico objeto

	private Sistema() {
		cAluno = new ControleAluno();
		cDisciplina = new ControleDisciplina();
		cTurma = new ControleTurma();
		cMatricula = new ControleMatricula();
		cNota = new ControleNotas();
		init();

	}

	// singleton
	public static Sistema getInstance() {
		if (instance == null)
			instance = new Sistema();

		return instance;
	}

	// aluno
	public boolean cadastrarAluno(Aluno a) {
		return cAluno.add(a);
	}

	public boolean alterarAlunoNome(int i, String nmaluno) {
		if (nmaluno != null && cAluno.alterarNome(i, nmaluno)
				&& cTurma.alterarMatricula(cMatricula.alterarAluno(cAluno.getAluno(i))))
			return true;
		else
			return false;
	}

	public boolean alterarAlunoCPF(int i, String cpf) {
		if (cpf != null && cAluno.alterarCpf(i, cpf)
				&& cTurma.alterarMatricula(cMatricula.alterarAluno(cAluno.getAluno(i))))
			return true;
		else
			return false;
	}


	
	public Aluno[] listarAlunos() {
		return cAluno.getListaAluno();
	}

public void init() {
    // 1. Criação dos alunos
    Aluno a1 = Aluno.getInstance(getProxCodigoAluno(), "Rian", "12332121103");
    cAluno.add(a1);
    Aluno a2 = Aluno.getInstance(getProxCodigoAluno(), "Mario", "74572467123");
    cAluno.add(a2);
    Aluno a3 = Aluno.getInstance(getProxCodigoAluno(), "Cadu", "02934567659");
    cAluno.add(a3);
    Aluno a4 = Aluno.getInstance(getProxCodigoAluno(), "Vitin", "56478690812");
    cAluno.add(a4);
    Aluno a5 = Aluno.getInstance(getProxCodigoAluno(), "Otavio", "64590752246");
    cAluno.add(a5);

    // 2. Criação das disciplinas
    Disciplina d1 = Disciplina.getInstance(getProxCodigoDisc(), "Banco de dados", "Balbino", "BD");
    cDisciplina.add(d1);
    Disciplina d2 = Disciplina.getInstance(getProxCodigoDisc(), "Análise e projeto de sistemas", "Marcia", "APS");
    cDisciplina.add(d2);
    Disciplina d3 = Disciplina.getInstance(getProxCodigoDisc(), "Programação orientada a objetos", "Luciano", "POO");
    cDisciplina.add(d3);
    Disciplina d4 = Disciplina.getInstance(getProxCodigoDisc(), "Fundamentos da programação", "Maurílio", "FP");
    cDisciplina.add(d4);
    Disciplina d5 = Disciplina.getInstance(getProxCodigoDisc(), "Programação para web", "Pantuza", "PW");
    cDisciplina.add(d5);

    // 3. Criação das turmas e adição de disciplinas
    Turma t1 = Turma.getInstance(getProxCodigoTurma(), "Turma A", 2, 10, "ta");
    cTurma.add(t1);
    cTurma.adicionarDisciplina(t1.getCodTurma(), d1);
    cTurma.adicionarDisciplina(t1.getCodTurma(), d2);
    cTurma.adicionarDisciplina(t1.getCodTurma(), d3);

    Turma t2 = Turma.getInstance(getProxCodigoTurma(), "Turma B", 3, 15, "tb");
    cTurma.add(t2);
    cTurma.adicionarDisciplina(t2.getCodTurma(), d3);
    cTurma.adicionarDisciplina(t2.getCodTurma(), d4);
    cTurma.adicionarDisciplina(t2.getCodTurma(), d5);

    Turma t3 = Turma.getInstance(getProxCodigoTurma(), "Turma C", 1, 20, "tc");
    cTurma.add(t3);
    cTurma.adicionarDisciplina(t3.getCodTurma(), d2);
    cTurma.adicionarDisciplina(t3.getCodTurma(), d5);
    cTurma.adicionarDisciplina(t3.getCodTurma(), d1);
    
    // ---
    
    // 4. Matricular alunos e atribuir notas
	
    // Aluno Joao da Silva
    matricularAlunoEAtribuirNotas(a1, t1, new Disciplina[] {d1, d2, d3}, new double[] {90.0, 80.0, 70.0});
    matricularAlunoEAtribuirNotas(a1, t2, new Disciplina[] {d3, d4, d5}, new double[] {85.0, 75.0, 95.0});

    // Aluno Maria da Silva
    matricularAlunoEAtribuirNotas(a2, t1, new Disciplina[] {d1, d2, d3}, new double[] {50.0, 20.0, 40.0});
    matricularAlunoEAtribuirNotas(a2, t3, new Disciplina[] {d2, d5, d1}, new double[] {70.0, 80.0, 60.0});

    // Aluno Carlos da Silva
    matricularAlunoEAtribuirNotas(a3, t1, new Disciplina[] {d1, d2, d3}, new double[] {80.0, 70.0, 80.0});
    matricularAlunoEAtribuirNotas(a3, t3, new Disciplina[] {d2, d5, d1}, new double[] {50.0, 40.0, 20.0});

    // Aluno Ana Paula
    matricularAlunoEAtribuirNotas(a4, t2, new Disciplina[] {d3, d4, d5}, new double[] {70.0, 70.0, 70.0});
    matricularAlunoEAtribuirNotas(a4, t3, new Disciplina[] {d2, d5, d1}, new double[] {70.0, 50.0, 20.0});

    // Aluno Pedro Henrique
    matricularAlunoEAtribuirNotas(a5, t2, new Disciplina[] {d3, d4, d5}, new double[] {60.0, 80.0, 10.0});
    matricularAlunoEAtribuirNotas(a5, t3, new Disciplina[] {d2, d5, d1}, new double[] {90.0, 90.0, 90.0});
	calcularMediaNotas();
}

	public int getProxCodigoTurma() {
		return cTurma.getProxCodigo();
	}

	public boolean verificarCodigoAluno(int Aaux) {
		return cAluno.verificarCodigo(Aaux);
	}

	public boolean verificarNomeAluno(String nome) {
		return cAluno.verificarNome(nome);
	}

	public int getProxCodigoAluno() {
		return cAluno.getProxCodigo();
	}

	public Aluno getAluno(int codigo) {
		return cAluno.getAluno(codigo);
	}

	public int getProxCodigoDisc() {
		return cDisciplina.getProxCodigo();
	}

	public boolean cadastrarDisciplina(Disciplina d) {
		return cDisciplina.add(d);
	}

	public String cortarNome2(String nome, int limite) {
		if (nome.length() <= limite) {
			return nome;
		}
		return nome.substring(0, limite - 3) + "...";
	}

	public Disciplina[] listagemDisc() {
		return cDisciplina.listar();
	}

	public boolean removerDisciplina(int codigo) {

		if (cDisciplina.remover(codigo) && cTurma.removerDisciplina(codigo) && cNota.removerNotaDelDiciplina(codigo))
			return true;
		return false;
	}

	public boolean removerDisciplina2(Turma turma, int codigo) {
		if (cTurma.removerDisciplina(codigo) && cNota.removerNotaDelDisciplinaDeTurma(turma, codigo))
			return true;
		return false;
	}

	public Matricula[] listarMatriculasTurma(int codigoTurma) {
		return cTurma.listarMatriculasTurma(codigoTurma);
	}

	public Matricula[] listarMatricula() {
		return cMatricula.listar();
	}

	public Turma[] listarTurmas() {
		return cTurma.listar();
	}

	public boolean matricularAluno(int codigoAluno, int codigoTurma) {

		if (cMatricula.add(cTurma.matricularAluno(cAluno.getAluno(codigoAluno), codigoTurma)) && cNota
				.adicionarNota3(cTurma.listarDisciplinas(codigoTurma), cTurma.getMatricula(codigoTurma, codigoAluno))) {
			return true;
		}
		return false;
	}

	public boolean cadastrarTurma(Turma turma) {
		return cTurma.add(turma);
	}

	 public boolean removerTurma(int codigo) { // AQUI: o método agora recebe o CÓDIGO da turma, não o objeto
        Turma turma = null;
        for (Turma t : cTurma.listar()) {
            if (t != null && t.getCodTurma() == codigo) {
                turma = t;
                break;
            }
        }

        if (turma == null) {
            return false;
        }

        boolean aux1 = cTurma.removerTurma(turma.getCodTurma());
        boolean aux2 = cNota.removerNotaDelturma(turma);
        boolean aux3 = cMatricula.removerMatriculaTurma(turma);

        return aux1 && aux2 && aux3;
    }

	public boolean alterarTurma(int codigoTurma, Turma turma) {
		return cTurma.alterar(codigoTurma, turma);
	}

	public boolean adicionarDisciplina(int codTurma, Disciplina disciplina) {
		if (cNota.adicionarNotaDisciplina(cTurma.listarMatriculas(), disciplina)) {
			return cTurma.adicionarDisciplina(codTurma, disciplina);
		}
		return false;
	}

	public boolean alterarDisciplinaNome(int codigo, String novoNome) {
		if ( cTurma.alterarDisciplina(codigo, cDisciplina.alterarNome(codigo, novoNome)))
			return true;
		return false;
	}



	public boolean removerMatricula(Matricula matricula, int codigo, int codTurma) {
		if (cNota.removerNotaDelMatricula(matricula) && cTurma.removerMatricula(codigo, codTurma)
				&& cMatricula.remover(matricula))
			return true;
		return false;
	}

	public boolean alterarNotasAluno(Notas notaAlterada) {
		return cNota.alterarNota(notaAlterada);
	}

	public Notas[] listarNotas(Matricula matricula) {
		Notas[] todasNotas = cNota.getNotas();
		int count = 0;
		for (Notas n : todasNotas) {
			if (n != null && n.getMatricula().equals(matricula)) {
				count++;
			}
		}
		Notas[] notasAluno = new Notas[count];
		int index = 0;
		for (Notas n : todasNotas) {
			if (n != null && n.getMatricula().equals(matricula)) {
				notasAluno[index++] = n;
			}
		}
		return notasAluno;
	}

public void calcularMediaNotas() {
    Notas[] notas = cNota.getNotas();
    Aluno[] alunos = cAluno.getListaAluno();

    for (Aluno aluno : alunos) {
        if (aluno != null) {
            double soma = 0;
            int count = 0;

            for (Notas nota : notas) {
                if (nota != null
                        && nota.getMatricula() != null
                        && nota.getMatricula().getAluno() != null
                        && nota.getMatricula().getAluno().getCodigo() == aluno.getCodigo()) {
                    soma += nota.getValor();
                    count++;
                }
            }

            if (count > 0) {
                double media = soma / count;
                aluno.setMedia(media);
            } else {
                aluno.setMedia(0);
               
            }
        }
    }

    cAluno.setListaAluno(alunos);
    cMatricula.AlterarMatriculas(alunos);
    cTurma.AlterarMatriculas(cMatricula.listar());
}
private void matricularAlunoEAtribuirNotas(Aluno aluno, Turma turma, Disciplina[] disciplinas, double[] notas) {
    matricularAluno(aluno.getCodigo(), turma.getCodTurma());
    Matricula matricula = cTurma.getMatricula(turma.getCodTurma(), aluno.getCodigo());
    
    // Verifica se a matrícula e as listas de notas/disciplinas são válidas
    if (matricula == null || disciplinas.length != notas.length) {
        System.out.println("Erro na atribuição de notas para o aluno " + aluno.getNmaluno());
        return;
    }

    // Atribui as notas
    for (int i = 0; i < disciplinas.length; i++) {
        cNota.alterarNota2(disciplinas[i], matricula, notas[i]);
    }
}


	public Turma[] getTurmasDoAluno(int codigo) {
		return cTurma.getTurmasDoAluno(codigo);
	}

	public Matricula[] listarMatriculas() {
		return cMatricula.listar();
	}

    public Aluno getInstanceAluno(int cd, String nome, String endereco, String nomeM, String cpf) {
        return cAluno.getInstance(cd, nome, cpf);
    }

	public Aluno[] listarAlunosNota() {

		return cAluno.listarAlunosNota();
	}

	public Disciplina getInstanceDisc(int cd, String nome, String nomeProfessor, String sigla) {
		return cDisciplina.getInstance(cd, nome, nomeProfessor, sigla);
	}

    public boolean alterarDisciplinaProfessor(int codigo, String novoProfessor) {
       if ( cTurma.alterarDisciplina(codigo, cDisciplina.alterarProfessor(codigo, novoProfessor)))
			return true;
		return false;
    }

    public Turma getInstanceTurma(int proxCodigoTurma, String nomeTurma, int anoTurma, int numVagas, String sigla) {
        return cTurma.getInstance(proxCodigoTurma, nomeTurma, anoTurma, numVagas, sigla);
    }

	public Notas getInstanceNota(Disciplina disciplina, Matricula matricula, double novaNota) {
		return cNota.getInstance(disciplina, matricula, novaNota);
	}

}
