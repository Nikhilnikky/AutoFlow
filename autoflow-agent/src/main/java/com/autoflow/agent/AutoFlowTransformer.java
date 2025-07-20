package com.autoflow.agent;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import org.objectweb.asm.*;

public class AutoFlowTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        if (className == null || !className.contains("com/nikky")) return null;

        ClassReader reader = new ClassReader(classfileBuffer);
        ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES);

        ClassVisitor visitor = new AutoFlowClassVisitor(Opcodes.ASM9, writer, className);
        reader.accept(visitor, ClassReader.EXPAND_FRAMES);

        return writer.toByteArray();
    }
}
