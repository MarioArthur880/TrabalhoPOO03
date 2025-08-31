package rian_mario.Dados;

import rian_mario.Controle.Aluno;
import rian_mario.Controle.Matricula;
import rian_mario.Controle.Turma;

public class RepoMatricula {
    private Matricula[] matriculas;
    private int qttMatriculas;

    // CORRIGIDO: Nome do construtor deve ser igual ao nome da classe
    public RepoMatricula() {
        matriculas = new Matricula[10];
        qttMatriculas = 0;
    }

    public boolean add(Matricula matricula) {
        contarMatriculas();
        shiftMatriculas();
        if (matricula != null) {
            if (matriculas.length == qttMatriculas) {
                aumentarVetorMatriculas();
                matriculas[qttMatriculas] = matricula;
                qttMatriculas++; // CORRIGIDO: incrementar após adicionar
                return true;
            }
            matriculas[qttMatriculas] = matricula;
            qttMatriculas++; // CORRIGIDO: incrementar após adicionar
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
            return new Matricula[0];
        }

        Matricula[] copia = new Matricula[qttMatriculas];
        for (int i = 0; i < qttMatriculas; i++) {
            if (matriculas[i] != null) { // CORRIGIDO: adicionada chaves
                copia[i] = new Matricula(matriculas[i]);
            }
        }
        return copia;
    }

    // CORRIGIDO: Método simplificado usando apenas o ID do aluno
    public boolean remover(int idAluno) {
        for (int i = 0; i < matriculas.length; i++) {
            if (matriculas[i] != null && matriculas[i].getAluno().getId() == idAluno) {
                matriculas[i] = null;
                qttMatriculas--;
                shiftMatriculas();
                return true;
            }
        }
        return false;
    }

    // CORRIGIDO: Método simplificado usando apenas o ID da turma
    public boolean removerMatriculaTurma(int idTurma) {
        boolean removed = false;
        for (int i = 0; i < matriculas.length; i++) {
            if (matriculas[i] != null && matriculas[i].getTurma().equals(String.valueOf(idTurma))) {
                matriculas[i] = null;
                qttMatriculas--;
                removed = true;
            }
        }
        if (removed) {
            shiftMatriculas(); // CORRIGIDO: reorganizar apenas se houve remoção
        }
        return removed;
    }

    // CORRIGIDO: Método renomeado e simplificado
    public void alterarAlunos(Aluno[] alunos) {
        for (Aluno aluno : alunos) {
            if (aluno == null) {
                continue;
            }
            for (int i = 0; i < matriculas.length; i++) {
                if (matriculas[i] != null && matriculas[i].getAluno().getId() == aluno.getId()) {
                    matriculas[i].setAluno(aluno);
                }
            }
        }
    }

    public Matricula alterarAluno(Aluno aluno) {
        for (int i = 0; i < matriculas.length; i++) {
            if (matriculas[i] != null && matriculas[i].getAluno().getId() == aluno.getId()) {
                matriculas[i].setAluno(aluno);
                return matriculas[i];
            }
        }
        return null;
    }
}