package view.panneauLateral;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;

import event.CandyEvent;
import event.candyEvent.CandyEventNbDeplacement;
import event.candyEvent.CandyEventNbDeplacementMAX;
import event.candyEvent.CandyEventNewScore;
import event.candyEvent.CandyEvetNewLevel;
import listener.Observerateur;

public class PartieView extends JPanel implements ActionListener,Observerateur{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel level= new JLabel("level       : 0");
	private JLabel score= new JLabel("score     : 0");
	private JLabel jLabelTime= new JLabel("time       : 00:00:00");
	private JLabel jLabelDeplacemetCourant= new JLabel("current  : 0");
	private JLabel jLabelDeplacemetMax= new JLabel("max        : 0");

	private Timer timer ;
	long tempsEcouler = 0;
	
	public PartieView()
	{

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		GridBagConstraints gbc_level = new GridBagConstraints();
		gbc_level.insets = new Insets(0, 0, 20, 20);
		gbc_level.anchor = GridBagConstraints.WEST;
		gbc_level.gridx = 0;
		gbc_level.gridy = 0;
		add(level, gbc_level);
		GridBagConstraints gbc_score = new GridBagConstraints();
		gbc_score.insets = new Insets(0, 0, 20, 0);
		gbc_score.anchor = GridBagConstraints.WEST;
		gbc_score.gridx = 0;
		gbc_score.gridy = 1;
		add(score, gbc_score);
		GridBagConstraints gbc_time = new GridBagConstraints();
		gbc_time.insets = new Insets(0, 0, 20, 0);
		gbc_time.anchor = GridBagConstraints.WEST;
		gbc_time.gridx = 0;
		gbc_time.gridy = 2;
		add(jLabelTime, gbc_time);
		
		
		GridBagConstraints gbc_deplacementCourant = new GridBagConstraints();
		gbc_deplacementCourant.insets = new Insets(0, 0, 20, 0);
		gbc_deplacementCourant.anchor = GridBagConstraints.WEST;
		gbc_deplacementCourant.gridx = 0;
		gbc_deplacementCourant.gridy = 3;
		add(jLabelDeplacemetCourant, gbc_deplacementCourant);

		GridBagConstraints gbc_deplacementMax = new GridBagConstraints();
		gbc_deplacementMax.insets = new Insets(0, 0, 0, 0);
		gbc_deplacementMax.anchor = GridBagConstraints.WEST;
		gbc_deplacementMax.gridx = 0;
		gbc_deplacementMax.gridy = 4;
		add(jLabelDeplacemetMax, gbc_deplacementMax);

		
		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.gray, Color.lightGray));
		
		timer = new Timer(1000, this) ;
		
	}
	
	public boolean isTimerRunning (){
		return timer.isRunning() ;
	}
	public void startTimer (){
		timer.start() ;
	}
	public void stopTimer (){
		timer.stop() ;
	}
	public void initTimer(){
		tempsEcouler = 0 ;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		tempsEcouler +=1 ;
		int second = (int) (tempsEcouler % 60) ;
		int minute = (int) (tempsEcouler / 60) % 60;
		int heure = (int) (tempsEcouler / 3600) ;
		jLabelTime.setText("time     : "  + 
						   ((heure < 10)?  "0" + heure  : heure ) + ":" +
						   ((minute < 10)? "0" + minute : minute) + ":" + 
						   ((second < 10)? "0" + second : second) );
	}

	@Override
	public void updates(CandyEvent e) {
		if(e instanceof CandyEventNewScore){
			score.setText("score    : " + e.getValue());
		}
		if(e instanceof CandyEvetNewLevel ){
			level.setText("level      : " + e.getValue());
		}
		if(e instanceof CandyEventNbDeplacementMAX ){
			jLabelDeplacemetMax.setText("max        : " + e.getValue());
		}
		if (e instanceof CandyEventNbDeplacement){
			jLabelDeplacemetCourant.setText("current  : " + e.getValue());
		}


		
	}
}
