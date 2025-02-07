import java.util.Arrays;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;

public class DataProcess {
	public static void main(String[] args) {
		try {
			Scanner n_grams = new Scanner(new FileInputStream("../data/2gram.txt"));
			HashMap<String, WordFreq> map = new HashMap<String, WordFreq>();
			
			while (n_grams.hasNextLine()) {
				String[] params = n_grams.nextLine().split("\t+");
				//System.out.println(Arrays.toString(params));
				int freq = Integer.parseInt(params[0]);
				String first = params[1], second = params[2];



				if (map.containsKey(first)) {
					if (freq > map.get(first).freq) {
						map.put(first, new WordFreq(second, freq));
					}
				} else {
					map.put(first, new WordFreq(second, freq));
				}
				
			}

			FileWriter writer = new FileWriter(new File("../data/2gramOutput.txt"));
			for (String key : map.keySet()) {
				writer.write(key + " " + map.get(key).word + "\n");
			}
			writer.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}