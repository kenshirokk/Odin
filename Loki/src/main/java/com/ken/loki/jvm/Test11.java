package com.kenshiro.concurrent.jvm;

public class Test11 {

    public static void main(String[] args) {
        int test = test();
        System.out.println(test);
    }

    public static int test() {
        try {
            return 10;
        } finally {
            return 20;
        }
    }
}

/**
 || public class com.kenshiro.concurrent.jvm.Test11
 minor version: 0
 major version: 52
 flags: (0x0021) ACC_PUBLIC, ACC_SUPER
 this_class: #5                          // com/kenshiro/concurrent/jvm/Test11
 super_class: #6                         // java/lang/Object
 interfaces: 0, fields: 0, methods: 3, attributes: 1
 Constant pool:
 #1 = Methodref          #6.#26         // java/lang/Object."<init>":()V
 #2 = Methodref          #5.#27         // com/kenshiro/concurrent/jvm/Test11.test:()I
 #3 = Fieldref           #28.#29        // java/lang/System.out:Ljava/io/PrintStream;
 #4 = Methodref          #30.#31        // java/io/PrintStream.println:(I)V
 #5 = Class              #32            // com/kenshiro/concurrent/jvm/Test11
 #6 = Class              #33            // java/lang/Object
 #7 = Utf8               <init>
 #8 = Utf8               ()V
 #9 = Utf8               Code
 #10 = Utf8               LineNumberTable
 #11 = Utf8               LocalVariableTable
 #12 = Utf8               this
 #13 = Utf8               Lcom/kenshiro/concurrent/jvm/Test11;
 #14 = Utf8               main
 #15 = Utf8               ([Ljava/lang/String;)V
 #16 = Utf8               args
 #17 = Utf8               [Ljava/lang/String;
 #18 = Utf8               test
 #19 = Utf8               I
 #20 = Utf8               MethodParameters
 #21 = Utf8               ()I
 #22 = Utf8               StackMapTable
 #23 = Class              #34            // java/lang/Throwable
 #24 = Utf8               SourceFile
 #25 = Utf8               Test11.java
 #26 = NameAndType        #7:#8          // "<init>":()V
 #27 = NameAndType        #18:#21        // test:()I
 #28 = Class              #35            // java/lang/System
 #29 = NameAndType        #36:#37        // out:Ljava/io/PrintStream;
 #30 = Class              #38            // java/io/PrintStream
 #31 = NameAndType        #39:#40        // println:(I)V
 #32 = Utf8               com/kenshiro/concurrent/jvm/Test11
 #33 = Utf8               java/lang/Object
 #34 = Utf8               java/lang/Throwable
 #35 = Utf8               java/lang/System
 #36 = Utf8               out
 #37 = Utf8               Ljava/io/PrintStream;
 #38 = Utf8               java/io/PrintStream
 #39 = Utf8               println
 #40 = Utf8               (I)V
 {
 public com.kenshiro.concurrent.jvm.Test11();
 descriptor: ()V
 flags: (0x0001) ACC_PUBLIC
 Code:
 stack=1, locals=1, args_size=1
 0: aload_0
 1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 4: return
 LineNumberTable:
 line 3: 0
 LocalVariableTable:
 Start  Length  Slot  Name   Signature
 0       5     0  this   Lcom/kenshiro/concurrent/jvm/Test11;

 public static void main(java.lang.String[]);
 descriptor: ([Ljava/lang/String;)V
 flags: (0x0009) ACC_PUBLIC, ACC_STATIC
 Code:
 stack=2, locals=2, args_size=1
 0: invokestatic  #2                  // Method test:()I
 3: istore_1
 4: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
 7: iload_1
 8: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
 11: return
 LineNumberTable:
 line 6: 0
 line 7: 4
 line 8: 11
 LocalVariableTable:
 Start  Length  Slot  Name   Signature
 0      12     0  args   [Ljava/lang/String;
 4       8     1  test   I
 MethodParameters:
 Name                           Flags
 args

 public static int test();
 descriptor: ()I
 flags: (0x0009) ACC_PUBLIC, ACC_STATIC
 Code:
 stack=1, locals=2, args_size=0
 0: bipush        10
 2: istore_0
 3: bipush        20
 5: ireturn
 6: astore_1
 7: bipush        20
 9: ireturn
 Exception table:
 from    to  target type
 0     3     6   any
 LineNumberTable:
 line 12: 0
 line 14: 3
 StackMapTable: number_of_entries = 1
 frame_type = 70 // same_locals_1_stack_item //
          stack = [ class java/lang/Throwable ]
        }
        SourceFile: "Test11.java"
 */