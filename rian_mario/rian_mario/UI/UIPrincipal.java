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

            switch (opcao) {
                
                case 1 -> uiDisciplina.cadastrarDisciplina();
                case 2 -> uiDisciplina.removerDisciplina(tmndespa);
                case 3 -> uiDisciplina.listarDisciplinas(tmndespa);
                case 4 -> uiDisciplina.alterarDisciplina(tmndespa);

                
                case 5 -> uiAluno.cadastrarAluno();
                case 6 -> uiAluno.alterarAluno();
                case 7 -> uiAluno.listarAlunosNome(tmndespa);
                case 8 -> uiAluno.listarAlunosNotas(tmndespa);
                case 9 -> uiTurma.alterarNotasAluno(tmndespa);

                case 10 -> uiTurma.cadastrarTurma();
                case 11 -> uiTurma.removerTurma();
                case 12 -> uiTurma.listarTurmas(tmndespa);
                case 13 -> uiTurma.alterarTurma();
                case 14 -> uiTurma.adicionarDisciplina();
                case 15 -> uiTurma.removerDisciplina(tmndespa);

                case 16 -> uiTurma.matricularAluno();
                case 17 -> uiTurma.listarMatriculas(tmndespa);
                case 18 -> uiTurma.removerMatricula(tmndespa);

                case 19 -> tmndespa = tamnahodespa(tmndespa);
                case 20 -> System.out.println("Você saiu do programa");

                default -> System.out.println("Opção inválida, tente novamente.");
            }

        } while (opcao != 20);
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
        System.out.println("\n===== MENU PRINCIPAL =====");

        System.out.println("DISCIPLINAS:");
        System.out.println(" 1. Cadastrar disciplina");
        System.out.println(" 2. Remover disciplina");
        System.out.println(" 3. Listar disciplinas");
        System.out.println(" 4. Alterar dados de uma disciplina");

        System.out.println("\nALUNOS:");
        System.out.println(" 5. Cadastrar aluno");
        System.out.println(" 6. Alterar dados de um aluno");
        System.out.println(" 7. Listar alunos por ordem de cadastro");
        System.out.println(" 8. Listar alunos por notas");
        System.out.println(" 9. Alterar notas de um aluno");

        System.out.println("\nTURMAS:");
        System.out.println("10. Cadastrar turma");
        System.out.println("11. Remover turma");
        System.out.println("12. Listar turmas");
        System.out.println("13. Alterar dados de uma turma");
        System.out.println("14. Adicionar disciplina a uma turma");
        System.out.println("15. Remover disciplina de uma turma");

        System.out.println("\nMATRÍCULAS:");
        System.out.println("16. Matricular aluno em disciplinas");
        System.out.println("17. Listar matrículas");
        System.out.println("18. Remover matrícula de uma turma");

        System.out.println("\nOUTROS:");
        System.out.println("19. Alterar valor de espaçamento das tabelas");
        System.out.println("20. Sair");

        System.out.print("Selecione uma opção: ");
        return scn.nextInt();
    }
}
