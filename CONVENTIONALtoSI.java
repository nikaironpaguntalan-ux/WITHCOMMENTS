public class CONVENTIONALtoSI {

    public static double glucoseToSI(double value) {// method to convert glucose from conventional units (mg/dL) to SI units (mmol/L)
        return value / 18.0;// conversion factor for glucose from mg/dL to mmol/L
    }

    public static double cholesterolToSI(double value) {// method to convert cholesterol from conventional units (mg/dL) to SI units (mmol/L)
        return value / 38.67;
    }

    public static double hdlToSI(double value) {// method to convert HDL cholesterol from conventional units (mg/dL) to SI units (mmol/L)
        return value / 38.67;
    }

    public static double ldlToSI(double value) {//process of converting LDL cholesterol from conventional units (mg/dL) to SI units (mmol/L)
        return value / 38.67;
    }

    public static double triglyceridesToSI(double value) {//process of converting triglycerides from conventional units (mg/dL) to SI units (mmol/L)
        return value / 88.57;
    }

    public static double creatinineToSI(double value) {// method to convert creatinine from conventional units (mg/dL) to SI units (µmol/L)
        return value * 88.4;
    }

    public static double uricAcidToSI(double value) {// method to convert uric acid from conventional units (mg/dL) to SI units (µmol/L)
        return value / 16.81;
    }

    public static double bunToSI(double value) {// method to convert blood urea nitrogen (BUN) from conventional units (mg/dL) to SI units (mmol/L)
        return value / 2.801;
    }

    public static double enzymeToSI(double value) {// method to convert enzyme levels ( AST, ALT) from conventional units (U/L) to SI units (µkat/L)
        return value / 60.0;
    }

    public static double electrolyteToSI(double value) {// method to convert electrolyte levels (sodium, potassium, chloride) from conventional units (mmol/L) to SI units (mmol/L)
        return value;
    }

    public static double totalCalciumToSI(double value) {// method to convert total calcium from conventional units (mg/dL) to SI units (mmol/L)
        return value * 0.2495;
    }

    public static double ionizedCalciumToSI(double value) {// method to convert ionized calcium from conventional units (mg/dL) to SI units (mmol/L)
        return value * 0.2495;
    }
}