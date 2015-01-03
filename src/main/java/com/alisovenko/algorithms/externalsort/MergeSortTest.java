package com.alisovenko.algorithms.externalsort;

import com.google.common.base.Charsets;
import com.google.common.base.Stopwatch;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author alisovenko 19.06.14
 */
public class MergeSortTest {
    private static int recordsCounter = 0;
    public static void main(String[] args) throws IOException {
        generate();

        Stopwatch stopwatch = Stopwatch.createStarted();
        PolyphaseMergeSort.sort("numbers", 1000, 15, new SortableInformation());
        System.out.printf("Size: %d, written: %d, time: %d\n", 1000, recordsCounter, stopwatch.elapsed(TimeUnit.SECONDS));

        recordsCounter=0;
        generate();
        stopwatch = Stopwatch.createStarted();
        PolyphaseMergeSort.sort("numbers", 100000, 10, new SortableInformation());
        System.out.printf("Size: %d, written: %d, time: %d\n", 100000, recordsCounter, stopwatch.elapsed(TimeUnit.SECONDS));

    }

    private static void generate() throws IOException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        int max = 1<< 15;
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("numbers"), Charsets.UTF_8)) {
            for (int i = 0; i < 2_000_000; i++) {
                writer.write(Integer.toString(ThreadLocalRandom.current().nextInt(max)));
                writer.newLine();
                writer.flush();
            }
        }

        System.out.printf("Generated! Time: %d\n", stopwatch.elapsed(TimeUnit.SECONDS));
    }

    private static class SortableInformation implements SortableRecordInformation {

        @Override
        public Comparator getComparator() {
            return new Comparator() {
                @Override
                public boolean relation(Object a, Object b) {
                    return ((SortableRecord)a).key() > ((SortableRecord) b).key();
                }
            };
        }

        @Override
        public Comparator getEqualToComparator() {
            return new Comparator() {
                @Override
                public boolean relation(Object a, Object b) {
                    return ((SortableRecord)a).key() == ((SortableRecord) b).key();
                }
            };
        }

        @Override
        public SortableRecordReader newSortableRecordReader(File f) throws IOException {
            return new FileRecordReader(new BufferedReader(new FileReader(f), 1024*1024));
        }

        @Override
        public SortableRecordWriter newSortableRecordWriter(File f) throws IOException {
            return new FileRecordWriter(new BufferedWriter(new FileWriter(f), 1024*1024));
        }
    }

    private static class FileRecordReader implements SortableRecordReader {
        private final BufferedReader bufferedReader;

        private FileRecordReader(BufferedReader bufferedReader) {
//            System.out.println("Created");
            this.bufferedReader = bufferedReader;
        }

        @Override
        public SortableRecord readSortableRecord() throws IOException {
            final String s = bufferedReader.readLine();
            if (s == null) {
                return null;
            }
            return new SortableRecord() {
                @Override
                public int key() {
                    return Integer.valueOf(s);
                }

                @Override
                public String toString() {
                    return s;
                }
            };
        }

        @Override
        public void close() throws IOException {
//            System.out.println("closed");
            bufferedReader.close();
        }

        @Override
        public void mark(int lookAheadLimit) throws IOException {
            bufferedReader.mark(lookAheadLimit);
        }

        @Override
        public void reset() throws IOException {
            bufferedReader.reset();
        }
    }

    private static class FileRecordWriter implements SortableRecordWriter {
        private final BufferedWriter bufferedWriter;

        private FileRecordWriter(BufferedWriter bufferedWriter) {
            this.bufferedWriter = bufferedWriter;
        }

        @Override
        public void writeSortableRecord(SortableRecord r) throws IOException {
            recordsCounter++;
            bufferedWriter.write(Integer.toString(r.key()));
            bufferedWriter.newLine();
        }

        @Override
        public void close() throws IOException {
            bufferedWriter.close();
        }
    }
}
