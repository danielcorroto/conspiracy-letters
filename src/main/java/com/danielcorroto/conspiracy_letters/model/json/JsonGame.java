package com.danielcorroto.conspiracy_letters.model.json;

public class JsonGame {
	private Long id;
	private String name;
	private int ownPoints;

	public JsonGame(Long id, String name, int ownPoints) {
		super();
		this.id = id;
		this.name = name;
		this.ownPoints = ownPoints;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOwnPoints() {
		return ownPoints;
	}

	public void setOwnPoints(int ownPoints) {
		this.ownPoints = ownPoints;
	}

	@Override
	public String toString() {
		return "JsonGame [id=" + id + ", name=" + name + ", ownPoints=" + ownPoints + "]";
	}

}
