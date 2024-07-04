package org.project.youtube.Client.Model;

public class YID {
    private char[] YID;

    public YID(char[] YID) {
        this.YID = YID;
    }

    public static YID randomYID(){
        int[] randomChars = new int[10];
        for (int i = 0 ; i < 10; i++){
            randomChars[i] = (int)(Math.random() * 36);
        }

        char[] newYID = new char[10];
        for (int i = 0; i < 10; i++) {
            switch (randomChars[i]) {
                case 0:
                    newYID[i] = '0';
                    break;
                case 1:
                    newYID[i] = '1';
                    break;
                case 2:
                    newYID[i] = '2';
                    break;
                case 3:
                    newYID[i] = '3';
                    break;
                case 4:
                    newYID[i] = '4';
                    break;
                case 5:
                    newYID[i] = '5';
                    break;
                case 6:
                    newYID[i] = '6';
                    break;
                case 7:
                    newYID[i] = '7';
                    break;
                case 8:
                    newYID[i] = '8';
                    break;
                case 9:
                    newYID[i] = '9';
                    break;
                case 10:
                    newYID[i] = 'a';
                    break;
                case 11:
                    newYID[i] = 'b';
                    break;
                case 12:
                    newYID[i] = 'c';
                    break;
                case 13:
                    newYID[i] = 'd';
                    break;
                case 14:
                    newYID[i] = 'e';
                    break;
                case 15:
                    newYID[i] = 'f';
                    break;
                case 16:
                    newYID[i] = 'g';
                    break;
                case 17:
                    newYID[i] = 'h';
                    break;
                case 18:
                    newYID[i] = 'i';
                    break;
                case 19:
                    newYID[i] = 'j';
                    break;
                case 20:
                    newYID[i] = 'k';
                    break;
                case 21:
                    newYID[i] = 'l';
                    break;
                case 22:
                    newYID[i] = 'm';
                    break;
                case 23:
                    newYID[i] = 'n';
                    break;
                case 24:
                    newYID[i] = 'o';
                    break;
                case 25:
                    newYID[i] = 'p';
                    break;
                case 26:
                    newYID[i] = 'q';
                    break;
                case 27:
                    newYID[i] = 'r';
                    break;
                case 28:
                    newYID[i] = 's';
                    break;
                case 29:
                    newYID[i] = 't';
                    break;
                case 30:
                    newYID[i] = 'u';
                    break;
                case 31:
                    newYID[i] = 'v';
                    break;
                case 32:
                    newYID[i] = 'w';
                    break;
                case 33:
                    newYID[i] = 'x';
                    break;
                case 34:
                    newYID[i] = 'y';
                    break;
                case 35:
                    newYID[i] = 'z';
                    break;
            }
        }

        return new YID(newYID);
    }

    public static YID fromCharArr(char[] arr){
        return new YID(arr);
    }

    public static YID fromString(String str){
        char[] arr = new char[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = str.charAt(i);
        }

        return new YID(arr);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            str.append(YID[i]);
        }

        return str.toString();
    }

    public char[] toCharArr() {
        return YID;
    }
}