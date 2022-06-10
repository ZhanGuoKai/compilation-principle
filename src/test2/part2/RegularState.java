package test2.part2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

import test2.EpsilonNFA;
import test2.EpsilonNFA.EpsilonNFAState;;

public class RegularState extends EpsilonNFAState<String, Character> {

    private RegularState start;
    private RegularState end;
    public static int stateId = 0;
    public static int writeTime = 0;

    public RegularState() {
        super(getStateId());
        this.start = this;
        this.end = this;
    }

    public static String getStateId() {
        int temp = ++stateId;
        String s = "";
        do {
            temp--;
            s += (char) (temp % 26 + 'A');
            temp /= 26;
        } while (temp > 0);
        return new StringBuilder(s).reverse().toString();
    }

    public static RegularState init(Character alpha) {
        RegularState start, end;
        start = new RegularState();
        end = new RegularState();
        start.addNextState(alpha, end);
        start.end = end;
        end.start = start;
        return start;
    }

    public static RegularState connect(RegularState left, RegularState right) {
        left.end.addEpsilonTransfer(right.start);
        left.end = right.end;
        right.start = left.start;
        return left;
    }

    public static RegularState or(RegularState left, RegularState right) {
        RegularState start, end;
        start = new RegularState();
        end = new RegularState();
        start.addEpsilonTransfer(left.start);
        start.addEpsilonTransfer(right.start);
        left.end.addEpsilonTransfer(end);
        right.end.addEpsilonTransfer(end);
        end.start = left.start = right.start = start;
        start.end = left.end = right.end = end;
        return start;
    }

    public static RegularState closure(RegularState state) {
        RegularState start, end;
        start = new RegularState();
        end = new RegularState();
        start.addEpsilonTransfer(state.start);
        start.addEpsilonTransfer(end);
        state.end.addEpsilonTransfer(state.start);
        state.end.addEpsilonTransfer(end);
        end.start = state.start = start;
        start.end = state.end = end;
        return start;
    }

    public static boolean isSign(char ch) {
        return ch == '·' || ch == '|' || ch == '*';
    }

    public static int compare(char left, char right) {
        if (left == right) return 0;
        if (left == '(') return -2;
        if (right == '*') return 1;
        if (left == '·') return -1;
        if (right == '·') return 1;
        if (left == '|') return -1;
        return 1;
    }

    public static void merge(char ch, Stack<RegularState> stateStack, Stack<Character> signStack) {
        RegularState left, right;
        switch (ch) {
            case '*':
                left = stateStack.pop();
                stateStack.add(closure(left));
                break;
            case '·':
                right = stateStack.pop();
                left = stateStack.pop();
                stateStack.add(connect(left, right));
                break;
            case '|':
                right = stateStack.pop();
                left = stateStack.pop();
                stateStack.add(or(left, right));
                break;
        }
    }

    public static RegularState loadNFAFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename, Charset.forName("UTF-8")));
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".md", Charset.forName("UTF-8")));
        String regular = reader.readLine();
        if (regular == null) {
            reader.close();
            writer.close();
            return null;
        }
        Stack<RegularState> stateStack = new Stack<>();
        Stack<Character> signStack = new Stack<>();
        char peek;
        loop:
        for (char ch : regular.toCharArray()) {
            if (isSign(ch)) {
                if (signStack.empty()) {
                    if (ch == '*') {
                        stateStack.add(closure(stateStack.pop()));
                        writeMD(writer, stateStack, signStack);
                    }
                    else signStack.add(ch);
                } else {
                    while (compare(peek = signStack.pop(), ch) == -1) {
                        merge(peek, stateStack, signStack);
                        writeMD(writer, stateStack, signStack);
                        if (signStack.empty()) {
                            signStack.add(ch);
                            continue loop;
                        }
                    }
                    signStack.add(peek);
                    if (ch == '*') {
                        merge(ch, stateStack, signStack);
                        writeMD(writer, stateStack, signStack);
                    }
                    else signStack.add(ch);
                }
            } else if (ch == '(') signStack.add(ch);
            else if (ch == ')') {
                while ((peek = signStack.pop()) != '(') {
                    merge(peek, stateStack, signStack);
                    writeMD(writer, stateStack, signStack);
                }
            } else {
                stateStack.add(init(ch));
                writeMD(writer, stateStack, signStack);
            }
        }
        while(!signStack.empty()) {
            merge(signStack.pop(), stateStack, signStack);
            writeMD(writer, stateStack, signStack);
        }
        writer.close();
        reader.close();
        return stateStack.pop();
        // BufferedReader reader = new BufferedReader(new FileReader(filename, Charset.forName("UTF-8")));
        // String regular = reader.readLine();
        // reader.close();
        // return loadFromString(regular);
    }

    private static void writeMD(BufferedWriter writer, Stack<RegularState> stateStack, Stack<Character> signStack) {
        try {
            writeTime++;
            writer.write(writeTime + "、\n符号栈：");
            if (signStack.empty()) writer.write("空");
            else signStack.forEach(sign -> {
                try {
                    writer.write(sign + " ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.write("\n");
            writer.write("```mermaid\ngraph LR\n");
            stateStack.forEach(state -> {
                try {
                    writer.write(state.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.write("```\n");
            writer.flush();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static RegularState loadFromString(String regular) {
        if (regular == null) {
            return null;
        }
        Stack<RegularState> stateStack = new Stack<>();
        Stack<Character> signStack = new Stack<>();
        char peek;
        loop:
        for (char ch : regular.toCharArray()) {
            if (isSign(ch)) {
                if (signStack.empty()) {
                    if (ch == '*') {
                        stateStack.add(closure(stateStack.pop()));
                    }
                    else signStack.add(ch);
                } else {
                    while (compare(peek = signStack.pop(), ch) == -1) {
                        merge(peek, stateStack, signStack);
                        if (signStack.empty()) {
                            signStack.add(ch);
                            continue loop;
                        }
                    }
                    signStack.add(peek);
                    if (ch == '*') {
                        merge(ch, stateStack, signStack);
                    }
                    else signStack.add(ch);
                }
            } else if (ch == '(') signStack.add(ch);
            else if (ch == ')') {
                while ((peek = signStack.pop()) != '(') {
                    merge(peek, stateStack, signStack);
                }
            } else {
                stateStack.add(init(ch));
            }
        }
        while(!signStack.empty()) {
            merge(signStack.pop(), stateStack, signStack);
        }
        return stateStack.pop();
    }

    public final EpsilonNFA<String, Character> getEpsilonNFA() {
        EpsilonNFA<String, Character> nfa = new EpsilonNFA<>(false, null);
        nfa.setInitState(start);
        nfa.addFinalState(end);
        return nfa;
    }

    public static List<Character> stringToCharacterList(String s) {
        return s.chars().mapToObj(ch -> (char) ch).collect(Collectors.toList());
    }

    public static void test1() throws IOException {
        loadNFAFromFile("./src/test2/part2/abc.txt");
    }

    public static void test2() throws IOException {
        loadNFAFromFile("./src/test2/part2/test2.txt");
    }

    public static void regularTest() throws IOException {
        String regularFile = "./src/test2/part2/regular.txt";
        String stringFile = "./src/test2/part2/string.txt";
        BufferedReader br = new BufferedReader(
            new FileReader(regularFile, Charset.forName("UTF-8")));
        List<EpsilonNFA<String, Character>> NFAs = new ArrayList<>();
        Map<EpsilonNFA<String, Character>, String> NFAName = new HashMap<>();
        EpsilonNFA<String, Character> nfa;
        String line;
        int lines = 1;
        while ((line = br.readLine()) != null) {
            nfa = loadFromString(line).getEpsilonNFA();
            NFAs.add(nfa);
            NFAName.put(nfa, "第 " + lines + " 个 NFA");
            lines++;
        }
        br.close();
        br = new BufferedReader(
            new FileReader(stringFile, Charset.forName("UTF-8")));
        Iterator<EpsilonNFA<String, Character>> it;
        while ((line = br.readLine()) != null) {
            Boolean accept = false;
            it = NFAs.iterator();
            while (it.hasNext()) {
                nfa = it.next();
                if (nfa.test(stringToCharacterList(line))) {
                    System.out.println(line + " 能被" + NFAName.get(nfa) + "识别");
                    accept = true;
                }
            }
            if (!accept) {
                System.out.println(line + " 不能被识别");
            }
        }
        br.close();
    }

    public static void main(String[] args) throws IOException {
        // test1();
        // test2();
    }

}