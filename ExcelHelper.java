/** Class for processing csv file.
* created by Keke Chen (keke.chen@wright.edu)
* For Cloud Computing Labs
* Feb. 2014
*/
import java.io.LineNumberReader;
import java.io.Writer;
import java.io.Reader;
import java.util.ArrayList;

public class ExcelHelper
{

   
    public static ArrayList<String> parseLine(Reader r) throws Exception {
        int ch = r.read();
        while (ch == '\r') {
            ch = r.read();
        }
        if (ch<0) {
            return null;
        }
        ArrayList<String> store = new ArrayList<String> ();
        StringBuffer curVal = new StringBuffer();
        boolean inquotes = false;
        boolean started = false;
        while (ch>=0) {
            if (inquotes) {
                started=true;
                if (ch == '\"') {
                    inquotes = false;
                }
                else {
                    curVal.append((char)ch);
                }
            }
            else {
                if (ch == '\"') {
                    inquotes = true;
                    if (started) {
   // if this is the second quote in a value, add a quote
   // this is for the double quote in the middle of a value
                        curVal.append('\"');
                    }
                }
                else if (ch == ',') {
                    store.add(curVal.toString());
                    curVal = new StringBuffer();
                    started = false;
                }
                else if (ch == '\r') {
                    //ignore LF characters
                }
                else if (ch == '\n') {
                    //end of a line, break out
                    break;
                }
                else {
                    curVal.append((char)ch);
                }
            }
            ch = r.read();
        }
        store.add(curVal.toString());
        System.out.println(store);
        return store;
    }
}