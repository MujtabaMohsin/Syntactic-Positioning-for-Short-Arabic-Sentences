import java.util.Arrays;

//import de.bwaldvogel.liblinear.Feature;

public class Main_Irab extends Stanford_Syntactic_Analysis2 {

	static String sen_type, sub_sen_type, structure;

	static String[][] irap = new String[numOfWords][6];

	static String[] words;

	static String pronoun_type;

	static int currentWord = 0;
	static int number_of_strucures;
	static int t, w;
	static int treeorder_acc_type;

	public static void doIraab() {

		// to make the defualt value of irap array = ""
		for (String[] row : irap)
			Arrays.fill(row, "");

		// split the sentence to words
		words = sentence.split(" ");

		sen_type = sentence_type();

		 

		// ßã ÊÑßíÈÉ İí ÇáÌãáÉ ¿
		if (sen_type.equals("VP")) {

			if (parse.getNodeNumber(3).nodeString().equals("VP")) {
				number_of_strucures = parse.getNodeNumber(3).numChildren();
				treeorder_acc_type = 3;
			}

			else {
				number_of_strucures = parse.getNodeNumber(2).numChildren();
				treeorder_acc_type = 2;
			}

		}

		else if (sen_type.equals("NP")) {

			if (parse.getNodeNumber(2).nodeString().equals("NP") || parse.getNodeNumber(2).nodeString().equals("S")) {
				number_of_strucures = parse.getNodeNumber(2).numChildren();
				treeorder_acc_type = 2;
			}

			else {
				number_of_strucures = parse.getNodeNumber(3).numChildren();
				treeorder_acc_type = 3;
			}

		}

		// ÇÚÑÇÈ ÍÓÈ ÇáÊÑßíÈíÉ
		// w = ÑŞã ÇáßáãÉ İí ÇáÊÑßíÈÉ
		// t = ÇáÊÑßíÈÉ

		 

		for (t = 0; t < number_of_strucures; t++) {

			for (w = 0; w < parse.getNodeNumber(treeorder_acc_type).getChild(t).getLeaves().size(); w++) {

				// ÊÍÏíÏ äæÚ ÇáßáãÉ ÇÓã ¡ İÚá ¡ ÍÑİ
				// Ëã ÊÍÏíÏ ÇáãæŞÚ ÇáÇÚÑÇÈí Úä ØÑíŞ ÇáÏÇáÉ ÇáãäÇÓÈÉ
				if (Fetures[currentWord][1].equals("verb")) {

					verb_irap();

				}

				else if (Fetures[currentWord][1].equals("prep") || Fetures[currentWord][1].equals("part_dem")
						|| Fetures[currentWord][1].equals("part_neg") || Fetures[currentWord][1].equals("part_restrict")
						|| Fetures[currentWord][1].equals("part_focus") || Fetures[currentWord][1].equals("part_fut")
						|| Fetures[currentWord][1].equals("part_voc") || Fetures[currentWord][1].equals("part_verb")
						|| Fetures[currentWord][1].equals("prep") || Fetures[currentWord][1].equals("conj")
						|| Fetures[currentWord][1].equals("conj_sub")) {

					particle_irap();
				}

				else {
					noun_irap();
				}

				currentWord++;
			}

		}

		// ØÈÇÚÉ ÇáÇÚÑÇÈ
		for (int i = 0; i < words.length; i++) {

			currentWord = 0;

			System.out.print(words[i] + " : ");

			for (int j = 0; j < 6; j++) {
				System.out.print(irap[i][j] + " ");

			}

			System.out.println();

			currentWord++;
		}
		 
		
		for (int i = 0; i < words.length; i++) {
			
			for (int j = 0; j < 6; j++) {
				
			}
			
		}

	}

	// ÏÇáÉ ßÇãáÉ áÇÚÑÇÈ ÇáÇÓãÇÁ
	public static void noun_irap() {
		
		if (Fetures[currentWord][0].equals("Çááå")) {
			irap[currentWord][1] = "áİÙ ÇáÌáÇáÉ ÚÒ æÌá";
		}

		// İí ÍÇáÉ ßÇä ÇáÇÓã íÍÊæí Úáì ÓæÇÈŞ ÈÇÓÊËäÇÁ Çá ÇáÊÚÑíİ
		if (!Fetures[currentWord][3].equals("0") || !Fetures[currentWord][4].equals("0")) {
			checkProclitic();
		}

		if (Fetures[currentWord][1].equals("pron_dem")) {
			irap[currentWord][1] = "ÇÓã ÇÔÇÑÉ";
		}

		if (Fetures[currentWord][1].equals("pron_rel") || Fetures[currentWord][1].equals("pron_interrog")) {
			irap[currentWord][1] = "ÇÓã ãæÕæá";
		}

		// ÇĞÇ ßÇä ÇáÇÓã ÈÏÇíÉ ÇáÌãáÉ İåæ ãÈÊÏÃ
		if (irap[currentWord][1].equals("") && t == 0 && currentWord == 0) {

			irap[currentWord][1] = "ãÈÊÏÃ";
		}

		// ÊÍŞŞ ÇĞÇ ßÇä ÇáÇÓã ãÖÇİÉ
		if (isMdaf(currentWord)) {

			irap[currentWord][5] = "æåæ ãÖÇİ";

		}

		// ÇĞÇ ßÇäÊ ÇáßáãÉ ÇáÓÇÈŞÉ ãÖÇİ İÇáÊÇáíÉ ãÖÇİ Çáíå
		if (irap[currentWord][1].equals("") && !(currentWord == 0) && words.length > 2) {

			if (irap[currentWord - 1][5].equals("æåæ ãÖÇİ")) {

				irap[currentWord][1] = "ãÖÇİ Çáíå";

			}
		}

		// ŞæÇÚÏ ÇáÎÈÑ
		// äÊÍŞŞ ÇĞÇ ßÇäÊ íÌÈ Ãä íßæä åäÇß ÎÈÑ ÈÇáÌãáÉ
		if (irap[currentWord][1].equals("") && sen_type.equals("NP") && isThereMobtaBefore(currentWord)) {

			// ÇĞÇ ßÇäÊ ÇáÌãáÉ ÊÊßæä İŞØ ãä ßáãÊíä ãÈÊÏÇ æÎÈÑ
			if (words.length == 2 && (Fetures[0][1].equals("noun") || Fetures[0][1].equals("noun_prop"))
					&& ((Fetures[1][1].equals("noun") || Fetures[1][1].equals("noun_prop")
							|| Fetures[1][1].equals("adj")))) {

				irap[currentWord][1] = "ÎÈÑ";
			}

			// ÇĞÇ ßÇä ÇáÎÈÑ İí ÈÏÇíÉ ÊÑßíÈÉ 1 æÇáãÈÊÏÇ æÍíÏÇğ ÈÊÑßíÈÉ 0
			else if (words.length > 2 && t == 1
					&& parse.getNodeNumber(treeorder_acc_type).getChild(t - 1).getLeaves().size() == 2 && w == 0
					&& Fetures[currentWord][14].equals("0")) {
				irap[currentWord][1] = "ÎÈÑ";
			}

			// ÇĞÇ ßÇä ÇáÎÈÑ ËÇáËÇğ íÃÊí ÈÚÏ ÇáãÈÊÏÃ æÇáãÖÇİ Çáíå
			else if (words.length > 2 && t == 1
					&& parse.getNodeNumber(treeorder_acc_type).getChild(t - 1).getLeaves().size() == 1 && w == 0
					&& Fetures[currentWord][14].equals("0")) {
				irap[currentWord][1] = "ÎÈÑ";
			}

			// ÇĞÇ ßÇä ãÇ íÓÈŞ ÇáÎÈÑ ãÈÊÏÃ æäÚÊ
			else if (words.length > 2 && t == 1 && (Fetures[currentWord - 1][1].equals("noun")
					|| Fetures[currentWord - 1][1].equals("noun_prop") || Fetures[currentWord - 1][1].equals("adj"))
					&& w == 1) {
				irap[currentWord][1] = "ÎÈÑ";
			}

			// ÇĞÇ ßÇä ÇáãÈÊÏÃ íÊßæä ãä ßáãÊíä ÇáËÇäíÉ áíÓÊ ãÖÇİ Çáíå
			else if (words.length > 2 && t == 1 && !Fetures[currentWord][14].equals("0") && w == 0) {
				irap[currentWord][1] = "ãÈÊÏÃ ËÇä";
				irap[currentWord + 1][1] = "ÎÈÑ";
			}

		}

		// åá ÇáßáãÉ äÚÊ
		if (irap[currentWord][1].equals("") && isNet(currentWord)) {
			irap[currentWord][1] = "äÚÊ";
		}

		// ÍÇáÇÊ ÇáİÇÚá
		if (sen_type.equals("VP") && !(currentWord == 0) && !isVerbHasPronoun(0)) {

			// ÇÓã ÙÇåÑ
			if (t == 1 && w == 0) {
				irap[currentWord][1] = "İÇÚá";
			}

			else if (t == 0 && w == 1) {
				irap[currentWord][1] = "İÇÚá";
			}

		}

		// ÇĞÇ ÓÈŞ ÇáÇÓã ÈÍÑİ ÌÑ İåæ ÇÓã ãÌÑæÑ
		if (!(currentWord == 0)) {

			if (irap[currentWord - 1][1].equals("ÍÑİ ÌÑ")) {

				irap[currentWord][1] = "ÇÓã ãÌÑæÑ";

			}
		}

		 

		// ÇáãİÚæá Èå
		if (!(currentWord == 0) && irap[currentWord][1].equals("") && !(Fetures[currentWord][1].equals("adj"))) {

			if (t == 2 && w == 0) {

				irap[currentWord][1] = "ãİÚæá Èå";

			}

			else {

				if (sen_type.equals("VP")) {

					if (isVerbHasPronoun(0)) {
						irap[currentWord][1] = "ãİÚæá Èå";
					}

					else if (t == 1 && w != 0 && isThereFael()) {

						irap[currentWord][1] = "ãİÚæá Èå";
					}

					else if (isThereFael()) {
						irap[currentWord][1] = "ãİÚæá Èå";
					}

				}

				else {

					if (currentWord > 1) {

						irap[currentWord][1] = "ãİÚæá Èå";
					}

				}

			}

		}

	}

	public static void verb_irap() {

		String anotherAsp = output.getOutDoc().getOutSeg().get(0).getWordInfo().getWord().get(currentWord).getAnalysis()
				.get(0).getMorphFeatureSet().getAsp();

		// ÇÚÑÇÈ ÇáÓæÇÈŞ
		if (!Fetures[currentWord][3].equals("0") || !Fetures[currentWord][4].equals("0")) {
			checkProclitic();
		}

		// ÇáÇÚÑÇÈ ÇáÑÆíÓí

		if (Fetures[currentWord][7].equals("p")) {

			irap[currentWord][1] = "İÚá ãÇÖ";

		}

		else if (Fetures[currentWord][7].equals("i")) {

			irap[currentWord][1] = "İÚá ãÖÇÑÚ";
		}

		// ÇĞÇ áã íÊÚÑİ ãÇÏÇãíÑÇ Úáì äæÚ ÇáİÚá
		else if (Fetures[currentWord][7].equals("na")) {
			Fetures[currentWord][7] = anotherAsp;

			if (Fetures[currentWord][7].equals("p")) {

				irap[currentWord][1] = "İÚá ãÇÖ";

			}

			else if (Fetures[currentWord][7].equals("i")) {

				irap[currentWord][1] = "İÚá ãÖÇÑÚ";
			}

			// ÇĞÇ ãÇ ÒÇá áã íÊÚÑİ äÍÏÏ äæÚ ÇáİÚá ãä äæÚ Çæá ÍÑİ
			else {

				if (words[currentWord].charAt(0) == 'Ê' || words[currentWord].charAt(0) == 'í'
						|| words[currentWord].charAt(0) == 'ä') {
					irap[currentWord][1] = "İÚá ãÖÇÑÚ";
				}

				else {
					irap[currentWord][1] = "İÚá ãÇÖ";
				}
			}

		}

		// ÇĞÇ ßÇä ÇáİÚá íÍÊæí Úáì ÊÇÁ ÇáİÇÚá
		if (isVerbHasPronoun(currentWord)) {

			if (pronoun_type.equals("Ê")) {
				irap[currentWord][4] = "ÇáÊÇÁ : ÖãíÑ ãÊÕá ÈãÍá ÑİÚ İÇÚá";
			}

			else if (pronoun_type.equals("æÇ")) {
				irap[currentWord][4] = "ÇáæÇæ : ÖãíÑ ãÊÕá ÈãÍá ÑİÚ İÇÚá";
			}

			else if (pronoun_type.equals("Ç")) {
				irap[currentWord][4] = "ÇáÇáİ : ÖãíÑ ãÊÕá ÈãÍá ÑİÚ İÇÚá";
			}

			else if (pronoun_type.equals("äÇ")) {
				irap[currentWord][4] = "äÇ : ÖãíÑ ãÊÕá ÈãÍá ÑİÚ İÇÚá";
			}

			else if (pronoun_type.equals("Êí")) {
				irap[currentWord][4] = "ÇáíÇÁ : ÖãíÑ ãÊÕá ÈãÍá ÑİÚ İÇÚá";
			}

			else if (pronoun_type.equals("ä")) {
				irap[currentWord][4] = "Çáäæä : ÖãíÑ ãÊÕá ÈãÍá ÑİÚ İÇÚá";
			}

		}

	}

	// ÇÚÑÇÈ ÎÇÕ ÈÇáÍÑæİ
	public static void particle_irap() {

		if (Fetures[currentWord][1].equals("prep") || Fetures[currentWord][1].equals("part_dem")) {
			irap[currentWord][1] = "ÍÑİ ÌÑ";
		}

		else if (Fetures[currentWord][1].equals("conj") || Fetures[currentWord][1].equals("conj_sub")) {
			irap[currentWord][1] = "ÍÑİ ÚØİ";
		}

		else if (Fetures[currentWord][1].equals("part_neg") || Fetures[currentWord][1].equals("part_interrog")) {
			irap[currentWord][1] = "ÍÑİ äİí";
		}

		else if (Fetures[currentWord][1].equals("part_fut")) {
			irap[currentWord][1] = "ÍÑİ ÇÓÊŞÈÇá";
		}

		else if (Fetures[currentWord][1].equals("part_voc")) {
			irap[currentWord][1] = "ÍÑİ äÏÇÁ";
		}

	}

	// ÊÍÏíÏ äæÚ ÇáÌãáÉ
	public static String sentence_type() {

		if (Fetures[0][1].equals("verb")) {
			return "VP";
		}

		else

			return "NP";

	}

	// ÏÇáÉ ÊÍŞŞ ÇĞÇ ßÇä åäÇß ÍÑİ ÌÑ ÈÇáÌãáÉ

	public static boolean isThereParticle() {

		for (int i = 0; i < words.length; i++) {

			if (Fetures[i][0].equals("prep")) {

				return true;
			}
		}

		return false;

	}

 

	// ÏÇáÉ ÊÍÏÏ ÇĞÇ ßÇä ÇáÇÓã ãÖÇİ
	public static boolean isMdaf(int current_word) {

		// ŞÇÚÏÉ ÊÍÏíÏ ÇáÇÓã ÇáãÖÇİ : 1- ÇáÊÑßíÈÉ ÊÍÊæí Úáì ÇßËÑ ãä ßáãÉ ¡ 2- ÇáßáãÉ
		// ÇáÍÇáíÉ äßÑÉ ¡ 3- ÇáßáãÉ ÇáÊÇáíÉ ÇÓã æáíÓÊ ÕİÉ
		
		 
		if ((parse.getNodeNumber(treeorder_acc_type).getChild(t).getLeaves().size() > 1) && !(isMerfh(current_word))
				&& !(Fetures[current_word][1].equals("adj"))) {

			if (!isLastWord(current_word) ) {

				if ((Fetures[current_word + 1][1].equals("noun") || (Fetures[current_word + 1][1].equals("noun_prop")))
						&& !(Fetures[current_word + 1][1].equals("adj"))) {

					
					if (!(Fetures[current_word][1].equals("pron_dem") ) && !(Fetures[current_word][1].equals("pron")) && !(Fetures[current_word][1].equals("pron_rel"))) {
						return true;
					}

				}

			}

		}

		else if (!(isMerfh(current_word))
				&& parse.getNodeNumber(treeorder_acc_type).getChild(t).getLeaves().size() == 1 
				&& !(Fetures[current_word][1].equals("pron_dem")) && !(Fetures[current_word][1].equals("pron")) && !(Fetures[current_word][1].equals("pron_rel"))) {

			if (!isLastWord(current_word) && (Fetures[current_word + 1][1].equals("noun")
					|| Fetures[current_word + 1][1].equals("noun_prop"))) {
				return true;
			}

		}
		return false;
	}

	// ÏÇáÉ ÊÍÏÏ ÇĞÇ ßÇä ÇáÇÓã ãÚÑİ ¡ ÇĞÇ áã íßä ãÚÑİÉ İåæ äßÑÉ
	public static boolean isMerfh(int current_word) {

		if (Fetures[current_word][1].equals("noun_prop") || Fetures[current_word][5].equals("Al_det")
				|| !(Fetures[current_word][14].equals("0"))) {

			return true;
		}

		return false;

	}

	// áÊÍÏíÏ ÇĞÇ ßÇä ÇáÇÓã äÚÊ
	public static boolean isNet(int current_word) {

		if (!(current_word == 0)) {

			if ((Fetures[current_word][1].equals("adj") || Fetures[current_word][1].equals("adj_num"))
					&& (Fetures[current_word - 1][1].equals("noun")
							|| Fetures[current_word - 1][1].equals("noun_prop"))) {
				irap[current_word][1] = "äÚÊ";

				return true;
			}

			else if (Fetures[current_word][1].equals("noun") && !irap[current_word - 1][1].equals("ãÖÇİ Çáíå")
					&& Fetures[current_word - 1][1].equals("noun")
					&& ((isMerfh(current_word) && isMerfh(current_word - 1))
							|| (!isMerfh(current_word) && !isMerfh(current_word - 1)))) {
				return true;
			}
		}
		return false;

	}

	// ÏÇáÉ ÊÍÏÏ ÇĞÇ ßÇäÊ ÇáßáãÉ åí ÇáÇÎíÑÉ ¡ íÓÊİÇÏ İí ÊÍÏíÏ ÇáãÖÇİ
	public static boolean isLastWord(int current_word) {

		if (current_word == words.length - 1) {
			return true;
		}
		return false;

	}

	public static boolean isThereMobtaBefore(int current_word) {

		for (int i = 0; i < current_word; i++) {

			if (irap[i][1].equals("ãÈÊÏÃ")) {
				return true;
			}

		}

		return false;

	}

	// ÏÇáÉ áÇÒÇáÉ ÇÎÑ ÍÑİ ãä ÇáßáãÉ ¡ æÓÊßæä ãİíÏÉ ááÊÚÑİ Úáì ÊÇÁ ÇáİÇÚá
	public static String deleteLastChar(String str) {
		if (str != null && str.length() > 0) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

	// ÏÇáÉ áÇÒÇáÉ ÇáÊÔßíá ãä ßáãÉ ÇáÌĞÑ ãä ãÏÇãíÑÇ
	public static String removeDiacritics(String str) {

		str = str.replaceAll("ğ", "");
		str = str.replaceAll(" ø", "");
		str = str.replaceAll("ñ", "");
		str = str.replaceAll("ò", "");
		str = str.replaceAll("ó", "");
		str = str.replaceAll("õ", "");
		str = str.replaceAll("ö", "");
		str = str.replaceAll("ú", "");

		// ÇÚãá ãÔßáÉ ÍÑİ ÇáÇáİ ÈÇáÊÚÑİ

		return str;

	}

	public static boolean isVerbHasPronoun(int current_word) {

		// ÖãíÑ íÊßæä ãä ÍÑİ æÇÍÏ ÈÇáäåÇíÉ
		if (removeDiacritics(Fetures[current_word][15]).equals(deleteLastChar(Fetures[current_word][0]))) {

			// ÊÇÁ ÇáİÇÚá
			if (Fetures[current_word][0].substring(Fetures[current_word][0].length() - 1).equals("Ê")) {
				
				// ÊÍŞŞ Ãä ÊÇÁ ÇáİÚá åí ÊÇÁ ÊÃäíË
				for (int i = current_word+1; i < numOfWords; i++) {

					if ( Fetures[i][1].equals("noun_prop")
							) {
						 
						if (Fetures[i][10].equals("f")) {
							 
							irap[i][1] = "İÇÚá";
							return false;
						}

						break;
					}
				}
			 
				pronoun_type = "Ê";
				return true;
			}

			// Çáİ ÇáÇËäíä
			else if (Fetures[current_word][0].substring(Fetures[current_word][0].length() - 1).equals("Ç")) {
				pronoun_type = "Ç";
				return true;
			}

			// äæä ÇáäÓæÉ
			else if (Fetures[current_word][0].substring(Fetures[current_word][0].length() - 1).equals("ä")) {
				pronoun_type = "ä";
				return true;
			}

		}

		// ÖãíÑ íÊßæä ãä ÍÑİíä ÈÇáäåÇíÉ
		else if (removeDiacritics(Fetures[current_word][15])
				.equals(deleteLastChar(deleteLastChar(Fetures[current_word][0])))) {

			 
			// æÇæ ÇáÌãÇÚÉ
			if (Fetures[current_word][0].substring(Fetures[current_word][0].length() - 2).equals("æÇ")) {
				pronoun_type = "æÇ";
				return true;
			}

			// äÇ ááãÊßáã
			else if (Fetures[current_word][0].substring(Fetures[current_word][0].length() - 2).equals("äÇ")) {
				pronoun_type = "äÇ";
				return true;
			}

			// íÇÁ ÇáãÄäËÉ
			else if (Fetures[current_word][0].substring(Fetures[current_word][0].length() - 2).equals("Êí")) {
				pronoun_type = "í";
				return true;
			}

		}

		return false;

	}

	// ááÊÍŞŞ ÈæÌæÏ İÇÚá ÈÇáÌãáÉ ãä ÇÌá ÇÚÑÇÈ ÇáãİÚæá Èå
	public static boolean isThereFael() {

		for (int i = 0; i < currentWord; i++) {

			if (irap[i][1].equals("İÇÚá")) {

				return true;

			}
		}

		return false;

	}

	public static boolean checkProclitic() {

		if (Fetures[currentWord][3].equals("wa_conj") || Fetures[currentWord][3].equals("wa_part")
				|| Fetures[currentWord][3].equals("wa_sub")) {
			irap[currentWord][0] = "ÇáæÇæ: ÍÑİ ÚØİ";
			irap[currentWord][1] = Fetures[currentWord][0].substring(1) + ": " + irap[currentWord - 1][1];
		}

		else if (Fetures[currentWord][3].equals("fa_conj") || Fetures[currentWord][3].equals("fa_conn")
				|| Fetures[currentWord][3].equals("fa_rc")) {
			irap[currentWord][0] = "ÇáİÇÁ: ÍÑİ ÚØİ";

		}

		else if (Fetures[currentWord][4].equals("bi_part") || Fetures[currentWord][4].equals("bi_prep")
				|| Fetures[currentWord][4].equals("bi_prog")) {
			irap[currentWord][0] = "ÇáÈÇÁ: ÍÑİ ÌÑ";
			irap[currentWord][1] = "ÇÓã ãÌÑæÑ";
		}

		else if (Fetures[currentWord][4].equals("ka_prep")) {
			irap[currentWord][0] = "ÇáßÇİ: ÍÑİ ÌÑ";
			if (Fetures[currentWord][1].equals("noun") || Fetures[currentWord][1].equals("noun_num")
					|| Fetures[currentWord][1].equals("noun_prop")) {
				irap[currentWord][1] = "ÇÓã ãÌÑæÑ";
			}
		}

		else if (Fetures[currentWord][4].equals("sa_fut")) {
			irap[currentWord][0] = "ÇáÓíä: ÍÑİ ÇÓÊŞÈÇá";

		}

		else if (Fetures[currentWord][4].equals("la_emph") || Fetures[currentWord][4].equals("la_prep")
				|| Fetures[currentWord][4].equals("la_rc") || Fetures[currentWord][4].equals("libi_prep")
				|| Fetures[currentWord][4].equals("li_jus") || Fetures[currentWord][4].equals("li_prep")) {

			irap[currentWord][0] = "ÇááÇã: ÍÑİ ÌÑ";

			if (Fetures[currentWord][1].equals("noun") || Fetures[currentWord][1].equals("noun_num")
					|| Fetures[currentWord][1].equals("noun_prop")) {
				irap[currentWord][1] = "ÇÓã ãÌÑæÑ";
			}

		}

		return false;
	}

}


