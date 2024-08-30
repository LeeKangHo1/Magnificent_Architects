package ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tables.Rank;

public class RankGUI extends JDialog {
	Rank r1 = new Rank("asd", "10000000", "1");
	Rank r2 = new Rank("qw", "5000000", "2");

	public RankGUI() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		setSize(420, 510);

		JPanel pnl = new JPanel();
		getContentPane().add(pnl, BorderLayout.NORTH);

		JLabel lblRankShow = new JLabel("명예의 전당");
		lblRankShow.setFont(new Font("굴림", Font.PLAIN, 17));
		pnl.add(lblRankShow);

		JPanel pnlRank = new JPanel();
		getContentPane().add(pnlRank, BorderLayout.CENTER);
		pnlRank.setLayout(null);

		JLabel lblNum = new JLabel("순위");
		lblNum.setBounds(70, 15, 57, 15);
		pnlRank.add(lblNum);

		JLabel lblUser = new JLabel("유저 ID");
		lblUser.setBounds(180, 15, 57, 15);
		pnlRank.add(lblUser);

		JLabel lblMoney = new JLabel("금액");
		lblMoney.setBounds(290, 15, 57, 15);
		pnlRank.add(lblMoney);

		JLabel lbl1 = new JLabel(r1.getRank_Rank());
		lbl1.setBounds(70, 50, 57, 15);
		JLabel lbl2 = new JLabel(r1.getRank_ID());
		lbl2.setBounds(180, 50, 57, 15);
		JLabel lbl3 = new JLabel(r1.getRank_Money());
		lbl3.setBounds(290, 50, 57, 15);
		pnlRank.add(lbl1);
		pnlRank.add(lbl2);
		pnlRank.add(lbl3);
		
        // 현재 랭크 목록을 표시
//        for (int i = 0; i < ranks.size(); i++) {
//            Rank rank = ranks.get(i);
//            JLabel lbl1 = new JLabel(rank.getRank_Rank());
//            lbl1.setBounds(70, 50 + (i * 30), 57, 15);
//            JLabel lbl2 = new JLabel(rank.getRank_ID());
//            lbl2.setBounds(180, 50 + (i * 30), 57, 15);
//            JLabel lbl3 = new JLabel(rank.getRank_Money());
//            lbl3.setBounds(290, 50 + (i * 30), 57, 15);
//            pnlRank.add(lbl1);
//            pnlRank.add(lbl2);
//            pnlRank.add(lbl3);
//        }
		
		JButton btnBack = new JButton("뒤로가기");
		btnBack.setBounds(159, 384, 97, 23);
		btnBack.setFocusable(false);
		pnlRank.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	
	}
}
