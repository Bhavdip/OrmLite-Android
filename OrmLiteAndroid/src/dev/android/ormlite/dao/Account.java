package dev.android.ormlite.dao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName ="account")
public class Account {
	
	public Account(){
		// all persisted classes must define a no-arg constructor
		// with at least package visibility
	}
	
	@DatabaseField(id = true)
	private int id;
	
	@DatabaseField(columnName="accountName")
	private String name;
	
	@DatabaseField(columnName="password")
	private String password;
	
	public String getName(){
		return name;
	}
	
	public String getPassword(){
		return password;
	}
	
	public Account setName(String name){
		this.name = name;
		return this;
	}
	
	public Account setPassword(String password){
		this.password = password;
		return this;
	}
	
}
