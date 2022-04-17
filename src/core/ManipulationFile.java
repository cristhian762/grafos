/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.ArrayList;

/**
 *
 * @author 20191BSI0077
 */
public class ManipulationFile {

    private final java.io.File file = new java.io.File("data.txt");

    public void generateFork(int order) {
        Random random = new Random();
        String line = "";

        boolean statusFile = false;
        
        file.delete();

        try {
            statusFile = file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (statusFile) {
            try {
                FileWriter fileWriter = new FileWriter(file, false);

                try ( PrintWriter printWriter = new PrintWriter(fileWriter)) {
                    int k = 0, s = 0, t = 0;
                    char c = 'a', d = 'a', e = 'a';
                    
                    while (k < order){
                        s = 0;
                        while(k < order && s < 26){
                            t = 0;
                            while(k < order && t < 26){
                                line = Character.toString(Character.toUpperCase(c)) + Character.toString(Character.toUpperCase(d)) +  Character.toString(Character.toUpperCase(e)) + " ";
                                System.out.println(line);
                                
                                t++;
                                e++;
                                k++;
                            }
                            d++;
                            k++;
                            s++;
                            e = 'a';
                        }
                        c++;
                        d = 'a';
                        k++;
                    }

                    for (int i = 0; i < order; i++) {
                        line = "";
                        for (int j = 0; j < order; j++) {
                            if (random.nextBoolean()) {
                                line += Integer.toString(random.nextInt(1, 10)) + " ";
                            } else {
                                line += "0 ";
                            }
                        }
                        printWriter.print(line + "\n");
                    }

                    printWriter.flush();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Não foi possível criar o arquivo!");
        }
    }
}
