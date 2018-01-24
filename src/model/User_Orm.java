package model;

import java.sql.Timestamp;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "User_Orm")

public class User_Orm {
	
		
		@DatabaseField(generatedId = true)
		private int userID;
		
		@DatabaseField(canBeNull = false)
		private String userName;
		
		@DatabaseField(canBeNull = false)
		private Timestamp userRegistrationDate;

		User_Orm() {
			
		}
		
		public User_Orm(int userID, String userName, Timestamp registrationdate) {
			this.userID = userID;
			this.userName = userName;
			this.userRegistrationDate = registrationdate;
		}

		public int getUserID() {
			return userID;
		}

		public void setUserID(int userID) {
			this.userID = userID;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public Timestamp getUserRegistrationDate() {
			return userRegistrationDate;
		}

		public void setUserRegistrationDate(Timestamp userRegistrationDate) {
			this.userRegistrationDate = userRegistrationDate;
		}

}