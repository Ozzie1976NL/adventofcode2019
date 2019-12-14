package com.ozzie.advantofcode.spaceimageformat;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SpaceImageFormat {


    static int[] convertToArray(String imdateData) {
        int length = imdateData.length();
        return IntStream.range(0, length).map(i -> Integer.parseInt(imdateData.substring(i, i + 1))).toArray();
    }


    static public long checkImage(int[] imageData, int width, int height) {

        int[][] layers = dermineLayers(imageData, width, height);
        int noLayers = layers.length;


        // Determine which layer has the least zeros
        long leastZeros = -1;
        int layerWithLeastZeros = -1;
        for (int i = 0; i < noLayers; i++) {
            long noZeros = countOccurencesDigit(layers[i], 0);
            if (leastZeros == -1) {
                leastZeros = noZeros;
                layerWithLeastZeros = i;
            }
            if (noZeros < leastZeros) {
                leastZeros = noZeros;
                layerWithLeastZeros = i;
            }
        }

        return countOccurencesDigit(layers[layerWithLeastZeros], 1) * countOccurencesDigit(layers[layerWithLeastZeros], 2);
    }

    public static int[][] dermineLayers(int[] imageData, int width, int height) {
        int sizeLayer = width * height;
        int noLayers = imageData.length / sizeLayer;


        int[][] layers = new int[noLayers][];

        for (int i = 0; i < noLayers; i++) {
            layers[i] = Arrays.copyOfRange(imageData, i * sizeLayer, i * sizeLayer + sizeLayer);
        }
        return layers;
    }

    public static void drawPicture(int[] imageData, int width, int height) {
        int[][] layers = dermineLayers(imageData, width, height);
        int noLayers = layers.length;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                PixelColor currentPixelColor = PixelColor.TRANSPARENT;
                for (int layerNo = 0; layerNo < noLayers; layerNo++) {
                    int offSet = width * y + x;
                    currentPixelColor = PixelColor.valueOf(layers[layerNo][offSet]);
                    if (!PixelColor.TRANSPARENT.equals(currentPixelColor)) {
                        break;
                    }
                }
                System.out.print(currentPixelColor.character);
            }
            System.out.println();
        }
    }

    public static long countOccurencesDigit(int[] data, int digit) {
        return IntStream.of(data).filter(d -> d == digit).count();
    }

    enum PixelColor {
        BLACK(0, "-"),
        WHITE(1, "*"),
        TRANSPARENT(2, " ");

        private final int    value;
        private final String character;

        PixelColor(int value, String character) {

            this.value = value;
            this.character = character;
        }

        static PixelColor valueOf(int integer) {
            switch (integer) {
                case 0:
                    return BLACK;
                case 1:
                    return WHITE;
                case 2:
                    return TRANSPARENT;
            }
            return null;
        }
    }
}
