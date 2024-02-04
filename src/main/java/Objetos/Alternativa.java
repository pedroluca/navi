package Objetos;

public class Alternativa {
  private int id;
  private String texto;
  private boolean isCorreta;
  private int idQuestao;

  public Alternativa (int id, String texto, boolean isCorreta, int idQuestao) {
    setId(id);
    setTexto(texto);
    setIsCorreta(isCorreta);
    setIdQuestao(idQuestao);
  }

  public void setId(int id) {
    this.id = id;
  }
  
  public int getId() {
    return id;
  }
  
  public void setTexto(String texto) {
    this.texto = texto;
  }
  
  public String getTexto() {
    return texto;
  }
  
  public void setIsCorreta(boolean isCorreta) {
    this.isCorreta = isCorreta;
  }
  
  public boolean getIsCorreta() {
    return isCorreta;
  }
  
  public void setIdQuestao(int idQuestao) {
    this.idQuestao = idQuestao;
  }
  
  public int getIdQuestao() {
    return idQuestao;
  }
}
