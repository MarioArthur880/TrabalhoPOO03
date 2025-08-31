package rian_mario.Controle;

public class Disciplina {

    private int idDisc;
    private String nomeDisc;
    private String prof;
    
    // Construtor padrão
    public Disciplina() {
    }
    
    // Construtor com parâmetros
    public Disciplina(int idDisc, String nomeDisc, String prof) {
        this.idDisc = idDisc;
        this.nomeDisc = nomeDisc;
        this.prof = prof;
    }
    
    // Construtor de cópia
    public Disciplina(Disciplina outra) {
        this.idDisc = outra.idDisc;
        this.nomeDisc = outra.nomeDisc;
        this.prof = outra.prof;
    }
    
    // Método fábrica
    public static Disciplina criarDisciplina(int idDisc, String nomeDisc, String prof) {
        if (nomeDisc != null && prof != null) {
            return new Disciplina(idDisc, nomeDisc, prof);
        }
        return null;
    }
    
    // Método fábrica alternativo para criar cópia
    public static Disciplina criarCopia(Disciplina original) {
        if (original != null) {
            return new Disciplina(original);
        }
        return null;
    }
    
    // Getters
    public int getIdDisc() {
        return idDisc;
    }
    
    public String getNomeDisc() {
        return nomeDisc;
    }
    
    public String getProf() {
        return prof;
    }
    
    // Setters
    public void setIdDisc(int idDisc) {
        this.idDisc = idDisc;
    }
    
    public void setNomeDisc(String nomeDisc) {
        this.nomeDisc = nomeDisc;
    }
    
    public void setProf(String prof) {
        this.prof = prof;
    }
}