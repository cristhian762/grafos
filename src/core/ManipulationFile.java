/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 *
 * @author 20191BSI0077
 */
public class ManipulationFile {

	private final java.io.File file = new java.io.File("data.txt");

	public void generateFork(int order) throws IOException {
		Random random = new Random();
		String line = "";
		int value = 0, qtd_vert = 0;

		boolean statusFile;

		file.delete();

		statusFile = file.createNewFile();

		if (statusFile) {
			FileWriter fileWriter = new FileWriter(file, false);

			try ( PrintWriter printWriter = new PrintWriter(fileWriter)) {
				for (int i = 1; i <= order; i++) {
					line += "V" + Integer.toString(i) + " ";
				}

				printWriter.print(line + "\n");

				for (int i = 0; i < order; i++) {
					line = "";
					for (int j = 0; j < order; j++) {
						value = random.nextInt(10);
						if (random.nextInt(100) > 80 && i != j && value > 0) {
							line += Integer.toString(value) + " ";
							qtd_vert++;
						} else {
							line += "0 ";
						}
					}
					printWriter.print(line + "\n");
				}

				printWriter.flush();
				System.out.printf("Grafo gerado com %d vertices.\n", order);
				System.out.printf("Grafo gerado com %d arestas.\n", qtd_vert);
			}

		} else {
			System.out.println("Não foi possível criar o arquivo!");
		}
	}

	public void createFork(int order) throws IOException {
		char vert = 'A';
		String line = "";

		boolean statusFile;

		file.delete();

		statusFile = file.createNewFile();

		if (statusFile) {
			FileWriter fileWriter = new FileWriter(file, false);

			try ( PrintWriter printWriter = new PrintWriter(fileWriter)) {
				for (int i = 1; i <= order; i++) {
					line += vert + " ";
					vert++;
				}

				printWriter.print(line + "\n");

				for (int i = 0; i < order; i++) {
					line = "";
					for (int j = 0; j < order; j++) {
						line += "0 ";
					}
					printWriter.print(line + "\n");
				}

				printWriter.flush();
			}

		} else {
			System.out.println("Não foi possível criar o arquivo!");
		}
	}

	public void updateFork(String origin, String destiny, int order) throws FileNotFoundException, IOException {
		int[][] matriz = new int[order][order];

		FileReader fileReader = new FileReader(file);

		BufferedReader bufferedReader = new BufferedReader(fileReader);

		String linha = "";
		String cabecalho = bufferedReader.readLine();

		int i = 0;
		while ((linha = bufferedReader.readLine()) != null) {

			for (int j = 0; j < linha.split(" ").length; j++) {
				matriz[i][j] = Integer.parseInt(linha.split(" ")[j]);
			}

			i++;
		}

		char a = 'A', b = 'A';

		for (i = 0; i < order; i++) {
			for (int j = 0; j < order; j++) {
				if (b == origin.charAt(0) && a == destiny.charAt(0)) {
					matriz[i][j] = 1;
				}

				b++;
			}

			a++;
			b = 'A';
		}

		file.delete();

		boolean statusFile = file.createNewFile();

		if (statusFile) {
			FileWriter fileWriter = new FileWriter(file, false);

			try ( PrintWriter printWriter = new PrintWriter(fileWriter)) {
				printWriter.print(cabecalho + "\n");

				for (i = 0; i < order; i++) {
					String line = "";
					for (int j = 0; j < order; j++) {
						line += matriz[i][j] + " ";
					}

					printWriter.print(line + "\n");
				}

				printWriter.flush();
			}

		} else {
			System.out.println("Não foi possível criar o arquivo!");
		}

		fileReader.close();
		bufferedReader.close();
	}
}
