package com.mio.libfixer.transformer;

import javassist.CtClass;
import javassist.CtMethod;

public class LibraryTransformer implements BaseTransformer {
    @Override
    public String getTargetClassName() {
        return "org.lwjgl.system.Library";
    }

    @Override
    public byte[] transform(byte[] buffer) {
        try {
            CtClass clazz = pool.get("org.lwjgl.system.Library");
            CtMethod method = clazz.getDeclaredMethod("checkHash");
            method.setBody("{}");
            byte[] bytes = clazz.toBytecode();
            clazz.detach();
            return bytes;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return buffer;
    }
}