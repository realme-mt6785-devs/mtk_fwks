package android.transition;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import android.view.ViewGroup;
import com.android.internal.R;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes3.dex */
public class TransitionInflater {
    private static final Class<?>[] sConstructorSignature = {Context.class, AttributeSet.class};
    private static final ArrayMap<String, Constructor> sConstructors = new ArrayMap<>();
    private Context mContext;

    private TransitionInflater(Context context) {
        this.mContext = context;
    }

    public static TransitionInflater from(Context context) {
        return new TransitionInflater(context);
    }

    public Transition inflateTransition(int resource) {
        XmlResourceParser parser = this.mContext.getResources().getXml(resource);
        try {
            try {
                return createTransitionFromXml(parser, Xml.asAttributeSet(parser), null);
            } catch (IOException e) {
                InflateException ex = new InflateException(parser.getPositionDescription() + ": " + e.getMessage());
                ex.initCause(e);
                throw ex;
            } catch (XmlPullParserException e2) {
                InflateException ex2 = new InflateException(e2.getMessage());
                ex2.initCause(e2);
                throw ex2;
            }
        } finally {
            parser.close();
        }
    }

    public TransitionManager inflateTransitionManager(int resource, ViewGroup sceneRoot) {
        XmlResourceParser parser = this.mContext.getResources().getXml(resource);
        try {
            try {
                return createTransitionManagerFromXml(parser, Xml.asAttributeSet(parser), sceneRoot);
            } catch (IOException e) {
                InflateException ex = new InflateException(parser.getPositionDescription() + ": " + e.getMessage());
                ex.initCause(e);
                throw ex;
            } catch (XmlPullParserException e2) {
                InflateException ex2 = new InflateException(e2.getMessage());
                ex2.initCause(e2);
                throw ex2;
            }
        } finally {
            parser.close();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:73:0x0183, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.transition.Transition createTransitionFromXml(org.xmlpull.v1.XmlPullParser r9, android.util.AttributeSet r10, android.transition.Transition r11) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 388
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.transition.TransitionInflater.createTransitionFromXml(org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.transition.Transition):android.transition.Transition");
    }

    private Object createCustom(AttributeSet attrs, Class expectedType, String tag) {
        Object newInstance;
        Class c;
        String className = attrs.getAttributeValue(null, "class");
        if (className != null) {
            try {
                ArrayMap<String, Constructor> arrayMap = sConstructors;
                synchronized (arrayMap) {
                    Constructor constructor = arrayMap.get(className);
                    if (constructor == null && (c = this.mContext.getClassLoader().loadClass(className).asSubclass(expectedType)) != null) {
                        constructor = c.getConstructor(sConstructorSignature);
                        constructor.setAccessible(true);
                        arrayMap.put(className, constructor);
                    }
                    newInstance = constructor.newInstance(this.mContext, attrs);
                }
                return newInstance;
            } catch (ClassNotFoundException e) {
                throw new InflateException("Could not instantiate " + expectedType + " class " + className, e);
            } catch (IllegalAccessException e2) {
                throw new InflateException("Could not instantiate " + expectedType + " class " + className, e2);
            } catch (InstantiationException e3) {
                throw new InflateException("Could not instantiate " + expectedType + " class " + className, e3);
            } catch (NoSuchMethodException e4) {
                throw new InflateException("Could not instantiate " + expectedType + " class " + className, e4);
            } catch (InvocationTargetException e5) {
                throw new InflateException("Could not instantiate " + expectedType + " class " + className, e5);
            }
        } else {
            throw new InflateException(tag + " tag must have a 'class' attribute");
        }
    }

    private void getTargetIds(XmlPullParser parser, AttributeSet attrs, Transition transition) throws XmlPullParserException, IOException {
        int depth = parser.getDepth();
        while (true) {
            int type = parser.next();
            if ((type == 3 && parser.getDepth() <= depth) || type == 1) {
                return;
            }
            if (type == 2) {
                String name = parser.getName();
                if (name.equals("target")) {
                    TypedArray a = this.mContext.obtainStyledAttributes(attrs, R.styleable.TransitionTarget);
                    int id = a.getResourceId(1, 0);
                    if (id != 0) {
                        transition.addTarget(id);
                    } else {
                        int id2 = a.getResourceId(2, 0);
                        if (id2 != 0) {
                            transition.excludeTarget(id2, true);
                        } else {
                            String transitionName = a.getString(4);
                            if (transitionName != null) {
                                transition.addTarget(transitionName);
                            } else {
                                String transitionName2 = a.getString(5);
                                if (transitionName2 != null) {
                                    transition.excludeTarget(transitionName2, true);
                                } else {
                                    String className = a.getString(3);
                                    if (className != null) {
                                        try {
                                            Class clazz = Class.forName(className);
                                            transition.excludeTarget(clazz, true);
                                        } catch (ClassNotFoundException e) {
                                            a.recycle();
                                            throw new RuntimeException("Could not create " + className, e);
                                        }
                                    } else {
                                        String className2 = a.getString(0);
                                        if (className2 != null) {
                                            Class clazz2 = Class.forName(className2);
                                            transition.addTarget(clazz2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    a.recycle();
                } else {
                    throw new RuntimeException("Unknown scene name: " + parser.getName());
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0058, code lost:
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.transition.TransitionManager createTransitionManagerFromXml(org.xmlpull.v1.XmlPullParser r8, android.util.AttributeSet r9, android.view.ViewGroup r10) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r7 = this;
            int r0 = r8.getDepth()
            r1 = 0
        L_0x0005:
            int r2 = r8.next()
            r3 = r2
            r4 = 3
            if (r2 != r4) goto L_0x0013
            int r2 = r8.getDepth()
            if (r2 <= r0) goto L_0x0058
        L_0x0013:
            r2 = 1
            if (r3 == r2) goto L_0x0058
            r2 = 2
            if (r3 == r2) goto L_0x001a
            goto L_0x0005
        L_0x001a:
            java.lang.String r2 = r8.getName()
            java.lang.String r4 = "transitionManager"
            boolean r4 = r2.equals(r4)
            if (r4 == 0) goto L_0x002e
            android.transition.TransitionManager r4 = new android.transition.TransitionManager
            r4.<init>()
            r1 = r4
            goto L_0x003c
        L_0x002e:
            java.lang.String r4 = "transition"
            boolean r4 = r2.equals(r4)
            if (r4 == 0) goto L_0x003d
            if (r1 == 0) goto L_0x003d
            r7.loadTransition(r9, r10, r1)
        L_0x003c:
            goto L_0x0005
        L_0x003d:
            java.lang.RuntimeException r4 = new java.lang.RuntimeException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Unknown scene name: "
            r5.append(r6)
            java.lang.String r6 = r8.getName()
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        L_0x0058:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.transition.TransitionInflater.createTransitionManagerFromXml(org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.view.ViewGroup):android.transition.TransitionManager");
    }

    private void loadTransition(AttributeSet attrs, ViewGroup sceneRoot, TransitionManager transitionManager) throws Resources.NotFoundException {
        Transition transition;
        TypedArray a = this.mContext.obtainStyledAttributes(attrs, R.styleable.TransitionManager);
        int transitionId = a.getResourceId(2, -1);
        int fromId = a.getResourceId(0, -1);
        Scene toScene = null;
        Scene fromScene = fromId < 0 ? null : Scene.getSceneForLayout(sceneRoot, fromId, this.mContext);
        int toId = a.getResourceId(1, -1);
        if (toId >= 0) {
            toScene = Scene.getSceneForLayout(sceneRoot, toId, this.mContext);
        }
        if (transitionId >= 0 && (transition = inflateTransition(transitionId)) != null) {
            if (toScene == null) {
                throw new RuntimeException("No toScene for transition ID " + transitionId);
            } else if (fromScene == null) {
                transitionManager.setTransition(toScene, transition);
            } else {
                transitionManager.setTransition(fromScene, toScene, transition);
            }
        }
        a.recycle();
    }
}
