package com.breakout.myapplication.concerts.concert.meeting;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.breakout.myapplication.R;
import com.breakout.myapplication.concerts.concert.meeting.chat.ChatActivity;
import com.breakout.myapplication.concerts.concert.meeting.qrcodetest.TestActivity;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FastMeetingActivity extends AppCompatActivity {

    final int TYPE_PHOTO = 1;
    private static final int REQUEST_CODE_PHOTO = 1;
    @BindView(R.id.qr_img)
    ImageView QRcode;

    @BindView(R.id.scan_qr_btn)
    Button scanQRBtn;

    @BindView(R.id.btnToChat)
    Button btnChat;

//    @BindView(R.id.text_decode)
//    TextView decodedText;
//    static {
//        System.loadLibrary("iconv");
//    }
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_layout);
        ButterKnife.bind(this);
        context = this;
        btnChat.setVisibility(View.GONE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    private void createQR() {
        new AsyncQRGenerator(this).execute("userID");

//        new java.util.Timer().schedule(
//                new java.util.TimerTask() {
//                    @Override
//                    public void run() {
//                        startActivity(new Intent(context, RegistrAct.class));
//                        // your code here
//                    }
//                },
//                10000
//        );
        btnChat.setVisibility(View.VISIBLE);

    }

    public void onScanQR(View view) {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////        intent.putExtra(MediaStore.EXTRA_OUTPUT, generateFileUri(TYPE_PHOTO));
//        startActivityForResult(intent, REQUEST_CODE_PHOTO);
//        Intent intent = new Intent(this, MainActivity2.class);
//        startActivity(intent);
//        addNewImage();
        startActivityForResult(new Intent(this, TestActivity.class), 100);


    }
    private void addNewImage() {
        String permission = Manifest.permission.CAMERA;
        int grant = ContextCompat.checkSelfPermission(this, permission);
        if (grant != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[1];
            permission_list[0] = permission;
            ActivityCompat.requestPermissions(this, permission_list, 1);
        } else {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"Select Picture"), 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
//            decodedText.setText(data.getStringExtra("decoded"));
            startActivity(new Intent(this, ChatActivity.class));
        } catch (Exception ignored) {}

    }

    String detectBarCode(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        int[] intArray = new int[bitmap.getWidth() * bitmap.getHeight()];
        bitmap.getPixels(intArray, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        LuminanceSource source = new RGBLuminanceSource(bitmap.getWidth(), bitmap.getHeight(), intArray);
        Reader reader = new QRCodeReader();
        try {
            Result result = reader.decode(new BinaryBitmap(new HybridBinarizer(source)));
            return result.getText();
        } catch (NotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (ChecksumException e) {
            e.printStackTrace();
            return null;
        } catch (FormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onPreviewFrame(byte[] data) {
        ImageScanner scanner;
        scanner = new ImageScanner();
        scanner.setConfig(0, Config.X_DENSITY, 3);
        scanner.setConfig(0, Config.Y_DENSITY, 3);
        String lastScannedCode;
        Image codeImage = null;
        codeImage.setData(data);
        int result = scanner.scanImage(codeImage);
        if (result != 0) {
            SymbolSet syms = scanner.getResults();
            for (Symbol sym : syms) {
                lastScannedCode = sym.getData();
            }
        }
    }

    public void mettingClick(View view) {
        switch (view.getId()) {
            case R.id.button2:
                createQR();
                break;
            case R.id.btnToChat:
                startActivity(new Intent(this, ChatActivity.class));
                break;
        }

    }

//    private Uri generateFileUri(int type) {
//        File file = null;
//        switch (type) {
//            case TYPE_PHOTO:
//                file = new File(directory.getPath() + "/" + "photo_"
//                        + System.currentTimeMillis() + ".jpg");
//                break;
//            case TYPE_VIDEO:
//                file = new File(directory.getPath() + "/" + "video_"
//                        + System.currentTimeMillis() + ".mp4");
//                break;
//        }
//        Log.d(TAG, "fileName = " + file);
//        return Uri.fromFile(file);
//    }
//
//    private void createDirectory() {
//        directory = new File(
//                Environment
//                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
//                "MyFolder");
//        if (!directory.exists())
//            directory.mkdirs();
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE_PHOTO) {
//            if (resultCode == RESULT_OK) {
//                if (data != null) {
//                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//                    Intent intent = new Intent(this, QRScanActivity.class);
//                    intent.putExtra("bitmap", bitmap);
//                    startActivityForResult(intent, 100);
//
//                }
//            }
//        }
//    }

    public class AsyncQRGenerator extends AsyncTask<String, Integer, ArrayList<Bitmap>> {

        private Activity activity;

        public AsyncQRGenerator(Activity a) {
            this.activity = a;
        }
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(activity);
            dialog.setTitle("Generating...");
            dialog.setMessage("Generating QR code, please wait...");
            dialog.setCancelable(false);
            dialog.show();
            super.onPreExecute();
        }

        private static final int WIDTH = 400; /* ширина выходного изображения */

        @Override
        protected ArrayList<Bitmap> doInBackground(String... params) {
            ArrayList<Bitmap> list = new ArrayList<Bitmap>();
            for (int i = 0; i < params.length; i++) {
                try {
            /* Используется библиотека ZXing.
               Первый параметр - исходная строка.
               Второй параметр - формат кода (ZXing умеет не только QR)
               Третий и четвёртый - размер матрицы */
                    BitMatrix matrix = new QRCodeWriter().encode(
                            params[i],
                            com.google.zxing.BarcodeFormat.QR_CODE,
                            WIDTH, WIDTH);
                    /* Конвертируем матрицу битов в картинку */
                    Bitmap bitmap = matrixToBitmap(matrix);
                    /* Сохраняем файл */
                    saveBitmapAsImageFile(bitmap, String.valueOf(i + 1));
                    list.add(bitmap);
                    /* Сообщаем об очередном сгенерированном коде */
                    publishProgress(i + 1, params.length);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
            return list;
        }

        private static final int BLACK = 0xFF000000;
        private static final int WHITE = 0xFFFFFFFF;

        private Bitmap matrixToBitmap(BitMatrix matrix) {
            int width = matrix.getWidth();
            int height = matrix.getHeight();
            Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setPixel(x, y, matrix.get(x, y) ? BLACK : WHITE);
                }
            }
            return image;
        }

        /* Сохраняет bitmap на SD-карту в файл с названием fileName.
       Возвращает true, если сохранение успешно, и false, если сохранить не удалось. */
        private boolean saveBitmapAsImageFile(Bitmap bitmap, String fileName) {
            /* Получаем путь до папки сохранения */
            String storagePath = Environment.getExternalStorageDirectory() + "/GroupLock/";
            File sdDir = new File(storagePath);
            /* Создаём директорию */
            sdDir.mkdirs();

            try {
                /* Создаём необходимые потоки */
                String filePath = sdDir.getPath() + "/" + fileName + ".png";
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);

                /* Сохраняем файл в формате .png */
                BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);

                /* Закрываем потоки */
                bos.flush();
                bos.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            dialog.setMessage(values[0] + " of " + values[1] + " done...");
        }

        @Override
        protected void onPostExecute(ArrayList<Bitmap> bitmap) {
            try {
                /* Закрываем диалоговое окно */
                dialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }

            /* Оповещаем пользователя об успешном завершении */
            String message = "QR codes generated successfully!";
            Toast toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT);
            toast.show();

            QRcode.setImageBitmap(bitmap.get(0));

            super.onPostExecute(bitmap);

        }


    }
}
