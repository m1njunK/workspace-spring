package com.bitc.filter.vo;

public class MemberVO {
	
	private String id;
	private String pw;
	private String name;

	public MemberVO() {
		System.out.println("기본 생성자 호출");
	}
	
	public MemberVO(String id, String name, String pw) {
		this.id = id;
		this.name = name;
		this.pw = pw;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		System.out.println("set id 호출 : " + id);
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		System.out.println("set pw 호출 : " + pw);
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		System.out.println("set name 호출 : " + name);
		this.name = name;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pw=" + pw + ", name=" + name + "]";
	}
	
}
