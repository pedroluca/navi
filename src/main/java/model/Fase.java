package model;

public class Fase {
  private int xpNecessaria;
  private String assunto;
  private String id;
  
  public Fase () {
  }

  public Fase (int xpNecessaria, String assunto, String id) {
    setXpNecessaria(xpNecessaria);
    setAssunto(assunto);
    setId(id);
  }

  public void setXpNecessaria(int xpNecessaria) {
    this.xpNecessaria = xpNecessaria;
  }
  
  public int getXpNecessaria() {
    return xpNecessaria;
  }
  
  public void setAssunto(String assunto) {
    this.assunto = assunto;
  }
  
  public String getAssunto() {
    return assunto;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getId() {
    return id;
  }
}
