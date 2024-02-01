package dto;

public class ShoppingDto {
//mypage 와 shop table을 join하여 찾을 정보
	
	private int num=0;
	private String goods=null;
	private int price=0;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getGoods() {
		return goods;
	}
	public void setGoods(String goods) {
		this.goods = goods;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
