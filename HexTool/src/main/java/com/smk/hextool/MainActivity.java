package com.smk.hextool;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.smk.hextool.utils.HexUtils;
import com.smk.hextool.utils.Logutil;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button btn_hex_xor,btn_remove;
    private EditText ed_hex;
    private TextView tv_xor_result;
    private HexHandler mHexHandler;


    //校验值：=（字节3+字节4+字节5+字节6+…+字节N）^0xFF+1（帧尾占用两个字节）
    void reqHandleHex(final String hexString) {
        Logutil.i(TAG, "reqHandleHex() hex : "
                + (null != hexString || hexString.length() <= 0 ? hexString : "null"));
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(hexString)) {
                    String sumHex = HexUtils.makeChecksum(hexString);
                    String xorHex = HexUtils.hexXor(sumHex, "FF");
                    String sumHex2 = HexUtils.makeChecksum(xorHex+" 01 ");
                    String xor_result_text = getString(R.string.xor_result_text);
                    xor_result_text = xor_result_text + sumHex2;
                    // Send message
                    Message msg = mHexHandler.obtainMessage();
                    msg.what = HexHandler.MSG_HEX_TO_XOR_RESULT;
                    msg.obj = xor_result_text;
                    mHexHandler.sendMessage(msg);
                } else {

                }
            }
        }).start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_hex_xor:
                reqHandleHex(ed_hex.getText().toString());
                break;
            case R.id.btn_remove:
                if(null != ed_hex && ed_hex.getText().toString().length() > 0){
                    ed_hex.setText("");
                }
                break;
        }
    }

    private void initViews() {
        btn_hex_xor = (Button) findViewById(R.id.btn_hex_xor);
        btn_remove = (Button) findViewById(R.id.btn_remove);
        ed_hex = (EditText) findViewById(R.id.ed_hex);
        tv_xor_result = (TextView) findViewById(R.id.tv_xor_result);

        btn_hex_xor.setOnClickListener(this);
        btn_remove.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initHexHandler();
        initViews();
    }

    private void initHexHandler() {
        mHexHandler = new HexHandler();
    }

    private class HexHandler extends Handler {
        private static final int MSG_HEX_TO_XOR_RESULT = 1;

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_HEX_TO_XOR_RESULT:
                    tv_xor_result.setText((String) msg.obj);
                    break;
            }
        }
    }

}
