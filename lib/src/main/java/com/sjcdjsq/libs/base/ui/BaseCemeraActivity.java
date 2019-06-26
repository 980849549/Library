package com.sjcdjsq.libs.base.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.sjcdjsq.libs.utils.AndroidUtils;
import com.lib.cooby.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public abstract class BaseCemeraActivity extends BaseActivity {
    /**
     * 请求相机
     */
    public static final int REQUEST_CODE_GETIMAGE_BYCAMERA = 1;

    private final String FILE_SAVEPATH = Environment
            .getExternalStorageDirectory().getAbsolutePath() + setPhotoSaveDir();

    private String protraitPath;
    private File protraitFile;

    // 拍照保存的绝对路径
    private Uri getCameraTempFile() {
        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
            File savedir = new File(FILE_SAVEPATH);
            if (!savedir.exists()) {
                savedir.mkdirs();
            }
        } else {
            Toast.makeText(mContext, "无法保存上传的头像，请检查SD卡是否挂载"
                    , Toast.LENGTH_SHORT).show();
            return null;
        }
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());
        // 照片命名
        String cropFileName = "camera_" + timeStamp + ".jpg";
        // 裁剪头像的绝对路径
        protraitPath = FILE_SAVEPATH + cropFileName;
        protraitFile = new File(protraitPath);
        return Uri.fromFile(protraitFile);
    }

    /**
     * 相机拍照
     *
     * @param
     */
    protected void startActionCamera() {
        final String[] permissions = {Manifest.permission.CAMERA};
        if (!AndroidUtils.requestPermissions(this, permissions, 0)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, this.getCameraTempFile());
            startActivityForResult(intent, REQUEST_CODE_GETIMAGE_BYCAMERA);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, this.getCameraTempFile());
            startActivityForResult(intent, REQUEST_CODE_GETIMAGE_BYCAMERA);
        } else {
            Toast.makeText(this, R.string.no_jurisdiction, Toast.LENGTH_SHORT).show();
        }
    }

    public abstract String setPhotoSaveDir();

    public abstract void returnCemeraPhotoPath(String filePath);

    @Override
    protected void onActivityResult(final int requestCode,
                                    final int resultCode, final Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_GETIMAGE_BYCAMERA:
                    returnCemeraPhotoPath(protraitPath);// 拍照后裁剪
                    break;
            }
        }
    }
}
