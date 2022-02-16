import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Question3Solution {
    /*
    * 3) Java:

        Create a function that takes the memory size (ms is a string type) as an argument and returns the actual memory size.

        Examples
        actualMemorySize("32GB") --> "29.76GB"

        actualMemorySize("2GB") --> "1.86GB"

        actualMemorySize("512MB") --> "476MB"

        Notes
        -The actual storage loss on a USB device is 7% of the overall memory size!
        -If the actual memory size was greater than 1 GB, round your result to two decimal places.
        -If the memory size after adjustment is smaller then 1 GB, return the result in MB.
    */
    public static final Double USB_STORAGE_LOSS = 0.07;
    public static Double getBigDecimalAdjustedMemorySize(String numberStr) {
        BigDecimal bdOriginalMemorySize = new BigDecimal(numberStr);
        BigDecimal bdMemoryLoss = bdOriginalMemorySize.multiply(new BigDecimal(0.07d));
        BigDecimal bdAdjustedMemorySize = bdOriginalMemorySize.subtract(bdMemoryLoss);
        return bdAdjustedMemorySize.round(new MathContext(13, RoundingMode.UP)).doubleValue();
    }

    public static String actualMemorySize(String memorySize) {
        StringBuilder sb = new StringBuilder(memorySize.toUpperCase());
        Integer indexMBorGB = sb.indexOf("GB");
        Double originalMemorySize;
        if (indexMBorGB>-1) {   // The memory size is in GB
            Double adjustedMemorySize = getBigDecimalAdjustedMemorySize(sb.substring(0, indexMBorGB));
            if (adjustedMemorySize > 1.0d) {
                return String.format( "%.2f", adjustedMemorySize ) + "GB";
            } else if (adjustedMemorySize <1.0d) {
                return String.format( "%f", adjustedMemorySize * 1000) + "MB";
            } else {    // It's exactly equal to 1GB
                return String.format("%.0f", adjustedMemorySize)+"GB";
            }
        } else {
            indexMBorGB = sb.indexOf("MB");
            if (indexMBorGB>-1) {
                double adjustedMemorySize = getBigDecimalAdjustedMemorySize(sb.substring(0, indexMBorGB));
                return String.format("%.0f", adjustedMemorySize)+"MB";
            }
        }

        return "ERROR: INVALID MEMORY SIZE INPUT";
    }
    public static void main(String[] args) {
        // To run from bash:
        // javac Question3Solution.java
        // java Question3Solution
        //
        System.out.println(actualMemorySize("32GB"));   // "29.76GB"
        System.out.println(actualMemorySize("2GB"));    // "1.86GB"
        System.out.println(actualMemorySize("512MB"));  // "476MB"

        // Important Edge case x - .07x = 1 GB
        // .93x = 1GB
        //
        // Making it exactly 1 GB in actualMemorySize(...) requires special decimal handling
        BigDecimal bd = new BigDecimal(1.0/0.93);
        System.out.println(actualMemorySize(bd.toPlainString()+"GB"));

        // Edge case, it will be slightly less than 1 GB
        BigDecimal bd2 = new BigDecimal(1.0/0.93001);
        System.out.println(actualMemorySize(bd2.toPlainString()+"GB"));

        // Edge case, it will be exactly 1.01GB
        // x - .07x = 1.01GB
        // .93x = 1.01
        BigDecimal bd3 = new BigDecimal(1.01/0.93);
        System.out.println(actualMemorySize(bd3.toPlainString()+"GB"));

        // Edge case, rounding 1.005GB
        // x - .07x = 1.005GB
        // .93x = 1.005
        BigDecimal bd4 = new BigDecimal(1.005/0.93);
        System.out.println(actualMemorySize(bd4.toPlainString()+"GB"));

        // Edge case, rounding 1.0049GB
        // x - .07x = 1.0049GB
        // .93x = 1.0049
        BigDecimal bd5 = new BigDecimal(1.0049/0.93);
        System.out.println(actualMemorySize(bd5.toPlainString()+"GB"));
    }
}
