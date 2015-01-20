package view.panneauComposite;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import controllers.PartieControlleur;
import event.CandyEvent;

import java.awt.Color;

import listener.Observerateur;

public class PanneauMenuPrincipal extends JPanel implements ActionListener, Observerateur{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField nomJoueur;
	JButton btnStart ;
	PartieControlleur partieControlleur ;
	private JLabel lblError;
	public PanneauMenuPrincipal(PartieControlleur partieControlleur) {
		
		this.partieControlleur = partieControlleur ;
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 182, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblEntrezVotreNom = new JLabel("Entrez votre nom :");
		GridBagConstraints gbc_lblEntrezVotreNom = new GridBagConstraints();
		gbc_lblEntrezVotreNom.insets = new Insets(0, 0, 5, 0);
		gbc_lblEntrezVotreNom.gridx = 9;
		gbc_lblEntrezVotreNom.gridy = 3;
		add(lblEntrezVotreNom, gbc_lblEntrezVotreNom);
		
		nomJoueur = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 9;
		gbc_textField.gridy = 4;
		add(nomJoueur, gbc_textField);
		nomJoueur.setColumns(10);
		
		btnStart = new JButton("Start");
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.insets = new Insets(0, 0, 5, 0);
		gbc_btnStart.gridx = 9;
		gbc_btnStart.gridy = 5;
		add(btnStart, gbc_btnStart);
		
		btnStart.addActionListener(this) ;
		
		lblError = new JLabel("Nom incorrect !!!!");
		lblError.setForeground(Color.RED);
		lblError.setVisible(false);
		
		GridBagConstraints gbc_lblError = new GridBagConstraints();
		gbc_lblError.gridx = 9;
		gbc_lblError.gridy = 6;
		add(lblError, gbc_lblError);
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource().equals(btnStart)){
			startGame() ;
		}
		
	}
	private void startGame(){
		
		partieControlleur.newPartieNotified(nomJoueur.getText()) ;
	}
	public void nameIsEmpty() {
		lblError.setVisible(true) ;
	}
	@Override
	public void updates(CandyEvent e) {
		
	}
}
