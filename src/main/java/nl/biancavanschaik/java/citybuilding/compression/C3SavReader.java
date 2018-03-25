package nl.biancavanschaik.java.citybuilding.compression;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static nl.biancavanschaik.java.citybuilding.compression.InputStreamHelper.*;

public class C3SavReader {
    public void readFile(String file) throws IOException {
        try (InputStream input = new FileInputStream(file)) {
            readData(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @param input
     * @throws IOException
     */
    private void readData(InputStream input) throws IOException {
        input.skip(8); // 2x integer
        skipCompressedChunk(input); // image grid
        skipCompressedChunk(input); // edge grid
        skipCompressedChunk(input); // building id grid
        int[] terrainGrid = readCompressedChunk(input, stream -> {
            int[] data = new int[GRID_SIZE * GRID_SIZE];
            for (int i = 0; i < data.length; i++) {
                data[i] = readUShort(stream);
            }
            return data;
        });
        for (int y = 0; y < GRID_SIZE; y++) {
            boolean hasTerrain = false;
            for (int x = 0; x < GRID_SIZE; x++) {
                int terrain = terrainGrid[y * GRID_SIZE + x];
                char symbol;
                if ((terrain & TERRAIN_OUTSIDE_MAP) == TERRAIN_OUTSIDE_MAP) {
                    symbol = 0;
                } else if ((terrain & TERRAIN_TREE) > 0) {
                    symbol = 'Y';
                } else if ((terrain & TERRAIN_ROCK) > 0) {
                    symbol = 'O';
                } else if ((terrain & TERRAIN_WATER) > 0) {
                    symbol = '~';
                } else if ((terrain & TERRAIN_BUILDING) > 0) {
                    symbol = 'B';
                } else if ((terrain & TERRAIN_SCRUB) > 0) {
                    symbol = 'v';
                } else if ((terrain & TERRAIN_GARDEN) > 0) {
                    symbol = 'z';
                } else if ((terrain & TERRAIN_ROAD) > 0) {
                    symbol = '=';
                } else if ((terrain & TERRAIN_AQUEDUCT) > 0) {
                    symbol = '|';
                } else if ((terrain & TERRAIN_ELEVATION) > 0) {
                    symbol = '/';
                } else if ((terrain & TERRAIN_ACCESS_RAMP) > 0) {
                    symbol = '\\';
                } else if ((terrain & TERRAIN_WALL) > 0) {
                    symbol = 'w';
                } else if ((terrain & TERRAIN_GATEHOUSE) > 0) {
                    symbol = 'W';
                } else if ((terrain & TERRAIN_MEADOW) > 0) {
                    symbol = '_';
                } else if ((terrain & TERRAIN_RUBBLE) > 0) {
                    symbol = ';';
                } else {
                    symbol = '.';
                }
                if (symbol > 0) {
                    System.out.print(symbol);
                    hasTerrain = true;
                }
            }
            if (hasTerrain) {
                System.out.println();
            }
        }
    }
    
    private static final int GRID_SIZE = 162;
    private static final int
            TERRAIN_TREE = 1,
            TERRAIN_ROCK = 2,
            TERRAIN_WATER = 4,
            TERRAIN_BUILDING = 8,
            TERRAIN_SCRUB = 0x10,
            TERRAIN_GARDEN = 0x20,
            TERRAIN_ROAD = 0x40,
            TERRAIN_RESERVOIR_RANGE = 0x80,
            TERRAIN_AQUEDUCT = 0x100,
            TERRAIN_ELEVATION = 0x200,
            TERRAIN_ACCESS_RAMP = 0x400,
            TERRAIN_MEADOW = 0x800,
            TERRAIN_RUBBLE = 0x1000,
            TERRAIN_FOUNTAIN_RANGE = 0x2000,
            TERRAIN_WALL = 0x4000,
            TERRAIN_GATEHOUSE = 0x8000,
            TERRAIN_OUTSIDE_MAP = TERRAIN_TREE | TERRAIN_WATER;

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Please specify a C3 savegame to read");
            return;
        }
        new C3SavReader().readFile(args[0]);
    }
}
