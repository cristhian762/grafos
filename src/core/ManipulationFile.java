/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author 20191bsi0077
 */
public class ManipulationFile {

	private int lastNumber;
	private final java.io.File file = new java.io.File("data.txt");

	public ManipulationFile() {

		if (!file.exists()) {
			creatFileData(999990101);
		}

		readFileData();
	}

	private void creatFileData(int number) {
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
					printWriter.print(Integer.toString(number));

					printWriter.flush();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("Não foi possível criar o arquivo!");
		}
	}

	private void readFileData() {
		try {
			BufferedReader bufferedReader;
			
			try (FileReader fileReader = new FileReader(file)) {
				bufferedReader = new BufferedReader(fileReader);
				lastNumber = Integer.parseInt(bufferedReader.readLine());
			}

			bufferedReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateFileData(int number) {
		file.delete();

		creatFileData(number);
	}

	public void setLastNumber(int number) {
		updateFileData(number);
	}

	public int getLastNumber() {
		return lastNumber;
	}
}
