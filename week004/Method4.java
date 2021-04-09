import java.util.Random;
import java.util.concurrent.*;

public class Method4 {
     public static void main(String[] args) {

       MyThread2 myThread2 = new MyThread2();

      FutureTask<Integer> futureTask = new FutureTask<>(myThread2);
      new Thread(futureTask, "线程名：有返回值的线程2").start();

       try {
             System.out.println(futureTask.get());
         } catch (Exception e) {
                e.printStackTrace();
        }
     }

}
class MyThread2 implements Callable<Integer> {

    public Integer call() throws Exception {
       return new Random().nextInt();
    }

 }