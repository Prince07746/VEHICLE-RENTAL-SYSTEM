import java.rmi.server.UID;
import java.util.Objects;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

    }
}


// cars, trucks and motorcycles
//license plate
//colour
//price per day
// ====================
//Customer information:
//
//Name
//        Address
//Age
abstract class Vehicle{
  private String license;
  private String plate;
  private String colour;
  private double price_per_day;

    public Vehicle(String colour, double price_per_day) {
        this.license = UUID.randomUUID().toString();
        // split the random id plate to have a small part of the id
        String[] splited = UUID.randomUUID().toString().split("-");
        this.plate = splited[0];
        this.colour = colour;
        this.price_per_day = price_per_day;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public double getPrice_per_day() {
        return price_per_day;
    }

    public void setPrice_per_day(double price_per_day) {
        this.price_per_day = price_per_day;
    }

    @Override
    public String toString() {
        return this.getClass().getName()+
                "| license=" + license +
                "| plate=" + plate +
                "| colour=" + colour +
                "| price_per_day=" + price_per_day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Double.compare(price_per_day, vehicle.price_per_day) == 0 && Objects.equals(license, vehicle.license) && Objects.equals(plate, vehicle.plate) && Objects.equals(colour, vehicle.colour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(license, plate, colour, price_per_day);
    }
}

class Car extends Vehicle{
    public Car(String colour, double price_per_day) {
        super(colour, price_per_day);
    }
}

class Truck extends Vehicle{
    public Truck(String colour, double price_per_day) {
        super(colour, price_per_day);
    }
}

class Motorcycle extends Vehicle{
    public Motorcycle(String colour, double price_per_day) {
        super(colour, price_per_day);
    }
}





class Customer{
    private String name;
    private String address;
    private int age;

    public Customer(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return age == customer.age && Objects.equals(name, customer.name) && Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, age);
    }
}


