import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreePrint;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.international.arabic.ArabicTreebankLanguagePack;

public class Stanford_Syntactic_Analysis2 extends Madamira_Morphological_Analysis2 {

 
	 
	static String sentenceType;
	static Tree parse;
	

	  /**
	   * The syntacticAnalysis method demonstrates the easiest way to load a parser.
	   * Simply call loadModel and specify the path of a serialized grammar
	   * model, which can be a file, a resource on the classpath, or even a URL.
	   * For example, this demonstrates loading a grammar from the models jar
	   * file, which you therefore need to include on the classpath for ParserDemo
	   * to work.
	   *
	   * Usage: {@code java ParserDemo [[model] textFile]}
	   * e.g.: java ParserDemo edu/stanford/nlp/models/lexparser/chineseFactored.ser.gz data/chinese-onesent-utf8.txt
	   *
	   */
	static String[] args22;
	
	
	public static void syntacticAnalysis(String[] args) {
		 
	    String parserModel = "edu/stanford/nlp/models/lexparser/arabicFactored.ser.gz";
	    if (args.length > 0) {
	      parserModel = args[0];
	    }
	    LexicalizedParser lp = LexicalizedParser.loadModel(parserModel);

	    if (args.length == 0) {
	      demoAPI(lp);
	    } else {
	      String textFile = (args.length > 1) ? args[1] : args[0];
	      demoDP(lp, textFile);
	    }
	  }

	  /**
	   * demoDP demonstrates turning a file into tokens and then parse
	   * trees.  Note that the trees are printed by calling pennPrint on
	   * the Tree object.  It is also possible to pass a PrintWriter to
	   * pennPrint if you want to capture the output.
	   * This code will work with any supported language.
	   */
	  public static void demoDP(LexicalizedParser lp, String filename) {
	    // This option shows loading, sentence-segmenting and tokenizing
	    // a file using DocumentPreprocessor.
	    TreebankLanguagePack tlp = lp.treebankLanguagePack(); // a PennTreebankLanguagePack for English
	    GrammaticalStructureFactory gsf = null;
	    if (tlp.supportsGrammaticalStructures()) {
	      gsf = tlp.grammaticalStructureFactory();
	    }
	    // You could also create a tokenizer here (as below) and pass it
	    // to DocumentPreprocessor
	    for (List<HasWord> sentence : new DocumentPreprocessor(filename)) {
	      Tree parse = lp.apply(sentence);
	      parse.pennPrint();
	      System.out.println();

	      if (gsf != null) {
	        GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
	        Collection tdl = gs.typedDependenciesCCprocessed();
	        System.out.println(tdl);
	        System.out.println();
	      }
	    }
	  }

	  /**
	   * demoAPI demonstrates other ways of calling the parser with
	   * already tokenized text, or in some cases, raw text that needs to
	   * be tokenized as a single sentence.  Output is handled with a
	   * TreePrint object.  Note that the options used when creating the
	   * TreePrint can determine what results to print out.  Once again,
	   * one can capture the output by passing a PrintWriter to
	   * TreePrint.printTree. This code is for English.
	   */
	  public static void demoAPI(LexicalizedParser lp) {
	    // This option shows parsing a list of correctly tokenized words
	 
	    // This option shows loading and using an explicit tokenizer
	     
	     
	    TokenizerFactory<CoreLabel> tokenizerFactory =
	        PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
	    Tokenizer<CoreLabel> tok =  
	        tokenizerFactory.getTokenizer(new StringReader(sentence));
	    List<CoreLabel> rawWords2 = tok.tokenize();
	     parse = lp.apply(rawWords2);

	    TreebankLanguagePack tlp = lp.treebankLanguagePack(); // PennTreebankLanguagePack for English
	    
	    
	    
	     
	    System.out.println();

	    // You can also use a TreePrint object to print trees and dependencies
	    //TreePrint tp = new TreePrint("penn,typedDependenciesCollapsed");
	    //tp.printTree(parse);
	   
	    
	    sentenceType = parse.getNodeNumber(3).nodeString();
	    
	    //System.out.println("This is the: " + parse.getNodeNumber(3).getChild(i).getLeaves().size() );
	     
	    
	     
	  }

}
