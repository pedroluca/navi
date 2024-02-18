package model;

public abstract class Usuario {
  private String id;
  private String username;
  private String nome;
  private String email;
  private String senha;
  private String telefone;
  private char sexo;
  private boolean is_adm;
  private String idFaseAtual;

  public Usuario () {};
  
  public Usuario (String id, String username, String nome, String email, String senha, String telefone, char sexo, boolean is_adm) {
	setId(id);
    setUsername(username);
    setNome(nome);
    setEmail(email);
    setSenha(senha);
    setTelefone(telefone);
    setSexo(sexo);
    setIsAdm(is_adm);
  }
  
  public void setId(String id) {
	  this.id = id;
  }
  
  public String getId() {
	  return id;
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
  
  public void setIsAdm(boolean is_adm) {
	  this.is_adm = is_adm;
  }
  
  public boolean getIsAdm() {
	  return is_adm;
  }

  public void setIdFaseAtual(String idFaseAtual) {
    this.idFaseAtual = idFaseAtual;
  }

  public String getIdFaseAtual() {
    return idFaseAtual;
  }
}
