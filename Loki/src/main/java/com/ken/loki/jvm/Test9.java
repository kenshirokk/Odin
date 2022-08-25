package com.kenshiro.concurrent.jvm;

import java.io.IOException;

public class Test9 {


    public static void main(String[] args) throws IOException {
        test(new Dog());
        test(new Cat());
        System.in.read();
    }

    public static void test(Animal animal) {
        animal.eat();
        System.out.println(animal);
    }
}

abstract class Animal {
    public abstract void eat();

    @Override
    public String toString() {
        return "我是" + this.getClass().getSimpleName();
    }
}

class Dog extends Animal {
    @Override
    public void eat() {
        System.out.println("狗吃屎");
    }
}

class Cat extends Animal {

    @Override
    public void eat() {
        System.out.println("猫喝尿");
    }
}

/**
 * public class com.kenshiro.concurrent.jvm.Test9
 *   minor version: 0
 *   major version: 52
 *   flags: (0x0021) ACC_PUBLIC, ACC_SUPER
 *   this_class: #12                         // com/kenshiro/concurrent/jvm/Test9
 *   super_class: #13                        // java/lang/Object
 *   interfaces: 0, fields: 0, methods: 3, attributes: 1
 * Constant pool:
 *    #1 = Methodref          #13.#34        // java/lang/Object."<init>":()V
 *    #2 = Class              #35            // com/kenshiro/concurrent/jvm/Dog
 *    #3 = Methodref          #2.#34         // com/kenshiro/concurrent/jvm/Dog."<init>":()V
 *    #4 = Methodref          #12.#36        // com/kenshiro/concurrent/jvm/Test9.test:
 *    (Lcom/kenshiro/concurrent/jvm/Animal;)V
 *    #5 = Class              #37            // com/kenshiro/concurrent/jvm/Cat
 *    #6 = Methodref          #5.#34         // com/kenshiro/concurrent/jvm/Cat."<init>":()V
 *    #7 = Fieldref           #38.#39        // java/lang/System.in:Ljava/io/InputStream;
 *    #8 = Methodref          #40.#41        // java/io/InputStream.read:()I
 *    #9 = Methodref          #42.#43        // com/kenshiro/concurrent/jvm/Animal.eat:()V
 *   #10 = Fieldref           #38.#44        // java/lang/System.out:Ljava/io/PrintStream;
 *   #11 = Methodref          #45.#46        // java/io/PrintStream.println:(Ljava/lang/Object;)V
 *   #12 = Class              #47            // com/kenshiro/concurrent/jvm/Test9
 *   #13 = Class              #48            // java/lang/Object
 *   #14 = Utf8               <init>
 *   #15 = Utf8               ()V
 *   #16 = Utf8               Code
 *   #17 = Utf8               LineNumberTable
 *   #18 = Utf8               LocalVariableTable
 *   #19 = Utf8               this
 *   #20 = Utf8               Lcom/kenshiro/concurrent/jvm/Test9;
 *   #21 = Utf8               main
 *   #22 = Utf8               ([Ljava/lang/String;)V
 *   #23 = Utf8               args
 *   #24 = Utf8               [Ljava/lang/String;
 *   #25 = Utf8               Exceptions
 *   #26 = Class              #49            // java/io/IOException
 *   #27 = Utf8               MethodParameters
 *   #28 = Utf8               test
 *   #29 = Utf8               (Lcom/kenshiro/concurrent/jvm/Animal;)V
 *   #30 = Utf8               animal
 *   #31 = Utf8               Lcom/kenshiro/concurrent/jvm/Animal;
 *   #32 = Utf8               SourceFile
 *   #33 = Utf8               Test9.java
 *   #34 = NameAndType        #14:#15        // "<init>":()V
 *   #35 = Utf8               com/kenshiro/concurrent/jvm/Dog
 *   #36 = NameAndType        #28:#29        // test:(Lcom/kenshiro/concurrent/jvm/Animal;)V
 *   #37 = Utf8               com/kenshiro/concurrent/jvm/Cat
 *   #38 = Class              #50            // java/lang/System
 *   #39 = NameAndType        #51:#52        // in:Ljava/io/InputStream;
 *   #40 = Class              #53            // java/io/InputStream
 *   #41 = NameAndType        #54:#55        // read:()I
 *   #42 = Class              #56            // com/kenshiro/concurrent/jvm/Animal
 *   #43 = NameAndType        #57:#15        // eat:()V
 *   #44 = NameAndType        #58:#59        // out:Ljava/io/PrintStream;
 *   #45 = Class              #60            // java/io/PrintStream
 *   #46 = NameAndType        #61:#62        // println:(Ljava/lang/Object;)V
 *   #47 = Utf8               com/kenshiro/concurrent/jvm/Test9
 *   #48 = Utf8               java/lang/Object
 *   #49 = Utf8               java/io/IOException
 *   #50 = Utf8               java/lang/System
 *   #51 = Utf8               in
 *   #52 = Utf8               Ljava/io/InputStream;
 *   #53 = Utf8               java/io/InputStream
 *   #54 = Utf8               read
 *   #55 = Utf8               ()I
 *   #56 = Utf8               com/kenshiro/concurrent/jvm/Animal
 *   #57 = Utf8               eat
 *   #58 = Utf8               out
 *   #59 = Utf8               Ljava/io/PrintStream;
 *   #60 = Utf8               java/io/PrintStream
 *   #61 = Utf8               println
 *   #62 = Utf8               (Ljava/lang/Object;)V
 * {
 *   public com.kenshiro.concurrent.jvm.Test9();
 *     descriptor: ()V
 *     flags: (0x0001) ACC_PUBLIC
 *     Code:
 *       stack=1, locals=1, args_size=1
 *          0: aload_0
 *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *          4: return
 *       LineNumberTable:
 *         line 5: 0
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       5     0  this   Lcom/kenshiro/concurrent/jvm/Test9;
 *
 *   public static void main(java.lang.String[]) throws java.io.IOException;
 *     descriptor: ([Ljava/lang/String;)V
 *     flags: (0x0009) ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=2, locals=1, args_size=1
 *          0: new           #2                  // class com/kenshiro/concurrent/jvm/Dog
 *          3: dup
 *          4: invokespecial #3                  // Method com/kenshiro/concurrent/jvm/Dog."<init>":()V
 *          7: invokestatic  #4                  // Method test:(Lcom/kenshiro/concurrent/jvm/Animal;)V
 *         10: new           #5                  // class com/kenshiro/concurrent/jvm/Cat
 *         13: dup
 *         14: invokespecial #6                  // Method com/kenshiro/concurrent/jvm/Cat."<init>":()V
 *         17: invokestatic  #4                  // Method test:(Lcom/kenshiro/concurrent/jvm/Animal;)V
 *         20: getstatic     #7                  // Field java/lang/System.in:Ljava/io/InputStream;
 *         23: invokevirtual #8                  // Method java/io/InputStream.read:()I
 *         26: pop
 *         27: return
 *       LineNumberTable:
 *         line 9: 0
 *         line 10: 10
 *         line 11: 20
 *         line 12: 27
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0      28     0  args   [Ljava/lang/String;
 *     Exceptions:
 *       throws java.io.IOException
 *     MethodParameters:
 *       Name                           Flags
 *       args
 *
 *   public static void test(com.kenshiro.concurrent.jvm.Animal);
 *     descriptor: (Lcom/kenshiro/concurrent/jvm/Animal;)V
 *     flags: (0x0009) ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=2, locals=1, args_size=1
 *          0: aload_0
 *          1: invokevirtual #9                  // Method com/kenshiro/concurrent/jvm/Animal.eat:()V
 *          4: getstatic     #10                 // Field java/lang/System.out:Ljava/io/PrintStream;
 *          7: aload_0
 *          8: invokevirtual #11                 // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
 *         11: return
 *       LineNumberTable:
 *         line 15: 0
 *         line 16: 4
 *         line 17: 11
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0      12     0 animal   Lcom/kenshiro/concurrent/jvm/Animal;
 *     MethodParameters:
 *       Name                           Flags
 *       animal
 * }
 * SourceFile: "Test9.java"
 */