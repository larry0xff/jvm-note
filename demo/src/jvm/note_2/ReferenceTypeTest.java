package jvm.note_2;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 类描述：引用类型
 * <p>
 *
 * @author 谢仲东
 * @since 2020/7/14 16:35
 */
public class ReferenceTypeTest {

    public static void main(String[] args) {
        strongReference();
        softReference();
        weakReference();
    }

    public static void strongReference() {
        String str = new String("hello");
        System.out.println(str);
        System.gc();
        System.out.println(str);
    }

    public static void softReference() {
        SoftReference<String> str = new SoftReference<>("hello1");
        System.out.println(str.get());
        System.gc();
        System.out.println(str.get());
    }

    public static void weakReference() {
        WeakReference<String> str = new WeakReference<>("hello2");
        System.out.println(str.get());
        System.gc();
        System.out.println(str.get());
    }

}
