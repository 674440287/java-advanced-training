import java.util.Random;

public class Method1 {
    
    public static void main(String[] args) {
        
        long start=System.currentTimeMillis();

        int result = random(); //这是得到的返回值
        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }
    
    private static int random() {
        return new Random().nextInt();
    }

}
