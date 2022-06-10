package test3.part2;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class SimpleOperation {

    private InputStream reader;
    private char current;
    private StringBuilder result;
    private StringBuilder md;
    private int count;

    public static class IllegalOperationException extends Exception {

        private static final IllegalOperationException INSTANCE = new IllegalOperationException();

        private IllegalOperationException() {
            super("表达式不合法！");
        }

    }

    private SimpleOperation(InputStream is) throws IOException {
        reader = is;
        current = (char) is.read();
        result = new StringBuilder();
        count = 0;
    }

    private static boolean isBlank(char ch) {
        return ch == ' ' || ch == '\n' || ch == '\t';
    }

    private void readNext() throws IOException {
        int ch = reader.read();
        int currentCount = count;
        result.append(current);
        md.append(++count).append("((\"").append(current).append("\"))\n")
            .append(currentCount).append(" --> ").append(count).append('\n');
        while (isBlank((char) ch)) ch = reader.read();
        if (ch == -1) current = '#';
        else current =  (char) ch;
    }

    public static void test(String filename) throws Exception{
        InputStream is = new FileInputStream(filename);
        SimpleOperation so = new SimpleOperation(is);
        so.test();
        System.out.println(so.result);
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename + "-tree.md"));
        bw.write(so.md.toString());
        bw.close();
    }

    private void test() throws Exception {
        md = new StringBuilder("```mermaid\ngraph TB\n");
        E();
        md.append("```\n");
    }

    private int E() throws Exception {
        result.append('E').append('(');
        md.append(++count).append("((E))\n");
        int currentCount = count, temp;
        if (current == '(' || current == 'i') {
            temp = T();
            md.append(currentCount).append(" --> ").append(temp).append('\n');
            temp = E_();
            md.append(currentCount).append(" --> ").append(temp).append('\n');
        } else throw IllegalOperationException.INSTANCE;
        result.append(')');
        return currentCount;
    }

    private int E_() throws Exception {
        result.append('E').append('\'');
        md.append(++count).append("((E'))\n");
        int currentCount = count, temp;
        if (current == '+') {
            result.append("(");
            readNext();
            temp = T();
            md.append(currentCount).append(" --> ").append(temp).append('\n');
            temp = E_();
            md.append(currentCount).append(" --> ").append(temp).append('\n');
            result.append(')');
        } else if (current != ')' && current != '#') {
            throw IllegalOperationException.INSTANCE;
        }
        return currentCount;
    }

    private int T() throws Exception {
        result.append('T').append('(');
        md.append(++count).append("((T))\n");
        int currentCount = count, temp;
        if (current == '(' || current == 'i') {
            temp = F();
            md.append(currentCount).append(" --> ").append(temp).append('\n');
            temp = T_();
            md.append(currentCount).append(" --> ").append(temp).append('\n');
        } else throw IllegalOperationException.INSTANCE;
        result.append(')');
        return currentCount;
    }

    private int T_() throws Exception {
        result.append('T').append('\'');
        md.append(++count).append("((T'))\n");
        int currentCount = count, temp;
        if (current == '*') {
            result.append("(");
            readNext();
            temp = F();
            md.append(currentCount).append(" --> ").append(temp).append('\n');
            temp = T_();
            md.append(currentCount).append(" --> ").append(temp).append('\n');
            result.append(')');
        } else if (current != '+' && current != '#' && current != ')') {
            throw IllegalOperationException.INSTANCE;
        }
        return currentCount;
    }

    private int F() throws Exception {
        result.append('F').append('(');
        md.append(++count).append("((F))\n");
        int currentCount = count, temp;
        if (current == '(') {
            readNext();
            temp = E();
            md.append(currentCount).append(" --> ").append(temp).append('\n');
            if (current != ')') {
                throw IllegalOperationException.INSTANCE;
            }
            readNext();
        } else if (current == 'i') {
            readNext();
        } else throw IllegalOperationException.INSTANCE;
        result.append(')');
        return currentCount;
    }

    public static void main(String[] args) {
        String[] filenames = {
            "./src/test3/part2/test1.txt",
            "./src/test3/part2/test2.txt",
            "./src/test3/part2/test3.txt",
            "./src/test3/part2/test4.txt"
        };
        for (String filename : filenames) {
            try {
                test(filename);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
    }

}
