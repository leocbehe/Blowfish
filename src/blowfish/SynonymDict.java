/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blowfish;

import java.util.ArrayList;

/**
 * Interacts with the synonym files to find appropriate longer synonyms for
 * a given word or common phrase.
 * @author Leo
 */
public class SynonymDict {
    protected String currentToken;    
    protected ArrayList<String> dict;
    
    public SynonymDict(){
        
    }
    
    /**
     * Reads in synonym values from synonyms.txt and stores them in
     * the class's dict value.
     * @return a boolean that is true if the file was ingested successfully.
     */
    protected boolean setDict(){
        
        return true;
    }
    
    /**
     * Reads in synonym values from the provided filepath and stores them 
     * in the class's dict value.
     * @param filepath the filepath of the synonyms file that should
     * be ingested.
     * @return a boolean that is true if the file was ingested successfully.
     */
    protected boolean setDict(String filepath){
        
        return true;
    }
        
    public String getCurrentToken() {
        return currentToken;
    }

    public void setCurrentToken(String currentToken) {
        this.currentToken = currentToken;
    }
    
    
}
