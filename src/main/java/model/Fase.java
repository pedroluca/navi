package model;

import java.util.ArrayList;

public class Fase {
  private int xpNecessaria;
  private int xpAtual;
  private String assunto;
  private String id;
  private ArrayList<Questao> questoes;
  
  public Fase () {}

  public Fase (int xpNecessaria, int xpAtual, String assunto, String id) {
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
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getId() {
    return id;
  }
  
  public void setQuestoes(ArrayList<Questao> questoes) {
	  this.questoes = questoes;
  }
  
  public ArrayList<Questao> getQuestoes() {
	  return questoes;
  }
}
