import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import edu.columbia.ccls.madamira.MADAMIRAWrapper;
import edu.columbia.ccls.madamira.configuration.MadamiraInput;
import edu.columbia.ccls.madamira.configuration.MadamiraOutput;
import edu.columbia.ccls.madamira.configuration.Word;

public class Madamira_Morphological_Analysis2 {

	public static MadamiraOutput output;
	static Word word;
	static String sentence;
	static MadamiraInput input1;
	static int numOfWords = 10;
	static int numOfFeture = 16;
	static String[][] Fetures = new String[numOfWords][numOfFeture];

	

	// MADAMIRA namespace as defined by its XML schema
	// YOU NEED TO CHANGE INPUT & OUTPUT PATHS
	private static final String MADAMIRA_NS = "edu.columbia.ccls.madamira.configuration";
	private static final String INPUT_FILE = "input.xml";
	private static final String OUTPUT_FILE = "output.xml";

	public static void morphologicalAnalysis(String text) {
		
		

		final MADAMIRAWrapper wrapper = new MADAMIRAWrapper();
		JAXBContext jc = null;

		try {
			jc = JAXBContext.newInstance(MADAMIRA_NS);
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// The structure of the MadamiraInput object is exactly similar to the
			// madamira_input element in the XML
			 input1 = (MadamiraInput) unmarshaller.unmarshal(new File(INPUT_FILE));

			{
				input1.getInDoc().getInSeg().get(0).setValue(text);
				int numSents = input1.getInDoc().getInSeg().size();
				String outputAnalysis = input1.getMadamiraConfiguration().getOverallVars().getOutputAnalyses();
				String outputEncoding = input1.getMadamiraConfiguration().getOverallVars().getOutputEncoding();

				System.out.println("processing " + numSents + " sentences for analysis type = " + outputAnalysis
						+ " and output encoding = " + outputEncoding);
			}

			// The structure of the MadamiraOutput object is exactly similar to the
			// madamira_output element in the XML
			
			 
			output = wrapper.processString(input1);

			{
				int numSents = output.getOutDoc().getOutSeg().size();

				System.out.println("processed output contains " + numSents + " sentences...");
			}

			jc.createMarshaller().marshal(output, new File(OUTPUT_FILE));

		} catch (JAXBException ex) {
			System.out.println("Error marshalling or unmarshalling data: " + ex.getMessage());
		} catch (InterruptedException ex) {
			System.out.println("MADAMIRA thread interrupted: " + ex.getMessage());
		} catch (ExecutionException ex) {
			System.out.println(
					"Unable to retrieve result of task. " + "MADAMIRA task may have been aborted: " + ex.getCause());
		}

		// ------------- from here is our work -----------------

		sentence = output.getOutDoc().getOutSeg().get(0).getSegmentInfo().getPreprocessed();
		System.out.println(sentence);

		// get the number of words in a sentence

		for (int i = 0; i < sentence.length(); i++) {
			char ch = sentence.charAt(i);
			String str = new String("" + ch);
			if (i + 1 != sentence.length() && str.equals(" ") && !("" + sentence.charAt(i + 1)).equals(" ")) {
				numOfWords++;
			}
		}

		 
		
		numOfWords= countNumOfWords(sentence);
		
		 

		for (int i = 0; i < countNumOfWords(sentence); i++) {

			Fetures[i][0] = output.getOutDoc().getOutSeg().get(0).getWordInfo().getWord().get(i).getWord();
			Fetures[i][1] = output.getOutDoc().getOutSeg().get(0).getWordInfo().getWord().get(i).getSvmPrediction()
					.getMorphFeatureSet().getPos();
			Fetures[i][2] = output.getOutDoc().getOutSeg().get(0).getWordInfo().getWord().get(i).getSvmPrediction()
					.getMorphFeatureSet().getPrc3();
			Fetures[i][3] = output.getOutDoc().getOutSeg().get(0).getWordInfo().getWord().get(i).getSvmPrediction()
					.getMorphFeatureSet().getPrc2();
			Fetures[i][4] = output.getOutDoc().getOutSeg().get(0).getWordInfo().getWord().get(i).getSvmPrediction()
					.getMorphFeatureSet().getPrc1();
			Fetures[i][5] = output.getOutDoc().getOutSeg().get(0).getWordInfo().getWord().get(i).getSvmPrediction()
					.getMorphFeatureSet().getPrc0();
			Fetures[i][6] = output.getOutDoc().getOutSeg().get(0).getWordInfo().getWord().get(i).getSvmPrediction()
					.getMorphFeatureSet().getPer();
			Fetures[i][7] = output.getOutDoc().getOutSeg().get(0).getWordInfo().getWord().get(i).getSvmPrediction()
					.getMorphFeatureSet().getAsp();
			Fetures[i][8] = output.getOutDoc().getOutSeg().get(0).getWordInfo().getWord().get(i).getSvmPrediction()
					.getMorphFeatureSet().getVox();
			Fetures[i][9] = output.getOutDoc().getOutSeg().get(0).getWordInfo().getWord().get(i).getSvmPrediction()
					.getMorphFeatureSet().getMod();
			Fetures[i][10] = output.getOutDoc().getOutSeg().get(0).getWordInfo().getWord().get(i).getSvmPrediction()
					.getMorphFeatureSet().getGen();
			Fetures[i][11] = output.getOutDoc().getOutSeg().get(0).getWordInfo().getWord().get(i).getSvmPrediction()
					.getMorphFeatureSet().getNum();
			Fetures[i][12] = output.getOutDoc().getOutSeg().get(0).getWordInfo().getWord().get(i).getSvmPrediction()
					.getMorphFeatureSet().getStt();
			Fetures[i][13] = output.getOutDoc().getOutSeg().get(0).getWordInfo().getWord().get(i).getSvmPrediction()
					.getMorphFeatureSet().getCas();
			Fetures[i][14] = output.getOutDoc().getOutSeg().get(0).getWordInfo().getWord().get(i).getSvmPrediction()
					.getMorphFeatureSet().getEnc0();
			Fetures[i][15] = output.getOutDoc().getOutSeg().get(0).getWordInfo().getWord().get(i).getAnalysis().get(0).getMorphFeatureSet().getStem();
		}
		
		
		  

		wrapper.shutdown();

	}

	// This method to count the number of words in the sentence
	static int countNumOfWords(String input) {

		String trim = input.trim();
		if (trim.isEmpty())
			return 0;
		return trim.split("\\s+").length; // separate string around spaces

	}
	
 

}
