package FindingUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Color;

import Astar.*;
import GBFS.*;
import Hasil.Hasil;
import UCS.*;
import java.awt.event.ActionEvent;
import State.*;

public class FindingUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fieldAwal;
	private JTextField fieldTujuan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindingUI frame = new FindingUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FindingUI() {
		setResizable(false);
		setTitle("Word Ladder Solver");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 725, 512);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		fieldAwal = new JTextField();
		fieldAwal.setBounds(262, 82, 181, 42);
		contentPane.add(fieldAwal);
		fieldAwal.setColumns(10);
		
		JLabel labelAwal = new JLabel("Masukkan Kata Awal   :");
		labelAwal.setFont(new Font("SansSerif", Font.PLAIN, 20));
		labelAwal.setBounds(27, 82, 218, 42);
		contentPane.add(labelAwal);
		
		JLabel labelTujuan = new JLabel("Masukkan Kata Tujuan :");
		labelTujuan.setFont(new Font("SansSerif", Font.PLAIN, 20));
		labelTujuan.setBounds(27, 163, 218, 42);
		contentPane.add(labelTujuan);
		
		fieldTujuan = new JTextField();
		fieldTujuan.setColumns(10);
		fieldTujuan.setBounds(262, 163, 181, 42);
		contentPane.add(fieldTujuan);
		
		JRadioButton ucsButton = new JRadioButton("UCS");
		ucsButton.setSelected(true);
		ucsButton.setBackground(new Color(255, 255, 128));
		ucsButton.setBounds(504, 99, 109, 23);
		contentPane.add(ucsButton);
		
		JRadioButton gbfsButton = new JRadioButton("GBFS");
		gbfsButton.setBackground(new Color(255, 255, 128));
		gbfsButton.setBounds(504, 147, 109, 23);
		contentPane.add(gbfsButton);
		
		JRadioButton asButton = new JRadioButton("A*");
		asButton.setBackground(new Color(255, 255, 128));
		asButton.setBounds(504, 194, 109, 23);
		contentPane.add(asButton);
		
		ButtonGroup G = new ButtonGroup();
		G.add(ucsButton);
		G.add(gbfsButton);
		G.add(asButton);
		
		JLabel lblNewLabel = new JLabel("Select a searching algorithm :");
		lblNewLabel.setBounds(504, 74, 153, 14);
		contentPane.add(lblNewLabel);
		
		JButton solveButton = new JButton("SOLVE");
		solveButton.setBounds(504, 234, 89, 23);
		contentPane.add(solveButton);
		
		JButton clearButton = new JButton("CLEAR");
		clearButton.setBounds(603, 234, 89, 23);
		contentPane.add(clearButton);
		
		JLabel PenyemangatTubes = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/ptrr.png")).getImage();
		PenyemangatTubes.setIcon(new ImageIcon(img));
		PenyemangatTubes.setBounds(574, 322, 135, 151);
		contentPane.add(PenyemangatTubes);
		
		JLabel lblWiki = new JLabel("WORD LADDER SOLVER");
		lblWiki.setFont(new Font("Dubai Medium", Font.BOLD, 20));
		lblWiki.setBounds(248, 11, 243, 48);
		contentPane.add(lblWiki);
		
		JLabel resultLabel = new JLabel("Result              :");
		resultLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		resultLabel.setBounds(27, 292, 218, 42);
		contentPane.add(resultLabel);
		
		JLabel execTimeLabel = new JLabel("Execution Time :");
		execTimeLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		execTimeLabel.setBounds(27, 336, 218, 42);
		contentPane.add(execTimeLabel);
		
		JLabel nodeAmmountLabel = new JLabel("Node Ammount :");
		nodeAmmountLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		nodeAmmountLabel.setBounds(27, 377, 218, 42);
		contentPane.add(nodeAmmountLabel);
		
		JLabel processingLabel = new JLabel("");
		processingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		processingLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		processingLabel.setBounds(62, 255, 595, 23);
		contentPane.add(processingLabel);
		
		JLabel resultLable = new JLabel("");
		resultLable.setBounds(220, 302, 393, 32);
		contentPane.add(resultLable);
		
		JLabel exectimeLable = new JLabel("");
		exectimeLable.setBounds(220, 346, 393, 32);
		contentPane.add(exectimeLable);
		
		JLabel nodeLable = new JLabel("");
		nodeLable.setBounds(220, 387, 393, 32);
		contentPane.add(nodeLable);
		
		// Logic section
        // Add ActionListener to the solve button
		solveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringCheck checker = new StringCheck("words_alpha.txt");
				Long start = System.currentTimeMillis();
                String _origin = fieldAwal.getText().toUpperCase();
                String _tujuan = fieldTujuan.getText().toUpperCase();
                UCS ucsObject = new UCS();
                GBFS gbfsObject = new GBFS();
                Astar astarObject = new Astar();
                Hasil result = new Hasil();
                if(_origin.length() == _tujuan.length()) {
                	if(checker.isValidWord(_origin)&&checker.isValidWord(_tujuan)) {
		                if(ucsButton.isSelected()) {
		                	processingLabel.setText("Processing using UCS...");
		                	result = ucsObject.find(_origin, _tujuan);
		                } else if(gbfsButton.isSelected()){
		                	processingLabel.setText("Processing using GBFS...");
		                	result = gbfsObject.find(_origin, _tujuan);
		                } else {
		                	processingLabel.setText("Processing using A*...");
		                	result = astarObject.find(_origin, _tujuan);
		                }
		                Long finish = System.currentTimeMillis();
		                Long timeElapsed = finish-start;
		                exectimeLable.setText(timeElapsed.toString());
		                resultLable.setText(result.Path.toString());
		                nodeLable.setText(((Integer)(result.ammountOfNode)).toString());
                	} else {
                		processingLabel.setText("NOT A REAL ENGLISH WORD");
                	}
				} else {
					processingLabel.setText("ERROR, ORIGIN WORD MUST BE THE SAME LENGTH AS THE TARGET WORD");
				}
			}
		});
		
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processingLabel.setText("");
				exectimeLable.setText("");
				resultLable.setText("");
				nodeLable.setText("");
				fieldAwal.setText("");
				fieldTujuan.setText("");
			}
		});
	}
}
