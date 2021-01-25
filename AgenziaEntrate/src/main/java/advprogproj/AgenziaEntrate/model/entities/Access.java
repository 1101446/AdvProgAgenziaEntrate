package advprogproj.AgenziaEntrate.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Access")
public class Access
{
    private long id;
    private String accessName;
    private int priority;
    private String description;
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
    
	public void setId(long id) {
		this.id = id;
	}
	
	@Column
	public String getAccessName() {
		return accessName;
	}
	
	public void setAccessName(String accessName) {
		this.accessName = accessName;
	}
	
	@Column
	public int getPriority() {
		return priority;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	@Column
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
    
}
