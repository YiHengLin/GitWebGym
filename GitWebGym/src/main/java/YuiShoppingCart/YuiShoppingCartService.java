package YuiShoppingCart;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import YuiEvent.YuiEventBean;

public class YuiShoppingCartService {
	private Map<Integer, YuiShoppingCartBean> cart = new LinkedHashMap< >();
	
	public YuiShoppingCartService(){
		
	}
	 
	public Map<Integer, YuiShoppingCartBean> getContent(){
		return cart;
	}
	
	public int getSize(){
		return cart.size();
	}
	
	public Boolean addToCart(int eventno, YuiShoppingCartBean scb){
		if ( cart.get(eventno) == null ) {
		    cart.put(eventno, scb);
		    return true;
		} else{
			return false;
		}
	}
	public double getSubtotal(){
		double subTotal = 0 ;
		Set<Integer> set = cart.keySet();
		for(int n : set){
			double price    = cart.get(n).getCharge();
			subTotal += price ;
		}
		return subTotal;
	}
	
	public int deleteEvent(int eventno){
		if(cart.get(eventno)!=null){
			cart.remove(eventno);
			return 1;
		}else{
			return 0;
		}
	}
	
}
