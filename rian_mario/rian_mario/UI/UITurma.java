package rian_mario.UI;

import rian_mario.Controle.Sistema;


import java.util.Scanner;

public class UITurma {
    private Sistema sis;
    private Scanner scn = new Scanner(System.in);
    private final int ESPACAMENTO = 15;

    public UITurma() {
        sis = Sistema.getInstance();
    }

    public void matricularAluno() {
         sis.listarAlunos();
        String[] cabecalhosAluno = { "CÓDIGO", "ALUNO" };
        System.out.printf("%-" + ESPACAMENTO + "s%s\n", cabecalhosAluno[0], cabecalhosAluno[1]);
        for (int i = 0; i < sis.listarAlunos().length; i++) {
            if (sis.listarAlunos()[i] != null)
                System.out.printf("%-" + ESPACAMENTO + "d%s\n", i, sis.listarAlunos()[i].getNmaluno());
        }
        System.out.println("Indique qual aluno deseja matricular:");
        int codigoAluno = scn.nextInt();
        while (codigoAluno < 0 || codigoAluno >= sis.listarAlunos().length || sis.listarAlunos()[codigoAluno] == null) {
            System.out.println("Codigo invalido, tente novamente:");
            codigoAluno = scn.nextInt();
        }
        sis.listarTurmas();
        String[] cabecalhosTurma = { "CÓDIGO", "TURMA" };
        System.out.printf("%-" + ESPACAMENTO + "s%s\n", cabecalhosTurma[0], cabecalhosTurma[1]);
        for (int i = 0; i <  sis.listarTurmas().length; i++) {
            if (sis.listarTurmas()[i] != null) {
                System.out.printf("%-" + ESPACAMENTO + "d%s\n", i, sis.listarTurmas()[i].getNmturma());
            }
        }
        System.out.println("Indique qual turma deseja matricular o aluno:");
        int codigoTurma = scn.nextInt();
        while (codigoTurma < 0 || codigoTurma >= sis.listarTurmas().length || sis.listarTurmas()[codigoTurma] == null) {
            System.out.println("Codigo invalido, tente novamente:");
            codigoTurma = scn.nextInt();
        }

        if (sis.matricularAluno(codigoAluno, codigoTurma)) {
            System.out.println("Aluno matriculado com sucesso!");
        } else {
            System.out.println("Falha ao matricular aluno.");
        }
    }

    public void listarMatriculas(int tmndespa) {
        String[] cabecalhos = { "NÚMERO", "CÓDIGO", "TURMA", "ALUNOS" };

        int numeroWidth = cabecalhos[0].length() + tmndespa;
        int codigoWidth = cabecalhos[1].length() + tmndespa;
        int turmaWidth = cabecalhos[2].length() + tmndespa + 10;
        int alunosWidth = cabecalhos[3].length() + tmndespa + 10;

        System.out.printf("%-" + numeroWidth + "s%-" + codigoWidth + "s%-" + turmaWidth + "s%-" + alunosWidth + "s\n",
                cabecalhos[0], cabecalhos[1], cabecalhos[2], cabecalhos[3]);

        sis.listarMatriculas();
        for (int i = 0; i < sis.listarMatriculas().length; i++) {
            if (sis.listarMatriculas()[i] != null) {
                System.out.printf(" %-" + numeroWidth + "d%-" + codigoWidth + "d%-" + turmaWidth + "s%-" + alunosWidth
                        + "s\n", i, sis.listarMatriculas()[i].getTurma().getCodTurma(),
                        sis.cortarNome2(sis.listarMatriculas()[i].getTurma().getNmturma(), tmndespa),
                        sis.cortarNome2(sis.listarMatriculas()[i].getAluno().getNmaluno(), tmndespa));
            }
        }
    }

    public void cadastrarTurma() {
        scn.nextLine();
        System.out.println("Digite o nome da turma:");
        String nomeTurma = scn.nextLine();
        System.out.println("Digite o ano da turma:");
        int anoTurma = scn.nextInt();
        while (anoTurma < 0) {
            System.out.println("Ano inválido. Digite um ano válido:");
            anoTurma = scn.nextInt();
        }
        System.out.println("digite o numero de vagas da turma:");
        int numVagas = scn.nextInt();
        while (numVagas <= 0) {
            System.out.println("Número de vagas inválido. Digite um número válido:");
            numVagas = scn.nextInt();
        }
        System.out.println("Indique a sigla da turma");
        String sigla = scn.next();
        while (sigla.equals("") && sigla.length() > 4) {
            System.out.println("Sigla invalida");
            System.out.println("Indique a sigla da turma");
            sigla = scn.next();
        }
        scn.nextLine(); 

        if (sis.cadastrarTurma(sis.getInstanceTurma(sis.getProxCodigoTurma(), nomeTurma, anoTurma, numVagas, sigla))) {
            System.out.println("Turma cadastrada com sucesso!");
        } else {
            System.out.println("Falha ao cadastrar turma.");
        }
    }

    public void removerTurma() {
      
        String[] cabecalhos = { "CÓDIGO", "TURMA" };
        System.out.printf("%-" + ESPACAMENTO + "s%s\n", cabecalhos[0], cabecalhos[1]);
        for (int i = 0; i < sis.listarTurmas().length; i++) {
            if (sis.listarTurmas()[i] != null) {
                System.out.printf("%-" + ESPACAMENTO + "d%s\n", sis.listarTurmas()[i].getCodTurma(), sis.listarTurmas()[i].getNmturma());
            }
        }
        System.out.println("Indique o CODIGO da turma que deseja remover:");
        int codigoTurma = scn.nextInt();
        
        boolean codigoExiste = false;
        for (int i = 0; i < sis.listarTurmas().length; i++) {
            if (sis.listarTurmas()[i] != null && sis.listarTurmas()[i].getCodTurma() == codigoTurma) {
                codigoExiste = true;
                break;
            }
        }

        while (!codigoExiste) {
            System.out.println("Codigo invalido, tente novamente:");
            codigoTurma = scn.nextInt();
            codigoExiste = false;
            for (int i = 0; i < sis.listarTurmas().length; i++) {
                if (sis.listarTurmas()[i] != null && sis.listarTurmas()[i].getCodTurma() == codigoTurma) {
                    codigoExiste = true;
                    break;
                }
            }
        }
        
        if (sis.removerTurma(codigoTurma)) {
            System.out.println("Turma removida com sucesso!");
        } else {
            System.out.println("Falha ao remover turma.");
        }
    }

    public void listarTurmas(int tmndespa) {
        String[] cabecalhos = { " CÓDIGO", "TURMA", "VAGAS", "ALUNOS", "DISCIPLINAS" };

        int codigoWidth = cabecalhos[0].length() + tmndespa;
        int turmaWidth = cabecalhos[1].length() + tmndespa + 10;
        int vagasWidth = cabecalhos[2].length() + tmndespa + 10;
        int alunosWidth = cabecalhos[3].length() + tmndespa + 10;
        int disciplinasWidth = cabecalhos[4].length() + tmndespa + 10;

        System.out.printf(
                "%-" + codigoWidth + "s%-" + turmaWidth + "s%-" + vagasWidth + "s%-" + alunosWidth + "s%-"
                        + disciplinasWidth + "s\n",
                cabecalhos[0], cabecalhos[1], cabecalhos[2], cabecalhos[3], cabecalhos[4]);

        for (int i = 0; i < sis.listarTurmas().length; i++) {
            if (sis.listarTurmas()[i] != null) {
                System.out.printf(
                        " %-" + codigoWidth + "d%-" + turmaWidth + "s%-" + vagasWidth + "d%-" + alunosWidth + "d%-"
                                + disciplinasWidth + "d\n",
                        sis.listarTurmas()[i].getCodTurma(), sis.cortarNome2(sis.listarTurmas()[i].getNmturma(), tmndespa), sis.listarTurmas()[i].getVagas(),
                        sis.listarTurmas()[i].getQttPessoas(), sis.listarTurmas()[i].copiaDiscs().length);
            }
        }
    }

    public void alterarTurma() {
      
        String[] cabecalhos = { "CÓDIGO", "TURMA" };
        System.out.printf("%-" + ESPACAMENTO + "s%s\n", cabecalhos[0], cabecalhos[1]);
        for (int i = 0; i < sis.listarTurmas().length; i++) {
            if (sis.listarTurmas()[i] != null) {
                System.out.printf("%-" + ESPACAMENTO + "d%s\n", i, sis.listarTurmas()[i].getNmturma());
            }
        }
        System.out.println("Indique qual turma deseja alterar:");
        int codigoTurma = scn.nextInt();

        while (codigoTurma < 0 || codigoTurma >= sis.listarTurmas().length || sis.listarTurmas()[codigoTurma] == null) {
            System.out.println("Codigo invalido, tente novamente:");
            codigoTurma = scn.nextInt();
        }
        



        System.out.println("Digite o novo nome da turma:");
        sis.listarTurmas()[codigoTurma].setNome(scn.next());
        System.out.println("Digite o novo ano da turma:");
        int novoAno = scn.nextInt();
        while (novoAno < 1) {
            System.out.println("Ano inválido. Digite um ano válido:");
            novoAno = scn.nextInt();
        }
      
        System.out.println("Digite o novo número de vagas da turma:");
        int novasVagas = scn.nextInt();
        while (novasVagas < sis.listarTurmas()[codigoTurma].getQttPessoas()) {
            System.out.println("Número de vagas inválido. Digite um número válido:");
            novasVagas = scn.nextInt();
        }
       
        System.out.println("digite a sigla nova");
        String sigla = scn.next();
        while (sigla.equals("") && sigla.length() > 4) {
            System.out.println("Sigla invalida");
            System.out.println("Indique a sigla da turma");
            sigla = scn.next();
        }
       

        scn.nextLine();

        if (sis.alterarTurma(codigoTurma, sis.getInstanceTurma(codigoTurma, sis.listarTurmas()[codigoTurma].getNmturma(), novoAno, novasVagas, sigla))) {
            System.out.println("Turma alterada com sucesso.");
        } else {
            System.out.println("Não foi possível alterar a turma.");
        }
    }

    public void adicionarDisciplina() {
         sis.listarTurmas();
        String[] cabecalhosTurma = { "CÓDIGO", "TURMA" };
        System.out.printf("%-" + ESPACAMENTO + "s%s\n", cabecalhosTurma[0], cabecalhosTurma[1]);
        for (int i = 0; i < sis.listarTurmas().length; i++) {
            if (sis.listarTurmas()[i] != null)
                System.out.printf("%-" + ESPACAMENTO + "d%s\n", i, sis.listarTurmas()[i].getNmturma());
        }
        System.out.println("Indique qual turma deseja adicionar uma disciplina:");
        int codigoTurma = scn.nextInt();
        while (codigoTurma < 0 || codigoTurma >= sis.listarTurmas().length || sis.listarTurmas()[codigoTurma] == null) {
            System.out.println("Codigo invalido, tente novamente:");
            codigoTurma = scn.nextInt();
        }

         sis.listagemDisc();
        String[] cabecalhosDisciplina = { "CÓDIGO", "DISCIPLINA" };
        System.out.printf("%-" + ESPACAMENTO + "s%s\n", cabecalhosDisciplina[0], cabecalhosDisciplina[1]);
        for (int i = 0; i < sis.listagemDisc().length; i++) {
            if (sis.listagemDisc()[i] != null) {
                System.out.printf("%-" + ESPACAMENTO + "d%s\n", i, sis.listagemDisc()[i].getNmdisc());
            }
        }
        System.out.println("Indique qual disciplina deseja adicionar a turma:");
        int codigoDisciplina = scn.nextInt();
        while (codigoDisciplina < 0 || codigoDisciplina >= sis.listagemDisc().length || sis.listagemDisc()[codigoDisciplina] == null) {
            System.out.println("Codigo invalido, tente novamente:");
            codigoDisciplina = scn.nextInt();
        }

        if (sis.adicionarDisciplina(sis.listarTurmas()[codigoTurma].getCodTurma(), sis.listagemDisc()[codigoDisciplina])) {
            System.out.println("Disciplina adicionada com sucesso.");
        } else {
            System.out.println(
                    "Não foi possível adicionar a disciplina. Verifique se já está adicionada ou se há espaço.");
        }
    }

    public void removerDisciplina(int tmndespa) {
        listarTurmas(tmndespa);
        System.out.println("Digite o numero (nao o codigo) da turma que deseja remover uma disciplina:");
        int codigoTurma = scn.nextInt();
        while (codigoTurma < 0 || codigoTurma >= sis.listarTurmas().length || sis.listarTurmas()[codigoTurma] == null) {
            System.out.println("Codigo invalido, tente novamente:");
            codigoTurma = scn.nextInt();
        }
        sis.listarTurmas()[codigoTurma].copiaDiscs();
        
        String[] cabecalhosDisciplina = { "CÓDIGO", "DISCIPLINA" };
        System.out.printf("%-" + ESPACAMENTO + "s%s\n", cabecalhosDisciplina[0], cabecalhosDisciplina[1]);
        for (int i = 0; i < sis.listarTurmas()[codigoTurma].copiaDiscs().length; i++) {
            if (sis.listarTurmas()[codigoTurma].copiaDiscs()[i] != null) {
                System.out.printf("%-" + ESPACAMENTO + "d%s\n", i, sis.listarTurmas()[codigoTurma].copiaDiscs()[i].getNmdisc());
            }
        }
        
        System.out.println("Indique qual disciplina deseja remover da turma:");
        int codigoDisciplina = scn.nextInt();
        while (codigoDisciplina < 0 || codigoDisciplina >= sis.listarTurmas()[codigoTurma].copiaDiscs().length || sis.listarTurmas()[codigoTurma].copiaDiscs()[codigoDisciplina] == null) {
            System.out.println("Codigo invalido, tente novamente:");
            codigoDisciplina = scn.nextInt();
        }

        if (sis.removerDisciplina2(sis.listarTurmas()[codigoTurma], sis.listarTurmas()[codigoTurma].copiaDiscs()[codigoDisciplina].getCddisc())) {
            System.out.println("Disciplina removida com sucesso.");
        } else {
            System.out.println("Não foi possível remover a disciplina.");
        }
    }

    public void listarDisciplinasDeUmaTurma(int tmndespa) {
        listarTurmas(tmndespa);
        System.out.println("Digite o código da turma que deseja listar as disciplinas:");
        int codigoTurma = scn.nextInt();
        while (codigoTurma < 0 || codigoTurma >= sis.listarTurmas().length || sis.listarTurmas()[codigoTurma] == null) {
            System.out.println("Codigo invalido, tente novamente:");
            codigoTurma = scn.nextInt();
        }
        String[] cabecalhos = { " CÓDIGO", "ETAPA", "DISCIPLINA", "PROFESSOR", "SIGLA" };

        int codigoWidth = cabecalhos[0].length() + tmndespa;
        int etapaWidth = cabecalhos[1].length() + tmndespa;
        int disciplinaWidth = cabecalhos[2].length() + tmndespa + 10;
        int professorWidth = cabecalhos[3].length() + tmndespa;
        int siglaWidth = cabecalhos[4].length() + tmndespa;

        System.out.printf(
                "%-" + codigoWidth + "s%-" + etapaWidth + "s%-" + disciplinaWidth + "s%-" + professorWidth + "s%-"
                        + siglaWidth + "s\n",
                cabecalhos[0], cabecalhos[1], cabecalhos[2], cabecalhos[3], cabecalhos[4]);
     

        for (int i = 0; i < sis.listarTurmas()[codigoTurma].copiaDiscs().length; i++) {
            if (sis.listarTurmas()[codigoTurma].copiaDiscs()[i] != null) {
                System.out.printf(
                        " %-" + codigoWidth + "d%-" + etapaWidth + "d%-" + disciplinaWidth + "s%-" + professorWidth
                                + "s%-" + siglaWidth + "s\n",
                        sis.listarTurmas()[codigoTurma].copiaDiscs()[i].getCddisc(), sis.cortarNome2(sis.listarTurmas()[codigoTurma].copiaDiscs()[i].getNmdisc(), tmndespa), sis.cortarNome2(sis.listarTurmas()[codigoTurma].copiaDiscs()[i].getNmprof(), tmndespa), sis.listarTurmas()[codigoTurma].copiaDiscs()[i].getSigla());
            }
        }
    }

    public void removerMatricula(int tmndespa) {
        listarMatriculas(tmndespa);
        System.out.println("Digite o Numero (nao o codigo) da matrícula que deseja remover:");
        int numeroMatricula = scn.nextInt();
        sis.listarMatricula();
        while (numeroMatricula < 0 || numeroMatricula >= sis.listarMatricula().length || sis.listarMatricula()[numeroMatricula] == null) {
            System.out.println("Codigo invalido, tente novamente:");
            numeroMatricula = scn.nextInt();
        }

        if (sis.removerMatricula(sis.listarMatricula()[numeroMatricula], sis.listarMatricula()[numeroMatricula].getAluno().getCodigo(),
                sis.listarMatricula()[numeroMatricula].getTurma().getCodTurma())) {
            System.out.println("Matrícula removida com sucesso!");
        } else {
            System.out.println("Falha ao remover matrícula.");
        }
    }

    public void alterarNotasAluno(int tmndespa) {
        listarMatriculas(tmndespa);
        System.out.println("Digite o Numero (nao o codigo) da matrícula que deseja alterar as notas:");
        int numeroMatricula = scn.nextInt();
         sis.listarMatricula();

        if (sis.listarMatricula().length == 0) {
            System.out.println("Nenhuma matrícula encontrada.");
            return;
        }

        while (numeroMatricula < 0 || numeroMatricula >= sis.listarMatricula().length || sis.listarMatricula()[numeroMatricula] == null) {
            System.out.println("Codigo invalido, tente novamente:");
            numeroMatricula = scn.nextInt();
        }

         sis.listarNotas(sis.listarMatricula()[numeroMatricula]);
        if (sis.listarNotas(sis.listarMatricula()[numeroMatricula]).length == 0) {
            System.out.println("Não há notas para esta matrícula.");
            return;
        }

        String[] cabecalhos = { "NÚMERO", "DISCIPLINA", "VALOR" };
        int numeroWidth = cabecalhos[0].length() + tmndespa;
        int disciplinaWidth = cabecalhos[1].length() + tmndespa + 10;
        int valorWidth = cabecalhos[2].length() + tmndespa;

        System.out.printf("%-" + numeroWidth + "s%-" + disciplinaWidth + "s%-" + valorWidth + "s\n",
                cabecalhos[0], cabecalhos[1], cabecalhos[2]);

        for (int i = 0; i < sis.listarNotas(sis.listarMatricula()[numeroMatricula]).length; i++) {
            if (sis.listarNotas(sis.listarMatricula()[numeroMatricula])[i] != null) {
                System.out.printf(" %-" + numeroWidth + "d%-" + disciplinaWidth + "s%-" + valorWidth + ".2f\n",
                        (i + 1), sis.cortarNome2(sis.listarNotas(sis.listarMatricula()[numeroMatricula])[i].getDisciplina().getNmdisc(), tmndespa),
                        sis.listarNotas(sis.listarMatricula()[numeroMatricula])[i].getValor());
            }
        }

        System.out.println("Digite a nota a ser alterada:");
        int NumeroNota = scn.nextInt() - 1;
        while (NumeroNota < 0 || NumeroNota >= sis.listarNotas(sis.listarMatricula()[numeroMatricula]).length || sis.listarNotas(sis.listarMatricula()[numeroMatricula])[NumeroNota] == null) {
            System.out.println("Nota inválida, tente novamente:");
            NumeroNota = scn.nextInt() - 1;
        }
        System.out.println("Digite a nova nota(0-100):");
        double novaNota = scn.nextDouble();
        while (novaNota < 0 || novaNota > 100) {
            System.out.println("Nota inválida. Digite uma nota entre 0 e 100:");
            novaNota = scn.nextDouble();
        }
        

        if (sis.alterarNotasAluno(sis.getInstanceNota(sis.listarNotas(sis.listarMatricula()[numeroMatricula])[NumeroNota].getDisciplina(), sis.listarNotas(sis.listarMatricula()[numeroMatricula])[NumeroNota].getMatricula(),
                novaNota))) {
            System.out.println("Notas alteradas com sucesso!");
        } else {
            System.out.println("Falha ao alterar notas.");
        }
    }
}