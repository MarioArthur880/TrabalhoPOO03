package rian_mario.UI;

import java.util.Scanner;

import rian_mario.Controle.Sistema;

public class UIDisciplina {


    private Sistema sis;
    private Scanner scn;

    public UIDisciplina() {

        sis = Sistema.getInstance();
        scn = new Scanner(System.in);
    }

    public void cadastrarDisciplina() {
        int cd = sis.proximoCodigoDisc();
 
        System.out.println("Codigo: " + cd);
        System.out.println("Nome: ");
        String nome = scn.next();
        if (nome.equals(""))
            System.out.println("Nome invalido");
        else {
            System.out.println(
                    "Nome do professor:");
            String nomeProfessor = scn.next();
            while (nomeProfessor.equals("")) {
                System.out.println("Nome do professor inválido");
                System.out.println(
                        "Nome do professor: ");
                nomeProfessor = scn.next();
            }

             System.out.println(
                    "Sigla (max 3 caracteres):");
            String sigla = scn.next();
            while (sigla.length() > 3) {
                System.out.println("tamanho de sigla invalido");
                System.out.println(
                        "Sigla (max 3 caracteres):");
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


        System.out.printf(
                "%-" + codigoWidth + "s%-" + disciplinaWidth + "s%-" + professorWidth + "s%-" + siglaWidth + "s\n",
                cabe[0], cabe[1], cabe[2], cabe[3]);

        for (int i = 0; i < sis.listagemDisc().length; i++) {
            if (sis.listagemDisc()[i] != null) {
          
                sis.listagemDisc()[i].setNmdisc(
                        sis.cortarNome2(sis.listagemDisc()[i].getNmdisc(), disciplinaWidth));
                sis.listagemDisc()[i].setNmprof(
                        sis.cortarNome2(sis.listagemDisc()[i].getNmprof(), professorWidth));

                System.out.printf(
                        "%-" + codigoWidth + "d%-" + disciplinaWidth + "s%-" + professorWidth + "s%-" + siglaWidth
                                + "s\n",
                        sis.listagemDisc()[i].getcodigoDisc(),
                        sis.listagemDisc()[i].getNmdisc(),
                        sis.listagemDisc()[i].getNmprof(),
                        sis.listagemDisc()[i].getSigla());
            }
        }
    }

    public void removerDisciplina(int tmndespa) {
        listarDisciplinas(tmndespa);
        System.out.println("Código da disciplina:");
        int codigo = scn.nextInt();
        if (sis.removerDisciplina(codigo)) {
            System.out.println("Disciplina removida com sucesso.");
        } else {
            System.out.println("Falha ao remover disciplina.");
        }
    }

    public void alterarDisciplina(int tmndespa) {
        int aux = -1;
        System.out.println("Código da disciplina:");
        listarDisciplinas(tmndespa);
        int codigo = scn.nextInt();

        for (int i = 0; i < sis.listagemDisc().length; i++) {
            if (sis.listagemDisc()[i] != null && sis.listagemDisc()[i].getcodigoDisc() == codigo) {

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
                System.out.println("1. Nome da disciplina");
                System.out.println("2. Nome do professor");
                System.out.println("3. Sair");
                opcao = scn.nextInt();
            }

            switch (opcao) {
                case 1:
                    System.out.println("Nome:");
        
                    scn.nextLine();
                    String novoNome = scn.nextLine();
                    alterarNomeDisciplina(codigo, novoNome);
                    break;
                case 2:
                    System.out.println("Nome do professor:");
       
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
