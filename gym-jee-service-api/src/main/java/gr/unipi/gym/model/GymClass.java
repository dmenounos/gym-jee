package gr.unipi.gym.model;

import java.io.Serializable;

public class GymClass implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String day;
	private String start;
	private String end;
	private GymProgram program;

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

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public GymProgram getProgram() {
		return program;
	}

	public void setProgram(GymProgram program) {
		this.program = program;
	}
}
