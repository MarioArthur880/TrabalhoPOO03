package rian_mario.UI;


import java.util.Scanner;

import rian_mario.Controle.Sistema;



public class UIAluno {
	private Sistema sis;
	private Scanner scn;

	public UIAluno() {

		sis = Sistema.getInstance();
		scn = new Scanner(System.in);
	}


	public void cadastrarAluno() {
		int cd = sis.proximoCodigoAluno();
		System.out.println("Codigo: " + cd);
		System.out.println("Nome: ");
		String nome = scn.next();
		while (sis.verificarNomeAluno(nome) == false) {
			System.out.println("Já há um aluno cadastrado com esse nome");
			nome = scn.next();
		}
		System.out.println("CPF: ");
		String cpf = scn.next();

		if (sis.cadastrarAluno(sis.getInstanceAluno(cd, nome, cpf))) {
			System.out.println("Cadastro realizado com sucesso");
		} else {
			System.out.println("Falha no cadastro");
		}

	}

	public void alterarAluno() {
		if (sis.listarAlunos()[0] == null) {
			System.out.println("Nenhum aluno cadastrado");
			return;
		}
		System.out.println("Qual o código do aluno que deseja alterar?");
		int max = alterarAlunoListar() - 1;
		int numero = scn.nextInt();

		while (numero > max || numero < 0) {
			System.out.println("valor inválido");
			System.out.println("Qual o código do aluno que deseja alterar?");
			max = alterarAlunoListar() - 1;
			numero = scn.nextInt();

		}

		int opcao;

		do {
			opcao = menu();
			while (opcao <= 0 || opcao > 3) {
				opcao = menu();
			}

			switch (opcao) {
				case 1:

					alterarNome(numero);
					break;

				case 2:
					AlterarCpf(numero);
					break;

				case 3:
					break;

				default:
					System.out.println("Você da opçao alterar");
					break;
			}
		} while (opcao != 3);
	}

	public int menu() {
		System.out.println("O que voce deseja alterar?");
		System.out.println("1. Nome:");
		System.out.println("2. CPF:");
		System.out.println("3. Sair");
		int num = scn.nextInt();
		return num;
	}

	public void alterarNome(int i) {
		if (sis.listarAlunos()[i] != null) {
			System.out.println("Novo nome:");
			String nmAluno = scn.next();
			while (sis.verificarNomeAluno(nmAluno) == false) {
				System.out.println("O nome do aluno nao pode ser igual a um existente");
				System.out.println("Novo nome:");
				nmAluno = scn.next();
			}

			if (sis.alterarAlunoNome(sis.listarAlunos()[i].getCodigo(), nmAluno)) {
				System.out.println("Nome alterado com sucesso");
			}

		}

	}


	public void AlterarCpf(int i) {
		if (sis.listarAlunos()[i] != null) {
			System.out.println("CPF atual: " + sis.listarAlunos()[i].getCpf());
			System.out.println("Novo CPF:");
			String cpf = scn.next();
			while (cpf.equals("")) {
				System.out.println("Cpf inválido");
				System.out.println("CPF atual: " + sis.listarAlunos()[i].getCpf());
				System.out.println("Novo CPF:");
				cpf = scn.next();
			}
			if (sis.alterarAlunoCPF(sis.listarAlunos()[i].getCodigo(), cpf)) {
				System.out.println("CPF alterado com sucesso");
			}
		}
	}

	public int alterarAlunoListar() {

		for (int i = 0; i < sis.listarAlunos().length; i++) {
			if (sis.listarAlunos()[i] != null) {
				System.out.println(i + " : " + sis.listarAlunos()[i].getnmAluno());
			}
		}
		return sis.listarAlunos().length;
	}

	public void listarAlunosNome(int tmndespa) {

		sis.calcularMediaNotas();

		String[] cabecalhos = { "CÓDIGO", "NOME", "QTD TURMAS.", "SIGLAS", "MÉDIA" };
		int codigoWidth = cabecalhos[0].length() + tmndespa;
		int nomeWidth = cabecalhos[1].length() + tmndespa + 10;
		int qtdturmasWidth = cabecalhos[2].length() + tmndespa;
		int siglasWidth = cabecalhos[3].length() + tmndespa + 8;
		int mediaWidth = cabecalhos[4].length() + tmndespa;

		System.out.printf("%-" + codigoWidth + "s%-" + nomeWidth + "s%-" + qtdturmasWidth + "s%-" + siglasWidth + "s%-"
				+ mediaWidth + "s\n", cabecalhos[0], cabecalhos[1], cabecalhos[2], cabecalhos[3], cabecalhos[4]);

		for (int i = 0; i <  sis.listarAlunos().length; i++) {
			if ( sis.listarAlunos()[i] != null) {
				String nomeCortado = cortarNome2(sis.listarAlunos()[i].getnmAluno(), nomeWidth - tmndespa);
				StringBuilder siglas = new StringBuilder();
				int disciplinasAtivas = 0;

				 
				for (int j = 0; j < sis.getTurmasDoAluno(sis.listarAlunos()[i].getCodigo()).length; j++) {
					if (sis.getTurmasDoAluno(sis.listarAlunos()[i].getCodigo())[j] != null) {
						disciplinasAtivas++;
						siglas.append(sis.getTurmasDoAluno(sis.listarAlunos()[i].getCodigo())[j].getSigla());
						if (j < sis.getTurmasDoAluno(sis.listarAlunos()[i].getCodigo()).length - 1 && sis.getTurmasDoAluno(sis.listarAlunos()[i].getCodigo())[j + 1] != null) {
							siglas.append(", ");
						}
					}
				}
				String slg = "...";
				if (siglas != null) {

					slg = cortarNome2(siglas.toString(), 13);
				}

				System.out.printf(
						"%-" + codigoWidth + "d%-" + nomeWidth + "s%-" + qtdturmasWidth + "d%-" + siglasWidth + "s%-"
								+ mediaWidth + ".2f\n",
						sis.listarAlunos()[i].getCodigo(), nomeCortado, disciplinasAtivas, slg, sis.listarAlunos()[i].getMedia());
			}
		}
	}

	public String cortarNome2(String nome, int limite) {
		if (nome.length() <= limite) {
			return nome;
		}
		return nome.substring(0, limite - 3) + "...";
	}

	public void listarAlunosNotas(int tmndespa) {

		sis.calcularMediaNotas();
		


		String[] cabecalhos = { "CÓDIGO", "NOME", "QTD TURMAS.", "SIGLAS", "MÉDIA" };
		int codigoWidth = cabecalhos[0].length() + tmndespa;
		int nomeWidth = cabecalhos[1].length() + tmndespa + 10;
		int qtdturmasWidth = cabecalhos[2].length() + tmndespa;
		int siglasWidth = cabecalhos[3].length() + tmndespa + 8;
		int mediaWidth = cabecalhos[4].length() + tmndespa;

		System.out.printf("%-" + codigoWidth + "s%-" + nomeWidth + "s%-" + qtdturmasWidth + "s%-" + siglasWidth + "s%-"
				+ mediaWidth + "s\n", cabecalhos[0], cabecalhos[1], cabecalhos[2], cabecalhos[3], cabecalhos[4]);

		

		for (int i = 0; i < sis.listarAlunosNota().length; i++) {
			if (sis.listarAlunosNota()[i] != null) {
				String nomeCortado = cortarNome2(sis.listarAlunosNota()[i].getnmAluno(), nomeWidth - tmndespa);
				StringBuilder siglas = new StringBuilder();
				int disciplinasAtivas = 0;

				 sis.getTurmasDoAluno(sis.listarAlunosNota()[i].getCodigo());
				for (int j = 0; j < sis.getTurmasDoAluno(sis.listarAlunosNota()[i].getCodigo()).length; j++) {
					if (sis.getTurmasDoAluno(sis.listarAlunosNota()[i].getCodigo())[j] != null) {
						disciplinasAtivas++;
						siglas.append(sis.getTurmasDoAluno(sis.listarAlunosNota()[i].getCodigo())[j].getSigla());
						if (j < sis.getTurmasDoAluno(sis.listarAlunosNota()[i].getCodigo()).length - 1 && sis.getTurmasDoAluno(sis.listarAlunosNota()[i].getCodigo())[j + 1] != null) {
							siglas.append(", ");
						}
					}
				}
				String slg = "...";
				if (siglas != null) {

					slg = cortarNome2(siglas.toString(), 13);
				}

				System.out.printf(
						"%-" + codigoWidth + "d%-" + nomeWidth + "s%-" + qtdturmasWidth + "d%-" + siglasWidth + "s%-"
								+ mediaWidth + ".2f\n",
						sis.listarAlunosNota()[i].getCodigo(), nomeCortado, disciplinasAtivas, slg, sis.listarAlunosNota()[i].getMedia());
			}
		}
	}

}
