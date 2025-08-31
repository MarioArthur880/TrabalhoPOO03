package rian_mario.Controle;

import java.util.Objects;

public class Aluno {

	private String nmaluno;
	private String cpf;
	private int codigo;

	private double media;

	private Aluno(int codigo, String nmaluno, String cpf) {
		this.nmaluno = nmaluno;
		this.codigo = codigo;
		this.cpf = cpf;
		media = 0;
	}


	public Aluno(Aluno a) {
		this.nmaluno = a.getNmaluno();
		this.codigo = a.getCodigo();
		this.cpf = a.getCpf();
		this.media = a.getMedia();

	}

	static public Aluno getInstance(int codigo, String nmaluno, String cpf) {
		if (codigo < 0 || nmaluno == null  || cpf == null) {
			return null;
		} else {

			
			return new Aluno(codigo, nmaluno, cpf);
			
		}
	}

	public void setMedia(double media) {
		this.media = media;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNmaluno() {
		return nmaluno;
	}

	public int getCodigo() {
		return codigo;
	}

	public double getMedia() {
		return media;
	}

	public void setNmaluno(String nmaluno) {
		this.nmaluno = nmaluno;
	}

	public void setCdaluno(int cdaluno) {
		this.codigo = cdaluno;
	}

	public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Aluno that = (Aluno) obj;
    return codigo == that.codigo; // ou outro identificador único
}

@Override
public int hashCode() {
    return Objects.hash(codigo); // mesmo campo usado no equals
}
	

}
