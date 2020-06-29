
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Buffer {

    private static final int INITIAL_SIZE = 1024*64;

    private byte[] bytes = new byte[1024*64];
    private int position = 0;
    private int limit = 0;
    private String file;
    private final BufferedInputStream in;


    public Buffer(String file) {
        this.file= file;
        try {
            in = new BufferedInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public int readInt() {
      require(4);

      int ret = readByte();
      ret |= readByte() << 8;
      ret |= readByte() << 16;
      ret |= readByte() << 24;

      return ret;
    }

    public short readShort() {
        require(2);

        int ret = readByte() | (readByte() << 8);

        return (short)ret;
    }

    public String readString(int size) {
        require(size);
        String ret = new String(bytes, position, size);
        position += size;
        return ret;
    }

    public byte[] readBytes(int size) {

        byte[] ret = new byte[size];
        int offset = 0;
        while (size > 0) {
            int toRead = Math.min(size, bytes.length);
            require(toRead);
            System.arraycopy(bytes, position, ret, offset, toRead);
            position += toRead;
            size -= toRead;
        }

        return ret;
    }
    public int readByte() {
        require(1);
        return bytes[position++] & 0xff;
    }

    public int getPosition()
    {
        return position;
    }

    private void require(int size) {
        if (limit - position < size) {
            refill();
            if (limit - position < size) {
                throw new RuntimeException("Want "+size+" bytes but only "+limit+" bytes remain");
            }
        }
    }

    private void refill() {
        int remaining = limit - position;
        System.arraycopy(bytes, position, bytes, 0, remaining);
        position = 0;
        limit = remaining;

        int free = bytes.length - remaining;
        int read = 0;
        try {
            read = in.read(bytes, remaining, free);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (read != -1) {
            limit += read;
        }
    }

    public void close() throws IOException
    {
        in.close();
    }

    public String getFile()
    {
        return file;
    }

}
