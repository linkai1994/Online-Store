import java.util.Date;

public class Order {
	
	private String uuid;
	
	private String userid;
	
	private float totalcost;
	
	private Date createtime;

	public Order(String uuid, String userid, float totalcost, Date createtime) {
		super();
		this.uuid = uuid;
		this.userid = userid;
		this.totalcost = totalcost;
		this.createtime = createtime;
	}

	public Order(){}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createtime == null) ? 0 : createtime.hashCode());
		result = prime * result + Float.floatToIntBits(totalcost);
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
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
		Order other = (Order) obj;
		if (createtime == null) {
			if (other.createtime != null)
				return false;
		} else if (!createtime.equals(other.createtime))
			return false;
		if (Float.floatToIntBits(totalcost) != Float.floatToIntBits(other.totalcost))
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
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
		return "Order [uuid=" + uuid + ", userid=" + userid + ", totalcost=" + totalcost + ", createtime=" + createtime
				+ "]";
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public float getTotalcost() {
		return totalcost;
	}

	public void setTotalcost(float totalcost) {
		this.totalcost = totalcost;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
}
