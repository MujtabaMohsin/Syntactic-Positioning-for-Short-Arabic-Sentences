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
           
        
        
 
        DefaultTableModel model = new DefaultTableModel();       // Model �� ����� ���� ������ .DefaultTableModel ��� ���� ������ ���� �� ������
        model.setDataVector(Fetures, columns);                      // Model ��� ���� ���� ��������� ����� ���
        JTable table = new JTable(model);                        // model ��� ���� ������ ���� ���� ��� �������� �������� �� ���
        JScrollPane tableScroller= new JScrollPane(table);       // ��� ������ Scroll Bar ����� ���� JScrollPane ����� ���� �� ������ table ��� ����� ���
 
        table.setRowHeight(41);
        
        tableScroller.setBounds(60, 40, 800, 900);               // frame �� ��� tableScroller ��� ���� ������ ��� � ���� ���
 
        Font font3 = new Font("Arial", Font.CENTER_BASELINE, 16);
        
         
        table.setFont(font3);
        f.add(tableScroller);                                // frame �� ��� tableScroller ��� ����� ���

		
		
		
		
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
