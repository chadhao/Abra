package Abra;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Utils {
	
	public static ArrayList<File> listAllFiles(String dir, ArrayList<File> files) {
		File directory = new File(dir);
		if (!directory.exists()) {
			return null;
		}
		
		File[] filesInDir = directory.listFiles();
		for (File file : filesInDir) {
			if (file.isDirectory()) {
				listAllFiles(file.getAbsolutePath(), files);
			} else if (file.isFile() && file.getName().endsWith(".jpg")) {
				files.add(file);
			}
		}
		
		return files;
	}
	
	public static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
	
}
