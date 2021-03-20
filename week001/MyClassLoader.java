
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class MyClassLoader extends ClassLoader {
    public static void main(String[] args) {
        try {
            Object obj = new MyClassLoader().findClass("Hello").newInstance();
            obj.getClass().getMethod("hello").invoke(obj);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{
        byte[] bytes = getFileBytes("./Hello.xlass");
        bytes = processByte(bytes);
        return defineClass(name,bytes,0,bytes.length);
    }

//    有字节（x=255-x）处理后的文件
    private static byte[] processByte(byte[] rawByte){
        byte[] result = new byte[rawByte.length];
        for (int i = 0; i < rawByte.length; i++) {
            result[i] = (byte) (intToByte(255) - rawByte[i]);
        }
        return result;
    }

    public static byte intToByte(int i) {
        return (byte)(i & 0xFF);
    }

    //读取文件到byte[]
    private static byte[] getFileBytes(String file) {
        try {
            File f = new File(file);
            int length = (int) f.length();
            byte[] data = new byte[length];
            int flag = new FileInputStream(f).read(data);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
