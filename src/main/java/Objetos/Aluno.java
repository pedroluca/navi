package objetos;

public class Aluno extends Usuario {
  private String nomeResponsavel;

  public Aluno () {
	super();
  }
  
  public Aluno (String username, String nome, String email, String senha, String telefone, char sexo, String nomeResponsavel) {
    super(username, nome, email, senha, telefone, sexo);
    setNomeResponsavel(nomeResponsavel);
  }

  public void setNomeResponsavel(String nomeResponsavel) {
    this.nomeResponsavel = nomeResponsavel;
  }
  
  public String getNomeResponsavel() {
    return nomeResponsavel;
  }
}
