package rian_mario.Controle;




public class Sistema {
	private ControleNotas controleNota;
	private ControleDisciplina controleDisciplina;
	private ControleAluno controleAluno;
	private ControleTurma controleTurma;
	private ControleMatricula controleMatricula;

	private static Sistema instance;

	private Sistema() {
		controleAluno = new ControleAluno();
		controleDisciplina = new ControleDisciplina();
		controleTurma = new ControleTurma();
		controleMatricula = new ControleMatricula();
		controleNota = new ControleNotas();
		init();

	}

	public static Sistema getInstance() {
		if (instance == null)
			instance = new Sistema();

		return instance;
	}

	public boolean cadastrarAluno(Aluno a) {
		return controleAluno.add(a);
	}

	public boolean alterarAlunoNome(int i, String nmAluno) {
		if (nmAluno != null && controleAluno.alterarNome(i, nmAluno)
				&& controleTurma.alterarMatricula(controleMatricula.alterarAluno(controleAluno.getAluno(i))))
			return true;
		else
			return false;
	}

	public boolean alterarAlunoCPF(int i, String cpf) {
		if (cpf != null && controleAluno.alterarCpf(i, cpf)
				&& controleTurma.alterarMatricula(controleMatricula.alterarAluno(controleAluno.getAluno(i))))
			return true;
		else
			return false;
	}


	
	public Aluno[] listarAlunos() {
		return controleAluno.getListaAluno();
	}

public void init() {

    Aluno a1 = Aluno.getInstance(proximoCodigoAluno(), "Rian", "12332121103");
    controleAluno.add(a1);
    Aluno a2 = Aluno.getInstance(proximoCodigoAluno(), "Mario", "74572467123");
    controleAluno.add(a2);
    Aluno a3 = Aluno.getInstance(proximoCodigoAluno(), "Cadu", "02934567659");
    controleAluno.add(a3);
    Aluno a4 = Aluno.getInstance(proximoCodigoAluno(), "Vitin", "56478690812");
    controleAluno.add(a4);
    Aluno a5 = Aluno.getInstance(proximoCodigoAluno(), "Otavio", "64590752246");
    controleAluno.add(a5);

    Disciplina d1 = Disciplina.getInstance(proximoCodigoDisc(), "Banco de dados", "Balbino", "BD");
    controleDisciplina.add(d1);
    Disciplina d2 = Disciplina.getInstance(proximoCodigoDisc(), "Análise e projeto de sistemas", "Marcia", "APS");
    controleDisciplina.add(d2);
    Disciplina d3 = Disciplina.getInstance(proximoCodigoDisc(), "Programação orientada a objetos", "Luciano", "POO");
    controleDisciplina.add(d3);
    Disciplina d4 = Disciplina.getInstance(proximoCodigoDisc(), "Fundamentos da programação", "Maurílio", "FP");
    controleDisciplina.add(d4);
    Disciplina d5 = Disciplina.getInstance(proximoCodigoDisc(), "Programação para web", "Pantuza", "PW");
    controleDisciplina.add(d5);


    Turma t1 = Turma.getInstance(proximoCodigoTurma(), "Turma A", 2, 10, "ta");
    controleTurma.add(t1);
    controleTurma.adicionarDisciplina(t1.getCodTurma(), d1);
    controleTurma.adicionarDisciplina(t1.getCodTurma(), d2);
    controleTurma.adicionarDisciplina(t1.getCodTurma(), d3);

    Turma t2 = Turma.getInstance(proximoCodigoTurma(), "Turma B", 3, 15, "tb");
    controleTurma.add(t2);
    controleTurma.adicionarDisciplina(t2.getCodTurma(), d3);
    controleTurma.adicionarDisciplina(t2.getCodTurma(), d4);
    controleTurma.adicionarDisciplina(t2.getCodTurma(), d5);

    Turma t3 = Turma.getInstance(proximoCodigoTurma(), "Turma C", 1, 20, "tc");
    controleTurma.add(t3);
    controleTurma.adicionarDisciplina(t3.getCodTurma(), d2);
    controleTurma.adicionarDisciplina(t3.getCodTurma(), d5);
    controleTurma.adicionarDisciplina(t3.getCodTurma(), d1);
    

    matricularAlunoEAtribuirNotas(a1, t1, new Disciplina[] {d1, d2, d3}, new double[] {90.0, 80.0, 70.0});
    matricularAlunoEAtribuirNotas(a1, t2, new Disciplina[] {d3, d4, d5}, new double[] {85.0, 75.0, 95.0});

    matricularAlunoEAtribuirNotas(a2, t1, new Disciplina[] {d1, d2, d3}, new double[] {50.0, 20.0, 40.0});
    matricularAlunoEAtribuirNotas(a2, t3, new Disciplina[] {d2, d5, d1}, new double[] {70.0, 80.0, 60.0});


    matricularAlunoEAtribuirNotas(a3, t1, new Disciplina[] {d1, d2, d3}, new double[] {80.0, 70.0, 80.0});
    matricularAlunoEAtribuirNotas(a3, t3, new Disciplina[] {d2, d5, d1}, new double[] {50.0, 40.0, 20.0});

    matricularAlunoEAtribuirNotas(a4, t2, new Disciplina[] {d3, d4, d5}, new double[] {70.0, 70.0, 70.0});
    matricularAlunoEAtribuirNotas(a4, t3, new Disciplina[] {d2, d5, d1}, new double[] {70.0, 50.0, 20.0});

    matricularAlunoEAtribuirNotas(a5, t2, new Disciplina[] {d3, d4, d5}, new double[] {60.0, 80.0, 10.0});
    matricularAlunoEAtribuirNotas(a5, t3, new Disciplina[] {d2, d5, d1}, new double[] {90.0, 90.0, 90.0});
	calcularMediaNotas();
}

	public int proximoCodigoTurma() {
		return controleTurma.proximoCodigo();
	}

	public boolean verificarCodigoAluno(int vari) {
		return controleAluno.verificarCodigo(vari);
	}

	public boolean verificarNomeAluno(String nome) {
		return controleAluno.verificarNome(nome);
	}

	public int proximoCodigoAluno() {
		return controleAluno.proximoCodigo();
	}

	public Aluno getAluno(int codigo) {
		return controleAluno.getAluno(codigo);
	}

	public int proximoCodigoDisc() {
		return controleDisciplina.proximoCodigo();
	}

	public boolean cadastrarDisciplina(Disciplina d) {
		return controleDisciplina.add(d);
	}

	public String cortarNome2(String nome, int limite) {
		if (nome.length() <= limite) {
			return nome;
		}
		return nome.substring(0, limite - 3) + "...";
	}

	public Disciplina[] listagemDisc() {
		return controleDisciplina.listar();
	}

	public boolean removerDisciplina(int codigo) {

		if (controleDisciplina.remover(codigo) && controleTurma.removerDisciplina(codigo) && controleNota.removerNotaDelDiciplina(codigo))
			return true;
		return false;
	}

	public boolean removerDisciplina2(Turma turma, int codigo) {
		if (controleTurma.removerDisciplina(codigo) && controleNota.removerNotaDelDisciplinaDeTurma(turma, codigo))
			return true;
		return false;
	}

	public Matricula[] listarMatriculasTurma(int codigoTurma) {
		return controleTurma.listarMatriculasTurma(codigoTurma);
	}

	public Matricula[] listarMatricula() {
		return controleMatricula.listar();
	}

	public Turma[] listarTurmas() {
		return controleTurma.listar();
	}

	public boolean matricularAluno(int codigoAluno, int codigoTurma) {

		if (controleMatricula.add(controleTurma.matricularAluno(controleAluno.getAluno(codigoAluno), codigoTurma)) && controleNota
				.adicionarNota3(controleTurma.listarDisciplinas(codigoTurma), controleTurma.getMatricula(codigoTurma, codigoAluno))) {
			return true;
		}
		return false;
	}

	public boolean cadastrarTurma(Turma turma) {
		return controleTurma.add(turma);
	}

	 public boolean removerTurma(int codigo) {
        Turma turma = null;
        for (Turma t : controleTurma.listar()) {
            if (t != null && t.getCodTurma() == codigo) {
                turma = t;
                break;
            }
        }

        if (turma == null) {
            return false;
        }

        boolean vari1 = controleTurma.removerTurma(turma.getCodTurma());
        boolean vari2 = controleNota.removerNotaDelturma(turma);
        boolean vari3 = controleMatricula.removerMatriculaTurma(turma);

        return vari1 && vari2 && vari3;
    }

	public boolean alterarTurma(int codigoTurma, Turma turma) {
		return controleTurma.alterar(codigoTurma, turma);
	}

	public boolean adicionarDisciplina(int codTurma, Disciplina disciplina) {
		if (controleNota.adicionarNotaDisciplina(controleTurma.listarMatriculas(), disciplina)) {
			return controleTurma.adicionarDisciplina(codTurma, disciplina);
		}
		return false;
	}

	public boolean alterarDisciplinaNome(int codigo, String novoNome) {
		if ( controleTurma.alterarDisciplina(codigo, controleDisciplina.alterarNome(codigo, novoNome)))
			return true;
		return false;
	}



	public boolean removerMatricula(Matricula matricula, int codigo, int codTurma) {
		if (controleNota.removerNotaDelMatricula(matricula) && controleTurma.removerMatricula(codigo, codTurma)
				&& controleMatricula.remover(matricula))
			return true;
		return false;
	}

	public boolean alterarNotasAluno(Notas notaAlterada) {
		return controleNota.alterarNota(notaAlterada);
	}

	public Notas[] listarNotas(Matricula matricula) {
		Notas[] todasNotas = controleNota.getNotas();
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
    Notas[] notas = controleNota.getNotas();
    Aluno[] alunos = controleAluno.getListaAluno();

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

    controleAluno.setListaAluno(alunos);
    controleMatricula.AlterarMatriculas(alunos);
    controleTurma.AlterarMatriculas(controleMatricula.listar());
}
private void matricularAlunoEAtribuirNotas(Aluno aluno, Turma turma, Disciplina[] disciplinas, double[] notas) {
    matricularAluno(aluno.getCodigo(), turma.getCodTurma());
    Matricula matricula = controleTurma.getMatricula(turma.getCodTurma(), aluno.getCodigo());
    

    if (matricula == null || disciplinas.length != notas.length) {
        System.out.println("Erro na atribuição de notas para o aluno " + aluno.getnmAluno());
        return;
    }

    for (int i = 0; i < disciplinas.length; i++) {
        controleNota.alterarNota2(disciplinas[i], matricula, notas[i]);
    }
}


	public Turma[] getTurmasDoAluno(int codigo) {
		return controleTurma.getTurmasDoAluno(codigo);
	}

	public Matricula[] listarMatriculas() {
		return controleMatricula.listar();
	}

    public Aluno getInstanceAluno(int cd, String nome, String cpf) {
        return controleAluno.getInstance(cd, nome, cpf);
    }

	public Aluno[] listarAlunosNota() {

		return controleAluno.listarAlunosNota();
	}

	public Disciplina getInstanceDisc(int cd, String nome, String nomeProfessor, String sigla) {
		return controleDisciplina.getInstance(cd, nome, nomeProfessor, sigla);
	}

    public boolean alterarDisciplinaProfessor(int codigo, String novoProfessor) {
       if ( controleTurma.alterarDisciplina(codigo, controleDisciplina.alterarProfessor(codigo, novoProfessor)))
			return true;
		return false;
    }

    public Turma getInstanceTurma(int proxCodigoTurma, String nomeTurma, int anoTurma, int numVagas, String sigla) {
        return controleTurma.getInstance(proxCodigoTurma, nomeTurma, anoTurma, numVagas, sigla);
    }

	public Notas getInstanceNota(Disciplina disciplina, Matricula matricula, double novaNota) {
		return controleNota.getInstance(disciplina, matricula, novaNota);
	}

}
