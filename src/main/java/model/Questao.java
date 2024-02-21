package model;

import java.util.ArrayList;

public class Questao {
  private String id;
  private String enunciado;
  private String assunto;
  private ArrayList<Alternativa> alternativas;

  public Questao () {}

  public void setId(String id) {
    this.id = id;
  }
  
  public String getId() {
    return id;
  }
  
  public void setEnunciado(String enunciado) {
    this.enunciado = enunciado;
  }
  
  public String getEnunciado() {
    return enunciado;
  }
  
  public void setAssunto(String assunto) {
	  this.assunto = assunto;
  }
  
  public String getAssunto() {
	  return assunto;
  }
  
  public void setAlternativas(ArrayList<Alternativa> alternativas) {
	  this.alternativas = alternativas;
  }
  
  public void addAlternativa(Alternativa alternativa) {
	  this.alternativas.add(alternativa);
  }
  
  public ArrayList<Alternativa> getAlternativas() {
	  return alternativas;
  }
  
  public Alternativa getAlternativaCorreta() {
	  for (Alternativa opcao : alternativas) {
		  if (opcao.getIsCorreta()) return opcao;
	  }
	  return null;
  }
  
  public String getTextoAlternativaCorreta() {
	  Alternativa temp = getAlternativaCorreta();
	  return temp.getTexto();
  }
}
