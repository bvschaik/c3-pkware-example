package nl.biancavanschaik.java.citybuilding.compression;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

public final class InputStreamHelper {

    public static int readInt(InputStream input) throws IOException {
        try {
            byte[] bytes = readBytes(input, 4);
            return (bytes[0] & 0xff) | ((bytes[1] & 0xff) << 8) | ((bytes[2] & 0xff) << 16) | ((bytes[3] & 0xff) << 24);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static int readUShort(InputStream input) {
        try {
            byte[] bytes = readBytes(input, 2);
            return (bytes[0] & 0xff) | ((bytes[1] & 0xff) << 8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static <T> T readCompressedChunk(InputStream input, Function<InputStream, T> function) throws IOException {
        int length = readInt(input);
        try (InputStream compressedInput = new PkwareInputStream(input, length)) {
            return function.apply(compressedInput);
        }
    }
    
    public static void skipCompressedChunk(InputStream input) throws IOException {
        int length = readInt(input);
        input.skip(length);
    }

    private static byte[] readBytes(InputStream input, int length) throws IOException {
        byte[] bytes = new byte[length];
        if (input.read(bytes) != length) {
            throw new EOFException();
        }
        return bytes;
    }
}
