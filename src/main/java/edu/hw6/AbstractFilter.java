package edu.hw6;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    static AbstractFilter smallerThan(long size) {
        return entry -> Files.size(entry) < size;
    }

    static AbstractFilter magicNumber(int... magicBytes) {
        return entry -> {
            try (SeekableByteChannel channel = Files.newByteChannel(entry)) {
                byte[] buffer = new byte[magicBytes.length];
                ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
                channel.read(byteBuffer);
                for (int i = 0; i < magicBytes.length; i++) {
                    if (buffer[i] != (byte) magicBytes[i]) {
                        return false;
                    }
                }
                return true;
            } catch (IOException e) {
                return false;
            }
        };
    }

    static AbstractFilter globMatches(String glob) {
        final PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
        return entry -> matcher.matches(entry.getFileName());
    }

    default AbstractFilter and(AbstractFilter other) {
        return new AbstractFilter() {
            @Override
            public boolean accept(Path entry) throws IOException {
                return AbstractFilter.this.accept(entry) && other.accept(entry);
            }

            public void close() {
                AbstractFilter.this.close();
            }
        };
    }

    default void close() {
    }

    boolean accept(Path entry) throws IOException;

}
