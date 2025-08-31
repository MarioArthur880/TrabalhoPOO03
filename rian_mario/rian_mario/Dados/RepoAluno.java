package rian_mario.Dados;

import rian_mario.Controle.Aluno;

public class RepoAluno {
    private Aluno[] alunos = new Aluno[3];
    private int qttAluno;

    public boolean add(Aluno a) {
        contarAluno();
        shiftAlunos();
        if (a != null) {
            if (alunos.length == qttAluno) {
                aumentarVetorAluno();
            }
            alunos[qttAluno] = a;
            qttAluno++; // CORRIGIDO: incrementar após adicionar
            return true;
        } else {
            return false;
        }
    }

    private void contarAluno() {
        qttAluno = 0;
        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i] != null) {
                qttAluno++;
            }
        }
    }

    private void aumentarVetorAluno() {
        int j = alunos.length * 2;
        Aluno[] vaux = new Aluno[j];
        for (int i = 0; i < alunos.length; i++) {
            vaux[i] = alunos[i];
        }
        alunos = vaux;
    }

    // CORRIGIDO: Método agora funciona corretamente
    public boolean excluir(int idAluno) {
        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i] != null && alunos[i].getId() == idAluno) {
                alunos[i] = null;
                qttAluno--;
                shiftAlunos();
                return true;
            }
        }
        return false;
    }

    // CORRIGIDO: Método agora busca por ID e altera o aluno encontrado
    public boolean alterar(int idAluno, Aluno novoAluno) {
        if (novoAluno != null) {
            for (int i = 0; i < alunos.length; i++) {
                if (alunos[i] != null && alunos[i].getId() == idAluno) {
                    alunos[i] = novoAluno;
                    return true;
                }
            }
        }
        return false;
    }

    // CORRIGIDO: Método agora busca por ID
    public Aluno buscar(int idAluno) {
        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i] != null && alunos[i].getId() == idAluno) {
                return new Aluno(alunos[i]); // retorna uma cópia
            }
        }
        return null;
    }

    public Aluno[] getListaAluno() {
        contarAluno();
        if (qttAluno == 0) {
            return new Aluno[0];
        }
        
        Aluno[] copia = new Aluno[qttAluno];
        int pos = 0;
        
        for (Aluno a : alunos) {
            if (a != null) {
                copia[pos++] = new Aluno(a);
            }
        }
        return copia;
    }

    // REMOVIDO: Método que usava getMedia() inexistente
    // Se necessário, pode ser implementado quando a classe Aluno tiver esse método

    // CORRIGIDO: Método usando getId() ao invés de getCodigo()
    public boolean verificarId(int id) {
        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i] != null && alunos[i].getId() == id) {
                return true;
            }
        }
        return false;
    }

    // CORRIGIDO: Método usando getNomeAluno() que existe na classe
    public boolean verificarNome(String nome) {
        if (nome == null) {
            return false;
        }
        
        contarAluno();
        for (int i = 0; i < qttAluno; i++) {
            if (alunos[i] != null && alunos[i].getNomeAluno().equals(nome)) {
                return false; // nome já existe
            }
        }
        return true; // nome não existe, pode usar
    }

    private void shiftAlunos() {
        int pos = 0;
        Aluno[] vaux = new Aluno[alunos.length];

        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i] != null) {
                vaux[pos++] = alunos[i];
            }
        }
        alunos = vaux;
    }

    public boolean buscarPorNome(Aluno a) {
        if (a == null) {
            return false;
        }
        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i] != null && alunos[i].getNomeAluno().equals(a.getNomeAluno())) {
                return true;
            }
        }
        return false;
    }

    // CORRIGIDO: Busca por ID que existe na classe Aluno
    public Aluno getAluno(int idAluno) {
        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i] != null && alunos[i].getId() == idAluno) {
                return new Aluno(alunos[i]); // retorna uma cópia
            }
        }
        return null;
    }

    public void setListaAluno(Aluno[] novosAlunos) {
        if (novosAlunos != null) {
            alunos = novosAlunos;
            contarAluno();
        }
    }

    // CORRIGIDO: Método usando getNomeAluno() e setNomeAluno()
    public boolean alterarNome(int idAluno, String novoNome) {
        if (novoNome != null) {
            for (int j = 0; j < alunos.length; j++) {
                if (alunos[j] != null && alunos[j].getId() == idAluno) {
                    alunos[j].setNomeAluno(novoNome);
                    return true;
                }
            }
        }
        return false;
    }

    // REMOVIDOS: Métodos que usavam propriedades inexistentes na classe Aluno
    // (alterarEndereco, alterarNomeMae)
    // Se necessário, podem ser implementados quando a classe Aluno tiver essas propriedades

    public boolean alterarCpf(int idAluno, String novoCpf) {
        if (novoCpf != null) {
            for (int j = 0; j < alunos.length; j++) {
                if (alunos[j] != null && alunos[j].getId() == idAluno) {
                    alunos[j].setCpf(novoCpf);
                    return true;
                }
            }
        }
        return false;
    }
}