package telran.view.test;

import telran.view.*;

import static telran.view.Item.*;

public class DatesMenu {
public static Item getMenu() {
	Menu menu = new Menu(getItems(), "Dates operations");
	return menu;
}

private static Item[] getItems() {
	Item[] items = {
		of("Date after given days", io -> dateAfter(io)), of("Date before given days", io -> dateBefore(io)), 
		of("Number days between two dates", io -> daysBetweenDates(io)), exit()
			
	};
	return items;
}

private static void daysBetweenDates(InputOutput io) {
	// TODO Auto-generated method stub
	
}

private static void dateBefore(InputOutput io) {
	// TODO Auto-generated method stub
	
}

private static void dateAfter(InputOutput io) {
	// TODO Auto-generated method stub
	
}

}
