package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.jetbrains.annotations.NotNull;

public class Task3 implements ByteCodeAppender {
    private static final int OPERAND_STACK_SIZE = 4;
    private static final int LOCAL_VARIABLE_SIZE = 8;

    @Override
    public @NotNull Size apply(
        @NotNull MethodVisitor methodVisitor,
        Implementation.@NotNull Context context,
        @NotNull MethodDescription methodDescription
    ) {
        generateFibMethod(methodVisitor);
        return new Size(OPERAND_STACK_SIZE, LOCAL_VARIABLE_SIZE);
    }

    @SuppressWarnings("MagicNumber")
    private static void generateFibMethod(MethodVisitor methodVisitor) {
        Label start = new Label();
        Label loop = new Label();
        Label end = new Label();

        methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
        methodVisitor.visitJumpInsn(Opcodes.IFNE, start);
        methodVisitor.visitInsn(Opcodes.LCONST_0);
        methodVisitor.visitInsn(Opcodes.LRETURN);

        methodVisitor.visitFrame(
            Opcodes.F_FULL,
            1,
            new Object[] {
                Opcodes.INTEGER
            },
            0,
            new Object[] {}
        );

        methodVisitor.visitLabel(start);
        methodVisitor.visitInsn(Opcodes.LCONST_0);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, 1);
        methodVisitor.visitInsn(Opcodes.LCONST_1);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, 3);
        methodVisitor.visitInsn(Opcodes.ICONST_0);
        methodVisitor.visitVarInsn(Opcodes.ISTORE, 5);

        methodVisitor.visitFrame(
            Opcodes.F_FULL,
            4,
            new Object[] {
                Opcodes.INTEGER,
                Opcodes.LONG,
                Opcodes.LONG,
                Opcodes.INTEGER
            },
            0,
            new Object[] {}
        );

        methodVisitor.visitLabel(loop);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 5);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGE, end);

        methodVisitor.visitVarInsn(Opcodes.LLOAD, 3);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, 6);
        methodVisitor.visitVarInsn(Opcodes.LLOAD, 1);
        methodVisitor.visitVarInsn(Opcodes.LLOAD, 3);
        methodVisitor.visitInsn(Opcodes.LADD);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, 3);
        methodVisitor.visitVarInsn(Opcodes.LLOAD, 6);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, 1);

        methodVisitor.visitIincInsn(5, 1);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, loop);

        methodVisitor.visitFrame(
            Opcodes.F_FULL,
            4,
            new Object[] {
                Opcodes.INTEGER,
                Opcodes.LONG,
                Opcodes.LONG,
                Opcodes.INTEGER
            },
            0,
            new Object[] {}
        );

        methodVisitor.visitLabel(end);
        methodVisitor.visitVarInsn(Opcodes.LLOAD, 1);
        methodVisitor.visitInsn(Opcodes.LRETURN);

        methodVisitor.visitEnd();
    }

    public static Class<?> createFibonacciClass() {
        return new ByteBuddy()
            .subclass(Object.class)
            .name("Fibonacci")
            .defineMethod("fib", long.class, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
            .withParameter(int.class)
            .intercept(new Implementation.Simple(new Task3()))
            .make()
            .load(Task3.class.getClassLoader())
            .getLoaded();
    }
}
