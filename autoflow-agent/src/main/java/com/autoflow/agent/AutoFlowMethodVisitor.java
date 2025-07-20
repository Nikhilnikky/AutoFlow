package com.autoflow.agent;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.LocalVariableNode;

import java.util.ArrayList;
import java.util.List;

public class AutoFlowMethodVisitor extends MethodVisitor {

    private final String methodName;
    private final String className;
    private final List<Label> loopLabels = new ArrayList<>();

    public AutoFlowMethodVisitor(int api, MethodVisitor methodVisitor, String className, String methodName) {
        super(api, methodVisitor);
        this.className = className;
        this.methodName = methodName;
    }

    @Override
    public void visitCode() {
        super.visitCode();
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("[AutoFlow] Entering method: " + className + "." + methodName);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    }

    @Override
    public void visitInsn(int opcode) {
        // Log return values
        if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN)) {
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("[AutoFlow] Exiting method: " + className + "." + methodName);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }
        super.visitInsn(opcode);
    }

    @Override
    public void visitVarInsn(int opcode, int varIndex) {
        // Log variable loads and stores
        String action = (opcode >= Opcodes.ISTORE && opcode <= Opcodes.ASTORE) ? "Stored" :
                (opcode >= Opcodes.ILOAD && opcode <= Opcodes.ALOAD) ? "Loaded" : null;
        if (action != null) {
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("[AutoFlow] Variable " + action + ": index=" + varIndex);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }
        super.visitVarInsn(opcode, varIndex);
    }

    @Override
    public void visitJumpInsn(int opcode, Label label) {
        if (opcode == Opcodes.IFEQ || opcode == Opcodes.IFNE || opcode == Opcodes.IFLT ||
                opcode == Opcodes.IFGE || opcode == Opcodes.IFGT || opcode == Opcodes.IFLE ||
                opcode == Opcodes.IF_ICMPEQ || opcode == Opcodes.IF_ICMPNE ||
                opcode == Opcodes.IF_ICMPLT || opcode == Opcodes.IF_ICMPGE ||
                opcode == Opcodes.IF_ICMPGT || opcode == Opcodes.IF_ICMPLE ||
                opcode == Opcodes.IF_ACMPEQ || opcode == Opcodes.IF_ACMPNE ||
                opcode == Opcodes.IFNULL || opcode == Opcodes.IFNONNULL) {

            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("[AutoFlow] Condition checked in: " + className + "." + methodName);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }
        super.visitJumpInsn(opcode, label);
    }

    @Override
    public void visitLabel(Label label) {
        super.visitLabel(label);
        if (!loopLabels.contains(label)) {
            loopLabels.add(label);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("[AutoFlow] Loop start at label in: " + className + "." + methodName);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
