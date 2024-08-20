package util;

public class SQL {
	
	
	public static final String SELECT_TERMS = "select * from terms";
	
	public static final String SELECT_COUNT_USER = "select count(*) from `user` ";
	public static final String WHERE_UID = "WHERE `uid`=?";
	public static final String WHERE_NICK = "WHERE `nick`=?";
	public static final String WHERE_EMAIL = "WHERE `email`=?";
	public static final String WHERE_HP = "WHERE `hp`=?";
	
	public static final String INSERT_USER = "insert into user set"
											+"`uid`=?,"
											+"`pass`=SHA2(?,256),"
											+"`name`=?,"
											+"`nick`=?,"
											+"`email`=?,"
											+"`hp`=?,"
											+"`zip`=?,"
											+"`addr1`=?,"
											+"`addr2`=?,"
											+"`regip`=?,"
											+"`regDate`=NOW()";
	public static final String SELECT_USER = "select pass from user where "
											+"uid=?";
}