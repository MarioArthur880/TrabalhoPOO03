package rian_mario.Controle;

import java.util.Objects;

public class Disciplina {
	private int codigoDisc;
	private String nomeDisc;
	private String professor;
	private String sigla;

	private Disciplina(int codigoDisc, String nomeDisc, String professor, String sigla) {
		this.codigoDisc = codigoDisc;
		this.nomeDisc = nomeDisc;
		this.professor = professor;
		this.sigla = sigla;

	}

	public Disciplina(Disciplina d) {
		this.codigoDisc = d.getCddisc();
		this.nomeDisc = d.getNmdisc();
		this.professor = d.getNmprof();
		this.sigla = d.getSigla();

	}
	
	public static Disciplina getInstance(int cd, String nome, String nomeProfessor, String sigla2) {
		if (cd >= 0 && nome != null && nomeProfessor != null && sigla2 != null) {
			return new Disciplina(cd, nome, nomeProfessor, sigla2);
		}
		return null;
	}

	public String getNmprof() {
		return professor;
	}

	public String getNmdisc() {
		return nomeDisc;
	}

	public int getCddisc() {
		return codigoDisc;
	}

	public void setCddisc(int cddisc) {
		this.codigoDisc = cddisc;
	}

	public void setNmdisc(String nmdisc) {
		this.nomeDisc = nmdisc;
	}

	public void setNmprof(String nmprof) {
		this.professor = nmprof;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Disciplina that = (Disciplina) obj;
		return this.codigoDisc == that.codigoDisc; // ou outro identificador único
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigoDisc); // mesmo campo usado no equals
	}

}
