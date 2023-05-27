package com.kc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kc.dto.Student;

public class StudentCRUD {

	private final static String path = "com.mysql.cj.jdbc.Driver";
	private final static String url = "jdbc:mysql://localhost:3306/student_details?user=root";

	public static int[] batchExecution(ArrayList<Student> students) {

		String query = "insert into student values(?,?,?)";
		Connection c = null;

		try {
			Class.forName(path);
			c = DriverManager.getConnection(url);
			PreparedStatement ps = c.prepareStatement(query);
			for (Student s : students) {
				ps.setInt(1, s.getId());
				ps.setString(2, s.getName());
				ps.setInt(3, s.getMarks());

				ps.addBatch();
			}
			return ps.executeBatch();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	public ArrayList<Student> getAllStudents(int page, Integer min, Integer max) {
		ArrayList<Student> al = new ArrayList<Student>();

		String query = "SELECT * FROM student LIMIT ?, ?";
		if (min != null && max != null && max != 0) {
			query = "SELECT * FROM student WHERE marks BETWEEN ? AND ?";
		}

		Connection c = null;

		try {
			Class.forName(path);
			c = DriverManager.getConnection(url);
			PreparedStatement ps = c.prepareStatement(query);

			if (min != null && max != null && max != 0) {
				ps.setInt(1, min);
				ps.setInt(2, max);
			} else {
				int startIndex = (page - 1) * 10;
				ps.setInt(1, startIndex);
				ps.setInt(2, 10);
			}

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setMarks(rs.getInt(3));

				al.add(s);
			}
			return al;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return al;

	}

}
