package Objetos;

public class Questao {
  private int id;
  private String enunciado;

  public Questao (int id, String enunciado) {
    setId(id);
    setEnunciado(enunciado);
  }

  public void setId(int id) {
    this.id = id;
  }
  
  public int getId() {
    return id;
  }
  
  public void setEnunciado(String enunciado) {
    this.enunciado = enunciado;
  }
  
  public String getEnunciado() {
    return enunciado;
  }
}
