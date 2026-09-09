import mayflower.*;
import java.util.Random;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class World3 extends World {
    boolean isMoving = false;
    // Global variable
    private static final int size = 25;

    public World3() {
        setBackground("img/world3background.png");
        generateWorld();
        Mayflower.stopMusic("audio/world2.mp3");
        Mayflower.playMusic("audio/world3.mp3");
        addObject(new Mario(2,scoreTracker.getScore()),0,150);
        showText("Score: ? Lives: ?", 10, 30, Color.WHITE);
        // makeWaterfallBridgeStructure();
        // makeHoleStructure();
        // makeWaterfallStructure();
    }

    public void makeHill(int length, int height, int bottomPixel, int leftPixel) {
        bottomPixel = 750 - (size * (750 - bottomPixel) / 16);
        leftPixel = size * leftPixel / 16;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                if (i == height - 1) {
                    if (j == 0) {
                        addObject(new HillTop("img/Level3/hillblockleft.png"), leftPixel + (j * size), bottomPixel - (i * size));
                    } else if (j == length - 1) {
                        addObject(new HillTop("img/Level3/hillblockright.png"), leftPixel + (j * size), bottomPixel - (i * size));
                    } else {
                        addObject(new HillTop("img/Level3/hillblocktop.png"), leftPixel + (j * size), bottomPixel - (i * size));
                    }
                } else {
                    addObject(new Hill("img/Level3/hillblock.png"), leftPixel + (j * size), bottomPixel - (i * size));
                }
            }
        }
    }

    public void makeGround(int length, int height, int bottomPixel, int leftPixel) {
        bottomPixel = 750 - (size * (750 - bottomPixel) / 16);
        leftPixel = size * leftPixel / 16;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                if (i == height - 1) {
                    addObject(new Grass("img/Level3/grassblock.png"), leftPixel + (j * size), bottomPixel - (i * size));
                } else {
                    addObject(new Ground(), leftPixel + (j * size), bottomPixel - (i * size));
                }
            }
        }
    }

    public void makeWaterfall(int length, int height, int bottomPixel, int leftPixel) {
        bottomPixel = 750 - (size * (750 - bottomPixel) / 16);
        leftPixel = size * leftPixel / 16;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                addObject(new Waterfall(), leftPixel + (j * size), bottomPixel - (i * size));
            }
        }
    }

    public void makePlatform(int length, int height, int bottomPixel, int leftPixel) {
        bottomPixel = 750 - (size * (750 - bottomPixel) / 16);
        leftPixel = size * leftPixel / 16;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                if (j == 0) {
                    addObject(new Log("img/logleft.png"), leftPixel + (j * size), bottomPixel - (i * size));
                } else if (j == length - 1) {
                    addObject(new Log("img/logright.png"), leftPixel + (j * size), bottomPixel - (i * size));
                } else {
                    addObject(new Log("img/log.png"), leftPixel + (j * size), bottomPixel - (i * size));
                }
            }
        }
    }

    public void makeVine(int length, int height, int bottomPixel, int leftPixel) {
        bottomPixel = 750 - (size * (750 - bottomPixel) / 16);
        leftPixel = size * leftPixel / 16;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                addObject(new Vine(), leftPixel + (j * size), bottomPixel - (i * size));
            }
        }
    }

    public int makeBasicStructure(int start) {
        makeGround(21, 5, 750, start + 0);
        makeHill(6, 7, 670, start + 224);
        makeVine(1, 9, 670, start + 320);
        start = size*(start/16);
        addObject(new Koopa(),start+50,625);
        addObject(new Cherry(),start+50,625);
        addObject(new Koopa(),start+125,625);
        addObject(new Cherry(),start+125,625);
        addObject(new Koopa(),start+450,450);
        addObject(new Cherry(),start+450,450);
        return 336;
    }

    public int makeWaterfallStructure(int start) {
        makeWaterfall(10, 11, 750, start + 304);
        makeHill(3, 12, 750, start + 464);
        makeHill(4, 7, 750, start + 480);
        makeHill(7, 5, 750, start + 448);
        makePlatform(2, 1, 574, start + 336);
        makePlatform(2, 1, 574, start + 400);
        makeHill(3, 12, 750, start + 256);
        makePlatform(13, 1, 558, start + 64);
        makeWaterfall(11, 9, 750, start + 80);
        makeHill(3, 12, 750, start + 32);
        makeHill(3, 10, 750, start + 0);
        makeHill(4, 8, 750, start + 32);
        makeGround(3, 5, 750, start + 0);
        start = size*(start/16);
        addObject(new Koopa(),start+125,550);
        addObject(new Cherry(),start+125,550);
        addObject(new Koopa(),200+start,425);
        addObject(new Cherry(),200+start,425);
        addObject(new Koopa(),300+start,425);
        addObject(new Cherry(),300+start,425);
        addObject(new Koopa(),400+start,425);
        addObject(new Cherry(),400+start,425);
        return 560;
    }

    public int makeHoleStructure(int start) {
        makeGround(14, 5, 750, start + 16);
        makeHill(4, 9, 670, start + 16);
        makeHill(6, 6, 670, start + 16);
        makeHill(7, 3, 670, start + 16);
        makeHill(2, 4, 670, start + 208);
        start = size*(start/16);
        addObject(new Koopa(),75+start,625);
        addObject(new Cherry(),75+start,625);
        addObject(new Koopa(),50+start,550);
        addObject(new Cherry(),50+start,550);
        addObject(new Koopa(),100+start,475);
        addObject(new Cherry(),100+start,475);
        return 240;
    }

    public int makeWaterfallBridgeStructure(int start) {
        makeGround(9, 5, 750, start + 0);
        makeGround(16, 5, 750, start + 208);
        makeHill(5, 11, 670, start + 64);
        makeHill(9, 7, 670, start + 0);
        makeHill(7, 11, 670, start + 208);
        makeHill(16, 7, 670, start + 208);
        makeWaterfall(4, 16, 750, start + 144);
        makePlatform(6, 1, 670, start + 128);
        start = size*(start/16);
        addObject(new Koopa(),125+start,625);
        addObject(new Cherry(),125+start,625);
        addObject(new Koopa(),400+start,625);
        addObject(new Cherry(),400+start,625);
        addObject(new Koopa(),50+start,450);
        addObject(new Cherry(),50+start,450);
        return 464;
    }
    public int makeFinalStructure(int start)
    {
        makeGround(18,5,750,start+0);
        makeGround(5,5,750,start+544);
        makeVine(1,5,510,start+80);
        makeWaterfall(16,23,750,start+288);
        makeHill(5,18,670,start+544);
        makeVine(1,9,382,start+608);
        makeHill(4,18,670,start+224);
        makeHill(9,15,670,start+96);
        makeHill(5,10,670,start+48);
        makeHill(5,7,670,start+16);
        makePlatform(5,1,382,356+start);
        makeVine(1,7,670,start);
        return 720;
    }
    public void generateWorld() {
        int position = 0;
        for (int i = 0; i < 3; i++) {
            int randomNum = new Random().nextInt(4) + 1;
            switch (randomNum) {
                case 1:
                    position += makeBasicStructure(position);
                    break;
                case 2:
                    position += makeWaterfallStructure(position);
                    break;
                case 3:
                    position += makeHoleStructure(position);
                    break;
                case 4:
                    position += makeWaterfallBridgeStructure(position);
                    break;
            }
        }
        makeFinalStructure(position);
    }

    public void act() {
    }
}
