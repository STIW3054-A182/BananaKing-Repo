package com.rtproject;

public class grandTotalPlayers implements Runnable {
	double grandTotal;
	double grandTotal1;
	double grandTotal2;
	double grandTotalPlayers;

	public grandTotalPlayers() {
	}

	@Override
	public void run() {
		KedahPlayers kedah = new KedahPlayers();
		grandTotal1 = kedah.getGrandTotal();
		KLPlayers kl = new KLPlayers();
		grandTotal2 = kl.getGrandTotal();
		grandTotalPlayers = grandTotal1 + grandTotal2;
		String a = "GRAND TOTAL";
		String format = "| %-13s| %-8s| %-8s|\n";
		System.out.format(format, a, grandTotalPlayers, " ");

	}
}