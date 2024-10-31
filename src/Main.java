import java.rmi.server.UID;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    static GarageRentalSystem systemRenting = new GarageRentalSystem();
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {

        //  customers are instantiated here
        Customer customer1 = new Customer("Prince","kampala",20);
        Customer customer2 = new Customer("Paolo","Palermo",20);
        Customer customer3 = new Customer("Veronica","Roma",20);


        // this will add all type of  vehicle  you want
        systemRenting.addVehicle();
        System.out.println("==================");


        // VIEW ALL THE STOCK OF OUR CARS
        systemRenting.viewAllCar();

        // this will show you all rented cars and customer
        systemRenting.viewRentingInfo();
        System.out.println("==================");

        // 3. rent a car
        System.out.println("Rent a vehicle");
        System.out.print("Enter the plate of the vehicle: ");
        String plateCar = input.nextLine();
        systemRenting.rentVehicle(customer1,plateCar);
        System.out.println("=================================");


        // 1. check if the vehicle is currently free or rented out
        System.out.print("Enter the plate to check if vehicle is free or rented: ");
        String plate = input.nextLine();
        systemRenting.checkVehicle(plate);



        // 2. calculate total rental price for the customer
        systemRenting.calculateTotalPrice();



    }

}

// super or main class for all kind of vehicles
// ===========================================
abstract class Vehicle{
  private String license;
  private String plate;
  private String colour;
  private double price_per_day;

    public Vehicle(String colour) {
        this.license = UUID.randomUUID().toString();
        // split the random id plate to have a small part of the id
        String[] splited = UUID.randomUUID().toString().split("-");
        this.plate = splited[0];
        this.colour = colour;
        this.price_per_day = 0;
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

    public Car(String colour,double price_per_day) {
        super(colour);
        setPrice_per_day(price_per_day);
    }

    public double getPrice(int days){
        return getPrice_per_day()*days;
    }
}

class Truck extends Vehicle{
    public Truck(String colour, double price_per_day) {
        super(colour);
        setPrice_per_day(price_per_day);
    }
    public double getPrice(int days){
        return getPrice_per_day()*days;
    }
}

class Motorcycle extends Vehicle{
    public Motorcycle(String colour, double price_per_day) {
        super(colour);
        setPrice_per_day(price_per_day);
    }
    public double getPrice(int days){
        return getPrice_per_day()*days;
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


class RentalSystem{
    Customer customer;
    Vehicle vehicle;
    int rentDays;

    public RentalSystem(Customer customer, Vehicle vehicle, int rentDays) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.rentDays = rentDays;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getRentDays() {
        return rentDays;
    }

    public void setRentDays(int rentDays) {
        this.rentDays = rentDays;
    }
}



class GarageRentalSystem{

    ArrayList<RentalSystem> garageRented = new ArrayList<>();
    ArrayList<Vehicle> garageCar = new ArrayList<>();
    static Scanner input = new Scanner(System.in);
    public GarageRentalSystem(){

    }
    public void addVehicle(){
        System.out.println("ADD VEHICLE");
        System.out.println("============");
        System.out.println("1. car");
        System.out.println("2. truck");
        System.out.println("3. motorcycle");
        System.out.println("Enter: ");
        String option  = input.nextLine();
        switch (option){
            case "1":

                System.out.println("Car");
                System.out.println("===");
                System.out.print("Enter color: ");
                String color = input.nextLine();
                System.out.print("Enter Price per day: ");
                double pricePerDay = input.nextDouble();
                input.nextLine();
                garageCar.add(new Car(color,pricePerDay));
                System.out.println("car added successfully");
                break;
            case "2":
                System.out.println("Truck");
                System.out.println("===");
                System.out.print("Enter color: ");
                String colorTruck = input.nextLine();
                System.out.print("Enter Price per day: ");
                double pricePerDayTuck = input.nextDouble();
                input.nextLine();
                garageCar.add(new Truck(colorTruck,pricePerDayTuck));
                System.out.println("Truck added successfully");
                break;
            case "3":
                System.out.println("Motorcycle");
                System.out.println("===");
                System.out.print("Enter color: ");
                String colorMotorcycle = input.nextLine();
                System.out.print("Enter Price per day: ");
                double pricePerDayMotorcycle = input.nextDouble();
                input.nextLine();
                garageCar.add(new Motorcycle(colorMotorcycle,pricePerDayMotorcycle));
                System.out.println("Motorcycle added successfully");
                break;
            default:
                System.out.println("Wrong number entered");
                break;
        }
    }
    public void rentVehicle(Customer customer,String carPlate){
        boolean check = true;
        for(RentalSystem rentalSystem:garageRented){
            if(rentalSystem.vehicle.getPlate().equals(carPlate)){
                System.out.println("car already rented");
                check = false;
            }
        }
        if(!check){
            System.out.print("Enter the number of days you want to rent: ");
            int days = input.nextInt();
            input.nextLine();
            for(Vehicle vehicle:garageCar){
                if(vehicle.getPlate().equals(carPlate)){
                    garageRented.add(new RentalSystem(customer,vehicle,days));
                    System.out.println("Car added successfully");
                }
            }
        }
    }
    public void viewRentingInfo(){
        if(garageRented.isEmpty()){
            System.out.println("not Rented Car in the garage");
        }
        for(RentalSystem rented:garageRented){
            System.out.println("Vehicle Rented");
            System.out.println("---------------");
            System.out.println(rented.getVehicle().toString());
            System.out.println("Customer who rented");
            System.out.println("-------------------");
            System.out.println(rented.getCustomer().toString());
            System.out.println("TOTAL COST for: "+rented.getRentDays()+" days : "+rented.getRentDays()*rented.getVehicle().getPrice_per_day());

        }
    }

    public void viewAllCar(){
        System.out.println("Garage of our Cars");
        System.out.println("==================");
        for(Vehicle vehicle:garageCar){
            System.out.println(vehicle.toString());
        }
    }

    public void checkVehicle(String carPlate){
        boolean check = true;
        for(RentalSystem rentalSystem:garageRented){
            if(rentalSystem.vehicle.getPlate().equals(carPlate)){
                System.out.println("Vehicle already rented");
                check = false;
            }
        }
        if(!check){
            System.out.println("Vehicle is FREE");
        }
    }
    public void calculateTotalPrice(){
        for(RentalSystem rentalSystem:garageRented){
            System.out.println("Customer "+rentalSystem.getCustomer().getName()+" | "+rentalSystem.rentDays*rentalSystem.getVehicle().getPrice_per_day());
        }
    }


}



