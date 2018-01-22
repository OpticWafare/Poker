import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import model.DB_Manager;


public class PokerTest {

	static int anzahlKarteProFarbe = 13;
	static int anzahlFarben = 4;
	static int anzahlKarten = anzahlKarteProFarbe * anzahlFarben;

	public static int[] poker = new int[52];
	public static int[] gezogeneKarten = new int[5];

	static int anzPair = 0;
	static int anzTripple = 0;
	static int anzFourOfAKind = 0;
	static int anzFullHouse = 0;
	static int anzStraight = 0;
	static int anzFlush = 0;
	static int anzStraightFlush = 0;
	static int anzRoyalFlush = 0;

	static boolean textAusgabe = false;
	
	static int UserID = 1;
	static String UserName = "Martin"; 

	static void arrayfuellen() {

		for (int i = 0; i < poker.length; i++) {
			poker[i] = i;
			// System.out.print(poker[i] + " ");
		}
	}

	/**
	 * Tauscht in dem angegebenen Array die Werte an den zwei angegebenen
	 * Indexen miteinander aus
	 * 
	 * @param poker
	 *            Das Array das die zu tauschenden Zahlen beinhaltet
	 * @param i
	 *            Index der ersten Zahl die mit der zweiten Zahl getauscht
	 *            werden soll
	 * @param j
	 *            Index der zweiten Zahl die mit der ersten Zahl getauscht
	 *            werden soll
	 */
	static void swapRandomNumbers(int[] poker, int i, int j) {
		int temp = poker[i];
		poker[i] = poker[j];
		poker[j] = temp;
	}

	/**
	 * Gibt mittels der absoluten Kartenzahl (0-51) die Farbe (Pick, Karo, Herz,
	 * etc) der Karte aus
	 * 
	 * @param karte
	 *            Kartenzahl (0-51)
	 * @return Nummer der Farbe (0-3)
	 */
	static int farbeKarten(int karte) {
		int farbe = karte / anzahlKarteProFarbe;
		return farbe;
	}

	/**
	 * Gibt mittels der absoluten Kartenzahl (0-51) die Kartennummer (2-10,
	 * Bube, Dame, Koenig, Ass) der Karte aus
	 * 
	 * @param karte
	 *            Kartenzahl (0-51)
	 * @return Nummer der Karte (1-13)
	 */
	static int numberKarte(int karte) {
		for (int i = 0; i < anzahlFarben; i++) {

			if ((karte >= (i * anzahlKarteProFarbe)) && (karte < ((i + 1) * anzahlKarteProFarbe))) {
				karte -= (i * anzahlKarteProFarbe);
				karte++;
				return karte;
			}
		}
		return 0;
	}

	/**
	 * Ordnet die Zahlen im Array der Groesse nach aufsteigend
	 * 
	 * @param gezogeneNummern
	 * @return
	 */
	public static int[] sortHandCards(int [] gezogeneKarten) {
		Arrays.sort(gezogeneKarten);
		return gezogeneKarten;
	}

	/**
	 * Schaut nach ob und wie viele Zwillinge, Drillinge und Vierlinge es in
	 * einem Array aus absoluten Kartenzahlen (0-51) gibt Zwillinge, Drillinge &
	 * Vierlinge basieren auf deren tatsaechlichen Kartennummern (2-9, Bube,
	 * Dame, Koenig, Ass)
	 * 
	 * @param gezogeneKarten
	 *            Array mit absoluten Kartenzahlen (0-51)
	 */
	static void checkPaarTrippleVierlingFullHouse(int[] gezogeneKarten) {

		/**
		 * Beinhaltet alle Nummern der zu checkenden Karten (0-9, B, D, K, A)
		 */
		int[] gezogeneNummern = new int[gezogeneKarten.length];
		// Alle absoluten Kartenzahlen (0-51) in Kartennummern (0-9, B, D, K, A)
		// umwandeln
		for (int i = 0; i < gezogeneNummern.length; i++) {
			gezogeneNummern[i] = numberKarte(gezogeneKarten[i]); // Jede Zahl
			// aus dem
			// Array mit
			// der
			// nummberKarte
			// Methode
			// umwandeln
			// und in
			// seperatem
			// Array
			// speichern
		}
		// Alle Karttennummern muessen der Groesse nach aufsteigend geordnet
		// sein
		sortHandCards(gezogeneNummern);
		if(textAusgabe == true){
			System.out.println("Geordnete Karten: ");}
		// for (int i = 0; i < gezogeneNummern.length; i++) {
		// System.out.print(gezogeneNummern[i] + " ");
		// }
		if(textAusgabe == true){
			System.out.println();}

		/**
		 * Anzahl der Karten in gezogeneNummern die ausschliesslich Zwillinge
		 * sind (keine Drillinge/Vierlinge)
		 */
		int anzZwillinge = 0;
		/**
		 * Anzahl der Karten in gezogeneNummern die ausschliesslich Drillinge
		 * sind (keine Zwillinge/Vierlinge)
		 */
		int anzDrillinge = 0;
		/**
		 * Anzahl der Karten in gezogeneNummern die ausschliesslich Vierlinge
		 * sind (keine Zwillinge/Drillinge)
		 */
		int anzVierlinge = 0;
		/** Zaehlt wie viele Karten hintereinander die gleiche Nummer haben */
		int zaehler = 1;

		// Array durchlaufen
		for (int i = 0; i < gezogeneNummern.length - 1; i++) {

			// Ist diese Karte die gleiche wie die naechste?
			if (gezogeneNummern[i] == gezogeneNummern[i + 1]) {
				zaehler++; // Ja -> Anzahl der gleichen Karten hintereinander
				// erhoehen
			} else {
				// Nein -> checken wie viele Karten hintereinander gleich waren
				// und jeweiligen Zaehler erhoehen
				if (zaehler == 2)
					anzZwillinge++; // 2 Karten hintereinader gleich -> Anzahl
				// Zwillinge erhoehen
				else if (zaehler == 3)
					anzDrillinge++; // ...
				else if (zaehler >= 4)
					anzVierlinge++; // ...
				zaehler = 1; // Zaehler wieder zuruecksetzen
			}
		}
		// Erneuter Check wegen zaehler, da sonst ein Zwilling/Drilling/Vierling
		// an der letzten Stelle im Array ignoriert werden koennte, da diese
		// Abfrage
		// ja nur ausgefuehrt wird, wenn die Folge von gleichen Karten
		// unterbrochen wird
		if (zaehler == 2)
			anzZwillinge++;
		else if (zaehler == 3)
			anzDrillinge++;
		else if (zaehler >= 4)
			anzVierlinge++;
		zaehler = 1;

		// Überprüfung, ob es ein Full House gibt
		if (anzDrillinge == 1 && anzZwillinge == 1) {
			if(textAusgabe == true){
				System.out.println("Es gibt ein Full House!");}
			anzFullHouse++;
		}
		if(textAusgabe == true){
			System.out.println("Zwillinge: " + anzZwillinge);
			System.out.println("Drillinge: " + anzDrillinge);
			System.out.println("Vierlinge: " + anzVierlinge);}

		anzFourOfAKind += anzVierlinge;
		anzTripple += anzDrillinge;
		anzPair += anzZwillinge;
	}

	/**
	 * Checkt ob die Karten in dem Array des Parameters alle in einer Strasse
	 * liegen
	 * 
	 * @param gezogeneKarten
	 *            Die Karten (0-51), die auf eine Strasse geürueft werden sollen
	 * @return true: angegebene Karten sind in einer Strasse; false: keine
	 *         strasse
	 */
	static boolean checkStraight(int[] gezogeneKarten) {
		// Kartenzahlen (0-51) werden in Kartennummern umgewandelt (2-9, B, D,
		// K, A)
		int[] gezogeneNummern = new int[gezogeneKarten.length];
		for (int i = 0; i < gezogeneKarten.length; i++) {
			gezogeneNummern[i] = numberKarte(gezogeneKarten[i]);
		}
		// Kartennummern werden der Groesse nach aufsteigend sortiert
		// Dadurch liegt (falls vorhanden) eine Strasse schon in der richtigen
		// Reihenfolge vor
		sortHandCards(gezogeneNummern);

		// Checken ob die Karten in dieser Reihe alle direkt aufeinander folgen
		// (keine Karte darf ausgelassen werden)
		for (int i = 0; i < gezogeneNummern.length - 1; i++) // Nur bis
			// .length-1,
			// damit wird
			// OutOfBounds
			// Error behoben
		{
			// Wenn man die derzeitige Karte um 1 erhoeht, muss sie gleich sein
			// wie die naechste Karte
			// Trifft diese Bedingung auch nur ein einziges Mal zu, wird die
			// Methode abgebrochen und "Keine Strasse" zurueckgegeben
			if ((gezogeneNummern[i] + 1) != gezogeneNummern[i + 1]) { // Es wird
				// überprüft,
				// ob es
				// sich
				// um
				// letzten
				// Schleifendurchlauf
				// handelt
				// (Vergleich
				// 3.
				// mit
				// 4.ten
				// Index)
				if (gezogeneNummern.length - 2 == i) { // Es wird überprüft, ob
					// die letzte Karte in
					// gezogeneNummern ein
					// Ass ist und ob die
					// erste Karte 2 ist
					// Tritt nur auf, wenn
					// bis zum letztem
					// Kartenwert eine
					// durchgehende
					// Reihenfolge vorkommt
					// und erst die letzte
					// Kartenummer eine Ass
					// ist
					// Einzige Möglichkeit
					// ist: 2 3 4 5 13
					if ((gezogeneNummern[4] == 13) && (gezogeneNummern[0] == 1)) // Wert
						// 1
						// =
						// Karte
						// 2
					{
						if(textAusgabe == true){
							System.out.println("Es gibt eine Straße!");}
						anzStraight++;
						return true;
					}
				}
				return false;
			}
		}
		if(textAusgabe == true){
			System.out.println("Es gibt eine Straße!");}
		return true;
	}

	/**
	 * Checkt ob es ein royalFlush ist und die Karten mit denen er gebildet
	 * wurde die höchsten Karten sind
	 * 
	 * @param gezogeneKarten
	 *            Die Karten (0-51), die auf eine Strasse geürueft werden sollen
	 * @return true: angegebene Karten sind ein RoyalFlush; false: kein
	 *         RoyalFlush
	 */
	static boolean checkRoyalFlush(int[] gezogeneKarten) {
		int[] gezogeneNummern = new int[gezogeneKarten.length];
		// Bedingung wird erst ausgeführt, wenn gezogeneKarten ein StraightFlush
		// ergeben
		if (checkStraightFlush(gezogeneKarten)) {
			for (int i = 0; i < gezogeneKarten.length; i++) {
				// Umwandlung der gezogenenKarten in Wert der Karten
				// (2,3,4,5,6,7,8,9,10,Bube,Dame,König und Ass)
				gezogeneNummern[i] = numberKarte(gezogeneKarten[i]);
			}
			// Kartenwerte werden der Größe nach aufsteigend geordnet
			sortHandCards(gezogeneNummern);
			// Wenn es eine Straße ist und der erste Wert eine 9 ist kann es nur
			// ein RoyalFlush ergeben (9,10,11,12,13)
			if (gezogeneNummern[0] == 9) {
				if(textAusgabe == true){
					System.out.println("Es gibt ein Royal Flush!");}
				anzRoyalFlush++;
				return true;
			}
		}
		return false;
	}

	/**
	 * Checkt ob alle Karten im Array (als Parameter angegeben) die gleiche
	 * Farbe (Pick, Herz, Karo, ...) haben (Flush) Annahme: Das Array hat genau
	 * 5 Elemente
	 * 
	 * @param gezogeneKarten
	 *            Array mit absoluten Kartenzahlen (0-51)
	 * @return true: Alle Karten im Parameter-Array haben die gleiche Farbe;
	 *         false: Nicht alle haben die gleiche Farbe
	 */
	static boolean checkFlush(int[] gezogeneKarten) {
		// Checkt ob die Farbe jedes Array-Eintrages mit der Farbe des ersten
		// Array-Eintrages uebereinstimmt
		// Wenn jede Karte die gleiche Farbe wie die erste Karte hat, haben alle
		// die gleiche Farbe
		if (farbeKarten(gezogeneKarten[0]) == farbeKarten(gezogeneKarten[1])
				&& farbeKarten(gezogeneKarten[0]) == farbeKarten(gezogeneKarten[2])
				&& farbeKarten(gezogeneKarten[0]) == farbeKarten(gezogeneKarten[3])
				&& farbeKarten(gezogeneKarten[0]) == farbeKarten(gezogeneKarten[4])) {
			if(textAusgabe == true){
				System.out.println("Es gibt ein Flush!");}
			anzFlush++;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checkt ob alle Karten im Array (als Parameter angegeben) die gleiche
	 * Farbe (Pick, Herz, Karo, ...) haben (Flush) und eine Straße ergeben
	 * Annahme: Das Array hat genau 5 Elemente
	 * 
	 * @param gezogeneKarten
	 *            Array mit absoluten Kartenzahlen (0-51)
	 * @return true: Alle Karten im Parameter-Array haben die gleiche Farbe und
	 *         ergeben eine Straße; false: Nicht alle haben die gleiche Farbe
	 *         oder ergeben keine Straße
	 */
	static boolean checkStraightFlush(int[] gezogeneKarten) {
		// Methoden müssen in Variable gespeichert werden, da sonst bei nicht
		// eintreten eines Falles die andere Methode nicht aufgerufen wird
		// Weil CheckRoyalFlush CheckStraightFlush aufruft und
		// CheckStraightFlush CheckFlush und CheckStraight aufruft kann es zu
		// einem Problem kommen da eine der beiden
		// Methoden nich aufgerufen werden könnte, jedoch aufgerufen werden
		// müssen, da CheckStraight und CheckFlush nicht einzeln aufgerufen
		// werden
		boolean b1 = checkFlush(gezogeneKarten);
		boolean b2 = checkStraight(gezogeneKarten);
		if (b1 && b2) {
			if(textAusgabe == true){
				System.out.println("Es gibt ein Straight Flush!");}
			anzStraightFlush++;
			return true;
		}
		return false;
	}

	static void information() {
		System.out.println();
		System.out.println("Bei den Karten 0 bis 12 handelt es sich um Karten der Farbe Herz!");
		System.out.println("Bei den Karten 13 bis 25 handelt es sich um Karten der Farbe Karo!");
		System.out.println("Bei den Karten 26 bis 38 handelt es sich um Karten der Farbe Kreuz!");
		System.out.println("Bei den Karten 39 bis 51 handelt es sich um Karten der Farbe Piek!");
	}

	/**
	 * @param poker
	 *            Array mit absoluten Kartenzahlen (0-51), deren Nummern (2-9,
	 *            B, D, K, A) ausgegeben werden sollen
	 */
	static void getValue(int[] poker) {
		for (int i = 0; i < poker.length; i++) {
			System.out.println(i + " " + poker[i]);
		}
	}

	/**
	 * Checkt welche von alle Karten im Array (als Parameter angegeben) die
	 * höchste ist
	 * 
	 * @param gezogeneKarten
	 *            Array mit absoluten Kartenzahlen (0-51)
	 * @return highestCard: Höchste Karte wird zurückgegeben;
	 */
	static int checkHighestCard(int[] gezogeneKarten) {
		// Die highestCard Varibale wird auf den ersten Kartenwert vom Array
		// gezogeneKarten gesetzt
		int highestCard = numberKarte(gezogeneKarten[0]);
		// For Schleife beginnt bei Wert 1, da highestCard schon den Wert von
		// gezogneKarten[0] angenommen hat
		for (int i = 1; i < gezogeneKarten.length; i++) {
			// Überprüft, ob ein Kartenwert von gezogenKarten höher ist als der
			// Wert von highestCard und überschreibt diesen, wenn dies der Fall
			// ist
			if (highestCard < numberKarte(gezogeneKarten[i])) {
				highestCard = numberKarte(gezogeneKarten[i]);
			}
		}
		if(textAusgabe == true){
			System.out.println("Die höchste Karte ist: " + highestCard + " !");}
		return highestCard;
	}

	static void kartenZiehen() {
		int a = (int) ((anzahlKarten) * Math.random());
		gezogeneKarten[0] = poker[a];
		swapRandomNumbers(poker, a, 51);
		int b = (int) ((anzahlKarten--) * Math.random());
		gezogeneKarten[1] = poker[b];
		swapRandomNumbers(poker, b, 50);

		int c = (int) ((anzahlKarten--) * Math.random());
		gezogeneKarten[2] = poker[c];
		swapRandomNumbers(poker, c, 49);

		int d = (int) ((anzahlKarten--) * Math.random());
		gezogeneKarten[3] = poker[d];
		swapRandomNumbers(poker, d, 48);

		int e = (int) ((anzahlKarten--) * Math.random());
		gezogeneKarten[4] = poker[e];
		swapRandomNumbers(poker, e, 47);

		if(textAusgabe == true){
			System.out.println(a);
			System.out.println(b);
			System.out.println(c);
			System.out.println(d);
			System.out.println(e);
			System.out.println();
			System.out.println("Sie haben folgende Karten gezogen: " + gezogeneKarten[0] + " " + gezogeneKarten[1] + " "
					+ gezogeneKarten[2] + " " + gezogeneKarten[3] + " " + gezogeneKarten[4]);}
	}

	static void durchlaufe() {
		int anz = 100000;
		Timestamp beginn = new Timestamp(System.currentTimeMillis());
		for (int i = 0; i < anz; i++) {
			arrayfuellen();
			kartenZiehen();
			anzahlKarten = anzahlKarteProFarbe * anzahlFarben;
			// System.out.println("Wert der gezogenen Karten: ");
			// for(int j = 0; j<gezogeneKarten.length; j++)
			// {
			//
			// System.out.print(numberKarte(gezogeneKarten[j]) + " ");
			// }
			if(textAusgabe == true){
				System.out.println("\n");}
			checkRoyalFlush(gezogeneKarten);
			checkHighestCard(gezogeneKarten);
			// getValue(poker);
			checkPaarTrippleVierlingFullHouse(gezogeneKarten);
		}
		Timestamp ende = new Timestamp(System.currentTimeMillis());
		
		System.out.println();
		System.out.println("Die Anzahl der Paare bei " + anz + " Durchläufen ist " + anzPair + " Relative Häufigkeit "  + (anzPair*100.0)/anz + "%");
		System.out.println("Die Anzahl der Drillinge bei " + anz + " Durchläufen ist " + anzTripple + " Relative Häufigkeit "  + (anzTripple*100.0)/anz + "%");
		System.out.println("Die Anzahl der Vierlinge bei " + anz + " Durchläufen ist " + anzFourOfAKind + " Relative Häufigkeit "  + (anzFourOfAKind*100.0)/anz + "%");
		System.out.println("Die Anzahl der FullHouses bei " + anz + " Durchläufen ist " + anzFullHouse + " Relative Häufigkeit "  + (anzFullHouse*100.0)/anz + "%");
		System.out.println("Die Anzahl der Straßen bei " + anz + " Durchläufen ist " + anzStraight + " Relative Häufigkeit "  + (anzStraight*100.0)/anz + "%");
		System.out.println("Die Anzahl der Flushs bei " + anz + " Durchläufen ist " + anzFlush + " Relative Häufigkeit "  + (anzFlush*100.0)/anz + "%");
		System.out.println("Die Anzahl der StraightFlushs bei " + anz + " Durchläufen ist " + anzStraightFlush + " Relative Häufigkeit "  + (anzStraightFlush*100.0)/anz + "%");
		System.out.println("Die Anzahl der RoyalFlushs bei " + anz + " Durchläufen ist " + anzRoyalFlush + " Relative Häufigkeit "  + (anzRoyalFlush*100.0)/anz + "%");

		DB_Manager.AddResult(beginn, ende, UserID, anzPair, anzTripple, anzFourOfAKind, anzFullHouse, anzFlush, anzStraight, anzStraightFlush, anzRoyalFlush);
	}
	
	public static void UserErstellen()
	{
		DB_Manager.UserErstellen(UserName);
	}
	
	public static void main(String[] args) {
		UserErstellen();
		durchlaufe();
		DB_Manager.AusgabeResults();
		// arrayfuellen();
		// information();
		// kartenZiehen();

		// randomNumberundPaar();

		// Straight Flush
		// gezogeneKarten[0] = 12;
		// gezogeneKarten[1] = 0;
		// gezogeneKarten[2] = 1;
		// gezogeneKarten[3] = 2;
		// gezogeneKarten[4] = 3;

		// Royal Flush
		// gezogeneKarten[0] = 25;
		// gezogeneKarten[1] = 23;
		// gezogeneKarten[2] = 24;
		// gezogeneKarten[3] = 21;
		// gezogeneKarten[4] = 22;

		// Flush
		// gezogeneKarten[0] = 5;
		// gezogeneKarten[1] = 0;
		// gezogeneKarten[2] = 1;
		// gezogeneKarten[3] = 7;
		// gezogeneKarten[4] = 10;

		// Straight
		// gezogeneKarten[0] = 0;
		// gezogeneKarten[1] = 14;
		// gezogeneKarten[2] = 28;
		// gezogeneKarten[3] = 42;
		// gezogeneKarten[4] = 51;

		// Full House
		// gezogeneKarten[0] = 11;
		// gezogeneKarten[1] = 37;
		// gezogeneKarten[2] = 24;
		// gezogeneKarten[3] = 15;
		// gezogeneKarten[4] = 28;

		// Paar
		// gezogeneKarten[0] = 0;
		// gezogeneKarten[1] = 13;
		// gezogeneKarten[2] = 24;
		// gezogeneKarten[3] = 16;
		// gezogeneKarten[4] = 27;

		// Two-Pair
		// gezogeneKarten[0] = 1;
		// gezogeneKarten[1] = 14;
		// gezogeneKarten[2] = 26;
		// gezogeneKarten[3] = 39;
		// gezogeneKarten[4] = 7;

		// Drilling
		// gezogeneKarten[0] = 0;
		// gezogeneKarten[1] = 13;
		// gezogeneKarten[2] = 26;
		// gezogeneKarten[3] = 5;
		// gezogeneKarten[4] = 2;

		// Vierling
		// gezogeneKarten[0] = 0;
		// gezogeneKarten[1] = 13;
		// gezogeneKarten[2] = 26;
		// gezogeneKarten[3] = 39;
		// gezogeneKarten[4] = 7;

		// Highest Card
		// gezogeneKarten[0] = 0;
		// gezogeneKarten[1] = 6;
		// gezogeneKarten[2] = 23;
		// gezogeneKarten[3] = 47;
		// gezogeneKarten[4] = 18;

		// checkFlush(gezogeneKarten);
		// checkStraight(gezogeneKarten);
		// checkFlush(gezogeneKarten);

		// checkRoyalFlush(gezogeneKarten);
		// checkHighestCard(gezogeneKarten);
		// getValue(poker);
		// System.out.println(anzahlKarten);
		// System.out.println("Wert der gezogenen Karten: ");
		// for(int i = 0; i<gezogeneKarten.length; i++)
		// {
		//
		// System.out.print(numberKarte(gezogeneKarten[i]) + " ");
		// }
		// System.out.println("\n");
		// checkPaarTrippleVierlingFullHouse(gezogeneKarten);
	}
}