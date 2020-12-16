public abstract class Item {
    protected String name;
    protected int quantity;
    protected int priority;
    protected boolean purchased;

    public Item() {
    }

    public Item(String name, int quantity, int priority, boolean purchased) {
        this.name = name;
        this.quantity = quantity;
        this.priority = priority;
        this.purchased = false;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int newPriority) {
        this.priority = newPriority;
    }

    public boolean isPurchased(){
        return this.purchased;
    }

    public boolean setPruchased(boolean purchased){
        return this.purchased = true;
    }

    public boolean equals(Object other) {
        if (other instanceof Item) {
            return this.name.equalsIgnoreCase(((Item) other).getName())
                    && this.priority == ((Item) other).getPriority()
                    && this.purchased == ((Item) other).isPurchased();
        }
        return false;
    }
}