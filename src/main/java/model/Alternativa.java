package model;

public class Alternativa {
	private String id;
	private String texto;
	private String id_questao;
	private boolean is_correta;

	public Alternativa () {}

	public void setId(String id) {
		this.id = id;
	}
  
	public String getId() {
		return id;
	}
  
	public void setTexto(String texto) {
		this.texto = texto;
	}
  
	public String getTexto() {
		return texto;
	}
	
	public void setIdQuestao(String id_questao) {
		this.id_questao = id_questao;
	}
	
	public String getIdQuestao() {
		return id_questao;
	}
	
	public void setIsCorreta(boolean is_correta) {
		this.is_correta = is_correta;
	}
	
	public boolean getIsCorreta() {
		return is_correta;
	}
}
