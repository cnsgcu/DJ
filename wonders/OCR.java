package home.journal.algorithm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


class Query
{
    List<Integer> idxs;
    List<Integer> vals;
    int[] hist = new int[10];
}

class Record
{
    int digit;
    int[] pixels = new int[256];

    Record(String csv)
    {
        digit = csv.charAt(0) - '0';

        for (int i = 2; i < csv.length(); i += 2)
        {
            pixels[(i >> 1) - 1] = csv.charAt(i) - '0';
        }
    }
}

public class OCR
{
    static private void combinationGenerator(List<List<Integer>> bag, int n, int cap, int val, int[] buf)
    {
        if (buf.length - n <= cap - val)
        {
            if (n == buf.length)
            {
                final List<Integer> list = new ArrayList<>(buf.length);
                for (int item : buf)
                {
                    list.add(item);
                }

                bag.add(list);
            }
            else
            {
                buf[n] = val;
                combinationGenerator(bag, n + 1, cap, val + 1, buf);
                combinationGenerator(bag, n, cap, val + 1, buf);
            }
        }
    };

    private static List<List<Integer>> combination(int len, int cap)
    {
        final List<List<Integer>> bag = new LinkedList<>();

        combinationGenerator(bag, 0, cap, 0, new int[len]);

        return bag;
    }

    static private void binaryGenerator(List<List<Integer>> bag, int[] buf, int pos)
    {
        if (pos == buf.length)
        {
            final List<Integer> list = new ArrayList<>(buf.length);

            for (int item : buf)
            {
                list.add(item);
            }

            bag.add(list);
        }
        else
        {
            buf[pos] = 0;
            binaryGenerator(bag, buf, pos + 1);
            buf[pos] = 1;
            binaryGenerator(bag, buf, pos + 1);
        }
    };

    private static List<Query> make_query(List<Integer> comb)
    {
        final List<Query> queries = new LinkedList<>();
        final List<List<Integer>> bag = new LinkedList<>();

        binaryGenerator(bag, new int[comb.size()], 0);

        for (final List<Integer> item : bag)
        {
            final Query query = new Query();
            query.idxs = comb;
            query.vals = item;

            queries.add(query);
        }

        return queries;
    }

    static private long sum(int[] bag)
    {
        int total = 0;
        for (int item : bag)
        {
            total += item;
        }

        return total;
    }

    static private int max_idx(double[] bag)
    {
        int max_idx = 0;
        for (int i = 0; i < bag.length; ++i)
        {
            if (bag[max_idx] < bag[i])
            {
                max_idx = i;
            }
        }
        return max_idx;
    }

    public static void main(String... args)
    {
        final long init = System.currentTimeMillis();
        final List<List<Integer>> bag = combination(2, 256);

        final List<Query> queries = new ArrayList<>(32640 * 4);

        for (final List<Integer> item : bag)
        {
            queries.addAll(make_query(item));
        }

        final URL url = OCR.class.getClassLoader().getResource("data/OCRpixels.csv");

        try (final FileInputStream stream = new FileInputStream(new File(url.toURI()));
             final Scanner scanner = new Scanner(stream, StandardCharsets.UTF_8.name()))
        {
            final List<Record> records = new ArrayList<>(50_000);

            while (scanner.hasNextLine())
            {
                final Record record = new Record(scanner.nextLine());
                records.add(record);

                for (int i = 0; i < queries.size() - 3; i += 4)
                {
                    final Query query = queries.get(i);

                    final int offset = (record.pixels[query.idxs.get(0)] << 1) + record.pixels[query.idxs.get(1)];
                    queries.get(i + offset).hist[record.digit] += 1;
                }
            }

            final int[][] hist = new int[10][10];

            for (final Record record : records)
            {
                final double[] prob = new double[10];

                for (int i = 0; i < queries.size() - 3; i += 4)
                {
                    final Query query = queries.get(i);
                    final int offset = (record.pixels[query.idxs.get(0)] << 1) + record.pixels[query.idxs.get(1)];
                    final double count = (double) sum(queries.get(i + offset).hist);

                    for (int j = 0; j < 10; ++j)
                    {
                        prob[j] += Math.log(queries.get(i + offset).hist[j] / count);
                    }
                }

                hist[record.digit][max_idx(prob)] += 1;
            }

            for (int i = 0; i < 10; ++i)
            {
                for (int j = 0; j < 10; ++j)
                {
                    System.out.print(hist[i][j]);
                    System.out.print(" ");
                }

                System.out.println();
            }
        }
        catch (IOException | URISyntaxException e)
        {
            e.printStackTrace();
        }

        System.out.println(System.currentTimeMillis() - init);
    }
}
