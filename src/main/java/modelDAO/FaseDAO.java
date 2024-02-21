package modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Fase;
import model.Questao;

public class FaseDAO {
	DAO dao = new DAO();
	
	public FaseDAO() {}
	
	public ArrayList<Questao> getQuestoes(Fase fase) {
		QuestaoDAO questaoDAO = new QuestaoDAO();
		ArrayList<Questao> todasQuestoes = questaoDAO.getAllQuestoes();
		
		String sql = "SELECT id FROM Questao WHERE assunto = ?;";
		
		try {
			Connection con = dao.conectar();
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setString(1, fase.getAssunto());
	        ResultSet resultSet = statement.executeQuery();
	        ArrayList<Questao> questoes = new ArrayList<Questao>();
	        
	        while (resultSet.next()) {
	        	for (Questao questao : todasQuestoes) {
	        		if (questao.getId().equals(resultSet.getString("id"))) questoes.add(questao);
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
		
		String sql = "SELECT assunto, xp_necessaria, id FROM Fase WHERE id = ?;";
		
		try {
			Connection con = dao.conectar();
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setString(1, id_fase);
	        ResultSet resultSet = statement.executeQuery();
	        
	        if (resultSet.next()) {
	        	fase.setId(id_fase);
		        fase.setAssunto(resultSet.getString("assunto"));
		        fase.setXpNecessaria(resultSet.getInt("xp_necessaria"));
		        fase.setId(resultSet.getString("id"));
	        }
	        
	        con.close();
	        return fase;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
