import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class GUI extends Main_Irab {

	static Madamira_Morphological_Analysis2 madamira = new Madamira_Morphological_Analysis2();
	static Stanford_Syntactic_Analysis2 stanford = new Stanford_Syntactic_Analysis2();
	static Main_Irab ierab = new Main_Irab();
	static Madamira_GUI mada_gui = new Madamira_GUI();

	static JFrame f;
	static JTextField input;
	static int word_num = 10;
	static int word_info = 2;
	static String[][] table_info, data2, data3;
	static DefaultTableModel model, model2, model3;
	static JTable table, table2, table3;
	static JScrollPane tableScroller, tableScroller2, tableScroller3;
	static JButton irp, help, models, about, exit;
	static JDialog dialog, dialog2, dialog3;
	static JButton button;
	static JLabel label1, label1_, label2, label3, label4, label5, label6, label7, label8, label9, label10;
	static int z;

	public static void mainGUI(String[] args) {

		f = new JFrame();

		f.setSize(700, 680);

		f.setLocationRelativeTo(null);
		f.setLayout(null);

		input = new JTextField();
		input.setBounds(242, 24, 425, 50);
		input.setHorizontalAlignment(JTextField.RIGHT);

		String columns[] = { "������ ��������", "������" };
		table_info = new String[word_num][word_info];

		for (String[] row : table_info)
			Arrays.fill(row, "");

		model = new DefaultTableModel();
		model.setDataVector(table_info, columns);
		table = new JTable(model);

		tableScroller = new JScrollPane(table);
		table.setRowHeight(40);
		table.getColumnModel().getColumn(0).setPreferredWidth(311);
		table.getColumnModel().getColumn(1).setPreferredWidth(11);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableScroller.setBounds(167, 95, 500, 425);

		Font font3 = new Font("Calibri", Font.CENTER_BASELINE, 22);
		Font font2 = new Font("Calibri", Font.CENTER_BASELINE, 18);
		Font font4 = new Font("Calibri", Font.CENTER_BASELINE, 33);

		table.setFont(font3);
		input.setFont(font3);

		f.add(tableScroller, BorderLayout.CENTER);
		f.add(input);

		irp = new JButton("����");
		help = new JButton("������");
		irp.setBounds(135, 25, 88, 40);
		help.setBounds(25, 25, 88, 40);
		irp.setFont(font2);
		help.setFont(font2);

		models = new JButton("�����");
		about = new JButton("�� �������");
		exit = new JButton("����");

		models.setBounds(480, 550, 130, 70);
		about.setBounds(280, 550, 140, 70);
		exit.setBounds(80, 550, 130, 66);

		models.setFont(font2);
		about.setFont(font2);
		exit.setFont(font2);

		f.add(irp);
		f.add(help);

		f.add(models);
		f.add(about);
		f.add(exit);

		irp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (String[] row : table_info)
					Arrays.fill(row, "");

				// Run Madamira
				madamira.morphologicalAnalysis(input.getText());

				// mada_gui.gui();

				// Run Stanford
				stanford.syntacticAnalysis(args);
				currentWord = 0;
				w = 0;
				t = 0;
				ierab.doIraab();

				for (int i = 0; i < numOfWords; i++) {

					table_info[i][0] = irap[i][0] + " " + irap[i][1] + " " + irap[i][4] + " " + irap[i][5];
					table_info[i][1] = words[i];

				}

				model.setDataVector(table_info, columns);
				table = new JTable(model);

				tableScroller = new JScrollPane(table);

			}
		});

		help.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				dialog = new JDialog(f);

				Font font1 = new Font("Calibri", Font.CENTER_BASELINE, 2);
				label1 = new JLabel("<html> <div align=right width='300' ><h1>����� �������:</h1></div></html>");
				label1_ = new JLabel("<html> <div align=right  width='300' ><h2>1- ���� ���� ����� ����� �� ��� ����� �� ���</h2></div></html>");
				JLabel label2_ = new JLabel("<html> <div align=right  width='300' ><h2>2- ���� �� ����</h2></div></html>");
				label2 = new JLabel("<html> <div align=right  width='300' ><h2>3- ����� ���� ����.</h2></div></html>");
				label3 = new JLabel(
						"<html> <div align=right  width='300' ><h2>4- ����� ������� ������� ��� ������ ��������.</h2></div></html>");
				label4 = new JLabel(
						"<html> <div align=right  width='300' ><h1>������ ��� ����� ����:</h1></div></html>");
				label5 = new JLabel(
						"<html> <div align=right  width='300' ><h2>1- ������ �� ���� �������.</h2></div></html>");
				label6 = new JLabel(
						"<html> <div align=right  width='300' ><h2>2- ������� ������ �������.</h2></div></html>");
				label7 = new JLabel(
						"<html> <div align=right  width='300' ><h2>3- ������ �� ���� ����� �����. </h2></div></html>");

				label8 = new JLabel(
						"<html> <div align=right  width='300' ><h2>4- ��� ������� ������ �������. </h2></div></html>");

				label9 = new JLabel(
						"<html> <div align=right  width='300' ><h2>5- ������ �� ����  ��� ��� ������ ������ . </h2></div></html>");
				
				label10 = new JLabel(
						"<html> <div align=right  width='300' ><h2>6- ������ �� ���� ��� ��� ��� �������� . </h2></div></html>");

				 
				label1.setFont(font1);
				label1_.setFont(font1);
				label2_.setFont(font1);
				label2.setFont(font1);
				label3.setFont(font1);
				label4.setFont(font1);
				label5.setFont(font1);
				label6.setFont(font1);
				label7.setFont(font1);
				label8.setFont(font1);
				label9.setFont(font1);
				label10.setFont(font1);
				
				button = new JButton("�����");

				// Dialog ��� ���� ������ ���� � ���� ������� ���� ������� �� ���
				label1.setBounds(210, 0, 300, 100);
				label1_.setBounds(210, 30, 333, 160);
				label2_.setBounds(210, 70, 333, 160);
				label2.setBounds(210, 100, 333, 160);
				label3.setBounds(210, 130, 333, 160);
				label4.setBounds(210, 210, 333, 160);
				label5.setBounds(210, 240, 333, 160);
				label6.setBounds(210, 290, 333, 160);
				label7.setBounds(210, 320, 333, 160);
				label8.setBounds(210, 350, 333, 160);
				label9.setBounds(210, 380, 333, 160);
				label10.setBounds(210, 410, 333, 160);

				
				button.setBounds(251, 571, 70, 30);

				
				
				// Dialog ��� ���� ������ ��� ����� ���
				dialog.setLocationRelativeTo(f);
				dialog.setLayout(null);
				dialog.setSize(561, 700);
				dialog.setTitle("������");

				// Dialog ����� ��� button � ��� label ��� ���� ���� ���
				dialog.add(label1);
				dialog.add(label1_);
				dialog.add(label2_);
				dialog.add(label2);
				dialog.add(label3);
				dialog.add(label4);
				dialog.add(label5);
				dialog.add(label6);
				dialog.add(label7);
				dialog.add(label8);
				dialog.add(label9);
				dialog.add(label10);

				dialog.add(button);

				// button ��� ����� ��� ��� Dialog ��� ���� ��� ���� ����� ���
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dialog.setVisible(false);
					}
				});

				dialog.setVisible(true);

			}

		});

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				f.setVisible(false);
			}
		});

		about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dialog2 = new JDialog(f);

				Font font1 = new Font("Calibri", Font.CENTER_BASELINE, 2);
				label1 = new JLabel("<html> <div align=right width='300' ><h1>����� �������</h1></div></html>");
				label2 = new JLabel(
						"<html> <div align=right width='300' ><h2>����� ������ ������� ��������� ������� ������</h2></div></html>");
				label3 = new JLabel(
						"<html> <div align=right width='400' ><h2>����� ������� ������� ������ ����� ��� ������� ��������</h2></div></html>");
				label4 = new JLabel("<html> <div align=right width='155' ><h2>����� ������� 192</h2></div></html>");
				label5 = new JLabel(
						"<html> <div align=right width='300' ><h2>����� ������: ����� ������</h2></div></html>");
				label6 = new JLabel(
						"<html> <div align=right width='300' ><h2>����� �������: ���� �������</h2></div></html>");

				label1.setFont(font1);
				label2.setFont(font1);
				label3.setFont(font1);
				label4.setFont(font1);
				label5.setFont(font1);
				label6.setFont(font1);

				button = new JButton("�����");

				// Dialog ��� ���� ������ ���� � ���� ������� ���� ������� �� ���

				label1.setBounds(24, 10, 522, 52);
				label2.setBounds(107, 70, 522, 52);
				label3.setBounds(39, 120, 522, 52);
				label4.setBounds(167, 170, 522, 52);
				label5.setBounds(58, 220, 522, 52);
				label6.setBounds(67, 270, 522, 52);

				button.setBounds(231, 333, 70, 30);

				// Dialog ��� ���� ������ ��� ����� ���
				dialog2.setLocationRelativeTo(f);
				dialog2.setLayout(null);
				dialog2.setSize(600, 450);
				dialog2.setTitle("�� �������");

				// Dialog ����� ��� button � ��� label ��� ���� ���� ���
				dialog2.add(label1);
				dialog2.add(label2);
				dialog2.add(label3);
				dialog2.add(label4);
				dialog2.add(label5);
				dialog2.add(label6);

				dialog2.add(button);

				// button ��� ����� ��� ��� Dialog ��� ���� ��� ���� ����� ���
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dialog2.setVisible(false);
					}
				});

				dialog2.setVisible(true);
			}
		});

		models.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dialog3 = new JDialog(f);

				Font font1 = new Font("Calibri", Font.CENTER_BASELINE, 2);
				int word_num2 = 20;
				int word_info2 = 2;

				dialog3.setLocationRelativeTo(f);
				dialog3.setLayout(null);
				dialog3.setSize(866, 699);
				dialog3.setTitle("����� �������");

				String columns2[] = { "������", "���" };

				JButton bb = new JButton();

				String data[][] = { { "����� �����", "1" }, { "���� ����� �����", "2" }, { "��� ���� �������", "3" },
						{ "���� ��� �����", "4" }, { "����� ����� �����", "5" },
						{ "���� ����� ������� �� ������", "6" }, { "��� ������ ������", "7" },
						{ "��� ���� ���� ����� ������", "8" }, { "����� ���� ������", "9" },
						{ "��� ����� ������ ������ �� ���� �������", "10" }, { "��� ������ ����� ��� �����", "11" },
						{ "���� �� ���� ������", "12" }, { "��� ���� ���� ����� ������", "13" },
						{ "���� ������ �����", "14" }, { "���� ����� �����", "15" },
						{ "��� ���� ������ ��� ����", "16" }, { "��� ����� �����", "17" },
						{ "��� ���� ��� �� ���� ����", "18" }, { "��� ����� ����� �����", "19" },
						{ "����� �������� �����", "20" } };

				Font font5 = new Font("Calibri", Font.CENTER_BASELINE, 18);
				Font font6 = new Font("Calibri", Font.CENTER_BASELINE, 15);
				model2 = new DefaultTableModel();
				model2.setDataVector(data, columns2);

				table2 = new JTable(model2);

				table2.setRowHeight(31);
				table2.getColumnModel().getColumn(0).setPreferredWidth(241);
				table2.getColumnModel().getColumn(1).setPreferredWidth(11);
				tableScroller2 = new JScrollPane(table2);
				table2.setFont(font5);
				table2.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
				tableScroller2.setBounds(563, 0, 277, 649);

				dialog3.add(tableScroller2);

				BorderLayout b = new BorderLayout();
				FlowLayout f = new FlowLayout();

				JButton btns[] = new JButton[40];

				for (int i = 0; i < btns.length; i++) {

					btns[i] = new JButton("����");

				}

				int y = 24;
				for (int i = 0; i < 20; i++) {
					btns[i].setFont(font6);
					btns[i].setBounds(477, y, 81, 24);
					y = y + 31;
					dialog3.add(btns[i]);

				}
				
				
				String data2[][] = { 
						{ "����� ����� ������", "21" }, { "����� ��� ���� ����� �����", "22" }, { "���� �� ���� �������", "23" },
						{ "��� ����� �����", "24" }, { "���� �� ���� �������", "25" }, { "���� ������ ��� �����", "26" },
						{ "���� ����� ���� �����", "27" }, { "������� ����� �����", "28" }, { "���� �� ������ �������", "29" },
						{ "���� ������ �����", "30" }, { "���� ������� �����", "31" }, { "���� ����� ����", "32" },
						{ "������� ������ ����", "33" }, { "��� ����� �����", "34" }, { "����� ������� �� ��� ������", "35" },
						{ "�������� ���� ��������", "36" }, { "��� ���� ����� ��������", "37" }, { "������ ��� �������", "38" }, 
						{ "��� ��� ����� ���� ����� ����� ������ �������", "39" },{ "��� ���� ����� ����� ���� ��� ����� �������", "40" }};
				
				
				model3 = new DefaultTableModel();
				model3.setDataVector(data2, columns2);

				table3 = new JTable(model3);

				table3.setRowHeight(31);
				table3.getColumnModel().getColumn(0).setPreferredWidth(241);
				table3.getColumnModel().getColumn(1).setPreferredWidth(11);
				tableScroller3 = new JScrollPane(table3);
				table3.setFont(font5);
				table3.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
				tableScroller3.setBounds(111, 0, 277, 649);

				dialog3.add(tableScroller3);

				
				y = 24;
				for (int i = 20; i < 40; i++) {
					btns[i].setFont(font6);
					btns[i].setBounds(25, y, 81, 24);
					y = y + 31;
					dialog3.add(btns[i]);

				}

 

				btns[0].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table2.getModel().getValueAt(0, 0).toString());
						dialog3.setVisible(false);
					}
				});

				btns[1].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table2.getModel().getValueAt(1, 0).toString());
						dialog3.setVisible(false);
					}
				});

				btns[2].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table2.getModel().getValueAt(2, 0).toString());
						dialog3.setVisible(false);
					}
				});
				
				btns[3].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table2.getModel().getValueAt(3, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[4].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table2.getModel().getValueAt(4, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[5].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table2.getModel().getValueAt(5, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[6].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table2.getModel().getValueAt(6, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[7].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table2.getModel().getValueAt(7, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[8].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table2.getModel().getValueAt(8, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[9].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table2.getModel().getValueAt(9, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[10].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table2.getModel().getValueAt(10, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[11].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table2.getModel().getValueAt(11, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[12].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table2.getModel().getValueAt(12, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[13].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table2.getModel().getValueAt(13, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
 

				
				btns[14].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table2.getModel().getValueAt(14, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[15].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table2.getModel().getValueAt(15, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[16].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table2.getModel().getValueAt(16, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[17].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table2.getModel().getValueAt(17, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[18].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table2.getModel().getValueAt(18, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[19].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table2.getModel().getValueAt(19, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[20].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table3.getModel().getValueAt(0, 0).toString());
						dialog3.setVisible(false);
					}
				});
 
				
 
				btns[21].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table3.getModel().getValueAt(1, 0).toString());
						dialog3.setVisible(false);
					}
				});

				btns[22].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table3.getModel().getValueAt(2, 0).toString());
						dialog3.setVisible(false);
					}
				});
				
				btns[23].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table3.getModel().getValueAt(3, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[24].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table3.getModel().getValueAt(4, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[25].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table3.getModel().getValueAt(5, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[26].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table3.getModel().getValueAt(6, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[27].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table3.getModel().getValueAt(7, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[28].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table3.getModel().getValueAt(8, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[29].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table3.getModel().getValueAt(9, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[30].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table3.getModel().getValueAt(10, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[31].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table3.getModel().getValueAt(11, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[32].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table3.getModel().getValueAt(12, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[33].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table3.getModel().getValueAt(13, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
 

				
				btns[34].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table3.getModel().getValueAt(14, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[35].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table3.getModel().getValueAt(15, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[36].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table3.getModel().getValueAt(16, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[37].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table3.getModel().getValueAt(17, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[38].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table3.getModel().getValueAt(18, 0).toString());
						dialog3.setVisible(false);
					}
				});

				
				btns[39].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						input.setText(table3.getModel().getValueAt(19, 0).toString());
						dialog3.setVisible(false);
					}
				});

				

				dialog3.setVisible(true);
			}
		});

		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}