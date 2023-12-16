package edu.hw11;

import java.io.FileOutputStream;
import net.bytebuddy.jar.asm.ClassWriter;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class FibonacciGenerator {
    private static final String PATH_TO_CLASS = "src/test/java/edu/hw11/utils/Fibonacci.class";
    public static byte[] generateFibonacciClass() {

        //Старался много не комментировать, но тогда сам начинаю путаться
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        MethodVisitor mv;

        // Создание класса
        cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC, "Fibonacci", null, "java/lang/Object", null);

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
        mv.visitIincInsn( 0, -1);

        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitInsn(Opcodes.ICONST_1);
        mv.visitJumpInsn(Opcodes.IF_ICMPGT, l2);

        // Возврат значения fib
        mv.visitVarInsn(Opcodes.ILOAD, 3);
        mv.visitInsn(Opcodes.IRETURN);

        mv.visitMaxs(0, 0);
        mv.visitEnd();

        cw.visitEnd();

        return cw.toByteArray();
    }

    public static void main(String[] args) throws Exception {
        byte[] bytecode = generateFibonacciClass();

        FileOutputStream fos = new FileOutputStream(PATH_TO_CLASS);
        fos.write(bytecode);
        fos.close();
    }
}
