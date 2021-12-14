package com.kenshiro.concurrent.jvm;

/**
 * public class com.kenshiro.concurrent.jvm.Test7
 *   minor version: 0
 *   major version: 52
 *   flags: (0x0021) ACC_PUBLIC, ACC_SUPER
 *   this_class: #5                          // com/kenshiro/concurrent/jvm/Test7
 *   super_class: #6                         // java/lang/Object
 *   interfaces: 0, fields: 3, methods: 3, attributes: 1
 * Constant pool:
 *    #1 = Methodref          #6.#26         // java/lang/Object."<init>":()V
 *    #2 = Fieldref           #27.#28        // java/lang/System.out:Ljava/io/PrintStream;
 *    #3 = Fieldref           #5.#29         // com/kenshiro/concurrent/jvm/Test7.i:I
 *    #4 = Methodref          #30.#31        // java/io/PrintStream.println:(I)V
 *    #5 = Class              #32            // com/kenshiro/concurrent/jvm/Test7
 *    #6 = Class              #33            // java/lang/Object
 *    #7 = Utf8               a
 *    #8 = Utf8               I
 *    #9 = Utf8               i
 *   #10 = Utf8               b
 *   #11 = Utf8               <init>
 *   #12 = Utf8               ()V
 *   #13 = Utf8               Code
 *   #14 = Utf8               LineNumberTable
 *   #15 = Utf8               LocalVariableTable
 *   #16 = Utf8               this
 *   #17 = Utf8               Lcom/kenshiro/concurrent/jvm/Test7;
 *   #18 = Utf8               main
 *   #19 = Utf8               ([Ljava/lang/String;)V
 *   #20 = Utf8               args
 *   #21 = Utf8               [Ljava/lang/String;
 *   #22 = Utf8               MethodParameters
 *   #23 = Utf8               <clinit>
 *   #24 = Utf8               SourceFile
 *   #25 = Utf8               Test7.java
 *   #26 = NameAndType        #11:#12        // "<init>":()V
 *   #27 = Class              #34            // java/lang/System
 *   #28 = NameAndType        #35:#36        // out:Ljava/io/PrintStream;
 *   #29 = NameAndType        #9:#8          // i:I
 *   #30 = Class              #37            // java/io/PrintStream
 *   #31 = NameAndType        #38:#39        // println:(I)V
 *   #32 = Utf8               com/kenshiro/concurrent/jvm/Test7
 *   #33 = Utf8               java/lang/Object
 *   #34 = Utf8               java/lang/System
 *   #35 = Utf8               out
 *   #36 = Utf8               Ljava/io/PrintStream;
 *   #37 = Utf8               java/io/PrintStream
 *   #38 = Utf8               println
 *   #39 = Utf8               (I)V
 * {
 *   static int a;
 *     descriptor: I
 *     flags: (0x0008) ACC_STATIC
 *
 *   static int i;
 *     descriptor: I
 *     flags: (0x0008) ACC_STATIC
 *
 *   static int b;
 *     descriptor: I
 *     flags: (0x0008) ACC_STATIC
 *
 *   public com.kenshiro.concurrent.jvm.Test7();
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
 *             0       5     0  this   Lcom/kenshiro/concurrent/jvm/Test7;
 *
 *   public static void main(java.lang.String[]);
 *     descriptor: ([Ljava/lang/String;)V
 *     flags: (0x0009) ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=2, locals=1, args_size=1
 *          0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *          3: getstatic     #3                  // Field i:I
 *          6: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
 *          9: return
 *       LineNumberTable:
 *         line 13: 0
 *         line 14: 9
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0      10     0  args   [Ljava/lang/String;
 *     MethodParameters:
 *       Name                           Flags
 *       args
 *
 *   static {};
 *     descriptor: ()V
 *     flags: (0x0008) ACC_STATIC
 *     Code:
 *       stack=1, locals=0, args_size=0
 *          0: bipush        9
 *          2: putstatic     #3                  // Field i:I
 *          5: iconst_5
 *          6: putstatic     #3                  // Field i:I
 *          9: return
 *       LineNumberTable:
 *         line 7: 0
 *         line 10: 5
 * }
 * SourceFile: "Test7.java"
 */
public class Test7 {

    static int a;
    static {
        i = 9;
    }

    static int i = 5;
    static int b;
    public static void main(String[] args) {
        System.out.println(i);
    }
}
