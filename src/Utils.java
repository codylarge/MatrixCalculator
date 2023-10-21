public class Utils
{
    public static float[] stringToFloatArray(String s)
    {
        String[] stringValues = s.split("\\s+"); // Split the input string by spaces (you can change the delimiter as needed)
        float[] floatArray = new float[stringValues.length];

        for (int i = 0; i < stringValues.length; i++) {
            try {
                floatArray[i] = Float.parseFloat(stringValues[i]);
            } catch (NumberFormatException e) {
                // Handle any invalid float values here
                System.err.println("Invalid float value: " + stringValues[i]);
            }
        }
        return floatArray;
    }
    public static boolean isFloat(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String[] trimStringsInArray(String[] inputArray) {
        if (inputArray == null) {
            return null; // Handle the case of a null input array
        }

        String[] trimmedArray = new String[inputArray.length];

        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] != null) {
                trimmedArray[i] = inputArray[i].trim();
            }
        }
        return trimmedArray;
    }

}
