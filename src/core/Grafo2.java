package core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class Grafo2 {

	public final int inf = 999999;
	int tempo;

	public UndirectedSparseMultigraph<String, EdgeType> getWg() {
		return wg;
	}

	public void setWg(UndirectedSparseMultigraph<String, EdgeType> wg) {
		this.wg = wg;
	}
	UndirectedSparseMultigraph<String, EdgeType> wg;

	/**
	 *
	 * @param graphFile carrega um grafo do arquivo de entrada
	 */
	public void load(String graphFile) {
		this.wg = new UndirectedSparseMultigraph<String, EdgeType>();

		BufferedReader buffread;
		String linha;
		List<String> vertices;
		List<String> result;
		int wei;
		int i;
		try {
			/* obtem os v�rtices primeiro */

			buffread = new BufferedReader(new FileReader(graphFile));
			i = 0;
			boolean eof = false;
			int vdest;
			/* obtem os v�rtices primeiro */
			linha = buffread.readLine();
			vertices = Arrays.asList(linha.split("\\s* \\s*"));
			for (String v : vertices) {
				this.wg.addVertex(v);

			}
			/* adiciona as arestas agora */
			i = 0;
			while ((linha = buffread.readLine()) != null) {
				result = Arrays.asList(linha.split("\\s* \\s*"));
				vdest = 0;
				for (String w : result) {
					wei = Integer.valueOf(w);
					if (wei != 0) {
						EdgeType e = new EdgeType(wei, String.valueOf(this.wg.getEdgeCount()));
						// e.setLabel(String.valueOf(this.wg.getEdgeCount()));
						this.wg.addEdge(e, vertices.get(i), vertices.get(vdest));

					}
					vdest++;
				}

				i++;
				System.out.print("reg" + i + ": ");
				System.out.println(result);

			}
			buffread.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void mostraGrafo1(UndirectedSparseMultigraph g, String nomedoGrafo) {
		// SimpleGraphView2 sgv = new SimpleGraphView2(); // This builds the graph
		// Layout<V, E>, VisualizationComponent<V,E>
		Layout<Integer, String> layout = new CircleLayout(g);
		layout.setSize(new Dimension(300, 300));
		//BasicVisualizationServer<Integer,String> vv = new BasicVisualizationServer<Integer,String>(layout);
		BasicVisualizationServer vv = new BasicVisualizationServer<>(layout);
		vv.setPreferredSize(new Dimension(350, 350));
		// Setup up a new vertex to paint transformer...
		Transformer<String, Paint> vertexPaint = new Transformer<String, Paint>() {
			public Paint transform(String i) {
				return Color.YELLOW;
			}
		};
		Transformer<EdgeType, String> edgePaint = new Transformer<EdgeType, String>() {
			public String transform(EdgeType i) {
				return String.valueOf(i.weight);

			}
		};
		vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
		// vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());

		vv.getRenderContext().setEdgeLabelTransformer(edgePaint);
		//vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);

		JFrame frame = new JFrame(nomedoGrafo);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);

	}

	/* M�todo de Prim 
 * Arvore geradora m�nima
	 */
	public String getMin(List<String> q, HashMap<String, VData> dVertices) {
		String min = q.get(0);
		float cMin = dVertices.get(min).custo;
		for (String v : q) {
			if (dVertices.get(v).custo < cMin) {
				min = v;
				cMin = dVertices.get(v).custo;
			}
		}
		return min;
	}

	public void prim(UndirectedSparseMultigraph<String, EdgeType> g, String r) {
		HashMap<String, VData> dVertices = new HashMap<String, VData>();
		List<String> q = new LinkedList<String>();
		//String min = Collections.min(q);
		/* inicializando os dados dos v�rtices */

		for (String u : g.getVertices()) {
			VData vd = new VData();
			vd.custo = this.inf;
			vd.pred = null;
			dVertices.put(u, vd);
			q.add(u);
		}
		dVertices.get(r).custo = 0;

		while (q.isEmpty() == false) {
			String min = this.getMin(q, dVertices);
			q.remove(min);
			for (String v : g.getNeighbors(min)) {
				float w = g.findEdge(min, v).weight;
				if (q.contains(v) && (w < dVertices.get(v).custo)) {
					dVertices.get(v).pred = min;
					dVertices.get(v).custo = w;
				}
			}
		}

		/* a partir daqui o c�digo cria um grafo a partir do resultado do DFS */
 /*
	    * Adiciono os v�rtices
		 */
		UndirectedSparseMultigraph<String, EdgeType> gMST = new UndirectedSparseMultigraph<String, EdgeType>();
		for (String u : g.getVertices()) {
			dVertices.get(u).print();
			gMST.addVertex(u);

		}
		/*
            * adiciono as arestas 	    
		 */
		for (String u : g.getVertices()) {
			String v = dVertices.get(u).pred;
			if (v != null) {
				EdgeType e = new EdgeType(this.wg.findEdge(v, u).weight, String.valueOf(gMST.getEdgeCount()));
				gMST.addEdge(e, v, u);

			}
		}

		/*
	   * chama o mostra grafo 2 para exibir o grafo gerado pelo conjunto de antecessores gerado pel DFS 
		 */
		this.mostraGrafo1(gMST, "grafo MST Prim");
	}

	public void kruskal(UndirectedSparseMultigraph<String, EdgeType> g) throws IOException {
		List<EdgeKruskal> edge = new LinkedList<>();
		HashMap<String, VData> dVertices = new HashMap<>();

		ManipulationFile file = new ManipulationFile();

		file.createFork(g.getVertexCount());

		Grafo2 h = new Grafo2();
		h.load("data.txt");

		for (String u : g.getVertices()) {
			for (String v : g.getNeighbors(u)) {
				float w = g.findEdge(u, v).weight;

				EdgeKruskal edgeKru = new EdgeKruskal();

				edgeKru.setInit(u);
				edgeKru.setEnd(v);
				edgeKru.setWeight(w);

				edge.add(edgeKru);
			}
		}

		Collections.sort(edge);

		for (EdgeKruskal r : edge) {
			System.out.println(r.getInit() + " " + r.getEnd() + " " + r.getWeight());

			for (String u : g.getVertices()) {
				VData vd = new VData();
				vd.pred = null;
				dVertices.put(u, vd);
			}

			if (ehDisjointed(r.getInit(), r.getEnd(), h.wg, dVertices)) {
				System.out.println(r.getInit() + " " + r.getEnd() + " - São disjuntos");

				file.updateFork(r.getInit(), r.getEnd(), g.getVertexCount());

				h.load("data.txt");
			}
		}

		UndirectedSparseMultigraph<String, EdgeType> gMST = new UndirectedSparseMultigraph<String, EdgeType>();

		for (String u : g.getVertices()) {
			dVertices.get(u).print();
			gMST.addVertex(u);
		}

		for (String u : g.getVertices()) {
			String v = dVertices.get(u).pred;
			if (v != null) {
				EdgeType e = new EdgeType(this.wg.findEdge(v, u).weight, String.valueOf(gMST.getEdgeCount()));
				gMST.addEdge(e, v, u);
			}
		}

		this.mostraGrafo1(gMST, "Kruskal");

	}

	public boolean ehDisjointed(String origin, String destiny, UndirectedSparseMultigraph<String, EdgeType> h, HashMap<String, VData> dVertices) {
		if (h.getNeighbors(origin).isEmpty()) {
			return true;
		}

		VData inicio = dVertices.get(origin);
		inicio.cor = 1;

		Queue<String> fila = new LinkedList<>();
		List<VData> visitados = new ArrayList<>();
		List<VData> encontrados = new ArrayList<>();

		fila.add(origin);
		encontrados.add(inicio);

		while (!fila.isEmpty()) {
			String u = fila.remove();
			if (visita((UndirectedSparseMultigraph<String, EdgeType>) h, u, dVertices, visitados, encontrados, fila, destiny)) {
				return true;
			}
			visitados.add(dVertices.get(u));
		}

		return false;

	}

	public boolean visita(UndirectedSparseMultigraph<String, EdgeType> h, String u, Map<String, VData> dVertices,
		List<VData> visitados, List<VData> encontrados, Queue<String> fila, String destiny) {

		for (String v : h.getNeighbors(u)) {
			VData vd = dVertices.get(v);
			if ((!visitados.contains(vd)) && (!encontrados.contains(vd))) {
				vd.cor = 1;
				vd.pred = u;
				encontrados.add(vd);
				fila.add(v);

				if (v.equals(destiny)) {
					return true;
				}
			}
		}

		dVertices.get(u).cor = 2;

		return false;
	}
}
