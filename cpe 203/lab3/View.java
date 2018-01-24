public class View
{
	private String sessionId;
	private String productId;
	private int price;

	public View(String sessionId, String productId, int price)
	{
		this.sessionId = sessionId;
		this.productId = productId;
		this.price = price;
	}

	public boolean equals(Object other)
	{
		if (other == null) { return false; }
		if (getClass() != other.getClass()) { return false; }
		return (sessionId == ((View)other).sessionId &&
				productId == ((View)other).productId &&
				price == ((View)other).price);
	}

	public String getsessionId() { return sessionId; }
	public String getProduct() { return productId; }
	public int getprice() { return price; }
}