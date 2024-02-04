package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import objetos.Aluno;

public class DAO {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3307/sistema_navi?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "Tykr0nn@123#";
	
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	// Cadastro
		public void inserirUsuario(Aluno aluno) {
			String sql = "INSERT INTO usuario_trilha (nome, email, username, telefone, sexo, senha) VALUES (?,?,?,?,?,?)";
			
			try {
				Connection con = conectar();
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
		
		// Login - validar usuário
		// Login - validar usuário
		public Aluno validarUsuario(Aluno aluno) {
		    String sql = "SELECT * FROM usuario_trilha WHERE email=? AND senha=?";
		    
		    try {
		        Connection con = conectar();
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

		            return foundAluno;
		        }
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return null;
		}

}
