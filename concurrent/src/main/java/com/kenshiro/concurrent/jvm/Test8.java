package com.kenshiro.concurrent.jvm;

/**
 * public class com.kenshiro.concurrent.jvm.Test8
 *   minor version: 0
 *   major version: 52
 *   flags: (0x0021) ACC_PUBLIC, ACC_SUPER
 *   this_class: #1                          // com/kenshiro/concurrent/jvm/Test8
 *   super_class: #8                         // java/lang/Object
 *   interfaces: 0, fields: 0, methods: 6, attributes: 1
 * Constant pool:
 *    #1 = Class              #28            // com/kenshiro/concurrent/jvm/Test8
 *    #2 = Methodref          #1.#29         // com/kenshiro/concurrent/jvm/Test8."<init>":()V
 *    #3 = Methodref          #1.#30         // com/kenshiro/concurrent/jvm/Test8.test1:()V
 *    #4 = Methodref          #1.#31         // com/kenshiro/concurrent/jvm/Test8.test2:()V
 *    #5 = Methodref          #1.#32         // com/kenshiro/concurrent/jvm/Test8.test3:()V
 *    #6 = Methodref          #1.#33         // com/kenshiro/concurrent/jvm/Test8.test4:()V
 *    #7 = Methodref          #8.#29         // java/lang/Object."<init>":()V
 *    #8 = Class              #34            // java/lang/Object
 *    #9 = Utf8               main
 *   #10 = Utf8               ([Ljava/lang/String;)V
 *   #11 = Utf8               Code
 *   #12 = Utf8               LineNumberTable
 *   #13 = Utf8               LocalVariableTable
 *   #14 = Utf8               args
 *   #15 = Utf8               [Ljava/lang/String;
 *   #16 = Utf8               t
 *   #17 = Utf8               Lcom/kenshiro/concurrent/jvm/Test8;
 *   #18 = Utf8               MethodParameters
 *   #19 = Utf8               <init>
 *   #20 = Utf8               ()V
 *   #21 = Utf8               this
 *   #22 = Utf8               test1
 *   #23 = Utf8               test2
 *   #24 = Utf8               test3
 *   #25 = Utf8               test4
 *   #26 = Utf8               SourceFile
 *   #27 = Utf8               Test8.java
 *   #28 = Utf8               com/kenshiro/concurrent/jvm/Test8
 *   #29 = NameAndType        #19:#20        // "<init>":()V
 *   #30 = NameAndType        #22:#20        // test1:()V
 *   #31 = NameAndType        #23:#20        // test2:()V
 *   #32 = NameAndType        #24:#20        // test3:()V
 *   #33 = NameAndType        #25:#20        // test4:()V
 *   #34 = Utf8               java/lang/Object
 * {
 *   public static void main(java.lang.String[]);
 *     descriptor: ([Ljava/lang/String;)V
 *     flags: (0x0009) ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=2, locals=2, args_size=1
 *          0: new           #1                  // class com/kenshiro/concurrent/jvm/Test8
 *          3: dup
 *          4: invokespecial #2                  // Method "<init>":()V
 *          7: astore_1
 *          8: aload_1
 *          9: invokespecial #3                  // Method test1:()V
 *         12: aload_1
 *         13: invokespecial #4                  // Method test2:()V
 *         16: aload_1
 *         17: invokevirtual #5                  // Method test3:()V
 *         20: aload_1
 *         21: pop
 *         22: invokestatic  #6                  // Method test4:()V
 *         25: invokestatic  #6                  // Method test4:()V
 *         28: return
 *       LineNumberTable:
 *         line 123: 0
 *         line 124: 8
 *         line 125: 12
 *         line 126: 16
 *         line 127: 20
 *         line 129: 25
 *         line 130: 28
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0      29     0  args   [Ljava/lang/String;
 *             8      21     1     t   Lcom/kenshiro/concurrent/jvm/Test8;
 *     MethodParameters:
 *       Name                           Flags
 *       args
 *
 *   public com.kenshiro.concurrent.jvm.Test8();
 *     descriptor: ()V
 *     flags: (0x0001) ACC_PUBLIC
 *     Code:
 *       stack=1, locals=1, args_size=1
 *          0: aload_0
 *          1: invokespecial #7                  // Method java/lang/Object."<init>":()V
 *          4: return
 *       LineNumberTable:
 *         line 132: 0
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       5     0  this   Lcom/kenshiro/concurrent/jvm/Test8;
 *
 *   public void test3();
 *     descriptor: ()V
 *     flags: (0x0001) ACC_PUBLIC
 *     Code:
 *       stack=0, locals=1, args_size=1
 *          0: return
 *       LineNumberTable:
 *         line 136: 0
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       1     0  this   Lcom/kenshiro/concurrent/jvm/Test8;
 *
 *   public static void test4();
 *     descriptor: ()V
 *     flags: (0x0009) ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=0, locals=0, args_size=0
 *          0: return
 *       LineNumberTable:
 *         line 137: 0
 * }
 * SourceFile: "Test8.java"
 */
public class Test8 {

    public static void main(String[] args) {
        Test8 t = new Test8();
        t.test1();
        t.test2();
        t.test3();
        t.test4();

        Test8.test4();
    }

    public Test8() {}

    private void test1() {}
    private final void test2(){}
    public void test3(){}
    public static void test4(){}
}
