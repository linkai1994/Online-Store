public class OrderDetails {
	
	private int id;
	
	private String goodsid;
	
	private String name;
	
	private String oid;
	
	private int shopquantity;

	public OrderDetails(int id, String goodsid, String oid, int shopquantity) {
		super();
		this.id = id;
		this.goodsid = goodsid;
		this.oid = oid;
		this.shopquantity = shopquantity;
	}
	
	public OrderDetails(String goodsid, String oid, int shopquantity, String name) {
		super();
		this.goodsid = goodsid;
		this.oid = oid;
		this.shopquantity = shopquantity;
		this.name = name;
	}
	
	public OrderDetails(){}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((goodsid == null) ? 0 : goodsid.hashCode());
		result = prime * result + id;
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime * result + shopquantity;
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
		OrderDetails other = (OrderDetails) obj;
		if (goodsid == null) {
			if (other.goodsid != null)
				return false;
		} else if (!goodsid.equals(other.goodsid))
			return false;
		if (id != other.id)
			return false;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		if (shopquantity != other.shopquantity)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderDetails [id=" + id + ", goodsid=" + goodsid + ", oid=" + oid + ", shopquantity=" + shopquantity
				+ "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public int getShopquantity() {
		return shopquantity;
	}

	public void setShopquantity(int shopquantity) {
		this.shopquantity = shopquantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
