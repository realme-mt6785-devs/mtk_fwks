package android.hardware.input;

import android.hardware.input.IInputDevicesChangedListener;
import android.hardware.input.IInputSensorEventListener;
import android.hardware.input.ITabletModeChangedListener;
import android.hardware.lights.Light;
import android.hardware.lights.LightState;
import android.os.Binder;
import android.os.CombinedVibration;
import android.os.IBinder;
import android.os.IInterface;
import android.os.IVibratorStateListener;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.VibrationEffect;
import android.view.InputDevice;
import android.view.InputEvent;
import android.view.InputMonitor;
import android.view.PointerIcon;
import android.view.VerifiedInputEvent;
import java.util.List;
/* loaded from: classes2.dex */
public interface IInputManager extends IInterface {
    void addKeyboardLayoutForInputDevice(InputDeviceIdentifier inputDeviceIdentifier, String str) throws RemoteException;

    void addPortAssociation(String str, int i) throws RemoteException;

    void addUniqueIdAssociation(String str, String str2) throws RemoteException;

    void cancelVibrate(int i, IBinder iBinder) throws RemoteException;

    void closeLightSession(int i, IBinder iBinder) throws RemoteException;

    void disableInputDevice(int i) throws RemoteException;

    void disableSensor(int i, int i2) throws RemoteException;

    void enableInputDevice(int i) throws RemoteException;

    boolean enableSensor(int i, int i2, int i3, int i4) throws RemoteException;

    boolean flushSensor(int i, int i2) throws RemoteException;

    int getBatteryCapacity(int i) throws RemoteException;

    int getBatteryStatus(int i) throws RemoteException;

    String getCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier inputDeviceIdentifier) throws RemoteException;

    String[] getEnabledKeyboardLayoutsForInputDevice(InputDeviceIdentifier inputDeviceIdentifier) throws RemoteException;

    InputDevice getInputDevice(int i) throws RemoteException;

    int[] getInputDeviceIds() throws RemoteException;

    KeyboardLayout getKeyboardLayout(String str) throws RemoteException;

    KeyboardLayout[] getKeyboardLayouts() throws RemoteException;

    KeyboardLayout[] getKeyboardLayoutsForInputDevice(InputDeviceIdentifier inputDeviceIdentifier) throws RemoteException;

    LightState getLightState(int i, int i2) throws RemoteException;

    List<Light> getLights(int i) throws RemoteException;

    InputSensorInfo[] getSensorList(int i) throws RemoteException;

    TouchCalibration getTouchCalibrationForInputDevice(String str, int i) throws RemoteException;

    int[] getVibratorIds(int i) throws RemoteException;

    boolean hasKeys(int i, int i2, int[] iArr, boolean[] zArr) throws RemoteException;

    boolean injectInputEvent(InputEvent inputEvent, int i) throws RemoteException;

    int isInTabletMode() throws RemoteException;

    boolean isInputDeviceEnabled(int i) throws RemoteException;

    int isMicMuted() throws RemoteException;

    boolean isVibrating(int i) throws RemoteException;

    InputMonitor monitorGestureInput(String str, int i) throws RemoteException;

    void openLightSession(int i, String str, IBinder iBinder) throws RemoteException;

    void registerInputDevicesChangedListener(IInputDevicesChangedListener iInputDevicesChangedListener) throws RemoteException;

    boolean registerSensorListener(IInputSensorEventListener iInputSensorEventListener) throws RemoteException;

    void registerTabletModeChangedListener(ITabletModeChangedListener iTabletModeChangedListener) throws RemoteException;

    boolean registerVibratorStateListener(int i, IVibratorStateListener iVibratorStateListener) throws RemoteException;

    void removeKeyboardLayoutForInputDevice(InputDeviceIdentifier inputDeviceIdentifier, String str) throws RemoteException;

    void removePortAssociation(String str) throws RemoteException;

    void removeUniqueIdAssociation(String str) throws RemoteException;

    void requestPointerCapture(IBinder iBinder, boolean z) throws RemoteException;

    void setCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier inputDeviceIdentifier, String str) throws RemoteException;

    void setCustomPointerIcon(PointerIcon pointerIcon) throws RemoteException;

    void setLightStates(int i, int[] iArr, LightState[] lightStateArr, IBinder iBinder) throws RemoteException;

    void setPointerIconType(int i) throws RemoteException;

    void setTouchCalibrationForInputDevice(String str, int i, TouchCalibration touchCalibration) throws RemoteException;

    void tryPointerSpeed(int i) throws RemoteException;

    void unregisterSensorListener(IInputSensorEventListener iInputSensorEventListener) throws RemoteException;

    boolean unregisterVibratorStateListener(int i, IVibratorStateListener iVibratorStateListener) throws RemoteException;

    VerifiedInputEvent verifyInputEvent(InputEvent inputEvent) throws RemoteException;

    void vibrate(int i, VibrationEffect vibrationEffect, IBinder iBinder) throws RemoteException;

    void vibrateCombined(int i, CombinedVibration combinedVibration, IBinder iBinder) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IInputManager {
        @Override // android.hardware.input.IInputManager
        public InputDevice getInputDevice(int deviceId) throws RemoteException {
            return null;
        }

        @Override // android.hardware.input.IInputManager
        public int[] getInputDeviceIds() throws RemoteException {
            return null;
        }

        @Override // android.hardware.input.IInputManager
        public boolean isInputDeviceEnabled(int deviceId) throws RemoteException {
            return false;
        }

        @Override // android.hardware.input.IInputManager
        public void enableInputDevice(int deviceId) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public void disableInputDevice(int deviceId) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public boolean hasKeys(int deviceId, int sourceMask, int[] keyCodes, boolean[] keyExists) throws RemoteException {
            return false;
        }

        @Override // android.hardware.input.IInputManager
        public void tryPointerSpeed(int speed) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public boolean injectInputEvent(InputEvent ev, int mode) throws RemoteException {
            return false;
        }

        @Override // android.hardware.input.IInputManager
        public VerifiedInputEvent verifyInputEvent(InputEvent ev) throws RemoteException {
            return null;
        }

        @Override // android.hardware.input.IInputManager
        public TouchCalibration getTouchCalibrationForInputDevice(String inputDeviceDescriptor, int rotation) throws RemoteException {
            return null;
        }

        @Override // android.hardware.input.IInputManager
        public void setTouchCalibrationForInputDevice(String inputDeviceDescriptor, int rotation, TouchCalibration calibration) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public KeyboardLayout[] getKeyboardLayouts() throws RemoteException {
            return null;
        }

        @Override // android.hardware.input.IInputManager
        public KeyboardLayout[] getKeyboardLayoutsForInputDevice(InputDeviceIdentifier identifier) throws RemoteException {
            return null;
        }

        @Override // android.hardware.input.IInputManager
        public KeyboardLayout getKeyboardLayout(String keyboardLayoutDescriptor) throws RemoteException {
            return null;
        }

        @Override // android.hardware.input.IInputManager
        public String getCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier identifier) throws RemoteException {
            return null;
        }

        @Override // android.hardware.input.IInputManager
        public void setCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier identifier, String keyboardLayoutDescriptor) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public String[] getEnabledKeyboardLayoutsForInputDevice(InputDeviceIdentifier identifier) throws RemoteException {
            return null;
        }

        @Override // android.hardware.input.IInputManager
        public void addKeyboardLayoutForInputDevice(InputDeviceIdentifier identifier, String keyboardLayoutDescriptor) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public void removeKeyboardLayoutForInputDevice(InputDeviceIdentifier identifier, String keyboardLayoutDescriptor) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public void registerInputDevicesChangedListener(IInputDevicesChangedListener listener) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public int isInTabletMode() throws RemoteException {
            return 0;
        }

        @Override // android.hardware.input.IInputManager
        public void registerTabletModeChangedListener(ITabletModeChangedListener listener) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public int isMicMuted() throws RemoteException {
            return 0;
        }

        @Override // android.hardware.input.IInputManager
        public void vibrate(int deviceId, VibrationEffect effect, IBinder token) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public void vibrateCombined(int deviceId, CombinedVibration vibration, IBinder token) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public void cancelVibrate(int deviceId, IBinder token) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public int[] getVibratorIds(int deviceId) throws RemoteException {
            return null;
        }

        @Override // android.hardware.input.IInputManager
        public boolean isVibrating(int deviceId) throws RemoteException {
            return false;
        }

        @Override // android.hardware.input.IInputManager
        public boolean registerVibratorStateListener(int deviceId, IVibratorStateListener listener) throws RemoteException {
            return false;
        }

        @Override // android.hardware.input.IInputManager
        public boolean unregisterVibratorStateListener(int deviceId, IVibratorStateListener listener) throws RemoteException {
            return false;
        }

        @Override // android.hardware.input.IInputManager
        public int getBatteryStatus(int deviceId) throws RemoteException {
            return 0;
        }

        @Override // android.hardware.input.IInputManager
        public int getBatteryCapacity(int deviceId) throws RemoteException {
            return 0;
        }

        @Override // android.hardware.input.IInputManager
        public void setPointerIconType(int typeId) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public void setCustomPointerIcon(PointerIcon icon) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public void requestPointerCapture(IBinder inputChannelToken, boolean enabled) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public InputMonitor monitorGestureInput(String name, int displayId) throws RemoteException {
            return null;
        }

        @Override // android.hardware.input.IInputManager
        public void addPortAssociation(String inputPort, int displayPort) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public void removePortAssociation(String inputPort) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public void addUniqueIdAssociation(String inputDeviceName, String displayUniqueId) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public void removeUniqueIdAssociation(String inputDeviceName) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public InputSensorInfo[] getSensorList(int deviceId) throws RemoteException {
            return null;
        }

        @Override // android.hardware.input.IInputManager
        public boolean registerSensorListener(IInputSensorEventListener listener) throws RemoteException {
            return false;
        }

        @Override // android.hardware.input.IInputManager
        public void unregisterSensorListener(IInputSensorEventListener listener) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public boolean enableSensor(int deviceId, int sensorType, int samplingPeriodUs, int maxBatchReportLatencyUs) throws RemoteException {
            return false;
        }

        @Override // android.hardware.input.IInputManager
        public void disableSensor(int deviceId, int sensorType) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public boolean flushSensor(int deviceId, int sensorType) throws RemoteException {
            return false;
        }

        @Override // android.hardware.input.IInputManager
        public List<Light> getLights(int deviceId) throws RemoteException {
            return null;
        }

        @Override // android.hardware.input.IInputManager
        public LightState getLightState(int deviceId, int lightId) throws RemoteException {
            return null;
        }

        @Override // android.hardware.input.IInputManager
        public void setLightStates(int deviceId, int[] lightIds, LightState[] states, IBinder token) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public void openLightSession(int deviceId, String opPkg, IBinder token) throws RemoteException {
        }

        @Override // android.hardware.input.IInputManager
        public void closeLightSession(int deviceId, IBinder token) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IInputManager {
        public static final String DESCRIPTOR = "android.hardware.input.IInputManager";
        static final int TRANSACTION_addKeyboardLayoutForInputDevice = 18;
        static final int TRANSACTION_addPortAssociation = 37;
        static final int TRANSACTION_addUniqueIdAssociation = 39;
        static final int TRANSACTION_cancelVibrate = 26;
        static final int TRANSACTION_closeLightSession = 51;
        static final int TRANSACTION_disableInputDevice = 5;
        static final int TRANSACTION_disableSensor = 45;
        static final int TRANSACTION_enableInputDevice = 4;
        static final int TRANSACTION_enableSensor = 44;
        static final int TRANSACTION_flushSensor = 46;
        static final int TRANSACTION_getBatteryCapacity = 32;
        static final int TRANSACTION_getBatteryStatus = 31;
        static final int TRANSACTION_getCurrentKeyboardLayoutForInputDevice = 15;
        static final int TRANSACTION_getEnabledKeyboardLayoutsForInputDevice = 17;
        static final int TRANSACTION_getInputDevice = 1;
        static final int TRANSACTION_getInputDeviceIds = 2;
        static final int TRANSACTION_getKeyboardLayout = 14;
        static final int TRANSACTION_getKeyboardLayouts = 12;
        static final int TRANSACTION_getKeyboardLayoutsForInputDevice = 13;
        static final int TRANSACTION_getLightState = 48;
        static final int TRANSACTION_getLights = 47;
        static final int TRANSACTION_getSensorList = 41;
        static final int TRANSACTION_getTouchCalibrationForInputDevice = 10;
        static final int TRANSACTION_getVibratorIds = 27;
        static final int TRANSACTION_hasKeys = 6;
        static final int TRANSACTION_injectInputEvent = 8;
        static final int TRANSACTION_isInTabletMode = 21;
        static final int TRANSACTION_isInputDeviceEnabled = 3;
        static final int TRANSACTION_isMicMuted = 23;
        static final int TRANSACTION_isVibrating = 28;
        static final int TRANSACTION_monitorGestureInput = 36;
        static final int TRANSACTION_openLightSession = 50;
        static final int TRANSACTION_registerInputDevicesChangedListener = 20;
        static final int TRANSACTION_registerSensorListener = 42;
        static final int TRANSACTION_registerTabletModeChangedListener = 22;
        static final int TRANSACTION_registerVibratorStateListener = 29;
        static final int TRANSACTION_removeKeyboardLayoutForInputDevice = 19;
        static final int TRANSACTION_removePortAssociation = 38;
        static final int TRANSACTION_removeUniqueIdAssociation = 40;
        static final int TRANSACTION_requestPointerCapture = 35;
        static final int TRANSACTION_setCurrentKeyboardLayoutForInputDevice = 16;
        static final int TRANSACTION_setCustomPointerIcon = 34;
        static final int TRANSACTION_setLightStates = 49;
        static final int TRANSACTION_setPointerIconType = 33;
        static final int TRANSACTION_setTouchCalibrationForInputDevice = 11;
        static final int TRANSACTION_tryPointerSpeed = 7;
        static final int TRANSACTION_unregisterSensorListener = 43;
        static final int TRANSACTION_unregisterVibratorStateListener = 30;
        static final int TRANSACTION_verifyInputEvent = 9;
        static final int TRANSACTION_vibrate = 24;
        static final int TRANSACTION_vibrateCombined = 25;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IInputManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IInputManager)) {
                return new Proxy(obj);
            }
            return (IInputManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getInputDevice";
                case 2:
                    return "getInputDeviceIds";
                case 3:
                    return "isInputDeviceEnabled";
                case 4:
                    return "enableInputDevice";
                case 5:
                    return "disableInputDevice";
                case 6:
                    return "hasKeys";
                case 7:
                    return "tryPointerSpeed";
                case 8:
                    return "injectInputEvent";
                case 9:
                    return "verifyInputEvent";
                case 10:
                    return "getTouchCalibrationForInputDevice";
                case 11:
                    return "setTouchCalibrationForInputDevice";
                case 12:
                    return "getKeyboardLayouts";
                case 13:
                    return "getKeyboardLayoutsForInputDevice";
                case 14:
                    return "getKeyboardLayout";
                case 15:
                    return "getCurrentKeyboardLayoutForInputDevice";
                case 16:
                    return "setCurrentKeyboardLayoutForInputDevice";
                case 17:
                    return "getEnabledKeyboardLayoutsForInputDevice";
                case 18:
                    return "addKeyboardLayoutForInputDevice";
                case 19:
                    return "removeKeyboardLayoutForInputDevice";
                case 20:
                    return "registerInputDevicesChangedListener";
                case 21:
                    return "isInTabletMode";
                case 22:
                    return "registerTabletModeChangedListener";
                case 23:
                    return "isMicMuted";
                case 24:
                    return "vibrate";
                case 25:
                    return "vibrateCombined";
                case 26:
                    return "cancelVibrate";
                case 27:
                    return "getVibratorIds";
                case 28:
                    return "isVibrating";
                case 29:
                    return "registerVibratorStateListener";
                case 30:
                    return "unregisterVibratorStateListener";
                case 31:
                    return "getBatteryStatus";
                case 32:
                    return "getBatteryCapacity";
                case 33:
                    return "setPointerIconType";
                case 34:
                    return "setCustomPointerIcon";
                case 35:
                    return "requestPointerCapture";
                case 36:
                    return "monitorGestureInput";
                case 37:
                    return "addPortAssociation";
                case 38:
                    return "removePortAssociation";
                case 39:
                    return "addUniqueIdAssociation";
                case 40:
                    return "removeUniqueIdAssociation";
                case 41:
                    return "getSensorList";
                case 42:
                    return "registerSensorListener";
                case 43:
                    return "unregisterSensorListener";
                case 44:
                    return "enableSensor";
                case 45:
                    return "disableSensor";
                case 46:
                    return "flushSensor";
                case 47:
                    return "getLights";
                case 48:
                    return "getLightState";
                case 49:
                    return "setLightStates";
                case 50:
                    return "openLightSession";
                case 51:
                    return "closeLightSession";
                default:
                    return null;
            }
        }

        @Override // android.os.Binder
        public String getTransactionName(int transactionCode) {
            return getDefaultTransactionName(transactionCode);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            boolean[] _arg3;
            InputEvent _arg0;
            InputEvent _arg02;
            TouchCalibration _arg2;
            InputDeviceIdentifier _arg03;
            InputDeviceIdentifier _arg04;
            InputDeviceIdentifier _arg05;
            InputDeviceIdentifier _arg06;
            InputDeviceIdentifier _arg07;
            InputDeviceIdentifier _arg08;
            VibrationEffect _arg1;
            CombinedVibration _arg12;
            PointerIcon _arg09;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg13 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg010 = data.readInt();
                            InputDevice _result = getInputDevice(_arg010);
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            int[] _result2 = getInputDeviceIds();
                            reply.writeNoException();
                            reply.writeIntArray(_result2);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg011 = data.readInt();
                            boolean isInputDeviceEnabled = isInputDeviceEnabled(_arg011);
                            reply.writeNoException();
                            reply.writeInt(isInputDeviceEnabled ? 1 : 0);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg012 = data.readInt();
                            enableInputDevice(_arg012);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg013 = data.readInt();
                            disableInputDevice(_arg013);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg014 = data.readInt();
                            int _arg14 = data.readInt();
                            int[] _arg22 = data.createIntArray();
                            int _arg3_length = data.readInt();
                            if (_arg3_length < 0) {
                                _arg3 = null;
                            } else {
                                _arg3 = new boolean[_arg3_length];
                            }
                            boolean hasKeys = hasKeys(_arg014, _arg14, _arg22, _arg3);
                            reply.writeNoException();
                            reply.writeInt(hasKeys ? 1 : 0);
                            reply.writeBooleanArray(_arg3);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg015 = data.readInt();
                            tryPointerSpeed(_arg015);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = InputEvent.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            int _arg15 = data.readInt();
                            boolean injectInputEvent = injectInputEvent(_arg0, _arg15);
                            reply.writeNoException();
                            reply.writeInt(injectInputEvent ? 1 : 0);
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = InputEvent.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            VerifiedInputEvent _result3 = verifyInputEvent(_arg02);
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg016 = data.readString();
                            int _arg16 = data.readInt();
                            TouchCalibration _result4 = getTouchCalibrationForInputDevice(_arg016, _arg16);
                            reply.writeNoException();
                            if (_result4 != null) {
                                reply.writeInt(1);
                                _result4.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg017 = data.readString();
                            int _arg17 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg2 = TouchCalibration.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            setTouchCalibrationForInputDevice(_arg017, _arg17, _arg2);
                            reply.writeNoException();
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            KeyboardLayout[] _result5 = getKeyboardLayouts();
                            reply.writeNoException();
                            reply.writeTypedArray(_result5, 1);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = InputDeviceIdentifier.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            KeyboardLayout[] _result6 = getKeyboardLayoutsForInputDevice(_arg03);
                            reply.writeNoException();
                            reply.writeTypedArray(_result6, 1);
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg018 = data.readString();
                            KeyboardLayout _result7 = getKeyboardLayout(_arg018);
                            reply.writeNoException();
                            if (_result7 != null) {
                                reply.writeInt(1);
                                _result7.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = InputDeviceIdentifier.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            String _result8 = getCurrentKeyboardLayoutForInputDevice(_arg04);
                            reply.writeNoException();
                            reply.writeString(_result8);
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = InputDeviceIdentifier.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            String _arg18 = data.readString();
                            setCurrentKeyboardLayoutForInputDevice(_arg05, _arg18);
                            reply.writeNoException();
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg06 = InputDeviceIdentifier.CREATOR.createFromParcel(data);
                            } else {
                                _arg06 = null;
                            }
                            String[] _result9 = getEnabledKeyboardLayoutsForInputDevice(_arg06);
                            reply.writeNoException();
                            reply.writeStringArray(_result9);
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg07 = InputDeviceIdentifier.CREATOR.createFromParcel(data);
                            } else {
                                _arg07 = null;
                            }
                            String _arg19 = data.readString();
                            addKeyboardLayoutForInputDevice(_arg07, _arg19);
                            reply.writeNoException();
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg08 = InputDeviceIdentifier.CREATOR.createFromParcel(data);
                            } else {
                                _arg08 = null;
                            }
                            String _arg110 = data.readString();
                            removeKeyboardLayoutForInputDevice(_arg08, _arg110);
                            reply.writeNoException();
                            return true;
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            IInputDevicesChangedListener _arg019 = IInputDevicesChangedListener.Stub.asInterface(data.readStrongBinder());
                            registerInputDevicesChangedListener(_arg019);
                            reply.writeNoException();
                            return true;
                        case 21:
                            data.enforceInterface(DESCRIPTOR);
                            int _result10 = isInTabletMode();
                            reply.writeNoException();
                            reply.writeInt(_result10);
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            ITabletModeChangedListener _arg020 = ITabletModeChangedListener.Stub.asInterface(data.readStrongBinder());
                            registerTabletModeChangedListener(_arg020);
                            reply.writeNoException();
                            return true;
                        case 23:
                            data.enforceInterface(DESCRIPTOR);
                            int _result11 = isMicMuted();
                            reply.writeNoException();
                            reply.writeInt(_result11);
                            return true;
                        case 24:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg021 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = VibrationEffect.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            IBinder _arg23 = data.readStrongBinder();
                            vibrate(_arg021, _arg1, _arg23);
                            reply.writeNoException();
                            return true;
                        case 25:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg022 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg12 = CombinedVibration.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            IBinder _arg24 = data.readStrongBinder();
                            vibrateCombined(_arg022, _arg12, _arg24);
                            reply.writeNoException();
                            return true;
                        case 26:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg023 = data.readInt();
                            IBinder _arg111 = data.readStrongBinder();
                            cancelVibrate(_arg023, _arg111);
                            reply.writeNoException();
                            return true;
                        case 27:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg024 = data.readInt();
                            int[] _result12 = getVibratorIds(_arg024);
                            reply.writeNoException();
                            reply.writeIntArray(_result12);
                            return true;
                        case 28:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg025 = data.readInt();
                            boolean isVibrating = isVibrating(_arg025);
                            reply.writeNoException();
                            reply.writeInt(isVibrating ? 1 : 0);
                            return true;
                        case 29:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg026 = data.readInt();
                            IVibratorStateListener _arg112 = IVibratorStateListener.Stub.asInterface(data.readStrongBinder());
                            boolean registerVibratorStateListener = registerVibratorStateListener(_arg026, _arg112);
                            reply.writeNoException();
                            reply.writeInt(registerVibratorStateListener ? 1 : 0);
                            return true;
                        case 30:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg027 = data.readInt();
                            IVibratorStateListener _arg113 = IVibratorStateListener.Stub.asInterface(data.readStrongBinder());
                            boolean unregisterVibratorStateListener = unregisterVibratorStateListener(_arg027, _arg113);
                            reply.writeNoException();
                            reply.writeInt(unregisterVibratorStateListener ? 1 : 0);
                            return true;
                        case 31:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg028 = data.readInt();
                            int _result13 = getBatteryStatus(_arg028);
                            reply.writeNoException();
                            reply.writeInt(_result13);
                            return true;
                        case 32:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg029 = data.readInt();
                            int _result14 = getBatteryCapacity(_arg029);
                            reply.writeNoException();
                            reply.writeInt(_result14);
                            return true;
                        case 33:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg030 = data.readInt();
                            setPointerIconType(_arg030);
                            reply.writeNoException();
                            return true;
                        case 34:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg09 = PointerIcon.CREATOR.createFromParcel(data);
                            } else {
                                _arg09 = null;
                            }
                            setCustomPointerIcon(_arg09);
                            reply.writeNoException();
                            return true;
                        case 35:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg031 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg13 = true;
                            }
                            requestPointerCapture(_arg031, _arg13);
                            return true;
                        case 36:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg032 = data.readString();
                            int _arg114 = data.readInt();
                            InputMonitor _result15 = monitorGestureInput(_arg032, _arg114);
                            reply.writeNoException();
                            if (_result15 != null) {
                                reply.writeInt(1);
                                _result15.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 37:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg033 = data.readString();
                            int _arg115 = data.readInt();
                            addPortAssociation(_arg033, _arg115);
                            reply.writeNoException();
                            return true;
                        case 38:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg034 = data.readString();
                            removePortAssociation(_arg034);
                            reply.writeNoException();
                            return true;
                        case 39:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg035 = data.readString();
                            String _arg116 = data.readString();
                            addUniqueIdAssociation(_arg035, _arg116);
                            reply.writeNoException();
                            return true;
                        case 40:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg036 = data.readString();
                            removeUniqueIdAssociation(_arg036);
                            reply.writeNoException();
                            return true;
                        case 41:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg037 = data.readInt();
                            InputSensorInfo[] _result16 = getSensorList(_arg037);
                            reply.writeNoException();
                            reply.writeTypedArray(_result16, 1);
                            return true;
                        case 42:
                            data.enforceInterface(DESCRIPTOR);
                            IInputSensorEventListener _arg038 = IInputSensorEventListener.Stub.asInterface(data.readStrongBinder());
                            boolean registerSensorListener = registerSensorListener(_arg038);
                            reply.writeNoException();
                            reply.writeInt(registerSensorListener ? 1 : 0);
                            return true;
                        case 43:
                            data.enforceInterface(DESCRIPTOR);
                            IInputSensorEventListener _arg039 = IInputSensorEventListener.Stub.asInterface(data.readStrongBinder());
                            unregisterSensorListener(_arg039);
                            reply.writeNoException();
                            return true;
                        case 44:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg040 = data.readInt();
                            int _arg117 = data.readInt();
                            int _arg25 = data.readInt();
                            boolean enableSensor = enableSensor(_arg040, _arg117, _arg25, data.readInt());
                            reply.writeNoException();
                            reply.writeInt(enableSensor ? 1 : 0);
                            return true;
                        case 45:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg041 = data.readInt();
                            int _arg118 = data.readInt();
                            disableSensor(_arg041, _arg118);
                            reply.writeNoException();
                            return true;
                        case 46:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg042 = data.readInt();
                            int _arg119 = data.readInt();
                            boolean flushSensor = flushSensor(_arg042, _arg119);
                            reply.writeNoException();
                            reply.writeInt(flushSensor ? 1 : 0);
                            return true;
                        case 47:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg043 = data.readInt();
                            List<Light> _result17 = getLights(_arg043);
                            reply.writeNoException();
                            reply.writeTypedList(_result17);
                            return true;
                        case 48:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg044 = data.readInt();
                            int _arg120 = data.readInt();
                            LightState _result18 = getLightState(_arg044, _arg120);
                            reply.writeNoException();
                            if (_result18 != null) {
                                reply.writeInt(1);
                                _result18.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 49:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg045 = data.readInt();
                            int[] _arg121 = data.createIntArray();
                            LightState[] _arg26 = (LightState[]) data.createTypedArray(LightState.CREATOR);
                            setLightStates(_arg045, _arg121, _arg26, data.readStrongBinder());
                            reply.writeNoException();
                            return true;
                        case 50:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg046 = data.readInt();
                            String _arg122 = data.readString();
                            IBinder _arg27 = data.readStrongBinder();
                            openLightSession(_arg046, _arg122, _arg27);
                            reply.writeNoException();
                            return true;
                        case 51:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg047 = data.readInt();
                            IBinder _arg123 = data.readStrongBinder();
                            closeLightSession(_arg047, _arg123);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IInputManager {
            public static IInputManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.hardware.input.IInputManager
            public InputDevice getInputDevice(int deviceId) throws RemoteException {
                InputDevice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInputDevice(deviceId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = InputDevice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public int[] getInputDeviceIds() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInputDeviceIds();
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public boolean isInputDeviceEnabled(int deviceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isInputDeviceEnabled(deviceId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void enableInputDevice(int deviceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().enableInputDevice(deviceId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void disableInputDevice(int deviceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().disableInputDevice(deviceId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public boolean hasKeys(int deviceId, int sourceMask, int[] keyCodes, boolean[] keyExists) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    _data.writeInt(sourceMask);
                    _data.writeIntArray(keyCodes);
                    if (keyExists == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(keyExists.length);
                    }
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasKeys(deviceId, sourceMask, keyCodes, keyExists);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    _reply.readBooleanArray(keyExists);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void tryPointerSpeed(int speed) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(speed);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().tryPointerSpeed(speed);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public boolean injectInputEvent(InputEvent ev, int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    if (ev != null) {
                        _data.writeInt(1);
                        ev.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().injectInputEvent(ev, mode);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public VerifiedInputEvent verifyInputEvent(InputEvent ev) throws RemoteException {
                VerifiedInputEvent _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (ev != null) {
                        _data.writeInt(1);
                        ev.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().verifyInputEvent(ev);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = VerifiedInputEvent.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public TouchCalibration getTouchCalibrationForInputDevice(String inputDeviceDescriptor, int rotation) throws RemoteException {
                TouchCalibration _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(inputDeviceDescriptor);
                    _data.writeInt(rotation);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTouchCalibrationForInputDevice(inputDeviceDescriptor, rotation);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = TouchCalibration.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void setTouchCalibrationForInputDevice(String inputDeviceDescriptor, int rotation, TouchCalibration calibration) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(inputDeviceDescriptor);
                    _data.writeInt(rotation);
                    if (calibration != null) {
                        _data.writeInt(1);
                        calibration.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setTouchCalibrationForInputDevice(inputDeviceDescriptor, rotation, calibration);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public KeyboardLayout[] getKeyboardLayouts() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getKeyboardLayouts();
                    }
                    _reply.readException();
                    KeyboardLayout[] _result = (KeyboardLayout[]) _reply.createTypedArray(KeyboardLayout.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public KeyboardLayout[] getKeyboardLayoutsForInputDevice(InputDeviceIdentifier identifier) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (identifier != null) {
                        _data.writeInt(1);
                        identifier.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getKeyboardLayoutsForInputDevice(identifier);
                    }
                    _reply.readException();
                    KeyboardLayout[] _result = (KeyboardLayout[]) _reply.createTypedArray(KeyboardLayout.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public KeyboardLayout getKeyboardLayout(String keyboardLayoutDescriptor) throws RemoteException {
                KeyboardLayout _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(keyboardLayoutDescriptor);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getKeyboardLayout(keyboardLayoutDescriptor);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = KeyboardLayout.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public String getCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier identifier) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (identifier != null) {
                        _data.writeInt(1);
                        identifier.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentKeyboardLayoutForInputDevice(identifier);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void setCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier identifier, String keyboardLayoutDescriptor) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (identifier != null) {
                        _data.writeInt(1);
                        identifier.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(keyboardLayoutDescriptor);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setCurrentKeyboardLayoutForInputDevice(identifier, keyboardLayoutDescriptor);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public String[] getEnabledKeyboardLayoutsForInputDevice(InputDeviceIdentifier identifier) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (identifier != null) {
                        _data.writeInt(1);
                        identifier.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEnabledKeyboardLayoutsForInputDevice(identifier);
                    }
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void addKeyboardLayoutForInputDevice(InputDeviceIdentifier identifier, String keyboardLayoutDescriptor) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (identifier != null) {
                        _data.writeInt(1);
                        identifier.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(keyboardLayoutDescriptor);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addKeyboardLayoutForInputDevice(identifier, keyboardLayoutDescriptor);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void removeKeyboardLayoutForInputDevice(InputDeviceIdentifier identifier, String keyboardLayoutDescriptor) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (identifier != null) {
                        _data.writeInt(1);
                        identifier.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(keyboardLayoutDescriptor);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeKeyboardLayoutForInputDevice(identifier, keyboardLayoutDescriptor);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void registerInputDevicesChangedListener(IInputDevicesChangedListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerInputDevicesChangedListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public int isInTabletMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isInTabletMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void registerTabletModeChangedListener(ITabletModeChangedListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerTabletModeChangedListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public int isMicMuted() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isMicMuted();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void vibrate(int deviceId, VibrationEffect effect, IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    if (effect != null) {
                        _data.writeInt(1);
                        effect.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().vibrate(deviceId, effect, token);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void vibrateCombined(int deviceId, CombinedVibration vibration, IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    if (vibration != null) {
                        _data.writeInt(1);
                        vibration.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().vibrateCombined(deviceId, vibration, token);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void cancelVibrate(int deviceId, IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().cancelVibrate(deviceId, token);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public int[] getVibratorIds(int deviceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVibratorIds(deviceId);
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public boolean isVibrating(int deviceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(28, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isVibrating(deviceId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public boolean registerVibratorStateListener(int deviceId, IVibratorStateListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerVibratorStateListener(deviceId, listener);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public boolean unregisterVibratorStateListener(int deviceId, IVibratorStateListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(30, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterVibratorStateListener(deviceId, listener);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public int getBatteryStatus(int deviceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    boolean _status = this.mRemote.transact(31, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBatteryStatus(deviceId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public int getBatteryCapacity(int deviceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBatteryCapacity(deviceId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void setPointerIconType(int typeId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(typeId);
                    boolean _status = this.mRemote.transact(33, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setPointerIconType(typeId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void setCustomPointerIcon(PointerIcon icon) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (icon != null) {
                        _data.writeInt(1);
                        icon.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(34, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setCustomPointerIcon(icon);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void requestPointerCapture(IBinder inputChannelToken, boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(inputChannelToken);
                    _data.writeInt(enabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(35, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestPointerCapture(inputChannelToken, enabled);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public InputMonitor monitorGestureInput(String name, int displayId) throws RemoteException {
                InputMonitor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(36, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().monitorGestureInput(name, displayId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = InputMonitor.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void addPortAssociation(String inputPort, int displayPort) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(inputPort);
                    _data.writeInt(displayPort);
                    boolean _status = this.mRemote.transact(37, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addPortAssociation(inputPort, displayPort);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void removePortAssociation(String inputPort) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(inputPort);
                    boolean _status = this.mRemote.transact(38, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removePortAssociation(inputPort);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void addUniqueIdAssociation(String inputDeviceName, String displayUniqueId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(inputDeviceName);
                    _data.writeString(displayUniqueId);
                    boolean _status = this.mRemote.transact(39, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addUniqueIdAssociation(inputDeviceName, displayUniqueId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void removeUniqueIdAssociation(String inputDeviceName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(inputDeviceName);
                    boolean _status = this.mRemote.transact(40, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeUniqueIdAssociation(inputDeviceName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public InputSensorInfo[] getSensorList(int deviceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    boolean _status = this.mRemote.transact(41, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSensorList(deviceId);
                    }
                    _reply.readException();
                    InputSensorInfo[] _result = (InputSensorInfo[]) _reply.createTypedArray(InputSensorInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public boolean registerSensorListener(IInputSensorEventListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(42, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerSensorListener(listener);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void unregisterSensorListener(IInputSensorEventListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(43, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterSensorListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public boolean enableSensor(int deviceId, int sensorType, int samplingPeriodUs, int maxBatchReportLatencyUs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    _data.writeInt(sensorType);
                    _data.writeInt(samplingPeriodUs);
                    _data.writeInt(maxBatchReportLatencyUs);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(44, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enableSensor(deviceId, sensorType, samplingPeriodUs, maxBatchReportLatencyUs);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void disableSensor(int deviceId, int sensorType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    _data.writeInt(sensorType);
                    boolean _status = this.mRemote.transact(45, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().disableSensor(deviceId, sensorType);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public boolean flushSensor(int deviceId, int sensorType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    _data.writeInt(sensorType);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(46, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().flushSensor(deviceId, sensorType);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public List<Light> getLights(int deviceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    boolean _status = this.mRemote.transact(47, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLights(deviceId);
                    }
                    _reply.readException();
                    List<Light> _result = _reply.createTypedArrayList(Light.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public LightState getLightState(int deviceId, int lightId) throws RemoteException {
                LightState _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    _data.writeInt(lightId);
                    boolean _status = this.mRemote.transact(48, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLightState(deviceId, lightId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = LightState.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void setLightStates(int deviceId, int[] lightIds, LightState[] states, IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    _data.writeIntArray(lightIds);
                    _data.writeTypedArray(states, 0);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(49, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setLightStates(deviceId, lightIds, states, token);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void openLightSession(int deviceId, String opPkg, IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    _data.writeString(opPkg);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(50, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().openLightSession(deviceId, opPkg, token);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.input.IInputManager
            public void closeLightSession(int deviceId, IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(51, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().closeLightSession(deviceId, token);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInputManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IInputManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
