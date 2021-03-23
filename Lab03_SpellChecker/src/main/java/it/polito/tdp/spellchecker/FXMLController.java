/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

	
	//boxlingua.getItems.getall
package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Dictionary d;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> boxLingua;

    @FXML
    private TextArea txtImmesso;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private TextField txtErrori;

    @FXML
    private TextField txtTempo;

    @FXML
    void doClearText(ActionEvent event) {

    	this.txtImmesso.clear();
    	this.txtRisultato.clear();
    	this.txtTempo.clear();
    	this.txtErrori.clear();
    	this.boxLingua.setValue("");
    }

    @FXML
    void doSpellCheck(ActionEvent event) {

    	String immesso="";
    	String lingua="";
    	int cnt=0;
    	
    	double tempo=(double)System.nanoTime();
    	List<String> frase=new LinkedList<String>();
    	immesso=this.txtImmesso.getText().toLowerCase();
    	immesso=immesso.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
    
    	StringTokenizer st = new StringTokenizer(immesso, " ");
    	while(st.hasMoreTokens()) {
    		frase.add((String)st.nextElement());
    	}

    	lingua=this.boxLingua.getValue();
    	d.loadDictionary(lingua);
    	
    	for(RichWord r: d.spellCheckText(frase)) {
    		if(r.isCorretta()==false) {
    			this.txtRisultato.appendText(r.getParola()+"\n");
    			cnt++;
    			//this.txtErrori.setText("The text contains: "+cnt+" error");
    		} 
    		//else if(r.isCorretta()==true){this.txtRisultato.appendText("Giusta");}
    	}       
    	double t=(double)System.nanoTime();
    	this.txtTempo.setText("Spell Checked complleted in: "+(t-tempo)+"secondi");
    	this.txtErrori.setText("The text contains: "+cnt+" error");
    }
    
    public void setModel(Dictionary dizionario) {
    	this.d=dizionario;
    	this.boxLingua.getItems().addAll("English","Italian");
    }

    @FXML
    void initialize() {
        assert boxLingua != null : "fx:id=\"boxLingua\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtImmesso != null : "fx:id=\"txtImmesso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrori != null : "fx:id=\"txtErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTempo != null : "fx:id=\"txtTempo\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
