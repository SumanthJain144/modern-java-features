package session1;

//Java 12
import java.text.NumberFormat;
import java.util.Locale;
import java.math.RoundingMode;

public class CompactNumberDemo {

    public static void main(String[] args) {

        int downloads = 2_455_300;
        System.out.println("Original Number: " + downloads);
        System.out.println("------------------------------------------------");

        // 1) Manual Custom Logic
        String custom;
        if (downloads >= 1_00_000) {
            double lakhs = downloads / 100000.0;
            custom = (lakhs == Math.floor(lakhs)) 
                     ? ((long) lakhs) + "L"
                     : String.format("%.1fL", lakhs);
        } else {
            custom = String.valueOf(downloads);
        }
        System.out.println("1) Custom Logic:				" + custom);

        // 2) Compact Number Format - Default
        var fmtDefault = NumberFormat.getCompactNumberInstance(); // default locale + short
        fmtDefault.setMinimumFractionDigits(1);
        System.out.println("2) Compact (Default Locale):			" + fmtDefault.format(downloads));

        // 3) Locale Difference
        var fmtIN = NumberFormat.getCompactNumberInstance(Locale.of("en", "IN"), NumberFormat.Style.SHORT);
        var fmtUS = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);

       // System.out.println("3a) Locale India  (en-IN):			" + fmtIN.format(downloads)); 
      //  System.out.println("3b) Locale US     (en-US):			" + fmtUS.format(downloads));

        // 4) Explicit Long Style
        var fmtLong  = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.LONG);
        
      //  System.out.println("4) Long Style:					" + fmtLong.format(downloads));

        // 5) Decimal Precision (Max & Min Fraction Digits)
        var fmtDecimal1 = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        fmtDecimal1.setMaximumFractionDigits(3);
      //  System.out.println("5a) Max Fraction (3):				" + fmtDecimal1.format(downloads)); 

        var fmtDecimal2 = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        fmtDecimal2.setMinimumFractionDigits(2);
       // System.out.println("5b) Min Fraction 21):				" + fmtDecimal2.format(downloads));

        // 6) Rounding Modes
        var fmtRound = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        fmtRound.setMaximumFractionDigits(1);

        fmtRound.setRoundingMode(RoundingMode.UP);
      //  System.out.println("6a) Rounding UP:				" + fmtRound.format(downloads));

        fmtRound.setRoundingMode(RoundingMode.DOWN);
       // System.out.println("6b) Rounding DOWN:          			" + fmtRound.format(downloads));

        System.out.println("------------------------------------------------");
    }
}