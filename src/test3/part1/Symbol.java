package test3.part1;

public class Symbol {

    private static int ID = 0;
    public static final String EPSILON = "epsilon";

    private int id;
    private String symbol;

    public Symbol(String symbol) {
        id = ID++;
        this.symbol = symbol;
    }

    public int getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getMDString() {
        return id + "((\"" + symbol + "\"))\n";
    }

    public static boolean isEpsilon(String symbol) {
        return symbol.equals(EPSILON);
    }

    public static boolean isVN(String symbol) {
        if (isEpsilon(symbol)) return false;
        char ch = symbol.charAt(0);
        return ch >= 'A' && ch <= 'Z';
    }

    public static boolean isVT(String symbol) {
        return !isVN(symbol);
    }

    public boolean isEpsilon() {
        return symbol.equals(EPSILON);
    }

    public boolean isVN() {
        return isVN(symbol);
    }

    public boolean isVT() {
        return !isVN(symbol);
    }

    public static Symbol getSymbol(String symbol) {
        if (isVN(symbol)) return new VNSymbol(symbol);
        if (isEpsilon(symbol)) return new VTSymbol("ε");
        return new VTSymbol(symbol);
    }

    public String getLeafString() {
        return this.toString();
    }

    @Override
    public String toString() {
        return symbol;
    }

}
