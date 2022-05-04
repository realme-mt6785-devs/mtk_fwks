package android.view;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.os.OplusJankMonitorExtPlugin;
import android.os.StrictMode;
import android.os.SystemClock;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.internal.R;
import com.android.internal.content.NativeLibraryHelper;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes3.dex */
public abstract class LayoutInflater {
    private static final String ATTR_LAYOUT = "layout";
    private static final String COMPILED_VIEW_DEX_FILE_NAME = "/compiled_view.dex";
    private static final boolean DEBUG = false;
    private static final String TAG_1995 = "blink";
    private static final String TAG_INCLUDE = "include";
    private static final String TAG_MERGE = "merge";
    private static final String TAG_REQUEST_FOCUS = "requestFocus";
    private static final String TAG_TAG = "tag";
    private static final String USE_PRECOMPILED_LAYOUT = "view.precompiled_layout_enabled";
    final Object[] mConstructorArgs;
    protected final Context mContext;
    private Factory mFactory;
    private Factory2 mFactory2;
    private boolean mFactorySet;
    private Filter mFilter;
    private HashMap<String, Boolean> mFilterMap;
    private ClassLoader mPrecompiledClassLoader;
    private Factory2 mPrivateFactory;
    private TypedValue mTempValue;
    private boolean mUseCompiledView;
    private static final String TAG = LayoutInflater.class.getSimpleName();
    private static final StackTraceElement[] EMPTY_STACK_TRACE = new StackTraceElement[0];
    static final Class<?>[] mConstructorSignature = {Context.class, AttributeSet.class};
    private static final HashMap<String, Constructor<? extends View>> sConstructorMap = new HashMap<>();
    private static final int[] ATTRS_THEME = {16842752};
    private static final ClassLoader BOOT_CLASS_LOADER = LayoutInflater.class.getClassLoader();

    /* loaded from: classes3.dex */
    public interface Factory {
        View onCreateView(String str, Context context, AttributeSet attributeSet);
    }

    /* loaded from: classes3.dex */
    public interface Factory2 extends Factory {
        View onCreateView(View view, String str, Context context, AttributeSet attributeSet);
    }

    /* loaded from: classes3.dex */
    public interface Filter {
        boolean onLoadClass(Class cls);
    }

    public abstract LayoutInflater cloneInContext(Context context);

    /* loaded from: classes3.dex */
    private static class FactoryMerger implements Factory2 {
        private final Factory mF1;
        private final Factory2 mF12;
        private final Factory mF2;
        private final Factory2 mF22;

        FactoryMerger(Factory f1, Factory2 f12, Factory f2, Factory2 f22) {
            this.mF1 = f1;
            this.mF2 = f2;
            this.mF12 = f12;
            this.mF22 = f22;
        }

        @Override // android.view.LayoutInflater.Factory
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            View v = this.mF1.onCreateView(name, context, attrs);
            return v != null ? v : this.mF2.onCreateView(name, context, attrs);
        }

        @Override // android.view.LayoutInflater.Factory2
        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
            Factory2 factory2 = this.mF12;
            View v = factory2 != null ? factory2.onCreateView(parent, name, context, attrs) : this.mF1.onCreateView(name, context, attrs);
            if (v != null) {
                return v;
            }
            Factory2 factory22 = this.mF22;
            return factory22 != null ? factory22.onCreateView(parent, name, context, attrs) : this.mF2.onCreateView(name, context, attrs);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LayoutInflater(Context context) {
        this.mConstructorArgs = new Object[2];
        StrictMode.assertConfigurationContext(context, "LayoutInflater");
        this.mContext = context;
        initPrecompiledViews();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LayoutInflater(LayoutInflater original, Context newContext) {
        this.mConstructorArgs = new Object[2];
        StrictMode.assertConfigurationContext(newContext, "LayoutInflater");
        this.mContext = newContext;
        this.mFactory = original.mFactory;
        this.mFactory2 = original.mFactory2;
        this.mPrivateFactory = original.mPrivateFactory;
        setFilter(original.mFilter);
        initPrecompiledViews();
    }

    public static LayoutInflater from(Context context) {
        LayoutInflater LayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (LayoutInflater != null) {
            return LayoutInflater;
        }
        throw new AssertionError("LayoutInflater not found.");
    }

    public Context getContext() {
        return this.mContext;
    }

    public final Factory getFactory() {
        return this.mFactory;
    }

    public final Factory2 getFactory2() {
        return this.mFactory2;
    }

    public void setFactory(Factory factory) {
        if (this.mFactorySet) {
            throw new IllegalStateException("A factory has already been set on this LayoutInflater");
        } else if (factory != null) {
            this.mFactorySet = true;
            Factory factory2 = this.mFactory;
            if (factory2 == null) {
                this.mFactory = factory;
            } else {
                this.mFactory = new FactoryMerger(factory, null, factory2, this.mFactory2);
            }
        } else {
            throw new NullPointerException("Given factory can not be null");
        }
    }

    public void setFactory2(Factory2 factory) {
        if (this.mFactorySet) {
            throw new IllegalStateException("A factory has already been set on this LayoutInflater");
        } else if (factory != null) {
            this.mFactorySet = true;
            Factory factory2 = this.mFactory;
            if (factory2 == null) {
                this.mFactory2 = factory;
                this.mFactory = factory;
                return;
            }
            FactoryMerger factoryMerger = new FactoryMerger(factory, factory, factory2, this.mFactory2);
            this.mFactory2 = factoryMerger;
            this.mFactory = factoryMerger;
        } else {
            throw new NullPointerException("Given factory can not be null");
        }
    }

    public void setPrivateFactory(Factory2 factory) {
        Factory2 factory2 = this.mPrivateFactory;
        if (factory2 == null) {
            this.mPrivateFactory = factory;
        } else {
            this.mPrivateFactory = new FactoryMerger(factory, factory, factory2, factory2);
        }
    }

    public Filter getFilter() {
        return this.mFilter;
    }

    public void setFilter(Filter filter) {
        this.mFilter = filter;
        if (filter != null) {
            this.mFilterMap = new HashMap<>();
        }
    }

    private void initPrecompiledViews() {
        initPrecompiledViews(false);
    }

    private void initPrecompiledViews(boolean enablePrecompiledViews) {
        this.mUseCompiledView = enablePrecompiledViews;
        if (!enablePrecompiledViews) {
            this.mPrecompiledClassLoader = null;
            return;
        }
        ApplicationInfo appInfo = this.mContext.getApplicationInfo();
        if (appInfo.isEmbeddedDexUsed() || appInfo.isPrivilegedApp()) {
            this.mUseCompiledView = false;
            return;
        }
        try {
            this.mPrecompiledClassLoader = this.mContext.getClassLoader();
            String dexFile = this.mContext.getCodeCacheDir() + COMPILED_VIEW_DEX_FILE_NAME;
            if (new File(dexFile).exists()) {
                this.mPrecompiledClassLoader = new PathClassLoader(dexFile, this.mPrecompiledClassLoader);
            } else {
                this.mUseCompiledView = false;
            }
        } catch (Throwable th) {
            this.mUseCompiledView = false;
        }
        if (!this.mUseCompiledView) {
            this.mPrecompiledClassLoader = null;
        }
    }

    public void setPrecompiledLayoutsEnabledForTesting(boolean enablePrecompiledLayouts) {
        initPrecompiledViews(enablePrecompiledLayouts);
    }

    public View inflate(int resource, ViewGroup root) {
        return inflate(resource, root, root != null);
    }

    public View inflate(XmlPullParser parser, ViewGroup root) {
        return inflate(parser, root, root != null);
    }

    public View inflate(int resource, ViewGroup root, boolean attachToRoot) {
        Resources res = getContext().getResources();
        View view = tryInflatePrecompiled(resource, res, root, attachToRoot);
        if (view != null) {
            return view;
        }
        XmlResourceParser parser = res.getLayout(resource);
        long startTime = SystemClock.uptimeMillis();
        try {
            View inflate = inflate(parser, root, attachToRoot);
            parser.close();
            if (res != null) {
                OplusJankMonitorExtPlugin.setLaunchStageTime.call(res.getResourceName(resource), "viewInflate", Long.valueOf(startTime));
            }
            return inflate;
        } catch (Throwable th) {
            parser.close();
            if (res != null) {
                OplusJankMonitorExtPlugin.setLaunchStageTime.call(res.getResourceName(resource), "viewInflate", Long.valueOf(startTime));
            }
            throw th;
        }
    }

    private View tryInflatePrecompiled(int resource, Resources res, ViewGroup root, boolean attachToRoot) {
        if (!this.mUseCompiledView) {
            return null;
        }
        Trace.traceBegin(8L, "inflate (precompiled)");
        String pkg = res.getResourcePackageName(resource);
        String layout = res.getResourceEntryName(resource);
        try {
            Class clazz = Class.forName("" + pkg + ".CompiledView", false, this.mPrecompiledClassLoader);
            Method inflater = clazz.getMethod(layout, Context.class, Integer.TYPE);
            View view = (View) inflater.invoke(null, this.mContext, Integer.valueOf(resource));
            if (!(view == null || root == null)) {
                XmlResourceParser parser = res.getLayout(resource);
                AttributeSet attrs = Xml.asAttributeSet(parser);
                advanceToRootNode(parser);
                ViewGroup.LayoutParams params = root.generateLayoutParams(attrs);
                if (attachToRoot) {
                    root.addView(view, params);
                } else {
                    view.setLayoutParams(params);
                }
                parser.close();
            }
            Trace.traceEnd(8L);
            return view;
        } catch (Throwable th) {
            Trace.traceEnd(8L);
            return null;
        }
    }

    private void advanceToRootNode(XmlPullParser parser) throws InflateException, IOException, XmlPullParserException {
        int type;
        do {
            type = parser.next();
            if (type == 2) {
                break;
            }
        } while (type != 1);
        if (type != 2) {
            throw new InflateException(parser.getPositionDescription() + ": No start tag found!");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Unknown variable types count: 4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View inflate(org.xmlpull.v1.XmlPullParser r20, android.view.ViewGroup r21, boolean r22) {
        /*
            Method dump skipped, instructions count: 240
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.LayoutInflater.inflate(org.xmlpull.v1.XmlPullParser, android.view.ViewGroup, boolean):android.view.View");
    }

    private static String getParserStateDescription(Context context, AttributeSet attrs) {
        int sourceResId = Resources.getAttributeSetSourceResId(attrs);
        if (sourceResId == 0) {
            return attrs.getPositionDescription();
        }
        return attrs.getPositionDescription() + " in " + context.getResources().getResourceName(sourceResId);
    }

    private final boolean verifyClassLoader(Constructor<? extends View> constructor) {
        ClassLoader constructorLoader = constructor.getDeclaringClass().getClassLoader();
        if (constructorLoader == BOOT_CLASS_LOADER) {
            return true;
        }
        ClassLoader cl = this.mContext.getClassLoader();
        while (constructorLoader != cl) {
            cl = cl.getParent();
            if (cl == null) {
                return false;
            }
        }
        return true;
    }

    public final View createView(String name, String prefix, AttributeSet attrs) throws ClassNotFoundException, InflateException {
        Context context = (Context) this.mConstructorArgs[0];
        if (context == null) {
            context = this.mContext;
        }
        return createView(context, name, prefix, attrs);
    }

    public final View createView(Context viewContext, String name, String prefix, AttributeSet attrs) throws ClassNotFoundException, InflateException {
        String str;
        String str2;
        String str3;
        String str4;
        Objects.requireNonNull(viewContext);
        Objects.requireNonNull(name);
        HashMap<String, Constructor<? extends View>> hashMap = sConstructorMap;
        Constructor<? extends View> constructor = hashMap.get(name);
        if (constructor != null && !verifyClassLoader(constructor)) {
            constructor = null;
            hashMap.remove(name);
        }
        Class<? extends View> clazz = null;
        try {
            try {
                try {
                    try {
                        try {
                            Trace.traceBegin(8L, name);
                            if (constructor == null) {
                                if (prefix != null) {
                                    str4 = prefix + name;
                                } else {
                                    str4 = name;
                                }
                                Class asSubclass = Class.forName(str4, false, this.mContext.getClassLoader()).asSubclass(View.class);
                                Filter filter = this.mFilter;
                                if (!(filter == null || asSubclass == null || filter.onLoadClass(asSubclass))) {
                                    failNotAllowed(name, prefix, viewContext, attrs);
                                }
                                constructor = asSubclass.getConstructor(mConstructorSignature);
                                constructor.setAccessible(true);
                                hashMap.put(name, constructor);
                            } else if (this.mFilter != null) {
                                Boolean allowedState = this.mFilterMap.get(name);
                                if (allowedState == null) {
                                    if (prefix != null) {
                                        str3 = prefix + name;
                                    } else {
                                        str3 = name;
                                    }
                                    Class asSubclass2 = Class.forName(str3, false, this.mContext.getClassLoader()).asSubclass(View.class);
                                    boolean allowed = asSubclass2 != null && this.mFilter.onLoadClass(asSubclass2);
                                    this.mFilterMap.put(name, Boolean.valueOf(allowed));
                                    if (!allowed) {
                                        failNotAllowed(name, prefix, viewContext, attrs);
                                    }
                                } else if (allowedState.equals(Boolean.FALSE)) {
                                    failNotAllowed(name, prefix, viewContext, attrs);
                                }
                            }
                            Object[] args = this.mConstructorArgs;
                            Object lastContext = args[0];
                            args[0] = viewContext;
                            args[1] = attrs;
                            try {
                                View view = (View) constructor.newInstance(args);
                                if (view instanceof ViewStub) {
                                    ViewStub viewStub = (ViewStub) view;
                                    viewStub.setLayoutInflater(cloneInContext((Context) args[0]));
                                }
                                return view;
                            } finally {
                                this.mConstructorArgs[0] = lastContext;
                            }
                        } catch (ClassCastException e) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(getParserStateDescription(viewContext, attrs));
                            sb.append(": Class is not a View ");
                            if (prefix != null) {
                                str2 = prefix + name;
                            } else {
                                str2 = name;
                            }
                            sb.append(str2);
                            InflateException ie = new InflateException(sb.toString(), e);
                            ie.setStackTrace(EMPTY_STACK_TRACE);
                            throw ie;
                        }
                    } catch (NoSuchMethodException e2) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(getParserStateDescription(viewContext, attrs));
                        sb2.append(": Error inflating class ");
                        if (prefix != null) {
                            str = prefix + name;
                        } else {
                            str = name;
                        }
                        sb2.append(str);
                        InflateException ie2 = new InflateException(sb2.toString(), e2);
                        ie2.setStackTrace(EMPTY_STACK_TRACE);
                        throw ie2;
                    }
                } catch (ClassNotFoundException e3) {
                    throw e3;
                }
            } catch (Exception e4) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(getParserStateDescription(viewContext, attrs));
                sb3.append(": Error inflating class ");
                sb3.append(0 == 0 ? "<unknown>" : clazz.getName());
                InflateException ie3 = new InflateException(sb3.toString(), e4);
                ie3.setStackTrace(EMPTY_STACK_TRACE);
                throw ie3;
            }
        } finally {
            Trace.traceEnd(8L);
        }
    }

    private void failNotAllowed(String name, String prefix, Context context, AttributeSet attrs) {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(getParserStateDescription(context, attrs));
        sb.append(": Class not allowed to be inflated ");
        if (prefix != null) {
            str = prefix + name;
        } else {
            str = name;
        }
        sb.append(str);
        throw new InflateException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public View onCreateView(String name, AttributeSet attrs) throws ClassNotFoundException {
        return createView(name, "android.view.", attrs);
    }

    protected View onCreateView(View parent, String name, AttributeSet attrs) throws ClassNotFoundException {
        return onCreateView(name, attrs);
    }

    public View onCreateView(Context viewContext, View parent, String name, AttributeSet attrs) throws ClassNotFoundException {
        return onCreateView(parent, name, attrs);
    }

    private View createViewFromTag(View parent, String name, Context context, AttributeSet attrs) {
        return createViewFromTag(parent, name, context, attrs, false);
    }

    /* JADX WARN: Finally extract failed */
    View createViewFromTag(View parent, String name, Context context, AttributeSet attrs, boolean ignoreThemeAttr) {
        if (name.equals("view")) {
            name = attrs.getAttributeValue(null, "class");
        }
        if (!ignoreThemeAttr) {
            TypedArray ta = context.obtainStyledAttributes(attrs, ATTRS_THEME);
            int themeResId = ta.getResourceId(0, 0);
            if (themeResId != 0) {
                context = new ContextThemeWrapper(context, themeResId);
            }
            ta.recycle();
        }
        try {
            View view = tryCreateView(parent, name, context, attrs);
            if (view == null) {
                Object[] objArr = this.mConstructorArgs;
                Object lastContext = objArr[0];
                objArr[0] = context;
                try {
                    if (-1 == name.indexOf(46)) {
                        if (Trace.isTagEnabled(8L)) {
                            Trace.traceBegin(8L, "onCreateView:" + name);
                        }
                        view = onCreateView(context, parent, name, attrs);
                        if (Trace.isTagEnabled(8L)) {
                            Trace.traceEnd(8L);
                        }
                    } else {
                        view = createView(context, name, null, attrs);
                    }
                    this.mConstructorArgs[0] = lastContext;
                } catch (Throwable th) {
                    this.mConstructorArgs[0] = lastContext;
                    throw th;
                }
            }
            return view;
        } catch (InflateException e) {
            throw e;
        } catch (ClassNotFoundException e2) {
            InflateException ie = new InflateException(getParserStateDescription(context, attrs) + ": Error inflating class " + name, e2);
            ie.setStackTrace(EMPTY_STACK_TRACE);
            throw ie;
        } catch (Exception e3) {
            InflateException ie2 = new InflateException(getParserStateDescription(context, attrs) + ": Error inflating class " + name, e3);
            ie2.setStackTrace(EMPTY_STACK_TRACE);
            throw ie2;
        }
    }

    public final View tryCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view;
        if (name.equals(TAG_1995)) {
            return new BlinkLayout(context, attrs);
        }
        if (this.mFactory2 != null) {
            if (Trace.isTagEnabled(8L)) {
                Trace.traceBegin(8L, "createView by " + this.mFactory2 + "-:" + name);
            }
            view = this.mFactory2.onCreateView(parent, name, context, attrs);
            if (Trace.isTagEnabled(8L)) {
                Trace.traceEnd(8L);
            }
        } else if (this.mFactory != null) {
            if (Trace.isTagEnabled(8L)) {
                Trace.traceBegin(8L, "createView by " + this.mFactory + "-:" + name);
            }
            view = this.mFactory.onCreateView(name, context, attrs);
            if (Trace.isTagEnabled(8L)) {
                Trace.traceEnd(8L);
            }
        } else {
            view = null;
        }
        if (view == null && this.mPrivateFactory != null) {
            if (Trace.isTagEnabled(8L)) {
                Trace.traceBegin(8L, "createView by " + this.mPrivateFactory + NativeLibraryHelper.CLEAR_ABI_OVERRIDE + name);
            }
            view = this.mPrivateFactory.onCreateView(parent, name, context, attrs);
            if (Trace.isTagEnabled(8L)) {
                Trace.traceEnd(8L);
            }
        }
        return view;
    }

    final void rInflateChildren(XmlPullParser parser, View parent, AttributeSet attrs, boolean finishInflate) throws XmlPullParserException, IOException {
        rInflate(parser, parent, parent.getContext(), attrs, finishInflate);
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0075, code lost:
        if (r1 == false) goto L_0x007a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0077, code lost:
        r10.restoreDefaultFocus();
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x007a, code lost:
        if (r13 == false) goto L_?;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x007c, code lost:
        r10.onFinishInflate();
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x007f, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:?, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void rInflate(org.xmlpull.v1.XmlPullParser r9, android.view.View r10, android.content.Context r11, android.util.AttributeSet r12, boolean r13) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r8 = this;
            int r0 = r9.getDepth()
            r1 = 0
        L_0x0005:
            int r2 = r9.next()
            r3 = r2
            r4 = 3
            if (r2 != r4) goto L_0x0013
            int r2 = r9.getDepth()
            if (r2 <= r0) goto L_0x0075
        L_0x0013:
            r2 = 1
            if (r3 == r2) goto L_0x0075
            r4 = 2
            if (r3 == r4) goto L_0x001a
            goto L_0x0005
        L_0x001a:
            java.lang.String r4 = r9.getName()
            java.lang.String r5 = "requestFocus"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x002c
            r1 = 1
            consumeChildElements(r9)
            goto L_0x006c
        L_0x002c:
            java.lang.String r5 = "tag"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x0039
            r8.parseViewTag(r9, r10, r12)
            goto L_0x006c
        L_0x0039:
            java.lang.String r5 = "include"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x0053
            int r2 = r9.getDepth()
            if (r2 == 0) goto L_0x004b
            r8.parseInclude(r9, r11, r10, r12)
            goto L_0x006c
        L_0x004b:
            android.view.InflateException r2 = new android.view.InflateException
            java.lang.String r5 = "<include /> cannot be the root element"
            r2.<init>(r5)
            throw r2
        L_0x0053:
            java.lang.String r5 = "merge"
            boolean r5 = r5.equals(r4)
            if (r5 != 0) goto L_0x006d
            android.view.View r5 = r8.createViewFromTag(r10, r4, r11, r12)
            r6 = r10
            android.view.ViewGroup r6 = (android.view.ViewGroup) r6
            android.view.ViewGroup$LayoutParams r7 = r6.generateLayoutParams(r12)
            r8.rInflateChildren(r9, r5, r12, r2)
            r6.addView(r5, r7)
        L_0x006c:
            goto L_0x0005
        L_0x006d:
            android.view.InflateException r2 = new android.view.InflateException
            java.lang.String r5 = "<merge /> must be the root element"
            r2.<init>(r5)
            throw r2
        L_0x0075:
            if (r1 == 0) goto L_0x007a
            r10.restoreDefaultFocus()
        L_0x007a:
            if (r13 == 0) goto L_0x007f
            r10.onFinishInflate()
        L_0x007f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.LayoutInflater.rInflate(org.xmlpull.v1.XmlPullParser, android.view.View, android.content.Context, android.util.AttributeSet, boolean):void");
    }

    private void parseViewTag(XmlPullParser parser, View view, AttributeSet attrs) throws XmlPullParserException, IOException {
        Context context = view.getContext();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ViewTag);
        int key = ta.getResourceId(1, 0);
        CharSequence value = ta.getText(0);
        view.setTag(key, value);
        ta.recycle();
        consumeChildElements(parser);
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x00a3, code lost:
        if (r0 != 2) goto L_0x014a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00a5, code lost:
        r0 = r5.getName();
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00b0, code lost:
        if (android.view.LayoutInflater.TAG_MERGE.equals(r0) == false) goto L_0x00cd;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00c3, code lost:
        rInflate(r5, r25, r15, r4, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00c6, code lost:
        r4 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00c9, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00ca, code lost:
        r4 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00e1, code lost:
        r0 = createViewFromTag(r25, r0, r15, r4, r1);
        r0 = (android.view.ViewGroup) r25;
        r0 = r15.obtainStyledAttributes(r26, com.android.internal.R.styleable.Include);
        r0 = r0.getResourceId(0, -1);
        r0 = r0.getInt(1, -1);
        r0.recycle();
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00ff, code lost:
        r21 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0105, code lost:
        r21 = r0.generateLayoutParams(r26);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0108, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0109, code lost:
        r4 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0146, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0147, code lost:
        r4 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x016a, code lost:
        throw new android.view.InflateException(getParserStateDescription(r15, r4) + ": No start tag found!");
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0171, code lost:
        r4.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0174, code lost:
        throw r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v9, types: [android.util.AttributeSet] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void parseInclude(org.xmlpull.v1.XmlPullParser r23, android.content.Context r24, android.view.View r25, android.util.AttributeSet r26) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 430
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.LayoutInflater.parseInclude(org.xmlpull.v1.XmlPullParser, android.content.Context, android.view.View, android.util.AttributeSet):void");
    }

    static final void consumeChildElements(XmlPullParser parser) throws XmlPullParserException, IOException {
        int type;
        int currentDepth = parser.getDepth();
        do {
            type = parser.next();
            if (type == 3 && parser.getDepth() <= currentDepth) {
                return;
            }
        } while (type != 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class BlinkLayout extends FrameLayout {
        private static final int BLINK_DELAY = 500;
        private static final int MESSAGE_BLINK = 66;
        private boolean mBlink;
        private boolean mBlinkState;
        private final Handler mHandler = new Handler(new Handler.Callback() { // from class: android.view.LayoutInflater.BlinkLayout.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message msg) {
                if (msg.what != 66) {
                    return false;
                }
                if (BlinkLayout.this.mBlink) {
                    BlinkLayout blinkLayout = BlinkLayout.this;
                    blinkLayout.mBlinkState = !blinkLayout.mBlinkState;
                    BlinkLayout.this.makeBlink();
                }
                BlinkLayout.this.invalidate();
                return true;
            }
        });

        public BlinkLayout(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void makeBlink() {
            Message message = this.mHandler.obtainMessage(66);
            this.mHandler.sendMessageDelayed(message, 500L);
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.mBlink = true;
            this.mBlinkState = true;
            makeBlink();
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.mBlink = false;
            this.mBlinkState = true;
            this.mHandler.removeMessages(66);
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void dispatchDraw(Canvas canvas) {
            if (this.mBlinkState) {
                super.dispatchDraw(canvas);
            }
        }
    }
}
