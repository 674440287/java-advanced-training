import java.util.concurrent.CompletableFuture;

public class Method7 {

    public static void main(String[] args){
        String result1 = CompletableFuture.supplyAsync(()-> "Hello ").thenApplyAsync(v -> v + "world").join();
        System.out.println(result1);
    }
}
