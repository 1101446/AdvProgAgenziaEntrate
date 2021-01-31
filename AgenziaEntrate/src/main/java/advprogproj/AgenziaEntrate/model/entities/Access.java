package advprogproj.AgenziaEntrate.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "access")
public class Access implements Serializable{
    private long id;
    private String roleName;
    private int priority;
    private String description;
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ACCESS")
    public long getId() {
		return this.id;
	}
    
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "ROLE", nullable = false)
	public String getRoleName() {
		return this.roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Column(name = "PRIORITY", nullable = false)
	public int getPriority() {
		return this.priority;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	@Column
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
    
}
