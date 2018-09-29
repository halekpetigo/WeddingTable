package WeddingTable;

import java.util.HashSet;
import java.util.Set;

//Party object used to store party information
public class Party {
	private String _name;
	private int _size;
	private int _numDisliked;
	private Set<String> _dislike = new HashSet<String>();

	// constructor with parameter
	Party(String name, int size) {
		this._name = name; // set name
		this._size = size; // ser size
	}

	// returns party name
	public String Name() {
		return this._name;
	}

	// returns party size
	public int Size() {
		return this._size;
	}

	// add name of a party this party dislikes
	public void addDisliked(String partyName) {
		this._dislike.add(partyName);
		this._numDisliked++;
	}

	// returns number of parties this party dislikes
	public int numDislike() {
		return this._numDisliked;
	}

	// checks if this party dislikes party in parameter sent
	public boolean isDisliked(String partyName) {
		boolean r = false;
		if (this._dislike != null)
			r = this._dislike.contains(partyName);
		return r;
	}

	// returns list of parties this party dislikes
	public Set<String> listOfDisliked() {
		return this._dislike;
	}

}
