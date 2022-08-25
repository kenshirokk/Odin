package com.kenshiro.concurrent.jvm;

public class Test13 {

    public static void main(String[] args) {
        Object lock = new Object();
        synchronized (lock) {
            System.out.println("ok");
        }
    }
}

/**
 * public class com.kenshiro.concurrent.jvm.Test13
 *   minor version: 0
 *   major version: 52
 *   flags: (0x0021) ACC_PUBLIC, ACC_SUPER
 *   this_class: #6                          // com/kenshiro/concurrent/jvm/Test13
 *   super_class: #2                         // java/lang/Object
 *   interfaces: 0, fields: 0, methods: 2, attributes: 1
 * Constant pool:
 *    #1 = Methodref          #2.#26         // java/lang/Object."<init>":()V
 *    #2 = Class              #27            // java/lang/Object
 *    #3 = Fieldref           #28.#29        // java/lang/System.out:Ljava/io/PrintStream;
 *    #4 = String             #30            // ok
 *    #5 = Methodref          #31.#32        // java/io/PrintStream.println:(Ljava/lang/String;)V
 *    #6 = Class              #33            // com/kenshiro/concurrent/jvm/Test13
 *    #7 = Utf8               <init>
 *    #8 = Utf8               ()V
 *    #9 = Utf8               Code
 *   #10 = Utf8               LineNumberTable
 *   #11 = Utf8               LocalVariableTable
 *   #12 = Utf8               this
 *   #13 = Utf8               Lcom/kenshiro/concurrent/jvm/Test13;
 *   #14 = Utf8               main
 *   #15 = Utf8               ([Ljava/lang/String;)V
 *   #16 = Utf8               args
 *   #17 = Utf8               [Ljava/lang/String;
 *   #18 = Utf8               lock
 *   #19 = Utf8               Ljava/lang/Object;
 *   #20 = Utf8               StackMapTable
 *   #21 = Class              #17            // "[Ljava/lang/String;"
 *   #22 = Class              #34            // java/lang/Throwable
 *   #23 = Utf8               MethodParameters
 *   #24 = Utf8               SourceFile
 *   #25 = Utf8               Test13.java
 *   #26 = NameAndType        #7:#8          // "<init>":()V
 *   #27 = Utf8               java/lang/Object
 *   #28 = Class              #35            // java/lang/System
 *   #29 = NameAndType        #36:#37        // out:Ljava/io/PrintStream;
 *   #30 = Utf8               ok
 *   #31 = Class              #38            // java/io/PrintStream
 *   #32 = NameAndType        #39:#40        // println:(Ljava/lang/String;)V
 *   #33 = Utf8               com/kenshiro/concurrent/jvm/Test13
 *   #34 = Utf8               java/lang/Throwable
 *   #35 = Utf8               java/lang/System
 *   #36 = Utf8               out
 *   #37 = Utf8               Ljava/io/PrintStream;
 *   #38 = Utf8               java/io/PrintStream
 *   #39 = Utf8               println
 *   #40 = Utf8               (Ljava/lang/String;)V
 * {
 *   public com.kenshiro.concurrent.jvm.Test13();
 *     descriptor: ()V
 *     flags: (0x0001) ACC_PUBLIC
 *     Code:
 *       stack=1, locals=1, args_size=1
 *          0: aload_0
 *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *          4: return
 *       LineNumberTable:
 *         line 3: 0
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       5     0  this   Lcom/kenshiro/concurrent/jvm/Test13;
 *
 *   public static void main(java.lang.String[]);
 *     descriptor: ([Ljava/lang/String;)V
 *     flags: (0x0009) ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=2, locals=4, args_size=1
 *          0: new           #2                  // class java/lang/Object
 *          3: dup
 *          4: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *          7: astore_1
 *          8: aload_1
 *          9: dup
 *         10: astore_2
 *         11: monitorenter
 *         12: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *         15: ldc           #4                  // String ok
 *         17: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 *         20: aload_2
 *         21: monitorexit
 *         22: goto          30
 *         25: astore_3
 *         26: aload_2
 *         27: monitorexit
 *         28: aload_3
 *         29: athrow
 *         30: return
 *       Exception table:
 *          from    to  target type
 *             12    22    25   any
 *             25    28    25   any
 *       LineNumberTable:
 *         line 6: 0
 *         line 7: 8
 *         line 8: 12
 *         line 9: 20
 *         line 10: 30
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0      31     0  args   [Ljava/lang/String;
 *             8      23     1  lock   Ljava/lang/Object;
 *       StackMapTable: number_of_entries = 2
 *         frame_type = 255 // full_frame //
 *        offset_delta=25
 *                locals=[class "[Ljava/lang/String;",
 *
 *       class java/lang/Object,
 *
 *       class java/lang/Object]
 *               stack=[
 *
 *       class java/lang/Throwable]
 *               frame_type=250 // chop //
 *               offset_delta=4
 *               MethodParameters:
 *               Name Flags
 *               args
 *               }
 *               SourceFile:"Test13.java"
 */