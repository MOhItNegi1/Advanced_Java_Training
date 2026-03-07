package PKG;



import java.util.Scanner;

public class MainDriver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1: for Petrol engine");
        System.out.println("2. for Diesel engine");
        System.out.print("Enter engine choioce: ");
        int choice = sc.nextInt();
        sc.nextLine();
        Car car = new Car();
        Engine e = null;
        switch (choice){
            case 1:
                e=new PetrolEngine();
                break;
            case 2:
                e=new DieselEngine();
                break;
        }
        car.setEngine(e);

        car.getEngine().run();

        System.out.println(car.getEngine().getClass());

    }
}
