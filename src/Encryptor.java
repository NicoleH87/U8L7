import java.util.ArrayList;

public class Encryptor {
    private String[][] letterBlock;
    private int numRows;
    private int numCols;
    private int shift;

    public Encryptor(int r, int c, int s)
    {
        letterBlock = new String[r][c];
        numRows = r;
        numCols = c;
        shift = s;
    }

    public String[][] getLetterBlock() {return letterBlock;}

    public void fillBlock(String str)
    {
        int count = 0;
        for (int r = 0; r < numRows; r++)
        {
            for (int c = 0; c < numCols; c++)
            {
                if (count < str.length())
                {
                    letterBlock[r][c] = str.substring(count, count + 1);
                    count++;
                }
                else
                {
                    letterBlock[r][c] = "A";
                }
            }
        }
    }

    public String encryptBlockRight() {

        for (int i = 0; i < shift; i++)
        {
            for (int r = 0; r < numRows; r++) {
                String temp = letterBlock[r][numCols - 1];
                for (int c = numCols - 1; c > 0; c--) {
                    letterBlock[r][c] = letterBlock[r][c - 1];
                }
                letterBlock[r][0] = temp;
            }
        }
        String product = "";
        for (int c = 0; c < numCols; c++)
        {
            for (int r = 0; r < numRows; r++)
            {
                product += letterBlock[r][c];
            }
        }
        return product;
    }

    public String encryptBlockLeft() {
        for (int r = 0; r < numRows; r++)
        {
            for (int t = 0; t < shift; t++)
            {
                String temp = letterBlock[r][0];
                for (int c = 1; c < numCols; c++)
                {
                    letterBlock[r][c-1] = letterBlock[r][c];
                }
                letterBlock[r][numCols - 1] = temp;
            }
        }

        String product = "";
        for (int c = 0; c < letterBlock[0].length; c++)
        {
            for (int r = 0; r < letterBlock.length; r++)
            {
                product += letterBlock[r][c];
            }
        }
        return product;
    }

    public String encryptMessageRight(String message)
    {
        String encryptMes = "";
        int time = message.length() / (numRows * numCols);
        if (message.length() % (numRows * numCols) > 0)
        {
            time++;
        }
        for (int i = 0; i < time; i++)
        {
            fillBlock(message);
            encryptMes += encryptBlockRight();
            if (i < time - 1)
            {
                message = message.substring(numRows * numCols);
            }
        }
        return encryptMes;
    }

    public String encryptMessageLeft(String message)
    {
        String encryptMes = "";
        int time = message.length() / (numRows * numCols);
        if (message.length() % (numRows * numCols) > 0)
        {
            time++;
        }
        for (int i = 0; i < time; i++)
        {
            fillBlock(message);
            encryptMes += encryptBlockLeft();
            if (i < time - 1)
            {
                message = message.substring(numRows * numCols);
            }
        }
        return encryptMes;
    }

    public String decryptMessageRight(String encryptedMessage)
    {
        String decryptMes = "";
        int time = encryptedMessage.length() / (numRows * numCols);
        for (int i = 0; i < time; i++)
        {
            int count = 0;
            for (int c = 0; c < numCols; c++)
            {
                for (int r = 0; r < numRows; r++)
                {
                    if (count < encryptedMessage.length())
                    {
                        letterBlock[r][c] = encryptedMessage.substring(count, count + 1);
                        count++;
                    }
                }
            }
            encryptBlockLeft();
            for (int r = 0; r < numRows; r++)
            {
                for (int c = 0; c < numCols; c++)
                {
                    decryptMes += letterBlock[r][c];
                }
            }
            encryptedMessage = encryptedMessage.substring(numRows * numCols);
        }
        while (decryptMes.substring(decryptMes.length() - 1).equals("A"))
        {
            decryptMes = decryptMes.substring(0, decryptMes.length() - 1);
        }
        return decryptMes;
    }

    public String decryptMessageLeft(String encryptedMessage)
    {
        String decryptMes = "";
        int time = encryptedMessage.length() / (numRows * numCols);
        for (int i = 0; i < time; i++)
        {
            int count = 0;
            for (int c = 0; c < numCols; c++)
            {
                for (int r = 0; r < numRows; r++)
                {
                    if (count < encryptedMessage.length())
                    {
                        letterBlock[r][c] = encryptedMessage.substring(count, count + 1);
                        count++;
                    }
                }
            }
            encryptBlockRight();
            for (int r = 0; r < numRows; r++)
            {
                for (int c = 0; c < numCols; c++)
                {
                    decryptMes += letterBlock[r][c];
                }
            }
            encryptedMessage = encryptedMessage.substring(numRows * numCols);
        }
        while (decryptMes.substring(decryptMes.length() - 1).equals("A"))
        {
            decryptMes = decryptMes.substring(0, decryptMes.length() - 1);
        }
        return decryptMes;
    }
}
