package test.jsonSimple;

import static test.jsonSimple.jsonSimple.writeJsonSimpleDemo;

public class mainJsonSimple {

    public static String workingDir = System.getProperty("user.dir");
    public static String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();

    public static void main(String[] args) throws Exception {

            writeJsonSimpleDemo(workingDir+"/src_test/test/nestedValuesJackson/"+nameofCurrMethod+".json");

    }
}
