package com.numa.rainbow.items;

import com.numa.rainbow.items.CombinationKey;
public class CombinationKey {

	private ItemType type1;
	private ItemType type2;
	

	public CombinationKey(ItemType type1, ItemType type2) {
		this.type1=type1;
		this.type2=type2;
	}
	
	public int hashCode() {
		return type1.hashCode()+type2.hashCode();
	}

	public boolean equals(Object o) {
		if(o==null&&o.getClass()!=this.getClass()) {
			return false;
		}
		CombinationKey other = (CombinationKey)o;
		return ((this.type1==other.type1&&this.type2==other.type2)||(this.type1==other.type2&&this.type2==other.type1));
	}
	
	public ItemType getType1() {
		return type1;
	}

	public void setType1(ItemType type1) {
		this.type1 = type1;
	}

	public ItemType getType2() {
		return type2;
	}

	public void setType2(ItemType type2) {
		this.type2 = type2;
	}
}
