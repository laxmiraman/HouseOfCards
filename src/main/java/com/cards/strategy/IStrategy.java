package com.cards.strategy;

import java.util.List;
import com.cards.bo.*;

/**
 * This interface is part of Strategy Pattern
 * so the Algorithms are loaded dynamically
 * 
 * @author Bakkiyalakshmi Ramanjulu
 *
 */
public interface IStrategy {
	public List<Card> doShuffling(List<Card> cards);
}
