package com.zhanhongit.model;

import javax.validation.constraints.Size;

public class Person {
	private Long id;
	private String city;
	private String area;
	private String school;
	private String grade;
	private String bj;
	@Size(max = 4, min = 2)
	private String name;
	private String teacher;
	private String teacher_tel;
	private String patriarch;
	private String patriarch_tel;
	private String zkzh;
	private Boolean state;
	private String kc;
	private String zs;
	private String kd;
	
	public String getKd() {
		return kd;
	}

	public void setKd(String kd) {
		this.kd = kd;
	}

	public String getKc() {
		return kc;
	}

	public void setKc(String kc) {
		this.kc = kc;
	}

	public String getZs() {
		return zs;
	}

	public void setZs(String zs) {
		this.zs = zs;
	}

	public Person() {

	}

	
	public String getArea() {
		return area;
	}

	public String getBj() {
		return bj;
	}

	public String getCity() {
		return city;
	}

	public String getGrade() {
		return grade;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPatriarch() {
		return patriarch;
	}

	public String getPatriarch_tel() {
		return patriarch_tel;
	}

	public String getSchool() {
		return school;
	}

	public String getTeacher() {
		return teacher;
	}

	public String getTeacher_tel() {
		return teacher_tel;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setBj(String bj) {
		this.bj = bj;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPatriarch(String patriarch) {
		this.patriarch = patriarch;
	}

	public void setPatriarch_tel(String patriarch_tel) {
		this.patriarch_tel = patriarch_tel;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public void setTeacher_tel(String teacher_tel) {
		this.teacher_tel = teacher_tel;
	}

	public String getZkzh() {
		return zkzh;
	}

	public void setZkzh(String zkzh) {
		this.zkzh = zkzh;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	

	public Person(Long id, String city, String area, String school, String grade, String bj, String name,
			String teacher, String teacher_tel, String patriarch, String patriarch_tel, String zkzh, Boolean state,
			String kc, String zs, String kd) {
		super();
		this.id = id;
		this.city = city;
		this.area = area;
		this.school = school;
		this.grade = grade;
		this.bj = bj;
		this.name = name;
		this.teacher = teacher;
		this.teacher_tel = teacher_tel;
		this.patriarch = patriarch;
		this.patriarch_tel = patriarch_tel;
		this.zkzh = zkzh;
		this.state = state;
		this.kc = kc;
		this.zs = zs;
		this.kd = kd;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", city=" + city + ", area=" + area + ", school=" + school + ", grade=" + grade
				+ ", bj=" + bj + ", name=" + name + ", teacher=" + teacher + ", teacher_tel=" + teacher_tel
				+ ", patriarch=" + patriarch + ", patriarch_tel=" + patriarch_tel + ", zkzh=" + zkzh + ", state="
				+ state + ", kc=" + kc + ", zs=" + zs + "]";
	}

	
}
