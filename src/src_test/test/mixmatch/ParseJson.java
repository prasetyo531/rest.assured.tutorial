package test.mixmatch;

import base.base;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ParseJson extends base {

        static String inline = "";
        static Map params = new LinkedHashMap<>();

        public static void main(String[] args) throws IOException, JSONException, ParseException {

                URL url = new URL("https://api.femaledaily.com/app/v1/feed/364623");
                params.put("version", "1.5");
                params.put("device", "1");
                params.put("key", "client01-pb3TVYisCGurD08ks3YW");
                params.put("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ2ZXJzaW9uIjoiMS41IiwiZGV2aWNlIjoiMyIsImtleSI6ImNsaWVudDAyLVd2Qk5NTnVWQm9lTEF2UmRja3NyIiwicG9zdCI6eyJ1c2VyX2lkIjo0OTA1NSwidXNlcm5hbWUiOiJwdXR3aWQiLCJmdWxsbmFtZSI6IlV0d2lkIiwiZW1haWwiOiJmYXpsdXIuZi5yYWhtYW5AZ21haWwuY29tIn0sImlhdCI6MTU2ODcyMjIyMjExMn0.A1fiOgpXf_JBXaBUXheIeMaweFBLE08AfzE4TzWGCHc");

                //Parse URL into HttpURLConnection in order to open the connection in order to get the JSON data
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //Set the request to GET or POST as per the requirements
                conn.setRequestMethod("GET");
                //Use the connect method to create the connection bridge
                conn.connect();
                //Get the response status of the Rest API
                Integer responsecode = conn.getResponseCode();
                System.out.println("Response code is: " + responsecode);

                //Iterating condition to if response code is not 200 then throw a runtime exception
                //else continue the actual process of getting the JSON data
                if (responsecode != 200)
                        throw new RuntimeException("HttpResponseCode: " + responsecode);
                else {
                        //Scanner functionality will read the JSON data from the stream
                        Scanner sc = new Scanner(url.openStream());
                        while (sc.hasNext()) {
                                inline += sc.nextLine();
                        }
                        System.out.println("\nJSON Response in String format");
                        System.out.println(inline);
                        //Close the stream when reading the data has been finished
                        sc.close();
                }
                JSONParser parse = new JSONParser();
                //Type caste the parsed json data in json object
                JSONObject jobj = (JSONObject)parse.parse(inline);
                //Store the JSON object in JSON array as objects (For level 1 array element i.e Results)
                JSONArray jsonarr_1 = (JSONArray) jobj.get("data");
                for(int i=0;i<jsonarr_1.size();i++)
                {
                        JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(i);
                        //Store the data as String objects
                        Long str_data1 = (Long) jsonobj_1.get("id");
                        System.out.println(str_data1);
                        String str_data2 = (String) jsonobj_1.get("caption");
                        System.out.println(str_data2);
                        System.out.println(jsonobj_1.get("type"));
                        System.out.println("\n");
                }
        }

}

