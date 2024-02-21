package model;

public class FaseAvaliativa extends Fase {
  public FaseAvaliativa (int xpNecessaria, int xpAtual, String assunto, String id) {
    super(xpNecessaria, assunto, id);
  }

  public double CalcularXP(int xp) {
    return 0;
  }

}
