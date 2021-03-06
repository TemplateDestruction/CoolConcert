package com.breakout.myapplication.user;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.breakout.myapplication.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.media.MediaRecorder.VideoSource.CAMERA;


public class EditingAct extends AppCompatActivity {


    private static final int PIC_CROP = 1;
    private static final int GET_IMAGE = 2;
    @BindView(R.id.edit_desc_user)
    EditText editUserDesc;

    @BindView(R.id.edit_nick_user)
    EditText editUserNick;

    @BindView(R.id.edit_user_photo)
    ImageView editUserPhoto;

    String[] strings;
    Intent backIntent;
    private Uri selectedImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_editing_layout);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        strings = Objects.requireNonNull(intent.getExtras()).getStringArray("strings");
        assert strings != null;
        editUserDesc.setText(strings[0]);
        editUserNick.setText(strings[1]);
        backIntent = getIntent();
    }

    public void onEditClick(View view) {
        switch (view.getId()) {
            case R.id.edit_user_photo:
                addNewImage();
                break;
            case R.id.btn_ok:
                strings[0] = editUserDesc.getText().toString();
                strings[1] = editUserNick.getText().toString();
                backIntent.putExtra("strings_new", strings);
                setResult(500, backIntent);
                finish();
            case R.id.btn_cancel:
                setResult(100);
                finish();
                break;
        }

    }

    private void addNewImage() {
        String permCamera = Manifest.permission.CAMERA;
        String permReadStorage = Manifest.permission.READ_EXTERNAL_STORAGE;
        int grantCamera = ContextCompat.checkSelfPermission(this, permCamera);
        int grantReadStorage = ContextCompat.checkSelfPermission(this, permReadStorage);
        if (grantCamera != PackageManager.PERMISSION_GRANTED || grantReadStorage != PackageManager.PERMISSION_GRANTED) {
            String[] permList = new String[2];
            permList[0] = permCamera;
            permList[1] = permReadStorage;
            ActivityCompat.requestPermissions(this, permList, 1);
        } else {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"Select Picture"), GET_IMAGE);

            // включение камеры
//            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            startActivityForResult(intent, CAMERA);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this,"Разрешение предоставлено", Toast.LENGTH_SHORT).show();
                // perform your action here
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), GET_IMAGE);

            } else {
                Toast.makeText(this,"Разрешение не предоставлено", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
                    if(resultCode == RESULT_OK){

                        // получение изображения с камеры
//                        Bitmap image = (Bitmap) data.getExtras().get("data");
//                        editUserPhoto.setImageBitmap(image);

                        if (requestCode == GET_IMAGE) {
                            selectedImage = data.getData();

                            performCrop();

                        } else if (requestCode == PIC_CROP) {
                            // Получим кадрированное изображение
                            Bitmap thePic = data.getExtras().getParcelable("data");
                            // передаём его в ImageView
                            editUserPhoto.setImageBitmap(thePic);
                            backIntent.putExtra("image", thePic);
                        }
            }
        }
    }

    private void performCrop(){
        try {
            // Намерение для кадрирования. Не все устройства поддерживают его
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(selectedImage, "image/*");
            cropIntent.putExtra("crop", "true");
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            cropIntent.putExtra("outputX", 128);
            cropIntent.putExtra("outputY", 128);
            cropIntent.putExtra("return-data", true);
            startActivityForResult(cropIntent, PIC_CROP);
        }
        catch(ActivityNotFoundException anfe){
            String errorMessage = "Извините, но ваше устройство не поддерживает кадрирование";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
