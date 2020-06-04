package main;

public class MyBooking {

	private String vid;
	private String vn;
	private String vc;
	private String bf;
	private String bt;
	public MyBooking(String vid, String vn, String vc, String bf, String bt) {
		
		this.vid = vid;
		this.vn = vn;
		this.vc = vc;
		this.bf = bf;
		this.bt = bt;
	}
	@Override
	public String toString() {
		return "MyBooking [vid=" + vid + ", vn=" + vn + ", vc=" + vc + ", bf=" + bf + ", bt=" + bt + "]";
	}
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public String getVn() {
		return vn;
	}
	public void setVn(String vn) {
		this.vn = vn;
	}
	public String getVc() {
		return vc;
	}
	public void setVc(String vc) {
		this.vc = vc;
	}
	public String getBf() {
		return bf;
	}
	public void setBf(String bf) {
		this.bf = bf;
	}
	public String getBt() {
		return bt;
	}
	public void setBt(String bt) {
		this.bt = bt;
	}
	
	
}
