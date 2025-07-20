package com.autoflow.agent;

import org.objectweb.asm.*;

public class AutoFlowClassVisitor extends ClassVisitor {
    private final String className;

    public AutoFlowClassVisitor(int api, ClassVisitor classVisitor, String className) {
        super(api, classVisitor);
        this.className = className;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor,
                                     String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        return new AutoFlowMethodVisitor(api, mv, className, name);
    }
}
