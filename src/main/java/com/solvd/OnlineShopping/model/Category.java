package com.solvd.OnlineShopping.model;

import com.solvd.OnlineShopping.model.enums.Department;
import com.solvd.OnlineShopping.model.enums.Group;

public class Category {

	private int id;
	private Department department;
	private Group grouping;
	
	
	
	public Category() {}
	public Category(int id, Department department, Group grouping) {
		super();
		this.id = id;
		this.department = department;
		this.grouping = grouping;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Group getGrouping() {
		return grouping;
	}
	public void setGrouping(Group grouping) {
		this.grouping = grouping;
	}
	
	
	
}
