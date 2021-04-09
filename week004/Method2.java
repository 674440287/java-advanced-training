import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class Method2 {

    public static void main(String[] args) throws Exception {
        Callable<Integer> callable = ()->{
            return 1;
        };

        int result = callable.call();

        System.out.println(result);
    }
}
