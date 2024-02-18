package modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Aluno;

public class UsuarioDAO {
	DAO dao = new DAO();
	
	public UsuarioDAO() {}
	
	public void inserirUsuario(Aluno aluno) {
		String sql = "INSERT INTO usuario_trilha (nome, email, username, telefone, sexo, senha) VALUES (?,?,?,?,?,?)";
		
		try {
			Connection con = dao.conectar();
			PreparedStatement pstm = con.prepareStatement(sql);
			
			pstm.setString(1, aluno.getNome());
			pstm.setString(2, aluno.getEmail());
			pstm.setString(3, aluno.getUsername());
			pstm.setString(4, aluno.getTelefone());
			pstm.setString(5, String.valueOf(aluno.getSexo()));
			pstm.setString(6, aluno.getSenha());

			pstm.execute();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Aluno validarUsuario(Aluno aluno) {
	    String sql = "SELECT * FROM usuario_trilha WHERE email=? AND senha=?";
	    
	    try {
	        Connection con = dao.conectar();
	        PreparedStatement pstm = con.prepareStatement(sql);
	        
	        pstm.setString(1, aluno.getEmail());
	        pstm.setString(2, aluno.getSenha());
	        
	        ResultSet rs = pstm.executeQuery();
	        
	        if (rs.next()) {
	            Aluno foundAluno = new Aluno();
	            foundAluno.setEmail(rs.getString("email"));
	            foundAluno.setSenha(rs.getString("senha"));
	            foundAluno.setNome(rs.getString("nome"));
	            foundAluno.setUsername(rs.getString("username"));
	            String sexoStr = rs.getString("sexo");
	            foundAluno.setSexo(!sexoStr.isEmpty() ? sexoStr.charAt(0) : ' ');
	            foundAluno.setTelefone(rs.getString("telefone"));
	            foundAluno.setNomeResponsavel(rs.getString("nome_do_responsavel"));
	            foundAluno.setId(rs.getString("id"));
	            foundAluno.setIsAdm(rs.getBoolean("is_adm"));

	            return foundAluno;
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public boolean validarCadastro(Aluno aluno) {
		String sql = "SELECT * FROM usuario_trilha WHERE username=? OR email=?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pstm = con.prepareStatement(sql);
			
			pstm.setString(1, aluno.getUsername());
			pstm.setString(2, aluno.getEmail());

			ResultSet rs = pstm.executeQuery();
	        return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean updateUsuario(Aluno aluno) {
		String sql = "UPDATE usuario_trilha SET nome = ?, email = ?, username = ?, telefone = ?, sexo = ?, senha = ? WHERE id = ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pstm = con.prepareStatement(sql);
			
			pstm.setString(1, aluno.getNome());
			pstm.setString(2, aluno.getEmail());
			pstm.setString(3, aluno.getUsername());
			pstm.setString(4, aluno.getTelefone());
			pstm.setString(5, String.valueOf(aluno.getSexo()));
			pstm.setString(6, aluno.getSenha());
			pstm.setString(7, aluno.getId());
			pstm.execute();
			con.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String buscarId(Aluno aluno) {
	    String sql = "SELECT id FROM usuario_trilha WHERE email = ?";
	    try {
	        Connection con = dao.conectar();
	        PreparedStatement pstm = con.prepareStatement(sql);

	        pstm.setString(1, aluno.getEmail());
	        ResultSet rs = pstm.executeQuery();
	        
	        if (rs.next()) {
	            String idRetornado = rs.getString("id");
	            con.close();
	            return idRetornado;
	        }
	        con.close();
	        return null;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public ArrayList<Aluno> getAllAlunos() {
		String sql = "SELECT id, nome, email, telefone, sexo FROM usuario_trilha";

		try {
			Connection con = dao.conectar();
	        PreparedStatement statement = con.prepareStatement(sql);
	        ResultSet resultSet = statement.executeQuery();

	        ArrayList<Aluno> alunos = new ArrayList<>();

	        while (resultSet.next()) {
	            Aluno foundAluno = new Aluno();
	            foundAluno.setId(resultSet.getString("id"));
	            foundAluno.setNome(resultSet.getString("nome"));
	            foundAluno.setEmail(resultSet.getString("email"));
	            foundAluno.setTelefone(resultSet.getString("telefone"));
	            String sexoStr = resultSet.getString("sexo");
	            foundAluno.setSexo(!sexoStr.isEmpty() ? sexoStr.charAt(0) : ' ');

	            alunos.add(foundAluno);
	        }

	        con.close();
	        return alunos;
		} catch (Exception e) {
	        e.printStackTrace();
	    }

        return null;
    }
}
