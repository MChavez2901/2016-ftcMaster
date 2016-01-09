package com.qualcomm.ftcrobotcontroller.opmodes;

import android.graphics.Color;

/**
 * Created by Miguel on 1/8/2016.
 */
public class ColorFunctions {
    public static int buttonx;
   public static int buttony;
    public  static int firstblue;
    public static int firstred;
    public static int relativeRed;
    public static int relativeBlue;
    static int width1;

    public static void  FindxStart(int [][] colorArray,  int width, int height){
        int[] currentColor = new int[3];
        int[] lastColor = new int[3];
        int highestRed = 0;
        int highestBlue = 0;
        int highestRedx = 0;
        int highestBluex = 0;
        int bstreak = 0;
        width1 = width;
        int rstreak = 0;
        int lastBlue = 0;
        int lastRed = 0;
        int blueLine = 0;
        int redLine = 0;
        int firstblueTemp = 0;
         firstblue = 0;
        int firstredTemp = 0;
         firstred = 0;
        int lastbluetemp = 0;
        int lastblue = 0;
        int lastred = 0;
        int lastredtemp = 0;
        boolean firstBluePicked = false;
        boolean firstRedPicked = false;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int maskRGB = colorArray[x][y];
                int currentBlue = Color.blue(maskRGB);
                int currentRed = Color.red(maskRGB);
                if (currentBlue == 255) {
                    if (bstreak > 8) {
                        lastbluetemp = y;

                    }
                    if (!firstBluePicked) {
                        firstblueTemp = y;
                        firstBluePicked = true;
                    }
                    if (lastBlue == 255) {
                        bstreak++;
                    }
                    blueLine++;
                    blueLine += bstreak;
                } else {
                    bstreak = 0;
                }
                if (currentRed == 255) {
                    if (rstreak > 8) {
                        lastredtemp = y;

                    }
                    if (!firstRedPicked) {
                        firstredTemp = y;
                        firstRedPicked = true;
                    }
                    if (lastRed == 255) {
                        rstreak++;
                    }
                    redLine++;
                    redLine += rstreak;
                } else {
                    rstreak = 0;
                }
                lastBlue = currentBlue;
                lastRed = currentRed;
            }
            if (blueLine > highestBlue) {
                highestBluex = x;
                highestBlue = blueLine;
                firstblue = firstblueTemp;
                lastblue = lastbluetemp;

            }
            if (redLine > highestRed) {
                highestRedx = x;
                highestRed = redLine;
                firstred = firstredTemp;
                lastred = lastredtemp;

            }
            firstBluePicked = false;
            firstRedPicked = false;
            firstblueTemp = 0;
            lastbluetemp = 0;
            firstredTemp = 0;
            lastredtemp = 0;
            blueLine = 0;
            redLine = 0;
        }

        //tele(firstred, 0);
        //printHLine(lastred, 0);
        // printHLine(firstblue, 0);
        // printHLine(lastblue, 0);
        findEdge(highestBluex, firstblue, lastred, "BLUE", colorArray, width, height);
        findEdge(highestRedx, firstred, lastred, "RED", colorArray, width, height);

    };
    private static void findEdge(int x, int y1, int y2, String color, int[][] colorArray, int width, int height){
        int[] currentColor;
        int colorVal = 0;
        int startx = 0;
        int endx = 0;
        for (int x1 = x; x1 > 0; x1 += -1) {
            for (int y = y1; y < y2; y++) {

                int maskRGB =colorArray[x1][ y];
                int colortotal = Color.blue(maskRGB) + Color.red(maskRGB);
                if (colortotal > 0) {
                    colorVal++;
                }

            }
            if (colorVal < ((y2 - y1) / 2)) {
                startx = x1;
                //printVLine(startx, 0);
                break;
            }
            colorVal = 0;
        }
        colorVal = 0;

        for (int x1 = x; x1 < width; x1 += 1) {
            for (int y = y1; y < y2; y++) {

                int maskRGB = colorArray[x1][y];
                int colortotal = Color.blue(maskRGB) + Color.red(maskRGB);
                if (colortotal > 0) {
                    colorVal++;
                }

            }
            if (colorVal < ((y2 - y1) / 4)) {
                endx = x1;
                //printVLine(endx, 0);
                break;
            }
            colorVal = 0;
        }
        if (color == "BLUE") {
            relativeBlue = startx ;
        }
        if (color == "RED") {
            relativeRed =startx ;
        }
        findButton(startx, endx, y1, y2, color, colorArray);
    }
    private static void findButton(int x1, int x2, int y1, int y2, String Color1, int[][] colorArray) {
        int blackVal = 0;
        int offset = Math.abs(x1 - x2) / 4;
        int yOffset = 2 * Math.abs(y1 - y2) / 3;
        int[] currentColor = new int[3];
        int blackxVal = 0;
        int blackyVal = 0;

        for (int x = x1 ; x < x2 ; x++) {
            for (int y = y1 ; y < y2;                y++) {
                int maskRGB = colorArray[x][y];
                int colortotal = Color.blue(maskRGB) + Color.red(maskRGB);
                if (colortotal == 0) {
                    blackxVal += x;
                    blackyVal += y;
                    blackVal++;
                }
            }
        }
        if (Math.abs(blackyVal*blackxVal) > 0) {
             buttonx = blackxVal / blackVal;
             buttony = blackyVal / blackVal;


        }
    }

}
