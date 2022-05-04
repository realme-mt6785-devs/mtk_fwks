package vendor.oplus.hardware.handlefactory.V1_0;

import android.hardware.radio.V1_6.QosFlowIdRange;
import android.hardware.radio.V1_6.QosProtocol;
import android.internal.hidl.base.V1_0.DebugInfo;
import android.internal.hidl.base.V1_0.IBase;
import android.os.HidlSupport;
import android.os.HwBinder;
import android.os.HwBlob;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.NativeHandle;
import android.os.RemoteException;
import com.android.internal.midi.MidiConstants;
import com.android.internal.telephony.GsmAlphabet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
/* loaded from: classes4.dex */
public interface IHandleFactory extends IBase {
    public static final String kInterfaceName = "vendor.oplus.hardware.handlefactory@1.0::IHandleFactory";

    @FunctionalInterface
    /* loaded from: classes4.dex */
    public interface ioctrlCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    /* loaded from: classes4.dex */
    public interface readCallback {
        void onValues(int i, String str);
    }

    @Override // android.internal.hidl.base.V1_0.IBase, android.os.IHwInterface
    IHwBinder asBinder();

    @Override // android.internal.hidl.base.V1_0.IBase
    void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) throws RemoteException;

    @Override // android.internal.hidl.base.V1_0.IBase
    DebugInfo getDebugInfo() throws RemoteException;

    NativeHandle getHandle(String str, int i) throws RemoteException;

    @Override // android.internal.hidl.base.V1_0.IBase
    ArrayList<byte[]> getHashChain() throws RemoteException;

    @Override // android.internal.hidl.base.V1_0.IBase
    ArrayList<String> interfaceChain() throws RemoteException;

    @Override // android.internal.hidl.base.V1_0.IBase
    String interfaceDescriptor() throws RemoteException;

    void ioctrl(String str, int i, int i2, int i3, ioctrlCallback ioctrlcallback) throws RemoteException;

    @Override // android.internal.hidl.base.V1_0.IBase
    boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) throws RemoteException;

    @Override // android.internal.hidl.base.V1_0.IBase
    void notifySyspropsChanged() throws RemoteException;

    @Override // android.internal.hidl.base.V1_0.IBase
    void ping() throws RemoteException;

    void read(String str, int i, readCallback readcallback) throws RemoteException;

    @Override // android.internal.hidl.base.V1_0.IBase
    void setHALInstrumentation() throws RemoteException;

    @Override // android.internal.hidl.base.V1_0.IBase
    boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) throws RemoteException;

    int write(String str, int i, String str2) throws RemoteException;

    static IHandleFactory asInterface(IHwBinder binder) {
        if (binder == null) {
            return null;
        }
        IHwInterface iface = binder.queryLocalInterface(kInterfaceName);
        if (iface != null && (iface instanceof IHandleFactory)) {
            return (IHandleFactory) iface;
        }
        IHandleFactory proxy = new Proxy(binder);
        try {
            Iterator<String> it = proxy.interfaceChain().iterator();
            while (it.hasNext()) {
                String descriptor = it.next();
                if (descriptor.equals(kInterfaceName)) {
                    return proxy;
                }
            }
        } catch (RemoteException e) {
        }
        return null;
    }

    static IHandleFactory castFrom(IHwInterface iface) {
        if (iface == null) {
            return null;
        }
        return asInterface(iface.asBinder());
    }

    static IHandleFactory getService(String serviceName, boolean retry) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, serviceName, retry));
    }

    static IHandleFactory getService(boolean retry) throws RemoteException {
        return getService("default", retry);
    }

    @Deprecated
    static IHandleFactory getService(String serviceName) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, serviceName));
    }

    @Deprecated
    static IHandleFactory getService() throws RemoteException {
        return getService("default");
    }

    /* loaded from: classes4.dex */
    public static final class Proxy implements IHandleFactory {
        private IHwBinder mRemote;

        public Proxy(IHwBinder remote) {
            Objects.requireNonNull(remote);
            this.mRemote = remote;
        }

        @Override // vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory, android.internal.hidl.base.V1_0.IBase, android.os.IHwInterface
        public IHwBinder asBinder() {
            return this.mRemote;
        }

        public String toString() {
            try {
                return interfaceDescriptor() + "@Proxy";
            } catch (RemoteException e) {
                return "[class or subclass of vendor.oplus.hardware.handlefactory@1.0::IHandleFactory]@Proxy";
            }
        }

        public final boolean equals(Object other) {
            return HidlSupport.interfacesEqual(this, other);
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory
        public NativeHandle getHandle(String name, int flags) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IHandleFactory.kInterfaceName);
            _hidl_request.writeString(name);
            _hidl_request.writeInt32(flags);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(1, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                NativeHandle _hidl_out_h = _hidl_reply.readNativeHandle();
                return _hidl_out_h;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory
        public void ioctrl(String name, int flags, int cmd, int param, ioctrlCallback _hidl_cb) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IHandleFactory.kInterfaceName);
            _hidl_request.writeString(name);
            _hidl_request.writeInt32(flags);
            _hidl_request.writeInt32(cmd);
            _hidl_request.writeInt32(param);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(2, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_result = _hidl_reply.readInt32();
                int _hidl_out_param = _hidl_reply.readInt32();
                _hidl_cb.onValues(_hidl_out_result, _hidl_out_param);
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory
        public void read(String name, int flags, readCallback _hidl_cb) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IHandleFactory.kInterfaceName);
            _hidl_request.writeString(name);
            _hidl_request.writeInt32(flags);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(3, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_result = _hidl_reply.readInt32();
                String _hidl_out_data = _hidl_reply.readString();
                _hidl_cb.onValues(_hidl_out_result, _hidl_out_data);
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory
        public int write(String name, int flags, String data) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IHandleFactory.kInterfaceName);
            _hidl_request.writeString(name);
            _hidl_request.writeInt32(flags);
            _hidl_request.writeString(data);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(4, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_result = _hidl_reply.readInt32();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory, android.internal.hidl.base.V1_0.IBase
        public ArrayList<String> interfaceChain() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256067662, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                ArrayList<String> _hidl_out_descriptors = _hidl_reply.readStringVector();
                return _hidl_out_descriptors;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory, android.internal.hidl.base.V1_0.IBase
        public void debug(NativeHandle fd, ArrayList<String> options) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            _hidl_request.writeNativeHandle(fd);
            _hidl_request.writeStringVector(options);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256131655, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory, android.internal.hidl.base.V1_0.IBase
        public String interfaceDescriptor() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256136003, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                String _hidl_out_descriptor = _hidl_reply.readString();
                return _hidl_out_descriptor;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory, android.internal.hidl.base.V1_0.IBase
        public ArrayList<byte[]> getHashChain() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256398152, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                ArrayList<byte[]> _hidl_out_hashchain = new ArrayList<>();
                HwBlob _hidl_blob = _hidl_reply.readBuffer(16L);
                int _hidl_vec_size = _hidl_blob.getInt32(8L);
                HwBlob childBlob = _hidl_reply.readEmbeddedBuffer(_hidl_vec_size * 32, _hidl_blob.handle(), 0L, true);
                _hidl_out_hashchain.clear();
                for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
                    byte[] _hidl_vec_element = new byte[32];
                    long _hidl_array_offset_1 = _hidl_index_0 * 32;
                    childBlob.copyToInt8Array(_hidl_array_offset_1, _hidl_vec_element, 32);
                    _hidl_out_hashchain.add(_hidl_vec_element);
                }
                return _hidl_out_hashchain;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory, android.internal.hidl.base.V1_0.IBase
        public void setHALInstrumentation() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256462420, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory, android.internal.hidl.base.V1_0.IBase
        public boolean linkToDeath(IHwBinder.DeathRecipient recipient, long cookie) throws RemoteException {
            return this.mRemote.linkToDeath(recipient, cookie);
        }

        @Override // vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory, android.internal.hidl.base.V1_0.IBase
        public void ping() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256921159, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory, android.internal.hidl.base.V1_0.IBase
        public DebugInfo getDebugInfo() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(257049926, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                DebugInfo _hidl_out_info = new DebugInfo();
                _hidl_out_info.readFromParcel(_hidl_reply);
                return _hidl_out_info;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory, android.internal.hidl.base.V1_0.IBase
        public void notifySyspropsChanged() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(257120595, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory, android.internal.hidl.base.V1_0.IBase
        public boolean unlinkToDeath(IHwBinder.DeathRecipient recipient) throws RemoteException {
            return this.mRemote.unlinkToDeath(recipient);
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends HwBinder implements IHandleFactory {
        @Override // vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory, android.internal.hidl.base.V1_0.IBase, android.os.IHwInterface
        public IHwBinder asBinder() {
            return this;
        }

        @Override // vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory, android.internal.hidl.base.V1_0.IBase
        public final ArrayList<String> interfaceChain() {
            return new ArrayList<>(Arrays.asList(IHandleFactory.kInterfaceName, IBase.kInterfaceName));
        }

        @Override // vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory, android.internal.hidl.base.V1_0.IBase
        public void debug(NativeHandle fd, ArrayList<String> options) {
        }

        @Override // vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory, android.internal.hidl.base.V1_0.IBase
        public final String interfaceDescriptor() {
            return IHandleFactory.kInterfaceName;
        }

        @Override // vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory, android.internal.hidl.base.V1_0.IBase
        public final ArrayList<byte[]> getHashChain() {
            return new ArrayList<>(Arrays.asList(new byte[]{40, GsmAlphabet.GSM_EXTENDED_ESCAPE, QosProtocol.AH, -100, -46, -4, 67, -73, 30, 82, 6, -105, -41, -126, 121, Byte.MAX_VALUE, -78, 30, -86, -109, QosFlowIdRange.MAX, -34, 90, -94, 110, -92, 125, -42, -120, 77, 79, -30}, new byte[]{-20, Byte.MAX_VALUE, -41, -98, MidiConstants.STATUS_CHANNEL_PRESSURE, 45, -6, -123, -68, 73, -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, MidiConstants.STATUS_SONG_SELECT, -51, 105, 87, 19, -109, 36, -72, 59, 24, -54, 76}));
        }

        @Override // vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory, android.internal.hidl.base.V1_0.IBase
        public final void setHALInstrumentation() {
        }

        @Override // android.os.IHwBinder, android.hardware.cas.V1_0.ICas, android.internal.hidl.base.V1_0.IBase
        public final boolean linkToDeath(IHwBinder.DeathRecipient recipient, long cookie) {
            return true;
        }

        @Override // vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory, android.internal.hidl.base.V1_0.IBase
        public final void ping() {
        }

        @Override // vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory, android.internal.hidl.base.V1_0.IBase
        public final DebugInfo getDebugInfo() {
            DebugInfo info = new DebugInfo();
            info.pid = HidlSupport.getPidIfSharable();
            info.ptr = 0L;
            info.arch = 0;
            return info;
        }

        @Override // vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory, android.internal.hidl.base.V1_0.IBase
        public final void notifySyspropsChanged() {
            HwBinder.enableInstrumentation();
        }

        @Override // android.os.IHwBinder, android.hardware.cas.V1_0.ICas, android.internal.hidl.base.V1_0.IBase
        public final boolean unlinkToDeath(IHwBinder.DeathRecipient recipient) {
            return true;
        }

        @Override // android.os.IHwBinder
        public IHwInterface queryLocalInterface(String descriptor) {
            if (IHandleFactory.kInterfaceName.equals(descriptor)) {
                return this;
            }
            return null;
        }

        public void registerAsService(String serviceName) throws RemoteException {
            registerService(serviceName);
        }

        public String toString() {
            return interfaceDescriptor() + "@Stub";
        }

        @Override // android.os.HwBinder
        public void onTransact(int _hidl_code, HwParcel _hidl_request, final HwParcel _hidl_reply, int _hidl_flags) throws RemoteException {
            switch (_hidl_code) {
                case 1:
                    _hidl_request.enforceInterface(IHandleFactory.kInterfaceName);
                    String name = _hidl_request.readString();
                    int flags = _hidl_request.readInt32();
                    NativeHandle _hidl_out_h = getHandle(name, flags);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeNativeHandle(_hidl_out_h);
                    _hidl_reply.send();
                    return;
                case 2:
                    _hidl_request.enforceInterface(IHandleFactory.kInterfaceName);
                    String name2 = _hidl_request.readString();
                    int flags2 = _hidl_request.readInt32();
                    int cmd = _hidl_request.readInt32();
                    int param = _hidl_request.readInt32();
                    ioctrl(name2, flags2, cmd, param, new ioctrlCallback() { // from class: vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory.Stub.1
                        @Override // vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory.ioctrlCallback
                        public void onValues(int result, int param2) {
                            _hidl_reply.writeStatus(0);
                            _hidl_reply.writeInt32(result);
                            _hidl_reply.writeInt32(param2);
                            _hidl_reply.send();
                        }
                    });
                    return;
                case 3:
                    _hidl_request.enforceInterface(IHandleFactory.kInterfaceName);
                    String name3 = _hidl_request.readString();
                    int flags3 = _hidl_request.readInt32();
                    read(name3, flags3, new readCallback() { // from class: vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory.Stub.2
                        @Override // vendor.oplus.hardware.handlefactory.V1_0.IHandleFactory.readCallback
                        public void onValues(int result, String data) {
                            _hidl_reply.writeStatus(0);
                            _hidl_reply.writeInt32(result);
                            _hidl_reply.writeString(data);
                            _hidl_reply.send();
                        }
                    });
                    return;
                case 4:
                    _hidl_request.enforceInterface(IHandleFactory.kInterfaceName);
                    String name4 = _hidl_request.readString();
                    int flags4 = _hidl_request.readInt32();
                    String data = _hidl_request.readString();
                    int _hidl_out_result = write(name4, flags4, data);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_result);
                    _hidl_reply.send();
                    return;
                case 256067662:
                    _hidl_request.enforceInterface(IBase.kInterfaceName);
                    ArrayList<String> _hidl_out_descriptors = interfaceChain();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeStringVector(_hidl_out_descriptors);
                    _hidl_reply.send();
                    return;
                case 256131655:
                    _hidl_request.enforceInterface(IBase.kInterfaceName);
                    NativeHandle fd = _hidl_request.readNativeHandle();
                    ArrayList<String> options = _hidl_request.readStringVector();
                    debug(fd, options);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.send();
                    return;
                case 256136003:
                    _hidl_request.enforceInterface(IBase.kInterfaceName);
                    String _hidl_out_descriptor = interfaceDescriptor();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeString(_hidl_out_descriptor);
                    _hidl_reply.send();
                    return;
                case 256398152:
                    _hidl_request.enforceInterface(IBase.kInterfaceName);
                    ArrayList<byte[]> _hidl_out_hashchain = getHashChain();
                    _hidl_reply.writeStatus(0);
                    HwBlob _hidl_blob = new HwBlob(16);
                    int _hidl_vec_size = _hidl_out_hashchain.size();
                    _hidl_blob.putInt32(8L, _hidl_vec_size);
                    _hidl_blob.putBool(12L, false);
                    HwBlob childBlob = new HwBlob(_hidl_vec_size * 32);
                    for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
                        long _hidl_array_offset_1 = _hidl_index_0 * 32;
                        byte[] _hidl_array_item_1 = _hidl_out_hashchain.get(_hidl_index_0);
                        if (_hidl_array_item_1 == null || _hidl_array_item_1.length != 32) {
                            throw new IllegalArgumentException("Array element is not of the expected length");
                        }
                        childBlob.putInt8Array(_hidl_array_offset_1, _hidl_array_item_1);
                    }
                    _hidl_blob.putBlob(0L, childBlob);
                    _hidl_reply.writeBuffer(_hidl_blob);
                    _hidl_reply.send();
                    return;
                case 256462420:
                    _hidl_request.enforceInterface(IBase.kInterfaceName);
                    setHALInstrumentation();
                    return;
                case 256660548:
                default:
                    return;
                case 256921159:
                    _hidl_request.enforceInterface(IBase.kInterfaceName);
                    ping();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.send();
                    return;
                case 257049926:
                    _hidl_request.enforceInterface(IBase.kInterfaceName);
                    DebugInfo _hidl_out_info = getDebugInfo();
                    _hidl_reply.writeStatus(0);
                    _hidl_out_info.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 257120595:
                    _hidl_request.enforceInterface(IBase.kInterfaceName);
                    notifySyspropsChanged();
                    return;
            }
        }
    }
}
