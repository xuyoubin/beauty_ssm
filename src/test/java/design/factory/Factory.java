package design.factory;

import java.util.List;

public abstract class Factory {

    /**
     * 让之类实现具体方法
     * @return
     */
    public abstract Product create();

    /**
     * 调用产品
     * @param product
     */
    public void process(Product product ){
       product.show();
    }

    public static void main(String[] args){
//        Factory factory = new FactoryPhone();
//        Product product = factory.create();
//        factory.process(product);
//        Class.forName()
        String str = ",4";
        String[] s = str.split(",");
        Integer.valueOf(s[0]);
        System.out.println(s[0]+s[1]);
    }
}
