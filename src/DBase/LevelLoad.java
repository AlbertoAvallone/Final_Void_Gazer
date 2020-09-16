package DBase;

import java.sql.ResultSet;
import java.sql.SQLException;


public class LevelLoad {
	DBManager db;

	public LevelLoad ()  {

		try {
			db = new DBManager();
			db.executeQuery("SELECT * FROM Nave LIMIT 1");
		} catch (SQLException e) {
			/* creating a new db */
			System.out.println("Creo il DB da zero");
			try {
				db.executeUpdate("DROP TABLE IF EXISTS asteroide");
				db.executeUpdate("CREATE TABLE asteroide ("+ "num INTEGER PRIMARY KEY,"+ "level INTEGER," + "x DOUBLE,"+ "y DOUBLE," +"v_x DOUBLE," + "v_y DOUBLE,"+ "radius DOUBLE" + ")");
				db.executeUpdate("DROP TABLE IF EXISTS nave");
				db.executeUpdate("CREATE TABLE nave ("+ "lev INTEGER PRIMARY KEY," + "xx DOUBLE,"+ "yy DOUBLE," + "fuel DOUBLE" +")");
				db.executeUpdate("DROP TABLE IF EXISTS traguardo");
				db.executeUpdate("CREATE TABLE traguardo (" +"l INTEGER PRIMARY KEY," + "xxx DOUBLE,"+ "yyy DOUBLE"+ ")");
				db.executeUpdate("DROP TABLE IF EXISTS border");
				db.executeUpdate("CREATE TABLE border (" + "ll INTEGER PRIMARY KEY,"+ "x DOUBLE," + "y DOUBLE," +" width DOUBLE,"+ " height DOUBLE"+")");

				/* adding the data */
				db.executeUpdate("INSERT INTO asteroide (num, level, x, y, v_x, v_y, radius) VALUES (0, 0, 300.0, 20.0, 0.0, 0.0, 10.0)");
				db.executeUpdate("INSERT INTO nave (lev, xx, yy, fuel) VALUES (0, 20.0, 20.0, 100.0)");
				db.executeUpdate("INSERT INTO traguardo (l, xxx, yyy) VALUES (0, 700.0, 20.0)");
				db.executeUpdate("INSERT INTO border (ll, x, y, width, height) VALUES (0, 0.0, 0.0, 2500.0, 1600.0)");

				db.executeUpdate("INSERT INTO asteroide (num, level, x, y, v_x, v_y, radius) VALUES (1, 1, 740.0, 240.0, 0.0, 0.0, 180.0)");
				db.executeUpdate("INSERT INTO asteroide (num, level, x, y, v_x, v_y, radius) VALUES (2, 1, 490.0, 450.0, 0.0, 0.0, 100.0)");
				db.executeUpdate("INSERT INTO asteroide (num, level, x, y, v_x, v_y, radius) VALUES (3, 1, 380.0, 620.0, 0.0, 0.0, 94.0)");
				db.executeUpdate("INSERT INTO asteroide (num, level, x, y, v_x, v_y, radius) VALUES (4, 1, 280.0, 860.0, 0.0, 0.0, 125.0)");
				db.executeUpdate("INSERT INTO asteroide (num, level, x, y, v_x, v_y, radius) VALUES (5, 1, 174.0, 340.0, 0.0, 0.0, 28.0)");
				db.executeUpdate("INSERT INTO asteroide (num, level, x, y, v_x, v_y, radius) VALUES (6, 1, 850.0, 530.0, 0.0, 0.0, 121.0)");
				db.executeUpdate("INSERT INTO asteroide (num, level, x, y, v_x, v_y, radius) VALUES (7, 1, 650.0, 766.0, 0.0, 0.0, 109.0)");
				db.executeUpdate("INSERT INTO asteroide (num, level, x, y, v_x, v_y, radius) VALUES (8, 1, 525.0, 930.0, 0.0, 0.0, 46.0)");
				db.executeUpdate("INSERT INTO asteroide (num, level, x, y, v_x, v_y, radius) VALUES (9, 1, 640.0, 570.0, 0.0, 0.0, 24.0)");
				db.executeUpdate("INSERT INTO asteroide (num, level, x, y, v_x, v_y, radius) VALUES (10, 1, 900.0, 790.0, 0.0, 0.0, 60.0)");
				db.executeUpdate("INSERT INTO asteroide (num, level, x, y, v_x, v_y, radius) VALUES (11, 1, 100.0, 730.0, 0.0, 0.0, 80.0)");
				db.executeUpdate("INSERT INTO asteroide (num, level, x, y, v_x, v_y, radius) VALUES (12, 1, 310.0, 327.0, 0.0, 0.0, 34.0)");
				db.executeUpdate("INSERT INTO asteroide (num, level, x, y, v_x, v_y, radius) VALUES (13, 1, 210.0, 624.0, 0.0, 0.0, 20.0)");
				db.executeUpdate("INSERT INTO asteroide (num, level, x, y, v_x, v_y, radius) VALUES (14, 1, 94.0, 560.0, 0.0, 0.0, 20.0)");
				db.executeUpdate("INSERT INTO asteroide (num, level, x, y, v_x, v_y, radius) VALUES (15, 1, 190.0, 510.0, 0.0, 0.0, 20.0)");
				db.executeUpdate("INSERT INTO asteroide (num, level, x, y, v_x, v_y, radius) VALUES (16, 1, 75.0, 470.0, 0.0, 0.0, 20.0)");
				db.executeUpdate("INSERT INTO asteroide (num, level, x, y, v_x, v_y, radius) VALUES (17, 1, 290.0, 456.0, 0.0, 0.0, 20.0)");
				db.executeUpdate("INSERT INTO asteroide (num, level, x, y, v_x, v_y, radius) VALUES (18, 1, 107.0, 145.0, 0.0, 0.0, 51.0)");
				db.executeUpdate("INSERT INTO asteroide (num, level, x, y, v_x, v_y, radius) VALUES (19, 1, 374.0, 200.0, 0.0, 0.0, 63.0)");
				db.executeUpdate("INSERT INTO asteroide (num, level, x, y, v_x, v_y, radius) VALUES (20, 1, 500.0, 110.0, 0.0, 0.0, 38.0)");
				db.executeUpdate("INSERT INTO nave (lev, xx, yy, fuel) VALUES (1, 200.0, 200.0, 100.0)");
				db.executeUpdate("INSERT INTO traguardo (l, xxx, yyy) VALUES (1, 800.0, 900.0)");
				db.executeUpdate("INSERT INTO border (ll, x, y, width, height) VALUES (1, 500.0, 500.0, 1000.0, 1000.0)");

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println("Error loading the level");
				e1.printStackTrace();
			}


		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Creo il DBManager");
		}
	}
	/* in i there is the number of the level to search in the dB */
	public ResultSet extractNave(int i)  {
		try {
			return db.executeQuery("SELECT * FROM nave WHERE lev = " + i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	public ResultSet extractAsteroidi(int i) {
		try {
			return db.executeQuery("SELECT * FROM asteroide WHERE level = " + i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public ResultSet extractTraguardo(int i)  {
		try {
			return db.executeQuery("SELECT * FROM traguardo WHERE l = " + i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
	}

	public ResultSet extractBorder(int i) {
		try {
			return db.executeQuery("SELECT * FROM border WHERE ll = " + i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	/*different printing methods*/
	public void printNave(ResultSet r)  {
		try {
			System.out.println("("  + "lev=" + r.getInt("lev") + ", xx=" + r.getDouble("xx") + ", yy=" + r.getDouble("yy") + ", fuel=" + r.getDouble("fuel")+ ")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Stamp error");
		}

	}

	public void printAsteroidi(ResultSet rs)  {
		try {
			System.out.println("("+"num="+ rs.getInt("num")+ ", level=" + rs.getInt("level") + ", x=" + rs.getDouble("x") + ", y=" + rs.getDouble("y") + ", v_x=" + rs.getDouble("v_x") + ", v_y="	+ rs.getDouble("v_y") + ", radius=" + rs.getDouble("radius")+ ")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Stamp error");
		}

	}



}
