package base.entities;

public class PayPalTemp {

	private String address;
	private double total;
	private String customerNote;
		
	public PayPalTemp() {
		super();
	}
	public PayPalTemp(String address, double total, String customerNote) {
		super();
		this.address = address;
		this.total = total;
		this.customerNote = customerNote;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getCustomerNote() {
		return customerNote;
	}
	public void setCustomerNote(String customerNote) {
		this.customerNote = customerNote;
	}
}
