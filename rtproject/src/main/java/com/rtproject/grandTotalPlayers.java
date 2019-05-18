package com.rtproject;


public class grandTotalPlayers implements Runnable  {

	int grandTotal, grandTotal1, grandTotal2, grandTotal3, grandTotal4;
	int grandTotal5, grandTotal6, grandTotal7, grandTotal8, grandTotal9;
	int grandTotal10, grandTotal11, grandTotal12, grandTotal13;
	int grandTotalPlayers;

	public grandTotalPlayers() {
		
	}
	
	public void run() {
		KedahPlayers kedah = new KedahPlayers();
		grandTotal1 = kedah.getGrandTotal();
		KLPlayers kl = new KLPlayers();
		grandTotal2 = kl.getGrandTotal();
		PPPlayers pp = new PPPlayers();
		grandTotal3 = pp.getGrandTotal();
		NSPlayers ns = new NSPlayers();
		grandTotal4 = ns.getGrandTotal();
		PahangPlayers ph = new PahangPlayers();
		grandTotal5 = ph.getGrandTotal();
		PutrajayaPlayers pj = new PutrajayaPlayers();
		grandTotal6 = pj.getGrandTotal();
		PerakPlayers prk = new PerakPlayers();
		grandTotal7 = prk.getGrandTotal();
		SelangorPlayers slg = new SelangorPlayers();
		grandTotal8 = slg.getGrandTotal();
		SarawakPlayers srk = new SarawakPlayers();
		grandTotal9 = srk.getGrandTotal();
		SabahPlayers sbh = new SabahPlayers();
		grandTotal10 = sbh.getGrandTotal();
		MelakaPlayers mlk = new MelakaPlayers();
		grandTotal11 = mlk.getGrandTotal();
		KelantanPlayers klt = new KelantanPlayers();
		grandTotal12 = klt.getGrandTotal();
		JohorPlayers jhr = new JohorPlayers();
		grandTotal13 = jhr.getGrandTotal();
		
		grandTotalPlayers = grandTotal1 + grandTotal2 +grandTotal3 +grandTotal4 + grandTotal5 + grandTotal6 + grandTotal7 + grandTotal8 +grandTotal9 + grandTotal10 + grandTotal11 +grandTotal12+ grandTotal13; ;
		
		String a = "GRAND TOTAL";
		String format = "| %-13s| %-8s| %-8d|\n";
		System.out.println("|--------------|---------|---------|");
		System.out.format(format, a, " ", grandTotalPlayers);
		System.out.println("------------------------------------");
		System.out.println("");
	}
}