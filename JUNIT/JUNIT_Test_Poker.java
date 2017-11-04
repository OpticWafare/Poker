import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUNIT_Test_Poker {
	
	static public JUNIT_Test_Poker test;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		test = new JUNIT_Test_Poker();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	PokerTest.anzPair = 0;
	PokerTest.anzTripple = 0;
	PokerTest.anzFourOfAKind = 0;
	PokerTest.anzFullHouse = 0;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCheckPair() {
		int[] karten =  new int[] {0,13,24,16,27};
		int[] karten2 =  new int[] {0,1,15,36,50};
		
		PokerTest.checkPaarTrippleVierlingFullHouse(karten);
		PokerTest.checkPaarTrippleVierlingFullHouse(karten2);
		
		assertEquals("Anzahl der Paare ist 1", PokerTest.anzPair, 1);
		assertEquals("Anzahl der Tripples ist 0", PokerTest.anzTripple, 0);
		assertEquals("Anzahl der FourOfAKinds ist 0", PokerTest.anzFourOfAKind, 0);
		assertEquals("Anzahl der FullHouses ist 0" , PokerTest.anzFullHouse, 0);
	}

	
	@Test
	public void testCheckTripple() {
		int[] karten =  new int[] {0,13,26,5,2};
		int[] karten2 =  new int[] {0,1,15,36,50};
		
		PokerTest.checkPaarTrippleVierlingFullHouse(karten);
		PokerTest.checkPaarTrippleVierlingFullHouse(karten2);
		
		assertEquals("Anzahl der Paare ist 0", PokerTest.anzPair, 0);
		assertEquals("Anzahl der Tripples ist 1", PokerTest.anzTripple, 1);
		assertEquals("Anzahl der FourOfAKinds ist 0", PokerTest.anzFourOfAKind, 0);
		assertEquals("Anzahl der FullHouses ist 0" , PokerTest.anzFullHouse, 0);
	}

	
	@Test
	public void testCheckFourOfAKind() {
		int[] karten =  new int[] {0,13,26,39,7};
		int[] karten2 =  new int[] {0,1,15,36,50};
		
		PokerTest.checkPaarTrippleVierlingFullHouse(karten);
		PokerTest.checkPaarTrippleVierlingFullHouse(karten2);
		
		assertEquals("Anzahl der Paare ist 0", PokerTest.anzPair, 0);
		assertEquals("Anzahl der Tripples ist 0", PokerTest.anzTripple, 0);
		assertEquals("Anzahl der FourOfAKinds ist 1", PokerTest.anzFourOfAKind, 1);
		assertEquals("Anzahl der FullHouses ist 0" , PokerTest.anzFullHouse, 0);
	}
	
	
	@Test
	public void testCheckFullHouse() {
		int[] karten =  new int[] {0,13,26,15,2};
		int[] karten2 =  new int[] {0,1,15,36,50};
		
		PokerTest.checkPaarTrippleVierlingFullHouse(karten);
		PokerTest.checkPaarTrippleVierlingFullHouse(karten2);
		
		assertEquals("Anzahl der Paare ist 1", PokerTest.anzPair, 1);
		assertEquals("Anzahl der Tripples ist 1", PokerTest.anzTripple, 1);
		assertEquals("Anzahl der FourOfAKinds ist 0", PokerTest.anzFourOfAKind, 0);
		assertEquals("Anzahl der FullHouses ist 1" , PokerTest.anzFullHouse, 1);
	}
	@Test
	public void testCheckStraight() {
		int[] karten =  new int[] {0,14,28,42,51};
		int[] karten2 =  new int[] {0,18,30,40,47};
		int[] karten3 =  new int[] {0,12,14,28,42};
		int[] karten4 =  new int[] {8,12,22,36,50};
		
		assertTrue("Es gibt eine Straﬂe", PokerTest.checkStraight(karten));
		assertFalse("Es gibt keine Straﬂe", PokerTest.checkStraight(karten2));
		assertTrue("Es gibt eine Straﬂe", PokerTest.checkStraight(karten3));
		assertTrue("Es gibt eine Straﬂe", PokerTest.checkStraight(karten4));
	}

	@Test
	public void testCheckRoyalFlush() {
		int[] karten =  new int[] {8,9,10,11,12};
		int[] karten2 =  new int[] {0,14,28,42,51};
		
		assertTrue("Es gibt ein RoyalFlush", PokerTest.checkRoyalFlush(karten));
		assertFalse("Es gibt kein RoyalFlush", PokerTest.checkRoyalFlush(karten2));
	}

	@Test
	public void testCheckFlush() {
		int[] karten =  new int[] {0,2,6,9,11};
		int[] karten2 =  new int[] {0,14,28,42,51};
		
		assertTrue("Es gibt ein Flush", PokerTest.checkFlush(karten));
		assertFalse("Es gibt kein Flush", PokerTest.checkFlush(karten2));
	}

	@Test
	public void testCheckStraightFlush() {
		int[] karten =  new int[] {0,1,2,3,4};
		int[] karten2 =  new int[] {0,14,28,42,51};
		
		assertTrue("Es gibt ein StraightFlush" ,PokerTest.checkStraightFlush(karten));
		assertFalse("Es gibt kein StraightFlush" ,PokerTest.checkStraightFlush(karten2));
	}

	@Test
	public void testCheckHighestCard() {
		int[] karten =  new int[] {2,7,10,6,3};
		
		assertEquals("Die hˆchste Karte ist 11" , PokerTest.checkHighestCard(karten), 11);
	}

	public static void main(String[] args) {
		try {
			setUpBeforeClass();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.testCheckStraight();
		test.testCheckRoyalFlush();
		test.testCheckFlush();
		test.testCheckStraightFlush();
		test.testCheckHighestCard();
		test.testCheckPair();
		test.testCheckTripple();
		test.testCheckFourOfAKind();
		test.testCheckFullHouse();
	}
}
