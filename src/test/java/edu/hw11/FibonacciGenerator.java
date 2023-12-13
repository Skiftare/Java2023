package edu.hw11;

import java.io.FileOutputStream;
import net.bytebuddy.jar.asm.ClassWriter;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class FibonacciGenerator {


    public static byte[] generateFibonacciClass(int i) {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        MethodVisitor mv;

        // Создание класса
        cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC, "Fibonacci", null, "java/lang/Object", null);

        // Создание метода main
       // mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
       // mv.visitCode();

        // Загрузка значения i в стек
        //mv.visitIntInsn(Opcodes.BIPUSH, i);

        // Вызов функции fibonacci
       // mv.visitMethodInsn(Opcodes.INVOKESTATIC, "Fibonacci", "fibonacci", "(I)I", false);

        // Вывод результата
        //mv.visitVarInsn(Opcodes.ISTORE, 1);
        //mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
      //  mv.visitVarInsn(Opcodes.ILOAD, 1);
      //  mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);

       //mv.visitInsn(Opcodes.RETURN);
       // mv.visitMaxs(0, 0); // Указание максимального размера стека и количества локальных переменных
       // mv.visitEnd();

        // Создание метода fibonacci
        mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "fibonacci", "(I)I", null, null);
        mv.visitCode();

        // Загрузка значения n в стек
        mv.visitVarInsn(Opcodes.ILOAD, 0);

        // Проверка условия if (n <= 1)
        mv.visitInsn(Opcodes.ICONST_1);
        Label l1 = new Label();
        mv.visitJumpInsn(Opcodes.IF_ICMPGT, l1);

        // Возврат значения n
        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitInsn(Opcodes.IRETURN);

        mv.visitLabel(l1);

        // Инициализация переменных fib1 = 0, fib2 = 1
        mv.visitInsn(Opcodes.ICONST_0);
        mv.visitVarInsn(Opcodes.ISTORE, 1);
        mv.visitInsn(Opcodes.ICONST_1);
        mv.visitVarInsn(Opcodes.ISTORE, 2);

        // Цикл для вычисления чисел Фибоначчи
        Label l2 = new Label();
        mv.visitLabel(l2);
        //mv.visitVarInsn(Opcodes.ILOAD, 0);
        //mv.visitInsn(Opcodes.ICONST_2);
        //mv.visitJumpInsn(Opcodes.IF_ICMPGT, l2);
        Label l3 = new Label();
        mv.visitLabel(l3);

        // fib = fib1 + fib2
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitInsn(Opcodes.IADD);
        mv.visitVarInsn(Opcodes.ISTORE, 3);

        // fib1 = fib2
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitVarInsn(Opcodes.ISTORE, 1);

        // fib2 = fib
        mv.visitVarInsn(Opcodes.ILOAD, 3);
        mv.visitVarInsn(Opcodes.ISTORE, 2);

        // Уменьшение счетчика цикла
        /*mv.visitInsn(Opcodes.ICONST_1);
        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitInsn(Opcodes.ISUB);
        mv.visitVarInsn(Opcodes.ISTORE, 0);*/
        mv.visitIincInsn( 0, -1);

        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitInsn(Opcodes.ICONST_1);
        mv.visitJumpInsn(Opcodes.IF_ICMPGT, l2);

        // Возврат значения fib
        mv.visitVarInsn(Opcodes.ILOAD, 3);
        mv.visitInsn(Opcodes.IRETURN);

        mv.visitMaxs(0, 0); // Указание максимального размера стека и количества локальных переменных
        mv.visitEnd();

        cw.visitEnd();

        return cw.toByteArray();
    }

    public static void main(String[] args) throws Exception {
        byte[] bytecode = generateFibonacciClass(8);
        // Запись байткода в файл Fibonacci.class
        FileOutputStream fos = new FileOutputStream("src/test/java/edu/hw11/utils/Fibonacci.class");
        fos.write(bytecode);
        fos.close();
    }
}
