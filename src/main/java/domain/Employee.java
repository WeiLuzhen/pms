package domain;

import java.io.Serializable;

/**
 * 实体Bean的特点：
 * （1）set和get方法遵循命名规则，即方法的名称由set/get及其
 *     属性的首字母大写的属性名称共同构成。
 * （2）JAVABean需要提供一个无参构造器
 */
public class Employee implements Serializable{
	private int id;
	private String name;
	private String job;
	private double s;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public double getSalary() {
		return s;
	}
	public void setSalary(double salary) { //salary
		this.s = salary;
	}
	
	
	/*
	public static void main(String[] args){
		Employee e1=new Employee();
		e1.setId(11);
		e1.setName("钱贵");
		e1.setJob("分析员");
		e1.setSalary(8000);
		
	}*/
}
