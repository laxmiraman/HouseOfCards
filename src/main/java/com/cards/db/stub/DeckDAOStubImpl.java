package com.cards.db.stub;

import java.util.*;

import com.cards.bo.Card;
import com.cards.bo.Rank;
import com.cards.bo.Suit;
import com.cards.dao.*;

/**
 * This Deck Implementation is a stub, handling different decks A deck contains
 * a collection of 52 cards, this stub holds a list of decks, each deck is
 * associated to a name
 * 
 * Persist the decks in-memory only, but stub the persistence layer such that it
 * can be later upgraded to a durable datastore.
 * 
 * Please note that these are throw away code stubbed for purely testing purpose
 * 
 * @author Bakkiyalakshmi Ramanjulu
 *
 */
public class DeckDAOStubImpl implements DeckDAO {

	/**
	 * This map member variable persists the deck list in memory, all the data
	 * is lost once the jvm is down
	 */
	private static Map<String, List<Card>> decks = new HashMap<String, List<Card>>();

	public DeckDAOStubImpl() {

	}

	/**
	 * Retrieves a list of decks with the deckname as a key
	 * 
	 * @return Map
	 */
	public Map<String, List<Card>> getDeckTable() {
		return this.decks;
	}

	/**
	 * Setter method for bigger object to list of deck
	 * 
	 * @param deckTable
	 */
	public void setDecks(Map<String, List<Card>> deckTable) {
		this.decks = deckTable;
	}

	/**
	 * This method creates a deck of 52 initially ordered deck using the Rank
	 * and Suit combinations
	 */
	@Override
	public List<Card> createDeck(String deckName) {
		int suitLength = Suit.values().length;
		int rankLength = Rank.values().length;
		int deckCount = suitLength + rankLength;
		ArrayList<Card> cards = new ArrayList<Card>(deckCount);
		// arrange card sequentially
		for (Rank rank : Rank.values()) {
			for (Suit suit : Suit.values()) {
				cards.add(new Card(suit, rank));
			}
		}
		decks.put(deckName, cards);
		return cards;
	}

	/**
	 * Retrieves the list of deck from the memory
	 */
	public Map<String, List<Card>> getDecks() {
		return this.decks;
	}

	/**
	 * Retrieves a deck of cards for the give deck Name
	 */
	public List<Card> getDeck(String deckName) {
		return this.decks.get(deckName);
	}

	/**
	 * Adds or updates a deck as per the deck name
	 */
	public void addOrUpdate(String deckName, List<Card> deck) {
		if (decks != null && decks.get(deckName) != null && !decks.get(deckName).isEmpty()) {
			decks.put(deckName, deck);
		} else {
			createDeck(deckName);
		}
	}

	/**
	 * Deletes a in memory deck
	 */
	public void remove(String deckName) {
		decks.remove(deckName);
	}

}
