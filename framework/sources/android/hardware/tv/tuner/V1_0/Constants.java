package android.hardware.tv.tuner.V1_0;
/* loaded from: classes2.dex */
public class Constants {

    /* loaded from: classes2.dex */
    public final class Result {
        public static final int INVALID_ARGUMENT = 4;
        public static final int INVALID_STATE = 3;
        public static final int NOT_INITIALIZED = 2;
        public static final int OUT_OF_MEMORY = 5;
        public static final int SUCCESS = 0;
        public static final int UNAVAILABLE = 1;
        public static final int UNKNOWN_ERROR = 6;

        public Result() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendType {
        public static final int ANALOG = 1;
        public static final int ATSC = 2;
        public static final int ATSC3 = 3;
        public static final int DVBC = 4;
        public static final int DVBS = 5;
        public static final int DVBT = 6;
        public static final int ISDBS = 7;
        public static final int ISDBS3 = 8;
        public static final int ISDBT = 9;
        public static final int UNDEFINED = 0;

        public FrontendType() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendInnerFec {
        public static final long AUTO = 1;
        public static final long FEC_11_15 = 4194304;
        public static final long FEC_11_20 = 8388608;
        public static final long FEC_11_45 = 16777216;
        public static final long FEC_13_18 = 33554432;
        public static final long FEC_13_45 = 67108864;
        public static final long FEC_14_45 = 134217728;
        public static final long FEC_1_2 = 2;
        public static final long FEC_1_3 = 4;
        public static final long FEC_1_4 = 8;
        public static final long FEC_1_5 = 16;
        public static final long FEC_23_36 = 268435456;
        public static final long FEC_25_36 = 536870912;
        public static final long FEC_26_45 = 1073741824;
        public static final long FEC_28_45 = -2147483648L;
        public static final long FEC_29_45 = 1;
        public static final long FEC_2_3 = 32;
        public static final long FEC_2_5 = 64;
        public static final long FEC_2_9 = 128;
        public static final long FEC_31_45 = 2;
        public static final long FEC_32_45 = 4;
        public static final long FEC_3_4 = 256;
        public static final long FEC_3_5 = 512;
        public static final long FEC_4_15 = 2048;
        public static final long FEC_4_5 = 1024;
        public static final long FEC_5_6 = 4096;
        public static final long FEC_5_9 = 8192;
        public static final long FEC_6_7 = 16384;
        public static final long FEC_77_90 = 8;
        public static final long FEC_7_15 = 131072;
        public static final long FEC_7_8 = 32768;
        public static final long FEC_7_9 = 65536;
        public static final long FEC_8_15 = 524288;
        public static final long FEC_8_9 = 262144;
        public static final long FEC_9_10 = 1048576;
        public static final long FEC_9_20 = 2097152;
        public static final long FEC_UNDEFINED = 0;

        public FrontendInnerFec() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendAtscModulation {
        public static final int AUTO = 1;
        public static final int MOD_16VSB = 8;
        public static final int MOD_8VSB = 4;
        public static final int UNDEFINED = 0;

        public FrontendAtscModulation() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendAtsc3Modulation {
        public static final int AUTO = 1;
        public static final int MOD_1024QAM = 32;
        public static final int MOD_16QAM = 4;
        public static final int MOD_256QAM = 16;
        public static final int MOD_4096QAM = 64;
        public static final int MOD_64QAM = 8;
        public static final int MOD_QPSK = 2;
        public static final int UNDEFINED = 0;

        public FrontendAtsc3Modulation() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendAtsc3Bandwidth {
        public static final int AUTO = 1;
        public static final int BANDWIDTH_6MHZ = 2;
        public static final int BANDWIDTH_7MHZ = 4;
        public static final int BANDWIDTH_8MHZ = 8;
        public static final int UNDEFINED = 0;

        public FrontendAtsc3Bandwidth() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendAtsc3TimeInterleaveMode {
        public static final int AUTO = 1;
        public static final int CTI = 2;
        public static final int HTI = 4;
        public static final int UNDEFINED = 0;

        public FrontendAtsc3TimeInterleaveMode() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendAtsc3CodeRate {
        public static final int AUTO = 1;
        public static final int CODERATE_10_15 = 512;
        public static final int CODERATE_11_15 = 1024;
        public static final int CODERATE_12_15 = 2048;
        public static final int CODERATE_13_15 = 4096;
        public static final int CODERATE_2_15 = 2;
        public static final int CODERATE_3_15 = 4;
        public static final int CODERATE_4_15 = 8;
        public static final int CODERATE_5_15 = 16;
        public static final int CODERATE_6_15 = 32;
        public static final int CODERATE_7_15 = 64;
        public static final int CODERATE_8_15 = 128;
        public static final int CODERATE_9_15 = 256;
        public static final int UNDEFINED = 0;

        public FrontendAtsc3CodeRate() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendAtsc3Fec {
        public static final int AUTO = 1;
        public static final int BCH_LDPC_16K = 2;
        public static final int BCH_LDPC_64K = 4;
        public static final int CRC_LDPC_16K = 8;
        public static final int CRC_LDPC_64K = 16;
        public static final int LDPC_16K = 32;
        public static final int LDPC_64K = 64;
        public static final int UNDEFINED = 0;

        public FrontendAtsc3Fec() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendAtsc3DemodOutputFormat {
        public static final byte ATSC3_LINKLAYER_PACKET = 1;
        public static final byte BASEBAND_PACKET = 2;
        public static final byte UNDEFINED = 0;

        public FrontendAtsc3DemodOutputFormat() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendDvbsModulation {
        public static final int AUTO = 1;
        public static final int MOD_128APSK = 2048;
        public static final int MOD_16APSK = 256;
        public static final int MOD_16PSK = 16;
        public static final int MOD_16QAM = 8;
        public static final int MOD_256APSK = 4096;
        public static final int MOD_32APSK = 512;
        public static final int MOD_32PSK = 32;
        public static final int MOD_64APSK = 1024;
        public static final int MOD_8APSK = 128;
        public static final int MOD_8PSK = 4;
        public static final int MOD_ACM = 64;
        public static final int MOD_QPSK = 2;
        public static final int MOD_RESERVED = 8192;
        public static final int UNDEFINED = 0;

        public FrontendDvbsModulation() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendDvbsRolloff {
        public static final int ROLLOFF_0_10 = 5;
        public static final int ROLLOFF_0_15 = 4;
        public static final int ROLLOFF_0_20 = 3;
        public static final int ROLLOFF_0_25 = 2;
        public static final int ROLLOFF_0_35 = 1;
        public static final int ROLLOFF_0_5 = 6;
        public static final int UNDEFINED = 0;

        public FrontendDvbsRolloff() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendDvbsPilot {
        public static final int AUTO = 3;
        public static final int OFF = 2;
        public static final int ON = 1;
        public static final int UNDEFINED = 0;

        public FrontendDvbsPilot() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendDvbsStandard {
        public static final byte AUTO = 1;
        public static final byte S = 2;
        public static final byte S2 = 4;
        public static final byte S2X = 8;

        public FrontendDvbsStandard() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendDvbsVcmMode {
        public static final int AUTO = 1;
        public static final int MANUAL = 2;
        public static final int UNDEFINED = 0;

        public FrontendDvbsVcmMode() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendDvbcModulation {
        public static final int AUTO = 1;
        public static final int MOD_128QAM = 16;
        public static final int MOD_16QAM = 2;
        public static final int MOD_256QAM = 32;
        public static final int MOD_32QAM = 4;
        public static final int MOD_64QAM = 8;
        public static final int UNDEFINED = 0;

        public FrontendDvbcModulation() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendDvbcOuterFec {
        public static final int OUTER_FEC_NONE = 1;
        public static final int OUTER_FEC_RS = 2;
        public static final int UNDEFINED = 0;

        public FrontendDvbcOuterFec() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendDvbcAnnex {
        public static final byte A = 1;
        public static final byte B = 2;
        public static final byte C = 4;
        public static final byte UNDEFINED = 0;

        public FrontendDvbcAnnex() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendDvbcSpectralInversion {
        public static final int INVERTED = 2;
        public static final int NORMAL = 1;
        public static final int UNDEFINED = 0;

        public FrontendDvbcSpectralInversion() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendDvbtBandwidth {
        public static final int AUTO = 1;
        public static final int BANDWIDTH_10MHZ = 64;
        public static final int BANDWIDTH_1_7MHZ = 32;
        public static final int BANDWIDTH_5MHZ = 16;
        public static final int BANDWIDTH_6MHZ = 8;
        public static final int BANDWIDTH_7MHZ = 4;
        public static final int BANDWIDTH_8MHZ = 2;
        public static final int UNDEFINED = 0;

        public FrontendDvbtBandwidth() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendDvbtConstellation {
        public static final int AUTO = 1;
        public static final int CONSTELLATION_16QAM = 4;
        public static final int CONSTELLATION_256QAM = 16;
        public static final int CONSTELLATION_64QAM = 8;
        public static final int CONSTELLATION_QPSK = 2;
        public static final int UNDEFINED = 0;

        public FrontendDvbtConstellation() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendDvbtHierarchy {
        public static final int AUTO = 1;
        public static final int HIERARCHY_1_INDEPTH = 64;
        public static final int HIERARCHY_1_NATIVE = 4;
        public static final int HIERARCHY_2_INDEPTH = 128;
        public static final int HIERARCHY_2_NATIVE = 8;
        public static final int HIERARCHY_4_INDEPTH = 256;
        public static final int HIERARCHY_4_NATIVE = 16;
        public static final int HIERARCHY_NON_INDEPTH = 32;
        public static final int HIERARCHY_NON_NATIVE = 2;
        public static final int UNDEFINED = 0;

        public FrontendDvbtHierarchy() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendDvbtCoderate {
        public static final int AUTO = 1;
        public static final int CODERATE_1_2 = 2;
        public static final int CODERATE_2_3 = 4;
        public static final int CODERATE_3_4 = 8;
        public static final int CODERATE_3_5 = 64;
        public static final int CODERATE_4_5 = 128;
        public static final int CODERATE_5_6 = 16;
        public static final int CODERATE_6_7 = 256;
        public static final int CODERATE_7_8 = 32;
        public static final int CODERATE_8_9 = 512;
        public static final int UNDEFINED = 0;

        public FrontendDvbtCoderate() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendDvbtGuardInterval {
        public static final int AUTO = 1;
        public static final int INTERVAL_19_128 = 64;
        public static final int INTERVAL_19_256 = 128;
        public static final int INTERVAL_1_128 = 32;
        public static final int INTERVAL_1_16 = 4;
        public static final int INTERVAL_1_32 = 2;
        public static final int INTERVAL_1_4 = 16;
        public static final int INTERVAL_1_8 = 8;
        public static final int UNDEFINED = 0;

        public FrontendDvbtGuardInterval() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendDvbtTransmissionMode {
        public static final int AUTO = 1;
        public static final int MODE_16K = 32;
        public static final int MODE_1K = 16;
        public static final int MODE_2K = 2;
        public static final int MODE_32K = 64;
        public static final int MODE_4K = 8;
        public static final int MODE_8K = 4;
        public static final int UNDEFINED = 0;

        public FrontendDvbtTransmissionMode() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendDvbtPlpMode {
        public static final int AUTO = 1;
        public static final int MANUAL = 2;
        public static final int UNDEFINED = 0;

        public FrontendDvbtPlpMode() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendDvbtStandard {
        public static final byte AUTO = 1;
        public static final byte T = 2;
        public static final byte T2 = 4;

        public FrontendDvbtStandard() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendIsdbsRolloff {
        public static final int ROLLOFF_0_35 = 1;
        public static final int UNDEFINED = 0;

        public FrontendIsdbsRolloff() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendIsdbsModulation {
        public static final int AUTO = 1;
        public static final int MOD_BPSK = 2;
        public static final int MOD_QPSK = 4;
        public static final int MOD_TC8PSK = 8;
        public static final int UNDEFINED = 0;

        public FrontendIsdbsModulation() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendIsdbsCoderate {
        public static final int AUTO = 1;
        public static final int CODERATE_1_2 = 2;
        public static final int CODERATE_2_3 = 4;
        public static final int CODERATE_3_4 = 8;
        public static final int CODERATE_5_6 = 16;
        public static final int CODERATE_7_8 = 32;
        public static final int UNDEFINED = 0;

        public FrontendIsdbsCoderate() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendIsdbsStreamIdType {
        public static final int RELATIVE_STREAM_NUMBER = 1;
        public static final int STREAM_ID = 0;

        public FrontendIsdbsStreamIdType() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendIsdbs3Rolloff {
        public static final int ROLLOFF_0_03 = 1;
        public static final int UNDEFINED = 0;

        public FrontendIsdbs3Rolloff() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendIsdbs3Modulation {
        public static final int AUTO = 1;
        public static final int MOD_16APSK = 16;
        public static final int MOD_32APSK = 32;
        public static final int MOD_8PSK = 8;
        public static final int MOD_BPSK = 2;
        public static final int MOD_QPSK = 4;
        public static final int UNDEFINED = 0;

        public FrontendIsdbs3Modulation() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendIsdbs3Coderate {
        public static final int AUTO = 1;
        public static final int CODERATE_1_2 = 8;
        public static final int CODERATE_1_3 = 2;
        public static final int CODERATE_2_3 = 32;
        public static final int CODERATE_2_5 = 4;
        public static final int CODERATE_3_4 = 64;
        public static final int CODERATE_3_5 = 16;
        public static final int CODERATE_4_5 = 256;
        public static final int CODERATE_5_6 = 512;
        public static final int CODERATE_7_8 = 1024;
        public static final int CODERATE_7_9 = 128;
        public static final int CODERATE_9_10 = 2048;
        public static final int UNDEFINED = 0;

        public FrontendIsdbs3Coderate() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendIsdbtMode {
        public static final int AUTO = 1;
        public static final int MODE_1 = 2;
        public static final int MODE_2 = 4;
        public static final int MODE_3 = 8;
        public static final int UNDEFINED = 0;

        public FrontendIsdbtMode() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendIsdbtBandwidth {
        public static final int AUTO = 1;
        public static final int BANDWIDTH_6MHZ = 8;
        public static final int BANDWIDTH_7MHZ = 4;
        public static final int BANDWIDTH_8MHZ = 2;
        public static final int UNDEFINED = 0;

        public FrontendIsdbtBandwidth() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendIsdbtModulation {
        public static final int AUTO = 1;
        public static final int MOD_16QAM = 8;
        public static final int MOD_64QAM = 16;
        public static final int MOD_DQPSK = 2;
        public static final int MOD_QPSK = 4;
        public static final int UNDEFINED = 0;

        public FrontendIsdbtModulation() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendAnalogType {
        public static final int AUTO = 1;
        public static final int NTSC = 32;
        public static final int NTSC_443 = 64;
        public static final int PAL = 2;
        public static final int PAL_60 = 16;
        public static final int PAL_M = 4;
        public static final int PAL_N = 8;
        public static final int SECAM = 128;
        public static final int UNDEFINED = 0;

        public FrontendAnalogType() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendAnalogSifStandard {
        public static final int AUTO = 1;
        public static final int BG = 2;
        public static final int BG_A2 = 4;
        public static final int BG_NICAM = 8;
        public static final int DK = 32;
        public static final int DK1_A2 = 64;
        public static final int DK2_A2 = 128;
        public static final int DK3_A2 = 256;
        public static final int DK_NICAM = 512;
        public static final int I = 16;
        public static final int I_NICAM = 32768;
        public static final int L = 1024;
        public static final int L_NICAM = 65536;
        public static final int L_PRIME = 131072;
        public static final int M = 2048;
        public static final int M_A2 = 8192;
        public static final int M_BTSC = 4096;
        public static final int M_EIAJ = 16384;
        public static final int UNDEFINED = 0;

        public FrontendAnalogSifStandard() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendScanType {
        public static final int SCAN_AUTO = 1;
        public static final int SCAN_BLIND = 2;
        public static final int SCAN_UNDEFINED = 0;

        public FrontendScanType() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendScanMessageType {
        public static final int ANALOG_TYPE = 6;
        public static final int ATSC3_PLP_INFO = 11;
        public static final int END = 1;
        public static final int FREQUENCY = 3;
        public static final int GROUP_IDS = 8;
        public static final int HIERARCHY = 5;
        public static final int INPUT_STREAM_IDS = 9;
        public static final int LOCKED = 0;
        public static final int PLP_IDS = 7;
        public static final int PROGRESS_PERCENT = 2;
        public static final int STANDARD = 10;
        public static final int SYMBOL_RATE = 4;

        public FrontendScanMessageType() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendEventType {
        public static final int LOCKED = 0;
        public static final int LOST_LOCK = 2;
        public static final int NO_SIGNAL = 1;

        public FrontendEventType() {
        }
    }

    /* loaded from: classes2.dex */
    public final class FrontendStatusType {
        public static final int AGC = 14;
        public static final int ATSC3_PLP_INFO = 21;
        public static final int BER = 2;
        public static final int DEMOD_LOCK = 0;
        public static final int EWBS = 13;
        public static final int FEC = 8;
        public static final int FREQ_OFFSET = 18;
        public static final int HIERARCHY = 19;
        public static final int LAYER_ERROR = 16;
        public static final int LNA = 15;
        public static final int LNB_VOLTAGE = 11;
        public static final int MER = 17;
        public static final int MODULATION = 9;
        public static final int PER = 3;
        public static final int PLP_ID = 12;
        public static final int PRE_BER = 4;
        public static final int RF_LOCK = 20;
        public static final int SIGNAL_QUALITY = 5;
        public static final int SIGNAL_STRENGTH = 6;
        public static final int SNR = 1;
        public static final int SPECTRAL = 10;
        public static final int SYMBOL_RATE = 7;

        public FrontendStatusType() {
        }
    }

    /* loaded from: classes2.dex */
    public final class LnbVoltage {
        public static final int NONE = 0;
        public static final int VOLTAGE_11V = 2;
        public static final int VOLTAGE_12V = 3;
        public static final int VOLTAGE_13V = 4;
        public static final int VOLTAGE_14V = 5;
        public static final int VOLTAGE_15V = 6;
        public static final int VOLTAGE_18V = 7;
        public static final int VOLTAGE_19V = 8;
        public static final int VOLTAGE_5V = 1;

        public LnbVoltage() {
        }
    }

    /* loaded from: classes2.dex */
    public final class LnbTone {
        public static final int CONTINUOUS = 1;
        public static final int NONE = 0;

        public LnbTone() {
        }
    }

    /* loaded from: classes2.dex */
    public final class LnbPosition {
        public static final int POSITION_A = 1;
        public static final int POSITION_B = 2;
        public static final int UNDEFINED = 0;

        public LnbPosition() {
        }
    }

    /* loaded from: classes2.dex */
    public final class LnbEventType {
        public static final int DISEQC_RX_OVERFLOW = 0;
        public static final int DISEQC_RX_PARITY_ERROR = 2;
        public static final int DISEQC_RX_TIMEOUT = 1;
        public static final int LNB_OVERLOAD = 3;

        public LnbEventType() {
        }
    }

    /* loaded from: classes2.dex */
    public final class DemuxFilterMainType {
        public static final int ALP = 16;
        public static final int IP = 4;
        public static final int MMTP = 2;
        public static final int TLV = 8;
        public static final int TS = 1;

        public DemuxFilterMainType() {
        }
    }

    /* loaded from: classes2.dex */
    public final class DemuxTsFilterType {
        public static final int AUDIO = 4;
        public static final int PCR = 6;
        public static final int PES = 2;
        public static final int RECORD = 7;
        public static final int SECTION = 1;
        public static final int TEMI = 8;
        public static final int TS = 3;
        public static final int UNDEFINED = 0;
        public static final int VIDEO = 5;

        public DemuxTsFilterType() {
        }
    }

    /* loaded from: classes2.dex */
    public final class DemuxMmtpFilterType {
        public static final int AUDIO = 4;
        public static final int DOWNLOAD = 7;
        public static final int MMTP = 3;
        public static final int PES = 2;
        public static final int RECORD = 6;
        public static final int SECTION = 1;
        public static final int UNDEFINED = 0;
        public static final int VIDEO = 5;

        public DemuxMmtpFilterType() {
        }
    }

    /* loaded from: classes2.dex */
    public final class DemuxIpFilterType {
        public static final int IP = 4;
        public static final int IP_PAYLOAD = 3;
        public static final int NTP = 2;
        public static final int PAYLOAD_THROUGH = 5;
        public static final int SECTION = 1;
        public static final int UNDEFINED = 0;

        public DemuxIpFilterType() {
        }
    }

    /* loaded from: classes2.dex */
    public final class DemuxTlvFilterType {
        public static final int PAYLOAD_THROUGH = 3;
        public static final int SECTION = 1;
        public static final int TLV = 2;
        public static final int UNDEFINED = 0;

        public DemuxTlvFilterType() {
        }
    }

    /* loaded from: classes2.dex */
    public final class DemuxAlpFilterType {
        public static final int PAYLOAD_THROUGH = 3;
        public static final int PTP = 2;
        public static final int SECTION = 1;
        public static final int UNDEFINED = 0;

        public DemuxAlpFilterType() {
        }
    }

    /* loaded from: classes2.dex */
    public final class Constant {
        public static final int INVALID_AV_SYNC_ID = -1;
        public static final int INVALID_FILTER_ID = -1;
        public static final int INVALID_STREAM_ID = 65535;
        public static final int INVALID_TS_PID = 65535;

        public Constant() {
        }
    }

    /* loaded from: classes2.dex */
    public final class DemuxFilterStatus {
        public static final byte DATA_READY = 1;
        public static final byte HIGH_WATER = 4;
        public static final byte LOW_WATER = 2;
        public static final byte OVERFLOW = 8;

        public DemuxFilterStatus() {
        }
    }

    /* loaded from: classes2.dex */
    public final class DemuxTsIndex {
        public static final int ADAPTATION_EXTENSION_FLAG = 4096;
        public static final int CHANGE_TO_EVEN_SCRAMBLED = 8;
        public static final int CHANGE_TO_NOT_SCRAMBLED = 4;
        public static final int CHANGE_TO_ODD_SCRAMBLED = 16;
        public static final int DISCONTINUITY_INDICATOR = 32;
        public static final int FIRST_PACKET = 1;
        public static final int OPCR_FLAG = 512;
        public static final int PAYLOAD_UNIT_START_INDICATOR = 2;
        public static final int PCR_FLAG = 256;
        public static final int PRIORITY_INDICATOR = 128;
        public static final int PRIVATE_DATA = 2048;
        public static final int RANDOM_ACCESS_INDICATOR = 64;
        public static final int SPLICING_POINT_FLAG = 1024;

        public DemuxTsIndex() {
        }
    }

    /* loaded from: classes2.dex */
    public final class DemuxScIndex {
        public static final int B_FRAME = 4;
        public static final int I_FRAME = 1;
        public static final int P_FRAME = 2;
        public static final int SEQUENCE = 8;

        public DemuxScIndex() {
        }
    }

    /* loaded from: classes2.dex */
    public final class DemuxScHevcIndex {
        public static final int AUD = 2;
        public static final int SLICE_BLA_N_LP = 16;
        public static final int SLICE_BLA_W_RADL = 8;
        public static final int SLICE_CE_BLA_W_LP = 4;
        public static final int SLICE_IDR_N_LP = 64;
        public static final int SLICE_IDR_W_RADL = 32;
        public static final int SLICE_TRAIL_CRA = 128;
        public static final int SPS = 1;

        public DemuxScHevcIndex() {
        }
    }

    /* loaded from: classes2.dex */
    public final class DemuxRecordScIndexType {
        public static final int NONE = 0;
        public static final int SC = 1;
        public static final int SC_HEVC = 2;

        public DemuxRecordScIndexType() {
        }
    }

    /* loaded from: classes2.dex */
    public final class DemuxAlpLengthType {
        public static final byte UNDEFINED = 0;
        public static final byte WITHOUT_ADDITIONAL_HEADER = 1;
        public static final byte WITH_ADDITIONAL_HEADER = 2;

        public DemuxAlpLengthType() {
        }
    }

    /* loaded from: classes2.dex */
    public final class DemuxQueueNotifyBits {
        public static final int DATA_CONSUMED = 2;
        public static final int DATA_READY = 1;

        public DemuxQueueNotifyBits() {
        }
    }

    /* loaded from: classes2.dex */
    public final class DataFormat {
        public static final int ES = 2;
        public static final int PES = 1;
        public static final int SHV_TLV = 3;
        public static final int TS = 0;

        public DataFormat() {
        }
    }

    /* loaded from: classes2.dex */
    public final class PlaybackStatus {
        public static final int SPACE_ALMOST_EMPTY = 2;
        public static final int SPACE_ALMOST_FULL = 4;
        public static final int SPACE_EMPTY = 1;
        public static final int SPACE_FULL = 8;

        public PlaybackStatus() {
        }
    }

    /* loaded from: classes2.dex */
    public final class DvrType {
        public static final byte PLAYBACK = 1;
        public static final byte RECORD = 0;

        public DvrType() {
        }
    }
}
