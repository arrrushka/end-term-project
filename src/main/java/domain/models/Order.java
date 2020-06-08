package domain.models;

import java.util.ArrayList;
import java.util.List;

public class Order {
        private List<Book> items;

        public Order(){
            items=new ArrayList<>();
        }

        public List<Book> getBooks(){
            return items;
        }

        public double getTotalPrice(){
            double sum=0.0;
            for (Book book :items) {
                sum=book.getPrice();
            }
            return sum;
        }

        public void addItem(Book book){
            items.add(book);
        }

    public void doCheckout() {
        System.out.println("Get items from cart,");
        System.out.println("Set delivery address,");
        System.out.println("Set billing address.");
    }

    public void doPayment() {
        System.out.println("Process payment without Card present");
    }

    public void doDelivery() {
        System.out.println("Ship the item to address");
    }

    public void doReceipt() {
        System.out.println("Email receipt");
    }

}
