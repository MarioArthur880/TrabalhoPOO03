package rian_mario.Dados;

import rian_mario.Controle.Aluno;
import rian_mario.Controle.Matricula;
import rian_mario.Controle.Turma;

public class RepositorioMatriculas {
    private Matricula[] matriculas;
    private int quantidadeMatriculas;
    private int proxCodigo;

    public RepositorioMatriculas() {
        matriculas = new Matricula[10];
        quantidadeMatriculas = 0;
        proxCodigo = 1;
    }

    public boolean add(Matricula matricula) {
        if (matricula == null) return false;

        contarMatriculas();
        shiftMatriculas();

        if (quantidadeMatriculas == matriculas.length) {
            aumentarVetorMatriculas();
        }

        matricula.setCodigo(proxCodigo++);
        matriculas[quantidadeMatriculas++] = matricula;
        return true;
    }

    private void aumentarVetorMatriculas() {
        Matricula[] aux = new Matricula[matriculas.length * 2];
        for (int i = 0; i < matriculas.length; i++) {
            aux[i] = matriculas[i];
        }
        matriculas = aux;
    }

    private void shiftMatriculas() {
        Matricula[] aux = new Matricula[matriculas.length];
        int pos = 0;
        for (Matricula m : matriculas) {
            if (m != null) {
                aux[pos++] = m;
            }
        }
        matriculas = aux;
    }

    private void contarMatriculas() {
        quantidadeMatriculas = 0;
        for (Matricula m : matriculas) {
            if (m != null) {
                quantidadeMatriculas++;
            }
        }
    }

    public Matricula[] listar() {
        contarMatriculas();
        if (quantidadeMatriculas <= 0) {
            return new Matricula[0];
        }

        Matricula[] copia = new Matricula[quantidadeMatriculas];
        int index = 0;
        for (Matricula m : matriculas) {
            if (m != null) {
                copia[index++] = new Matricula(m); // construtor de cópia
            }
        }
        return copia;
    }

    public boolean remover(Matricula matricula) {
        if (matricula == null) return false;

        for (int i = 0; i < matriculas.length; i++) {
            if (matriculas[i] != null 
                && matriculas[i].getAluno().getCodigo() == matricula.getAluno().getCodigo()
                && matriculas[i].getTurma().getCodTurma() == matricula.getTurma().getCodTurma()) {
                
                matriculas[i] = null;
                quantidadeMatriculas--;
                shiftMatriculas();
                return true;
            }
        }
        return false;
    }

    public boolean removerMatriculaTurma(Turma turma) {
        if (turma == null) return false;

        boolean removed = false;
        for (int i = 0; i < matriculas.length; i++) {
            if (matriculas[i] != null && matriculas[i].getTurma().getCodTurma() == turma.getCodTurma()) {
                matriculas[i] = null;
                quantidadeMatriculas--;
                removed = true;
            }
        }
        if (removed) {
            shiftMatriculas();
        }
        return removed;
    }

    public void AlterarMatriculas(Aluno[] alunos) {
        if (alunos == null) return;

        for (Aluno aluno : alunos) {
            if (aluno == null) continue;
            for (Matricula m : matriculas) {
                if (m != null && m.getAluno().getCodigo() == aluno.getCodigo()) {
                    m.setAluno(aluno);
                }
            }
        }
    }

    public Matricula alterarAluno(Aluno aluno) {
        if (aluno == null) return null;

        for (Matricula m : matriculas) {
            if (m != null && m.getAluno().getCodigo() == aluno.getCodigo()) {
                m.setAluno(aluno);
                return m;
            }
        }
        return null;
    }
}
