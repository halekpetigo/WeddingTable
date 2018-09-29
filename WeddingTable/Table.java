package WeddingTable;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import WeddingTable.Party;

//Table object used to store table information
public class Table {
	private String _name;
	private int _capacity;
	private int _capacityLeft;
	private Set<Party> _parties = new HashSet<Party>();

	// constructor with parameters
	Table(String name, int capacity) {
		this._name = name; // set name
		this._capacity = capacity; // set capacity of the table
		this._capacityLeft = this._capacity; // set empty seats at the table
	}

	// returns name of the table
	public String Name() {
		return this._name;
	}

	// returns names and party size of the parties seated at the table
	public String strParties() {
		String str = "";

		Iterator<Party> iterator = this._parties.iterator();
		while (iterator.hasNext()) {
			Party p = iterator.next();
			str += p.Name() + ", party of " + p.Size() + " & ";
		}

		// removes trailing &
		if (str.endsWith(" & "))
			str = str.substring(0, str.length() - 3);

		return str;
	}

	// returns capacity of the table
	public int Capacity() {
		return this._capacity;
	}

	// returns number of empty seats left at the table
	public int CapacityLeft() {
		return this._capacityLeft;
	}

	// checks if there is a party not liked seated at the table
	public boolean hasDisliked(String partyName) {
		boolean r = false;
		Iterator<Party> iterator = this._parties.iterator();
		while (iterator.hasNext() && !r) {
			if (iterator.next().listOfDisliked().contains(partyName))
				r = true;
		}
		return r;
	}

	// seats a party at the table
	// returns true if party was successfully seated, false otherwise;
	public boolean addParty(Party party) {
		boolean r = false;
		// checks first if there are enough seats for my party and that there are no
		// seated parties that do not dislike my party
		if (this._capacityLeft >= party.Size() && !hasDisliked(party.Name())) {
			this._parties.add(party); // add party
			this._capacityLeft -= party.Size(); // update capacity left at table
			r = true;
		}
		return r;
	}

	// checks if any of the people I do not like are seated at this table
	public boolean hasDislikedList(Set<String> notFriends) {
		boolean r = false;
		Iterator<Party> iterator = this._parties.iterator();
		while (iterator.hasNext() && !r) {
			if (notFriends.contains(iterator.next().Name()))
				r = true;
		}
		return r;
	}
}
