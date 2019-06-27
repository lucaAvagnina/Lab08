package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {
	
	private Graph<String, DefaultEdge> grafo;
	
	private WordDAO wordDAO;
	private List<String> wordsList;

	public Model() {
		this.wordDAO = new WordDAO();
	}

	public void createGraph(int numeroLettere) {
		
		wordsList = new LinkedList<String>(wordDAO.getAllWordsFixedLength(numeroLettere));
		
		this.grafo = new SimpleGraph<>(DefaultEdge.class);
		
		//aggiungo i vertici
		Graphs.addAllVertices(this.grafo, this.wordsList);
		
		for(String parola1 : this.grafo.vertexSet()) {
			for(String parola2 : this.grafo.vertexSet()) {
				if(parola1.equals(parola2) == false) {
					int counter = 0;
					for(int i=0; i<numeroLettere; i++) {
						if(parola1.charAt(i)!=parola2.charAt(i))
							counter++;
					}
					if(counter == 1) {
						this.grafo.addEdge(parola1, parola2);
					}
				}
			}
		}
		
	}

	public List<String> displayNeighbours(String parolaInserita) {
		
		//Uso il metodo statico presente nella libreria Graph
		
		return new ArrayList<String>(Graphs.neighborListOf(grafo, parolaInserita));
	}

	public String findMaxDegree() {
		int max = 0;
		String parolaMax = null; 
		for(String s : this.grafo.vertexSet()) {
			int grado = grafo.degreeOf(s);
			if(grado > max) {
				max = grado;
				parolaMax = s;
			}
		}
			
		return parolaMax;
	}

	public boolean esiste(String parolaInserita) {
	
		if(wordsList.contains(parolaInserita))
			return true;
		
		return false;
	}

	public Graph<String, DefaultEdge> getGrafo() {
		return grafo;
	}

	public List<String> getWordsList() {
		return wordsList;
	}
	
}

