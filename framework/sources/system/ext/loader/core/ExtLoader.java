package system.ext.loader.core;

import android.util.Log;
/* loaded from: classes4.dex */
public class ExtLoader {
    private static final String TAG = "ExtLoader";
    private static final boolean DEFAULT_DEBUG_DUMMY = false;
    private static final boolean DEFAULT_DEBUG_CREATE = false;

    static {
        Log.d(TAG, "DEFAULT_DEBUG_DUMMY=false, DEFAULT_DEBUG_CREATE=false");
    }

    public static <T> T load(Class<T> extClass) {
        return (T) load(extClass, false);
    }

    public static <T> T load(Class<T> extClass, boolean debug) {
        return (T) ExtDummy.createDummyExt(extClass, debug);
    }

    public static <T> ExtBuilder<T> type(Class<T> extClass) {
        return new ExtBuilder().type(extClass);
    }

    /* loaded from: classes4.dex */
    public static class ExtBuilder<T> {
        private Object mBase;
        private boolean mDebugCreate;
        private boolean mDebugDummy;
        private Class<T> mExtClass;
        private boolean mHasDummyExt;

        private ExtBuilder() {
            this.mHasDummyExt = true;
            this.mDebugDummy = ExtLoader.DEFAULT_DEBUG_DUMMY;
            this.mDebugCreate = ExtLoader.DEFAULT_DEBUG_CREATE;
        }

        public ExtBuilder<T> type(Class<T> extClass) {
            this.mExtClass = extClass;
            return this;
        }

        public ExtBuilder<T> base(Object base) {
            this.mBase = base;
            return this;
        }

        public ExtBuilder<T> enableDebugCreate() {
            this.mDebugCreate = true;
            return this;
        }

        public ExtBuilder<T> disableDebugCreate() {
            this.mDebugCreate = false;
            return this;
        }

        public ExtBuilder<T> enableDebugDummy() {
            this.mDebugDummy = true;
            return this;
        }

        public ExtBuilder<T> disableDebugDummy() {
            this.mDebugDummy = false;
            return this;
        }

        public ExtBuilder<T> enableDummyExt() {
            this.mHasDummyExt = true;
            return this;
        }

        public ExtBuilder<T> disableDummyExt() {
            this.mHasDummyExt = false;
            return this;
        }

        public T create() {
            ExtCreator<T> extCreator = ExtRegistry.getExt(this.mExtClass);
            if (extCreator != null) {
                log(this.mDebugCreate, this.mExtClass, "createExtWith");
                return extCreator.createExtWith(this.mBase);
            } else if (this.mHasDummyExt) {
                log(this.mDebugDummy, this.mExtClass, "createDummyExt");
                return (T) ExtDummy.createDummyExt(this.mExtClass, this.mDebugDummy);
            } else {
                log(true, this.mExtClass, "create null");
                return null;
            }
        }

        private void log(boolean debug, Class<?> extClass, String msg) {
            if (debug) {
                Log.d(ExtLoader.TAG, msg + " : " + extClass);
            }
        }
    }
}
