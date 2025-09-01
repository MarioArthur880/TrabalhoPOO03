package rian_mario.UI;

import rian_mario.Controle.Disciplina;
import rian_mario.Controle.Sistema;
import rian_mario.Controle.Turma;

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
                System.out.printf("%-" + ESPACAMENTO + "d%s\n", i, sis.listarAlunos()[i].getnmAluno());
        }
        System.out.println("Codigo do aluno:");
        int codigoAluno = scn.nextInt();
        while (codigoAluno < 0 || codigoAluno >= sis.listarAlunos().length || sis.listarAlunos()[codigoAluno] == null) {
            System.out.println("Codigo invalido, tente novamente:");
            codigoAluno = scn.nextInt();
        }
        sis.listarTurmas();
        String[] cabecalhosTurma = { "CÓDIGO", "TURMA" };
        System.out.printf("%-" + ESPACAMENTO + "s%s\n", cabecalhosTurma[0], cabecalhosTurma[1]);
        for (int i = 0; i < sis.listarTurmas().length; i++) {
            if (sis.listarTurmas()[i] != null) {
                System.out.printf("%-" + ESPACAMENTO + "d%s\n", i, sis.listarTurmas()[i].getNmturma());
            }
        }
        System.out.println("Em qual turma deseja matricular o aluno:");
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
                        sis.cortarNome2(sis.listarMatriculas()[i].getAluno().getnmAluno(), tmndespa));
            }
        }
    }

    public void cadastrarTurma() {
        System.out.println("Nome da turma:");
        String nomeTurma = scn.nextLine();
        System.out.println("Ano da turma:");
        int anoTurma = scn.nextInt();
        while (anoTurma < 0) {
            System.out.println("Ano inválido. Digite um ano válido:");
            anoTurma = scn.nextInt();
        }
        System.out.println("Numero de vagas:");
        int numVagas = scn.nextInt();
        while (numVagas <= 0) {
            System.out.println("Número de vagas inválido. Digite um número válido:");
            numVagas = scn.nextInt();
        }
        System.out.println("Nova sigla");
        String sigla = scn.next();
        while (sigla.equals("") && sigla.length() > 4) {
            System.out.println("Sigla invalida");
            System.out.println("Indique a sigla da turma");
            sigla = scn.next();
        }
        scn.nextLine();

        if (sis.cadastrarTurma(sis.getInstanceTurma(sis.proximoCodigoTurma(), nomeTurma, anoTurma, numVagas, sigla))) {
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
                System.out.printf("%-" + ESPACAMENTO + "d%s\n", sis.listarTurmas()[i].getCodTurma(),
                        sis.listarTurmas()[i].getNmturma());
            }
        }
        System.out.println("Codigo da turma:");
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
                        sis.listarTurmas()[i].getCodTurma(),
                        sis.cortarNome2(sis.listarTurmas()[i].getNmturma(), tmndespa), sis.listarTurmas()[i].getVagas(),
                        sis.listarTurmas()[i].getQttPessoas(), sis.listarTurmas()[i].copiaDiscs().length);
            }
        }
    }

    public void alterarTurma() {
        String[] cabecalhos = { "CÓDIGO", "TURMA" };
        System.out.printf("%-" + ESPACAMENTO + "s%s\n", cabecalhos[0], cabecalhos[1]);

        // pega a referência uma vez (evita chamar sis.listarTurmas() várias vezes)
        Turma[] turmas = sis.listarTurmas();
        for (int i = 0; i < turmas.length; i++) {
            if (turmas[i] != null) {
                System.out.printf("%-" + ESPACAMENTO + "d%s\n", i, turmas[i].getNmturma());
            }
        }

        // lê índice usando nextLine() e parse (evita problemas com nextInt/nextLine)
        int codigoTurma = -1;
        while (true) {
            System.out.println("Codigo da turma:");
            String linha = scn.nextLine().trim();
            try {
                codigoTurma = Integer.parseInt(linha);
                if (codigoTurma < 0 || codigoTurma >= turmas.length || turmas[codigoTurma] == null) {
                    System.out.println("Código inválido, tente novamente.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Informe um número.");
            }
        }

        Turma t = turmas[codigoTurma];

        // nome
        System.out.println("Novo nome da turma:");
        String novoNome = scn.nextLine().trim();
        while (novoNome.isEmpty()) {
            System.out.println("Nome inválido. Digite novamente:");
            novoNome = scn.nextLine().trim();
        }

        // ano
        int novoAno;
        while (true) {
            System.out.println("Novo ano:");
            String ln = scn.nextLine().trim();
            try {
                novoAno = Integer.parseInt(ln);
                if (novoAno < 1) {
                    System.out.println("Informe um ano válido");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Informe um número para o ano.");
            }
        }

        // vagas
        int novasVagas;
        while (true) {
            System.out.println("Nova quantidade de vagas da turma:");
            String ln = scn.nextLine().trim();
            try {
                novasVagas = Integer.parseInt(ln);
                if (novasVagas < t.getQttPessoas()) {
                    System.out
                            .println("Número de vagas inválido. Não pode ser menor que a quantidade atual de pessoas ("
                                    + t.getQttPessoas() + ").");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Informe um número para vagas.");
            }
        }

        // sigla
        System.out.println("Nova sigla (máx 3 caracteres):");
        String sigla = scn.nextLine().trim();
        while (sigla.isEmpty() || sigla.length() > 4) {
            System.out.println("Sigla inválida (1-3 caracteres):");
            sigla = scn.nextLine().trim();
        }

        // tenta atualizar de forma segura e captura exceções para debugar
        try {
            // 1) Preferível: atualizar o objeto existente se a classe Turma tiver setters
            t.setNome(novoNome); // ajuste conforme nome real do setter
            t.setAno(novoAno); // ajuste conforme nome real do setter
            t.setVagas(novasVagas); // ajuste conforme nome real do setter
            t.setSigla(sigla); // ajuste conforme nome real do setter

            // 2) Em alguns projetos você precisa passar uma nova instância para o sistema.
            // Se for o seu caso, use getInstanceTurma (substitua pela assinatura real se
            // diferente):
            // Turma nova = sis.getInstanceTurma(codigoTurma, novoNome, novoAno, novasVagas,
            // sigla);
            // boolean ok = sis.alterarTurma(codigoTurma, nova);

            // aqui chamo alterarTurma com o objeto atualizado (ajuste se sua API for
            // diferente)
            boolean ok = sis.alterarTurma(codigoTurma, t);

            if (ok) {
                System.out.println("Turma alterada com sucesso.");
            } else {
                System.out.println("Não foi possível alterar a turma");
            }
        } catch (Exception e) {
            System.out.println("Erro ao alterar turma: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            e.printStackTrace(); // mostra stacktrace no console para debugar
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
        System.out.println("Em qual turma deseja adicionar uma disciplina:");
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
        System.out.println("EM qual disciplina deseja adicionar a turma:");
        int codigoDisciplina = scn.nextInt();
        while (codigoDisciplina < 0 || codigoDisciplina >= sis.listagemDisc().length
                || sis.listagemDisc()[codigoDisciplina] == null) {
            System.out.println("Codigo invalido, tente novamente:");
            codigoDisciplina = scn.nextInt();
        }

        if (sis.adicionarDisciplina(sis.listarTurmas()[codigoTurma].getCodTurma(),
                sis.listagemDisc()[codigoDisciplina])) {
            System.out.println("Disciplina adicionada com sucesso.");
        } else {
            System.out.println(
                    "Não foi possível adicionar a disciplina. Verifique se já está adicionada ou se há espaço.");
        }
    }

    public void removerDisciplina(int tmndespa) {
        listarTurmas(tmndespa);
        System.out.println("Digite o numero da turma que deseja remover uma disciplina:");
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
                System.out.printf("%-" + ESPACAMENTO + "d%s\n", i,
                        sis.listarTurmas()[codigoTurma].copiaDiscs()[i].getNmdisc());
            }
        }

        System.out.println("EM qual disciplina deseja remover da turma:");
        int codigoDisciplina = scn.nextInt();
        while (codigoDisciplina < 0 || codigoDisciplina >= sis.listarTurmas()[codigoTurma].copiaDiscs().length
                || sis.listarTurmas()[codigoTurma].copiaDiscs()[codigoDisciplina] == null) {
            System.out.println("Codigo invalido, tente novamente:");
            codigoDisciplina = scn.nextInt();
        }

        if (sis.removerDisciplina2(sis.listarTurmas()[codigoTurma],
                sis.listarTurmas()[codigoTurma].copiaDiscs()[codigoDisciplina].getcodigoDisc())) {
            System.out.println("Disciplina removida com sucesso.");
        } else {
            System.out.println("Não foi possível remover a disciplina.");
        }
    }

    public void listarDisciplinasDeUmaTurma(int tmndespa) {
        listarTurmas(tmndespa);

        System.out.println("Código da turma que deseja listar as disciplinas:");
        int codigoTurma = scn.nextInt();
        while (codigoTurma < 0 || codigoTurma >= sis.listarTurmas().length || sis.listarTurmas()[codigoTurma] == null) {
            System.out.println("Código inválido, tente novamente:");
            codigoTurma = scn.nextInt();
        }

        String[] cabecalhos = { "CÓDIGO", "DISCIPLINA", "PROFESSOR", "SIGLA" };

        int codigoWidth = cabecalhos[0].length() + tmndespa;
        int disciplinaWidth = cabecalhos[1].length() + tmndespa + 10;
        int professorWidth = cabecalhos[2].length() + tmndespa;
        int siglaWidth = cabecalhos[3].length() + tmndespa;

        // Cabeçalho
        System.out.printf(
                "%-" + codigoWidth + "s%-" + disciplinaWidth + "s%-" + professorWidth + "s%-" + siglaWidth + "s\n",
                cabecalhos[0], cabecalhos[1], cabecalhos[2], cabecalhos[3]);

        // Linhas
        Disciplina[] disciplinas = sis.listarTurmas()[codigoTurma].copiaDiscs();
        for (int i = 0; i < disciplinas.length; i++) {
            if (disciplinas[i] != null) {
                System.out.printf(
                        "%-" + codigoWidth + "d%-" + disciplinaWidth + "s%-" + professorWidth + "s%-" + siglaWidth
                                + "s\n",
                        disciplinas[i].getcodigoDisc(),
                        sis.cortarNome2(disciplinas[i].getNmdisc(), disciplinaWidth),
                        sis.cortarNome2(disciplinas[i].getNmprof(), professorWidth),
                        disciplinas[i].getSigla());
            }
        }
    }

    public void removerMatricula(int tmndespa) {
        listarMatriculas(tmndespa);
        System.out.println("Digite o Numero da matrícula que deseja remover:");
        int numeroMatricula = scn.nextInt();
        sis.listarMatricula();
        while (numeroMatricula < 0 || numeroMatricula >= sis.listarMatricula().length
                || sis.listarMatricula()[numeroMatricula] == null) {
            System.out.println("Codigo invalido, tente novamente:");
            numeroMatricula = scn.nextInt();
        }

        if (sis.removerMatricula(sis.listarMatricula()[numeroMatricula],
                sis.listarMatricula()[numeroMatricula].getAluno().getCodigo(),
                sis.listarMatricula()[numeroMatricula].getTurma().getCodTurma())) {
            System.out.println("Matrícula removida com sucesso!");
        } else {
            System.out.println("Falha ao remover matrícula.");
        }
    }

    public void alterarNotasAluno(int tmndespa) {
        listarMatriculas(tmndespa);
        System.out.println("Digite o Numero da matrícula que deseja alterar as notas:");
        int numeroMatricula = scn.nextInt();
        sis.listarMatricula();

        if (sis.listarMatricula().length == 0) {
            System.out.println("Nenhuma matrícula encontrada.");
            return;
        }

        while (numeroMatricula < 0 || numeroMatricula >= sis.listarMatricula().length
                || sis.listarMatricula()[numeroMatricula] == null) {
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
                        (i + 1),
                        sis.cortarNome2(
                                sis.listarNotas(sis.listarMatricula()[numeroMatricula])[i].getDisciplina().getNmdisc(),
                                tmndespa),
                        sis.listarNotas(sis.listarMatricula()[numeroMatricula])[i].getValor());
            }
        }

        System.out.println("Digite a nota a ser alterada:");
        int NumeroNota = scn.nextInt() - 1;
        while (NumeroNota < 0 || NumeroNota >= sis.listarNotas(sis.listarMatricula()[numeroMatricula]).length
                || sis.listarNotas(sis.listarMatricula()[numeroMatricula])[NumeroNota] == null) {
            System.out.println("Nota inválida, tente novamente:");
            NumeroNota = scn.nextInt() - 1;
        }
        System.out.println("Digite a nova nota(0-100):");
        double novaNota = scn.nextDouble();
        while (novaNota < 0 || novaNota > 100) {
            System.out.println("Nota inválida. Digite uma nota entre 0 e 100:");
            novaNota = scn.nextDouble();
        }

        if (sis.alterarNotasAluno(
                sis.getInstanceNota(sis.listarNotas(sis.listarMatricula()[numeroMatricula])[NumeroNota].getDisciplina(),
                        sis.listarNotas(sis.listarMatricula()[numeroMatricula])[NumeroNota].getMatricula(),
                        novaNota))) {
            System.out.println("Notas alteradas com sucesso!");
        } else {
            System.out.println("Falha ao alterar notas.");
        }
    }
}