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

    private static String fileText;
    private static HashMap<Integer, String[]> labeledTokens;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide the filename as an argument.");
            return;
        }

        //printLabeledText(args[0]);		  
    }

    private static void printLabeledText(String fileName) {
        labeledTokens = getTokens(fileName);
        for(String[] token : labeledTokens.values()){
            //System.out.println(token[0]);
        }
    }

    private static HashMap<Integer, String[]> getTokens(String fileName) {
		HashMap<Integer, String[]> tmp = new HashMap();
		int tokenIndex = 0;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileName));
			LexedTokenFactory factory = new CoreLabelTokenFactory(false);
			PTBTokenizer tokenizer = new PTBTokenizer(br, factory, null);
			List<CoreLabel> tokens = tokenizer.tokenize();
			MaxentTagger posTagger = new MaxentTagger(
					"C:\\Users\\Leo\\Coding\\Java\\Libraries\\english-left3words-distsim.tagger");
			List<TaggedWord> taggedTokens = posTagger.apply(tokens);
			while (tokenIndex < tokens.size()) {
				CoreLabel token = tokens.get(tokenIndex);
				tmp.put(tokenIndex, new String[]{token.originalText(), ""});
				System.out.println(taggedTokens.get(tokenIndex).word()+" <> "+taggedTokens.get(tokenIndex).tag());
				tokenIndex++;
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		return tmp;
	}

}
