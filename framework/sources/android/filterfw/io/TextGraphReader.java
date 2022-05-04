package android.filterfw.io;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterFactory;
import android.filterfw.core.FilterGraph;
import android.filterfw.core.KeyValueMap;
import android.filterfw.core.ProtocolException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;
/* loaded from: classes.dex */
public class TextGraphReader extends GraphReader {
    private KeyValueMap mBoundReferences;
    private ArrayList<Command> mCommands = new ArrayList<>();
    private Filter mCurrentFilter;
    private FilterGraph mCurrentGraph;
    private FilterFactory mFactory;
    private KeyValueMap mSettings;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface Command {
        void execute(TextGraphReader textGraphReader) throws GraphIOException;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ImportPackageCommand implements Command {
        private String mPackageName;

        public ImportPackageCommand(String packageName) {
            this.mPackageName = packageName;
        }

        @Override // android.filterfw.io.TextGraphReader.Command
        public void execute(TextGraphReader reader) throws GraphIOException {
            try {
                reader.mFactory.addPackage(this.mPackageName);
            } catch (IllegalArgumentException e) {
                throw new GraphIOException(e.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class AddLibraryCommand implements Command {
        private String mLibraryName;

        public AddLibraryCommand(String libraryName) {
            this.mLibraryName = libraryName;
        }

        @Override // android.filterfw.io.TextGraphReader.Command
        public void execute(TextGraphReader reader) {
            FilterFactory unused = reader.mFactory;
            FilterFactory.addFilterLibrary(this.mLibraryName);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class AllocateFilterCommand implements Command {
        private String mClassName;
        private String mFilterName;

        public AllocateFilterCommand(String className, String filterName) {
            this.mClassName = className;
            this.mFilterName = filterName;
        }

        @Override // android.filterfw.io.TextGraphReader.Command
        public void execute(TextGraphReader reader) throws GraphIOException {
            try {
                Filter filter = reader.mFactory.createFilterByClassName(this.mClassName, this.mFilterName);
                reader.mCurrentFilter = filter;
            } catch (IllegalArgumentException e) {
                throw new GraphIOException(e.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class InitFilterCommand implements Command {
        private KeyValueMap mParams;

        public InitFilterCommand(KeyValueMap params) {
            this.mParams = params;
        }

        @Override // android.filterfw.io.TextGraphReader.Command
        public void execute(TextGraphReader reader) throws GraphIOException {
            Filter filter = reader.mCurrentFilter;
            try {
                filter.initWithValueMap(this.mParams);
                reader.mCurrentGraph.addFilter(TextGraphReader.this.mCurrentFilter);
            } catch (ProtocolException e) {
                throw new GraphIOException(e.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ConnectCommand implements Command {
        private String mSourceFilter;
        private String mSourcePort;
        private String mTargetFilter;
        private String mTargetName;

        public ConnectCommand(String sourceFilter, String sourcePort, String targetFilter, String targetName) {
            this.mSourceFilter = sourceFilter;
            this.mSourcePort = sourcePort;
            this.mTargetFilter = targetFilter;
            this.mTargetName = targetName;
        }

        @Override // android.filterfw.io.TextGraphReader.Command
        public void execute(TextGraphReader reader) {
            reader.mCurrentGraph.connect(this.mSourceFilter, this.mSourcePort, this.mTargetFilter, this.mTargetName);
        }
    }

    @Override // android.filterfw.io.GraphReader
    public FilterGraph readGraphString(String graphString) throws GraphIOException {
        FilterGraph result = new FilterGraph();
        reset();
        this.mCurrentGraph = result;
        parseString(graphString);
        applySettings();
        executeCommands();
        reset();
        return result;
    }

    private void reset() {
        this.mCurrentGraph = null;
        this.mCurrentFilter = null;
        this.mCommands.clear();
        this.mBoundReferences = new KeyValueMap();
        this.mSettings = new KeyValueMap();
        this.mFactory = new FilterFactory();
    }

    private void parseString(String graphString) throws GraphIOException {
        Pattern semicolonPattern;
        Pattern wordPattern;
        PatternScanner scanner;
        Pattern semicolonPattern2;
        String curClassName;
        Pattern packageNamePattern;
        Pattern libraryNamePattern;
        Pattern commandPattern = Pattern.compile("@[a-zA-Z]+");
        Pattern curlyClosePattern = Pattern.compile("\\}");
        Pattern curlyOpenPattern = Pattern.compile("\\{");
        Pattern ignorePattern = Pattern.compile("(\\s+|//[^\\n]*\\n)+");
        Pattern packageNamePattern2 = Pattern.compile("[a-zA-Z\\.]+");
        Pattern libraryNamePattern2 = Pattern.compile("[a-zA-Z\\./:]+");
        Pattern portPattern = Pattern.compile("\\[[a-zA-Z0-9\\-_]+\\]");
        Pattern rightArrowPattern = Pattern.compile("=>");
        String str = ";";
        Pattern commandPattern2 = Pattern.compile(str);
        Pattern wordPattern2 = Pattern.compile("[a-zA-Z0-9\\-_]+");
        int state = 0;
        PatternScanner scanner2 = new PatternScanner(graphString, ignorePattern);
        String curSourceFilterName = null;
        String curSourcePortName = null;
        String curTargetFilterName = null;
        String curClassName2 = null;
        while (!scanner2.atEnd()) {
            switch (state) {
                case 0:
                    semicolonPattern = commandPattern2;
                    str = str;
                    packageNamePattern = packageNamePattern2;
                    scanner = scanner2;
                    libraryNamePattern = libraryNamePattern2;
                    wordPattern = wordPattern2;
                    curClassName = curClassName2;
                    semicolonPattern2 = commandPattern;
                    String curCommand = scanner.eat(semicolonPattern2, "<command>");
                    if (curCommand.equals("@import")) {
                        state = 1;
                        break;
                    } else if (curCommand.equals("@library")) {
                        state = 2;
                        break;
                    } else if (curCommand.equals("@filter")) {
                        state = 3;
                        break;
                    } else if (curCommand.equals("@connect")) {
                        state = 8;
                        break;
                    } else if (curCommand.equals("@set")) {
                        state = 13;
                        break;
                    } else if (curCommand.equals("@external")) {
                        state = 14;
                        break;
                    } else if (curCommand.equals("@setting")) {
                        state = 15;
                        break;
                    } else {
                        throw new GraphIOException("Unknown command '" + curCommand + "'!");
                    }
                case 1:
                    semicolonPattern = commandPattern2;
                    str = str;
                    scanner = scanner2;
                    libraryNamePattern = libraryNamePattern2;
                    wordPattern = wordPattern2;
                    curClassName = curClassName2;
                    packageNamePattern = packageNamePattern2;
                    String packageName = scanner.eat(packageNamePattern, "<package-name>");
                    this.mCommands.add(new ImportPackageCommand(packageName));
                    state = 16;
                    semicolonPattern2 = commandPattern;
                    break;
                case 2:
                    semicolonPattern = commandPattern2;
                    str = str;
                    scanner = scanner2;
                    wordPattern = wordPattern2;
                    curClassName = curClassName2;
                    libraryNamePattern = libraryNamePattern2;
                    String libraryName = scanner.eat(libraryNamePattern, "<library-name>");
                    this.mCommands.add(new AddLibraryCommand(libraryName));
                    state = 16;
                    semicolonPattern2 = commandPattern;
                    packageNamePattern = packageNamePattern2;
                    break;
                case 3:
                    semicolonPattern = commandPattern2;
                    str = str;
                    scanner = scanner2;
                    wordPattern = wordPattern2;
                    String curClassName3 = scanner.eat(wordPattern, "<class-name>");
                    curClassName = curClassName3;
                    state = 4;
                    semicolonPattern2 = commandPattern;
                    packageNamePattern = packageNamePattern2;
                    libraryNamePattern = libraryNamePattern2;
                    break;
                case 4:
                    semicolonPattern = commandPattern2;
                    str = str;
                    scanner = scanner2;
                    wordPattern = wordPattern2;
                    String curFilterName = scanner.eat(wordPattern, "<filter-name>");
                    curClassName = curClassName2;
                    this.mCommands.add(new AllocateFilterCommand(curClassName, curFilterName));
                    state = 5;
                    semicolonPattern2 = commandPattern;
                    packageNamePattern = packageNamePattern2;
                    libraryNamePattern = libraryNamePattern2;
                    break;
                case 5:
                    semicolonPattern = commandPattern2;
                    str = str;
                    scanner = scanner2;
                    wordPattern = wordPattern2;
                    scanner.eat(curlyOpenPattern, "{");
                    state = 6;
                    semicolonPattern2 = commandPattern;
                    packageNamePattern = packageNamePattern2;
                    libraryNamePattern = libraryNamePattern2;
                    curClassName = curClassName2;
                    break;
                case 6:
                    semicolonPattern = commandPattern2;
                    str = str;
                    scanner = scanner2;
                    wordPattern = wordPattern2;
                    KeyValueMap params = readKeyValueAssignments(scanner, curlyClosePattern);
                    this.mCommands.add(new InitFilterCommand(params));
                    state = 7;
                    semicolonPattern2 = commandPattern;
                    packageNamePattern = packageNamePattern2;
                    libraryNamePattern = libraryNamePattern2;
                    curClassName = curClassName2;
                    break;
                case 7:
                    semicolonPattern = commandPattern2;
                    str = str;
                    scanner = scanner2;
                    wordPattern = wordPattern2;
                    scanner.eat(curlyClosePattern, "}");
                    state = 0;
                    semicolonPattern2 = commandPattern;
                    packageNamePattern = packageNamePattern2;
                    libraryNamePattern = libraryNamePattern2;
                    curClassName = curClassName2;
                    break;
                case 8:
                    semicolonPattern = commandPattern2;
                    str = str;
                    scanner = scanner2;
                    wordPattern = wordPattern2;
                    String curSourceFilterName2 = scanner.eat(wordPattern, "<source-filter-name>");
                    curSourceFilterName = curSourceFilterName2;
                    state = 9;
                    semicolonPattern2 = commandPattern;
                    packageNamePattern = packageNamePattern2;
                    libraryNamePattern = libraryNamePattern2;
                    curClassName = curClassName2;
                    break;
                case 9:
                    semicolonPattern = commandPattern2;
                    str = str;
                    scanner = scanner2;
                    wordPattern = wordPattern2;
                    String portString = scanner.eat(portPattern, "[<source-port-name>]");
                    String curSourcePortName2 = portString.substring(1, portString.length() - 1);
                    curSourcePortName = curSourcePortName2;
                    state = 10;
                    semicolonPattern2 = commandPattern;
                    packageNamePattern = packageNamePattern2;
                    libraryNamePattern = libraryNamePattern2;
                    curClassName = curClassName2;
                    break;
                case 10:
                    semicolonPattern = commandPattern2;
                    str = str;
                    scanner = scanner2;
                    wordPattern = wordPattern2;
                    scanner.eat(rightArrowPattern, "=>");
                    state = 11;
                    semicolonPattern2 = commandPattern;
                    packageNamePattern = packageNamePattern2;
                    libraryNamePattern = libraryNamePattern2;
                    curClassName = curClassName2;
                    break;
                case 11:
                    semicolonPattern = commandPattern2;
                    str = str;
                    scanner = scanner2;
                    wordPattern = wordPattern2;
                    String curTargetFilterName2 = scanner.eat(wordPattern, "<target-filter-name>");
                    curTargetFilterName = curTargetFilterName2;
                    state = 12;
                    semicolonPattern2 = commandPattern;
                    packageNamePattern = packageNamePattern2;
                    libraryNamePattern = libraryNamePattern2;
                    curClassName = curClassName2;
                    break;
                case 12:
                    String portString2 = scanner2.eat(portPattern, "[<target-port-name>]");
                    String curTargetPortName = portString2.substring(1, portString2.length() - 1);
                    scanner = scanner2;
                    wordPattern = wordPattern2;
                    semicolonPattern = commandPattern2;
                    str = str;
                    this.mCommands.add(new ConnectCommand(curSourceFilterName, curSourcePortName, curTargetFilterName, curTargetPortName));
                    state = 16;
                    semicolonPattern2 = commandPattern;
                    packageNamePattern = packageNamePattern2;
                    libraryNamePattern = libraryNamePattern2;
                    curClassName = curClassName2;
                    break;
                case 13:
                    KeyValueMap assignment = readKeyValueAssignments(scanner2, commandPattern2);
                    this.mBoundReferences.putAll(assignment);
                    state = 16;
                    semicolonPattern = commandPattern2;
                    str = str;
                    semicolonPattern2 = commandPattern;
                    packageNamePattern = packageNamePattern2;
                    scanner = scanner2;
                    libraryNamePattern = libraryNamePattern2;
                    wordPattern = wordPattern2;
                    curClassName = curClassName2;
                    break;
                case 14:
                    String externalName = scanner2.eat(wordPattern2, "<external-identifier>");
                    bindExternal(externalName);
                    state = 16;
                    semicolonPattern = commandPattern2;
                    str = str;
                    semicolonPattern2 = commandPattern;
                    packageNamePattern = packageNamePattern2;
                    scanner = scanner2;
                    libraryNamePattern = libraryNamePattern2;
                    wordPattern = wordPattern2;
                    curClassName = curClassName2;
                    break;
                case 15:
                    KeyValueMap setting = readKeyValueAssignments(scanner2, commandPattern2);
                    this.mSettings.putAll(setting);
                    state = 16;
                    semicolonPattern = commandPattern2;
                    str = str;
                    semicolonPattern2 = commandPattern;
                    packageNamePattern = packageNamePattern2;
                    scanner = scanner2;
                    libraryNamePattern = libraryNamePattern2;
                    wordPattern = wordPattern2;
                    curClassName = curClassName2;
                    break;
                case 16:
                    scanner2.eat(commandPattern2, str);
                    state = 0;
                    semicolonPattern = commandPattern2;
                    str = str;
                    semicolonPattern2 = commandPattern;
                    packageNamePattern = packageNamePattern2;
                    scanner = scanner2;
                    libraryNamePattern = libraryNamePattern2;
                    wordPattern = wordPattern2;
                    curClassName = curClassName2;
                    break;
                default:
                    semicolonPattern = commandPattern2;
                    str = str;
                    semicolonPattern2 = commandPattern;
                    packageNamePattern = packageNamePattern2;
                    scanner = scanner2;
                    libraryNamePattern = libraryNamePattern2;
                    wordPattern = wordPattern2;
                    curClassName = curClassName2;
                    break;
            }
            libraryNamePattern2 = libraryNamePattern;
            packageNamePattern2 = packageNamePattern;
            scanner2 = scanner;
            commandPattern = semicolonPattern2;
            commandPattern2 = semicolonPattern;
            curClassName2 = curClassName;
            wordPattern2 = wordPattern;
        }
        if (state != 16 && state != 0) {
            throw new GraphIOException("Unexpected end of input!");
        }
    }

    @Override // android.filterfw.io.GraphReader
    public KeyValueMap readKeyValueAssignments(String assignments) throws GraphIOException {
        Pattern ignorePattern = Pattern.compile("\\s+");
        PatternScanner scanner = new PatternScanner(assignments, ignorePattern);
        return readKeyValueAssignments(scanner, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x0176, code lost:
        if (r15 == 0) goto L_0x019c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0179, code lost:
        if (r15 != 3) goto L_0x017c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x019b, code lost:
        throw new android.filterfw.io.GraphIOException("Unexpected end of assignments on line " + r25.lineNo() + "!");
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x019c, code lost:
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.filterfw.core.KeyValueMap readKeyValueAssignments(android.filterfw.io.PatternScanner r25, java.util.regex.Pattern r26) throws android.filterfw.io.GraphIOException {
        /*
            Method dump skipped, instructions count: 426
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.filterfw.io.TextGraphReader.readKeyValueAssignments(android.filterfw.io.PatternScanner, java.util.regex.Pattern):android.filterfw.core.KeyValueMap");
    }

    private void bindExternal(String name) throws GraphIOException {
        if (this.mReferences.containsKey(name)) {
            Object value = this.mReferences.get(name);
            this.mBoundReferences.put(name, value);
            return;
        }
        throw new GraphIOException("Unknown external variable '" + name + "'! You must add a reference to this external in the host program using addReference(...)!");
    }

    private void checkReferences() throws GraphIOException {
        for (String reference : this.mReferences.keySet()) {
            if (!this.mBoundReferences.containsKey(reference)) {
                throw new GraphIOException("Host program specifies reference to '" + reference + "', which is not declared @external in graph file!");
            }
        }
    }

    private void applySettings() throws GraphIOException {
        for (String setting : this.mSettings.keySet()) {
            Object value = this.mSettings.get(setting);
            if (setting.equals("autoBranch")) {
                expectSettingClass(setting, value, String.class);
                if (value.equals("synced")) {
                    this.mCurrentGraph.setAutoBranchMode(1);
                } else if (value.equals("unsynced")) {
                    this.mCurrentGraph.setAutoBranchMode(2);
                } else if (value.equals("off")) {
                    this.mCurrentGraph.setAutoBranchMode(0);
                } else {
                    throw new GraphIOException("Unknown autobranch setting: " + value + "!");
                }
            } else if (setting.equals("discardUnconnectedOutputs")) {
                expectSettingClass(setting, value, Boolean.class);
                this.mCurrentGraph.setDiscardUnconnectedOutputs(((Boolean) value).booleanValue());
            } else {
                throw new GraphIOException("Unknown @setting '" + setting + "'!");
            }
        }
    }

    private void expectSettingClass(String setting, Object value, Class expectedClass) throws GraphIOException {
        if (value.getClass() != expectedClass) {
            throw new GraphIOException("Setting '" + setting + "' must have a value of type " + expectedClass.getSimpleName() + ", but found a value of type " + value.getClass().getSimpleName() + "!");
        }
    }

    private void executeCommands() throws GraphIOException {
        Iterator<Command> it = this.mCommands.iterator();
        while (it.hasNext()) {
            Command command = it.next();
            command.execute(this);
        }
    }
}
