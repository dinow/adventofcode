package be.dno.test;

public class Day11_reddit {
    //String inputa = "hxbxwxba";
    String input = "hepxcrrq";
    //String inputTest = "ghijklmn";

    public static void main(String[] args) {
        new Day11_reddit().run();

    }

    public void run() {
        char[] array = new char[input.length()];
        array = input.toCharArray();
        long begin = System.nanoTime(); 
        for (int i = 0; i > -1; i++) {
            increment(array);
            boolean isPassword = isPassword(array);
            if(isPassword){
                System.out.println("Password detected:" + new String(array));
                break;
            }


        }
        long end = System.nanoTime();
        System.out.println((end - begin) / 10e9);
    }

    private boolean isPassword(char[] array) {
        boolean threeLetters = false;
        int pair = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == 'i' || array[i] == 'o' || array[i] == 'l') {
                specialIncrement(array, i);
                return false;
            }

            if (i < array.length - 2) {
                if ((array[i] + 1 == array[i + 1])
                        && (array[i + 1] == array[i + 2] - 1)) {
                    threeLetters = true;

                }
            }
            if (i < array.length - 1) {
                if (array[i] == array[i + 1]) {
                    pair++;
                }
            }

            if (i < array.length - 2) {
                if (array[i] == array[i + 1] && array[i] == array[i + 2]) {
                    pair--;
                }
            }

        }
        return threeLetters && pair > 1;
    }

    private void specialIncrement(char[] array, int position) {
        increment(array, position);
        for (int i = position + 1; i < array.length; i++) {
            array[i] = 'a';
        }

    }

    private void increment(char[] array) {
        increment(array, array.length - 1);
    }

    private void increment(char[] array, int position) {
        array[position] = incrementChar(array[position]);
        int i = position;
        while (array[i] == 'a' && i > 0) {
            i--;
            array[i] = incrementChar(array[i]);
        }

    }

    private char incrementChar(char c) {
        if (c == 'z') {
            return 'a';
        } else {
            char result = (char) (c + 1);
            return result;

        }
    }
}
