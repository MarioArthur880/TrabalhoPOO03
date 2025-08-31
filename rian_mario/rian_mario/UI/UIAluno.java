package rian_mario.UI;

//import java.util.Random;
import java.util.Scanner;

import rian_mario.Controle.Sistema;



public class UIAluno {
	// private Random rnd;
	private Sistema sis;
	private Scanner scn;

	public UIAluno() {
		// rnd = new Random();
		sis = Sistema.getInstance();
		scn = new Scanner(System.in);
	}

	// responsavel por cadastrar/inserir aluno
	public void cadastrarAluno() {
		int cd = sis.getProxCodigoAluno();
		// int cd = Sistema.getInstance().getProxCodigo();
		System.out.println("Codigo: " + cd);
		System.out.println("Qual é o nome do aluno que deseja cadastrar? ");
		String nome = scn.next();
		while (sis.verificarNomeAluno(nome) == false) {
			System.out.println("Nome ja existente");
			nome = scn.next();
		}
		System.out.println(
				"Qual é o endereço do aluno que deseja cadastrar? ");
		String endereco = scn.next();
		System.out.println(
				"Qual é o nome da mãe aluno que deseja cadastrar? ");
		String nomeM = scn.next();
		System.out.println("Qual é o cpf do aluno que deseja cadastrar? ");
		String cpf = scn.next();

		if (sis.cadastrarAluno(sis.getInstanceAluno(cd, nome, endereco, nomeM, cpf))) {
			System.out.println("Cadastro de aluno realizado com sucesso");
		} else {
			System.out.println("Falha no cadastro do aluno");
		}

	}

	public void alterarAluno() {
		if (sis.listarAlunos()[0] == null) {
			System.out.println("Nenhum aluno cadastrado");
			return;
		}
		System.out.println("com base na lista abaixo escreva qual o numero do aluno que deseja fazer a alteração");
		int max = alterarAlunoListar() - 1;
		int numero = scn.nextInt();

		while (numero > max || numero < 0) {
			System.out.println("valor incorreto ");
			System.out.println("com base na lista abaixo escreva qual o numero do aluno que deseja fazer a alteração");
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
					System.out.println("Voce saiu das alteracoes");
					break;

				default:
					System.out.println("Você da opçao alterar");
					break;
			}
		} while (opcao != 5);
		System.out.println("Você saiu do programa");
	}

	public int menu() {
		System.out.println("O que voce deseja alterar?");
		System.out.println("1: Nome");
		System.out.println("2: CPF");
		System.out.println("3: Sair");
		int num = scn.nextInt();
		return num;
	}

	public void alterarNome(int i) {
		if (sis.listarAlunos()[i] != null) {
			System.out.println("O nome atual é " + sis.listarAlunos()[i].getNmaluno());
			System.out.println("Qual nome voce deseja colocar?");
			String nmaluno = scn.next();
			while (sis.verificarNomeAluno(nmaluno) == false) {
				System.out.println("O nome do aluno nao pode ser igual a um existente");
				System.out.println("O nome atual é " + sis.listarAlunos()[i].getNmaluno());
				System.out.println("Qual nome voce deseja colocar?");
				nmaluno = scn.next();
			}

			if (sis.alterarAlunoNome(sis.listarAlunos()[i].getCodigo(), nmaluno)) {
				System.out.println("Alteração realizada com sucesso");
			}

		}

	}


	public void AlterarCpf(int i) {
		if (sis.listarAlunos()[i] != null) {
			System.out.println("O cpf atual é " + sis.listarAlunos()[i].getCpf());
			System.out.println("Qual cpf voce deseja colocar?");
			String cpf = scn.next();
			while (cpf.equals("")) {
				System.out.println("Cpf inválido");
				System.out.println("O cpf atual é " + sis.listarAlunos()[i].getCpf());
				System.out.println("Qual cpf voce deseja colocar?");
				cpf = scn.next();
			}
			if (sis.alterarAlunoCPF(sis.listarAlunos()[i].getCodigo(), cpf)) {
				System.out.println("Alteração realizada com sucesso");
			}
		}
	}

	public int alterarAlunoListar() {

		for (int i = 0; i < sis.listarAlunos().length; i++) {
			if (sis.listarAlunos()[i] != null) {
				System.out.println(i + " : " + sis.listarAlunos()[i].getNmaluno());
			}
		}
		return sis.listarAlunos().length;
	}

	public void listarAlunosNome(int tmndespa) {

		sis.calcularMediaNotas();
		

		System.out.println("Os alunos atualmente cadastrados ordenados por nota são:\n");

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
				String nomeCortado = cortarNome2(sis.listarAlunos()[i].getNmaluno(), nomeWidth - tmndespa);
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
		

		System.out.println("Os alunos atualmente cadastrados ordenados por nota são:\n");

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
				String nomeCortado = cortarNome2(sis.listarAlunosNota()[i].getNmaluno(), nomeWidth - tmndespa);
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
