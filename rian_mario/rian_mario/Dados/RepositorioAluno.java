package rian_mario.Dados;

import rian_mario.Controle.Aluno;

public class RepositorioAluno {

    private Aluno[] alunos = new Aluno[3];
    private int quantidadeAluno;
    private int proxCodigo;

    public boolean add(Aluno a) {
        if (a == null) {
            return false;
        }

        contarAluno();
        shiftAlunos();

        if (quantidadeAluno == alunos.length) {
            aumentarVetorAluno();
        }

        alunos[quantidadeAluno] = a;
        return true;
    }

    private void contarAluno() {
        quantidadeAluno = 0;
        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i] != null) {
                quantidadeAluno++;
            }
        }
    }

    private void aumentarVetorAluno() {
        Aluno[] aux = new Aluno[alunos.length * 2];
        for (int i = 0; i < alunos.length; i++) {
            aux[i] = alunos[i];
        }
        alunos = aux;
    }

    public boolean excluir(int codigo) {
        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i] != null && alunos[i].getCodigo() == codigo) {
                alunos[i] = null;
                return true;
            }
        }
        return false;
    }

    public boolean alterar(int posicao, Aluno novoAluno) {
        if (posicao >= 0 && posicao < alunos.length && novoAluno != null) {
            alunos[posicao] = novoAluno;
            return true;
        }
        return false;
    }

    public Aluno buscar(int codigo) {
        for (Aluno a : alunos) {
            if (a != null && a.getCodigo() == codigo) {
                return a;
            }
        }
        return null;
    }

    public Aluno[] getListaAluno() {
        Aluno[] copia = new Aluno[alunos.length];
        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i] != null) {
                copia[i] = new Aluno(alunos[i]); // construtor de cópia
            }
        }
        return copia;
    }

    public Aluno[] getListaAlunoNota() {
        Aluno[] copia = getListaAluno();

        for (int x = 0; x < copia.length - 1; x++) {
            for (int y = x + 1; y < copia.length; y++) {
                if (copia[x] != null && copia[y] != null) {
                    if (copia[x].getMedia() < copia[y].getMedia()) {
                        Aluno aux = copia[x];
                        copia[x] = copia[y];
                        copia[y] = aux;
                    }
                }
            }
        }
        return copia;
    }

    public boolean verificarCodigo(int codigo) {
        for (Aluno a : alunos) {
            if (a != null && a.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }

    public boolean verificarNome(String nome) {
        for (Aluno a : alunos) {
            if (a != null && a.getnmAluno().equals(nome)) {
                return false; // já existe aluno com esse nome
            }
        }
        return true;
    }

    private void shiftAlunos() {
        Aluno[] aux = new Aluno[alunos.length];
        int pos = 0;
        for (Aluno a : alunos) {
            if (a != null) {
                aux[pos++] = a;
            }
        }
        alunos = aux;
    }

    public boolean buscarPorNome(Aluno a) {
        if (a == null) return false;
        for (Aluno aluno : alunos) {
            if (aluno != null && aluno.getnmAluno().equals(a.getnmAluno())) {
                return true;
            }
        }
        return false;
    }

    public int proximoCodigo() {
        return proxCodigo++;
    }

    public Aluno getAluno(int codigo) {
        for (Aluno a : alunos) {
            if (a != null && a.getCodigo() == codigo) {
                return a;
            }
        }
        return null;
    }

    public void setListaAluno(Aluno[] novaLista) {
        alunos = novaLista;
    }

    public boolean alterarNome(int codigo, String novoNome) {
        if (novoNome == null) return false;
        for (Aluno a : alunos) {
            if (a != null && a.getCodigo() == codigo) {
                a.setnmAluno(novoNome);
                return true;
            }
        }
        return false;
    }

    public boolean alterarCpf(int codigo, String cpf) {
        if (cpf == null) return false;
        for (Aluno a : alunos) {
            if (a != null && a.getCodigo() == codigo) {
                a.setCpf(cpf);
                return true;
            }
        }
        return false;
    }
}
