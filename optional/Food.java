package main;

import java.io.Serializable;

public class Food implements Serializable {
	int itemNo;
	int quantity;
	float cost;

	protected Food(int itemno, int quantity) {
		this.itemNo = itemno;
		this.quantity = quantity;
		switch (itemNo) {
		case 1:
			cost = quantity * 25000;
			break;
		case 2:
			cost = quantity * 20000;
			break;
		case 3:
			cost = quantity * 15000;
			break;
		case 4:
			cost = quantity * 10000;
			break;
		}
	}
}