package rian_mario.Controle;

public class Turma {


    private String nome;
    private String sigla;
    private int ano;
    private int vagas;
    private int qttPessoas;
    private Disciplina[] tDiscs;
    private int qttDisc;
    private int codTurma;
    private Matricula[] matriculas;


    private Turma(int codTurma, String nome, int ano, int vagas, String sigla) {
        this.codTurma = codTurma;
        this.nome = nome;
        this.ano = ano;
        this.vagas = vagas;
        this.sigla = sigla;

        this.tDiscs = new Disciplina[10]; 
        this.matriculas = new Matricula[10]; 
        this.qttPessoas = 0; 
        this.qttDisc = 0;
    }

    public Turma(Turma t) {
        this.codTurma = t.getCodTurma();
        this.nome = t.getNmturma();
        this.ano = t.getAno();
        this.vagas = t.getVagas();
        this.sigla = t.getSigla();

        this.tDiscs = t.copiaDiscs();
        this.matriculas = t.copiaMatriculas();
        this.qttPessoas = t.getQttPessoas();
        this.qttDisc = t.qttDisc;
    }


    public static Turma getInstance(int codigo, String nomeTurma, int anoTurma, int numVagas, String Sigla) {
        if (nomeTurma != null && anoTurma > 0 && numVagas > 0) {
            return new Turma(codigo, nomeTurma, anoTurma, numVagas, Sigla);
        }
        return null;
    }


    public boolean addDisciplinas(Disciplina disc) {
        if (disc == null) return false;

        for (int i = 0; i < qttDisc; i++) {
            if (tDiscs[i].getcodigoDisc() == disc.getcodigoDisc()) {
                return false;
            }
        }

 
        if (qttDisc == tDiscs.length) {
            aumentarVetorDisciplina();
        }

        tDiscs[qttDisc] = disc;
        qttDisc++;
        return true;
    }
    
    public boolean removerDisciplina(int codigo) {
        for (int i = 0; i < qttDisc; i++) {
            if (tDiscs[i] != null && tDiscs[i].getcodigoDisc() == codigo) {

                for (int j = i; j < qttDisc - 1; j++) {
                    tDiscs[j] = tDiscs[j + 1];
                }
                tDiscs[qttDisc - 1] = null;
                qttDisc--;
                return true;
            }
        }
        return false;
    }

    public boolean alterarDisciplina(int codigo, Disciplina disciplina) {
        for (int i = 0; i < qttDisc; i++) {
            if (tDiscs[i] != null && tDiscs[i].getcodigoDisc() == codigo) {
                tDiscs[i] = disciplina;
                return true;
            }
        }
        return false;
    }

    public boolean addMatriculas(Aluno a) {
        if (a == null || qttPessoas >= vagas) return false;


        for (int i = 0; i < qttPessoas; i++) {
            if (matriculas[i].getAluno().getCodigo() == a.getCodigo()) {
                return false;
            }
        }

        if (qttPessoas == matriculas.length) {
            aumentarVetorMatriculas();
        }

        Matricula matricula = Matricula.getInstance(this, a);
        matriculas[qttPessoas] = matricula;
        qttPessoas++;
        return true;
    }

    public boolean removerMatricula(int codigo) {
        for (int i = 0; i < qttPessoas; i++) {
            if (matriculas[i] != null && matriculas[i].getAluno().getCodigo() == codigo) {
                for (int j = i; j < qttPessoas - 1; j++) {
                    matriculas[j] = matriculas[j + 1];
                }
                matriculas[qttPessoas - 1] = null;
                qttPessoas--;
                return true;
            }
        }
        return false;
    }

    public Matricula getMatricula(int codigoAluno) {
        for (int i = 0; i < qttPessoas; i++) {
            if (matriculas[i] != null && matriculas[i].getAluno().getCodigo() == codigoAluno) {
                return matriculas[i];
            }
        }
        return null;
    }
   

    public void alterarMatricula(Matricula m, int k) {
        if (k >= 0 && k < qttPessoas) {
            matriculas[k] = m;
        }
    }
    
    public String getNmturma() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }
    
    public String getSigla() {
        return sigla;
    }

    public void setSigla(String nome) {
        if (nome.length() >= 3) {
            this.sigla = nome.substring(0, 3) + this.ano;
        } else {
            this.sigla = nome + this.ano;
        }
    }

    public int getCodTurma() {
        return codTurma;
    }

    public void setCodTurma(int codTurma) {
        this.codTurma = codTurma;
    }
    
    public int getQttPessoas() {
        return qttPessoas;
    }
    
    public Disciplina[] copiaDiscs() {
        Disciplina[] aux = new Disciplina[qttDisc];
        for (int i = 0; i < qttDisc; i++) {
            aux[i] = new Disciplina(tDiscs[i]);
        }
        return aux;
    }

    public Matricula[] copiaMatriculas() {
        Matricula[] aux = new Matricula[qttPessoas];
        for (int i = 0; i < qttPessoas; i++) {
            aux[i] = new Matricula(matriculas[i]);
        }
        return aux;
    }

    private void aumentarVetorDisciplina() {
        int novoTamanho = tDiscs.length * 2;
        Disciplina[] novoArray = new Disciplina[novoTamanho];
        System.arraycopy(tDiscs, 0, novoArray, 0, tDiscs.length);
        this.tDiscs = novoArray;
    }
    
    private void aumentarVetorMatriculas() {
        int novoTamanho = matriculas.length * 2;
        Matricula[] novoArray = new Matricula[novoTamanho];
        System.arraycopy(matriculas, 0, novoArray, 0, matriculas.length);
        this.matriculas = novoArray;
    }
}