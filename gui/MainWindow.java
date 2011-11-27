package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import crossword.Crossword;
import crossword.TooLongEntryRequestedException;

import browser.CwBrowser;

public class MainWindow extends JFrame {
	public MainWindow() {
		super();
		initUI();
		setVisible(true);
	}
	
	private void initUI() {
		setSize(1024,700);
		setTitle("Generator Krzyżówek");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon("gfx/icon512x512.png").getImage());
		layout = new BorderLayout();
		mainPanel = new JPanel();
		mainPanel.setLayout(layout);
		crosswordPanel = new CrosswordPanel();
		crosswordPanel.setBackground(Color.WHITE);
		topPanel = new JPanel();
		statusBar = new StatusBar();
		
		createOptionPanel();
		createGeneratePanel();
		createFromFilePanel();
		
		topPanel.add(generate);
		topPanel.add(fromFile);
		topPanel.add(optionPanel);
		mainPanel.add(topPanel, BorderLayout.PAGE_START);
		mainPanel.add(crosswordPanel, BorderLayout.CENTER);
		mainPanel.add(statusBar, BorderLayout.PAGE_END);
		add(mainPanel);
	}
	
	private void createGeneratePanel() {
		generate = new JPanel();
		generate.setBorder(BorderFactory.createTitledBorder("Generuj"));
		
		widthSpin = new JSpinner(new SpinnerNumberModel(5,2,99,1));
		heightSpin = new JSpinner(new SpinnerNumberModel(5,2,99,1));
		
		generateBtn = new JButton("Generuj");
		generateBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(!dbFile.equals("")) {
						int w = Integer.parseInt(widthSpin.getValue().toString());
						int h = Integer.parseInt(heightSpin.getValue().toString());
						try {
							crossword = new CwBrowser().generateNewSimpleCrossword(dbFile, w, h);
							crosswordPanel.setCrossword(crossword);
							statusBar.setMessage("Wygenerowano krzyżówkę o wymiarach: "+w+"x"+h);
							optionSaveBtn.setEnabled(true);
							optionSolveBtn.setEnabled(true);
							optionPrintBtn.setEnabled(true);
						} catch (TooLongEntryRequestedException excep) {
							new ErrorDialog(topPanel,excep.getMessage()).show();
							statusBar.setMessage("Wygenerowanie krzyżówki nie powiodło się. Brak hasła o długości "+h+" liter.");
						}
				}
				else {
					new ErrorDialog(topPanel,"Nie wybrano pliku bazy! Nie mozna utworzyć krzyżówki.").show();
					statusBar.setMessage("Wybierz plik z bazą. W tym celu kliknij przycisk \"Baza\" z prawej strony u góry");
				}
			}
		});
		
		
		generate.add(new JLabel("Szerokość: "));
		generate.add(widthSpin);
		generate.add(new JLabel(" Wysokość: "));
		generate.add(heightSpin);
		generate.add(generateBtn);
	}
	
	private void createFromFilePanel() {
		fromFile = new JPanel();
		fromFile.setBorder(BorderFactory.createTitledBorder("Wczytaj z pliku"));
		
		fromFilePath = new JTextField(13);
		fromFileBtn = new JButton("...");
		fromFileLoad = new JButton("Wczytaj");
		
		fromFileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CwOpenDialog fc = new CwOpenDialog(fromFile,"./");
				int selected = fc.open();
				if(selected == CwOpenDialog.APPROVE_OPTION) {
					File selectedFile = fc.getSelectedFile();
					String filePath = selectedFile.getAbsolutePath();
					fromFilePath.setText(filePath);
				}
			}
		});
		
		fromFileLoad.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(!fromFilePath.getText().equals("")) {
					try {
						crossword = CwBrowser.load(fromFilePath.getText());
						crosswordPanel.setCrossword(crossword);
						statusBar.setMessage("Załadowano krzyżówkę: "+fromFilePath.getText());
						fromFilePath.setText("");
						optionSaveBtn.setEnabled(true);
						optionSolveBtn.setEnabled(true);
						optionPrintBtn.setEnabled(true);
					} catch(Exception excep) {
						new ErrorDialog(topPanel, excep.getMessage()).show();
					}
				} else {
					statusBar.setMessage("Nie wybrano pliku do wczytania!");
				}
			}
		});
		
		fromFile.add(fromFilePath);
		fromFile.add(fromFileBtn);
		fromFile.add(fromFileLoad);
	}
	
	private void createOptionPanel() {
		optionPanel = new JPanel();
		optionPanel.setBorder(BorderFactory.createTitledBorder("Opcje"));
		
		optionSaveBtn = new JButton("Zapisz");
		optionPrintBtn = new JButton("Drukuj");
		optionDbBtn = new JButton("Baza");
		optionSolveBtn = new JButton("Rozwiąż");
		
		optionSaveBtn.setToolTipText("Zapisz krzyżówkę");
		optionPrintBtn.setToolTipText("Wydrukuj krzyżówkę");
		optionDbBtn.setToolTipText("Wybierz plik z bazą haseł do generowania krzyżówek");
		
		optionSaveBtn.setEnabled(false);
		optionSolveBtn.setEnabled(false);
		optionPrintBtn.setEnabled(false);
		
		optionSaveBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(crossword != null) {
					try {
						new CwBrowser("./").save(crossword);
						statusBar.setMessage("Zapisano krzyżówkę w bieżącym katalogu jako: "+crossword.getUniqID()+".crossword");
					} catch (Exception e1) {
						new ErrorDialog(topPanel,e1.getMessage()).show();
						statusBar.setMessage("Zapisanie krzyżówki nie powiodło się!");
					}
				} else {
					statusBar.setMessage("Nie wczytano, anie nie wygenerowano żadnej krzyżówki. Należy to wykonać aby móc zapisać.");
				}
			}
		});
		
		optionDbBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser("./");
				int selected = fc.showOpenDialog(topPanel);
				if(selected == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					String filePath = file.getAbsolutePath();
					dbFile = filePath;
					statusBar.setMessage("Wybrano plik z bazą: "+filePath);
				} else {
					statusBar.setMessage("Nie wybrano żadnego pliku. Pamiętaj, aby wygenerować krzyżówki należy wybrać plik z bazą!");
				}
			}
		});
		
		optionSolveBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				crosswordPanel.solve();
			}
		});
		
		optionPanel.add(optionSaveBtn);
		optionPanel.add(optionPrintBtn);
		optionPanel.add(optionDbBtn);
		optionPanel.add(optionSolveBtn);
	}
	
	BorderLayout layout;
	
	JPanel mainPanel;
	JPanel topPanel;
	StatusBar statusBar;
	
	JPanel generate;
	JSpinner widthSpin;
	JSpinner heightSpin;
	JButton generateBtn;
	
	JPanel fromFile;
	JTextField fromFilePath;
	JButton fromFileBtn;
	JButton fromFileLoad;
	
	JPanel optionPanel;
	JButton optionSaveBtn;
	JButton optionPrintBtn;
	JButton optionDbBtn;
	JButton optionSolveBtn;
	
	CrosswordPanel crosswordPanel;
	
	Crossword crossword = null;
	String dbFile = "";
}
