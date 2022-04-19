/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.PrintIteneraryServlet.addBr;
import static controllers.PrintIteneraryServlet.concatBegTags;
import static controllers.PrintIteneraryServlet.concatEndTags;
import static controllers.PrintIteneraryServlet.concatTags;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author jim
 */
public class PrintItenerary {
 public static ArrayList ITarray() throws FileNotFoundException{
        ArrayList<String> list = new ArrayList<String>();
            Scanner s = new Scanner(new File("C:/Users/jim/Documents/GitHub/Ka-Entengs-Farm-to-Table-Restaurant-Website/ReadFiles/Itenerary.txt"));
            String container = null;
            
            while (s.hasNextLine()) {
                String current = s.nextLine();
                if (current.isEmpty()) {
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
}
