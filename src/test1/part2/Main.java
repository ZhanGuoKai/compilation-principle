package test1.part2;

import java.io.IOException;

public class Main {

    private static final String STOP_WORDS_FILE = "./src/test1/stopWords.txt";    // 停用词文件位置
    private static final String TEXT_FILE = "./src/test1/text.txt";               // 待过滤文本位置
    private static final String FILTERED_FILE = "./src/test1/filteredText.txt";   // 过滤后文本位置

    public static void main(String[] args) {
        try {
            new CreateStopWords(STOP_WORDS_FILE);
            new CreateText(TEXT_FILE);
            new TextFilter(STOP_WORDS_FILE, TEXT_FILE, FILTERED_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
