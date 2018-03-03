package com.example.anyi.bomb;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by Anyi on 2018/1/15.
 */

public class textalbumActivity extends AppCompatActivity {

    private Button take_photo,select_photo,upload;
    public static final int TAKE_PHOTO = 1;
    public static final int SELECT_PHOTO = 2;
    private ImageView  imageview;
    private EditText eName;
    private Uri imageUri;
    private String pimagePath;


    private EditText E_name;
    private EditText E_price;
    private EditText E_mess;


    private TextInputLayout L_name;
    private TextInputLayout L_price;
    private TextInputLayout L_mess;

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textalbum);
        Bmob.initialize(this, "c6f075c125f3c78b69088d3c81022306");
        find();
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        E_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        E_mess.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        E_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        select_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //从相册中选取图片
                select_photo();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name,
                        mess,
                        icon_path;
                final double price;

                if (pimagePath.equals("") || E_name.getText().toString().equals("")||E_mess.getText().toString().equals("")||E_price.getText().toString().equals("")){
                    //Toast.makeText(getApplicationContext(),"请提交完整信息！",Toast.LENGTH_SHORT).show();
                    //System.out.println(pimagePath+"\n"+eName.getText().toString());
                }
                else
                {
                    //Toast.makeText(getApplicationContext(),pimagePath,Toast.LENGTH_SHORT).show();
                    //System.out.println(pimagePath+"\n"+eName.getText().toString());
                    name = E_name.getText().toString();
                    mess = E_mess.getText().toString();
                    price = Double.parseDouble(E_price.getText().toString());
                    icon_path = pimagePath;
                    final BmobFile bmobFile = new BmobFile(new File(icon_path));
                    bmobFile.upload(new UploadFileListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null){
                                BmobUser userMe = BmobUser.getCurrentUser();
                                System.out.println("ss");
                                text goods = new text();
                                goods.setUid(userMe.getObjectId());
                                goods.setName(name);
                                goods.setPrice(price);
                                goods.setMess(mess);
                                goods.setIcon(bmobFile);
                                goods.save(new SaveListener<String>() {
                                    @Override
                                    public void onStart() {
                                        Toast.makeText(textalbumActivity.this, "正在上传", Toast.LENGTH_SHORT).show();
                                        super.onStart();
                                    }

                                    @Override
                                    public void done(String s, BmobException e) {
                                        if (e == null) {
                                            Toast.makeText(textalbumActivity.this, "添加数据成功", Toast.LENGTH_SHORT).show();
                                            System.out.println("添加数据成功");
                                            finish();
                                        }
                                        else {
                                            Toast.makeText(textalbumActivity.this, "添加数据失败", Toast.LENGTH_SHORT).show();
                                            System.out.println("添加数据失败");
                                        }
                                    }
                                });
                            }
                            else
                            {
                                Toast.makeText(textalbumActivity.this, "添加数据失败，：", Toast.LENGTH_SHORT).show();
                                System.out.println("添加数据失败");
                            }
                        }
                    });

                }
            }
        });


    }

    private void find() {
        //        take_photo = (Button) findViewById(R.id.take_photo);
        select_photo = (Button) findViewById(R.id.select_photo);
        imageview = (ImageView) findViewById(R.id.imageview);
        upload = (Button) findViewById(R.id.upload);
        //eName = (EditText) findViewById(R.id.editName);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        L_name = (TextInputLayout) findViewById(R.id.editName);
        L_price= (TextInputLayout) findViewById(R.id.editPrice);
        L_mess = (TextInputLayout) findViewById(R.id.editMess);
//        L_tel = (TextInputLayout) findViewById(R.id.log_tel);

        E_name = L_name.getEditText();
        E_price = L_price.getEditText();
        E_mess = L_mess.getEditText();
     //   E_tel = L_tel.getEditText();


    }


    /**
     *拍照获取图片
     **/
    /*public void take_photo() {
        //创建File对象，用于存储拍照后的图片
        File outputImage = new File(getExternalCacheDir(),"output_image.jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();b
        }
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(this,"com.example.anyi.bfileprovider",outputImage);
        }else {
            imageUri = Uri.fromFile(outputImage);
        }
        //启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent,TAKE_PHOTO);
    }*/
    /**
     * 从相册中获取图片
     * */
    public void select_photo() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else {
            openAlbum();
        }
    }
    /**
     * 打开相册的方法
     * */
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,SELECT_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO :
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        imageview.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case SELECT_PHOTO :
                if (resultCode == RESULT_OK) {
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT > 19) {
                        //4.4及以上系统使用这个方法处理图片
                        handleImgeOnKitKat(data);
                    }else {
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }
    /**
     *4.4以下系统处理图片的方法
     * */
    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);
    }


    /**
     * 4.4及以上系统处理图片的方法
     * */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void handleImgeOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this,uri)) {
            //如果是document类型的uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                //解析出数字格式的id
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
            }else if ("content".equalsIgnoreCase(uri.getScheme())) {
                //如果是content类型的uri，则使用普通方式处理
                imagePath = getImagePath(uri,null);
            }else if ("file".equalsIgnoreCase(uri.getScheme())) {
                //如果是file类型的uri，直接获取图片路径即可
                imagePath = uri.getPath();
            }
            //根据图片路径显示图片
            displayImage(imagePath);
        }
    }
    /**
     * 根据图片路径显示图片的方法
     * */
    private void displayImage(String imagePath) {
        if (imagePath != null) {
            pimagePath = imagePath;
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageview.setImageBitmap(bitmap);
        }else {
            Toast.makeText(this,"failed to get image",Toast.LENGTH_SHORT).show();
//            ToastUtil.showShort(this,"failed to get image");
        }
    }

    /**
     * 通过uri和selection来获取真实的图片路径
     * */
    private String getImagePath(Uri uri,String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1 :
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                }else {
                    Toast.makeText(this,"you need the permission",Toast.LENGTH_SHORT).show();
//                    ToastUtil.showShort(this,"you need the permission");
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
