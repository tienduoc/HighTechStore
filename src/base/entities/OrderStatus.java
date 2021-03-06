package base.entities;
// Generated Feb 2, 2018 11:45:13 AM by Hibernate Tools 5.2.8.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * OrderStatus generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "OrderStatus", catalog = "HighTechStore")
public class OrderStatus implements java.io.Serializable {

	private int id;
	private String name;
	private boolean status;
	private Set<Orders> orderses = new HashSet<Orders>(0);

	public OrderStatus() {
	}

	public OrderStatus(int id, String name, boolean status) {
		this.id = id;
		this.name = name;
		this.status = status;
	}

	public OrderStatus(int id, String name, boolean status, Set<Orders> orderses) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.orderses = orderses;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "Name", nullable = false, length = 250)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Status", nullable = false)
	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderStatus")
	public Set<Orders> getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set<Orders> orderses) {
		this.orderses = orderses;
	}

}
