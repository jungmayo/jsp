package util;

public class SQL {
	
	
	public static final String SELECT_TERMS = "select * from terms";
	
	public static final String SELECT_COUNT_USER = "select count(*) from `user` ";
	public static final String WHERE_UID = "WHERE `uid`=?";
	public static final String WHERE_NICK = "WHERE `nick`=?";
	public static final String WHERE_EMAIL = "WHERE `email`=?";
	public static final String WHERE_HP = "WHERE `hp`=?";
	
	public static final String SELECT_USER = "select * from `user` where `uid`=? and `pass`=SHA2(?,256)";

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
	public static final String INSERT_ARTICLE = "insert into article set"
											//+"`cate`=?,"
											+"`title`=?,"
											+"`content`=?,"
											+"`file`=?,"
											+"`writer`=?,"
											+"`regip`=?,"
											+"`rdate`=NOW()";
	public static final String UPDATE_ARTICLE = "update `article` set `hit` = `hit` + 1 where `no`=?";
	
	
	public static final String SELECT_ARTICLES = "select a.*, b.nick, "
											+ "ROW_NUMBER () OVER ( ORDER BY `no` ASC) "
											+ "from `article` AS a "
											+ "JOIN `user` AS b on a.writer = b.uid "
											+ "ORDER BY `no` DESC "
											+ "LIMIT ?, 10";
											
	
	public static final String INSERT_FILE = "insert into file set "
											+"`ano`=?,"
											+"`oName`=?,"
											+"`sName`=?,"
											+"`rdate`=NOW()";
	public static final String SELET_MAX_NO = "select MAX(`no`) from `article`";
	
	public static final String SELECT_COUNT_TOTAL = "SELECT COUNT(*) FROM `article`";
	
	public static final String SELECT_ARTICLE = "SELECT * FROM `article` As a "
												+ "left Join `file` as B on a.`no` = b.ano "
												+ "where `no`=?";
	
	
	public static final String SELECT_FILE = "select * from `file` where `fno`=?";
	public static final String UPDATE_FILE_DOWNLOAD_COUNT = "update file set `download` = `download` + 1 where `fno`=?";
}