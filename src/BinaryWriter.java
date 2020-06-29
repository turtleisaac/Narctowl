import java.io.*;

public class BinaryWriter {

    private final RandomAccessFile raf;
    private final byte[] buf = new byte[4];

    public static void writeFile(File file, int... bytes) throws IOException {
        BinaryWriter writer = new BinaryWriter(file);
        writer.writeBytes(bytes);
        writer.close();
    }

    public BinaryWriter(File file) throws IOException {
        raf = new RandomAccessFile(file, "rw");
        raf.setLength(0);
    }

    public BinaryWriter(String fileName) throws IOException {
        this(new File(fileName));
    }
    
    public void setPosition(long pos) throws IOException {
        raf.seek(pos);
    }
    
    public long getPosition() throws IOException {
        return raf.getFilePointer();
    }
    
    public void skipBytes(int bytes) throws IOException {
        raf.skipBytes(bytes);
    }

    public void writeInt(int i) throws IOException {
        buf[0] = (byte) (i & 0xff);
        buf[1] = (byte) ((i >> 8) & 0xff);
        buf[2] = (byte) ((i >> 16) & 0xff);
        buf[3] = (byte) ((i >> 24) & 0xff);
        raf.write(buf, 0, 4);
    }

    public void writeShort(short s) throws IOException {
        buf[0] = (byte) (s & 0xff);
        buf[1] = (byte) ((s >> 8) & 0xff);
        raf.write(buf, 0, 2);
    }

    public void writeByte(byte b) throws IOException {
        raf.write(b);
    }

    public void writeBytes(int... bytes) throws IOException {
        for (int b : bytes) {
            raf.write(b);
        }
    }

    public void write(byte[] bytes) throws IOException {
        raf.write(bytes);
    }

    public void write(byte[] bytes, int offset, int length) throws IOException {
        raf.write(bytes, offset, length);
    }

    public void close() throws IOException {
        raf.close();
    }
}
