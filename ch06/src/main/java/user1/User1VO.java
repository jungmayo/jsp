package user1;

public class User1VO {
	
	private String uid;
	private String name;
	private String hp;
	private int age;
	private String adr;
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
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAdr() {
		return adr;
	}
	public void setAdr(String adr) {
		this.adr = adr;
	}
	@Override
	public String toString() {
		return "User1VO [uid=" + uid + ", name=" + name + ", hp=" + hp + ", age=" + age + ", adr=" + adr + "]";
	}
	
	
}
