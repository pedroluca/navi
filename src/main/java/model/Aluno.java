package model;

public class Aluno extends Usuario {
  private String nomeResponsavel;

  public Aluno () {
	super();
  }

  public void setNomeResponsavel(String nomeResponsavel) {
    this.nomeResponsavel = nomeResponsavel;
  }
  
  public String getNomeResponsavel() {
    return nomeResponsavel;
  }
}
