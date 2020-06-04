package main;

public class MyRent {
	
	
	private String id;
	private String uid;
	private String vid;
	private String bookingfrom;
	private String bookingtill;
	private String amount;
	public MyRent(String id, String uid, String vid, String bookingfrom, String bookingtill,String amount) {
		this.id = id;
		this.uid = uid;
		this.vid = vid;
		this.bookingfrom = bookingfrom;
		this.bookingtill = bookingtill;
		this.amount=amount;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public String getBookingfrom() {
		return bookingfrom;
	}
	public void setBookingfrom(String bookingfrom) {
		this.bookingfrom = bookingfrom;
	}
	public String getBookingtill() {
		return bookingtill;
	}
	public void setBookingtill(String bookingtill) {
		this.bookingtill = bookingtill;
	}
	
	
}
