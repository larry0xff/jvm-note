package jvm.note_1;

import java.util.ArrayList;

/**
 * 类描述： 模拟堆内存溢出
 * -Xmx20M -Xms20M
 * <p>
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * 	at java.util.Arrays.copyOf(Arrays.java:3210)
 * 	at java.util.Arrays.copyOf(Arrays.java:3181)
 * 	at java.util.ArrayList.grow(ArrayList.java:265)
 * 	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:239)
 * 	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:231)
 * 	at java.util.ArrayList.add(ArrayList.java:462)
 * 	at jvm.note_1.HeapOomTest.main(HeapOomTest.java:18)
 * @author 谢仲东
 * @since 2020/7/10 08:50
 */
public class HeapOomTest {

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        while (true) {
            list.add(new Object());
        }

    }
}
