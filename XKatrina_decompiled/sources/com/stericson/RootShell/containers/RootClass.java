package com.stericson.RootShell.containers;

import androidx.exifinterface.media.ExifInterface;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes6.dex */
public class RootClass {
    static String PATH_TO_DX = "/Users/Chris/Projects/android-sdk-macosx/build-tools/18.0.1/dx";

    /* loaded from: classes6.dex */
    public @interface Candidate {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public enum READ_STATE {
        STARTING,
        FOUND_ANNOTATION;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static READ_STATE[] valuesCustom() {
            READ_STATE[] valuesCustom = values();
            int length = valuesCustom.length;
            READ_STATE[] read_stateArr = new READ_STATE[length];
            System.arraycopy(valuesCustom, 0, read_stateArr, 0, length);
            return read_stateArr;
        }
    }

    public RootClass(String[] strArr) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String str = strArr[0];
        RootArgs rootArgs = new RootArgs();
        rootArgs.args = new String[strArr.length - 1];
        System.arraycopy(strArr, 1, rootArgs.args, 0, strArr.length - 1);
        Class.forName(str).getConstructor(RootArgs.class).newInstance(rootArgs);
    }

    /* loaded from: classes6.dex */
    public class RootArgs {
        public String[] args;

        public RootArgs() {
        }
    }

    static void displayError(Exception exc) {
        PrintStream printStream = System.out;
        printStream.println("##ERR##" + exc.getMessage() + "##");
        exc.printStackTrace();
    }

    /* loaded from: classes6.dex */
    public static class AnnotationsFinder {
        private static volatile /* synthetic */ int[] $SWITCH_TABLE$com$stericson$RootShell$containers$RootClass$READ_STATE;
        private final String AVOIDDIRPATH = "stericson" + File.separator + "RootShell" + File.separator;
        private List<File> classFiles;

        static /* synthetic */ int[] $SWITCH_TABLE$com$stericson$RootShell$containers$RootClass$READ_STATE() {
            int[] iArr = $SWITCH_TABLE$com$stericson$RootShell$containers$RootClass$READ_STATE;
            if (iArr != null) {
                return iArr;
            }
            int[] iArr2 = new int[READ_STATE.valuesCustom().length];
            try {
                iArr2[READ_STATE.FOUND_ANNOTATION.ordinal()] = 2;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr2[READ_STATE.STARTING.ordinal()] = 1;
            } catch (NoSuchFieldError unused2) {
            }
            $SWITCH_TABLE$com$stericson$RootShell$containers$RootClass$READ_STATE = iArr2;
            return iArr2;
        }

        public AnnotationsFinder() throws IOException {
            boolean z;
            String str;
            String[] strArr;
            System.out.println("Discovering root class annotations...");
            this.classFiles = new ArrayList();
            lookup(new File("src"), this.classFiles);
            System.out.println("Done discovering annotations. Building jar file.");
            File builtPath = getBuiltPath();
            String str2 = "raw";
            if (builtPath != null) {
                String str3 = "com" + File.separator + "stericson" + File.separator + "RootShell" + File.separator + "containers" + File.separator + "RootClass.class";
                String str4 = "com" + File.separator + "stericson" + File.separator + "RootShell" + File.separator + "containers" + File.separator + "RootClass$RootArgs.class";
                String str5 = "com" + File.separator + "stericson" + File.separator + "RootShell" + File.separator + "containers" + File.separator + "RootClass$AnnotationsFinder.class";
                String str6 = "com" + File.separator + "stericson" + File.separator + "RootShell" + File.separator + "containers" + File.separator + "RootClass$AnnotationsFinder$1.class";
                String str7 = "com" + File.separator + "stericson" + File.separator + "RootShell" + File.separator + "containers" + File.separator + "RootClass$AnnotationsFinder$2.class";
                boolean z2 = -1 != System.getProperty("os.name").toLowerCase().indexOf("win");
                if (z2) {
                    z = z2;
                    str = "raw";
                    StringBuilder sb = new StringBuilder(" " + str3 + " " + str4 + " " + str5 + " " + str6 + " " + str7);
                    Iterator<File> it = this.classFiles.iterator();
                    while (it.hasNext()) {
                        sb.append(" " + it.next().getPath());
                    }
                    strArr = new String[]{"cmd", "/C", "jar cvf anbuild.jar" + sb.toString()};
                } else {
                    z = z2;
                    str = "raw";
                    ArrayList arrayList = new ArrayList();
                    arrayList.add("jar");
                    arrayList.add("cf");
                    arrayList.add("anbuild.jar");
                    arrayList.add(str3);
                    arrayList.add(str4);
                    arrayList.add(str5);
                    arrayList.add(str6);
                    arrayList.add(str7);
                    for (File file : this.classFiles) {
                        arrayList.add(file.getPath());
                    }
                    strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
                }
                ProcessBuilder processBuilder = new ProcessBuilder(strArr);
                processBuilder.directory(builtPath);
                try {
                    processBuilder.start().waitFor();
                } catch (IOException | InterruptedException unused) {
                }
                String str8 = File.separator;
                builtPath.toString().startsWith("build");
                StringBuilder sb2 = new StringBuilder("src");
                sb2.append(File.separator);
                sb2.append("main");
                sb2.append(File.separator);
                sb2.append("res");
                sb2.append(File.separator);
                str2 = str;
                sb2.append(str2);
                String sb3 = sb2.toString();
                File file2 = new File(sb3);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                System.out.println("Done building jar file. Creating dex file.");
                try {
                    new ProcessBuilder(z ? new String[]{"cmd", "/C", "dx --dex --output=" + sb3 + File.separator + "anbuild.dex " + builtPath + File.separator + "anbuild.jar"} : new String[]{getPathToDx(), "--dex", "--output=" + sb3 + File.separator + "anbuild.dex", builtPath + File.separator + "anbuild.jar"}).start().waitFor();
                } catch (IOException | InterruptedException unused2) {
                }
            }
            System.out.println("All done. ::: anbuild.dex should now be in your project's src" + File.separator + "main" + File.separator + "res" + File.separator + str2 + File.separator + " folder :::");
        }

        protected void lookup(File file, List<File> list) {
            File[] listFiles;
            File[] listFiles2;
            String replace = file.toString().replace("src" + File.separator, "").replace("main" + File.separator + "java" + File.separator, "");
            for (File file2 : file.listFiles(new FileFilter() { // from class: com.stericson.RootShell.containers.RootClass.AnnotationsFinder.1
                @Override // java.io.FileFilter
                public boolean accept(File file3) {
                    return true;
                }
            })) {
                if (file2.isDirectory()) {
                    if (-1 == file2.getAbsolutePath().indexOf(this.AVOIDDIRPATH)) {
                        lookup(file2, list);
                    }
                } else if (file2.getName().endsWith(".java") && hasClassAnnotation(file2)) {
                    final String replace2 = file2.getName().replace(".java", "");
                    for (File file3 : new File(String.valueOf(getBuiltPath().toString()) + File.separator + replace).listFiles(new FilenameFilter() { // from class: com.stericson.RootShell.containers.RootClass.AnnotationsFinder.2
                        @Override // java.io.FilenameFilter
                        public boolean accept(File file4, String str) {
                            return str.startsWith(replace2);
                        }
                    })) {
                        list.add(new File(String.valueOf(replace) + File.separator + file3.getName()));
                    }
                }
            }
        }

        protected boolean hasClassAnnotation(File file) {
            READ_STATE read_state = READ_STATE.STARTING;
            Pattern compile = Pattern.compile(" class ([A-Za-z0-9_]+)");
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    int i = $SWITCH_TABLE$com$stericson$RootShell$containers$RootClass$READ_STATE()[read_state.ordinal()];
                    if (i != 1) {
                        if (i == 2) {
                            Matcher matcher = compile.matcher(readLine);
                            if (matcher.find()) {
                                PrintStream printStream = System.out;
                                printStream.println(" Found annotated class: " + matcher.group(0));
                                return true;
                            }
                            PrintStream printStream2 = System.err;
                            printStream2.println("Error: unmatched annotation in " + file.getAbsolutePath());
                            read_state = READ_STATE.STARTING;
                        }
                    } else if (-1 < readLine.indexOf("@RootClass.Candidate")) {
                        read_state = READ_STATE.FOUND_ANNOTATION;
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return false;
        }

        protected String getPathToDx() throws IOException {
            String str;
            File[] listFiles;
            String name;
            if (System.getenv("ANDROID_HOME") == null) {
                throw new IOException("Error: you need to set $ANDROID_HOME globally");
            }
            String str2 = null;
            int i = 0;
            for (File file : new File(String.valueOf(str) + File.separator + "build-tools").listFiles()) {
                if (file.getName().contains("-")) {
                    String[] split = file.getName().split("-");
                    if (split[1].contains(ExifInterface.LONGITUDE_WEST)) {
                        name = String.valueOf(split[1].toCharArray()[0]);
                    } else if (!split[1].contains("rc")) {
                        name = split[1];
                    }
                } else {
                    name = file.getName();
                }
                String[] split2 = name.split("[.]");
                int parseInt = Integer.parseInt(split2[0]) * 10000;
                if (split2.length > 1) {
                    parseInt += Integer.parseInt(split2[1]) * 100;
                    if (split2.length > 2) {
                        parseInt += Integer.parseInt(split2[2]);
                    }
                }
                if (parseInt > i) {
                    String str3 = String.valueOf(file.getAbsolutePath()) + File.separator + "dx";
                    if (new File(str3).exists()) {
                        str2 = str3;
                        i = parseInt;
                    }
                }
            }
            if (str2 != null) {
                return str2;
            }
            throw new IOException("Error: unable to find dx binary in $ANDROID_HOME");
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0057  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0078  */
        /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        protected java.io.File getBuiltPath() {
            /*
                r5 = this;
                java.io.File r0 = new java.io.File
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r2 = "out"
                r1.<init>(r2)
                java.lang.String r2 = java.io.File.separator
                r1.append(r2)
                java.lang.String r2 = "production"
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                boolean r1 = r0.isDirectory()
                if (r1 == 0) goto L52
                com.stericson.RootShell.containers.RootClass$AnnotationsFinder$3 r1 = new com.stericson.RootShell.containers.RootClass$AnnotationsFinder$3
                r1.<init>()
                java.io.File[] r1 = r0.listFiles(r1)
                int r2 = r1.length
                if (r2 <= 0) goto L52
                java.io.File r2 = new java.io.File
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                java.lang.String r0 = r0.getAbsolutePath()
                java.lang.String r0 = java.lang.String.valueOf(r0)
                r3.<init>(r0)
                java.lang.String r0 = java.io.File.separator
                r3.append(r0)
                r0 = 0
                r0 = r1[r0]
                java.lang.String r0 = r0.getName()
                r3.append(r0)
                java.lang.String r0 = r3.toString()
                r2.<init>(r0)
                goto L53
            L52:
                r2 = 0
            L53:
                java.lang.String r0 = "classes"
                if (r2 != 0) goto L76
                java.io.File r1 = new java.io.File
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                java.lang.String r4 = "bin"
                r3.<init>(r4)
                java.lang.String r4 = java.io.File.separator
                r3.append(r4)
                r3.append(r0)
                java.lang.String r3 = r3.toString()
                r1.<init>(r3)
                boolean r3 = r1.isDirectory()
                if (r3 == 0) goto L76
                r2 = r1
            L76:
                if (r2 != 0) goto Lab
                java.io.File r1 = new java.io.File
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                java.lang.String r4 = "build"
                r3.<init>(r4)
                java.lang.String r4 = java.io.File.separator
                r3.append(r4)
                java.lang.String r4 = "intermediates"
                r3.append(r4)
                java.lang.String r4 = java.io.File.separator
                r3.append(r4)
                r3.append(r0)
                java.lang.String r0 = java.io.File.separator
                r3.append(r0)
                java.lang.String r0 = "debug"
                r3.append(r0)
                java.lang.String r0 = r3.toString()
                r1.<init>(r0)
                boolean r0 = r1.isDirectory()
                if (r0 == 0) goto Lab
                r2 = r1
            Lab:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.stericson.RootShell.containers.RootClass.AnnotationsFinder.getBuiltPath():java.io.File");
        }
    }

    public static void main(String[] strArr) {
        try {
            if (strArr.length == 0) {
                new AnnotationsFinder();
            } else {
                new RootClass(strArr);
            }
        } catch (Exception e) {
            displayError(e);
        }
    }
}
