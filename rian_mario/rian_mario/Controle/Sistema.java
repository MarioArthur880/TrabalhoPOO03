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
        if (instance == null) {
            instance = new Sistema();
        }
        return instance;
    }

    // --- ALUNO ---
    public boolean cadastrarAluno(Aluno a) {
        return controleAluno.add(a);
    }

    public boolean alterarAlunoNome(int i, String nmAluno) {
        if (nmAluno != null) {
            boolean alterouNome = controleAluno.alterarNome(i, nmAluno);
            boolean alterouMatricula = controleTurma.alterarMatricula(
                    controleMatricula.alterarAluno(controleAluno.getAluno(i))
            );
            return alterouNome && alterouMatricula;
        }
        return false;
    }

    public boolean alterarAlunoCPF(int i, String cpf) {
        if (cpf != null) {
            boolean alterouCpf = controleAluno.alterarCpf(i, cpf);
            boolean alterouMatricula = controleTurma.alterarMatricula(
                    controleMatricula.alterarAluno(controleAluno.getAluno(i))
            );
            return alterouCpf && alterouMatricula;
        }
        return false;
    }

    public Aluno[] listarAlunos() {
        return controleAluno.getListaAluno();
    }

    public boolean verificarCodigoAluno(int codigo) {
        return controleAluno.verificarCodigo(codigo);
    }

    public boolean verificarNomeAluno(String nome) {
        return controleAluno.verificarNome(nome);
    }

    public Aluno getAluno(int codigo) {
        return controleAluno.getAluno(codigo);
    }

    public int proximoCodigoAluno() {
        return controleAluno.proximoCodigo();
    }

    // --- DISCIPLINA ---
    public boolean cadastrarDisciplina(Disciplina d) {
        return controleDisciplina.add(d);
    }

    public Disciplina[] listagemDisc() {
        return controleDisciplina.listar();
    }

    public int proximoCodigoDisc() {
        return controleDisciplina.proximoCodigo();
    }

    public boolean removerDisciplina(int codigo) {
        return controleDisciplina.remover(codigo)
                && controleTurma.removerDisciplina(codigo)
                && controleNota.removerNotaDelDiciplina(codigo);
    }

    public boolean removerDisciplina2(Turma turma, int codigo) {
        return controleTurma.removerDisciplina(codigo)
                && controleNota.removerNotaDelDisciplinaDeTurma(turma, codigo);
    }

    public boolean alterarDisciplinaNome(int codigo, String novoNome) {
        return controleTurma.alterarDisciplina(codigo, controleDisciplina.alterarNome(codigo, novoNome));
    }

    public boolean alterarDisciplinaProfessor(int codigo, String novoProfessor) {
        return controleTurma.alterarDisciplina(codigo, controleDisciplina.alterarProfessor(codigo, novoProfessor));
    }

    public Disciplina getInstanceDisc(int cd, String nome, String nomeProfessor, String sigla) {
        return controleDisciplina.getInstance(cd, nome, nomeProfessor, sigla);
    }

    // --- TURMA ---
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
        if (turma == null) return false;

        boolean b1 = controleTurma.removerTurma(turma.getCodTurma());
        boolean b2 = controleNota.removerNotaDelturma(turma);
        boolean b3 = controleMatricula.removerMatriculaTurma(turma);

        return b1 && b2 && b3;
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

    public Turma getInstanceTurma(int proxCodigoTurma, String nomeTurma, int anoTurma, int numVagas, String sigla) {
        return controleTurma.getInstance(proxCodigoTurma, nomeTurma, anoTurma, numVagas, sigla);
    }

    public Turma[] listarTurmas() {
        return controleTurma.listar();
    }

    public Turma[] getTurmasDoAluno(int codigo) {
        return controleTurma.getTurmasDoAluno(codigo);
    }

    public int proximoCodigoTurma() {
        return controleTurma.proximoCodigo();
    }

    // --- MATRÍCULA ---
    public boolean matricularAluno(int codigoAluno, int codigoTurma) {
        Matricula matricula = controleTurma.matricularAluno(controleAluno.getAluno(codigoAluno), codigoTurma);
        if (controleMatricula.add(matricula)) {
            return controleNota.adicionarNota3(controleTurma.listarDisciplinas(codigoTurma), matricula);
        }
        return false;
    }

    public boolean removerMatricula(Matricula matricula, int codigo, int codTurma) {
        return controleNota.removerNotaDelMatricula(matricula)
                && controleTurma.removerMatricula(codigo, codTurma)
                && controleMatricula.remover(matricula);
    }

    public Matricula[] listarMatriculas() {
        return controleMatricula.listar();
    }

    public Matricula[] listarMatriculasTurma(int codigoTurma) {
        return controleTurma.listarMatriculasTurma(codigoTurma);
    }

    // --- NOTAS ---
    public boolean alterarNotasAluno(Notas notaAlterada) {
        return controleNota.alterarNota(notaAlterada);
    }

    public Notas[] listarNotas(Matricula matricula) {
        Notas[] todasNotas = controleNota.getNotas();
        int count = 0;
        for (Notas n : todasNotas) {
            if (n != null && n.getMatricula().equals(matricula)) count++;
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

    public Notas getInstanceNota(Disciplina disciplina, Matricula matricula, double novaNota) {
        return controleNota.getInstance(disciplina, matricula, novaNota);
    }

    public void calcularMediaNotas() {
        Notas[] todasNotas = controleNota.getNotas();
        Aluno[] alunos = controleAluno.getListaAluno();

        for (Aluno aluno : alunos) {
            if (aluno != null) {
                double soma = 0;
                int qtdNotas = 0;
                for (Notas nota : todasNotas) {
                    if (nota != null && nota.getMatricula() != null && nota.getMatricula().getAluno() != null
                            && nota.getMatricula().getAluno().getCodigo() == aluno.getCodigo()) {
                        soma += nota.getValor();
                        qtdNotas++;
                    }
                }
                aluno.setMedia(qtdNotas > 0 ? soma / qtdNotas : 0);
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

    // --- INSTÂNCIAS ---
    public Aluno getInstanceAluno(int cd, String nome, String cpf) {
        return controleAluno.getInstance(cd, nome, cpf);
    }

    public Aluno[] listarAlunosNota() {
        return controleAluno.listarAlunosNota();
    }

    // --- INICIALIZAÇÃO ---
    public void init() {
        // Inicialização de alunos, disciplinas e turmas
        Aluno[] alunos = {
                Aluno.getInstance(proximoCodigoAluno(), "Rian", "12332121103"),
                Aluno.getInstance(proximoCodigoAluno(), "Mario", "74572467123"),
                Aluno.getInstance(proximoCodigoAluno(), "Cadu", "02934567659"),
                Aluno.getInstance(proximoCodigoAluno(), "Vitin", "56478690812"),
                Aluno.getInstance(proximoCodigoAluno(), "Otavio", "64590752246")
        };

        for (Aluno a : alunos) controleAluno.add(a);

        Disciplina[] disciplinas = {
                Disciplina.getInstance(proximoCodigoDisc(), "Banco de dados", "Balbino", "BD"),
                Disciplina.getInstance(proximoCodigoDisc(), "Análise e projeto de sistemas", "Marcia", "APS"),
                Disciplina.getInstance(proximoCodigoDisc(), "Programação orientada a objetos", "Luciano", "POO"),
                Disciplina.getInstance(proximoCodigoDisc(), "Fundamentos da programação", "Maurílio", "FP"),
                Disciplina.getInstance(proximoCodigoDisc(), "Programação para web", "Pantuza", "PW")
        };

        for (Disciplina d : disciplinas) controleDisciplina.add(d);

        Turma t1 = Turma.getInstance(proximoCodigoTurma(), "Turma 1", 2, 10, "T1");
        Turma t2 = Turma.getInstance(proximoCodigoTurma(), "Turma 2", 3, 15, "T2");
        Turma t3 = Turma.getInstance(proximoCodigoTurma(), "Turma 3", 1, 20, "T3");

        Turma[] turmas = {t1, t2, t3};
        for (Turma t : turmas) controleTurma.add(t);

        // Adiciona disciplinas nas turmas
        controleTurma.adicionarDisciplina(t1.getCodTurma(), disciplinas[0]);
        controleTurma.adicionarDisciplina(t1.getCodTurma(), disciplinas[1]);
        controleTurma.adicionarDisciplina(t1.getCodTurma(), disciplinas[2]);

        controleTurma.adicionarDisciplina(t2.getCodTurma(), disciplinas[2]);
        controleTurma.adicionarDisciplina(t2.getCodTurma(), disciplinas[3]);
        controleTurma.adicionarDisciplina(t2.getCodTurma(), disciplinas[4]);

        controleTurma.adicionarDisciplina(t3.getCodTurma(), disciplinas[1]);
        controleTurma.adicionarDisciplina(t3.getCodTurma(), disciplinas[4]);
        controleTurma.adicionarDisciplina(t3.getCodTurma(), disciplinas[0]);

        // Matrículas e notas iniciais
        matricularAlunoEAtribuirNotas(alunos[0], t1, new Disciplina[]{disciplinas[0], disciplinas[1], disciplinas[2]}, new double[]{90, 80, 70});
        matricularAlunoEAtribuirNotas(alunos[0], t2, new Disciplina[]{disciplinas[2], disciplinas[3], disciplinas[4]}, new double[]{85, 75, 95});

        matricularAlunoEAtribuirNotas(alunos[1], t1, new Disciplina[]{disciplinas[0], disciplinas[1], disciplinas[2]}, new double[]{50, 20, 40});
        matricularAlunoEAtribuirNotas(alunos[1], t3, new Disciplina[]{disciplinas[1], disciplinas[4], disciplinas[0]}, new double[]{70, 80, 60});

        matricularAlunoEAtribuirNotas(alunos[2], t1, new Disciplina[]{disciplinas[0], disciplinas[1], disciplinas[2]}, new double[]{80, 70, 80});
        matricularAlunoEAtribuirNotas(alunos[2], t3, new Disciplina[]{disciplinas[1], disciplinas[4], disciplinas[0]}, new double[]{50, 40, 20});

        matricularAlunoEAtribuirNotas(alunos[3], t2, new Disciplina[]{disciplinas[2], disciplinas[3], disciplinas[4]}, new double[]{70, 70, 70});
        matricularAlunoEAtribuirNotas(alunos[3], t3, new Disciplina[]{disciplinas[1], disciplinas[4], disciplinas[0]}, new double[]{70, 50, 20});

        matricularAlunoEAtribuirNotas(alunos[4], t2, new Disciplina[]{disciplinas[2], disciplinas[3], disciplinas[4]}, new double[]{60, 80, 10});
        matricularAlunoEAtribuirNotas(alunos[4], t3, new Disciplina[]{disciplinas[1], disciplinas[4], disciplinas[0]}, new double[]{90, 90, 90});

        calcularMediaNotas();
    }

    // --- UTILITÁRIOS ---
    public String cortarNome2(String nome, int limite) {
        if (nome.length() <= limite) return nome;
        return nome.substring(0, limite - 3) + "...";
    }
}
