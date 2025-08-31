package rian_mario.Controle;

import java.time.LocalDateTime;

public class Sistema {
    private ControleNota cNota;
    private ControleDisciplina cDisciplina;
    private ControleAluno cAluno;
    private ControleTurma cTurma;
    private ControleMatricula cMatricula;

    private static Sistema instance;
    private int proxIdAluno = 1;
    private int proxIdDisc = 1;
    private int proxAnoTurma = 2024;

    private Sistema() {
        cAluno = new ControleAluno();
        cDisciplina = new ControleDisciplina();
        cTurma = new ControleTurma();
        cMatricula = new ControleMatricula();
        cNota = new ControleNota();
        init();
    }

    // singleton
    public static Sistema getInstance() {
        if (instance == null) {
            instance = new Sistema();
        }
        return instance;
    }

    // aluno
    public boolean cadastrarAluno(Aluno a) {
        return cAluno.add(a);
    }

    public boolean alterarAlunoNome(int idAluno, String novoNome) {
        if (novoNome != null && cAluno.alterarNome(idAluno, novoNome)) {
            Aluno alunoAtualizado = cAluno.getAluno(idAluno);
            if (alunoAtualizado != null) {
                cMatricula.alterarAluno(alunoAtualizado);
            }
            return true;
        }
        return false;
    }

    public boolean alterarAlunoCPF(int idAluno, String novoCpf) {
        if (novoCpf != null && cAluno.alterarCpf(idAluno, novoCpf)) {
            Aluno alunoAtualizado = cAluno.getAluno(idAluno);
            if (alunoAtualizado != null) {
                cMatricula.alterarAluno(alunoAtualizado);
            }
            return true;
        }
        return false;
    }

    public Aluno[] listarAlunos() {
        return cAluno.getListaAluno();
    }

    public void init() {
        // 1. Criação dos alunos
        Aluno a1 = cAluno.getInstance(getProxIdAluno(), "João da Silva", "123456789");
        cAluno.add(a1);
        Aluno a2 = cAluno.getInstance(getProxIdAluno(), "Maria da Silva", "987654321");
        cAluno.add(a2);
        Aluno a3 = cAluno.getInstance(getProxIdAluno(), "Carlos da Silva", "123123123");
        cAluno.add(a3);

        // 2. Criação das disciplinas
        Disciplina d1 = cDisciplina.getInstance(getProxIdDisc(), "Matemática", "Prof. João");
        cDisciplina.add(d1);
        Disciplina d2 = cDisciplina.getInstance(getProxIdDisc(), "Português", "Prof. Maria");
        cDisciplina.add(d2);
        Disciplina d3 = cDisciplina.getInstance(getProxIdDisc(), "História", "Prof. Carlos");
        cDisciplina.add(d3);

        // 3. Criação de matrículas
        Matricula m1 = new Matricula(LocalDateTime.now(), "Turma A", a1);
        cMatricula.add(m1);
        Matricula m2 = new Matricula(LocalDateTime.now(), "Turma A", a2);
        cMatricula.add(m2);
        Matricula m3 = new Matricula(LocalDateTime.now(), "Turma B", a3);
        cMatricula.add(m3);

        // 4. Criação de notas
        cNota.alterarValorNota(d1, m1, 90.0);
        cNota.alterarValorNota(d2, m1, 85.0);
        cNota.alterarValorNota(d1, m2, 75.0);
        cNota.alterarValorNota(d3, m3, 80.0);
    }

    public int getProxIdAluno() {
        return proxIdAluno++;
    }

    public boolean verificarIdAluno(int id) {
        return cAluno.verificarId(id);
    }

    public boolean verificarNomeAluno(String nome) {
        return cAluno.verificarNome(nome);
    }

    public Aluno getAluno(int idAluno) {
        return cAluno.getAluno(idAluno);
    }

    public int getProxIdDisc() {
        return proxIdDisc++;
    }

    public boolean cadastrarDisciplina(Disciplina d) {
        return cDisciplina.add(d);
    }

    public Disciplina[] listagemDisc() {
        return cDisciplina.listar();
    }

    public boolean removerDisciplina(int idDisc) {
        if (cDisciplina.remover(idDisc) && cNota.removerNotaDisciplina(idDisc)) {
            return true;
        }
        return false;
    }

    public Matricula[] listarMatricula() {
        return cMatricula.listar();
    }

    public Turma[] listarTurmas() {
        return cTurma.listar();
    }

    public boolean matricularAluno(int idAluno, String turma) {
        Aluno aluno = cAluno.getAluno(idAluno);
        if (aluno != null) {
            Matricula matricula = new Matricula(LocalDateTime.now(), turma, aluno);
            return cMatricula.add(matricula);
        }
        return false;
    }

    public boolean cadastrarTurma(Turma turma) {
        return cTurma.add(turma);
    }

    public boolean removerTurma(int ano) {
        if (cTurma.removerTurma(ano)) {
            cMatricula.removerMatriculaTurma(String.valueOf(ano));
            return true;
        }
        return false;
    }

    public boolean alterarTurma(int ano, Turma novaTurma) {
        return cTurma.alterar(ano, novaTurma);
    }

    public boolean alterarDisciplinaNome(int idDisc, String novoNome) {
        return cDisciplina.alterarNome(idDisc, novoNome);
    }

    public boolean removerMatricula(int idAluno) {
        if (cNota.removerNotaMatricula(idAluno) && cMatricula.remover(idAluno)) {
            return true;
        }
        return false;
    }

    public boolean alterarNotasAluno(Nota notaAlterada) {
        return cNota.alterarNota(notaAlterada);
    }

    public Nota[] listarNotas(Matricula matricula) {
        Nota[] todasNotas = cNota.getNotas();
        int count = 0;
        
        for (Nota n : todasNotas) {
            if (n != null && n.getMatricula() != null && 
                n.getMatricula().getAluno() != null && matricula.getAluno() != null &&
                n.getMatricula().getAluno().getId() == matricula.getAluno().getId()) {
                count++;
            }
        }
        
        Nota[] notasAluno = new Nota[count];
        int index = 0;
        
        for (Nota n : todasNotas) {
            if (n != null && n.getMatricula() != null && 
                n.getMatricula().getAluno() != null && matricula.getAluno() != null &&
                n.getMatricula().getAluno().getId() == matricula.getAluno().getId()) {
                notasAluno[index++] = n;
            }
        }
        
        return notasAluno;
    }

    public Matricula[] listarMatriculas() {
        return cMatricula.listar();
    }

    public Aluno getInstanceAluno(int id, String nome, String cpf) {
        return cAluno.getInstance(id, nome, cpf);
    }

    public Disciplina getInstanceDisc(int idDisc, String nome, String professor) {
        return cDisciplina.getInstance(idDisc, nome, professor);
    }

    public boolean alterarDisciplinaProfessor(int idDisc, String novoProfessor) {
        return cDisciplina.alterarProfessor(idDisc, novoProfessor);
    }

    public Turma getInstanceTurma(int ano, int vagas, Disciplina[] disciplinas, Matricula[] matriculas) {
        return cTurma.getInstance(ano, vagas, disciplinas, matriculas);
    }

    public Nota getInstanceNota(Disciplina disciplina, Matricula matricula, double valor) {
        return cNota.getInstance(disciplina, matricula, valor);
    }
}