package it.polito.tdp.dizionariograph;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.dizionariograph.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioGraphController {
	
	private Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNumeroLettere;

    @FXML
    private TextField txtParola;

    @FXML
    private Button btnGraph;

    @FXML
    private Button btnVicini;

    @FXML
    private Button btnGradoMax;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doGradoMax(ActionEvent event) {
    	
    	int numeroLettere;
    	try {
    		numeroLettere = Integer.parseInt(this.txtNumeroLettere.getText());
    	}catch(NumberFormatException nfe) {
    		txtResult.setText("Inserisci un numero");
    		return;
    	}
    	
    	if(numeroLettere == 0) {
    		txtResult.setText("Inserire un numero maggiore uguale a 1");
    		return;
    	}
    	
    	if(model.getWordsList()==null && model.getGrafo()==null) {
    		txtResult.setText("Non hai creato un grafo, clicca prima su Crea Grafo");
    		return;
    	}
    	
    	String parolaMax = this.model.findMaxDegree();
    	List<String> vicini = new LinkedList<String>(model.displayNeighbours(parolaMax));
    	
    	String result = "Grado Massimo:\n" + parolaMax + ": " + "grado " + vicini.size() + "\nVicini: [";
    	
    	int conta = 0;
    	for(String s : vicini) {
    		conta++;
    		if(conta < vicini.size())
    			result += s +", ";
    		else {
    			result += s;
    		}
    	}
    	result += "]";
    	
    	txtResult.setText(result);
    }

    @FXML
    void doGraph(ActionEvent event) {
    	
    	int numeroLettere;
    	try {
    		numeroLettere = Integer.parseInt(this.txtNumeroLettere.getText());
    	}catch(NumberFormatException nfe) {
    		txtResult.setText("Inserisci un numero");
    		return;
    	}
    	
    	if(numeroLettere == 0) {
    		txtResult.setText("Inserire un numero maggiore uguale a 1");
    		return;
    	}
    	
    	model.createGraph(numeroLettere);
    	
    	txtResult.setText("Grafo generato correttamente!");
    }

    @FXML
    void doReset(ActionEvent event) {
    	this.txtNumeroLettere.clear();
    	this.txtParola.clear();
    	this.txtResult.clear();
    }

    @FXML
    void doVicini(ActionEvent event) {
    	int numeroLettere;
    	try {
    		numeroLettere = Integer.parseInt(this.txtNumeroLettere.getText());
    	}catch(NumberFormatException nfe) {
    		txtResult.setText("Inserisci un numero");
    		return;
    	}
    	String parolaInserita = this.txtParola.getText();
    	
    	if(numeroLettere != parolaInserita.length()) {
    		txtResult.setText("Dati inseriti non coerenti fra loro");
    		return;
    	}
    	
    	if(!parolaInserita.matches("[a-zA-Z]*")) {
    		txtResult.setText("Inserire solo caratteri alfabetici");
    		return;
    	}
    	
    	if(model.getWordsList()==null && model.getGrafo()==null) {
    		txtResult.setText("Non hai creato un grafo, clicca prima su Crea Grafo");
    		return;
    	}
    	
    	if(!model.esiste(parolaInserita)) {
    		txtResult.setText("Parola non esistente");
    		return;
    	}
    	
    	List<String> vicini = new LinkedList<String>(model.displayNeighbours(parolaInserita));
    	
    	String result = "[";
    	int conta = 0;
    	for(String s : vicini) {
    		conta++;
    		if(conta < vicini.size())
    			result += s +", ";
    		else {
    			result += s;
    		}
    	}
    	result += "]";
    	txtResult.setText(result);
    	
    }

    @FXML
    void initialize() {
        assert txtNumeroLettere != null : "fx:id=\"txtNumeroLettere\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGraph != null : "fx:id=\"btnGraph\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnVicini != null : "fx:id=\"btnVicini\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGradoMax != null : "fx:id=\"btnGradoMax\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";

    }

	public void setModel(Model model) {
		
		this.model = model;
		
	}
}

