package telran.view;

import java.time.LocalDate;
import java.util.function.Function;

public interface InputOutput {
	String readString(String prompt);
	void write(String str);
	default void writeLine(String str) {
		write(str + "\n");
		
	}
	default <T> T readObject(String prompt, String errorPrompt, Function<String, T> mapper) {
		boolean running = false;
		T res = null;
		do {
			try {
				String str = readString(prompt);
				res = mapper.apply(str);
				
			}catch(Exception e) {
				running = true;
				writeLine(errorPrompt + ": " + e.getMessage());
			}
		}while(running);
		return res;
	}
	default int readInt(String prompt, String errorPrompt) {
		return readObject(prompt, errorPrompt, Integer::parseInt);
	}
	default long readInt(String prompt, String errorPrompt, long min, long max) {
		return readObject(String.format("%s [%d-%d]", prompt, min, max), errorPrompt, str -> {
			long num = Long.parseLong(str);
			if(num < min || num > max) {
				throw new RuntimeException(String.format("Must be in the range [%d-%d]", min, max));
			}
			return num;
		});
	}
	default LocalDate readDate(String prompt, String errorPrompt) {
		return readObject(prompt, errorPrompt, LocalDate::parse);
	}
	default LocalDate readDate(String prompt, String errorPrompt, LocalDate from, LocalDate to) {
		return readObject(prompt, errorPrompt, str -> {LocalDate date = LocalDate.parse(str);
		if(date.compareTo(from) < 0 || date.compareTo(to) > 0) {
			throw new RuntimeException(String.format("date must be in the range [%s-%s]",from, to));
		}
		return date;
		});
	}

}
 