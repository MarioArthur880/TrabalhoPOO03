package rian_mario.Controle;
import rian_mario.Dados.RepositorioTurma;


public class ControleTurma {
    private RepositorioTurma repoTurma;
    public ControleTurma() {
        repoTurma = RepositorioTurma.getInstance();
    }

    public boolean removerDisciplina(int codigo) {
       return repoTurma.removerDisciplina(codigo);
    }

    public Turma[] listar() {
        return repoTurma.listar();
    }

    public Matricula matricularAluno(Aluno codigoAluno, int codigoTurma) {
        return repoTurma.matricularAluno(codigoAluno, codigoTurma);
    }

    public Matricula[] listarMatriculas() {
        return repoTurma.listarMatriculas();
    }
    public Disciplina[] listarDisciplinas(int codigoTurma) {
        return repoTurma.listarDisciplinas(codigoTurma);
    }

    public boolean add(Turma turma) {
        return repoTurma.add(turma);
    }

    public boolean removerTurma(int codTurma) {
       return repoTurma.removerTurma(codTurma);
    }

    public boolean alterar(int codigoTurma, Turma turma) {
       return repoTurma.alterar(codigoTurma, turma);
    }

    public boolean adicionarDisciplina(int codTurma, Disciplina disciplina) {
        return repoTurma.adicionarDisciplina(codTurma, disciplina);
    }

    public boolean alterarDisciplina(int codigo, Disciplina disciplina) {
        return repoTurma.alterarDisciplina(codigo, disciplina);
    }

    public boolean removerMatricula(int codigo, int codTurma) {
        return repoTurma.removerMatricula(codigo, codTurma);
    }

    public Matricula getMatricula(int codigoTurma, int codigoAluno) {
        return repoTurma.getMatricula(codigoTurma, codigoAluno);
    }

    public Turma[] getTurmasDoAluno(int codigo) {
        return repoTurma.getTurmasDoAluno(codigo);
    }

    public void AlterarMatriculas(Matricula[] listar) {
        repoTurma.AlterarMatriculas(listar);
    }

    public boolean alterarMatricula(Matricula alterarAluno) {
       return repoTurma.alterarMatricula(alterarAluno);
    }

    public int proximoCodigo() {
      return repoTurma.proximoCodigo();
    }

    public Matricula[] listarMatriculasTurma(int codTurma) {
        
        return repoTurma.listarMatriculasTurma(codTurma);
    }

    public Turma getInstance(int proxCodigoTurma, String nomeTurma, int anoTurma, int numVagas, String sigla) {
        return Turma.getInstance(proxCodigoTurma, nomeTurma, anoTurma, numVagas, sigla);
    }

    }

    

    

    
    

