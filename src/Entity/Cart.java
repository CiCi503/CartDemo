package Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by CiCi on 2017/3/1.
 * 实体类会提供一些方法，但是这些方法和前端传来的参数是完全无关的，是纯Java代码
 */
public class Cart {
	private HashMap<Items,Integer> goods ;
	double totalPrice;
	Items test;
	
	public Cart() {
		goods = new HashMap<Items,Integer>();
		totalPrice = 0.0;
	}
	
	public HashMap<Items, Integer> getGoods() {
		return goods;
	}
	
	public void setGoods(HashMap<Items, Integer> goods) {
		this.goods = goods;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public boolean addIntoCart(Items items,Integer num) {
		System.out.println(items);
		System.out.println();
		System.out.println(items.equals(test));
		if (goods.containsKey(items)) {
			System.out.println("包含相同元素");
			goods.put( items,goods.get(items)+num);
		}
		else {
			System.out.println("else");
			goods.put(items,num);
		}
		Set<Items> set = goods.keySet();
		Iterator iterator = set.iterator();
		System.out.println("*****输出goods中元素");
		while (iterator.hasNext()) {
			Items i = (Items) iterator.next();
			System.out.println(i);
		}
		System.out.println("*****输出goods中元素结束");
		calTotalPrice();
		test = items;
		return true;

	}
//这个删除就是删除购物车里的这个商品,通过id来删除购物车中的商品。如果传来的参数是Items类型的，可能会出现删除的对象和要删除的对象虽然是一个名字但是不是同一对象的情况
	public boolean removeGoodsFromCart(int id) {
		Set<Items> set = goods.keySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			Items items = (Items)iterator.next();
			if (items.getId() == id) {
				goods.remove(items);
				calTotalPrice();
				return true;
			}
			
		}
		return false;
		
//		System.out.println("---------------------在Cart里输出内容开始");
//		System.out.println("有没有获取到要删除啥" + item.getName());
//		this.goods.remove(item);
//		calTotalPrice();
//		System.out.println("************delete方法执行");
//		System.out.println(item);
//
//		Set<Items> set = goods.keySet();
//		Iterator iterator = set.iterator();
//		while (iterator.hasNext()) {
//			Items items = (Items)iterator.next();
//			System.out.println(items);
//
//		}
//		System.out.println("---------------------在Cart里输出内容结束");
//		return true;
//
		

	}
	public void calTotalPrice() {
		double sum = 0.0;
		Set keys = goods.keySet(); //将所有的键放到这个Set中
		Iterator<Items> itemsIterator = keys.iterator();
		while (itemsIterator.hasNext())
		{
			Items i = itemsIterator.next();
			sum = sum + i.getPrice()*goods.get(i);
		}
		setTotalPrice(sum);
		

	}
	
	public static void main(String[] args) {
		Cart cart = new Cart();
		Items items1 = new Items();
		Items items2 = new Items();
		Items items3 = new Items();
		items1.setId(1);
		items1.setName("items1");
		items1.setPrice(100);
		items3.setId(1);
		items3.setName("items1");
		items3.setPrice(100);
		items2.setId(2);
		items2.setName("items2");
		items2.setPrice(1);
		cart.addIntoCart(items1,1);
		System.out.println(items1.equals(items3));
		System.out.println(cart.getGoods().containsKey(items3));
		cart.addIntoCart(items2,2);
		cart.addIntoCart(items3,2);
		System.out.println(cart.getTotalPrice());
		Set<Items> keySet= cart.getGoods().keySet();
		Iterator i = keySet.iterator();
		while (i.hasNext()) {
			Items items = (Items)i.next();
			System.out.println(items.getName() + " " + cart.getGoods().get(items));
			
			
		}
//		cart.removeGoodsFromCart(2);
//
//		System.out.println();
//		keySet= cart.getGoods().keySet();
//		i = keySet.iterator();
//		while (i.hasNext()) {
//			Items items = (Items)i.next();
//			System.out.println(items.getName());
//
//
//		}
//		System.out.println(cart.getTotalPrice());
	}
}
