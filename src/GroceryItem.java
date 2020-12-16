public class GroceryItem extends Item {
    private double price;


    public GroceryItem(){
    }

    public GroceryItem(String name,  int quantity, int priority, boolean purchased,  double price) {
        super(name, quantity, priority, purchased);
        this.price = price;
    }

    @Override
    public boolean isPurchased() {
        return super.isPurchased();
    }

    @Override
    public boolean setPruchased(boolean purchased) {
        return super.setPruchased(purchased);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof GroceryItem) {
            return super.getName().equalsIgnoreCase(((GroceryItem) other).getName())
                    && super.getPriority() == ((GroceryItem) other).getPriority()
                    && super.isPurchased() == ((GroceryItem) other).isPurchased()
                    && this.price == ((GroceryItem) other).getPrice();
        }
        return false;
    }

    public GroceryItem copy() {
        GroceryItem newGroceryItem = new GroceryItem(this.name, this.quantity, this.priority, this.purchased, this.price);
        return newGroceryItem;
    }
}
