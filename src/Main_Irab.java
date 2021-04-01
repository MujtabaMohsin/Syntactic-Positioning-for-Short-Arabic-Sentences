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

		 

		// �� ������ �� ������ �
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

		// ����� ��� ���������
		// w = ��� ������ �� ��������
		// t = ��������

		 

		for (t = 0; t < number_of_strucures; t++) {

			for (w = 0; w < parse.getNodeNumber(treeorder_acc_type).getChild(t).getLeaves().size(); w++) {

				// ����� ��� ������ ��� � ��� � ���
				// �� ����� ������ �������� �� ���� ������ ��������
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

		// ����� �������
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

	// ���� ����� ������ �������
	public static void noun_irap() {
		
		if (Fetures[currentWord][0].equals("����")) {
			irap[currentWord][1] = "��� ������� �� ���";
		}

		// �� ���� ��� ����� ����� ��� ����� �������� �� �������
		if (!Fetures[currentWord][3].equals("0") || !Fetures[currentWord][4].equals("0")) {
			checkProclitic();
		}

		if (Fetures[currentWord][1].equals("pron_dem")) {
			irap[currentWord][1] = "��� �����";
		}

		if (Fetures[currentWord][1].equals("pron_rel") || Fetures[currentWord][1].equals("pron_interrog")) {
			irap[currentWord][1] = "��� �����";
		}

		// ��� ��� ����� ����� ������ ��� �����
		if (irap[currentWord][1].equals("") && t == 0 && currentWord == 0) {

			irap[currentWord][1] = "�����";
		}

		// ���� ��� ��� ����� �����
		if (isMdaf(currentWord)) {

			irap[currentWord][5] = "��� ����";

		}

		// ��� ���� ������ ������� ���� �������� ���� ����
		if (irap[currentWord][1].equals("") && !(currentWord == 0) && words.length > 2) {

			if (irap[currentWord - 1][5].equals("��� ����")) {

				irap[currentWord][1] = "���� ����";

			}
		}

		// ����� �����
		// ����� ��� ���� ��� �� ���� ���� ��� �������
		if (irap[currentWord][1].equals("") && sen_type.equals("NP") && isThereMobtaBefore(currentWord)) {

			// ��� ���� ������ ����� ��� �� ������ ����� ����
			if (words.length == 2 && (Fetures[0][1].equals("noun") || Fetures[0][1].equals("noun_prop"))
					&& ((Fetures[1][1].equals("noun") || Fetures[1][1].equals("noun_prop")
							|| Fetures[1][1].equals("adj")))) {

				irap[currentWord][1] = "���";
			}

			// ��� ��� ����� �� ����� ������ 1 �������� ������ ������� 0
			else if (words.length > 2 && t == 1
					&& parse.getNodeNumber(treeorder_acc_type).getChild(t - 1).getLeaves().size() == 2 && w == 0
					&& Fetures[currentWord][14].equals("0")) {
				irap[currentWord][1] = "���";
			}

			// ��� ��� ����� ������ ���� ��� ������� ������� ����
			else if (words.length > 2 && t == 1
					&& parse.getNodeNumber(treeorder_acc_type).getChild(t - 1).getLeaves().size() == 1 && w == 0
					&& Fetures[currentWord][14].equals("0")) {
				irap[currentWord][1] = "���";
			}

			// ��� ��� �� ���� ����� ����� ����
			else if (words.length > 2 && t == 1 && (Fetures[currentWord - 1][1].equals("noun")
					|| Fetures[currentWord - 1][1].equals("noun_prop") || Fetures[currentWord - 1][1].equals("adj"))
					&& w == 1) {
				irap[currentWord][1] = "���";
			}

			// ��� ��� ������� ����� �� ������ ������� ���� ���� ����
			else if (words.length > 2 && t == 1 && !Fetures[currentWord][14].equals("0") && w == 0) {
				irap[currentWord][1] = "����� ���";
				irap[currentWord + 1][1] = "���";
			}

		}

		// �� ������ ���
		if (irap[currentWord][1].equals("") && isNet(currentWord)) {
			irap[currentWord][1] = "���";
		}

		// ����� ������
		if (sen_type.equals("VP") && !(currentWord == 0) && !isVerbHasPronoun(0)) {

			// ��� ����
			if (t == 1 && w == 0) {
				irap[currentWord][1] = "����";
			}

			else if (t == 0 && w == 1) {
				irap[currentWord][1] = "����";
			}

		}

		// ��� ��� ����� ���� �� ��� ��� �����
		if (!(currentWord == 0)) {

			if (irap[currentWord - 1][1].equals("��� ��")) {

				irap[currentWord][1] = "��� �����";

			}
		}

		 

		// ������� ��
		if (!(currentWord == 0) && irap[currentWord][1].equals("") && !(Fetures[currentWord][1].equals("adj"))) {

			if (t == 2 && w == 0) {

				irap[currentWord][1] = "����� ��";

			}

			else {

				if (sen_type.equals("VP")) {

					if (isVerbHasPronoun(0)) {
						irap[currentWord][1] = "����� ��";
					}

					else if (t == 1 && w != 0 && isThereFael()) {

						irap[currentWord][1] = "����� ��";
					}

					else if (isThereFael()) {
						irap[currentWord][1] = "����� ��";
					}

				}

				else {

					if (currentWord > 1) {

						irap[currentWord][1] = "����� ��";
					}

				}

			}

		}

	}

	public static void verb_irap() {

		String anotherAsp = output.getOutDoc().getOutSeg().get(0).getWordInfo().getWord().get(currentWord).getAnalysis()
				.get(0).getMorphFeatureSet().getAsp();

		// ����� �������
		if (!Fetures[currentWord][3].equals("0") || !Fetures[currentWord][4].equals("0")) {
			checkProclitic();
		}

		// ������� �������

		if (Fetures[currentWord][7].equals("p")) {

			irap[currentWord][1] = "��� ���";

		}

		else if (Fetures[currentWord][7].equals("i")) {

			irap[currentWord][1] = "��� �����";
		}

		// ��� �� ����� �������� ��� ��� �����
		else if (Fetures[currentWord][7].equals("na")) {
			Fetures[currentWord][7] = anotherAsp;

			if (Fetures[currentWord][7].equals("p")) {

				irap[currentWord][1] = "��� ���";

			}

			else if (Fetures[currentWord][7].equals("i")) {

				irap[currentWord][1] = "��� �����";
			}

			// ��� �� ��� �� ����� ���� ��� ����� �� ��� ��� ���
			else {

				if (words[currentWord].charAt(0) == '�' || words[currentWord].charAt(0) == '�'
						|| words[currentWord].charAt(0) == '�') {
					irap[currentWord][1] = "��� �����";
				}

				else {
					irap[currentWord][1] = "��� ���";
				}
			}

		}

		// ��� ��� ����� ����� ��� ��� ������
		if (isVerbHasPronoun(currentWord)) {

			if (pronoun_type.equals("�")) {
				irap[currentWord][4] = "����� : ���� ���� ���� ��� ����";
			}

			else if (pronoun_type.equals("��")) {
				irap[currentWord][4] = "����� : ���� ���� ���� ��� ����";
			}

			else if (pronoun_type.equals("�")) {
				irap[currentWord][4] = "����� : ���� ���� ���� ��� ����";
			}

			else if (pronoun_type.equals("��")) {
				irap[currentWord][4] = "�� : ���� ���� ���� ��� ����";
			}

			else if (pronoun_type.equals("��")) {
				irap[currentWord][4] = "����� : ���� ���� ���� ��� ����";
			}

			else if (pronoun_type.equals("�")) {
				irap[currentWord][4] = "����� : ���� ���� ���� ��� ����";
			}

		}

	}

	// ����� ��� �������
	public static void particle_irap() {

		if (Fetures[currentWord][1].equals("prep") || Fetures[currentWord][1].equals("part_dem")) {
			irap[currentWord][1] = "��� ��";
		}

		else if (Fetures[currentWord][1].equals("conj") || Fetures[currentWord][1].equals("conj_sub")) {
			irap[currentWord][1] = "��� ���";
		}

		else if (Fetures[currentWord][1].equals("part_neg") || Fetures[currentWord][1].equals("part_interrog")) {
			irap[currentWord][1] = "��� ���";
		}

		else if (Fetures[currentWord][1].equals("part_fut")) {
			irap[currentWord][1] = "��� �������";
		}

		else if (Fetures[currentWord][1].equals("part_voc")) {
			irap[currentWord][1] = "��� ����";
		}

	}

	// ����� ��� ������
	public static String sentence_type() {

		if (Fetures[0][1].equals("verb")) {
			return "VP";
		}

		else

			return "NP";

	}

	// ���� ���� ��� ��� ���� ��� �� �������

	public static boolean isThereParticle() {

		for (int i = 0; i < words.length; i++) {

			if (Fetures[i][0].equals("prep")) {

				return true;
			}
		}

		return false;

	}

 

	// ���� ���� ��� ��� ����� ����
	public static boolean isMdaf(int current_word) {

		// ����� ����� ����� ������ : 1- �������� ����� ��� ���� �� ���� � 2- ������
		// ������� ���� � 3- ������ ������� ��� ����� ���
		
		 
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

	// ���� ���� ��� ��� ����� ���� � ��� �� ��� ����� ��� ����
	public static boolean isMerfh(int current_word) {

		if (Fetures[current_word][1].equals("noun_prop") || Fetures[current_word][5].equals("Al_det")
				|| !(Fetures[current_word][14].equals("0"))) {

			return true;
		}

		return false;

	}

	// ������ ��� ��� ����� ���
	public static boolean isNet(int current_word) {

		if (!(current_word == 0)) {

			if ((Fetures[current_word][1].equals("adj") || Fetures[current_word][1].equals("adj_num"))
					&& (Fetures[current_word - 1][1].equals("noun")
							|| Fetures[current_word - 1][1].equals("noun_prop"))) {
				irap[current_word][1] = "���";

				return true;
			}

			else if (Fetures[current_word][1].equals("noun") && !irap[current_word - 1][1].equals("���� ����")
					&& Fetures[current_word - 1][1].equals("noun")
					&& ((isMerfh(current_word) && isMerfh(current_word - 1))
							|| (!isMerfh(current_word) && !isMerfh(current_word - 1)))) {
				return true;
			}
		}
		return false;

	}

	// ���� ���� ��� ���� ������ �� ������� � ������ �� ����� ������
	public static boolean isLastWord(int current_word) {

		if (current_word == words.length - 1) {
			return true;
		}
		return false;

	}

	public static boolean isThereMobtaBefore(int current_word) {

		for (int i = 0; i < current_word; i++) {

			if (irap[i][1].equals("�����")) {
				return true;
			}

		}

		return false;

	}

	// ���� ������ ��� ��� �� ������ � ������ ����� ������ ��� ��� ������
	public static String deleteLastChar(String str) {
		if (str != null && str.length() > 0) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

	// ���� ������ ������� �� ���� ����� �� �������
	public static String removeDiacritics(String str) {

		str = str.replaceAll("�", "");
		str = str.replaceAll(" �", "");
		str = str.replaceAll("�", "");
		str = str.replaceAll("�", "");
		str = str.replaceAll("�", "");
		str = str.replaceAll("�", "");
		str = str.replaceAll("�", "");
		str = str.replaceAll("�", "");

		// ���� ����� ��� ����� �������

		return str;

	}

	public static boolean isVerbHasPronoun(int current_word) {

		// ���� ����� �� ��� ���� ��������
		if (removeDiacritics(Fetures[current_word][15]).equals(deleteLastChar(Fetures[current_word][0]))) {

			// ��� ������
			if (Fetures[current_word][0].substring(Fetures[current_word][0].length() - 1).equals("�")) {
				
				// ���� �� ��� ����� �� ��� �����
				for (int i = current_word+1; i < numOfWords; i++) {

					if ( Fetures[i][1].equals("noun_prop")
							) {
						 
						if (Fetures[i][10].equals("f")) {
							 
							irap[i][1] = "����";
							return false;
						}

						break;
					}
				}
			 
				pronoun_type = "�";
				return true;
			}

			// ��� �������
			else if (Fetures[current_word][0].substring(Fetures[current_word][0].length() - 1).equals("�")) {
				pronoun_type = "�";
				return true;
			}

			// ��� ������
			else if (Fetures[current_word][0].substring(Fetures[current_word][0].length() - 1).equals("�")) {
				pronoun_type = "�";
				return true;
			}

		}

		// ���� ����� �� ����� ��������
		else if (removeDiacritics(Fetures[current_word][15])
				.equals(deleteLastChar(deleteLastChar(Fetures[current_word][0])))) {

			 
			// ��� �������
			if (Fetures[current_word][0].substring(Fetures[current_word][0].length() - 2).equals("��")) {
				pronoun_type = "��";
				return true;
			}

			// �� �������
			else if (Fetures[current_word][0].substring(Fetures[current_word][0].length() - 2).equals("��")) {
				pronoun_type = "��";
				return true;
			}

			// ��� �������
			else if (Fetures[current_word][0].substring(Fetures[current_word][0].length() - 2).equals("��")) {
				pronoun_type = "�";
				return true;
			}

		}

		return false;

	}

	// ������ ����� ���� ������� �� ��� ����� ������� ��
	public static boolean isThereFael() {

		for (int i = 0; i < currentWord; i++) {

			if (irap[i][1].equals("����")) {

				return true;

			}
		}

		return false;

	}

	public static boolean checkProclitic() {

		if (Fetures[currentWord][3].equals("wa_conj") || Fetures[currentWord][3].equals("wa_part")
				|| Fetures[currentWord][3].equals("wa_sub")) {
			irap[currentWord][0] = "�����: ��� ���";
			irap[currentWord][1] = Fetures[currentWord][0].substring(1) + ": " + irap[currentWord - 1][1];
		}

		else if (Fetures[currentWord][3].equals("fa_conj") || Fetures[currentWord][3].equals("fa_conn")
				|| Fetures[currentWord][3].equals("fa_rc")) {
			irap[currentWord][0] = "�����: ��� ���";

		}

		else if (Fetures[currentWord][4].equals("bi_part") || Fetures[currentWord][4].equals("bi_prep")
				|| Fetures[currentWord][4].equals("bi_prog")) {
			irap[currentWord][0] = "�����: ��� ��";
			irap[currentWord][1] = "��� �����";
		}

		else if (Fetures[currentWord][4].equals("ka_prep")) {
			irap[currentWord][0] = "�����: ��� ��";
			if (Fetures[currentWord][1].equals("noun") || Fetures[currentWord][1].equals("noun_num")
					|| Fetures[currentWord][1].equals("noun_prop")) {
				irap[currentWord][1] = "��� �����";
			}
		}

		else if (Fetures[currentWord][4].equals("sa_fut")) {
			irap[currentWord][0] = "�����: ��� �������";

		}

		else if (Fetures[currentWord][4].equals("la_emph") || Fetures[currentWord][4].equals("la_prep")
				|| Fetures[currentWord][4].equals("la_rc") || Fetures[currentWord][4].equals("libi_prep")
				|| Fetures[currentWord][4].equals("li_jus") || Fetures[currentWord][4].equals("li_prep")) {

			irap[currentWord][0] = "�����: ��� ��";

			if (Fetures[currentWord][1].equals("noun") || Fetures[currentWord][1].equals("noun_num")
					|| Fetures[currentWord][1].equals("noun_prop")) {
				irap[currentWord][1] = "��� �����";
			}

		}

		return false;
	}

}


