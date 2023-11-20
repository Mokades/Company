package telran.view.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import telran.view.*;
record Point(int x, int y) {
	
}

class InputOutputTest {
InputOutput io = new StandartInputOutput();
	@Disabled
	@Test
	void readPointTest() {
		Point point = io.readObject("Enter coordinates of point; usage <x>#<y>", "Wrong coordinates", str -> {
			String [] xy = str.split("#");
			if(xy.length != 2) {
				throw new RuntimeException("Incorrect format of input");
			}
			int x = Integer.parseInt(xy[0]);
			int y = Integer.parseInt(xy[1]);
			return new Point(x, y);
		});
		io.writeLine(point.x() + point.y() + "");
	}
	@Test
	void readObjectIntegerRange() {
		Integer number = io.readObject("Enter number in range [100-200]", "Wrong number", str -> {
			int num = Integer.parseInt(str);
			if(num < 100 || num > 200) {
				throw new RuntimeException("Must be in the range [100-200]");
			}
			return num;
		});
		io.writeLine(number / 2 + "");
		
	}
}
