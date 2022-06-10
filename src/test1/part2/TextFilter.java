package test1.part2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TextFilter {

    private BufferedReader stopWordReader;      // 读取停用词文件
    private BufferedReader textReader;          // 读取文本文件
    private BufferedWriter filteredTextWriter;  // 写入过滤后的文件
    private boolean newLine;                    // 是否换行

    /**
     * 过滤文本构造方法。
     * 
     * @param StopWordsFileName 停用词文件。
     * @param textFileName      待过滤文本文件。
     * @param filteredTextName  过滤后文本文件。
     * @throws IOException      如果文件路径不存在、无法打开文件、文件读写错误、文件关闭错误时，
     *                          则会抛出 {@code IOException} 异常。
     */
    TextFilter(String StopWordsFileName, String textFileName,
            String filteredTextName) throws IOException {
        stopWordReader = new BufferedReader(new FileReader(StopWordsFileName));
        textReader = new BufferedReader(new FileReader(textFileName));
        filteredTextWriter = new BufferedWriter(new FileWriter(filteredTextName));
        newLine = false;
        filter();
    }

    /**
     * 获取停用词。
     * 
     * @return 停用词。
     * @throws IOException 如果文件读取失败会抛出 {@code IOException} 异常。
     */
    private ArrayList<String> getStopWords() throws IOException {
        ArrayList<String> words = new ArrayList<>();
        String word;
        while ((word = stopWordReader.readLine()) != null) {
            words.add(word);
        }
        return words;
    }

    /**
     * 从待过滤文本中读取下一个单词。
     * 
     * @return 待过滤文本中下一个单词，如果文本已读完，则返回 {@code null} 。
     * @throws IOException 如果文件读取失败会抛出 {@code IOException} 异常。
     */
    private String readNextWord() throws IOException {
        int next = textReader.read();
        StringBuilder sb = new StringBuilder();
        while (next != ' ' || next == '\n') {
            if (next == -1) return null;
            sb.append((char) next);
            next = textReader.read();
        }
        newLine = next == '\n';
        return sb.toString();
    }

    /**
     * 过滤文本。
     * 
     * @throws IOException 如果文本读写失败，会抛出 {@code IOException} 异常。
     */
    private void filter() throws IOException {
        ArrayList<String> stopWords = getStopWords();
        String word;
        while ((word = readNextWord()) != null) {
            if (stopWords.contains(word)) {
                filteredTextWriter.write("**");
            } else {
                filteredTextWriter.write(word);
            }
            filteredTextWriter.write(newLine ? '\n' : ' ');
        }
        close();
    }

    /**
     * 关闭 IO 。
     * 
     * @throws IOException 如果文件关闭时出错会抛出 {@code IOException} 异常。
     */
    private void close() throws IOException {
        stopWordReader.close();
        textReader.close();
        filteredTextWriter.close();
    }

    public static void main(String[] args) {
        try {
            new TextFilter("./src/test1/stopWords.txt", "./src/test1/text.txt", "./src/test1/filteredText.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
