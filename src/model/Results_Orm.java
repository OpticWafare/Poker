package model;

import java.sql.Timestamp;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Results_Orm")

public class Results_Orm {

		@DatabaseField(canBeNull = false)
		private Timestamp beginDateTimeOfTest;
		@DatabaseField(canBeNull = false)
		private Timestamp endDateTimeOfTest;
		@DatabaseField(canBeNull = false, generatedId = true)
		private int userID;
		@DatabaseField(canBeNull = false)
		private int nrPairs;
		@DatabaseField(canBeNull = false)
		private int nrTripples;
		@DatabaseField(canBeNull = false)
		private int nrFourOfAKind;
		@DatabaseField(canBeNull = false)
		private int nrFlush;
		@DatabaseField(canBeNull = false)
		private int nrStraight;
		@DatabaseField(canBeNull = false)
		private int nrStraightFlush;
		@DatabaseField(canBeNull = false)
		private int nrRoyalFlush;
		@DatabaseField(canBeNull = false)
		private int nrFullHouse;

		Results_Orm() {

		}

		public Results_Orm(Timestamp beginDateTimeOfTest, Timestamp endDateTimeOfTest, int userID, int nrPairs, int nrTripples,
				int nrFourOfAKind, int nrFlush, int nrStraight, int nrStraightFlush, int nrRoyalFlush,
				int nrFullHouse) {
			this.beginDateTimeOfTest = beginDateTimeOfTest;
			this.endDateTimeOfTest = endDateTimeOfTest;
			this.userID = userID;
			this.nrPairs = nrPairs;
			this.nrTripples = nrTripples;
			this.nrFourOfAKind = nrFourOfAKind;
			this.nrFlush = nrFlush;
			this.nrStraight = nrStraight;
			this.nrStraightFlush = nrStraightFlush;
			this.nrRoyalFlush = nrRoyalFlush;
			this.nrFullHouse = nrFullHouse;
		}

		public Timestamp getBeginDateTimeOfTest() {
			return beginDateTimeOfTest;
		}

		public void setBeginDateTimeOfTest(Timestamp beginDateTimeOfTest) {
			this.beginDateTimeOfTest = beginDateTimeOfTest;
		}

		public Timestamp getEndDateTimeOfTest() {
			return endDateTimeOfTest;
		}

		public void setEndDateTimeOfTest(Timestamp endDateTimeOfTest) {
			this.endDateTimeOfTest = endDateTimeOfTest;
		}

		public int getUserID() {
			return userID;
		}

		public void setUserID(int userID) {
			this.userID = userID;
		}

		public double getNrPairs() {
			return nrPairs;
		}

		public void setNrPairs(int nrPairs) {
			this.nrPairs = nrPairs;
		}

		public double getNrTripples() {
			return nrTripples;
		}

		public void setNrTripples(int nrTripples) {
			this.nrTripples = nrTripples;
		}

		public double getNrFourOfAKind() {
			return nrFourOfAKind;
		}

		public void setNrFourOfAKind(int nrFourOfAKind) {
			this.nrFourOfAKind = nrFourOfAKind;
		}

		public double getNrFlush() {
			return nrFlush;
		}

		public void setNrFlush(int nrFlush) {
			this.nrFlush = nrFlush;
		}

		public double getNrStraightFlush() {
			return nrStraightFlush;
		}

		public void setNrStraightFlush(int nrStraightFlush) {
			this.nrStraightFlush = nrStraightFlush;
		}

		public double getNrRoyalFlush() {
			return nrRoyalFlush;
		}

		public void setNrRoyalFlush(int nrRoyalFlush) {
			this.nrRoyalFlush = nrRoyalFlush;
		}

		public double getNrFullHouse() {
			return nrFullHouse;
		}

		public void setNrFullHouse(int nrFullHouse) {
			this.nrFullHouse = nrFullHouse;
		}

	}