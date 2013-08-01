
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import tr.gov.eba.hfm.entity.Expression;
import tr.gov.eba.hfm.entity.ExtendedDnsIp;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ismailakpolat
 */
public class JsontTest {

    public static void writeJson() throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("jsontest.hfm")));
        Gson gson = new Gson();
        ArrayList<ExtendedDnsIp> list = new ArrayList<ExtendedDnsIp>();
        list.add(new ExtendedDnsIp("127.0.0.1", true));
        list.add(new ExtendedDnsIp("192.168.5.31", true));
        ArrayList<Expression> expList = new ArrayList<Expression>();
        expList.add(new Expression("deneme.eba.gov.tr", list));
        expList.add(new Expression("deneme.eba.gov.tr", list));
        expList.add(new Expression("deneme.eba.gov.tr", list));
        expList.add(new Expression("deneme.eba.gov.tr", list));
        String json = gson.toJson(expList);
        writer.write(json);
        writer.close();
    }
    
    public static ArrayList<Expression> readJson() throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("jsontest.hfm")));
        Type type = new TypeToken<ArrayList<Expression>>() {}.getType();
        ArrayList<Expression> expList = new Gson().fromJson(reader, type);
        reader.close();
//        System.out.println(((Expression)expList.get(0)).getDnsName());
//        System.out.println(((Expression)expList.get(0)).getIpList());
        return expList;
    }
    
//    public static void main(String[] args) throws IOException {
////        writeJson();
//        readJson();
//    }
    
}
