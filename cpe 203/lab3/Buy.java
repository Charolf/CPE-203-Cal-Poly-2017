public class Buy
{
	private String sessionId;
	private String productId;
	private int price;
	private int quantity;

	public Buy(String sessionId, String productId, int price, int quantity)
	{
		this.sessionId = sessionId;
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
	}

	public boolean equals(Object other)
	{
		if (other == null) { return false; }
		if (getClass() != other.getClass()) { return false; }
		return (sessionId == ((Buy)other).sessionId &&
				productId == ((Buy)other).productId &&
				price == ((Buy)other).price &&
				quantity == ((Buy)other).quantity);
	}

	public String getsessionId() { return sessionId; }
	public String getProduct() { return productId; }
	public int getprice() { return price; }
	public int getquantity() { return quantity; }

}

