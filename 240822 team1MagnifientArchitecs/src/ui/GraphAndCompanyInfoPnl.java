package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dbUtil.DBUtil;
import dbUtil.IResultMapper;
import mapper.CompanyInfoMapper;
import tables.CompanyInfo;

public class GraphAndCompanyInfoPnl extends JPanel implements ActionListener {
	private CardLayout cardLayout;
	private JPanel pnlCenter;

	public GraphAndCompanyInfoPnl() {
		setLayout(new BorderLayout());

		JPanel pnlNorth = new JPanel();
		pnlNorth.setLayout(new GridLayout(2, 1));
		add(pnlNorth, "North");

		// 회사 이름, 회사 현재 주가, 전일대비 가격, 전일대비 상승률
		JPanel companyStockpnl = new JPanel();

		JLabel companyNamelbl = new JLabel();
		companyNamelbl.setText("A 회사");
		JLabel stockPriceNowlbl = new JLabel();
		stockPriceNowlbl.setText("100원");
		JLabel comparePrevDaylbl = new JLabel();
		comparePrevDaylbl.setText("전일대비 0원 (0%)");

		companyStockpnl.add(companyNamelbl);
		companyStockpnl.add(stockPriceNowlbl);
		companyStockpnl.add(comparePrevDaylbl);

		pnlNorth.add(companyStockpnl);

		// 차트보기, 회사 정보 패널
		JPanel chartAndbtnpnl = new JPanel();

		JButton graphbtn = new JButton("차트 보기");
		graphbtn.addActionListener(this);
		JButton companyInfobtn = new JButton("회사 정보");
		companyInfobtn.addActionListener(this);

		chartAndbtnpnl.add(graphbtn);
		chartAndbtnpnl.add(companyInfobtn);

		pnlNorth.add(chartAndbtnpnl);

		pnlCenter = new JPanel();
		cardLayout = new CardLayout();
		pnlCenter.setLayout(cardLayout);
		add(pnlCenter, "Center");

		// 그래프 패널
		JPanel graphpnl = new JPanel();
		JLabel templbl = new JLabel("주식 그래프 차트 들어올 예정");
		graphpnl.add(templbl);
		pnlCenter.add(graphpnl, "First");

		// 회사정보 패널
		String selectedCompanyName = "A 회사";
		CompanyInfo companyInfo = selectCompany(selectedCompanyName);
		CompanyInfoPnl companyInfopnl = new CompanyInfoPnl(companyInfo);
		companyInfopnl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		companyInfopnl.setBackground(Color.WHITE);
		pnlCenter.add(companyInfopnl, "Second");
		
		// south 패널 매수 버튼, 매도 버튼
		JPanel pnlSouth = new JPanel();
		JButton buybtn = new JButton("매수");
		JButton sellbtn = new JButton("매도");
		
		pnlSouth.add(buybtn);
		pnlSouth.add(sellbtn);
		
		add(pnlSouth, "South");
	}

	private static CompanyInfo selectCompany(String companyName) {
		IResultMapper<CompanyInfo> mapper = new CompanyInfoMapper();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM CompanyInfo WHERE companyName = ?; ";
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, companyName);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return mapper.resultMapping(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if (command.equals("차트 보기")) {
			cardLayout.show(pnlCenter, "First");
		} else if (command.equals("회사 정보")) {
			cardLayout.show(pnlCenter, "Second");
		}
	}
}