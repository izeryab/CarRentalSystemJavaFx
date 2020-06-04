package main;

public class MyHistory {
	String id;
	String name;
	String bookingfrom;
	String Bookingtill;
	String amount;
	public MyHistory(String id, String name, String bookingfrom, String bookingtill, String date) {
		this.id = id;
		this.name = name;
		this.bookingfrom = bookingfrom;
		Bookingtill = bookingtill;
		this.amount = date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBookingfrom() {
		return bookingfrom;
	}
	public void setBookingfrom(String bookingfrom) {
		this.bookingfrom = bookingfrom;
	}
	public String getBookingtill() {
		return Bookingtill;
	}
	public void setBookingtill(String bookingtill) {
		Bookingtill = bookingtill;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String date) {
		this.amount = date;
	}
	

}
