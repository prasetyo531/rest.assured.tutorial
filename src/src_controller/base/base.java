package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class base {

    public static Properties prop;

    public static String workingDir = System.getProperty("user.dir");

    /*
    if not static, variable are not able to use by other class
    */
    public static Integer userId;
    public static String response_email;
    public static String first_name;
    public static String last_name;
    public static String avatar;

    public base(){
        try {
            prop=new Properties();
            ///Users/mac/Documents/Automation/fdn.restassured.com/fdn.restassured.com/env.properties
            FileInputStream ip= new FileInputStream(workingDir+"/env.properties");
            prop.load(ip);
        } catch (IOException e){
            e.printStackTrace();
        }
    }



}
