package model;

	import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

	import com.j256.ormlite.dao.Dao;
	import com.j256.ormlite.dao.DaoManager;
	import com.j256.ormlite.dao.GenericRawResults;
	import com.j256.ormlite.jdbc.JdbcConnectionSource;
	import com.j256.ormlite.stmt.QueryBuilder;
	import com.j256.ormlite.support.ConnectionSource;
	import com.j256.ormlite.table.TableUtils;

	public class ORM_Manager {
		private static ConnectionSource con;
		private static String server = "localhost";
		private static String dbname = "Pokersimulation_mit_ORM";
		private static String pfad = server+"/"+dbname+"?user=root&password="; 
		private static Dao<User_Orm, Integer> User_Orm_Dao;
		private static Dao<Results_Orm, Integer> Result_Orm_Dao;
		

	

		public static ConnectionSource con(User_Orm user, Results_Orm results) {
			if(con == null)
			{
				try {
					con = new JdbcConnectionSource("jdbc:mysql://" + pfad);
					
					ORM_Manager.createDatabase(con);
					
					ORM_Manager.Insert(con, user, results);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return con;
		}
		
		public static void Insert(ConnectionSource con, User_Orm user, Results_Orm results) {
			try {
				
				User_Orm User_Orm = user;
				User_Orm_Dao.create(User_Orm);

				Results_Orm Results_Orm = results;
				Result_Orm_Dao.create(Results_Orm);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		public static void createDatabase(ConnectionSource con) {

			try {
				User_Orm_Dao = DaoManager.createDao(con, User_Orm.class);
				Result_Orm_Dao = DaoManager.createDao(con, Results_Orm.class);

				TableUtils.createTable(con, User_Orm.class);
				TableUtils.createTable(con, Results_Orm.class);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
}
