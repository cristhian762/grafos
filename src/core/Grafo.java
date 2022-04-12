/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author 20191bsi0077
 */
public class Grafo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Grafo1 g = new Grafo1();
        Queue<String> G = new LinkedList<>();
        
//        G.add("g1.txt");
//        G.add("g2.txt");
//        G.add("g3.txt");
//        G.add("g4.txt");
//        G.add("g5.txt");
        G.add("g6.txt");
        G.add("g7.txt");

        for (String grafo : G) {
            g.load(grafo);
            g.mostraGrafo1(g.wg, "Grafo de Entrada");
            g.dijkstra(g.wg, "A");
            g.menorCaminhoorigemUnica(g.wg, "A"); // bellman_ford
        }
        
        
    }
}
