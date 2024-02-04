package objetos;

public class Usuario {
  private String username;
  private String nome;
  private String email;
  private String senha;
  private String telefone;
  private char sexo;

  public Usuario () {};
  
  public Usuario (String username, String nome, String email, String senha, String telefone, char sexo) {
    setUsername(username);
    setNome(nome);
    setEmail(email);
    setSenha(senha);
    setTelefone(telefone);
    setSexo(sexo);
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getNome() {
    return nome;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public String getSenha() {
    return senha;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setSexo(char sexo) {
    this.sexo = sexo;
  }

  public char getSexo() {
    return sexo;
  }
}
