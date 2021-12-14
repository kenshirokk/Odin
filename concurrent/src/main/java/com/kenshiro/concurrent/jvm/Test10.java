package com.kenshiro.concurrent.jvm;

public class Test10 {

    public static void main(String[] args) {

        int i = 0;
        try {
            i = 10;
        } catch (Exception e) {
            i = 20;
        }
    }
}

/**
 * public class com.kenshiro.concurrent.jvm.Test10
 *   minor version: 0
 *   major version: 52
 *   flags: (0x0021) ACC_PUBLIC, ACC_SUPER
 *   this_class: #3                          // com/kenshiro/concurrent/jvm/Test10
 *   super_class: #4                         // java/lang/Object
 *   interfaces: 0, fields: 0, methods: 2, attributes: 1
 * Constant pool:
 *    #1 = Methodref          #4.#25         // java/lang/Object."<init>":()V
 *    #2 = Class              #26            // java/lang/Exception
 *    #3 = Class              #27            // com/kenshiro/concurrent/jvm/Test10
 *    #4 = Class              #28            // java/lang/Object
 *    #5 = Utf8               <init>
 *    #6 = Utf8               ()V
 *    #7 = Utf8               Code
 *    #8 = Utf8               LineNumberTable
 *    #9 = Utf8               LocalVariableTable
 *   #10 = Utf8               this
 *   #11 = Utf8               Lcom/kenshiro/concurrent/jvm/Test10;
 *   #12 = Utf8               main
 *   #13 = Utf8               ([Ljava/lang/String;)V
 *   #14 = Utf8               e
 *   #15 = Utf8               Ljava/lang/Exception;
 *   #16 = Utf8               args
 *   #17 = Utf8               [Ljava/lang/String;
 *   #18 = Utf8               i
 *   #19 = Utf8               I
 *   #20 = Utf8               StackMapTable
 *   #21 = Class              #17            // "[Ljava/lang/String;"
 *   #22 = Utf8               MethodParameters
 *   #23 = Utf8               SourceFile
 *   #24 = Utf8               Test10.java
 *   #25 = NameAndType        #5:#6          // "<init>":()V
 *   #26 = Utf8               java/lang/Exception
 *   #27 = Utf8               com/kenshiro/concurrent/jvm/Test10
 *   #28 = Utf8               java/lang/Object
 * {
 *   public com.kenshiro.concurrent.jvm.Test10();
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
 *             0       5     0  this   Lcom/kenshiro/concurrent/jvm/Test10;
 *
 *   public static void main(java.lang.String[]);
 *     descriptor: ([Ljava/lang/String;)V
 *     flags: (0x0009) ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=1, locals=3, args_size=1
 *          0: iconst_0
 *          1: istore_1
 *          2: bipush        10
 *          4: istore_1
 *          5: goto          12
 *          8: astore_2
 *          9: bipush        20
 *         11: istore_1
 *         12: return
 *       Exception table:
 *          from    to  target type
 *              2     5     8   Class java/lang/Exception
 *       LineNumberTable:
 *         line 7: 0
 *         line 9: 2
 *         line 12: 5
 *         line 10: 8
 *         line 11: 9
 *         line 13: 12
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             9       3     2     e   Ljava/lang/Exception;
 *             0      13     0  args   [Ljava/lang/String;
 *             2      11     1     i   I
 *       StackMapTable: number_of_entries = 2
 *         frame_type = 255 // full_frame //
 *          offset_delta=8
 *          locals=[class "[Ljava/lang/String;",int]
 *          stack=[class java/lang/Exception]
 *          frame_type=3 // same //
 *     MethodParameters:
 *          Name Flags
 *          args
 *           }
        *SourceFile:"Test10.java"
 */