package test1.part1;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.function.IntUnaryOperator;

public class CppScanner {

    /**
     * 保留字
     */
    private static final String[] RESERVED_WORDS = {
        "asm", "auto", "bool", "break", "case", "catch", "char", "class",
        "const", "const_cast", "continue", "default", "delete", "do", "double",
        "dynamic_cast", "else", "enum", "explicit", "export", "export", "extern",
        "false", "float", "for", "friend", "goto", "if", "inline", "int",
        "long", "mutable", "namespace", "new", "operator", "private", "protected",
        "public", "register", "reinterpret_cast", "return", "short", "signed",
        "sizeof", "static", "static_cast", "struct", "switch", "template",
        "this", "throw", "true", "try", "typedef", "typeid", "typename", "union",
        "unsigned", "using", "virtual", "void", "volatile", "wchar_t"
    };

    /**
     * 空白字符
     */
    private static final char[] BLANKS = {
        ' ', '\t', '\r', '\n', '\f', '\0'
    };

    /**
     * 特殊符号
     */
    private static final char[] SIGNS = {
        '=', '+', '-', '*', '/', '%', '!', '<', '>', '&',
        '|', '^', '~', '?', ':', '(', ')', '[', ']', '{',
        '}', ',', ';', '.', '#', '\'', '"', '\\'
    };

    private static final int LETTER = 1;                    // 字母
    private static final int DIGIT = 2;                     // 数字
    private static final int DOLLAR = 3;                    // 美元符号 $
    private static final int UNDERLINE = 4;                 // 下划线 _
    private static final int BLANK = 5;                     // 空白字符

    private static final int ASSIGNMENT = 6;                // =
    private static final int ADDITION = 7;                  // +
    private static final int SUBTRACTION = 8;               // -
    private static final int MULTIPLICATION = 9;            // *
    private static final int DIVISION = 10;                 // /
    private static final int MODULO = 11;                   // %
    private static final int NOT = 12;                      // !
    private static final int LESS = 13;                     // <
    private static final int GREATER = 14;                  // >
    private static final int AND = 15;                      // &
    private static final int OR = 16;                       // |
    private static final int XOR = 17;                      // ^
    private static final int INVERSION = 18;                // ~
    private static final int TERNARY_FIRST = 19;            // ?
    private static final int TERNARY_SECOND = 20;           // :
    private static final int PARENTHESIS_LEFT = 21;         // (
    private static final int PARENTHESIS_RIGHT = 22;        // )
    private static final int BRACKET_LEFT = 23;             // [
    private static final int BRACKET_RIGHT = 24;            // ]
    private static final int BRACE_LEFT = 25;               // {
    private static final int BRACE_RIGHT = 26;              // }
    private static final int COMMA = 27;                    // ,
    private static final int SEMICOLON = 28;                // ;
    private static final int DOT = 29;                      // .
    private static final int HASH = 30;                     // #
    private static final int SINGLE_QUOTATION_MARK = 31;    // '
    private static final int DOUBLE_QUOTATION_MARK = 32;    // "
    private static final int ESCAPE = 33;                   // \

    private static final int NAME_TYPE = 34;                // 变量名类型
    private static final int RESERVED_WORD_TYPE = 35;       // 关键字类型
    private static final int NUMBER_TYPE = 36;              // 数字类型
    private static final int CHARACTER_TYPE = 37;           // 字符类型
    private static final int STRING_TYPE = 38;              // 字符串类型
    private static final int OTHER_TYPE = 39;               // 其它类型

    private static final int OTHERS = -1;                   // 其它符号

    private static final int END = 0xFFFF;                  // 结束

    private BufferedInputStream inputStream;                // 文件输入流
    private StringBuilder currentWord;                      // 当前单词
    private char nextChar;                                  // 下一个字符
    private int count;                                      // 嵌套层数
    private final IntUnaryOperator[] operators;             // 判断操作数组

    /**
     * 构造方法。
     * 
     * @param fileName 文件名。
     * @throws FileNotFoundException 如果文件不存在会抛出 {@code FileNotFoundException} 异常。
     */
    public CppScanner(String fileName) throws FileNotFoundException {
        inputStream = new BufferedInputStream(new FileInputStream(fileName));
        currentWord = new StringBuilder();
        nextChar = '\0';
        count = 0;
        operators = new IntUnaryOperator[] {
                type -> 0,
                type -> {
                    if (type != LETTER && type != DIGIT && type != DOLLAR && type != UNDERLINE) {
                        if (isReservedWord(currentWord.toString())) {
                            print("保留字");
                            return RESERVED_WORD_TYPE;
                        }
                        print("标识符");
                        return NAME_TYPE;
                    }
                    currentWord.append(nextChar);
                    return -1;
                },
                type -> {
                    if (type != DIGIT && type != DOT) {
                        print("数字");
                        return NUMBER_TYPE;
                    }
                    currentWord.append(nextChar);
                    return -1;
                },
                type -> {
                    if (type != LETTER && type != DIGIT && type != DOLLAR && type != UNDERLINE) {
                        if (isReservedWord(currentWord.toString())) {
                            print("保留字");
                            return RESERVED_WORD_TYPE;
                        }
                        print("标识符");
                        return NAME_TYPE;
                    }
                    currentWord.append(nextChar);
                    return -1;
                },
                type -> {
                    if (type != LETTER && type != DIGIT && type != DOLLAR && type != UNDERLINE) {
                        if (isReservedWord(currentWord.toString())) {
                            print("保留字");
                            return RESERVED_WORD_TYPE;
                        }
                        print("标识符");
                        return NAME_TYPE;
                    }
                    currentWord.append(nextChar);
                    return -1;
                },
                type -> 0,
                type -> signOperate(
                            type,
                            new int[] { ASSIGNMENT },
                            new String[] { "判断相等运算符" },
                            ASSIGNMENT,
                            "赋值运算符"),
                type -> signOperate(
                            type,
                            new int[] { ASSIGNMENT, ADDITION },
                            new String[] { "加且赋值运算符", "自增运算符" },
                            ADDITION,
                            "加法运算符"),
                type -> signOperate(
                            type,
                            new int[] { ASSIGNMENT, SUBTRACTION },
                            new String[] { "减且赋值运算符", "自减运算符" },
                            SUBTRACTION,
                            "减法运算符"),
                type -> signOperate(
                            type,
                            new int[] { ASSIGNMENT, DIVISION },
                            new String[] { "乘且赋值运算符", "多行注释结束" },
                            MULTIPLICATION,
                            "乘法运算符"),
                type -> signOperate(
                            type,
                            new int[] { ASSIGNMENT, DIVISION, MULTIPLICATION },
                            new String[] { "除且赋值运算符", "单行注释", "多行注释开始" },
                            DIVISION,
                            "除法运算符"),
                type -> signOperate(
                            type,
                            new int[] { ASSIGNMENT },
                            new String[] { "取模且赋值运算符" },
                            MODULO,
                            "取模运算符"),
                type -> signOperate(
                            type,
                            new int[] { ASSIGNMENT },
                            new String[] { "判断不相等运算符" },
                            NOT,
                            "逻辑非运算符"),
                type -> signOperate(
                            type,
                            new int[] { ASSIGNMENT, LESS },
                            new String[] { "判断不大于运算符", "左移运算符" },
                            LESS,
                            "判断小于运算符"),
                type -> signOperate(
                            type,
                            new int[] { ASSIGNMENT, GREATER },
                            new String[] { "判断不小于运算符", "右移运算符" },
                            GREATER,
                            "判断大于运算符"),
                type -> signOperate(
                            type,
                            new int[] { ASSIGNMENT, AND },
                            new String[] { "按位与且赋值运算符", "逻辑与运算符" },
                            AND,
                            "按位与运算符"),
                type -> signOperate(
                            type,
                            new int[] { ASSIGNMENT, OR },
                            new String[] { "按位或且赋值运算符", "逻辑或运算符" },
                            OR,
                            "按位或运算符"),
                type -> signOperate(
                            type,
                            new int[] { ASSIGNMENT },
                            new String[] { "异或且赋值运算符" },
                            XOR,
                            "异或运算符"),
                type -> signOperate(
                            type,
                            new int[] {},
                            new String[] {},
                            INVERSION,
                            "取反运算符"),
                type -> {
                    matchSign(TERNARY_SECOND, "三元运算符", "?:");
                    return TERNARY_FIRST;
                },
                type -> TERNARY_SECOND,
                type -> {
                    matchSign(PARENTHESIS_RIGHT, "括号运算符", "()");
                    return PARENTHESIS_LEFT;
                },
                type -> PARENTHESIS_RIGHT,
                type -> {
                    matchSign(BRACKET_RIGHT, "下标运算符", "[]");
                    return BRACKET_LEFT;
                },
                type -> BRACKET_RIGHT,
                type -> {
                    matchSign(BRACE_RIGHT, "代码块运算符", "{}");
                    return BRACE_LEFT;
                },
                type -> BRACE_RIGHT,
                type -> signOperate(
                            type,
                            new int[] {},
                            new String[] {},
                            COMMA,
                            "逗号运算符"),
                type -> signOperate(
                            type,
                            new int[] {},
                            new String[] {},
                            SEMICOLON,
                            "分隔符"),
                type -> signOperate(
                            type,
                            new int[] {},
                            new String[] {},
                            DOT,
                            "点运算符"),
                type -> signOperate(
                            type,
                            new int[] { HASH },
                            new String[] { "连接符" },
                            HASH,
                            "井号"),
                type -> {
                    getString(SINGLE_QUOTATION_MARK);
                    print("字符");
                    return CHARACTER_TYPE;
                },
                type -> {
                    getString(DOUBLE_QUOTATION_MARK);
                    print("字符串");
                    return STRING_TYPE;
                },
                type -> {
                    currentWord = new StringBuilder(escape(nextChar) + "");
                    return OTHER_TYPE;
                },
                type -> 0
        };
    }

    /**
     * 判断一个字符是否为字母。
     * 
     * @param ch 需要判断的字符。
     * @return 如果是字母，则返回 {@code true} ，否则返回 {@code false} 。
     */
    private static boolean isLetter(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }

    /**
     * 判断一个字符是否为数字。
     * 
     * @param ch 需要判断的字符。
     * @return 如果是数字，则返回 {@code true} ，否则返回 {@code false} 。
     */
    private static boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    /**
     * 判断一个字符是否为空白字符。
     * 
     * @param ch 需要判断的字符。
     * @return 如果是空白字符，则返回 {@code true} ，否则返回 {@code false} 。
     */
    private static boolean isBlank(char ch) {
        int i = BLANKS.length;
        while (i-- != 0) {
            if (ch == BLANKS[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取符号类型。
     * 
     * @param ch 需要获取的字符。
     * @return 如果是常见符号，返回值范围从 6 到 33 的整数，否则返回 -1。
     */
    private static int getSign(char ch) {
        int i = 0, length = SIGNS.length;
        for (; i < length; i++) {
            if (ch == SIGNS[i]) {
                return i + 6;
            }
        }
        return OTHERS;
    }

    /**
     * 判断字符类型。
     * 
     * @param ch 需要判断的字符。
     * @return 如果是字母，则返回 1，
     *         如果是数字，则返回 2，
     *         如果是美元符号，则返回 3，
     *         如果是下划线，则返回 4，
     *         如果是空白字符，则返回 5，
     *         特殊符号分别是 6 到 33，
     *         如果非上述字符，则返回 -1。
     */
    private static int judgeChar(char ch) {
        if (isLetter(ch)) {
            return LETTER;
        }
        if (isDigit(ch)) {
            return DIGIT;
        }
        if (ch == '$') {
            return DOLLAR;
        }
        if (ch == '_') {
            return UNDERLINE;
        }
        if (isBlank(ch)) {
            return BLANK;
        }
        return getSign(ch);
    }

    /**
     * 判断一个单词是否为保留字。
     * 
     * @param word 需要判断的单词。
     * @return 如果是保留字，则返回 {@code true} ，否则返回 {@code false} 。
     */
    private static boolean isReservedWord(String word) {
        int i = RESERVED_WORDS.length;
        while (i-- != 0) {
            if (word.equals(RESERVED_WORDS[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 对字符进行判断操作。
     * 
     * @param operation 需要进行的操作。
     * @return 返回值为单词类型，34 到 39 。
     * @throws IOException 如果输入流被关闭，会抛出 {@code IOException} 异常。
     */
    private int operate(IntUnaryOperator operation) throws IOException {
        int charType, result;
        while ((nextChar = (char) inputStream.read()) != END) {
            charType = judgeChar(nextChar);
            result = operation.applyAsInt(charType);
            if (result != -1) {
                count--;
                return result;
            }
        }
        count--;
        return operation.applyAsInt(judgeChar(nextChar));
    }

    /**
     * 输出信息，输出格式为 {@code wordType，word} 。
     * 
     * @param wordType  单词类型。
     * @param word      单词。
     */
    private void print(String wordType, String word) {
        for (int i = 1; i < count; i++) {
            System.out.print("    ");
        }
        System.out.println(wordType + "，" + word);
    }

    /**
     * 输出语句。
     * 
     * @param wordType 单词类型。
     */
    private void print(String wordType) {
        print(wordType, currentWord.toString());
    }

    /**
     * 对特殊符号进行操作。
     * 
     * @param sign             当前符号，值为 5 到 33。
     * @param acceptableValues 可接受的符号。
     * @param results          不同符号的结果。
     * @param resultType       返回的结果类型。
     * @param defaultResult    没有匹配到可接受符号的结果。
     * @return 返回值为 {@code resultType} 。
     */
    private int signOperate(int sign, int[] acceptableValues, String[] results,
        int resultType, String defaultResult) {
        int i = acceptableValues.length;
        while (i-- != 0) {
            if (sign == acceptableValues[i]) {
                currentWord.append(nextChar);
                try {
                    nextChar = (char) inputStream.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                print(results[i]);
                return resultType;
            }
        }
        print(defaultResult);
        return resultType;
    }

    /**
     * 匹配下一个目标符号。
     * 
     * @param sign     需要匹配的符号。
     * @param wordType 单词类型。
     * @param word     单词。
     */
    private void matchSign(int sign, String wordType, String word) {
        try {
            int result = readNextWord();
            while (result != sign && result != -1 && result != 0) {
                result = readNextWord();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        print(wordType, word);
    }

    /**
     * 转义。
     * 
     * @param ch 待转义的符号。
     * @return 转义的符号。
     */
    private static char escape(char ch) {
        switch (ch) {
            case '\'':
            case '"':
            case '\\':
                return ch;
            case 't':
                return '\t';
            case 'n':
                return '\n';
            case 'r':
                return '\r';
            default:
                return ch;
        }
    }

    /**
     * 获取字符串。
     * 
     * @param mark 需要匹配的标志。
     */
    private void getString(int mark) {
        int type;
        try {
            currentWord.append(nextChar);
            nextChar = (char) inputStream.read();
            while (nextChar != END) {
                type = judgeChar(nextChar);
                if (type == ESCAPE) {
                    nextChar = (char) inputStream.read();
                    currentWord.append(escape(nextChar));
                } else {
                    currentWord.append(nextChar);
                    if (judgeChar(nextChar) == mark) {
                        nextChar = (char) inputStream.read();
                        return;
                    }
                }
                nextChar = (char) inputStream.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取下一个单词。
     * 
     * @return 单词类型，一般为 6 到 36，文件读取完毕时返回 -1。
     * @throws IOException 如果输入流被关闭，会抛出 {@code IOException} 异常。
     */
    private int readNextWord() throws IOException {
        int charType;
        count++;
        while (isBlank(nextChar)) {
            if ((nextChar = (char) inputStream.read()) == END) {
                return -1;
            }
        }
        currentWord = new StringBuilder(nextChar + "");
        charType = judgeChar(nextChar);
        if (charType > 0) {
            return operate(operators[charType]);
        }
        count--;
        return -1;
    }

    /**
     * 扫描文件。
     * 
     * @throws IOException
     */
    public void scan() throws IOException {
        int result = readNextWord();
        while (result != -1 && result != 0) {
            result = readNextWord();
        }
    }

    public static void main(String[] args) {
        try {
            new CppScanner("./src/test1/test.cpp").scan();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
