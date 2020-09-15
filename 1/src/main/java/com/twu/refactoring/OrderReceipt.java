package com.twu.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 * 
 */
public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
	}

	public String printReceipt() {
		StringBuilder output = new StringBuilder();

		// print headers
		final String header="======Printing Orders======\n"
		output.append(header);

		// print date, bill no, customer name
//        output.append("Date - " + order.getDate();
        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());
//        output.append(order.getCustomerLoyaltyNumber());

		// prints lineItems
		double totSalesTx = 0d;
		double tot = 0d;
		for (LineItem lineItem : order.getLineItems()) {
			addAppend(lineItem.getDescription());
			addAppend(lineItem.getPrice());
			addAppend(lineItem.getQuantity());
			addAppend(lineItem.totalAmount());
			// calculate sales tax @ rate of 10%
			float tax=.01
            double salesTax = lineItem.totalAmount() * tax;
            totSalesTx += salesTax;

            // calculate total amount of lineItem = price * quantity + 10 % sales tax
            tot += lineItem.totalAmount() + salesTax;
		}

		// prints the state tax
		output.append("Sales Tax").append('\t').append(totSalesTx);

        // print total amount
		output.append("Total Amount").append('\t').append(tot);
		return output.toString();
	}

	private void addAppend(StringBuilder output, LineItem lineItem) {
		output.append('\t');
	}
}