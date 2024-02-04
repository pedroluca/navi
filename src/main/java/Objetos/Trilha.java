package Objetos;

public class Trilha {
  private int id;
  private int idFaseAtual;

  public Trilha (int id, int idFaseAtual) {
    setId(id);
    setIdFaseAtual(idFaseAtual);
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getID() {
    return id;
  }

  public void setIdFaseAtual(int idFaseAtual) {
    this.idFaseAtual = idFaseAtual;
  }

  public int getIdFaseAtual() {
    return idFaseAtual;
  }
}
