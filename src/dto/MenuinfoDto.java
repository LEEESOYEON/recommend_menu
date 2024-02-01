package dto;

import java.util.ArrayList;

public class MenuinfoDto {

	private String menuname=null;
	private String mainstuff=null;
	//ArrayList<String> mainstuff=new ArrayList<>();
	private String sidestuff=null;
	//ArrayList<String> sidestuff=new ArrayList<>();
	private String cook=null;
	private String foodImage=null;
	
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getMainstuff() {
		return mainstuff;
	}
	public void setMainstuff(String mainstuff) {
		this.mainstuff = mainstuff;
	}
	public String getSidestuff() {
		return sidestuff;
	}
	public void setSidestuff(String sidestuff) {
		this.sidestuff = sidestuff;
	}
	public String getCook() {
		return cook;
	}
	public void setCook(String cook) {
		this.cook = cook;
	}
	public String getFoodImage() {
		return foodImage;
	}
	public void setFoodImage(String foodImage) {
		this.foodImage = foodImage;
	}
	
	
}
