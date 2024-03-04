package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DAO {
	
	//parametros de conexao
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone = true&serverTimezone=UTC";
	private String user = "root";
	private String password = "admin";
	
	//metodo de conexao
	private Connection conectar() {
		Connection conn = null;
		
		try {
			Class.forName(driver);//vai ler o driver
			conn = DriverManager.getConnection(url, user, password);
			return conn;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contato (nome, fone, email) values (?,  ?, ?)";
		
		try {
			//abre a conexão
			Connection con = conectar();
			
			//prepare a query para o banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			
			//colocando as variáveis
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			
			pst.executeUpdate();
			
			con.close();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
