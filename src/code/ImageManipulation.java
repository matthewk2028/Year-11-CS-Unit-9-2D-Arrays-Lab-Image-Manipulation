package code;

import image.Pixel;
import image.APImage;

    public class ImageManipulation {

        /**
         * CHALLENGE 0: Display Image
         * Write a statement that will display the image in a window
         */
        public static void main(String[] args) {
            APImage image = new APImage("cyberpunk2077.jpg");
            image.draw();

            grayScale("cyberpunk2077.jpg");
            blackAndWhite("cyberpunk2077.jpg");
            edgeDetection("cyberpunk2077.jpg", 20);
            reflectImage("cyberpunk2077.jpg");
            rotateImage("cyberpunk2077.jpg");

        }

        /**
         * CHALLENGE ONE: Grayscale
         * <p>
         * INPUT: the complete path file name of the image
         * OUTPUT: a grayscale copy of the image
         * <p>
         * To convert a colour image to grayscale, we need to visit every pixel in the image ...
         * Calculate the average of the red, green, and blue components of the pixel.
         * Set the red, green, and blue components to this average value.
         */
        public static void grayScale(String filename) {
            APImage original = new APImage(filename);
            APImage grayscale = original.clone();

            int width = grayscale.getWidth();
            int height = grayscale.getHeight();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Pixel pixel = grayscale.getPixel(x, y);

                    int red = pixel.getRed();
                    int green = pixel.getGreen();
                    int blue = pixel.getBlue();
                    int average = (red + green + blue) / 3;

                    pixel.setRed(average);
                    pixel.setGreen(average);
                    pixel.setBlue(average);

                }
            }

            grayscale.draw();
        }

        /**
         * A helper method that can be used to assist you in each challenge.
         * This method simply calculates the average of the RGB values of a single pixel.
         *
         * @param pixel
         * @return the average RGB value
         */
        private static int getAverageColour(Pixel pixel) {
            int red = pixel.getRed();
            int green = pixel.getGreen();
            int blue = pixel.getBlue();
            return (red + green + blue) / 3;
        }

        /**
         * CHALLENGE TWO: Black and White
         * <p>
         * INPUT: the complete path file name of the image
         * OUTPUT: a black and white copy of the image
         * <p>
         * To convert a colour image to black and white, we need to visit every pixel in the image ...
         * Calculate the average of the red, green, and blue components of the pixel.
         * If the average is less than 128, set the pixel to black
         * If the average is equal to or greater than 128, set the pixel to white
         */
        public static void blackAndWhite(String pathOfFile) {
            APImage original = new APImage(pathOfFile);
            APImage blackWhite = original.clone();

            int width = blackWhite.getWidth();
            int height = blackWhite.getHeight();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Pixel pixel = blackWhite.getPixel(x, y);

                    int red = pixel.getRed();
                    int green = pixel.getGreen();
                    int blue = pixel.getBlue();
                    int average = (red + green + blue) / 3;

                    if (average < 128) {
                        pixel.setRed(0);
                        pixel.setGreen(0);
                        pixel.setBlue(0);
                    } else {
                        pixel.setRed(255);
                        pixel.setGreen(255);
                        pixel.setBlue(255);
                    }
                }


            }
            blackWhite.draw();
        }

        /**
         * CHALLENGE Three: Edge Detection
         * <p>
         * INPUT: the complete path file name of the image
         * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.
         * <p>
         * Edge detection is an image processing technique for finding the boundaries of objects within images.
         * It works by detecting discontinuities in brightness. Edge detection is used for image segmentation
         * and data extraction in areas such as image processing, computer vision, and machine vision.
         * <p>
         * There are many different edge detection algorithms. We will use a basic edge detection technique
         * For each pixel, we will calculate ...
         * 1. The average colour value of the current pixel
         * 2. The average colour value of the pixel to the left of the current pixel
         * 3. The average colour value of the pixel below the current pixel
         * If the difference between 1. and 2. OR if the difference between 1. and 3. is greater than some threshold value,
         * we will set the current pixel to black. This is because an absolute difference that is greater than our threshold
         * value should indicate an edge and thus, we colour the pixel black.
         * Otherwise, we will set the current pixel to white
         * NOTE: We want to be able to apply edge detection using various thresholds
         * For example, we could apply edge detection to an image using a threshold of 20 OR we could apply
         * edge detection to an image using a threshold of 35
         *
         */
        public static void edgeDetection(String pathToFile, int threshold) {
            APImage original = new APImage(pathToFile);
            APImage edgeDetection = original.clone();

            int width = edgeDetection.getWidth();
            int height = edgeDetection.getHeight();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Pixel currentPixel = edgeDetection.getPixel(x, y);

                    int currentRed = currentPixel.getRed();
                    int currentGreen = currentPixel.getGreen();
                    int currentBlue = currentPixel.getBlue();
                    int currentAvg = (currentRed + currentGreen + currentBlue) / 3;

                    int leftAvg = currentAvg;
                    int belowAvg = currentAvg;

                    if (x > 0) {
                        Pixel leftPixel = edgeDetection.getPixel(x - 1, y);
                        int leftRed = leftPixel.getRed();
                        int leftBlue = leftPixel.getBlue();
                        int leftGreen = leftPixel.getGreen();
                        leftAvg = (leftRed + leftGreen + leftBlue) / 3;
                    }

                    if (y < height - 1) {
                        Pixel belowPixel = edgeDetection.getPixel(x, y + 1);
                        int belowRed = belowPixel.getRed();
                        int belowGreen = belowPixel.getGreen();
                        int belowBlue = belowPixel.getBlue();
                        belowAvg = (belowRed + belowGreen + belowBlue) / 3;
                    }

                    int diffLeft = Math.abs(currentAvg - leftAvg);
                    int diffBelow = Math.abs(currentAvg - belowAvg);

                    if (diffLeft > threshold || diffBelow > threshold) {

                        currentPixel.setRed(0);
                        currentPixel.setGreen(0);
                        currentPixel.setBlue(0);
                    } else {
                        currentPixel.setRed(255);
                        currentPixel.setGreen(255);
                        currentPixel.setBlue(255);
                    }
                }

            }
            edgeDetection.draw();
        }

        /**
         * CHALLENGE Four: Reflect Image
         * <p>
         * INPUT: the complete path file name of the image
         * OUTPUT: the image reflected about the y-axis
         *
         */
        public static void reflectImage(String pathToFile) {
            APImage original = new APImage("cyberpunk2077.jpg");
            APImage reflected = original.clone();

            int width = reflected.getWidth();
            int height = reflected.getHeight();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width / 2; x++) {

                    int leftX = x;
                    int rightX = width - 1 - x;

                    Pixel leftPixel = reflected.getPixel(leftX, y);
                    Pixel rightPixel = reflected.getPixel(rightX, y);

                    Pixel temp = leftPixel.clone();
                    reflected.setPixel(leftX, y, rightPixel.clone());
                    reflected.setPixel(rightX, y, temp);
                }
            }
            reflected.draw();

        }


        /**
         * CHALLENGE Five: Rotate Image
         * <p>
         * INPUT: the complete path file name of the image
         * OUTPUT: the image rotated 90 degrees CLOCKWISE
         *
         *
         */
        public static void rotateImage(String pathToFile) {
            APImage original = new APImage("cyberpunk2077.jpg");

            int originalWidth = original.getWidth();
            int originalHeight = original.getHeight();

            APImage rotated = new APImage(originalHeight, originalWidth);

            for (int y = 0; y < originalHeight; y++) {
                for (int x = 0; x < originalWidth; x++) {
                    Pixel originalPixel = original.getPixel(x, y);

                    int newX = originalHeight - 1 - y;
                    int newY = x;

                    rotated.setPixel(newX, newY, originalPixel.clone());
                }
            }
            rotated.draw();
        }
    }


