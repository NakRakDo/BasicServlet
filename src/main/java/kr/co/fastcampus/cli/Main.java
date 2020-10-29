package kr.co.fastcampus.cli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Main {

	private static Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws ClassNotFoundException {
		logger.info("Hello world!!!");

		Class.forName("org.h2.Driver");
		String url = "jdbc:h2:mem:test;MODE=MySQL;"; //메모리 에서 실행

		try (Connection connection = DriverManager.getConnection(url, "sa", "");
				Statement statement = connection.createStatement();) {

			connection.setAutoCommit(false);
			statement.execute("create table member(" + "id int auto_increment, username varchar(255) not null,"
					+ "password varchar(255) not null," + "primary key(id))");

			try {
				statement.executeUpdate("insert into member(username, password) values('kyunghun','1234')");
				connection.commit();
			} catch (Exception e) {
				connection.rollback();
			}
			ResultSet resultSet = statement.executeQuery("select id, username, password from member;");
			while (resultSet.next()) {
				Member member = new Member(resultSet);
				logger.info(member.toString());
				logger.info(member.getUsername());
			}

		} catch (SQLException e) {

		}
	}
}