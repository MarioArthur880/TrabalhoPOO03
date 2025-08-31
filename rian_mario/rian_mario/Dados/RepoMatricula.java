package rian_mario.Dados;

import rian_mario.Controle.Aluno;
import rian_mario.Controle.Matricula;
import rian_mario.Controle.Turma;

public class RepoMatricula {
    private Matricula[] matriculas;
    private int qttMatriculas;
    private int proxCodigo;

    public RepositorioMatriculas() {
        matriculas = new Matricula[10];
        qttMatriculas = 0;
        proxCodigo = 1;
    }

    public boolean add(Matricula matricula) {
        contarMatriculas();
        shiftMatriculas();
        if (matricula != null) {
            if (matriculas.length == qttMatriculas) {
                aumentarVetorMatriculas();
                matricula.setCodigo(proxCodigo++);
                matriculas[qttMatriculas] = matricula;
                return true;
            }
            matriculas[qttMatriculas] = matricula;
            return true;
        } else {
            return false;
        }
    }

    private void aumentarVetorMatriculas() {
        int j = matriculas.length * 2;
        Matricula[] vaux = new Matricula[j];
        for (int i = 0; i < matriculas.length; i++) {
            vaux[i] = matriculas[i];
        }
        matriculas = vaux;

    }

    private void shiftMatriculas() {
        int pos = 0;
        Matricula[] vaux = new Matricula[matriculas.length];

        for (int i = 0; i < matriculas.length; i++) {
            if (matriculas[i] != null) {
                vaux[pos++] = matriculas[i];
            }
        }

        matriculas = vaux;
    }

    private void contarMatriculas() {
        qttMatriculas = 0;
        for (int i = 0; i < matriculas.length; i++) {
            if (matriculas[i] != null) {
                qttMatriculas++;
            }
        }
    }

    public Matricula[] listar() {
        contarMatriculas();
        if (qttMatriculas <= 0) {
            return new Matricula[0]; // Retorna um array vazio se não houver matrículas
        }

        Matricula[] copia = new Matricula[qttMatriculas];
        for (int i = 0; i < qttMatriculas; i++) {
            if (matriculas[i] != null)
            copia[i] = new Matricula(matriculas[i]);
        }
        return copia;
    }

  public boolean remover(Matricula matricula) {
    for (int i = 0; i < matriculas.length; i++) {
        if (matriculas[i] != null 
            && matriculas[i].getAluno().getCodigo() == matricula.getAluno().getCodigo()
            && matriculas[i].getTurma().getCodTurma() == matricula.getTurma().getCodTurma()) {
            
            matriculas[i] = null;
            qttMatriculas--;
            shiftMatriculas(); // reorganiza e mantém consistência
            return true;
        }
    }
    return false;
}

    public boolean removerMatriculaTurma(Turma turma) {
        boolean removed = false;
        for (int i = 0; i < matriculas.length; i++) {
            if (matriculas[i] != null && matriculas[i].getTurma().getCodTurma() == turma.getCodTurma()) {
                matriculas[i] = null;

                qttMatriculas--; // Decrementar a contagem de matrículas
                removed = true;
            }
        }
        return removed;
    }

    public void AlterarMatriculas(Aluno[] alunos) {
    for (Aluno aluno : alunos) {
        if (aluno == null)
            continue; // ignora só esse aluno
        for (int i = 0; i < matriculas.length; i++) {
            if (matriculas[i] != null && matriculas[i].getAluno().getCodigo() == aluno.getCodigo()) {
                matriculas[i].setAluno(aluno); // atualiza o objeto aluno na matrícula
            }
        }
    }
}

    public Matricula alterarAluno(Aluno aluno) {
        for (int i = 0; i < matriculas.length; i++) {
            if (matriculas[i] != null && matriculas[i].getAluno().getCodigo() == aluno.getCodigo()) {
                matriculas[i].setAluno(aluno);
                return matriculas[i];
            }
        }
        return null;
    }
}
