package Entity;

/**
 * Created by CiCi on 2017/2/27.
 */
public class Items {
	private int id;
	private String name;
	private String city;
	private int price;
	private int number;
	private String picture;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getPicture() {
		return picture;
	}
	
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o instanceof Items) {
			Items items = (Items) o;
			if (items.getId() == this.getId() && items.getName().equals(this.getName())) {
				return true;
			}
			else
				return false;
		}
		else {
			return false;
		}
	
		
	}
	
	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}
	
}
