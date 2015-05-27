package eg.edu.guc.santorini.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Welcome extends BaseFrame implements ActionListener {

	private JPanel welcomePanel;
	private JPanel welcomePanel2;
	private JButton startButton;
	private JLabel logo;

	public Welcome() {
		super("Santorini");
		setResizable(false);
		welcomePanel = new JPanel(new FlowLayout());
		welcomePanel.setBounds(0, 0, 1024, 500);
		logo = new JLabel();
		logo.setIcon(new ImageIcon("logo.png"));
		welcomePanel.add(logo);
		add(welcomePanel, BorderLayout.CENTER);
		welcomePanel2 = new JPanel(new FlowLayout());
		welcomePanel2.setBounds(0, 0, 1024, 168);
		startButton = new JButton("Start");
		startButton.setPreferredSize(new Dimension(1024, 168));
		startButton.addActionListener(this);
		welcomePanel2.add(startButton);
		add(welcomePanel2, BorderLayout.SOUTH);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton x = (JButton) e.getSource();
		if (x.equals(startButton)) {
			this.dispose();
			new Window();
		}

	}

	public JPanel getWelcomePanel() {
		return welcomePanel;
	}

	public void setWelcomePanel(JPanel welcomePanel) {
		this.welcomePanel = welcomePanel;
	}

	public JButton getStartButton() {
		return startButton;
	}

	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}

	public JLabel getLogo() {
		return logo;
	}

	public void setLogo(JLabel logo) {
		this.logo = logo;
	}
}