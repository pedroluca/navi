package model;

public class FaseAvaliativa extends Fase {
  public FaseAvaliativa (int xpNecessaria, int xpAtual, String assunto, String id) {
    super(xpNecessaria, xpAtual, assunto, id);
  }

  public double CalcularXP(int xp) {
    return 0;
  }

  //provavelmente um método para avançar o aluno, pois o calcular xp também deve ter na fase superclasse
}
