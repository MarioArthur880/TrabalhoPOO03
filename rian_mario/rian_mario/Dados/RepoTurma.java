package rian_mario.Dados;

import rian_mario.Controle.Aluno;
import rian_mario.Controle.Disciplina;
import rian_mario.Controle.Matricula;
import rian_mario.Controle.Turma;

public class RepoTurma {
    // 1. Variáveis
    private static RepositorioTurma instancia;
    private Turma[] turmas = new Turma[3];
    private int qttTurmas = 0;
    private int proxCodigo = 0;

    // 2. Construtor
    private RepositorioTurma() {
       
    }

    // 3. Método getInstance()
    public static RepositorioTurma getInstance() {
        if (instancia == null) {
            instancia = new RepositorioTurma();
        }
        return instancia;
    }

    // 4. Métodos
    public boolean add(Turma turma) {
        if (turma != null) {
            if (turmas.length == qttTurmas) {
                aumentarVetorTurma();
            }
            turmas[qttTurmas] = turma;
            qttTurmas++;
            return true;
        }
        return false;
    }

    public Turma[] listar() {
        Turma[] copia = new Turma[qttTurmas];
        int i = 0;
        for (Turma t : turmas) {
            if (t != null) {
                copia[i++] = new Turma(t);
            }
        }
        return copia;
    }

    public boolean removerTurma(int codigo) {
        if (qttTurmas == 0) {
            return false;
        }
        
        int indexParaRemover = -1;
        for (int i = 0; i < qttTurmas; i++) {
            if (turmas[i] != null && turmas[i].getCodTurma() == codigo) {
                indexParaRemover = i;
                break;
            }
        }

        if (indexParaRemover == -1) {
            return false;
        }

        // Compacta o array movendo os elementos para a esquerda
        for (int i = indexParaRemover; i < qttTurmas - 1; i++) {
            turmas[i] = turmas[i + 1];
        }

        // Limpa o último elemento e diminui o contador
        turmas[qttTurmas - 1] = null;
        qttTurmas--;
        return true;
    }
    
    public boolean removerDisciplina(int codigo) {
        if (qttTurmas == 0)
            return false;
        for (int i = 0; i < qttTurmas; i++) {
            if (turmas[i] != null) {
                if (turmas[i].removerDisciplina(codigo)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Matricula matricularAluno(Aluno a, int codigoTurma) {
        for (int i = 0; i < qttTurmas; i++) {
            if (turmas[i] != null && turmas[i].getCodTurma() == codigoTurma) {
                turmas[i].addMatriculas(a);
                return turmas[i].getMatricula(a.getCodigo());
            }
        }
        return null;
    }
    
    public Matricula[] listarMatriculas() {
        int totalMatriculas = 0;
        for (int i = 0; i < qttTurmas; i++) {
            if (turmas[i] != null) {
                totalMatriculas += turmas[i].getQttPessoas();
            }
        }
        Matricula[] todasMatriculas = new Matricula[totalMatriculas];
        int index = 0;
        for (int i = 0; i < qttTurmas; i++) {
            if (turmas[i] != null) {
                Matricula[] matriculasTurma = turmas[i].copiaMatriculas();
                for (int j = 0; j < matriculasTurma.length; j++) {
                    if (matriculasTurma[j] != null) {
                        todasMatriculas[index++] = matriculasTurma[j];
                    }
                }
            }
        }
        return todasMatriculas;
    }

    public boolean alterar(int codigoTurma, Turma turma) {
        for (int i = 0; i < qttTurmas; i++) {
            if (turmas[i] != null && turmas[i].getCodTurma() == codigoTurma) {
                turmas[i] = turma;
                return true;
            }
        }
        return false;
    }

    public boolean adicionarDisciplina(int codTurma, Disciplina disciplina) {
        for (int i = 0; i < qttTurmas; i++) {
            Turma turma = turmas[i];
            if (turma != null && turma.getCodTurma() == codTurma) {
                Disciplina[] disciplinas = turma.copiaDiscs();
                for (Disciplina d : disciplinas) {
                    if (d != null && d.getCddisc() == disciplina.getCddisc()) {
                        return false;
                    }
                }
                return turma.addDisciplinas(disciplina);
            }
        }
        return false;
    }

    public boolean alterarDisciplina(int codigo, Disciplina disciplina) {
        for (int i = 0; i < qttTurmas; i++) {
            if (turmas[i] != null) {
                for (Disciplina d : turmas[i].copiaDiscs()) {
                    if (d != null && d.getCddisc() == codigo) {
                        return turmas[i].alterarDisciplina(codigo, disciplina);
                    }
                }
            }
        }
        return false;
    }
    
    public boolean removerMatricula(int codigo, int codTurma) {
        for (int i = 0; i < qttTurmas; i++) {
            if (turmas[i] != null && turmas[i].getCodTurma() == codTurma) {
                return turmas[i].removerMatricula(codigo);
            }
        }
        return false;
    }
    
    public Disciplina[] listarDisciplinas(int codigoTurma) {
        for (int i = 0; i < qttTurmas; i++) {
            if (turmas[i] != null && turmas[i].getCodTurma() == codigoTurma) {
                return turmas[i].copiaDiscs();
            }
        }
        return new Disciplina[0];
    }
    
    public Matricula getMatricula(int codigoTurma, int codigoAluno) {
        for (int i = 0; i < qttTurmas; i++) {
            if (turmas[i] != null && turmas[i].getCodTurma() == codigoTurma) {
                return turmas[i].getMatricula(codigoAluno);
            }
        }
        return null;
    }
    
    public Turma[] getTurmasDoAluno(int codigo) {
        Turma[] turmasDoAluno = new Turma[qttTurmas];
        int count = 0;
        for (int i = 0; i < qttTurmas; i++) {
            if (turmas[i] != null) {
                Matricula[] matriculas = turmas[i].copiaMatriculas();
                for (Matricula m : matriculas) {
                    if (m != null && m.getAluno().getCodigo() == codigo) {
                        turmasDoAluno[count++] = turmas[i];
                        break;
                    }
                }
            }
        }
        Turma[] resultado = new Turma[count];
        System.arraycopy(turmasDoAluno, 0, resultado, 0, count);
        return resultado;
    }
    
    public void AlterarMatriculas(Matricula[] listar) {
        for (int j = 0; j < listar.length; j++) {
            if (listar[j] == null)
                continue;
            Matricula m = listar[j];
            for (int i = 0; i < qttTurmas; i++) {
                if (turmas[i] != null && turmas[i].getCodTurma() == m.getTurma().getCodTurma()) {
                    Matricula[] matriculasTurma = turmas[i].copiaMatriculas();
                    for (int k = 0; k < matriculasTurma.length; k++) {
                        if (matriculasTurma[k] != null
                                && matriculasTurma[k].getAluno().getCodigo() == m.getAluno().getCodigo()) {
                            turmas[i].alterarMatricula(m, k);
                        }
                    }
                }
            }
        }
    }
    
    public boolean alterarMatricula(Matricula alterarAluno) {
        for (int i = 0; i < qttTurmas; i++) {
            if (turmas[i] != null && turmas[i].getCodTurma() == alterarAluno.getTurma().getCodTurma()) {
                Matricula[] matriculas = turmas[i].copiaMatriculas();
                for (int j = 0; j < matriculas.length; j++) {
                    if (matriculas[j] != null
                            && matriculas[j].getAluno().getCodigo() == alterarAluno.getAluno().getCodigo()) {
                        turmas[i].alterarMatricula(alterarAluno, j);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public Matricula[] listarMatriculasTurma(int codTurma) {
        for (int i = 0; i < qttTurmas; i++) {
            if (turmas[i] != null && turmas[i].getCodTurma() == codTurma) {
                return turmas[i].copiaMatriculas();
            }
        }
        return new Matricula[0];
    }

    // 5. Getters e Setters
    private boolean aumentarVetorTurma() {
        int j = turmas.length * 2;
        Turma[] vaux = new Turma[j];
        for (int i = 0; i < turmas.length; i++) {
            vaux[i] = turmas[i];
        }
        turmas = vaux;
        return true;
    }
    
    public int getProxCodigo() {
        return proxCodigo++;
    }
}
