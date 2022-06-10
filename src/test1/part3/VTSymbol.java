package test1.part3;

/**
 * 终止符类
 */
public class VTSymbol extends Symbol {

    /**
     * {@code VTSymbol} 构造方法。
     * 
     * @param symbol 符号。
     */
    public VTSymbol(char symbol) {
        super(symbol);
    }

    @Override
    public boolean isVT() {
        return true;
    }

}
