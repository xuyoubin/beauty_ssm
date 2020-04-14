package design.factory;

public class FactoryComputer extends Factory {

    /**
     * 让之类实现具体方法
     * @return
     */
    public  Product create(){
        System.out.println("创建电脑");
        return new ProductComputer();
    }

}
