package jvm.note_2;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 类描述：引用类型
 * <p>
 *
 * @author 谢仲东
 * @since 2020/7/14 19:35
 */
public class ReferenceTypeTest {

    public static void main(String[] args) {
        strongReference();
        softReference();
        weakReference();
    }

    /**
     * 强引用
     */
    public static void strongReference() {
        String str = new String("hello");
        System.out.println(str);
        System.gc();
        System.out.println(str);
    }

    /**
     * 软引用
     */
    public static void softReference() {
        SoftReference<String> str = new SoftReference<>(new String("hello1"));
        System.out.println(str.get());
        System.gc();
        System.out.println(str.get());
    }

    /**
     * 弱引用
     */
    public static void weakReference() {
        WeakReference<String> str = new WeakReference<>(new String("hello2"));
        System.out.println(str.get());
        System.gc();
        System.out.println(str.get());
    }

}
