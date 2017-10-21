package com.cards.db.stub;

import java.util.*;

import com.cards.dao.*;
import com.cards.bo.*;

/**
 * 
 * This Card stub acts as a Database persistence class
 * It stores at Card level detail. The will be available in memory
 * 
 * Please note that these are throw away code stubbed for purely testing purpose
 * 
 * @author Bakkiyalakshmi Ramanjulu
 *
 */
public class CardDAOStubImpl implements CardDAO {

	/**
	 * Retrieves a random card from a given deck, also removes the card item from 
     * the deck list once the card is picked
     * 
	 * @param deckName
	 * @return
	 */
	public Card getARandomCard(String deckName) {
		DeckDAOStubImpl deckDAO = new DeckDAOStubImpl();
		ArrayList<Card> cardList = (ArrayList<Card>) deckDAO.getDeck(deckName);
		Random r = new Random();
		int rndNo = r.nextInt(cardList.size());
		Card randomCard = cardList.get(rndNo);
		cardList.remove(rndNo);
		deckDAO.addOrUpdate(deckName, cardList);
		return randomCard;
	}

	/**
	 * Retrieves the card from the deck stack
	 */
	public Card get(String deckName) {
		DeckDAOStubImpl deckDAO = new DeckDAOStubImpl();
		ArrayList<Card> cardList = (ArrayList<Card>) deckDAO.getDeck(deckName);
		Card card = cardList.remove(0);
		deckDAO.addOrUpdate(deckName, cardList);
		return card;
	}

	/**
	 * Adds a card to the deck if not present, otherwise updates the existing card
	 * to the deck list
	 * 
	 * @see com.cards.dao.CardDAO#addOrUpdate(java.lang.String, com.cards.bo.Card)
	 */
	public void addOrUpdate(String deckName, Card card) {
		DeckDAOStubImpl deckDAO = new DeckDAOStubImpl();
		ArrayList<Card> cardList = (ArrayList<Card>) deckDAO.getDeck(deckName);
		if(!cardList.contains(card)){
			cardList.add(card);
			deckDAO.addOrUpdate(deckName, cardList);
		}
	}

	/**
	 * deletes a card from the given deck, also updates the deck list to keep in synch
	 */
	public void remove(String deckName, Card card) {
		DeckDAOStubImpl deckDAO = new DeckDAOStubImpl();
		ArrayList<Card> cardList = (ArrayList<Card>) deckDAO.getDeck(deckName);
		cardList.remove(card);
		deckDAO.addOrUpdate(deckName, cardList);
	}
}
