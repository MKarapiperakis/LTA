public class App 
{
    static int[] sbox = {6,4,0xc,5,0,7,2,0xe,1,0xf,3,0xd,8,0xa,9,0xb};
    static int SIZE_SBOX = sbox.length;

    public static void main(String[] args) throws Exception {
        // rows
        System.out.print( "    | ");
        for (int i=0;i<SIZE_SBOX;i++)
            System.out.print(rjust(Integer.toHexString(i),3) + " ");
            
        System.out.println("");
        
        String dashes = "";
        for (int j = 0;j< (SIZE_SBOX * 4 + 4);j++) dashes += "-";
			System.out.println(" " + dashes);
        
        for (int row=0;row< SIZE_SBOX;row++) 
		{
            System.out.print(rjust(Integer.toHexString(row),3) +  " | ");
            for (int col = 0; col<SIZE_SBOX;col++)
			{
                System.out.print(rjust(linearApprox(row, col),3) + " ");
            }
            System.out.println("");
        }       
    }

    private static String rjust(String s, int len) 
	{
        len = len-s.length();
        for (int i =0;i<len;i++) s = " "+s;
        return s;
    }
    
    public static String linearApprox(int input_int, int output_int) 
	{
        int total = 0;
        for (int ii=0; ii<SIZE_SBOX;ii++) 
		{
            int input_masked = ii & input_int;
            int output_masked = sbox[ii] & output_int;
            total += (Integer.toBinaryString(input_masked).codePoints().filter(ch -> ch == '1').count() - Integer.toBinaryString(output_masked).codePoints().filter(ch -> ch == '1').count()) % 2 == 0?1:0;
        }
        int result = total - Math.floorDiv(SIZE_SBOX,2);
        if (result > 0)
		{
            return "+" + result;
        } else 
		{
            return String.valueOf(result);
        }
    }
}
