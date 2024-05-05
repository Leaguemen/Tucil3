package State;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class StringCheck {
	private HashSet<String> container;
	private String fileName;

	public StringCheck(String _fileName) {
		fileName = _fileName;
		container = new HashSet<>();
		System.out.println("Attempting to read file from: " + new File(fileName).getAbsolutePath());
		try (BufferedReader br = new BufferedReader(new FileReader("../src/State/" + fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				container.add(line.toUpperCase());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isValidWord(String word) {
		return container.contains(word);
	}
}
