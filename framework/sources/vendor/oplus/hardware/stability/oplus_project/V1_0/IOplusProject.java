package vendor.oplus.hardware.stability.oplus_project.V1_0;

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
public interface IOplusProject extends IBase {
    public static final String kInterfaceName = "vendor.oplus.hardware.stability.oplus_project@1.0::IOplusProject";

    @Override // android.internal.hidl.base.V1_0.IBase, android.os.IHwInterface
    IHwBinder asBinder();

    @Override // android.internal.hidl.base.V1_0.IBase
    void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) throws RemoteException;

    @Override // android.internal.hidl.base.V1_0.IBase
    DebugInfo getDebugInfo() throws RemoteException;

    @Override // android.internal.hidl.base.V1_0.IBase
    ArrayList<byte[]> getHashChain() throws RemoteException;

    int get_eng_version() throws RemoteException;

    String get_force_reboot() throws RemoteException;

    int get_ftmmode() throws RemoteException;

    String get_hungtask() throws RemoteException;

    String get_mt_gpio() throws RemoteException;

    String get_ocp() throws RemoteException;

    String get_opboot() throws RemoteException;

    int get_operator_name() throws RemoteException;

    String get_oplusocp_status(int i) throws RemoteException;

    String get_opluspoff_reason(int i) throws RemoteException;

    String get_opluspon_reason(int i) throws RemoteException;

    int get_pcb_version() throws RemoteException;

    String get_phoenix() throws RemoteException;

    String get_poff_reason() throws RemoteException;

    String get_pon_reason() throws RemoteException;

    int get_project() throws RemoteException;

    int get_rf_type() throws RemoteException;

    int get_sau() throws RemoteException;

    String get_serial_ID() throws RemoteException;

    String get_shutdown_detect() throws RemoteException;

    @Override // android.internal.hidl.base.V1_0.IBase
    ArrayList<String> interfaceChain() throws RemoteException;

    @Override // android.internal.hidl.base.V1_0.IBase
    String interfaceDescriptor() throws RemoteException;

    boolean is_ufs_devices() throws RemoteException;

    @Override // android.internal.hidl.base.V1_0.IBase
    boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) throws RemoteException;

    String miscModule2Load() throws RemoteException;

    String miscModuleBlocklist() throws RemoteException;

    String miscReadLinkedModules() throws RemoteException;

    @Override // android.internal.hidl.base.V1_0.IBase
    void notifySyspropsChanged() throws RemoteException;

    @Override // android.internal.hidl.base.V1_0.IBase
    void ping() throws RemoteException;

    ArrayList<Byte> readAgingData(int i) throws RemoteException;

    String read_theia_node(int i) throws RemoteException;

    boolean saveAgingData(int i, ArrayList<Byte> arrayList, int i2) throws RemoteException;

    @Override // android.internal.hidl.base.V1_0.IBase
    void setHALInstrumentation() throws RemoteException;

    boolean set_opboot(String str) throws RemoteException;

    boolean set_phoenix(String str) throws RemoteException;

    boolean set_shutdown_detect(String str) throws RemoteException;

    @Override // android.internal.hidl.base.V1_0.IBase
    boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) throws RemoteException;

    boolean write_theia_node(int i, String str) throws RemoteException;

    static IOplusProject asInterface(IHwBinder binder) {
        if (binder == null) {
            return null;
        }
        IHwInterface iface = binder.queryLocalInterface(kInterfaceName);
        if (iface != null && (iface instanceof IOplusProject)) {
            return (IOplusProject) iface;
        }
        IOplusProject proxy = new Proxy(binder);
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

    static IOplusProject castFrom(IHwInterface iface) {
        if (iface == null) {
            return null;
        }
        return asInterface(iface.asBinder());
    }

    static IOplusProject getService(String serviceName, boolean retry) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, serviceName, retry));
    }

    static IOplusProject getService(boolean retry) throws RemoteException {
        return getService("default", retry);
    }

    @Deprecated
    static IOplusProject getService(String serviceName) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, serviceName));
    }

    @Deprecated
    static IOplusProject getService() throws RemoteException {
        return getService("default");
    }

    /* loaded from: classes4.dex */
    public static final class Proxy implements IOplusProject {
        private IHwBinder mRemote;

        public Proxy(IHwBinder remote) {
            Objects.requireNonNull(remote);
            this.mRemote = remote;
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject, android.internal.hidl.base.V1_0.IBase, android.os.IHwInterface
        public IHwBinder asBinder() {
            return this.mRemote;
        }

        public String toString() {
            try {
                return interfaceDescriptor() + "@Proxy";
            } catch (RemoteException e) {
                return "[class or subclass of vendor.oplus.hardware.stability.oplus_project@1.0::IOplusProject]@Proxy";
            }
        }

        public final boolean equals(Object other) {
            return HidlSupport.interfacesEqual(this, other);
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public int get_project() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(1, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_result = _hidl_reply.readInt32();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public int get_pcb_version() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(2, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_result = _hidl_reply.readInt32();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public String get_serial_ID() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(3, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                String _hidl_out_result = _hidl_reply.readString();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public int get_operator_name() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
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

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public int get_rf_type() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(5, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_result = _hidl_reply.readInt32();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public int get_eng_version() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(6, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_result = _hidl_reply.readInt32();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public String get_hungtask() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(7, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                String _hidl_out_result = _hidl_reply.readString();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public String get_force_reboot() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(8, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                String _hidl_out_result = _hidl_reply.readString();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public int get_ftmmode() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(9, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_result = _hidl_reply.readInt32();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public String get_mt_gpio() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(10, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                String _hidl_out_result = _hidl_reply.readString();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public String get_opboot() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(11, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                String _hidl_out_result = _hidl_reply.readString();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public boolean set_opboot(String opboot) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            _hidl_request.writeString(opboot);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(12, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                boolean _hidl_out_success = _hidl_reply.readBool();
                return _hidl_out_success;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public String get_opluspoff_reason(int pmic_id) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            _hidl_request.writeInt32(pmic_id);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(13, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                String _hidl_out_result = _hidl_reply.readString();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public String get_opluspon_reason(int pmic_id) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            _hidl_request.writeInt32(pmic_id);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(14, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                String _hidl_out_result = _hidl_reply.readString();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public String get_oplusocp_status(int pmic_id) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            _hidl_request.writeInt32(pmic_id);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(15, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                String _hidl_out_result = _hidl_reply.readString();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public String get_phoenix() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(16, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                String _hidl_out_result = _hidl_reply.readString();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public boolean set_phoenix(String monitoting_command) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            _hidl_request.writeString(monitoting_command);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(17, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                boolean _hidl_out_success = _hidl_reply.readBool();
                return _hidl_out_success;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public String get_shutdown_detect() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(18, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                String _hidl_out_result = _hidl_reply.readString();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public boolean set_shutdown_detect(String shutdown_detect_vaule) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            _hidl_request.writeString(shutdown_detect_vaule);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(19, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                boolean _hidl_out_success = _hidl_reply.readBool();
                return _hidl_out_success;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public String get_poff_reason() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(20, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                String _hidl_out_result = _hidl_reply.readString();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public String get_pon_reason() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(21, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                String _hidl_out_result = _hidl_reply.readString();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public String get_ocp() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(22, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                String _hidl_out_result = _hidl_reply.readString();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public int get_sau() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(23, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_result = _hidl_reply.readInt32();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public boolean is_ufs_devices() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(24, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                boolean _hidl_out_success = _hidl_reply.readBool();
                return _hidl_out_success;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public String read_theia_node(int theia_node_id) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            _hidl_request.writeInt32(theia_node_id);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(25, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                String _hidl_out_theia_data = _hidl_reply.readString();
                return _hidl_out_theia_data;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public boolean write_theia_node(int theia_node_id, String theia_data) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            _hidl_request.writeInt32(theia_node_id);
            _hidl_request.writeString(theia_data);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(26, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                boolean _hidl_out_success = _hidl_reply.readBool();
                return _hidl_out_success;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public ArrayList<Byte> readAgingData(int type) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            _hidl_request.writeInt32(type);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(27, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                ArrayList<Byte> _hidl_out_data = _hidl_reply.readInt8Vector();
                return _hidl_out_data;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public boolean saveAgingData(int type, ArrayList<Byte> saveAgingData, int length) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            _hidl_request.writeInt32(type);
            _hidl_request.writeInt8Vector(saveAgingData);
            _hidl_request.writeInt32(length);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(28, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                boolean _hidl_out_data = _hidl_reply.readBool();
                return _hidl_out_data;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public String miscReadLinkedModules() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(29, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                String _hidl_out_result = _hidl_reply.readString();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public String miscModule2Load() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(30, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                String _hidl_out_result = _hidl_reply.readString();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject
        public String miscModuleBlocklist() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IOplusProject.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(31, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                String _hidl_out_result = _hidl_reply.readString();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject, android.internal.hidl.base.V1_0.IBase
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

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject, android.internal.hidl.base.V1_0.IBase
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

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject, android.internal.hidl.base.V1_0.IBase
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

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject, android.internal.hidl.base.V1_0.IBase
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

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject, android.internal.hidl.base.V1_0.IBase
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

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject, android.internal.hidl.base.V1_0.IBase
        public boolean linkToDeath(IHwBinder.DeathRecipient recipient, long cookie) throws RemoteException {
            return this.mRemote.linkToDeath(recipient, cookie);
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject, android.internal.hidl.base.V1_0.IBase
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

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject, android.internal.hidl.base.V1_0.IBase
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

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject, android.internal.hidl.base.V1_0.IBase
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

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject, android.internal.hidl.base.V1_0.IBase
        public boolean unlinkToDeath(IHwBinder.DeathRecipient recipient) throws RemoteException {
            return this.mRemote.unlinkToDeath(recipient);
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends HwBinder implements IOplusProject {
        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject, android.internal.hidl.base.V1_0.IBase, android.os.IHwInterface
        public IHwBinder asBinder() {
            return this;
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject, android.internal.hidl.base.V1_0.IBase
        public final ArrayList<String> interfaceChain() {
            return new ArrayList<>(Arrays.asList(IOplusProject.kInterfaceName, IBase.kInterfaceName));
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject, android.internal.hidl.base.V1_0.IBase
        public void debug(NativeHandle fd, ArrayList<String> options) {
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject, android.internal.hidl.base.V1_0.IBase
        public final String interfaceDescriptor() {
            return IOplusProject.kInterfaceName;
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject, android.internal.hidl.base.V1_0.IBase
        public final ArrayList<byte[]> getHashChain() {
            return new ArrayList<>(Arrays.asList(new byte[]{-51, 39, 26, 70, 33, 29, -122, -78, 49, 105, 5, -43, -115, -87, -104, 78, 96, -113, 91, QosProtocol.UDP, -87, -22, -53, -43, -114, 59, -5, MidiConstants.STATUS_NOTE_ON, -93, 7, GsmAlphabet.GSM_EXTENDED_ESCAPE, -24}, new byte[]{-20, Byte.MAX_VALUE, -41, -98, MidiConstants.STATUS_CHANNEL_PRESSURE, 45, -6, -123, -68, 73, -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, MidiConstants.STATUS_SONG_SELECT, -51, 105, 87, 19, -109, 36, -72, 59, 24, -54, 76}));
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject, android.internal.hidl.base.V1_0.IBase
        public final void setHALInstrumentation() {
        }

        @Override // android.os.IHwBinder, android.hardware.cas.V1_0.ICas, android.internal.hidl.base.V1_0.IBase
        public final boolean linkToDeath(IHwBinder.DeathRecipient recipient, long cookie) {
            return true;
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject, android.internal.hidl.base.V1_0.IBase
        public final void ping() {
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject, android.internal.hidl.base.V1_0.IBase
        public final DebugInfo getDebugInfo() {
            DebugInfo info = new DebugInfo();
            info.pid = HidlSupport.getPidIfSharable();
            info.ptr = 0L;
            info.arch = 0;
            return info;
        }

        @Override // vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject, android.internal.hidl.base.V1_0.IBase
        public final void notifySyspropsChanged() {
            HwBinder.enableInstrumentation();
        }

        @Override // android.os.IHwBinder, android.hardware.cas.V1_0.ICas, android.internal.hidl.base.V1_0.IBase
        public final boolean unlinkToDeath(IHwBinder.DeathRecipient recipient) {
            return true;
        }

        @Override // android.os.IHwBinder
        public IHwInterface queryLocalInterface(String descriptor) {
            if (IOplusProject.kInterfaceName.equals(descriptor)) {
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
        public void onTransact(int _hidl_code, HwParcel _hidl_request, HwParcel _hidl_reply, int _hidl_flags) throws RemoteException {
            switch (_hidl_code) {
                case 1:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    int _hidl_out_result = get_project();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_result);
                    _hidl_reply.send();
                    return;
                case 2:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    int _hidl_out_result2 = get_pcb_version();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_result2);
                    _hidl_reply.send();
                    return;
                case 3:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    String _hidl_out_result3 = get_serial_ID();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeString(_hidl_out_result3);
                    _hidl_reply.send();
                    return;
                case 4:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    int _hidl_out_result4 = get_operator_name();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_result4);
                    _hidl_reply.send();
                    return;
                case 5:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    int _hidl_out_result5 = get_rf_type();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_result5);
                    _hidl_reply.send();
                    return;
                case 6:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    int _hidl_out_result6 = get_eng_version();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_result6);
                    _hidl_reply.send();
                    return;
                case 7:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    String _hidl_out_result7 = get_hungtask();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeString(_hidl_out_result7);
                    _hidl_reply.send();
                    return;
                case 8:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    String _hidl_out_result8 = get_force_reboot();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeString(_hidl_out_result8);
                    _hidl_reply.send();
                    return;
                case 9:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    int _hidl_out_result9 = get_ftmmode();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_result9);
                    _hidl_reply.send();
                    return;
                case 10:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    String _hidl_out_result10 = get_mt_gpio();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeString(_hidl_out_result10);
                    _hidl_reply.send();
                    return;
                case 11:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    String _hidl_out_result11 = get_opboot();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeString(_hidl_out_result11);
                    _hidl_reply.send();
                    return;
                case 12:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    String opboot = _hidl_request.readString();
                    boolean _hidl_out_success = set_opboot(opboot);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeBool(_hidl_out_success);
                    _hidl_reply.send();
                    return;
                case 13:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    int pmic_id = _hidl_request.readInt32();
                    String _hidl_out_result12 = get_opluspoff_reason(pmic_id);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeString(_hidl_out_result12);
                    _hidl_reply.send();
                    return;
                case 14:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    int pmic_id2 = _hidl_request.readInt32();
                    String _hidl_out_result13 = get_opluspon_reason(pmic_id2);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeString(_hidl_out_result13);
                    _hidl_reply.send();
                    return;
                case 15:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    int pmic_id3 = _hidl_request.readInt32();
                    String _hidl_out_result14 = get_oplusocp_status(pmic_id3);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeString(_hidl_out_result14);
                    _hidl_reply.send();
                    return;
                case 16:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    String _hidl_out_result15 = get_phoenix();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeString(_hidl_out_result15);
                    _hidl_reply.send();
                    return;
                case 17:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    String monitoting_command = _hidl_request.readString();
                    boolean _hidl_out_success2 = set_phoenix(monitoting_command);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeBool(_hidl_out_success2);
                    _hidl_reply.send();
                    return;
                case 18:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    String _hidl_out_result16 = get_shutdown_detect();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeString(_hidl_out_result16);
                    _hidl_reply.send();
                    return;
                case 19:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    String shutdown_detect_vaule = _hidl_request.readString();
                    boolean _hidl_out_success3 = set_shutdown_detect(shutdown_detect_vaule);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeBool(_hidl_out_success3);
                    _hidl_reply.send();
                    return;
                case 20:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    String _hidl_out_result17 = get_poff_reason();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeString(_hidl_out_result17);
                    _hidl_reply.send();
                    return;
                case 21:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    String _hidl_out_result18 = get_pon_reason();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeString(_hidl_out_result18);
                    _hidl_reply.send();
                    return;
                case 22:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    String _hidl_out_result19 = get_ocp();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeString(_hidl_out_result19);
                    _hidl_reply.send();
                    return;
                case 23:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    int _hidl_out_result20 = get_sau();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_result20);
                    _hidl_reply.send();
                    return;
                case 24:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    boolean _hidl_out_success4 = is_ufs_devices();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeBool(_hidl_out_success4);
                    _hidl_reply.send();
                    return;
                case 25:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    int theia_node_id = _hidl_request.readInt32();
                    String _hidl_out_theia_data = read_theia_node(theia_node_id);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeString(_hidl_out_theia_data);
                    _hidl_reply.send();
                    return;
                case 26:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    int theia_node_id2 = _hidl_request.readInt32();
                    String theia_data = _hidl_request.readString();
                    boolean _hidl_out_success5 = write_theia_node(theia_node_id2, theia_data);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeBool(_hidl_out_success5);
                    _hidl_reply.send();
                    return;
                case 27:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    int type = _hidl_request.readInt32();
                    ArrayList<Byte> _hidl_out_data = readAgingData(type);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt8Vector(_hidl_out_data);
                    _hidl_reply.send();
                    return;
                case 28:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    int type2 = _hidl_request.readInt32();
                    ArrayList<Byte> saveAgingData = _hidl_request.readInt8Vector();
                    int length = _hidl_request.readInt32();
                    boolean _hidl_out_data2 = saveAgingData(type2, saveAgingData, length);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeBool(_hidl_out_data2);
                    _hidl_reply.send();
                    return;
                case 29:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    String _hidl_out_result21 = miscReadLinkedModules();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeString(_hidl_out_result21);
                    _hidl_reply.send();
                    return;
                case 30:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    String _hidl_out_result22 = miscModule2Load();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeString(_hidl_out_result22);
                    _hidl_reply.send();
                    return;
                case 31:
                    _hidl_request.enforceInterface(IOplusProject.kInterfaceName);
                    String _hidl_out_result23 = miscModuleBlocklist();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeString(_hidl_out_result23);
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
