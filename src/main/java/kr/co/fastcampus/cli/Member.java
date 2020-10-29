package kr.co.fastcampus.cli;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


// d@NoArgsConstructor //인자가 없느 ㄴ생성자 -> 기본생성자
// d@AllArgsConstructor	// 인자가 모두 받는 생성자
// d@RequiredArgsConstructor //non null 어노테이션이 붙은 항목만 인자로 받는 생성자.
@Getter
@Setter
@ToString
public class Member {
	private int id;
	@NonNull private String username;
	@NonNull private String password;

	public Member(ResultSet rs) {
		try {
			this.id = rs.getInt("id");
			this.username = rs.getString("username");
			this.password = rs.getString("password");
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
}
