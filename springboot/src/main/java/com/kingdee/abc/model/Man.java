package com.kingdee.abc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "TB_MAN")
public class Man implements Serializable {
	
	private static final long serialVersionUID = 1L;
	  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Man_id")
    private Integer id;
    
    @Column(name="Man_name")
    private String name;
    
    @Column(name="Man_gender")
    private String gender;
    
    @Column(name="Man_age")
    private int age;
	
    public Man() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
    

}
