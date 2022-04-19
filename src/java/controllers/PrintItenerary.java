package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author jim
 */
public class PrintItenerary {

    public static ArrayList ITarray(File f) throws FileNotFoundException {
        ArrayList<String> list = new ArrayList<String>();
        Scanner s = new Scanner(f);
        String container = null;

        while (s.hasNextLine()) {
            String current = s.nextLine();
            if (current.isEmpty()) {
                container = concatEndTags(container);
                container = concatEndTags(container);
                list.add(container);
                container = null;
            }
            if (current.contains("AM") || current.contains("PM")) {
                container = concatTags("col-2 col-sm-1", current);
                container = concatBegTags("row my-3 justify-content-center", container);
                current = s.nextLine();
                container += concatBegTags("col-10 col-lg-6 col-sm-8", current);
            } else {
                container += addBr(current);
            }
        }
        return list;

    }

    public static String concatTags(String Class, String Text) {
        String s = "<div class =\"" + Class + "\">" + Text + "</div>";
        return s;
    }

    public static String addBr(String Text) {
        String s = "<br>" + Text;
        return s;
    }

    public static String concatBegTags(String Class, String Text) {
        String s = "<div class =\"" + Class + "\">" + Text;
        return s;
    }

    public static String concatEndTags(String Text) {
        String s = Text + "</div>";
        return s;
    }
}
