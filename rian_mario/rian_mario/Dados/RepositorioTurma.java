package rian_mario.Dados;

import rian_mario.Controle.Aluno;
import rian_mario.Controle.Disciplina;
import rian_mario.Controle.Matricula;
import rian_mario.Controle.Turma;
import java.time.LocalDateTime;

public class RepositorioTurma {

    // 1. Variáveis
    private static RepositorioTurma instancia;
    private Turma[] turmas = new Turma[3];
    private int qttTurmas = 0;
    private int proxId = 0;

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

    public boolean removerTurma(int id) {
        if (qttTurmas == 0) {
            return false;
        }
        
        int indexParaRemover = -1;
        for (int i = 0; i < qttTurmas; i++) {
            if (turmas[i] != null && turmas[i].getAno() == id) { // Usando ano como identificador
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

    public boolean removerDisciplina(int idDisciplina) {
        if (qttTurmas == 0)
            return false;
        boolean removido = false;
        
        for (int i = 0; i < qttTurmas; i++) {
            if (turmas[i] != null && turmas[i].getDisciplinas() != null) {
                Disciplina[] disciplinas = turmas[i].getDisciplinas();
                for (int j = 0; j < disciplinas.length; j++) {
                    if (disciplinas[j] != null && disciplinas[j].getIdDisc() == idDisciplina) {
                        disciplinas[j] = null;
                        removido = true;
                    }
                }
            }
        }
        return removido;
    }

    public Matricula matricularAluno(Aluno aluno, int anoTurma, String nomeTurma) {
        for (int i = 0; i < qttTurmas; i++) {
            if (turmas[i] != null && turmas[i].getAno() == anoTurma) {
                // Cria nova matrícula
                Matricula novaMatricula = Matricula.criarMatricula(
                    LocalDateTime.now(), 
                    nomeTurma, 
                    aluno
                );
                
                if (novaMatricula != null) {
                    // Adiciona matrícula ao array de matrículas da turma
                    Matricula[] matriculas = turmas[i].getMatriculas();
                    if (matriculas != null) {
                        for (int j = 0; j < matriculas.length; j++) {
                            if (matriculas[j] == null) {
                                matriculas[j] = novaMatricula;
                                return novaMatricula;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    
    public Matricula[] listarMatriculas() {
        int totalMatriculas = 0;
        
        // Conta total de matrículas
        for (int i = 0; i < qttTurmas; i++) {
            if (turmas[i] != null && turmas[i].getMatriculas() != null) {
                Matricula[] matriculas = turmas[i].getMatriculas();
                for (Matricula m : matriculas) {
                    if (m != null) totalMatriculas++;
                }
            }
        }
        
        Matricula[] todasMatriculas = new Matricula[totalMatriculas];
        int index = 0;
        
        // Copia todas as matrículas
        for (int i = 0; i < qttTurmas; i++) {
            if (turmas[i] != null && turmas[i].getMatriculas() != null) {
                Matricula[] matriculas = turmas[i].getMatriculas();
                for (Matricula m : matriculas) {
                    if (m != null) {
                        todasMatriculas[index++] = new Matricula(m);
                    }
                }
            }
        }
        return todasMatriculas;
    }

    public boolean alterar(int ano, Turma turma) {
        for (int i = 0; i < qttTurmas; i++) {
            if (turmas[i] != null && turmas[i].getAno() == ano) {
                turmas[i] = turma;
                return true;
            }
        }
        return false;
    }

    public boolean adicionarDisciplina(int anoTurma, Disciplina disciplina) {
        for (int i = 0; i < qttTurmas; i++) {
            Turma turma = turmas[i];
            if (turma != null && turma.getAno() == anoTurma && turma.getDisciplinas() != null) {
                Disciplina[] disciplinas = turma.getDisciplinas();
                
                // Verifica se disciplina já existe
                for (Disciplina d : disciplinas) {
                    if (d != null && d.getIdDisc() == disciplina.getIdDisc()) {
                        return false;
                    }
                }
                
                // Adiciona disciplina em posição vazia
                for (int j = 0; j < disciplinas.length; j++) {
                    if (disciplinas[j] == null) {
                        disciplinas[j] = disciplina;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean alterarDisciplina(int idDisciplina, Disciplina novaDisciplina) {
        for (int i = 0; i < qttTurmas; i++) {
            if (turmas[i] != null && turmas[i].getDisciplinas() != null) {
                Disciplina[] disciplinas = turmas[i].getDisciplinas();
                for (int j = 0; j < disciplinas.length; j++) {
                    if (disciplinas[j] != null && disciplinas[j].getIdDisc() == idDisciplina) {
                        disciplinas[j] = novaDisciplina;
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean removerMatricula(int idAluno, int anoTurma) {
        for (int i = 0; i < qttTurmas; i++) {
            if (turmas[i] != null && turmas[i].getAno() == anoTurma && turmas[i].getMatriculas() != null) {
                Matricula[] matriculas = turmas[i].getMatriculas();
                for (int j = 0; j < matriculas.length; j++) {
                    if (matriculas[j] != null && 
                        matriculas[j].getAluno() != null && 
                        matriculas[j].getAluno().getId() == idAluno) {
                        matriculas[j] = null;
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public Disciplina[] listarDisciplinas(int anoTurma) {
        for (int i = 0; i < qttTurmas; i++) {
            if (turmas[i] != null && turmas[i].getAno() == anoTurma) {
                Disciplina[] disciplinas = turmas[i].getDisciplinas();
                if (disciplinas != null) {
                    // Conta disciplinas não nulas
                    int count = 0;
                    for (Disciplina d : disciplinas) {
                        if (d != null) count++;
                    }
                    
                    // Cria array com cópias
                    Disciplina[] copia = new Disciplina[count];
                    int index = 0;
                    for (Disciplina d : disciplinas) {
                        if (d != null) {
                            copia[index++] = new Disciplina(d);
                        }
                    }
                    return copia;
                }
            }
        }
        return new Disciplina[0];
    }
    
    public Matricula getMatricula(int anoTurma, int idAluno) {
        for (int i = 0; i < qttTurmas; i++) {
            if (turmas[i] != null && turmas[i].getAno() == anoTurma && turmas[i].getMatriculas() != null) {
                Matricula[] matriculas = turmas[i].getMatriculas();
                for (Matricula m : matriculas) {
                    if (m != null && m.getAluno() != null && m.getAluno().getId() == idAluno) {
                        return m;
                    }
                }
            }
        }
        return null;
    }
    
    public Turma[] getTurmasDoAluno(int idAluno) {
        Turma[] turmasDoAluno = new Turma[qttTurmas];
        int count = 0;
        for (int i = 0; i < qttTurmas; i++) {
            if (turmas[i] != null && turmas[i].getMatriculas() != null) {
                Matricula[] matriculas = turmas[i].getMatriculas();
                for (Matricula m : matriculas) {
                    if (m != null && m.getAluno() != null && m.getAluno().getId() == idAluno) {
                        turmasDoAluno[count++] = new Turma(turmas[i]);
                        break;
                    }
                }
            }
        }
        Turma[] resultado = new Turma[count];
        System.arraycopy(turmasDoAluno, 0, resultado, 0, count);
        return resultado;
    }
    
    public boolean alterarMatricula(Matricula matriculaAlterada) {
        if (matriculaAlterada == null || matriculaAlterada.getAluno() == null) {
            return false;
        }
        
        int idAluno = matriculaAlterada.getAluno().getId();
        String turmaAlvo = matriculaAlterada.getTurma();
        
        for (int i = 0; i < qttTurmas; i++) {
            if (turmas[i] != null && turmas[i].getMatriculas() != null) {
                Matricula[] matriculas = turmas[i].getMatriculas();
                for (int j = 0; j < matriculas.length; j++) {
                    if (matriculas[j] != null && 
                        matriculas[j].getAluno() != null &&
                        matriculas[j].getAluno().getId() == idAluno &&
                        matriculas[j].getTurma() != null &&
                        matriculas[j].getTurma().equals(turmaAlvo)) {
                        matriculas[j] = matriculaAlterada;
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public Matricula[] listarMatriculasTurma(int anoTurma) {
        for (int i = 0; i < qttTurmas; i++) {
            if (turmas[i] != null && turmas[i].getAno() == anoTurma && turmas[i].getMatriculas() != null) {
                Matricula[] matriculas = turmas[i].getMatriculas();
                
                // Conta matrículas não nulas
                int count = 0;
                for (Matricula m : matriculas) {
                    if (m != null) count++;
                }
                
                // Cria array com cópias
                Matricula[] copia = new Matricula[count];
                int index = 0;
                for (Matricula m : matriculas) {
                    if (m != null) {
                        copia[index++] = new Matricula(m);
                    }
                }
                return copia;
            }
        }
        return new Matricula[0];
    }

    public Turma buscarTurma(int ano) {
        for (int i = 0; i < qttTurmas; i++) {
            if (turmas[i] != null && turmas[i].getAno() == ano) {
                return turmas[i];
            }
        }
        return null;
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
    
    public int getProxId() {
        return proxId++;
    }
}