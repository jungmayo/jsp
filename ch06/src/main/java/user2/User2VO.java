package user2;

public class User2VO {
	
	private String uid;
	private String name;
	private String birth;
	private String adrr;
	
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getAdrr() {
		return adrr;
	}
	public void setAdrr(String adrr) {
		this.adrr = adrr;
	}
	@Override
	public String toString() {
		return "UserVO2 [uid=" + uid + ", name=" + name + ", birth=" + birth + ", adrr=" + adrr + "]";
	}
	
	

}
