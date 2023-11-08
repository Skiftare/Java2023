package edu.hw6;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {
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

    };

    static AbstractFilter regularFile = Files::isRegularFile;
    static AbstractFilter readable = Files::isReadable;

    static AbstractFilter largerThan(long size) {
        return entry -> Files.size(entry) > size;
    }
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

    static AbstractFilter regexContains(String regex) {
        final Pattern pattern = Pattern.compile(regex);
        return entry -> pattern.matcher(entry.getFileName().toString()).find();
    }


    boolean accept(Path entry) throws IOException;


}
