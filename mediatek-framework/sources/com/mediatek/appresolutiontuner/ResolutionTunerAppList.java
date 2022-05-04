package com.mediatek.appresolutiontuner;

import android.os.SystemProperties;
import android.util.Slog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/* loaded from: classes.dex */
public class ResolutionTunerAppList {
    private static final String APP_LIST_PATH = "system/etc/resolution_tuner_app_list.xml";
    private static final String APP_LIST_PATH_FOR_AIVRS = "/vendor/etc/aivrs.ini";
    private static final String APP_LIST_PATH_FOR_GAISR = "/vendor/etc/gaisr.ini";
    private static final String NODE_FILTERED_WINDOW = "filteredwindow";
    private static final String NODE_PACKAGE_NAME = "packagename";
    private static final String NODE_SCALE = "scale";
    private static final String NODE_SCALING_FLOW = "flow";
    private static final String TAG = "AppResolutionTuner";
    private static final String TAG_APP = "app";
    private static final String VALUE_SCALING_FLOW_SURFACEVIEW = "surfaceview";
    private static final String VALUE_SCALING_FLOW_WMS = "wms";
    private static ResolutionTunerAppList sInstance;
    private ArrayList<Applic> mTunerAppCache;

    public static ResolutionTunerAppList getInstance() {
        if (sInstance == null) {
            sInstance = new ResolutionTunerAppList();
        }
        return sInstance;
    }

    public void loadTunerAppList() {
        IOException e;
        Slog.d(TAG, "loadTunerAppList + ");
        InputStream inputStream = null;
        try {
            try {
            } catch (Throwable th) {
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                        Slog.w(TAG, "close failed..", e2);
                    }
                }
                Slog.d(TAG, "loadTunerAppList - ");
                throw th;
            }
        } catch (IOException e3) {
            Slog.w(TAG, "IOException", e3);
            if (0 != 0) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                    e = e4;
                    Slog.w(TAG, "close failed..", e);
                    Slog.d(TAG, "loadTunerAppList - ");
                }
            }
        }
        if ("1".equals(SystemProperties.get("ro.vendor.game_aisr_enable"))) {
            File target = new File(APP_LIST_PATH_FOR_AIVRS);
            if (target.exists()) {
                this.mTunerAppCache = parseAppListFileForAIVRS(target);
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (IOException e5) {
                        Slog.w(TAG, "close failed..", e5);
                    }
                }
                Slog.d(TAG, "loadTunerAppList - ");
                return;
            }
            Slog.d(TAG, "Target file doesn't exist: /vendor/etc/aivrs.ini");
            File target2 = new File(APP_LIST_PATH_FOR_GAISR);
            if (target2.exists()) {
                this.mTunerAppCache = parseAppListFileForGAISR(target2);
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (IOException e6) {
                        Slog.w(TAG, "close failed..", e6);
                    }
                }
                Slog.d(TAG, "loadTunerAppList - ");
                return;
            }
            Slog.d(TAG, "Target file doesn't exist: /vendor/etc/gaisr.ini");
            if (0 != 0) {
                try {
                    inputStream.close();
                } catch (IOException e7) {
                    Slog.w(TAG, "close failed..", e7);
                }
            }
            Slog.d(TAG, "loadTunerAppList - ");
            return;
        }
        File target3 = new File(APP_LIST_PATH);
        if (!target3.exists()) {
            Slog.e(TAG, "Target file doesn't exist: system/etc/resolution_tuner_app_list.xml");
            if (0 != 0) {
                try {
                    inputStream.close();
                } catch (IOException e8) {
                    Slog.w(TAG, "close failed..", e8);
                }
            }
            Slog.d(TAG, "loadTunerAppList - ");
            return;
        }
        InputStream inputStream2 = new FileInputStream(target3);
        this.mTunerAppCache = parseAppListFile(inputStream2);
        try {
            inputStream2.close();
        } catch (IOException e9) {
            e = e9;
            Slog.w(TAG, "close failed..", e);
            Slog.d(TAG, "loadTunerAppList - ");
        }
        Slog.d(TAG, "loadTunerAppList - ");
    }

    public boolean isScaledByWMS(String packageName, String windowName) {
        ArrayList<Applic> arrayList = this.mTunerAppCache;
        if (arrayList == null) {
            return false;
        }
        Iterator<Applic> it = arrayList.iterator();
        while (it.hasNext()) {
            Applic app = it.next();
            if (app.getPackageName().equals(packageName) && app.getScalingFlow().equals(VALUE_SCALING_FLOW_WMS)) {
                return !app.isFiltered(windowName);
            }
        }
        return false;
    }

    public boolean isScaledBySurfaceView(String packageName) {
        ArrayList<Applic> arrayList = this.mTunerAppCache;
        if (arrayList == null) {
            return false;
        }
        Iterator<Applic> it = arrayList.iterator();
        while (it.hasNext()) {
            Applic app = it.next();
            if (app.getPackageName().equals(packageName) && !app.getScalingFlow().equals(VALUE_SCALING_FLOW_WMS)) {
                return true;
            }
        }
        return false;
    }

    public float getScaleValue(String packageName) {
        ArrayList<Applic> arrayList = this.mTunerAppCache;
        if (arrayList == null) {
            return 1.0f;
        }
        Iterator<Applic> it = arrayList.iterator();
        while (it.hasNext()) {
            Applic app = it.next();
            if (app.getPackageName().equals(packageName)) {
                return app.getScale();
            }
        }
        return 1.0f;
    }

    public float getScaleWidth(String packageName) {
        ArrayList<Applic> arrayList = this.mTunerAppCache;
        if (arrayList == null) {
            return 1.0f;
        }
        Iterator<Applic> it = arrayList.iterator();
        while (it.hasNext()) {
            Applic app = it.next();
            if (app.getPackageName().equals(packageName)) {
                return app.getScaleWidth();
            }
        }
        return 1.0f;
    }

    public float getScaleHeight(String packageName) {
        ArrayList<Applic> arrayList = this.mTunerAppCache;
        if (arrayList == null) {
            return 1.0f;
        }
        Iterator<Applic> it = arrayList.iterator();
        while (it.hasNext()) {
            Applic app = it.next();
            if (app.getPackageName().equals(packageName)) {
                return app.getScaleHeight();
            }
        }
        return 1.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class Applic {
        private String packageName;
        private float scale;
        private float scaleHeight;
        private float scaleWidth;
        private ArrayList<String> filteredWindows = new ArrayList<>();
        private String scalingFlow = "";

        Applic() {
        }

        public String getPackageName() {
            return this.packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public float getScale() {
            return this.scale;
        }

        public void setScale(float scale) {
            this.scale = scale;
        }

        public void setScale(float scaleWidth, float scaleHeight) {
            this.scaleWidth = scaleWidth;
            this.scaleHeight = scaleHeight;
        }

        public float getScaleWidth() {
            return this.scaleWidth;
        }

        public void setScaleWidth(float scaleWidth) {
            this.scaleWidth = scaleWidth;
        }

        public float getScaleHeight() {
            return this.scaleHeight;
        }

        public void setScaleHeight(float scaleHeight) {
            this.scaleHeight = scaleHeight;
        }

        public void addFilteredWindow(String windowName) {
            this.filteredWindows.add(windowName);
        }

        public boolean isFiltered(String windowName) {
            return this.filteredWindows.contains(windowName);
        }

        public String getScalingFlow() {
            return this.scalingFlow;
        }

        public void setScalingFlow(String scalingFlow) {
            this.scalingFlow = scalingFlow;
        }

        public String toString() {
            return "App{packageName='" + this.packageName + "', scale='" + this.scale + "', scaleWidthHight='" + this.scaleWidth + "x" + this.scaleHeight + "', filteredWindows= " + this.filteredWindows + "', scalingFlow= " + this.scalingFlow + "'}";
        }
    }

    private ArrayList<Applic> parseAppListFile(InputStream is) {
        ArrayList<Applic> list = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(is);
            NodeList appList = document.getElementsByTagName(TAG_APP);
            for (int i = 0; i < appList.getLength(); i++) {
                Node node_applic = appList.item(i);
                NodeList childNodes = node_applic.getChildNodes();
                Applic applic = new Applic();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node childNode = childNodes.item(j);
                    if (childNode.getNodeName().equals(NODE_PACKAGE_NAME)) {
                        String packageName = childNode.getTextContent();
                        applic.setPackageName(packageName);
                    } else if (childNode.getNodeName().equals(NODE_SCALE)) {
                        String scale = childNode.getTextContent();
                        applic.setScale(Float.parseFloat(scale));
                    } else if (childNode.getNodeName().startsWith(NODE_FILTERED_WINDOW)) {
                        String filteredWindow = childNode.getTextContent();
                        applic.addFilteredWindow(filteredWindow);
                    } else if (childNode.getNodeName().startsWith(NODE_SCALING_FLOW)) {
                        String scalingFlow = childNode.getTextContent();
                        applic.setScalingFlow(scalingFlow);
                    }
                }
                list.add(applic);
                Slog.d(TAG, "dom2xml: " + applic);
            }
            return list;
        } catch (IOException e) {
            Slog.w(TAG, "IOException", e);
            return list;
        } catch (ParserConfigurationException e2) {
            Slog.w(TAG, "dom2xml ParserConfigurationException", e2);
            return list;
        } catch (SAXException e3) {
            Slog.w(TAG, "dom2xml SAXException", e3);
            return list;
        }
    }

    private ArrayList<Applic> parseAppListFileForGAISR(File listFile) {
        ArrayList<Applic> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(listFile));
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                } else if (!line.isEmpty() && line.indexOf("=") >= 1) {
                    String packageName = line.substring(0, line.indexOf("="));
                    float width = Float.parseFloat(line.substring(line.indexOf("\"") + 1, line.indexOf(" ")));
                    float height = Float.parseFloat(line.substring(line.indexOf(" ") + 1, line.length() - 1));
                    Applic applic = new Applic();
                    applic.setPackageName(packageName.trim());
                    applic.setScale(width, height);
                    list.add(applic);
                    Slog.w(TAG, "parseAppListFileForGAISR  packageName: " + packageName);
                }
            }
            br.close();
            br.close();
        } catch (Exception e) {
            Slog.w(TAG, "Failed to read app list for resolution tuner app list " + listFile, e);
        }
        return list;
    }

    private ArrayList<Applic> parseAppListFileForAIVRS(File listFile) {
        ArrayList<Applic> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(listFile));
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                } else if (!line.isEmpty() && line.indexOf("=") >= 1 && line.indexOf("\"") >= 1) {
                    String value = line.substring(line.indexOf("\"") + 1, line.length() - 1);
                    if (Integer.parseInt(value) < 70 && Integer.parseInt(value) > 0) {
                        String packageName = line.substring(0, line.indexOf("="));
                        Applic applic = new Applic();
                        applic.setPackageName(packageName.trim());
                        applic.setScale(1.5f);
                        list.add(applic);
                        Slog.w(TAG, "parseAppListFileForAIVRS  packageName: " + packageName + " value:" + value);
                    }
                }
            }
            br.close();
            br.close();
        } catch (Exception e) {
            Slog.w(TAG, "Failed to read app list for resolution tuner app list " + listFile, e);
        }
        return list;
    }
}
