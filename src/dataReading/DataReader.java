
package main;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class DataReader {
	
	protected Scanner inputStream = null;
	
	public DataReader() {
		
	}
	
	public void openInputStream (String path) {
		try {
			inputStream = new Scanner(new FileInputStream(path));
		} catch(FileNotFoundException e) {
			System.out.println("Failed to open " + path + "!!\n");
			System.exit(1);
		}
	}
	
	public void closeInputStream () {
    	inputStream.close();
	}
	
}
