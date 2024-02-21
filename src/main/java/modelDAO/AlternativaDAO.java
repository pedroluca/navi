package modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Alternativa;

public class AlternativaDAO {
	DAO dao = new DAO();
	
	public AlternativaDAO() {}
	
	public Alternativa buscarAlternativa(String id_alternativa) {
		String sql = "SELECT * FROM Alternativa WHERE id = ?";
		
		try {
			Connection con = dao.conectar();
			PreparedStatement statement = con.prepareStatement(sql);
	        statement.setString(1, id_alternativa);
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
				Alternativa opcao = new Alternativa();
				opcao.setId(id_alternativa);
				opcao.setTexto(rs.getString("texto"));
				opcao.setIsCorreta(rs.getBoolean("is_correta"));
				opcao.setIdQuestao(rs.getString("id_questao"));
				return opcao;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
