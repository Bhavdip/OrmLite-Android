package com.example.hellotwodbs;

import com.j256.ormlite.field.DatabaseField;

/**
 * Another demonstration object we are creating and persisting to the database.
 */
public class ComplexData {

	// id is generated by the database and set on the object automagically
	@DatabaseField(generatedId = true)
	int id;
	@DatabaseField
	long secs;
	@DatabaseField
	boolean odd;

	ComplexData() {
		// needed by ormlite
	}

	public ComplexData(long millis) {
		this.secs = millis / 1000;
		this.odd = ((this.secs % 2) == 1);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id=").append(id);
		sb.append(", ").append("secs=").append(secs);
		sb.append(", ").append("odd=").append(odd);
		return sb.toString();
	}
}