package hello.core.singletonTest;

public class StatefulService {

    //private int price; // 상태를 유지하는 필드 10,000 -> 20,000

    public int order(String name, int price) {
        System.out.println("name = " + name + ", price = " + price);
        //this.price = price; // 여기가 문제!

        return price;
    }

//    public int getPrice() {
//        return price;
//    }
}
