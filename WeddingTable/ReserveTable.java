package WeddingTable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//performs reservations
public class ReserveTable {

	private Map<String, Table> _myTables = new HashMap<String, Table>();
	private Map<String, Party> _myParties = new HashMap<String, Party>();

	// constructor
	ReserveTable() {
	}

	// constructor with parameters; takes Table Map and Party Map
	ReserveTable(Map<String, Table> tables, Map<String, Party> parties) {
		this._myTables = tables;
		this._myParties = parties;
	}

	// Finds party who dislikes the most parties
	private Party findMostDisliked() {
		Party p = null;
		Party pWithMost = null;
		int max = 0;

		Iterator<String> iterator = this._myParties.keySet().iterator();
		while (iterator.hasNext()) {
			p = this._myParties.get(iterator.next());
			if (p.numDislike() > max) {
				max = p.numDislike();
				pWithMost = p;
			}
		}
		return pWithMost;
	}

	// gets next Party in the map
	private Party getNextParty() {
		Party p = null;

		Iterator<String> iterator = this._myParties.keySet().iterator();
		if (iterator.hasNext())
			p = this._myParties.get(iterator.next());

		return p;
	}

	// find the smallest table to fit the party
	private Table findSmallestTable(Party p) {
		Table t = null;
		Table tSmallest = null;
		int size = 0;

		Iterator<String> iterator = this._myTables.keySet().iterator();
		while (iterator.hasNext()) {
			t = this._myTables.get(iterator.next());

			// first big enough when party can sit; make sure there are no conflict with
			// seated parties
			if (size == 0 && t.CapacityLeft() >= p.Size() && !t.hasDislikedList(p.listOfDisliked())) {
				size = t.CapacityLeft();
				tSmallest = t;
			}
			// look for anything smaller that can fit; make sure there are no conflict with
			// seated parties
			else if (t.CapacityLeft() <= size && p.Size() <= t.CapacityLeft()
					&& !t.hasDislikedList(p.listOfDisliked())) {
				size = t.CapacityLeft();
				tSmallest = t;
			}
		}

		return tSmallest;
	}

	// execute reservation
	public void Reserve() {

		Party lastParty = null;

		while (!this._myParties.isEmpty()) {
			// begins with most difficult parties to seat
			Party p = findMostDisliked();
			if (p == null)
				// once done seating difficult parties, seat the rest
				p = getNextParty();
			if (p == lastParty) {
				// could not seat last Party; stop to avoid infinite loop
				// to improve algorithm, we could randomly move already seated parties and
				// continue iteration to see if
				// better seating is possible; we can limit number of iterations to avoid
				// infinite loops
				System.out.println("We are in a dead lock situation; not all parties could be seated");
				break;
			}

			// find table with the most fitting seat left for party to seat
			Table t = findSmallestTable(p);

			if (t.addParty(p)) {
				this._myParties.remove(p.Name()); // remove party seated from reservation
				this._myTables.replace(t.Name(), t); // update table is Map
				lastParty = p;
			}
		}
	}

	// outputs reservation results
	public void PrintResults() {
		Table t = null;
		Iterator<String> iterator = this._myTables.keySet().iterator();
		while (iterator.hasNext()) {
			t = this._myTables.get(iterator.next());
			String outputStr = String.format("Table %s: %s", t.Name(), t.strParties());
			System.out.println(outputStr);
		}
	}

}
