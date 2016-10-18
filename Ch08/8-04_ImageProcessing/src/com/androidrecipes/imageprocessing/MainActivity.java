
package com.androidrecipes.imageprocessing;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v8.renderscript.*;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity implements
        View.OnClickListener {

    private ImageView mImage;
    private boolean mOriginal = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImage = new ImageView(this);
        mImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mImage.setImageResource(R.drawable.sol);
        mImage.setOnClickListener(this);
        
        setContentView(mImage);
    }

    @Override
    public void onClick(View v) {
        if (mOriginal) {
            drawWavy(mImage, R.drawable.sol);
        } else {
            mImage.setImageResource(R.drawable.sol);
        }
        //Toggle state
        mOriginal = !mOriginal;
    }

    private void drawWavy(ImageView iv, int imID) {
        Bitmap bmIn = BitmapFactory.decodeResource(
                getResources(), imID);
        Bitmap bmOut = Bitmap.createBitmap(bmIn.getWidth(),
                bmIn.getHeight(), bmIn.getConfig());
        //Initialize the RenderScript context
        RenderScript rs = RenderScript.create(this);
        //Create data allocations
        Allocation allocIn = Allocation.createFromBitmap(rs, bmIn,
                Allocation.MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT);
        Allocation allocOut = Allocation.createTyped(rs,
                allocIn.getType());
        //Obtain script instance and initial parameters
        ScriptC_wavy script = new ScriptC_wavy(rs,
                getResources(), R.raw.wavy);
        script.set_in(allocIn);
        script.set_height(bmIn.getHeight());
        //Run the script
        script.forEach_root(allocIn, allocOut);
        
        allocOut.copyTo(bmOut);
        iv.setImageBitmap(bmOut);
        //Tear down the RenderScript context
        rs.destroy();
    }

}
