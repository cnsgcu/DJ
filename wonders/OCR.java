package home.journal.algorithm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    static private void combinationGenerator(List<int[]> bag, int n, int cap, int val, int[] buf)
    {
        if (buf.length - n <= cap - val)
        {
            if (n == buf.length)
            {
                final int[] list = new int[buf.length];
                System.arraycopy(buf, 0, list, 0, buf.length);

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

    private static List<int[]> combination(int len, int cap)
    {
        final List<int[]> bag = new ArrayList<>(combination_len(len, cap));

        combinationGenerator(bag, 0, cap, 0, new int[len]);

        return bag;
    }

    private static int combination_len(int len, int cap)
    {
        int count = 1;
        for (int i = len - 1; i > -1; i--)
        {
            count = (count * (cap - i)) / (len - i);
        }
        
        return count;
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
        final List<int[]> combs = combination(2, 256);

        final int[][] queries = new int[combination_len(2, 256) << 2][10];

        final URL url = OCR.class.getClassLoader().getResource("data/OCRpixels.csv");

        try (final FileInputStream stream = new FileInputStream(new File(url.toURI()));
             final Scanner scanner = new Scanner(stream, StandardCharsets.UTF_8.name()))
        {
            final List<Record> records = new ArrayList<>(50_000);

            while (scanner.hasNextLine())
            {
                final Record record = new Record(scanner.nextLine());
                records.add(record);

                for (int i = 0; i < combs.size(); i++)
                {
                    final int[] comb = combs.get(i);

                    int offset = 0;
                    for (int j = 0; j < comb.length; j++)
                    {
                        offset += (record.pixels[comb[j]] << (comb.length - j - 1));
                    }

                    queries[(i << 2) + offset][record.digit] += 1;
                }
            }

            final int[][] hist = new int[10][10];

            for (final Record record : records)
            {
                final double[] prob = new double[10];

                for (int i = 0; i < combs.size(); i++)
                {
                    final int[] comb = combs.get(i);

                    int offset = 0;
                    for (int j = 0; j < comb.length; j++)
                    {
                        offset += (record.pixels[comb[j]] << (comb.length - j - 1));
                    }

                    final double count = (double) sum(queries[(i << 2) + offset]);
                    for (int j = 0; j < 10; j++)
                    {
                        prob[j] += Math.log(queries[(i << 2) + offset][j] / count);
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
