package com.qualcomm.ftcrobotcontroller.opmodes;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * TeleOp Mode
 * <p/>
 * Enables control of the robot via the gamepad
 */
public class DetectColor extends OpModeCamera {

    int ds2 = 2;  // additional downsampling of the image
    private int looped = 0;
    private long lastLoopTime = 0;
    // set to 1 to disable further downsampling

    /*
     * Code to run when the op mode is first enabled goes here
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
     */
    @Override
    public void init() {
        setCameraDownsampling(8);
        // parameter determines how downsampled you want your images
        // 8, 4, 2, or 1.
        // higher number is more downsampled, so less resolution but faster
        // 1 is original resolution, which is detailed but slow
        // must be called before super.init sets up the camera

        super.init(); // inits camera functions, starts preview callback
    }

    /*
     * This method will be called repeatedly in a loop
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
     */

    @Override
    public void loop() {
        long startTime = System.currentTimeMillis();

        if (imageReady()) { // only do this if an image has been returned from the camera
            int redValue = 0;
            int blueValue = 0;
            int greenValue = 0;
            int[][] colorArray = new int[width] [height];

            Bitmap rgbImage;
            rgbImage = convertYuvImageToRgb(yuvImage, width, height, ds2);
            for (int x = 0; x < width / ds2; x++) {
                for (int y = 0; y < height / ds2; y++) {
                    int pixel = rgbImage.getPixel(x, y);
                    int oldcolors[] = {Color.red(pixel), Color.green(pixel), Color.blue(pixel)};
                    int[] newColors = {0, 0, 0};
                    for (int x1 = 0; x1 <= 2; x1++) {
                        if ((oldcolors[0] + oldcolors[1] + oldcolors[2] > 400) && (oldcolors[0] + oldcolors[1] + oldcolors[2] < 600)
                                && x1 == 0) {
                            // newColors[1] = 100;
                            // newColors[2] = 120;
                            // newColors[0] = 100;
                        }
                        if (x1 == 1) {
                            continue;
                        }
                        if (x1 == 0) {

                        }
                        if ((oldcolors[0] + oldcolors[1] + oldcolors[2] > 440) && oldcolors[x1] > 200
                                && oldcolors[x1] > 50 + .5 * ((oldcolors[1] + oldcolors[0] + oldcolors[2]) - (oldcolors[x1]))) {
                            newColors[1] = 0;
                            newColors[2] = 0;
                            newColors[0] = 0;
                            newColors[x1] = 255;
                        } else {
                            // newColors[x1] = 0;
                        }

                        if (x1 == 0 && oldcolors[1] > oldcolors[2] && oldcolors[0] < (oldcolors[1] + 60)) {
                            newColors[1] = 0;
                            newColors[2] = 0;
                            newColors[0] = 0;

                        }


                        if ((oldcolors[0] + oldcolors[1] + oldcolors[2]) > 700) {
                            newColors[1] = 0;
                            newColors[2] = 0;
                            newColors[0] = 0;
                        }
                    }
                    int color = Color.argb(255, newColors[0], newColors[1], newColors[2]);
                    colorArray[x][y] = color;

                }
            }
            int color = highestColor(redValue, greenValue, blueValue);
            String colorString = "";
            switch (color) {
                case 0:
                    colorString = "RED";
                    break;
                case 1:
                    colorString = "GREEN";
                    break;
                case 2:
                    colorString = "BLUE";
            }
            telemetry.addData("Color:", "Color detected is: " + colorString);

        }
        long endTime = System.currentTimeMillis();
        telemetry.addData("Dims", Integer.toString(width / ds2) + " x " + Integer.toString(height / ds2));
        telemetry.addData("Loop Time", Long.toString(endTime - startTime));
        telemetry.addData("Loop to Loop Time", Long.toString(endTime - lastLoopTime));

        lastLoopTime = endTime;
    }

    @Override
    public void stop() {
        super.stop(); // stops camera functions
    }
}
