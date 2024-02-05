package model;

public class Fase {
  private int xpNecessaria;
  private int xpAtual;
  private String assunto;
  private int id;

  public Fase (int xpNecessaria, int xpAtual, String assunto, int id) {
    setXpNecessaria(xpNecessaria);
    setXpAtual(xpAtual);
    setAssunto(assunto);
    setId(id);
  }

  public void setXpNecessaria(int xpNecessaria) {
    this.xpNecessaria = xpNecessaria;
  }
  
  public int getXpNecessaria() {
    return xpNecessaria;
  }
  
  public void setXpAtual(int xpAtual) {
    this.xpAtual = xpAtual;
  }
  
  public int getXpAtual() {
    return xpAtual;
  }
  
  public void setAssunto(String assunto) {
    this.assunto = assunto;
  }
  
  public String getAssunto() {
    return assunto;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public int getId() {
    return id;
  }
}
