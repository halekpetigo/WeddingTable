package WeddingTable;

import java.util.HashMap;
import java.util.Map;

import WeddingTable.Party;
import WeddingTable.Table;
import WeddingTable.ReserveTable;

public class WeddingTable {

	// constructor
	WeddingTable() {
	}

	public static void main(String[] args) {

		Map<String, Table> myTables = new HashMap<String, Table>();
		Map<String, Party> myResa = new HashMap<String, Party>();
		ReserveTable rt;

		// Load Tables
		Table t = new Table("A", 8);
		myTables.put(t.Name(), t);
		t = new Table("B", 8);
		myTables.put(t.Name(), t);
		t = new Table("C", 7);
		myTables.put(t.Name(), t);
		t = new Table("D", 7);
		myTables.put(t.Name(), t);

		// Reservation Requests
		Party p = new Party("Thornton", 3);
		myResa.put(p.Name(), p);
		p = new Party("Garcia", 2);
		myResa.put(p.Name(), p);
		p = new Party("Owens", 6);
		p.addDisliked("Thornton");
		p.addDisliked("Taylor");
		myResa.put(p.Name(), p);
		p = new Party("Smith", 1);
		p.addDisliked("Garcia");
		myResa.put(p.Name(), p);
		p = new Party("Taylor", 5);
		myResa.put(p.Name(), p);
		p = new Party("Reese", 7);
		myResa.put(p.Name(), p);

		// create and instance of Table reservation object and pass tables and
		// reservation information
		rt = new ReserveTable(myTables, myResa);

		// performs reservations
		rt.Reserve();

		// prints reservation result
		rt.PrintResults();

	}

}
