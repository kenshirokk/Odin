package com.kenshiro.concurrent.jvm;

/**
 * public class com.kenshiro.concurrent.jvm.Test5
 *   minor version: 0
 *   major version: 55
 *   flags: (0x0021) ACC_PUBLIC, ACC_SUPER
 *   this_class: #6                          // com/kenshiro/concurrent/jvm/Test5
 *   super_class: #7                         // java/lang/Object
 *   interfaces: 0, fields: 0, methods: 2, attributes: 1
 * Constant pool:
 *    #1 = Methodref          #7.#26         // java/lang/Object."<init>":()V
 *    #2 = Class              #27            // java/lang/Short
 *    #3 = Integer            32768
 *    #4 = Fieldref           #28.#29        // java/lang/System.out:Ljava/io/PrintStream;
 *    #5 = Methodref          #30.#31        // java/io/PrintStream.println:(I)V
 *    #6 = Class              #32            // com/kenshiro/concurrent/jvm/Test5
 *    #7 = Class              #33            // java/lang/Object
 *    #8 = Utf8               <init>
 *    #9 = Utf8               ()V
 *   #10 = Utf8               Code
 *   #11 = Utf8               LineNumberTable
 *   #12 = Utf8               LocalVariableTable
 *   #13 = Utf8               this
 *   #14 = Utf8               Lcom/kenshiro/concurrent/jvm/Test5;
 *   #15 = Utf8               main
 *   #16 = Utf8               ([Ljava/lang/String;)V
 *   #17 = Utf8               args
 *   #18 = Utf8               [Ljava/lang/String;
 *   #19 = Utf8               a
 *   #20 = Utf8               I
 *   #21 = Utf8               b
 *   #22 = Utf8               c
 *   #23 = Utf8               MethodParameters
 *   #24 = Utf8               SourceFile
 *   #25 = Utf8               Test5.java
 *   #26 = NameAndType        #8:#9          // "<init>":()V
 *   #27 = Utf8               java/lang/Short
 *   #28 = Class              #34            // java/lang/System
 *   #29 = NameAndType        #35:#36        // out:Ljava/io/PrintStream;
 *   #30 = Class              #37            // java/io/PrintStream
 *   #31 = NameAndType        #38:#39        // println:(I)V
 *   #32 = Utf8               com/kenshiro/concurrent/jvm/Test5
 *   #33 = Utf8               java/lang/Object
 *   #34 = Utf8               java/lang/System
 *   #35 = Utf8               out
 *   #36 = Utf8               Ljava/io/PrintStream;
 *   #37 = Utf8               java/io/PrintStream
 *   #38 = Utf8               println
 *   #39 = Utf8               (I)V
 * {
 *   public com.kenshiro.concurrent.jvm.Test5();
 *     descriptor: ()V
 *     flags: (0x0001) ACC_PUBLIC
 *     Code:
 *       stack=1, locals=1, args_size=1
 *          0: aload_0
 *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *          4: return
 *       LineNumberTable:
 *         line 92: 0
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       5     0  this   Lcom/kenshiro/concurrent/jvm/Test5;
 *
 *   public static void main(java.lang.String[]);
 *     descriptor: ([Ljava/lang/String;)V
 *     flags: (0x0009) ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=2, locals=4, args_size=1
 *          0: bipush        10
 *          2: istore_1
 *          3: ldc           #3                  // int 32768
 *          5: istore_2
 *          6: iload_1
 *          7: iload_2
 *          8: iadd
 *          9: istore_3
 *         10: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *         13: iload_3
 *         14: invokevirtual #5                  // Method java/io/PrintStream.println:(I)V
 *         17: return
 *       LineNumberTable:
 *         line 95: 0
 *         line 96: 3
 *         line 97: 6
 *         line 98: 10
 *         line 99: 17
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0      18     0  args   [Ljava/lang/String;
 *             3      15     1     a   I
 *             6      12     2     b   I
 *            10       8     3     c   I
 *     MethodParameters:
 *       Name                           Flags
 *       args
 * }
 * SourceFile: "Test5.java"
 */
public class Test5 {

    public static void main(String[] args) {
//      0: bipush        10
//      2: istore_1
        int a = 10;

//      3: ldc           #3                  // int 32768
//      5: istore_2
        int b = Short.MAX_VALUE + 1;
        int c = a + b;
        System.out.println(c);
    }

}
