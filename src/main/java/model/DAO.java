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
			String sql = "INSERT INTO usuario(nome, email, username, telefone, sexo, senha) VALUES (?,?,?,?,?,?)";
			
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
		public boolean validarUsuario(Aluno aluno) {
			String sql = "Select * From usuario WHERE username=? AND senha=?";
			
			try {
				Connection con = conectar();
				PreparedStatement pstm = con.prepareStatement(sql);
				
				pstm.setString(1, aluno.getUsername());
				pstm.setString(2, aluno.getSenha());
				
				ResultSet rs = pstm.executeQuery();
				
				return rs.next(); // Retorna true se encontrar o usuário e senha fornecidos
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
			
		}
}
