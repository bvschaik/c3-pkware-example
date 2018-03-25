package nl.biancavanschaik.java.citybuilding.compression;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

public class PkwareInputStreamTest {

    private byte[] compressedBytes = {0x00, 0x04, (byte) 0x82, 0x24, 0x25, (byte) 0x8f, (byte) 0x80, 0x7f};

    @Test
    public void testDecompressExample() throws IOException {
        try (ByteArrayInputStream byteStream = new ByteArrayInputStream(compressedBytes)) {
            PkwareInputStream stream = new PkwareInputStream(byteStream, compressedBytes.length);
            try (InputStreamReader reader = new InputStreamReader(stream)) {
                char[] charBuffer = new char[20];
                int bytesRead = reader.read(charBuffer);
                
                assertEquals(13, bytesRead);
                assertEquals("AIAIAIAIAIAIA", new String(charBuffer, 0, bytesRead));
            }
        }
    }

    @Test
    public void testCloseEmptiesCompressedPartAndKeepsUnderlyingStreamOpen() throws IOException {
        byte[] bytesWithExtraData = new byte[compressedBytes.length + 3];
        System.arraycopy(compressedBytes, 0, bytesWithExtraData, 0, compressedBytes.length);
        bytesWithExtraData[compressedBytes.length] = 1;
        bytesWithExtraData[compressedBytes.length + 1] = 2;
        bytesWithExtraData[compressedBytes.length + 2] = 3;
        try (ByteArrayInputStream byteStream = new ByteArrayInputStream(bytesWithExtraData)) {
            PkwareInputStream stream = new PkwareInputStream(byteStream, compressedBytes.length);

            stream.close();
            
            assertEquals(1, byteStream.read());
            assertEquals(2, byteStream.read());
            assertEquals(3, byteStream.read());
            assertEquals(-1, byteStream.read());
        }
    }
}
