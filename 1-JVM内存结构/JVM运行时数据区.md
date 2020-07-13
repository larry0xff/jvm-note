# JVM运行时数据区

Java运行时数据区分为:

1. 程序计数器
2. Java虚拟机栈
3. 本地方法栈
4. Java堆
5. 方法区
6. 运行时常量池
7. 直接内存
 
## 🔘程序计数器

> “程序计数器（Program Counter Register）是一块较小的内存空间，它可以看作是当前线程所执行的字节码的行号指示器。在Java虚拟机的概念模型里 [1] ，字节码解释器工作时就是通过改变这个计数器的值来选取下一条需要执行的字节码指令，它是程序控制流的指示器，分支、循环、跳转、异常处理、线程恢复等基础功能都需要依赖这个计数器来完成”


## 🔘Java虚拟机栈

> “与程序计数器一样，Java虚拟机栈（Java Virtual Machine Stack）也是线程私有的，它的生命周期与线程相同。虚拟机栈描述的是Java方法执行的线程内存模型：每个方法被执行的时候，Java虚拟机都会同步创建一个栈帧 [1] （Stack Frame）用于存储局部变量表、操作数栈、动态连接、方法出口等信息。每一个方法被调用直至执行完毕的过程，就对应着一个栈帧在虚拟机栈中从入栈到出栈的过程。”


## 🔘本地方法栈

> “本地方法栈（Native Method Stacks）与虚拟机栈所发挥的作用是非常相似的，其区别只是虚拟机栈为虚拟机执行Java方法（也就是字节码）服务，而本地方法栈则是为虚拟机使用到的本地（Native）方法服务。”


## 🔘Java堆

> “Java堆（Java Heap）是虚拟机所管理的内存中最大的一块。Java堆是被所有线程共享的一块内存区域，在虚拟机启动时创建。此内存区域的唯一目的就是存放对象实例”

## 🔘方法区

> “方法区（Method Area）与Java堆一样，是各个线程共享的内存区域，它用于存储已被虚拟机加载的类型信息、常量、静态变量、即时编译器编译后的代码缓存等数据。虽然《Java虚拟机规范》中把方法区描述为堆的一个逻辑部分，但是它却有一个别名叫作“非堆”（Non-Heap），目的是与Java堆区分开来。”


## 🔘运行时常量池

> “运行时常量池（Runtime Constant Pool）是方法区的一部分。Class文件中除了有类的版本、字段、方法、接口等描述信息外，还有一项信息是常量池表（Constant Pool Table），用于存放编译期生成的各种字面量与符号引用，这部分内容将在类加载后存放到方法区的运行时常量池中。”

## 🔘直接内存

> “直接内存（Direct Memory）并不是虚拟机运行时数据区的一部分，也不是《Java虚拟机规范》中定义的内存区域。但是这部分内存也被频繁地使用，而且也可能导致OutOfMemoryError异常出现，所以我们放到这里一起讲解。”

> NIO（New input/output）是JDK1.4中新加入的类，引入了一种基于通道（channel）和缓冲区（buffer）的I/O方式，它可以使用Native函数库直接分配堆外内存，然后通过堆上的DirectByteBuffer对象对这块内存进行引用和操作。直接内存（Direct Memory）的容量大小可通过-XX：MaxDirectMemorySize参数来指定，如果不去指定，则默认与Java堆最大值（由-Xmx指定）一致


## 内存溢出类型

- ❗️Java堆溢出

随着对象创建越来越多，Java堆被塞满，就会出现堆内存溢出异常：
```
java.lang.OutOfMemoryError: Java heap space
```

- ❗️虚拟机栈和本地方法栈溢出

如果线程请求的栈深度大于虚拟机指定的深度，则会抛出占内存溢出异常，在代码上的表现就是方法调用链路过长或者局部变量太多：
```
Exception in thread "main" java.lang.StackOverflowError
```

- ❗️方法区和运行时常量池溢出

在JDK 6中，永久代存放在方法区中，所以如果不断往方法区中填充数据，会导致程序报永久代空间不足异常：
```
Exception in thread "main" java.lang.OutOfMemoryError: PermGen space
```
但是从JDK 7开始，永久代已被废弃，取而代之的是堆中的“元空间”，所以永久数据的不断上涨，导致的是堆内存溢出

- ❗️本机直接内存溢出

NIO会通过Native函数库直接分配堆外内存，如果分配的内存超出了指定上限，会导致堆外内存溢出

```
Exception in thread "main" java.lang.OutOfMemoryError
    at sun.misc.Unsafe.allocateMemory(Native Method)
    at org.fenixsoft.oom.DMOOM.main(DMOOM.java:20)

```