/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blowfish;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.LexedTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Leo
 */
public class Main {

    private static HashMap<Integer, String[]> labeledTokens;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide the filename as an argument.");
            return;
        }
    }

    /**
     * For each token (word or punctuation entity) in the input text file, this
     * method will return an key-value pair where the key is an integer and the
     * value is a String array of length two. The integer represents the token's
     * index, and the String array contains the token text itself and the part
     * of speech the token has been classified as.
     * @param fileName
     * @return 
     */
    private static HashMap<Integer, String[]> getTokens(String fileName) {
		HashMap<Integer, String[]> result = new HashMap();
		int tokenIndex = 0;
		BufferedReader br;
		try {
                        // Read in text file and tokenize it.
			br = new BufferedReader(new FileReader(fileName));
			LexedTokenFactory factory = new CoreLabelTokenFactory(false);
			PTBTokenizer tokenizer = new PTBTokenizer(br, factory, null);
			List<CoreLabel> tokens = tokenizer.tokenize();
                        // Apply maximum entropy POS tagger to the tokens.
			MaxentTagger posTagger = new MaxentTagger(
					".\\lib\\english-left3words-distsim.tagger");
			List<TaggedWord> taggedTokens = posTagger.apply(tokens);
                        // Loop through tokens and insert part-of-speech tags
                        // for each token.
			while (tokenIndex < tokens.size()) {
				CoreLabel token = tokens.get(tokenIndex);
				result.put(tokenIndex, new String[]{token.originalText(), 
                                    taggedTokens.get(tokenIndex).tag()});
				tokenIndex++;
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		return result;
	}

}
