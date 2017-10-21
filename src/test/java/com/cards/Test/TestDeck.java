package com.cards.Test;

import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.Map;

import com.cards.bo.Card;
import com.cards.dao.*;
import com.cards.db.stub.*;

public class TestDeck {
	
	@Test
	public void testCreateDeck(){
		DeckDAO deckDAO = new DeckDAOStubImpl();
		List<Card> list = deckDAO.createDeck("FIRST");
		assertEquals(52, list.size());
	}
	
	@Test
	public void testUpdateDeck(){
		DeckDAO deckDAO = new DeckDAOStubImpl();
		List<Card> list1 = deckDAO.createDeck("FIRST");
		deckDAO.addOrUpdate("FIRST", list1);		
		assertEquals(2, deckDAO.getDecks().size());
	}
	
	@Test
	public void testAddDeck(){
		DeckDAO deckDAO = new DeckDAOStubImpl();
		List<Card> list1 = deckDAO.createDeck("FIRST");
		deckDAO.addOrUpdate("SECOND", list1);		
		assertEquals(2, deckDAO.getDecks().size());
	}

	@Test
	public void testRemove(){
		DeckDAO deckDAO = new DeckDAOStubImpl();
		List<Card> list1 = deckDAO.createDeck("FIRST");
		List<Card> list2 = deckDAO.createDeck("SECOND");
		deckDAO.remove("FIRST");
		assertEquals(52, deckDAO.getDeck("SECOND").size());
	}

}
