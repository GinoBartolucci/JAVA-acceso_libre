import data.*;
import entities.*;

public class Main {
    public static void main(String[] args) {
        DataProvincia dp = new DataProvincia();
        Provincia p = new Provincia();
        p.setId(1);
        System.out.println(dp.findOne(p).getNombre());
        System.out.println("Hello world!");
        System.out.println("Hello world!");
    }
}