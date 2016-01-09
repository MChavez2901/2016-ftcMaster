package com.qualcomm.ftcrobotcontroller.opmodes;

import android.app.Activity;
import android.os.Bundle;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.android.JavaCameraView;
import org.opencv.core.Mat;
/*
 * CameraActivity opens the back camera and displays the view to the screen.
 * By calling takePhoto(), the current view is saved to a jpg file in mat format.
 * see http://docs.opencv.org/java/org/opencv/android/CameraBridgeViewBase.html
 */
public class CameraActivity extends Activity implements CvCameraViewListener2 {
    private static final String TAG = "CameraActivity";

    private static CameraBridgeViewBase mOpenCvCameraView;
    private Mat mRgba; // Mat to hold current camera frame in
    public static Mat FMat;
    private int cameraIdBack;
    public int pixel;
    public double[] data;
    public static boolean color;

    // Called as part of the activity lifecycle when this activity is starting.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_camera);
       // cameraIdBack = JavaCameraView.CAMERA_ID_BACK;
        // mOpenCvCameraView = (CameraBridgeViewBase)findViewById(R.id.surface_view);
        // help

        // ODO set mOpenCvCameraView to the JavaCameraView in the layout
       // mOpenCvCameraView.setCvCameraViewListener(this);
        // TODO set mOpenCvCameraView's listener to this class
        // done
    }

    // Called as part of the activity lifecycle when this activity is no longer visible. close the camera
    @Override
    public void onPause() {
        super.onPause();
        mOpenCvCameraView.disableView();
    }

    // Called as part of the activity lifecycle when this activity becomes visible. open the camera
    @Override
    public void onResume() {
        super.onResume();
        mOpenCvCameraView.enableView();
    }

    // This method is invoked when camera preview has started.
    @Override
    public void onCameraViewStarted(int width, int height) {
        mRgba = new Mat();
        //TODO initialize mRgba to a new Mat
    }

    // This method is invoked when camera preview has been stopped for some reason.
    @Override
    public void onCameraViewStopped() { /* do nothing */}

    // This method is invoked when delivery of the frame needs to be done.
    @Override
    public Mat onCameraFrame(CvCameraViewFrame inputFrame) {
        mRgba = inputFrame.rgba();
        for (int x = 0; x < mRgba.cols(); x++) {
            for (int y = 0; y < mRgba.rows(); y++) {
                data = mRgba.get(x, y);

            }
        }
        if (color) {
            color = false;
        }
        if (!color) {
            color = true;
        }
        //ODO set mRgba to the inputFrame's rgba Mat

        return mRgba;
    }

    public static void enableBeaconSeeker() {

        mOpenCvCameraView.enableView();
    }

    public void test() {

    }
}