package main;

public class MyVehicle {
	private String id;
	private String name;
	private String category;
	private String reserved;
	public MyVehicle(String id,String name, String category, String reserved) {
		this.id=id;
		this.name = name;
		this.category = category;
		this.reserved = reserved;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
