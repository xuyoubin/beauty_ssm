package design.factory;

public class FactoryPhone extends Factory {

    /**
     * 让之类实现具体方法
     * @return
     */
    public  Product create(){
        System.out.println("创建手机");
        return new ProductPhone();
    }

}
