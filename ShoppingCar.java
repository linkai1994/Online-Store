public class ShoppingCar {
	
	private String goodsid;
	
	private String goodsname;
	
	private float goodsprice;
	
	private int goodsqutity;
	
	private float goodtotoalprice;

	public ShoppingCar(String goodsid, String goodsname, float goodsprice, int goodsqutity, float goodtotoalprice) {
		super();
		this.goodsid = goodsid;
		this.goodsname = goodsname;
		this.goodsprice = goodsprice;
		this.goodsqutity = goodsqutity;
		this.goodtotoalprice = goodtotoalprice;
	}
	
	public ShoppingCar(){}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((goodsid == null) ? 0 : goodsid.hashCode());
		result = prime * result + ((goodsname == null) ? 0 : goodsname.hashCode());
		result = prime * result + Float.floatToIntBits(goodsprice);
		result = prime * result + goodsqutity;
		result = prime * result + Float.floatToIntBits(goodtotoalprice);
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
		ShoppingCar other = (ShoppingCar) obj;
		if (goodsid == null) {
			if (other.goodsid != null)
				return false;
		} else if (!goodsid.equals(other.goodsid))
			return false;
		if (goodsname == null) {
			if (other.goodsname != null)
				return false;
		} else if (!goodsname.equals(other.goodsname))
			return false;
		if (Float.floatToIntBits(goodsprice) != Float.floatToIntBits(other.goodsprice))
			return false;
		if (goodsqutity != other.goodsqutity)
			return false;
		if (Float.floatToIntBits(goodtotoalprice) != Float.floatToIntBits(other.goodtotoalprice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ShoppingCar [goodsid=" + goodsid + ", goodsname=" + goodsname + ", goodsprice=" + goodsprice
				+ ", goodsqutity=" + goodsqutity + ", goodtotoalprice=" + goodtotoalprice + "]";
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public float getGoodsprice() {
		return goodsprice;
	}

	public void setGoodsprice(float goodsprice) {
		this.goodsprice = goodsprice;
	}

	public int getGoodsqutity() {
		return goodsqutity;
	}

	public void setGoodsqutity(int goodsqutity) {
		this.goodsqutity = goodsqutity;
	}

	public float getGoodtotoalprice() {
		return goodtotoalprice;
	}

	public void setGoodtotoalprice(float goodtotoalprice) {
		this.goodtotoalprice = goodtotoalprice;
	}
	
	

}
