package server.player;

import java.util.HashMap;
import java.util.LinkedList;

import server.items.Item;

public class Inventory {

	private HashMap<String, LinkedList<Item>> _items;
	
	public Inventory(){
		_items = new HashMap<>();
	}
	
	/**
	 * This pulls the item from the inventory. Note: This removes the item!
	 * @param s 
	 * @return
	 */
	public Item removeItem(String s){
		if(_items.containsKey(s)){
			Item i = _items.get(s).poll();
			
			if(_items.get(s).isEmpty()){
				_items.remove(s);
			}
			
			return i; 
		}else{
			return null;
		}
	}
	
	public Item removeItem(String s, int index){
		if(_items.containsKey(s) && index < _items.get(s).size()){
			Item i = _items.get(s).get(index);
			
			if(_items.get(s).isEmpty()){
				_items.remove(s);
			}
			
			return i;
		}else{
			return null;
		}
	}
	
	public void putItem(Item i){
		if(_items.containsKey(i.getItemName())){
			_items.get(i.getItemName()).add(i);
		}else{
			_items.put(i.getItemName(), new LinkedList<>());
			_items.get(i.getItemName()).add(i);
		}
	}
	
	public Item getItem(String s){
		if(_items.containsKey(s)){
			return _items.get(s).get(0);
		}else{
			return null;
		}
	}
	
	public Item getItem(String s, int index){
		if(_items.containsKey(s)){
			return _items.get(s).get(index);
		}else{
			return null;
		}
	}
	
	public String getListOfItems(){
		StringBuilder sb = new StringBuilder("");
		
		for(String i : _items.keySet()){
			sb.append(_items.get(i).getFirst().getItemName() + "("+_items.get(i).size()+")");
		}
		
		return sb.toString();
	}
	
}
