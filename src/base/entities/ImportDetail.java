package base.entities;
// Generated Feb 2, 2018 11:45:13 AM by Hibernate Tools 5.2.8.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ImportDetail generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ImportDetail", catalog = "HighTechStore")
public class ImportDetail implements java.io.Serializable {

	private ImportDetailId id;
	private Imports imports;
	private Product product;
	private Supplier supplier;
	private double price;
	private int quantity;

	public ImportDetail() {
	}

	public ImportDetail(ImportDetailId id, Imports imports, Product product, Supplier supplier, double price,
			int quantity) {
		this.id = id;
		this.imports = imports;
		this.product = product;
		this.supplier = supplier;
		this.price = price;
		this.quantity = quantity;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "importId", column = @Column(name = "ImportID", nullable = false)),
			@AttributeOverride(name = "productId", column = @Column(name = "ProductID", nullable = false)) })
	public ImportDetailId getId() {
		return this.id;
	}

	public void setId(ImportDetailId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ImportID", nullable = false, insertable = false, updatable = false)
	public Imports getImports() {
		return this.imports;
	}

	public void setImports(Imports imports) {
		this.imports = imports;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ProductID", nullable = false, insertable = false, updatable = false)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Supplier", nullable = false)
	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	@Column(name = "Price", nullable = false, precision = 15, scale = 0)
	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name = "Quantity", nullable = false)
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
