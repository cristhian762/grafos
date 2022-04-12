/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author 20191BSI0077
 */
public class CreateGrafo {
    
    private final java.io.File file = new java.io.File("data.txt");

    private void generateFork(int order) {
        boolean statusFile = false;

        try {
            statusFile = file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (statusFile) {
            try {
                FileWriter fileWriter = new FileWriter(file, false);

                try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
                    printWriter.print(Integer.toString(order));

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
