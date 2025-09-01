package rian_mario.UI;

import java.util.Scanner;

import rian_mario.Controle.Sistema;

public class UIDisciplina {

    // import java.util.Random;

    // private Random rnd;
    private Sistema sis;
    private Scanner scn;

    public UIDisciplina() {
        // rnd = new Random();
        sis = Sistema.getInstance();
        scn = new Scanner(System.in);
    }

    // responsavel por cadastrar/inserir aluno
    public void cadastrarDisciplina() {
        int cd = sis.getProxCodigoDisc();
        // int cd = Sistema.getInstance().getProxCodigo();
        System.out.println("Codigo: " + cd);
        System.out.println("Qual é o nome da Disciplina que deseja cadastrar? ");
        String nome = scn.next();
        if (nome.equals(""))
            System.out.println("Nome invalido");
        else {
            System.out.println(
                    "Qual é o nome do professor da Disciplina que deseja cadastrar? ");
            String nomeProfessor = scn.next();
            while (nomeProfessor.equals("")) {
                System.out.println("Nome do professor inválido");
                System.out.println(
                        "Qual é o nome do professor da Disciplina que deseja cadastrar? ");
                nomeProfessor = scn.next();
            }

            System.out.println(
                    "Qual é a sigla da Disciplina que deseja cadastrar? ");
            System.out.println("ex: calculo 1 => Cal1(no max 4 caracteres)");
            String sigla = scn.next();
            while (sigla.length() > 4) {
                System.out.println("tamanho de sigla invalido");
                System.out.println(
                        "Qual é a sigla da Disciplina que deseja cadastrar? ");
                System.out.println("ex: calculo 1 => Cal1(no max 4 caracteres)");
                sigla = scn.next();
            }

            if (sis.cadastrarDisciplina(sis.getInstanceDisc(cd, nome, nomeProfessor, sigla))) {
                System.out.println("Cadastro de Disciplina realizado com sucesso");
            } else {
                System.out.println("Falha no cadastro de Disciplina");
            }

        }
    }

    public void listarDisciplinas(int tmndespa) {
        String[] cabe = { "CÓDIGO", "DISCIPLINA", "PROFESSOR", "SIGLA" };

        int codigoWidth = cabe[0].length() + tmndespa;
        int disciplinaWidth = cabe[1].length() + tmndespa + 10;
        int professorWidth = cabe[2].length() + tmndespa;
        int siglaWidth = cabe[3].length() + tmndespa;

        // Cabeçalho
        System.out.printf(
                "%-" + codigoWidth + "s%-" + disciplinaWidth + "s%-" + professorWidth + "s%-" + siglaWidth + "s\n",
                cabe[0], cabe[1], cabe[2], cabe[3]);

        // Linhas
        for (int i = 0; i < sis.listagemDisc().length; i++) {
            if (sis.listagemDisc()[i] != null) {
                // Cortar disciplina e professor para não passar do limite
                sis.listagemDisc()[i].setNmdisc(
                        sis.cortarNome2(sis.listagemDisc()[i].getNmdisc(), disciplinaWidth));
                sis.listagemDisc()[i].setNmprof(
                        sis.cortarNome2(sis.listagemDisc()[i].getNmprof(), professorWidth));

                System.out.printf(
                        "%-" + codigoWidth + "d%-" + disciplinaWidth + "s%-" + professorWidth + "s%-" + siglaWidth
                                + "s\n",
                        sis.listagemDisc()[i].getCddisc(),
                        sis.listagemDisc()[i].getNmdisc(),
                        sis.listagemDisc()[i].getNmprof(),
                        sis.listagemDisc()[i].getSigla());
            }
        }
    }

    public void removerDisciplina(int tmndespa) {
        listarDisciplinas(tmndespa);
        System.out.println("Digite o código da disciplina que deseja remover:");
        int codigo = scn.nextInt();
        if (sis.removerDisciplina(codigo)) {
            System.out.println("Disciplina removida com sucesso.");
        } else {
            System.out.println("Falha ao remover disciplina.");
        }
    }

    public void alterarDisciplina(int tmndespa) {
        int aux = -1;
        System.out.println("com base na lista abaixo escreva qual o codigo da disciplina que deseja fazer a alteração");
        listarDisciplinas(tmndespa);
        int codigo = scn.nextInt();

        for (int i = 0; i < sis.listagemDisc().length; i++) {
            if (sis.listagemDisc()[i] != null && sis.listagemDisc()[i].getCddisc() == codigo) {

                aux = i;
                break;
            }
        }
        if (aux != -1) {
            System.out.println("Disciplina encontrada:");
            System.out.println(sis.listagemDisc()[aux]);
        } else {
            System.out.println("Disciplina não encontrada.");
            return;
        }

        System.out.println("o que voce deseja alterar?");

        int opcao;

        do {
            opcao = 0;
            while (opcao <= 0 || opcao > 4) {
                System.out.println("Digite 1 para alterar nome da disciplina");
                System.out.println("Digite 2 para alterar nome do professor");
                System.out.println("Digite 3 para sair");
                opcao = scn.nextInt();
            }

            switch (opcao) {
                case 1:
                    System.out.println("Qual é o novo nome da disciplina?");
                    // Adicione scn.nextLine() aqui para evitar problemas de InputMismatchException
                    scn.nextLine();
                    String novoNome = scn.nextLine();
                    alterarNomeDisciplina(codigo, novoNome);
                    break;
                case 2:
                    System.out.println("Qual é o novo nome do professor?");
                    // Adicione scn.nextLine() aqui para evitar problemas de InputMismatchException
                    scn.nextLine();
                    String novoProfessor = scn.nextLine();
                    alterarNomeProfessor(codigo, novoProfessor);
                    break;
                case 0:
                    System.out.println("Você saiu das alterações.");
                    break;
            }
        } while (opcao != 3);

    }

    private boolean alterarNomeDisciplina(int codigo, String novoNome) {

        return sis.alterarDisciplinaNome(codigo, novoNome);

    }

    private boolean alterarNomeProfessor(int codigo, String novoProfessor) {

        return sis.alterarDisciplinaProfessor(codigo, novoProfessor);

    }

}
