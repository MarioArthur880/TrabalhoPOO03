package rian_mario.Controle;

public class Aluno {

	private String nmAluno;
	private String cpf;
	private int codigo;
	private double media;

	private Aluno(int codigo, String nmAluno, String cpf) {
		this.nmAluno = nmAluno;
		this.codigo = codigo;
		this.cpf = cpf;
		media = 0;
	}


	public Aluno(Aluno a) {
		this.nmAluno = a.getnmAluno();
		this.codigo = a.getCodigo();
		this.cpf = a.getCpf();
		this.media = a.getMedia();

	}

	static public Aluno getInstance(int codigo, String nmAluno, String cpf) {
		if (codigo < 0 || nmAluno == null  || cpf == null) {
			return null;
		} else {

			
			return new Aluno(codigo, nmAluno, cpf);
			
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

	public String getnmAluno() {
		return nmAluno;
	}

	public int getCodigo() {
		return codigo;
	}

	public double getMedia() {
		return media;
	}

	public void setnmAluno(String nmAluno) {
		this.nmAluno = nmAluno;
	}

	public void setCodigoAluno(int cdaluno) {
		this.codigo = cdaluno;
	}

	public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Aluno that = (Aluno) obj;
    return codigo == that.codigo; 
}


	

}
