package samples;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TestTracking {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String number = "106-5762912-0818614";
			FileReader reader = new FileReader("tracking.json");
			JSONObject jsonData = (JSONObject) new JSONParser().parse(reader);
			JSONArray jArray = (JSONArray) jsonData.get(number);
			
			System.out.println(jArray.toArray()[1]);
			reader.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
