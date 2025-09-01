package rian_mario.UI;

import java.util.Scanner;

public class UIPrincipal {
	private Scanner scn;
	private UIAluno uiAluno;
	private UITurma uiTurma;
	private UIDisciplina uiDisciplina;

	public UIPrincipal() {
		scn = new Scanner(System.in);
		uiAluno = new UIAluno();
		uiTurma = new UITurma();
		uiDisciplina = new UIDisciplina();
		
	}

	public void iniciar() {
    int opcao;
    int tmndespa = 14;  
    do {
        opcao = menuPrincipal();
        while (opcao <= 0 || opcao > 21) {
            opcao = menuPrincipal();
        }

        if (opcao == 1) {
            uiDisciplina.cadastrarDisciplina();
        }
        if (opcao == 2) {
            uiDisciplina.removerDisciplina(tmndespa);
        }
        if (opcao == 3) {
            uiDisciplina.listarDisciplinas(tmndespa);
        }
        if (opcao == 4) {
            uiAluno.cadastrarAluno();
        }
        if (opcao == 5) {
            uiAluno.alterarAluno();
        }
        if (opcao == 6) {
            uiAluno.listarAlunosNome(tmndespa);
        }
        if (opcao == 7) {
            uiAluno.listarAlunosNotas(tmndespa);
        }
        if (opcao == 8) {
            uiTurma.matricularAluno();
        }
        if (opcao == 9) {
            uiTurma.listarMatriculas(tmndespa);
        }
        if (opcao == 10) {
            tmndespa = tamnahodespa(tmndespa);
        }
        if (opcao == 11) {
            uiTurma.cadastrarTurma();
        }
        if (opcao == 12) {
            uiTurma.removerTurma();
        }
        if (opcao == 13) {
            uiTurma.listarTurmas(tmndespa);
        }
        if (opcao == 14) {
            uiTurma.alterarTurma();
        }
        if (opcao == 15) {
            uiTurma.adicionarDisciplina();
        }
        if (opcao == 16) {
            uiTurma.removerDisciplina(tmndespa);
        }
        if (opcao == 17) {
            uiTurma.listarDisciplinasDeUmaTurma(tmndespa);
        }
        if (opcao == 18) {
            uiDisciplina.alterarDisciplina(tmndespa);
        }
        if (opcao == 19) {
            uiTurma.removerMatricula(tmndespa);
        }
        if (opcao == 20) {
            uiTurma.alterarNotasAluno(tmndespa);
        }

    } while (opcao != 21);

    System.out.println("Você saiu do programa");
}

private int tamnahodespa(int tmndespa) {
    System.out.println("Qual o novo tamanho de espaçamento?");
    int novoTam = scn.nextInt();
    while (novoTam <= 5) {
        System.out.println("Tamanho inválido. Informe um valor maior que 5.");
        novoTam = scn.nextInt();
    }
    return novoTam;
}

public int menuPrincipal() {
    System.out.println("Selecione uma opção");
    System.out.println("1. Cadastrar disciplina");
    System.out.println("2. Remover disciplina");
    System.out.println("3. Listar disciplinas");
    System.out.println("4. Cadastrar alunos");
    System.out.println("5. Alterar dados de um aluno");
    System.out.println("6. Listar os alunos por ordem de cadastro");
    System.out.println("7. Listar os alunos por notas");
    System.out.println("8. Matricular aluno em disciplinas");
    System.out.println("9. Listar matriculas");
    System.out.println("10. Alterar valor de espaçamento das tabelas");
    System.out.println("11. Cadastrar turma");
    System.out.println("12. Remover turma");
    System.out.println("13. Listar turmas");
    System.out.println("14. Alterar dados de uma turma");
    System.out.println("15. Adicionar disciplina a uma turma");
    System.out.println("16. Remover disciplina de uma turma");
    System.out.println("17. Listar disciplinas de uma turma");
    System.out.println("18. Alterar dados de uma disciplina");
    System.out.println("19. Remover uma matricula de uma turma");
    System.out.println("20. Alterar Notas de um aluno");
    System.out.println("21. Sair do código");

    return scn.nextInt();
}

}
