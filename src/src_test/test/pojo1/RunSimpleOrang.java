package test.pojo1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class RunSimpleOrang {

    public static String url_user2= "http://reqres.in/api/users/2";
    public static String url_user3= "http://reqres.in/api/users/3";

    public static File xmlOutput;
    public static String filePath = System.getProperty("user.dir")+"/src_test/test/pojo1/";

    public static Integer id;
    public static String email;
    public static String first_name;
    public static String last_name;
    public static String avatar;
    public static String url;
    public static String text;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        //user 3
        responseUser(url_user3);

        serializeToXML("user-3");
        deserializeFromXML("user-3");

        serializeToJSON("user-3");
        deserializeFromJSON("user-3");

        //user 3
        responseUser(url_user2);

        serializeToXML("user-2");
        deserializeFromXML("user-2");

        serializeToJSON("user-2");
        deserializeFromJSON("user-2");

        compare("user-2","user-3","xml");
        compare("user-2","user-2","json");
    }

    public static void responseUser(String urlapi) {
        // TODO Auto-generated method stub
        //BaseURL or Host
        //RestAssured.baseURI = "http://reqres.in/api";
        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.get(urlapi);
        Assert.assertEquals(response.getStatusCode(), 200);

        ResponseBody body = response.getBody();
        System.out.println("response is" + ((ResponseBody) body).prettyPrint());

        //using pojo classes
        //to make content-type as json , parser.json
        Data dt = given().expect().defaultParser(Parser.JSON).
                when().
                get(urlapi).as(Data.class);

        //grab value
        id = dt.getData().getId();
        email = dt.getData().getEmail();
        first_name = dt.getData().getFirst_name();
        last_name = dt.getData().getLast_name();
        avatar = dt.getData().getAvatar();
        url = dt.getSupport().getUrl();
        text = dt.getSupport().getText();

        System.out.println(dt.getData().getFirst_name());
        System.out.println(dt.getSupport().getUrl());
    }

    public static void serializeToXML(String filename) {
        try {
            //https://stackabuse.com/serialize-and-deserialize-xml-in-java-with-jackson/
            //serialize to xml
            XmlMapper xmlMapper = new XmlMapper();

            //write the nested object first
            Support sto = new Support(url,text);
            //then write parent object
            String xmlString = xmlMapper.writeValueAsString(new Data(id,email,first_name,last_name,avatar, sto));

            ObjectMapper om = new ObjectMapper();
            //remove double quotes
            String s = om.writeValueAsString(xmlString).replace("\"", "");
            System.out.println(s);

            xmlOutput = new File(filePath+filename+".xml");
            FileWriter fileWriter = new FileWriter(xmlOutput);
            fileWriter.write(s);
            fileWriter.close();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserializeFromXML(String filename) {
        try {
            XmlMapper xmlMapper = new XmlMapper();

            // read file to string
            String readContent = new String(Files.readAllBytes(Paths.get(filePath+filename+".xml")));

            // deserialize from the XML into a data object
            Data deserializedXml = xmlMapper.readValue(readContent, Data.class);

            // Print object details
            System.out.println("Deserialized data from xml: ");
            System.out.println("\tid: " + deserializedXml.getId());
            System.out.println("\temail: " + deserializedXml.getEmail());
            System.out.println("\tfirst name: " + deserializedXml.getFirst_name());
            System.out.println("\tlast name: " + deserializedXml.getLast_name());
            System.out.println("\tavatar: " + deserializedXml.getAvatar());
            System.out.println("\turl: " + deserializedXml.getSupport().getUrl());
            System.out.println("\ttext: " + deserializedXml.getSupport().getText());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void serializeToJSON(String filename) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();

            //build child element
            Support sto = new Support(url,text);
            //write parent
            String jsonString = objectMapper.writeValueAsString(new Data(id, email, first_name, last_name, avatar, sto));

            System.out.println("json" + " " + jsonString);

            File jsonOutput = new File(filePath+filename+".json");
            FileWriter fileWriter = new FileWriter(jsonOutput);
            fileWriter.write(jsonString);
            fileWriter.close();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserializeFromJSON(String filename) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            // read file and put contents into the string
            String readContent = new String(Files.readAllBytes(Paths.get(filePath+filename+".json")));

            // deserialize from the XML into a data object
            Data deserializedJson = mapper.readValue(readContent, Data.class);

            // Print object details
            System.out.println("Deserialized data from json: ");
            System.out.println("\tid: " + deserializedJson.getId());
            System.out.println("\temail: " + deserializedJson.getEmail());
            System.out.println("\tfirst name: " + deserializedJson.getFirst_name());
            System.out.println("\tlast name: " + deserializedJson.getLast_name());
            System.out.println("\tavatar: " + deserializedJson.getAvatar());
            System.out.println("\turl: " + deserializedJson.getSupport().getUrl());
            System.out.println("\ttext: " + deserializedJson.getSupport().getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void compare(String filename1, String filename2, String compare) throws IOException {
        //Data deserializedXml = new Data();
        //Data deserializedJson = new Data();

        XmlMapper xmlMapper = new XmlMapper();
        ObjectMapper mapper = new ObjectMapper();

        // read file to string
        /*
        Path path1 = Paths.get(filePath+filecompare1);
        Path naming1 = path1.getFileName();

        Path path2 = Paths.get(filePath+filecompare2);
        Path naming2 = path2.getFileName();
         */

        //deserialized
        if(compare=="xml"){
            String readContent1 = new String(Files.readAllBytes(Paths.get(filePath+filename1+".xml")));
            String readContent2 = new String(Files.readAllBytes(Paths.get(filePath+filename2+".xml")));
            Data deserializedXml1 = xmlMapper.readValue(readContent1, Data.class);
            Data deserializedXml2 = xmlMapper.readValue(readContent2, Data.class);


            System.out.println("\tcompare xml to xml");
            System.out.println("\tis id equals?: " + deserializedXml1.getId().equals(deserializedXml2.getId()));
            System.out.println("\tis url equals?: " + deserializedXml1.getSupport().getUrl().equals(deserializedXml2.getSupport().getUrl()));

            //CONTOH AJA - reason why equals should Override, klo pake == bandingin lokasi object di memory (default java equals pake ==)
            if (deserializedXml1.getSupport().getUrl() == deserializedXml2.getSupport().getUrl()) {
                System.out.println("\tUrl Equal ");
            } else {
                System.out.println("\tUrl Not Equal ");
            }
        } else {
            String readContent1 = new String(Files.readAllBytes(Paths.get(filePath+filename1+".json")));
            String readContent2 = new String(Files.readAllBytes(Paths.get(filePath+filename2+".json")));
            Data deserializedJson1 = mapper.readValue(readContent1, Data.class);
            Data deserializedJson2 = mapper.readValue(readContent2, Data.class);

            System.out.println("\tcompare json to json");
            System.out.println("\tis id equals?: " + deserializedJson1.getId().equals(deserializedJson2.getId()));
            System.out.println("\tis url equals?: " + deserializedJson1.getSupport().getUrl().equals(deserializedJson2.getSupport().getUrl()));
        }
    }

}
