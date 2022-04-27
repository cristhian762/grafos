/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.IOException;

/**
 *
 * @author 20191bsi0077
 */
public class Grafo {

	/**
	 * @param args the command line arguments
	 * @throws java.io.IOException
	 *
	 */
	public static void main(String[] args) throws IOException {
		Grafo1 g = new Grafo1();
		ManipulationFile file = new ManipulationFile();
		file.generateFork(1000);
//        
		g.load("data.txt");
		g.mostraGrafo1(g.wg, "Grafo de Entrada");

		long tempInitDijkstra = System.currentTimeMillis();
		g.dijkstra(g.wg, "V1");
		long tempDijkstra = System.currentTimeMillis() - tempInitDijkstra;

		long tempInitBellmanford = System.currentTimeMillis();
		g.menorCaminhoorigemUnica(g.wg, "V1"); // bellman_ford
		long tempBellmanford = System.currentTimeMillis() - tempInitBellmanford;

		System.out.printf("O metodo Bellman Ford executou em: %d milisegundos\n", tempDijkstra);
		System.out.printf("O metodo Dijkstra executou em: %d milisegundos\n", tempBellmanford);

//		g.load("g7.txt");
//		g.mostraGrafo1(g.wg, "Grafo de Entrada");
//		long tempInitDijkstra = System.currentTimeMillis();
//		g.dijkstra(g.wg, "A");
//		long tempDijkstra = System.currentTimeMillis() - tempInitDijkstra;
//
//		long tempInitBellmanford = System.currentTimeMillis();
//		g.menorCaminhoorigemUnica(g.wg, "A"); // bellman_ford
//		long tempBellmanford = System.currentTimeMillis() - tempInitBellmanford;
//
//		System.out.printf("O metodo Bellman Ford executou em: %d milisegundos\n", tempDijkstra);
//		System.out.printf("O metodo Dijkstra executou em: %d milisegundos\n", tempBellmanford);
//        G.add("g1.txt");
//        G.add("g2.txt");
//        G.add("g3.txt");
//        G.add("g4.txt");
//        G.add("g5.txt");
//        G.add("g6.txt");
//          g.add("g7.txt");
//
//        for (String grafo : G) {
//            g.load(grafo);
//            g.mostraGrafo1(g.wg, "Grafo de Entrada");
//            g.dijkstra(g.wg, "A");
//            g.menorCaminhoorigemUnica(g.wg, "A"); // bellman_ford
//        }
	}
}
