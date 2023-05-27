package com.kc.controller;

import java.util.ArrayList;
import java.util.Random;

import com.kc.dao.StudentCRUD;
import com.kc.dto.Student;
import java.util.Random;

public class Studentbatch {

	public static void main(String[] args) {
		
		Random random = new Random();
		ArrayList<Student> students = new ArrayList<Student>();
		for (int i = 1; i < 100; i++) {
			Student s = new Student();
			s.setId(i);
			s.setName("Student " + i);
			s.setMarks(random.nextInt(100));
		    
			students.add(s);
		}
		
		

		int[] arr = StudentCRUD.batchExecution(students);
		for (int a : arr) {
			System.out.println(a);
		}
		System.out.println("Done");

	}

}
