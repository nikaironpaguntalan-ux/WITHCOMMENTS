public class rangeInterpretation {

    public static String interpret(double value, double low, double high) {

        if (value > high) {
            return "HIGH";
        } 
        else if (value < low) {
            return "LOW";
        } 
        else {
            return "NORMAL";
        }
    }
}
