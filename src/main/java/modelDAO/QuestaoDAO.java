package modelDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Alternativa;
import model.Questao;

public class QuestaoDAO {
	DAO dao = new DAO();

	public QuestaoDAO() {}
	
//	public void inserirUsuario(Aluno aluno) {
//		String sql = "INSERT INTO usuario_trilha (nome, email, username, telefone, sexo, senha) VALUES (?,?,?,?,?,?)";
//		
//		try {
//			Connection con = dao.conectar();
//			PreparedStatement pstm = con.prepareStatement(sql);
//			
//			pstm.setString(1, aluno.getNome());
//			pstm.setString(2, aluno.getEmail());
//			pstm.setString(3, aluno.getUsername());
//			pstm.setString(4, aluno.getTelefone());
//			pstm.setString(5, String.valueOf(aluno.getSexo()));
//			pstm.setString(6, aluno.getSenha());
//
//			pstm.execute();
//			con.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	public Aluno validarUsuario(Aluno aluno) {
//	    String sql = "SELECT * FROM usuario_trilha WHERE email=? AND senha=?";
//	    
//	    try {
//	        Connection con = dao.conectar();
//	        PreparedStatement pstm = con.prepareStatement(sql);
//	        
//	        pstm.setString(1, aluno.getEmail());
//	        pstm.setString(2, aluno.getSenha());
//	        
//	        ResultSet rs = pstm.executeQuery();
//	        
//	        if (rs.next()) {
//	            Aluno foundAluno = new Aluno();
//	            foundAluno.setEmail(rs.getString("email"));
//	            foundAluno.setSenha(rs.getString("senha"));
//	            foundAluno.setNome(rs.getString("nome"));
//	            foundAluno.setUsername(rs.getString("username"));
//	            String sexoStr = rs.getString("sexo");
//	            foundAluno.setSexo(!sexoStr.isEmpty() ? sexoStr.charAt(0) : ' ');
//	            foundAluno.setTelefone(rs.getString("telefone"));
//	            foundAluno.setNomeResponsavel(rs.getString("nome_do_responsavel"));
//	            foundAluno.setId(rs.getString("id"));
//	            foundAluno.setIsAdm(rs.getBoolean("is_adm"));
//
//	            return foundAluno;
//	        }
//	        
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//	    return null;
//	}
	
//	public boolean validarCadastro(Aluno aluno) {
//		String sql = "SELECT * FROM usuario_trilha WHERE username=? OR email=?";
//		try {
//			Connection con = dao.conectar();
//			PreparedStatement pstm = con.prepareStatement(sql);
//			
//			pstm.setString(1, aluno.getUsername());
//			pstm.setString(2, aluno.getEmail());
//
//			ResultSet rs = pstm.executeQuery();
//	        return rs.next();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return true;
//	}
	
//	public void updateUsuario(Aluno aluno) {
//		String sql = "UPDATE usuario_trilha SET nome = ?, email = ?, username = ?, telefone = ?, sexo = ?, senha = ? WHERE id = ?";
//		try {
//			Connection con = dao.conectar();
//			PreparedStatement pstm = con.prepareStatement(sql);
//			
//			pstm.setString(1, aluno.getNome());
//			pstm.setString(2, aluno.getEmail());
//			pstm.setString(3, aluno.getUsername());
//			pstm.setString(4, aluno.getTelefone());
//			pstm.setString(5, String.valueOf(aluno.getSexo()));
//			pstm.setString(6, aluno.getSenha());
//			pstm.setString(7, aluno.getId());
//			pstm.execute();
//			con.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	public String buscarId(Aluno aluno) {
//	    String sql = "SELECT id FROM usuario_trilha WHERE email = ?";
//	    try {
//	        Connection con = dao.conectar();
//	        PreparedStatement pstm = con.prepareStatement(sql);
//
//	        pstm.setString(1, aluno.getEmail());
//	        ResultSet rs = pstm.executeQuery();
//	        
//	        if (rs.next()) {
//	            String idRetornado = rs.getString("id");
//	            con.close();
//	            return idRetornado;
//	        }
//	        con.close();
//	        return null;
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
//	    return null;
//	}
	
	public ArrayList<Questao> getAllQuestoes() {
		String sql = "SELECT q.id, q.enunciado, a.texto, a.is_correta, a.id_questao, a.id FROM questao AS q JOIN alternativa AS a ON q.id = a.id_questao";
		ArrayList<Questao> questoes = new ArrayList<>();

		try {
			Connection con = dao.conectar();
	        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        ResultSet resultSet = statement.executeQuery(sql);


	        while (resultSet.next()) {
	            Questao foundQuestao = new Questao();
	            foundQuestao.setId(resultSet.getString("q.id"));
	            foundQuestao.setEnunciado(resultSet.getString("q.enunciado"));
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
}
