

package miuyongjun.utillibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BitmapUtils {
    private static final int THUMB_WIDTH = 200;
    private static final int THUMB_HEIGHT = 200;
    private static final String IMAGE_FOLDER = Environment.getExternalStorageDirectory().getAbsolutePath() + "/BitmapTemp/";

    public static Bitmap decodeFile(String pathName, int width, int height) {
        Bitmap bitmap = null;
        if (FileUtils.isFileExist(pathName)) {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(pathName, opts);
            opts.inSampleSize = ImageUtils.calculateInSampleSize(opts, width, height);
            opts.inJustDecodeBounds = false;
            opts.inPurgeable = true;
            opts.inInputShareable = true;
            opts.inPreferredConfig = android.graphics.Bitmap.Config.ALPHA_8;
            opts.inDither = true;
            try {
                bitmap = BitmapFactory.decodeFile(pathName, opts);
            } catch (OutOfMemoryError e) {
                System.gc();
                try {
                    bitmap = BitmapFactory.decodeFile(pathName, opts);
                } catch (OutOfMemoryError e1) {
                    System.gc();
                    try {
                        bitmap = BitmapFactory.decodeFile(pathName, opts);
                    } catch (OutOfMemoryError e2) {
                        System.gc();
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (bitmap == null) {
            bitmap = getImageThumbnail(pathName);
        }
        return bitmap;
    }

    public static Bitmap getImageThumbnail(String imagePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);
        options.inJustDecodeBounds = false;
        int h = options.outHeight;
        int w = options.outWidth;
        int beWidth = w / THUMB_WIDTH;
        int beHeight = h / THUMB_HEIGHT;
        int be = 4;
        if (beWidth < beHeight && beHeight >= 1) {
            be = beHeight;
        }
        if (beHeight < beWidth && beWidth >= 1) {
            be = beWidth;
        }
        if (be <= 0) {
            be = 1;
        } else if (be > 3) {
            be = 3;
        }
        options.inSampleSize = be;
        options.inPreferredConfig = Bitmap.Config.ARGB_4444;
        options.inPurgeable = true;
        options.inInputShareable = true;
        try {
            bitmap = BitmapFactory.decodeFile(imagePath, options);
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, THUMB_WIDTH, THUMB_HEIGHT, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        } catch (OutOfMemoryError e) {
            System.gc();
        }
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(10, 10, Bitmap.Config.ALPHA_8);
        }
        return bitmap;
    }

    public static Bitmap createCircularClip(Bitmap input, int width, int height) {
        if (input == null) return null;

        final int inWidth = input.getWidth();
        final int inHeight = input.getHeight();
        final Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);
        final Paint paint = new Paint();
        paint.setShader(new BitmapShader(input, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        final RectF srcRect = new RectF(0, 0, inWidth, inHeight);
        final RectF dstRect = new RectF(0, 0, width, height);
        final Matrix m = new Matrix();
        m.setRectToRect(srcRect, dstRect, Matrix.ScaleToFit.CENTER);
        canvas.setMatrix(m);
        canvas.drawCircle(inWidth / 2, inHeight / 2, inWidth / 2, paint);
        return output;
    }

    /**
     * Byte[]转Bitmap
     */
    public static Bitmap bytes2Bitmap(byte[] data) {
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }


    /**
     * Bitmap转Byte[]
     *
     * @param needRecycle 转换完毕后是否需要回收bitmap
     */
    public static byte[] bitmap2Bytes(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * Bitmap转Drawable
     */
    public static Drawable bitmap2Drawable(Bitmap bitmap) {
        return new BitmapDrawable(bitmap);
    }


    /**
     * Drawable转Bitmap
     */
    public static Bitmap drawable2Bitmap(Drawable drawable) {
        BitmapDrawable bd = (BitmapDrawable) drawable;
        return bd.getBitmap();
    }


    /**
     * Bitmap对象转换TransitionDrawable对象.
     *
     * @param bitmap 要转化的Bitmap对象 imageView.setImageDrawable(td);
     * td.startTransition(200);
     * @return Drawable 转化完成的Drawable对象
     */
    @SuppressWarnings("ResourceType")
    public static TransitionDrawable bitmapToTransitionDrawable(Bitmap bitmap) {
        TransitionDrawable mBitmapDrawable = null;
        try {
            if (bitmap == null) {
                return null;
            }
            mBitmapDrawable = new TransitionDrawable(
                    new Drawable[] { new ColorDrawable(android.R.color.transparent),
                            new BitmapDrawable(bitmap) });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mBitmapDrawable;
    }


    /**
     * Drawable对象转换TransitionDrawable对象.
     *
     * @param drawable 要转化的Drawable对象 imageView.setImageDrawable(td);
     * td.startTransition(200);
     * @return Drawable 转化完成的Drawable对象
     */
    @SuppressWarnings("ResourceType")
    public static TransitionDrawable drawableToTransitionDrawable(Drawable drawable) {
        TransitionDrawable mBitmapDrawable = null;
        try {
            if (drawable == null) {
                return null;
            }
            mBitmapDrawable = new TransitionDrawable(
                    new Drawable[] { new ColorDrawable(android.R.color.transparent), drawable });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mBitmapDrawable;
    }


    /**
     * 将ImageView转换为Bitmap.
     *
     * @param view 要转换为bitmap的View
     * @return byte[] 图片的byte[]
     */
    public static Bitmap imageView2Bitmap(ImageView view) {
        Bitmap bitmap = null;
        try {
            bitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


}
