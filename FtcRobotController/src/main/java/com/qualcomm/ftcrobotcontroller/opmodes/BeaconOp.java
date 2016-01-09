package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by Miguel on 1/5/2016.
 */
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Spinner;

import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.ftcrobotcontroller.opmodes.CameraActivity;

import org.opencv.core.Mat;

import java.io.File;

public class BeaconOp extends OpMode {

    public static final String EXTRA_FILE_PATH = "extraFilePath";
    private Mat mSrcMat; // Unaltered Mat to use as base
    private File mFile; // File to get mSrcMat from

    private Bitmap mBitmap; // Bitmap use hold Mat's in View friendly form


@Override
    public void init(){
        CameraActivity.enableBeaconSeeker();

    }
@Override
    public void  loop (){
        telemetry.addData("s", "running");

        telemetry.addData("1", CameraActivity.color);
        if (gamepad1.a) {
            //takephoto
        }
    }
}
