package rian_mario.Dados;

import rian_mario.Controle.Aluno;
import rian_mario.Controle.Disciplina;
import rian_mario.Controle.Matricula;
import rian_mario.Controle.Turma;

public class RepositorioTurma {

    private static RepositorioTurma instancia;
    private Turma[] turmas = new Turma[3];
    private int quantidadeTurmas = 0;
    private int proxCodigo = 0;

    private RepositorioTurma() {}

    public static RepositorioTurma getInstance() {
        if (instancia == null) {
            instancia = new RepositorioTurma();
        }
        return instancia;
    }

    public boolean add(Turma turma) {
        if (turma == null) return false;

        if (quantidadeTurmas == turmas.length) {
            aumentarVetorTurma();
        }
        turmas[quantidadeTurmas++] = turma;
        return true;
    }

    public Turma[] listar() {
        Turma[] copia = new Turma[quantidadeTurmas];
        for (int i = 0; i < quantidadeTurmas; i++) {
            copia[i] = new Turma(turmas[i]); // usa construtor de cópia
        }
        return copia;
    }

    public boolean removerTurma(int codigo) {
        for (int i = 0; i < quantidadeTurmas; i++) {
            if (turmas[i] != null && turmas[i].getCodTurma() == codigo) {
                for (int j = i; j < quantidadeTurmas - 1; j++) {
                    turmas[j] = turmas[j + 1];
                }
                turmas[--quantidadeTurmas] = null;
                return true;
            }
        }
        return false;
    }

    public boolean removerDisciplina(int codigo) {
        for (int i = 0; i < quantidadeTurmas; i++) {
            if (turmas[i] != null && turmas[i].removerDisciplina(codigo)) {
                return true;
            }
        }
        return false;
    }

    public Matricula matricularAluno(Aluno a, int codigoTurma) {
        for (int i = 0; i < quantidadeTurmas; i++) {
            if (turmas[i] != null && turmas[i].getCodTurma() == codigoTurma) {
                turmas[i].addMatriculas(a);
                return turmas[i].getMatricula(a.getCodigo());
            }
        }
        return null;
    }

    public Matricula[] listarMatriculas() {
        int total = 0;
        for (int i = 0; i < quantidadeTurmas; i++) {
            if (turmas[i] != null) {
                total += turmas[i].getQttPessoas();
            }
        }
        Matricula[] todas = new Matricula[total];
        int idx = 0;
        for (int i = 0; i < quantidadeTurmas; i++) {
            if (turmas[i] != null) {
                Matricula[] ms = turmas[i].copiaMatriculas();
                for (Matricula m : ms) {
                    if (m != null) todas[idx++] = m;
                }
            }
        }
        return todas;
    }

    public boolean alterar(int codigoTurma, Turma turma) {
        for (int i = 0; i < quantidadeTurmas; i++) {
            if (turmas[i] != null && turmas[i].getCodTurma() == codigoTurma) {
                turmas[i] = turma;
                return true;
            }
        }
        return false;
    }

    public boolean adicionarDisciplina(int codTurma, Disciplina disciplina) {
        for (int i = 0; i < quantidadeTurmas; i++) {
            Turma t = turmas[i];
            if (t != null && t.getCodTurma() == codTurma) {
                for (Disciplina d : t.copiaDiscs()) {
                    if (d != null && d.getcodigoDisc() == disciplina.getcodigoDisc()) {
                        return false; // já existe
                    }
                }
                return t.addDisciplinas(disciplina);
            }
        }
        return false;
    }

    public boolean alterarDisciplina(int codigo, Disciplina disciplina) {
        for (int i = 0; i < quantidadeTurmas; i++) {
            Turma t = turmas[i];
            if (t != null) {
                for (Disciplina d : t.copiaDiscs()) {
                    if (d != null && d.getcodigoDisc() == codigo) {
                        return t.alterarDisciplina(codigo, disciplina);
                    }
                }
            }
        }
        return false;
    }

    public boolean removerMatricula(int codigo, int codTurma) {
        for (int i = 0; i < quantidadeTurmas; i++) {
            if (turmas[i] != null && turmas[i].getCodTurma() == codTurma) {
                return turmas[i].removerMatricula(codigo);
            }
        }
        return false;
    }

    public Disciplina[] listarDisciplinas(int codigoTurma) {
        for (int i = 0; i < quantidadeTurmas; i++) {
            if (turmas[i] != null && turmas[i].getCodTurma() == codigoTurma) {
                return turmas[i].copiaDiscs();
            }
        }
        return new Disciplina[0];
    }

    public Matricula getMatricula(int codigoTurma, int codigoAluno) {
        for (int i = 0; i < quantidadeTurmas; i++) {
            if (turmas[i] != null && turmas[i].getCodTurma() == codigoTurma) {
                return turmas[i].getMatricula(codigoAluno);
            }
        }
        return null;
    }

    public Turma[] getTurmasDoAluno(int codigo) {
        Turma[] aux = new Turma[quantidadeTurmas];
        int count = 0;
        for (int i = 0; i < quantidadeTurmas; i++) {
            if (turmas[i] != null) {
                for (Matricula m : turmas[i].copiaMatriculas()) {
                    if (m != null && m.getAluno().getCodigo() == codigo) {
                        aux[count++] = turmas[i];
                        break;
                    }
                }
            }
        }
        Turma[] resultado = new Turma[count];
        System.arraycopy(aux, 0, resultado, 0, count);
        return resultado;
    }

    public void AlterarMatriculas(Matricula[] listar) {
        for (Matricula m : listar) {
            if (m == null) continue;
            for (int i = 0; i < quantidadeTurmas; i++) {
                if (turmas[i] != null && turmas[i].getCodTurma() == m.getTurma().getCodTurma()) {
                    Matricula[] ms = turmas[i].copiaMatriculas();
                    for (int k = 0; k < ms.length; k++) {
                        if (ms[k] != null && ms[k].getAluno().getCodigo() == m.getAluno().getCodigo()) {
                            turmas[i].alterarMatricula(m, k);
                        }
                    }
                }
            }
        }
    }

    public boolean alterarMatricula(Matricula alterarAluno) {
        for (int i = 0; i < quantidadeTurmas; i++) {
            if (turmas[i] != null && turmas[i].getCodTurma() == alterarAluno.getTurma().getCodTurma()) {
                Matricula[] ms = turmas[i].copiaMatriculas();
                for (int j = 0; j < ms.length; j++) {
                    if (ms[j] != null && ms[j].getAluno().getCodigo() == alterarAluno.getAluno().getCodigo()) {
                        turmas[i].alterarMatricula(alterarAluno, j);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Matricula[] listarMatriculasTurma(int codTurma) {
        for (int i = 0; i < quantidadeTurmas; i++) {
            if (turmas[i] != null && turmas[i].getCodTurma() == codTurma) {
                return turmas[i].copiaMatriculas();
            }
        }
        return new Matricula[0];
    }

    private void aumentarVetorTurma() {
        Turma[] aux = new Turma[turmas.length * 2];
        System.arraycopy(turmas, 0, aux, 0, turmas.length);
        turmas = aux;
    }

    public int proximoCodigo() {
        return proxCodigo++;
    }
}
