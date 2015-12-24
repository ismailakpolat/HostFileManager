/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hfm.json.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import hfm.entity.Expression;

/**
 *
 * @author ismailakpolat
 */
public class Utility {
    
    public static ArrayList readListJson(String path, Type type) throws FileNotFoundException, IOException, JsonSyntaxException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
        ArrayList<Expression> expList = new Gson().fromJson(reader, type);
        reader.close();
        return expList;
    }
}
