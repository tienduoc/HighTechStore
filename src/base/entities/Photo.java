package base.entities;
// Generated Feb 2, 2018 11:45:13 AM by Hibernate Tools 5.2.8.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Photo generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Photo", catalog = "HighTechStore")
public class Photo implements java.io.Serializable {

	private int id;
	private Product product;
	private String url;
	private boolean main;

	public Photo() {
	}

	public Photo(int id, Product product, String url, boolean main) {
		this.id = id;
		this.product = product;
		this.url = url;
		this.main = main;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ProductID", nullable = false)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "Url", nullable = false)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "Main", nullable = false)
	public boolean isMain() {
		return this.main;
	}

	public void setMain(boolean main) {
		this.main = main;
	}

}
