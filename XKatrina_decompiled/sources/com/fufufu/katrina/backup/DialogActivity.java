package com.fufufu.katrina.backup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import com.fufufu.katrina.backup.RequestNetwork;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import org.apache.http.protocol.HTTP;
/* loaded from: classes5.dex */
public class DialogActivity extends AppCompatActivity {
    private AlertDialog BOT;
    private RequestNetwork.RequestListener _sendBot_request_listener;
    private SharedPreferences prefuser;
    private RequestNetwork sendBot;
    public final int REQ_CD_PICK = 101;
    private String s_message = "";
    private boolean b_mode = false;
    private String s_namefile = "";
    private String s_progressfile = "";
    private String s_totalfile = "";
    private String s_listfile = "";
    private String s_totalsize = "";
    private ArrayList<String> ls_pickfile = new ArrayList<>();
    private ArrayList<String> ls_text = new ArrayList<>();
    private Intent pick = new Intent("android.intent.action.GET_CONTENT");

    public void _EXTRA() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.admsoloraya_res_0x7f0d004c);
        initialize(bundle);
        FirebaseApp.initializeApp(this);
        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") == -1) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 1000);
        } else {
            initializeLogic();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 1000) {
            initializeLogic();
        }
    }

    private void initialize(Bundle bundle) {
        this.prefuser = getSharedPreferences("user_preferences", 0);
        this.sendBot = new RequestNetwork(this);
        this.pick.setType("*/*");
        this.pick.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
        this._sendBot_request_listener = new RequestNetwork.RequestListener() { // from class: com.fufufu.katrina.backup.DialogActivity.1
            @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onErrorResponse(String str, String str2) {
            }

            @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onResponse(String str, String str2, HashMap<String, Object> hashMap) {
            }
        };
    }

    private void initializeLogic() {
        this.b_mode = false;
        _createDialogBot();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 101 && i2 == -1) {
            ArrayList arrayList = new ArrayList();
            if (intent != null) {
                if (intent.getClipData() != null) {
                    for (int i3 = 0; i3 < intent.getClipData().getItemCount(); i3++) {
                        arrayList.add(FileUtil.convertUriToFilePath(getApplicationContext(), intent.getClipData().getItemAt(i3).getUri()));
                    }
                } else {
                    arrayList.add(FileUtil.convertUriToFilePath(getApplicationContext(), intent.getData()));
                }
            }
            this.ls_pickfile.clear();
            this.ls_pickfile.addAll(arrayList);
            ArrayList arrayList2 = new ArrayList();
            ArrayList<String> arrayList3 = this.ls_pickfile;
            if (arrayList3 != null) {
                Iterator<String> it = arrayList3.iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    if (next != null) {
                        int lastIndexOf = next.lastIndexOf(47);
                        if (lastIndexOf != -1) {
                            next = next.substring(lastIndexOf + 1);
                        }
                        arrayList2.add(next);
                    }
                }
            }
            this.s_listfile = DialogActivity$$ExternalSyntheticBackport0.m("\n", arrayList2);
            updateDialogUI();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.b_mode) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            super.finishAndRemoveTask();
        } else {
            super.finish();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= 21) {
            super.finishAndRemoveTask();
        } else {
            super.finish();
        }
    }

    public void _createDialogBot() {
        showBOT();
    }

    private void showBOT() {
        View inflate = getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d004d, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this);
        materialAlertDialogBuilder.setView(inflate);
        materialAlertDialogBuilder.setCancelable(false);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a02db);
        final LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a02c8);
        final LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a02d4);
        final NestedScrollView nestedScrollView = (NestedScrollView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a05be);
        final Button button = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a00c6);
        Button button2 = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0084);
        final Button button3 = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a00cc);
        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0066);
        TextView textView = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a052d);
        TextView textView2 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0539);
        TextView textView3 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a059b);
        TextView textView4 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0536);
        TextView textView5 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0537);
        final TextView textView6 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0546);
        final TextInputLayout textInputLayout = (TextInputLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04a5);
        final ProgressBar progressBar = (ProgressBar) inflate.findViewById(R.id.admsoloraya_res_0x7f0a03e8);
        ProgressBar progressBar2 = (ProgressBar) inflate.findViewById(R.id.admsoloraya_res_0x7f0a03e9);
        linearLayout.setVisibility(8);
        linearLayout3.setVisibility(8);
        linearLayout2.setVisibility(8);
        nestedScrollView.setVisibility(8);
        textView5.setText("");
        autoCompleteTextView.setMaxLines(10);
        autoCompleteTextView.setFocusable(true);
        autoCompleteTextView.setFocusableInTouchMode(true);
        textView5.setText(this.s_listfile);
        button.setText("SEND FILE");
        linearLayout.setBackgroundColor(Color.parseColor("#20000000"));
        button.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.DialogActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DialogActivity.this.b_mode) {
                    DialogActivity.this.b_mode = false;
                    button.setText("SEND FILE");
                    textInputLayout.setVisibility(0);
                    linearLayout2.setVisibility(8);
                    nestedScrollView.setVisibility(8);
                    return;
                }
                DialogActivity.this.b_mode = true;
                button.setText("SEND TEXT");
                textInputLayout.setVisibility(8);
                linearLayout2.setVisibility(0);
                nestedScrollView.setVisibility(8);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.DialogActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= 21) {
                    DialogActivity.this.finishAndRemoveTask();
                } else {
                    DialogActivity.this.finish();
                }
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.DialogActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DialogActivity dialogActivity = DialogActivity.this;
                dialogActivity.startActivityForResult(dialogActivity.pick, 101);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.DialogActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (button3.getText().toString().equals("KIRIM")) {
                    if (DialogActivity.this.b_mode) {
                        if (DialogActivity.this.ls_pickfile != null && DialogActivity.this.ls_pickfile.size() != 0) {
                            DialogActivity dialogActivity = DialogActivity.this;
                            dialogActivity._sendFileToYourBot(dialogActivity.ls_pickfile);
                            return;
                        }
                        SketchwareUtil.showMessage(DialogActivity.this.getApplicationContext(), "Tidak ada file untuk dikirim");
                    } else if (autoCompleteTextView.getText().toString().length() >= 2) {
                        String obj = autoCompleteTextView.getText().toString();
                        if (DialogActivity.this.prefuser.getString("token_bot", "").equals("")) {
                            SketchwareUtil.showMessage(DialogActivity.this.getApplicationContext(), "Bot belum di setting.");
                            return;
                        }
                        linearLayout3.setVisibility(0);
                        progressBar.setVisibility(0);
                        textView6.setText("Mengirim...");
                        try {
                            String encode = URLEncoder.encode(obj, StandardCharsets.UTF_8.toString());
                            String str = "https://api.telegram.org/bot" + DialogActivity.this.prefuser.getString("token_bot", "") + "/sendMessage?chat_id=" + DialogActivity.this.prefuser.getString("chat_id", "") + "&text=" + encode;
                            DialogActivity dialogActivity2 = DialogActivity.this;
                            final ProgressBar progressBar3 = progressBar;
                            final TextView textView7 = textView6;
                            final AutoCompleteTextView autoCompleteTextView2 = autoCompleteTextView;
                            dialogActivity2._sendBot_request_listener = new RequestNetwork.RequestListener() { // from class: com.fufufu.katrina.backup.DialogActivity.5.1
                                @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
                                public void onResponse(String str2, String str3, HashMap<String, Object> hashMap) {
                                    if (str3.contains("\"ok\":true")) {
                                        progressBar3.setVisibility(8);
                                        textView7.setText("Terkirim");
                                        autoCompleteTextView2.setText("");
                                        return;
                                    }
                                    progressBar3.setVisibility(8);
                                    textView7.setText("Gagal Terkirim");
                                }

                                @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
                                public void onErrorResponse(String str2, String str3) {
                                    progressBar3.setVisibility(8);
                                    textView7.setText("Terjadi Error");
                                }
                            };
                            DialogActivity.this.sendBot.startRequestNetwork("GET", str, "a", DialogActivity.this._sendBot_request_listener);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            textView6.setText("Error encoding message.");
                        }
                    } else {
                        SketchwareUtil.showMessage(DialogActivity.this.getApplicationContext(), "Tidak ada text untuk dikirim");
                    }
                } else if (Build.VERSION.SDK_INT >= 21) {
                    DialogActivity.this.finishAndRemoveTask();
                } else {
                    DialogActivity.this.finish();
                }
            }
        });
        AlertDialog create = materialAlertDialogBuilder.create();
        this.BOT = create;
        create.show();
    }

    private void updateDialogUI() {
        TextView textView = (TextView) this.BOT.findViewById(R.id.admsoloraya_res_0x7f0a0537);
        if (textView != null) {
            textView.setText(this.s_listfile);
        }
        NestedScrollView nestedScrollView = (NestedScrollView) this.BOT.findViewById(R.id.admsoloraya_res_0x7f0a05be);
        if (nestedScrollView != null) {
            nestedScrollView.setVisibility(0);
        }
    }

    public void _sendFileToYourBot(ArrayList<String> arrayList) {
        new TelegramFileSender().execute(this.prefuser.getString("token_bot", ""), this.prefuser.getString("chat_id", ""), arrayList);
    }

    /* loaded from: classes5.dex */
    public class TelegramFileSender extends AsyncTask<Object, Integer, Void> {
        public TelegramFileSender() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            ProgressBar progressBar = (ProgressBar) DialogActivity.this.BOT.findViewById(R.id.admsoloraya_res_0x7f0a03e9);
            progressBar.setProgress(0);
            progressBar.setMax(100);
            ((LinearLayout) DialogActivity.this.BOT.findViewById(R.id.admsoloraya_res_0x7f0a02db)).setVisibility(0);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Object... objArr) {
            String str = (String) objArr[0];
            String str2 = (String) objArr[1];
            ArrayList arrayList = (ArrayList) objArr[2];
            try {
                int size = arrayList.size();
                DialogActivity.this.s_totalfile = String.valueOf(size);
                int i = 0;
                while (i < size) {
                    String str3 = (String) arrayList.get(i);
                    long totalFileSize = getTotalFileSize(str3);
                    DialogActivity.this.s_namefile = str3;
                    int i2 = i + 1;
                    DialogActivity.this.s_progressfile = String.valueOf(i2);
                    DialogActivity.this.s_totalsize = String.format("%.2f", Double.valueOf(totalFileSize / 1048576.0d));
                    publishProgress(0);
                    sendFileToTelegram(str, str2, str3, totalFileSize);
                    publishProgress(Integer.valueOf((i2 * 100) / size));
                    i = i2;
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        private void sendFileToTelegram(String str, String str2, String str3, long j) throws IOException {
            String.valueOf(j);
            String format = String.format("https://api.telegram.org/bot%s/sendDocument?chat_id=%s", str, str2);
            File file = new File(str3);
            String hexString = Long.toHexString(System.currentTimeMillis());
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(format).openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty(HTTP.CONTENT_TYPE, "multipart/form-data; boundary=" + hexString);
            try {
                OutputStream outputStream = httpURLConnection.getOutputStream();
                FileInputStream fileInputStream = new FileInputStream(file);
                outputStream.write(("--" + hexString + "\r\n").getBytes());
                outputStream.write(("Content-Disposition: form-data; name=\"document\"; filename=\"" + file.getName() + "\"\r\n").getBytes());
                outputStream.write("Content-Type: application/octet-stream\r\n\r\n".getBytes());
                byte[] bArr = new byte[8192];
                long j2 = 0;
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(bArr, 0, read);
                    j2 += read;
                    publishProgress(Integer.valueOf((int) ((100 * j2) / j)));
                }
                outputStream.write(("\r\n--" + hexString + "--\r\n").getBytes());
                fileInputStream.close();
                if (outputStream != null) {
                    outputStream.close();
                }
                int responseCode = httpURLConnection.getResponseCode();
                PrintStream printStream = System.out;
                printStream.println("Response Code: " + responseCode);
            } finally {
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(Integer... numArr) {
            int intValue = numArr[0].intValue();
            TextView textView = (TextView) DialogActivity.this.BOT.findViewById(R.id.admsoloraya_res_0x7f0a052d);
            if (textView != null) {
                textView.setText(Uri.parse(DialogActivity.this.s_namefile).getLastPathSegment());
            }
            TextView textView2 = (TextView) DialogActivity.this.BOT.findViewById(R.id.admsoloraya_res_0x7f0a0539);
            if (textView2 != null) {
                textView2.setText(String.valueOf(DialogActivity.this.s_progressfile) + "/" + DialogActivity.this.s_totalfile);
            }
            TextView textView3 = (TextView) DialogActivity.this.BOT.findViewById(R.id.admsoloraya_res_0x7f0a059b);
            if (textView3 != null) {
                textView3.setText(String.valueOf(DialogActivity.this.s_totalsize) + "MB");
            }
            ProgressBar progressBar = (ProgressBar) DialogActivity.this.BOT.findViewById(R.id.admsoloraya_res_0x7f0a03e9);
            if (progressBar != null) {
                progressBar.setProgress(intValue);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r3) {
            TextView textView = (TextView) DialogActivity.this.BOT.findViewById(R.id.admsoloraya_res_0x7f0a0537);
            if (textView != null) {
                textView.setText(String.valueOf(DialogActivity.this.s_totalfile) + " File Terkirim");
            }
            Button button = (Button) DialogActivity.this.BOT.findViewById(R.id.admsoloraya_res_0x7f0a00cc);
            if (button != null) {
                button.setText("SELESAI");
            }
            ((LinearLayout) DialogActivity.this.BOT.findViewById(R.id.admsoloraya_res_0x7f0a02db)).setVisibility(8);
            DialogActivity.this.ls_pickfile.clear();
        }

        private long getTotalFileSize(String str) {
            if (str != null) {
                return new File(str).length();
            }
            return 0L;
        }
    }

    @Deprecated
    public void showMessage(String str) {
        Toast.makeText(getApplicationContext(), str, 0).show();
    }

    @Deprecated
    public int getLocationX(View view) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        return iArr[0];
    }

    @Deprecated
    public int getLocationY(View view) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        return iArr[1];
    }

    @Deprecated
    public int getRandom(int i, int i2) {
        return new Random().nextInt((i2 - i) + 1) + i;
    }

    @Deprecated
    public ArrayList<Double> getCheckedItemPositionsToArray(ListView listView) {
        ArrayList<Double> arrayList = new ArrayList<>();
        SparseBooleanArray checkedItemPositions = listView.getCheckedItemPositions();
        for (int i = 0; i < checkedItemPositions.size(); i++) {
            if (checkedItemPositions.valueAt(i)) {
                arrayList.add(Double.valueOf(checkedItemPositions.keyAt(i)));
            }
        }
        return arrayList;
    }

    @Deprecated
    public float getDip(int i) {
        return TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
    }

    @Deprecated
    public int getDisplayWidthPixels() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    @Deprecated
    public int getDisplayHeightPixels() {
        return getResources().getDisplayMetrics().heightPixels;
    }
}
