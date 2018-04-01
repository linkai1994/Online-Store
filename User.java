import java.util.Date;

public class User {
	
	private String uuid;
	
	private String loginname;
	
	private String loginpass;
	
	private String headimg;
	
	private Date createtime;

	public User(String uuid, String loginname, String loginpass, String headimg, Date createtime) {
		super();
		this.uuid = uuid;
		this.loginname = loginname;
		this.loginpass = loginpass;
		this.headimg = headimg;
		this.createtime = createtime;
	}
	
	public User(){}

	public User(String uuid2, String loginname2, String loginpass2, String headimg2, String string) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createtime == null) ? 0 : createtime.hashCode());
		result = prime * result + ((headimg == null) ? 0 : headimg.hashCode());
		result = prime * result + ((loginname == null) ? 0 : loginname.hashCode());
		result = prime * result + ((loginpass == null) ? 0 : loginpass.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (createtime == null) {
			if (other.createtime != null)
				return false;
		} else if (!createtime.equals(other.createtime))
			return false;
		if (headimg == null) {
			if (other.headimg != null)
				return false;
		} else if (!headimg.equals(other.headimg))
			return false;
		if (loginname == null) {
			if (other.loginname != null)
				return false;
		} else if (!loginname.equals(other.loginname))
			return false;
		if (loginpass == null) {
			if (other.loginpass != null)
				return false;
		} else if (!loginpass.equals(other.loginpass))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [uuid=" + uuid + ", loginname=" + loginname + ", loginpass=" + loginpass + ", headimg=" + headimg
				+ ", createtime=" + createtime + "]";
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getLoginpass() {
		return loginpass;
	}

	public void setLoginpass(String loginpass) {
		this.loginpass = loginpass;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	

}
