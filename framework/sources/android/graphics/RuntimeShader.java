package android.graphics;

import android.graphics.ColorSpace;
import libcore.util.NativeAllocationRegistry;
/* loaded from: classes.dex */
public class RuntimeShader extends Shader {
    private boolean mIsOpaque;
    private long mNativeInstanceRuntimeShaderBuilder;

    private static native long nativeCreateBuilder(String str);

    private static native long nativeCreateShader(long j, long j2, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public static native long nativeGetFinalizer();

    private static native void nativeUpdateShader(long j, String str, long j2);

    private static native void nativeUpdateUniforms(long j, String str, float[] fArr);

    /* loaded from: classes.dex */
    private static class NoImagePreloadHolder {
        public static final NativeAllocationRegistry sRegistry = NativeAllocationRegistry.createMalloced(RuntimeShader.class.getClassLoader(), RuntimeShader.nativeGetFinalizer());

        private NoImagePreloadHolder() {
        }
    }

    public RuntimeShader(String sksl, boolean isOpaque) {
        super(ColorSpace.get(ColorSpace.Named.SRGB));
        this.mIsOpaque = isOpaque;
        this.mNativeInstanceRuntimeShaderBuilder = nativeCreateBuilder(sksl);
        NoImagePreloadHolder.sRegistry.registerNativeAllocation(this, this.mNativeInstanceRuntimeShaderBuilder);
    }

    public void setUniform(String uniformName, float value) {
        setUniform(uniformName, new float[]{value});
    }

    public void setUniform(String uniformName, float value1, float value2) {
        setUniform(uniformName, new float[]{value1, value2});
    }

    public void setUniform(String uniformName, float[] values) {
        nativeUpdateUniforms(this.mNativeInstanceRuntimeShaderBuilder, uniformName, values);
        discardNativeInstance();
    }

    public void setInputShader(String shaderName, Shader shader) {
        nativeUpdateShader(this.mNativeInstanceRuntimeShaderBuilder, shaderName, shader.getNativeInstance());
        discardNativeInstance();
    }

    @Override // android.graphics.Shader
    protected long createNativeInstance(long nativeMatrix, boolean filterFromPaint) {
        return nativeCreateShader(this.mNativeInstanceRuntimeShaderBuilder, nativeMatrix, this.mIsOpaque);
    }

    public long getNativeShaderBuilder() {
        return this.mNativeInstanceRuntimeShaderBuilder;
    }

    public boolean isOpaque() {
        return this.mIsOpaque;
    }
}
