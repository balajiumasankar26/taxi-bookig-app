public class Booking {
    int bookingId;
    String src,des;
    long fair;
    Driver driver;
    Customer customer;
    long zulaCommision;
    public Booking(int bookingId, String src, String des, long fair, Driver driver, Customer customer) {
        this.bookingId = bookingId;
        this.src = src;
        this.des = des;
        this.fair = fair;
        this.driver = driver;
        this.customer = customer;
        zulaCommision=(long)(fair*0.3);
    }
    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", src='" + src + '\'' +
                ", des='" + des + '\'' +
                ", fair=" + fair +
                ", driver=" + driver +
                ", customer=" + customer +
                '}';
    }
}
