package com.codSoft;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


//This is an Entity Class
@Entity
@Table(name="Students")
public class Student {

	//Fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public String name;
    public int age;
    public String subject;
    public String collegeName;
    public String address;
	
	//Constructors
	public Student(String name, int age, int rollNo, String address, String subject, String collegeName) {
		super();
		this.name = name;
		this.age = age;
		this.id = rollNo;
		this.address = address;
		this.collegeName = collegeName;
		this.subject= subject;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	//Getters Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getRollNo() {
		return id;
	}

	public void setRollNo(int rollNo) {
		this.id = rollNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	
	
	
}
