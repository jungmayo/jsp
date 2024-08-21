package dto;

public class FileDTO {
	private int fno;
	private int ano;
	private String oname;
	private String sname;
	private int download;
	private String rdate;
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getOname() {
		return oname;
	}
	public void setOname(String oname) {
		this.oname = oname;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public int getDownload() {
		return download;
	}
	public void setDownload(int download) {
		this.download = download;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	@Override
	public String toString() {
		return "FileDTO [fno=" + fno + ", ano=" + ano + ", oname=" + oname + ", sname=" + sname + ", download="
				+ download + ", rdate=" + rdate + "]";
	}
	
	

}
