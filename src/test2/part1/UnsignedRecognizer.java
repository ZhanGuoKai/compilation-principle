package test2.part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import test2.DFA;

public class UnsignedRecognizer extends DFA<String, String> {

    private static final String DIGIT = "数字";
    private static final String DOT = "小数点";
    private static final String E = "E";
    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String OTHER = "其它";

    private static final String STATE0 = "初始";
    private static final String STATE1 = "整数";
    private static final String STATE2 = "小数无小数部分异常";
    private static final String STATE3 = "小数";
    private static final String STATE4 = "科学计数法无符号和数字异常";
    private static final String STATE5 = "科学计数法无数字异常";
    private static final String STATE6 = "科学计数法";
    private static final String STATE7 = "其它";

    private Map<String, String> cipher;
    private StringBuilder sb;

    public UnsignedRecognizer() {
        this("0123456789.E+-");
    }

    public UnsignedRecognizer(String cipherText) {
        super(true, (state, stateList) -> {
            if (state == null) {
                System.out.println(stateList.get(stateList.size() - 1));
            }
        });
        if (cipherText.length() < 14) throw new Error("加密文本太短");
        int pos = 0;
        cipher = new HashMap<>(14);
        for (pos = 0; pos < 10; pos++) {
            cipher.put(cipherText.charAt(pos) + "", pos + "");
        }
        cipher.put(cipherText.charAt(pos++) + "", ".");
        cipher.put(cipherText.charAt(pos++) + "", "E");
        cipher.put(cipherText.charAt(pos++) + "", "+");
        cipher.put(cipherText.charAt(pos++) + "", "-");
        sb = new StringBuilder();
        initDFA();
    }

    private void initDFA() {
        setInitState(STATE0);

        addTransfer(STATE0, DIGIT, STATE1);
        addTransfer(STATE0, DOT, STATE2);
        addTransfer(STATE0, new String[] { E, PLUS, MINUS, OTHER }, STATE7);

        addTransfer(STATE1, DIGIT, STATE1);
        addTransfer(STATE1, DOT, STATE3);
        addTransfer(STATE1, E, STATE4);

        addTransfer(STATE2, DIGIT, STATE3);

        addTransfer(STATE3, DIGIT, STATE3);
        addTransfer(STATE3, E, STATE4);

        addTransfer(STATE4, new String[] { PLUS, MINUS }, STATE5);
        addTransfer(STATE4, DIGIT, STATE6);

        addTransfer(STATE5, DIGIT, STATE6);

        addTransfer(STATE6, DIGIT, STATE6);

        addTransfer(STATE7, new String[] { DOT, E, PLUS, MINUS, OTHER }, STATE7);
    }

    public String getType(char ch) {
        if (ch >= '0' && ch <='9') return DIGIT;
        if (ch == '.') return DOT;
        if (ch == 'E') return E;
        if (ch == '+') return PLUS;
        if (ch == '-') return MINUS;
        return OTHER;
    }

    public void recognize(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        DFAState<String, String> state;
        while ((line = reader.readLine()) != null) {
            state = getLastState(stringToCharacters(line));
            System.out.print(sb.append(' '));
            System.out.println(state);
            sb = new StringBuilder();
        }
        reader.close();
    }

    private List<String> stringToCharacters(String s) {
        return s.chars().mapToObj(ch -> (char) ch + "").collect(Collectors.toList());
    }

    @Override
    protected DFAState<String, String> transfer(
            DFAState<String, String> currentState,
            String alpha) {
        String s = cipher.get(alpha);
        DFAState<String, String> state =
            currentState.getNextState(s == null ? OTHER : getType(s.charAt(0)));
        if (state == null) {
            System.out.print(sb.append(' '));
            sb = new StringBuilder();
            return null;
        }
        sb.append(s == null ? alpha : s);
        return state;
    }

    public static void main(String[] args) {
        try {
            UnsignedRecognizer ur = new UnsignedRecognizer();
            ur.recognize("./src/test2/part1/test1.txt");
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            UnsignedRecognizer ur = new UnsignedRecognizer("ABCDEFGHIJKLMN");
            ur.recognize("./src/test2/part1/test2.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
