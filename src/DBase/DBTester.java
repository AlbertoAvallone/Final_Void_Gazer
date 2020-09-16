package DBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBTester {
		DBManager db;
		ResultSet rNavi;
		ResultSet rAster;
		
		public DBTester (DBManager db) throws SQLException {
			this.db = db;	
			rAster = db.executeQuery("SELECT * FROM Asteroide");
			rNavi = db.executeQuery("SELECT * FROM Nave");
			
			System.out.println("(" + rNavi.getObject(1) + ", " + rNavi.getObject(2) + ", " + rNavi.getObject(3) + ", " + rNavi.getObject(4) +")" );
			
			
			System.out.println("(" + rAster.getObject(1) + ", " + rAster.getObject(2) + ", " + rAster.getObject(3) + ", "  + rAster.getObject(4) + ", " + rAster.getObject(5) + ", " + rAster.getObject(6) + ", " + rAster.getObject(7) + ")" );
			rAster.next();
			System.out.println("(" + rAster.getObject(1) + ", " + rAster.getObject(2) + ", " + rAster.getObject(3) + ", "  + rAster.getObject(4) + ", " + rAster.getObject(5) + ", " + rAster.getObject(6) + ", " + rAster.getObject(7) + ")" );
			
		}
		
		
}
