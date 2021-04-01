import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Madamira_GUI extends Madamira_Morphological_Analysis2 {

	public void gui() {

		JFrame f = new JFrame();

	

		f.setSize(1080, 720);
		 
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		
		
		 
        String columns[] = {"word","Pos","prc3","prc2","prc1","prc0","Per","Asp","Vox","Mod","Gen","Num","Stt","Cas","enc0","Stem"};  
           
        
        
 
        DefaultTableModel model = new DefaultTableModel();       // Model √Ì ﬂ√‰‰« ﬁ„‰« » ⁄—Ì› .DefaultTableModel Â‰« ﬁ„‰« »≈‰‘«¡ ﬂ«∆‰ „‰ «·ﬂ·«”
        model.setDataVector(Fetures, columns);                      // Model Â‰« ﬁ„‰« »Ê÷⁄ «·„’›Ê›«  »œ«Œ· «·‹
        JTable table = new JTable(model);                        // model Â‰« ﬁ„‰« » ⁄—Ì› ÃœÊ· „»‰Ì ⁄·Ï «·»Ì«‰«  «·„ÊÃÊœ… ›Ì «·‹
        JScrollPane tableScroller= new JScrollPane(table);       // ⁄‰œ «·Õ«Ã… Scroll Bar ·÷„«‰ ŸÂÊ— JScrollPane »œ«Œ· ﬂ«∆‰ „‰ «·ﬂ·«” table Â‰« Ê÷⁄‰« «·‹
 
        table.setRowHeight(41);
        
        tableScroller.setBounds(60, 40, 800, 900);               // frame ›Ì «·‹ tableScroller Â‰« ﬁ„‰« » ÕœÌœ ÕÃ„ Ê „Êﬁ⁄ «·‹
 
        Font font3 = new Font("Arial", Font.CENTER_BASELINE, 16);
        
         
        table.setFont(font3);
        f.add(tableScroller);                                // frame ›Ì «·‹ tableScroller Â‰« √÷›‰« «·‹

		
		
		
		
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
