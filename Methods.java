package Dependencies;

public class Methods {
    private static boolean isIn(char[] x, char[] y) {
        for (char a : x) {
            for (char b : y) {
                if (a == b) {
                    return true;
                }
            }
        }
        return false;
    }

    protected static char[] sigFigger(String value) {
        char[] output = new char[value.length()];
        for (int i = 0; i < value.length(); i++) {
            final char v = value.charAt(i);
            if (isIn(new char[]{v}, Constants.NATURAL_NUMBERS)) {
                output[i] = 's';
            } else if (v == '0' && isIn(value.substring(0, i).toCharArray(), Constants.NATURAL_NUMBERS)
                    && (isIn(value.substring(i + 1).toCharArray(), Constants.NATURAL_NUMBERS)
                    || isIn(value.substring(i + 1).toCharArray(), new char[]{'.'}))) {
                output[i] = 's';
            } else if (v == '0' && isIn(value.substring(0, i).toCharArray(), new char[] {'.'})
                    && !isIn(value.substring(i+1).toCharArray(), Constants.NATURAL_NUMBERS)){
                output[i] = 's';
            } else if (v == '.'){
                output[i] = 'd';
            } else{
                output[i] = 'n';
            }
        }
        return output;
    }

    
}
