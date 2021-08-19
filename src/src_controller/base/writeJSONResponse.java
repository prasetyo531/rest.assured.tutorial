package base;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;

public class writeJSONResponse {

    public static String writeJsonResponse(String responseBody, String jsonData){
        try {
            FileWriter fileWriter = new FileWriter(jsonData);
            fileWriter.write(responseBody);
            fileWriter.flush();
            fileWriter.close();
            return "Success";
        } catch (Exception e){
            StringWriter error = new StringWriter();
            e.printStackTrace(new PrintWriter(error));
            return error.toString();
        }
    }
}
