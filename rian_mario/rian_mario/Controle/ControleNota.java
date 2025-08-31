package rian_mario.Controle;

import rian_mario.Dados.RepoNota;

public class ControleNota {
    private RepoNota repoNotas;

    public ControleNota() {
        repoNotas = new RepoNota();
    }

    public boolean adicionarNota(Nota nota) {
        return repoNotas.add(nota);
    }

    public boolean adicionarNotasMatriculas(Matricula[] matriculas, Disciplina disciplina) {
        for (Matricula matricula : matriculas) {
            if (matricula != null) {
                Nota nota = new Nota(disciplina, matricula, 0.0);
                if (!repoNotas.add(nota)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean adicionarNotasDisciplinas(Disciplina[] disciplinas, Matricula matricula) {
        for (Disciplina disciplina : disciplinas) {
            if (disciplina != null) {
                Nota nota = new Nota(disciplina, matricula, 0.0);
                if (!repoNotas.add(nota)) {
                    return false;
                }
            }
        }
        return true;
    }

    public Nota[] getNotas() {
        return repoNotas.getNotas();
    }

    public boolean alterarNota(Nota nota) {
        return repoNotas.alterarNota(nota);
    }

    public boolean removerNotaDisciplina(int idDisc) {
        return repoNotas.removerNotaDisciplina(idDisc);
    }

    public boolean removerNotaMatricula(int idAluno) {
        return repoNotas.removerNotaMatricula(idAluno);
    }

    public void alterarValorNota(Disciplina disciplina, Matricula matricula, double valor) {
        Nota nota = repoNotas.getNota(disciplina, matricula);
        
        if (nota == null) {
            Nota novaNota = new Nota(disciplina, matricula, valor);
            repoNotas.add(novaNota);
        } else {
            nota.setValor(valor);
        }
    }

    public Nota getInstance(Disciplina disciplina, Matricula matricula, double valor) {
        return Nota.criarNota(disciplina, matricula, valor);
    }
}