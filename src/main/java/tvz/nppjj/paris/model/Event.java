package tvz.nppjj.paris.model;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "EVENT", schema = "PARIS")
public class Event {
	
	@Id
    @Column(name = "ID_EVENT")
    @GeneratedValue
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ADDRESS")
	private String location;

	@Column(name = "DATE")
	private Date date;

	// getters and setters
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
