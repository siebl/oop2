package ch.develop.w1repetition.exce1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
	public static ArrayList<Double> readList(String fileName)
			throws NumberFormatException, InvalidPathException, IOException {
		ArrayList<Double> list = new ArrayList<>();
		List<String> lines = Files.readAllLines(Paths.get(fileName));
		for (String line : lines) {
			double d = Double.parseDouble(line);
			list.add(d);
		}
		return list;
	}
}
