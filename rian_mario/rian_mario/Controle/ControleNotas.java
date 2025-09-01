package rian_mario.Controle;

import rian_mario.Dados.RepositorioNota;

public class ControleNotas {
    private RepositorioNota repoNotas;

    public ControleNotas() {
        repoNotas = new RepositorioNota();
    }

    public boolean adicionarNota1(Notas nota) {
        return repoNotas.add(nota);
    }

    public boolean adicionarNota2(Matricula[] matriculas, Disciplina disciplina) {
        for (Matricula matricula : matriculas) {
            Notas nota = new Notas(disciplina, matricula);
            if (!repoNotas.add(nota)) {
                return false;
            }
        }
        return true;
    }

    public boolean adicionarNota3(Disciplina[] disciplinas, Matricula matricula) {
        for (Disciplina disciplina : disciplinas) {
            Notas nota = new Notas(disciplina, matricula);
            if (!repoNotas.add(nota)) {
                return false;
            }
        }
        return true;
    }

    public Notas[] getNotas() {
        return repoNotas.getNotas();
    }

    public boolean alterarNota(Notas nota) {
        return repoNotas.alterarNota(nota);
    }

    public boolean removerNotaDelDiciplina(int codigo) {

        return repoNotas.removerNota(codigo);
    }

    public boolean removerNotaDelDisciplinaDeTurma(Turma turma, int codigo) {
        return repoNotas.removerNota2(turma, codigo);
    }

    public boolean removerNotaDelturma(Turma turma) {
        return repoNotas.removerNota(turma);
    }

    public boolean removerNotaDelMatricula(Matricula matricula) {
        return repoNotas.removerNota4(matricula);
    }

    public boolean adicionarNotaDisciplina(Matricula[] listarMatriculas, Disciplina disciplina) {
        for (Matricula matricula : listarMatriculas) {
            Notas nota = new Notas(disciplina, matricula);
            if (!repoNotas.add(nota)) {
                return false;
            }
        }
        return true;
    }

    public void alterarNota2(Disciplina disciplina, Matricula matricula, double d) {
    Notas nota = repoNotas.getNota(disciplina, matricula);
    

    if (nota == null) {
        Notas novaNota = new Notas(disciplina, matricula, d);
        repoNotas.add(novaNota);
    } else {

        nota.setValor(d);
    }
}

    public Notas getInstance(Disciplina disciplina, Matricula matricula, double novaNota) {
        return Notas.getInstance(disciplina, matricula, novaNota);
    }
}
