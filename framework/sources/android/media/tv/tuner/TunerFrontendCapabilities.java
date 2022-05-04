package android.media.tv.tuner;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
public final class TunerFrontendCapabilities implements Parcelable {
    public static final Parcelable.Creator<TunerFrontendCapabilities> CREATOR = new Parcelable.Creator<TunerFrontendCapabilities>() { // from class: android.media.tv.tuner.TunerFrontendCapabilities.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TunerFrontendCapabilities createFromParcel(Parcel _aidl_source) {
            return new TunerFrontendCapabilities(_aidl_source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TunerFrontendCapabilities[] newArray(int _aidl_size) {
            return new TunerFrontendCapabilities[_aidl_size];
        }
    };
    public static final int analogCaps = 0;
    public static final int atsc3Caps = 2;
    public static final int atscCaps = 1;
    public static final int cableCaps = 3;
    public static final int dvbsCaps = 4;
    public static final int dvbtCaps = 5;
    public static final int isdbs3Caps = 7;
    public static final int isdbsCaps = 6;
    public static final int isdbtCaps = 8;
    private int _tag;
    private Object _value;

    public TunerFrontendCapabilities() {
        this._tag = 0;
        this._value = null;
    }

    private TunerFrontendCapabilities(Parcel _aidl_parcel) {
        readFromParcel(_aidl_parcel);
    }

    private TunerFrontendCapabilities(int _tag, Object _value) {
        this._tag = _tag;
        this._value = _value;
    }

    public int getTag() {
        return this._tag;
    }

    public static TunerFrontendCapabilities analogCaps(TunerFrontendAnalogCapabilities _value) {
        return new TunerFrontendCapabilities(0, _value);
    }

    public TunerFrontendAnalogCapabilities getAnalogCaps() {
        _assertTag(0);
        return (TunerFrontendAnalogCapabilities) this._value;
    }

    public void setAnalogCaps(TunerFrontendAnalogCapabilities _value) {
        _set(0, _value);
    }

    public static TunerFrontendCapabilities atscCaps(TunerFrontendAtscCapabilities _value) {
        return new TunerFrontendCapabilities(1, _value);
    }

    public TunerFrontendAtscCapabilities getAtscCaps() {
        _assertTag(1);
        return (TunerFrontendAtscCapabilities) this._value;
    }

    public void setAtscCaps(TunerFrontendAtscCapabilities _value) {
        _set(1, _value);
    }

    public static TunerFrontendCapabilities atsc3Caps(TunerFrontendAtsc3Capabilities _value) {
        return new TunerFrontendCapabilities(2, _value);
    }

    public TunerFrontendAtsc3Capabilities getAtsc3Caps() {
        _assertTag(2);
        return (TunerFrontendAtsc3Capabilities) this._value;
    }

    public void setAtsc3Caps(TunerFrontendAtsc3Capabilities _value) {
        _set(2, _value);
    }

    public static TunerFrontendCapabilities cableCaps(TunerFrontendCableCapabilities _value) {
        return new TunerFrontendCapabilities(3, _value);
    }

    public TunerFrontendCableCapabilities getCableCaps() {
        _assertTag(3);
        return (TunerFrontendCableCapabilities) this._value;
    }

    public void setCableCaps(TunerFrontendCableCapabilities _value) {
        _set(3, _value);
    }

    public static TunerFrontendCapabilities dvbsCaps(TunerFrontendDvbsCapabilities _value) {
        return new TunerFrontendCapabilities(4, _value);
    }

    public TunerFrontendDvbsCapabilities getDvbsCaps() {
        _assertTag(4);
        return (TunerFrontendDvbsCapabilities) this._value;
    }

    public void setDvbsCaps(TunerFrontendDvbsCapabilities _value) {
        _set(4, _value);
    }

    public static TunerFrontendCapabilities dvbtCaps(TunerFrontendDvbtCapabilities _value) {
        return new TunerFrontendCapabilities(5, _value);
    }

    public TunerFrontendDvbtCapabilities getDvbtCaps() {
        _assertTag(5);
        return (TunerFrontendDvbtCapabilities) this._value;
    }

    public void setDvbtCaps(TunerFrontendDvbtCapabilities _value) {
        _set(5, _value);
    }

    public static TunerFrontendCapabilities isdbsCaps(TunerFrontendIsdbsCapabilities _value) {
        return new TunerFrontendCapabilities(6, _value);
    }

    public TunerFrontendIsdbsCapabilities getIsdbsCaps() {
        _assertTag(6);
        return (TunerFrontendIsdbsCapabilities) this._value;
    }

    public void setIsdbsCaps(TunerFrontendIsdbsCapabilities _value) {
        _set(6, _value);
    }

    public static TunerFrontendCapabilities isdbs3Caps(TunerFrontendIsdbs3Capabilities _value) {
        return new TunerFrontendCapabilities(7, _value);
    }

    public TunerFrontendIsdbs3Capabilities getIsdbs3Caps() {
        _assertTag(7);
        return (TunerFrontendIsdbs3Capabilities) this._value;
    }

    public void setIsdbs3Caps(TunerFrontendIsdbs3Capabilities _value) {
        _set(7, _value);
    }

    public static TunerFrontendCapabilities isdbtCaps(TunerFrontendIsdbtCapabilities _value) {
        return new TunerFrontendCapabilities(8, _value);
    }

    public TunerFrontendIsdbtCapabilities getIsdbtCaps() {
        _assertTag(8);
        return (TunerFrontendIsdbtCapabilities) this._value;
    }

    public void setIsdbtCaps(TunerFrontendIsdbtCapabilities _value) {
        _set(8, _value);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel _aidl_parcel, int _aidl_flag) {
        _aidl_parcel.writeInt(this._tag);
        switch (this._tag) {
            case 0:
                if (getAnalogCaps() != null) {
                    _aidl_parcel.writeInt(1);
                    getAnalogCaps().writeToParcel(_aidl_parcel, 0);
                    return;
                }
                _aidl_parcel.writeInt(0);
                return;
            case 1:
                if (getAtscCaps() != null) {
                    _aidl_parcel.writeInt(1);
                    getAtscCaps().writeToParcel(_aidl_parcel, 0);
                    return;
                }
                _aidl_parcel.writeInt(0);
                return;
            case 2:
                if (getAtsc3Caps() != null) {
                    _aidl_parcel.writeInt(1);
                    getAtsc3Caps().writeToParcel(_aidl_parcel, 0);
                    return;
                }
                _aidl_parcel.writeInt(0);
                return;
            case 3:
                if (getCableCaps() != null) {
                    _aidl_parcel.writeInt(1);
                    getCableCaps().writeToParcel(_aidl_parcel, 0);
                    return;
                }
                _aidl_parcel.writeInt(0);
                return;
            case 4:
                if (getDvbsCaps() != null) {
                    _aidl_parcel.writeInt(1);
                    getDvbsCaps().writeToParcel(_aidl_parcel, 0);
                    return;
                }
                _aidl_parcel.writeInt(0);
                return;
            case 5:
                if (getDvbtCaps() != null) {
                    _aidl_parcel.writeInt(1);
                    getDvbtCaps().writeToParcel(_aidl_parcel, 0);
                    return;
                }
                _aidl_parcel.writeInt(0);
                return;
            case 6:
                if (getIsdbsCaps() != null) {
                    _aidl_parcel.writeInt(1);
                    getIsdbsCaps().writeToParcel(_aidl_parcel, 0);
                    return;
                }
                _aidl_parcel.writeInt(0);
                return;
            case 7:
                if (getIsdbs3Caps() != null) {
                    _aidl_parcel.writeInt(1);
                    getIsdbs3Caps().writeToParcel(_aidl_parcel, 0);
                    return;
                }
                _aidl_parcel.writeInt(0);
                return;
            case 8:
                if (getIsdbtCaps() != null) {
                    _aidl_parcel.writeInt(1);
                    getIsdbtCaps().writeToParcel(_aidl_parcel, 0);
                    return;
                }
                _aidl_parcel.writeInt(0);
                return;
            default:
                return;
        }
    }

    public void readFromParcel(Parcel _aidl_parcel) {
        TunerFrontendAnalogCapabilities _aidl_value;
        TunerFrontendAtscCapabilities _aidl_value2;
        TunerFrontendAtsc3Capabilities _aidl_value3;
        TunerFrontendCableCapabilities _aidl_value4;
        TunerFrontendDvbsCapabilities _aidl_value5;
        TunerFrontendDvbtCapabilities _aidl_value6;
        TunerFrontendIsdbsCapabilities _aidl_value7;
        TunerFrontendIsdbs3Capabilities _aidl_value8;
        TunerFrontendIsdbtCapabilities _aidl_value9;
        int _aidl_tag = _aidl_parcel.readInt();
        switch (_aidl_tag) {
            case 0:
                if (_aidl_parcel.readInt() != 0) {
                    _aidl_value = TunerFrontendAnalogCapabilities.CREATOR.createFromParcel(_aidl_parcel);
                } else {
                    _aidl_value = null;
                }
                _set(_aidl_tag, _aidl_value);
                return;
            case 1:
                if (_aidl_parcel.readInt() != 0) {
                    _aidl_value2 = TunerFrontendAtscCapabilities.CREATOR.createFromParcel(_aidl_parcel);
                } else {
                    _aidl_value2 = null;
                }
                _set(_aidl_tag, _aidl_value2);
                return;
            case 2:
                if (_aidl_parcel.readInt() != 0) {
                    _aidl_value3 = TunerFrontendAtsc3Capabilities.CREATOR.createFromParcel(_aidl_parcel);
                } else {
                    _aidl_value3 = null;
                }
                _set(_aidl_tag, _aidl_value3);
                return;
            case 3:
                if (_aidl_parcel.readInt() != 0) {
                    _aidl_value4 = TunerFrontendCableCapabilities.CREATOR.createFromParcel(_aidl_parcel);
                } else {
                    _aidl_value4 = null;
                }
                _set(_aidl_tag, _aidl_value4);
                return;
            case 4:
                if (_aidl_parcel.readInt() != 0) {
                    _aidl_value5 = TunerFrontendDvbsCapabilities.CREATOR.createFromParcel(_aidl_parcel);
                } else {
                    _aidl_value5 = null;
                }
                _set(_aidl_tag, _aidl_value5);
                return;
            case 5:
                if (_aidl_parcel.readInt() != 0) {
                    _aidl_value6 = TunerFrontendDvbtCapabilities.CREATOR.createFromParcel(_aidl_parcel);
                } else {
                    _aidl_value6 = null;
                }
                _set(_aidl_tag, _aidl_value6);
                return;
            case 6:
                if (_aidl_parcel.readInt() != 0) {
                    _aidl_value7 = TunerFrontendIsdbsCapabilities.CREATOR.createFromParcel(_aidl_parcel);
                } else {
                    _aidl_value7 = null;
                }
                _set(_aidl_tag, _aidl_value7);
                return;
            case 7:
                if (_aidl_parcel.readInt() != 0) {
                    _aidl_value8 = TunerFrontendIsdbs3Capabilities.CREATOR.createFromParcel(_aidl_parcel);
                } else {
                    _aidl_value8 = null;
                }
                _set(_aidl_tag, _aidl_value8);
                return;
            case 8:
                if (_aidl_parcel.readInt() != 0) {
                    _aidl_value9 = TunerFrontendIsdbtCapabilities.CREATOR.createFromParcel(_aidl_parcel);
                } else {
                    _aidl_value9 = null;
                }
                _set(_aidl_tag, _aidl_value9);
                return;
            default:
                throw new IllegalArgumentException("union: unknown tag: " + _aidl_tag);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        switch (getTag()) {
            case 0:
                int _mask = 0 | describeContents(getAnalogCaps());
                return _mask;
            case 1:
                int _mask2 = 0 | describeContents(getAtscCaps());
                return _mask2;
            case 2:
                int _mask3 = 0 | describeContents(getAtsc3Caps());
                return _mask3;
            case 3:
                int _mask4 = 0 | describeContents(getCableCaps());
                return _mask4;
            case 4:
                int _mask5 = 0 | describeContents(getDvbsCaps());
                return _mask5;
            case 5:
                int _mask6 = 0 | describeContents(getDvbtCaps());
                return _mask6;
            case 6:
                int _mask7 = 0 | describeContents(getIsdbsCaps());
                return _mask7;
            case 7:
                int _mask8 = 0 | describeContents(getIsdbs3Caps());
                return _mask8;
            case 8:
                int _mask9 = 0 | describeContents(getIsdbtCaps());
                return _mask9;
            default:
                return 0;
        }
    }

    private int describeContents(Object _v) {
        if (_v != null && (_v instanceof Parcelable)) {
            return ((Parcelable) _v).describeContents();
        }
        return 0;
    }

    private void _assertTag(int tag) {
        if (getTag() != tag) {
            throw new IllegalStateException("bad access: " + _tagString(tag) + ", " + _tagString(getTag()) + " is available.");
        }
    }

    private String _tagString(int _tag) {
        switch (_tag) {
            case 0:
                return "analogCaps";
            case 1:
                return "atscCaps";
            case 2:
                return "atsc3Caps";
            case 3:
                return "cableCaps";
            case 4:
                return "dvbsCaps";
            case 5:
                return "dvbtCaps";
            case 6:
                return "isdbsCaps";
            case 7:
                return "isdbs3Caps";
            case 8:
                return "isdbtCaps";
            default:
                throw new IllegalStateException("unknown field: " + _tag);
        }
    }

    private void _set(int _tag, Object _value) {
        this._tag = _tag;
        this._value = _value;
    }
}
