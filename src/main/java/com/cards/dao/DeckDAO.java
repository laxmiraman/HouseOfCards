package com.cards.dao;

import com.cards.bo.Card;

/**
 * 
 * DeckDAO for retrieving collection of Cards
 * @author Bakkiyalakshmi Ramanjulu
 *
 */
import java.util.*;

public interface DeckDAO {
	
	public Map<String,List<Card>> getDecks();	
	public List<Card> getDeck(String deckName);
	public List<Card> createDeck(String deckName);
	public void addOrUpdate(String deckName, List<Card> deck);
	public void remove(String deckName);
	
}
