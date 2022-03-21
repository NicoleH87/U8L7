public class EncryptorTester {
    public static void main(String[] args) {
        Encryptor test1 = new Encryptor(3, 4, 3);
        String message = test1.encryptMessageRight("What are you doing next weekend?");
        System.out.println("Message: " + message);
        System.out.println("Decrypt: " + test1.decryptMessageRight(message));

        System.out.println();
        Encryptor test2 = new Encryptor(4, 5, 3);
        String message2 = test2.encryptMessageLeft("We have a problem. I hope this works.");
        System.out.println("Message: " + message2);
        System.out.println("Decrypt: " + test2.decryptMessageLeft(message2));


        String[][] letterBlock = {{"a", "b", "c", "d"},
                                    {"e", "f", "g", "h"},
                                    {"i", "j", "k", "l"}};
        int shift = 4;
        for (int i = 0; i < shift; i++)
        {
            for (int r = 0; r < 3; r++) {
                String temp = letterBlock[r][4 - 1];
                for (int c = 4 - 1; c > 0; c--) {
                    letterBlock[r][c] = letterBlock[r][c - 1];
                }
                letterBlock[r][0] = temp;
            }
        }

        String product = "";
        for (int c = 0; c < 4; c++)
        {
            for (int r = 0; r < 3; r++)
            {
                product += letterBlock[r][c];
            }
        }
        System.out.println(product);
    }
}
