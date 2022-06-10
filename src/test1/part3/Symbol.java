package test1.part3;

/**
 * 文法符号接口
 */
public class Symbol {

    /**
     * 符号
     */
    protected char symbol;

    public Symbol(char symbol) {
        this.symbol = symbol;
    }

    /**
     * 判断是否为终结符。
     * 
     * @return  如果是终结符，返回 {@code true}；
     *          如果不是终结符，返回 {@code false} 。
     */
    public boolean isVT() {
        return isVT(symbol);
    }

    /**
     * 获取 {@code symbol} 的值。
     * 
     * @return 符号。
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * 判断 {@code ch} 是否为终结符。
     * 
     * @param ch    要判断的字符。
     * @return      如果是终结符，返回 {@code true}；
     *              如果不是终结符，返回 {@code false} 。
     */
    public static boolean isVT(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    /**
     * 判断 {@code ch} 是否为非终结符。
     * 
     * @param ch    要判断的字符。
     * @return      如果是非终结符，返回 {@code true}；
     *              如果不是非终结符，返回 {@code false} 。
     */
    public static boolean isVN(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }

    /**
     * 判断 {@code ch} 是否为产生式右侧的文法符号。
     * 
     * @param ch    要判断的字符。
     * @return      如果是产生式右侧的文法符号，返回 {@code true}；
     *              如果不是产生式右侧的文法符号，返回 {@code false} 。
     */
    public static boolean isSymbol(char ch) {
        return isVT(ch) || isVN(ch);
    }

    @Override
    public String toString() {
        return symbol + "";
    }

}
