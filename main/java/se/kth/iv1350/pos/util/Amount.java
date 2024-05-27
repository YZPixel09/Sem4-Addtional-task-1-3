package se.kth.iv1350.pos.util;
;
/**
 * The <code>Amount</code> class wraps an amount of money. Instances 
 * are immutable. The class is not complete since it ignores currency.
 */
public final class Amount {
    private final int amount;
    
    

    /**
     * Constructs an <code>Amount</code> object with the specified amount.
     *
     * @param  amount The amount to be represented by the created object.
     */
    public Amount(int amount) {
	this.amount = amount;
    }

    /**
     * Returns the amount of this object.
     *
     * @return    The amount of this object.
     */
    public int getAmount() {
	return amount;
    }

    /**
     * Subtracts the amount of the specified <code>Amount</code> from
     * the amount of this object and returns a <code>Amount</code> with
     * the result.
     *
     * @param  amount The <code>Amount</code> whose amount shall be subtracted.
     * @return        The result of the subtraction.
     */
    public Amount minus(Amount amount) {
	return new Amount(getAmount() - amount.getAmount());
    }

    /**
     * Adds the amount of the specified <code>Amount</code> to
     * the amount of this object and returns a <code>Amount</code> with
     * the result.
     *
     * @param  amount The <code>Amount</code> whose amount shall be added.
     * @return        The result of the addition.
     */
    public Amount plus(Amount amount) {
	return new Amount(getAmount() + amount.getAmount());
    }

    /**
     * Multiplies the specified factor with the amount of this object and 
     * returns a <code>Amount</code> with the result.
     *
     * @param  factor The factor of the multiplication.
     * @return        The result of the multiplication.
     */
    public Amount times(int factor) {
	return new Amount(getAmount() * factor);
    }

    /**
     * Compares this object with the specified object. The result is true
     * if the argument is an instance of <code>Amount</code> and has the same
     * amount as this object.
     *
     * @param obj  the object to compare with.
     * @return     <code>true</code> if the objects are the same; 
     *             <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object obj) {
	if (!(obj instanceof Amount)) {
	    return false;
	}

	Amount other = (Amount)obj;
	return (other.getAmount() == getAmount());
    }

    /**
     * Returns a hash code for this <code>Amount</code>.
     *
     * @return a hash code for this object, equal to the amount.
     */
    @Override
    public int hashCode() {
	return amount;
    }

    /**
     * Returns a string representation of the <code>Amount</code>.
     *
     * @return A string representation of the amount.
     */
    @Override
    public String toString() {
        return String.valueOf(amount);
    }
}