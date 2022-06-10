package test1.part2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class CreateStopWords {

    private static final int WORDS_NUM = 500;   // 停用词数量
    private static final int WORD_LENGTH = 5;   // 停用词平均长度
    private static final int MAX_LENGTH = 7;    // 停用词最大长度
    private static final int MIN_LENGTH = 3;    // 停用词最小长度

    private BufferedWriter bw;                  // 文本写入
    private Random random;                      // 产生随机数
    private int max;                            // 随机数的最大值
    private ArrayList<String> stopWords;        // 产生的随机单词

    /**
     * {@code CreateStopWords} 构造方法，构造后自动生成通用词文件。
     * 
     * @param fileName      文件的路径和名字
     * @throws IOException  如果路径不存在、无法打开文件、文件写入错误、文件关闭错误时，
     *                      则会抛出 {@code IOException} 异常。
     */
    public CreateStopWords(String fileName) throws IOException {
        this.bw = new BufferedWriter(new FileWriter(fileName));
        random = new Random();
        max = 26 * (WORD_LENGTH + 1) / WORD_LENGTH;
        stopWords = new ArrayList<>(WORDS_NUM);
        create();
    }


    /**
     * 创建停用词。
     * 
     * @throws IOException  如果文件写入错误或者文件关闭错误时，则会抛出
     *                      {@code IOException} 异常。
     */
    private void create() throws IOException {
        String word;
        for (int i = 0; i < WORDS_NUM; ++i) {
            word = getRandomWord();
            stopWords.add(word);
            bw.write(word);
            bw.append('\n');
        }
        close();
    }

    /**
     * 关闭IO。
     * 
     * @throws IOException 如果文件关闭时出错会抛出 {@code IOException} 异常。
     */
    private void close() throws IOException {
        bw.close();
    }

    /**
     * 随机获得单词。
     * 
     * @return 不重复的随机单词
     */
    private String getRandomWord() {
        StringBuilder sb = new StringBuilder();
        int n = random.nextInt(max);
        while (n < 26) {
            sb.append((char) ('a' + n));
            n = random.nextInt(max);
        }
        String result = sb.toString();
        int len = result.length();
        return len > MAX_LENGTH || len < MIN_LENGTH || result.equals("") ||
                stopWords.contains(result) ? getRandomWord() : result;
    }

    public static void main(String[] args) {
        try {
            new CreateStopWords("./src/test1/stopWords.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
