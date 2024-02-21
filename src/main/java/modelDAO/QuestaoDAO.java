package modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Alternativa;
import model.Questao;

public class QuestaoDAO {
	DAO dao = new DAO();

	public QuestaoDAO() {}
	
	public ArrayList<Questao> getAllQuestoes() {
		String sql = "SELECT q.id, q.enunciado, q.assunto, a.texto, a.is_correta, a.id_questao, a.id FROM Questao AS q JOIN Alternativa AS a ON q.id = a.id_questao";
		ArrayList<Questao> questoes = new ArrayList<>();

		try {
			Connection con = dao.conectar();
	        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        ResultSet resultSet = statement.executeQuery(sql);


	        while (resultSet.next()) {
	            Questao foundQuestao = new Questao();
	            foundQuestao.setId(resultSet.getString("q.id"));
	            foundQuestao.setEnunciado(resultSet.getString("q.enunciado"));
	            foundQuestao.setAssunto(resultSet.getString("q.assunto"));
	            ArrayList<Alternativa> alternativas = new ArrayList<>();
	            
	            do {
	            	Alternativa foundAlternativa = new Alternativa();
	                foundAlternativa.setId(resultSet.getString("a.id"));
	                foundAlternativa.setTexto(resultSet.getString("a.texto"));
	                foundAlternativa.setIsCorreta(resultSet.getBoolean("a.is_correta"));
	                alternativas.add(foundAlternativa);
	                
	                if (resultSet.next()) {
	                    if (!resultSet.getString("a.id_questao").equals(foundQuestao.getId())) {
	                        resultSet.previous();
	                        break;
	                    }
	                } else {
	                    break;
	                }
	            } while (true);
	            
	            foundQuestao.setAlternativas(alternativas);
	            questoes.add(foundQuestao);
	        }
	        
	        con.close();
	        return questoes;
		} catch (Exception e) {
	        e.printStackTrace();
	    }
		return null;
    }
	
	public boolean cadastrarQuestao(Questao questao) {
		String sql = "INSERT INTO Questao (enunciado, assunto) VALUES (?, ?)";
		int rowsAffected = 0;
		
		try {
			Connection con = dao.conectar();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, questao.getEnunciado());
			statement.setString(2, questao.getAssunto());
			rowsAffected = statement.executeUpdate();
			
			con.close();
			if (rowsAffected > 0) {
				if (cadastrarAlternativas(questao)) return rowsAffected > 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String getIdQuestao(Questao questao) {
		String sql = "SELECT id FROM Questao WHERE enunciado = ? AND assunto = ?";
		
		try {
			Connection con = dao.conectar();
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, questao.getEnunciado());
			pstm.setString(2, questao.getAssunto());
			ResultSet rs = pstm.executeQuery();
			
			if(rs.next() ) {
				return rs.getString("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean cadastrarAlternativas(Questao questao) {
		String idQuestao = this.getIdQuestao(questao);
		for (Alternativa opcao : questao.getAlternativas()) {
			String sql2 = "INSERT INTO Alternativa (texto, is_correta, id_questao) VALUES (?, ?, ?)";
			
			try {
				Connection con = dao.conectar();
				PreparedStatement statement2 = con.prepareStatement(sql2);
				statement2.setString(1, opcao.getTexto());
				statement2.setBoolean(2, opcao.getIsCorreta());
				statement2.setString(3, idQuestao);
				int rowsAffected = statement2.executeUpdate();
				
				con.close();
				if (rowsAffected == 0) return false;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
}
