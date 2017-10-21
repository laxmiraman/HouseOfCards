package com.cards.strategy;

import java.util.*;
import com.cards.bo.*;

/**
 * Implements a simple shuffling algorithm that simply randomizes the deck in-place.
 * 
 * @author Bakkiyalakshmi
 *
 */
public class SimpleShuffling implements IStrategy {
	
	public SimpleShuffling(){ }

	public List<Card> doShuffling(List<Card> cards) {
		for (int i = 0; i < cards.size(); i++) {
			int r = i + (int) (Math.random() * (cards.size() - i));
			Card temp = cards.get(r);
			cards.set(r, cards.get(i));
			cards.set(i, temp);
		}

		return new ArrayList<Card>(cards);
	}	
}