package model;

public class Aluno extends Usuario {
  private String nomeResponsavel;

  public Aluno () {
	super();
  }
  
  public Aluno (String id, String username, String nome, String email, String senha, String telefone, char sexo, boolean is_adm, String nomeResponsavel) {
    super(id, username, nome, email, senha, telefone, sexo, is_adm);
    setNomeResponsavel(nomeResponsavel);
  }

  public void setNomeResponsavel(String nomeResponsavel) {
    this.nomeResponsavel = nomeResponsavel;
  }
  
  public String getNomeResponsavel() {
    return nomeResponsavel;
  }
}
