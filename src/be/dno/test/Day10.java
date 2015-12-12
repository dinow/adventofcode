package be.dno.test;

public class Day10 {
	private static int cpt = 0;
	public static void main(String[] args) throws Exception {
        convert("1113222113"/*, 1000*/);

    }

    public static void convert(String input/*, int stillToGo*/){
        /*String output = getStrconverted(input, stillToGo);
        if (stillToGo > 0){
            return convert(output, --stillToGo);
        }
        return output;*/
    	getStrconverted(input);
    }

    public static void getStrconverted(String input){
    	cpt++;
        long start = System.currentTimeMillis();
        //System.out.println("input : " + input);
        StringBuilder output = new StringBuilder();
        char[] chars = input.toCharArray();
        char current = chars[0];
        int currentpos = 0;
        int cptCurrent = 0;
        while(currentpos < chars.length) {
            while (currentpos < chars.length && chars[currentpos] == current) {
                //System.out.println("chars[currentpos] : " + chars[currentpos] + ", current: " + current + ", cptCurrent: " + cptCurrent + ", currentpos: " + currentpos);
                cptCurrent++;
                currentpos++;
            }

            output.append(cptCurrent).append(current);
            //System.out.println("inprogress : " + output);
            cptCurrent = 0;
            if (currentpos < chars.length) {
                current = chars[currentpos];
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(cpt + " outputlength : " + output.length() + ", took " + (end-start) + "ms");
        if (end-start < 60000){
        	getStrconverted(output.toString());
        }
    }
}
