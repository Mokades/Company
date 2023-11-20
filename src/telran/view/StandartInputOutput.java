package telran.view;

import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;




public class StandartInputOutput implements InputOutput {
private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	@Override
	public String readString(String prompt) {
		writeLine(prompt);
		String line = null;
		try {
			line = reader.readLine();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}

	@Override
	public void write(String str) {
		System.out.println(str);
		
	}
	

}
