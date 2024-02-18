package modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Fase;
import model.Questao;

public class FaseDAO {
	DAO dao = new DAO();
	
	public FaseDAO() {}
	
	public ArrayList<Questao> getQuestoes(String id_fase) {
		QuestaoDAO questaoDAO = new QuestaoDAO();
		ArrayList<Questao> todasQuestoes = questaoDAO.getAllQuestoes();
		
		String sql = "SELECT id_questao FROM fase_questao WHERE id_fase = ?";
		
		try {
			Connection con = dao.conectar();
	        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        ((PreparedStatement) statement).setString(1, id_fase);
	        ResultSet resultSet = statement.executeQuery(sql);
	        ArrayList<Questao> questoes = new ArrayList<Questao>();
	        
	        while (resultSet.next()) {
	        	for (Questao questao : todasQuestoes) {
	        		if (questao.getId().equals(resultSet.getString("id_questao"))) questoes.add(questao);
	        	}
	        }
	        
	        con.close();
	        return questoes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Fase buscarFase(String id_fase) {
		Fase fase = new Fase();
		
		String sql = "SELECT assunto, xp_necessaria, xp_atual FROM fase WHERE id = ?";
		
		try {
			Connection con = dao.conectar();
	        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        ((PreparedStatement) statement).setString(1, id_fase);
	        ResultSet resultSet = statement.executeQuery(sql);
	        
	        fase.setId(id_fase);
	        fase.setQuestoes(this.getQuestoes(id_fase));
	        fase.setAssunto(resultSet.getString("assunto"));
	        fase.setXpAtual(resultSet.getInt("xp_atual"));
	        fase.setXpNecessaria(resultSet.getInt("xp_necessaria"));
	        
	        con.close();
	        return fase;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
