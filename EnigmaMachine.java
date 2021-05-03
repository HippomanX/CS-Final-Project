public class EnigmaMachine{
   //define board and rotors
   private int[] plugboard;
   private int[] reflector;
   private Rotor rotor1;
   private Rotor rotor2;
   private Rotor rotor3;
   //Constructor, initialize board
   public EnigmaMachine(){
      //plugboard definition
      plugboard = new int[26];
      reflector = new int[26];
      //rotor setups
      int[] rot1 = new int[26];
      int[] rot2 = new int[26];
      int[] rot3 = new int[26];
      String rot1string = "DMTWSILRUYQNKFEJCAZBPGXOHV";
      String rot2string = "HQZGPJTMOBLNCIFDYAWVEUSRKX";
      String rot3string = "UQNTLSZFMREHDPXKIBVYGJCWOA";
      
      for (int i = 0; i < 26; i ++){
         rot1[i] = charNumber(rot1string.charAt(i));
      }
      for (int i = 0; i < 26; i ++){
         rot2[i] = charNumber(rot2string.charAt(i));
      }
      for (int i = 0; i < 26; i ++){
         rot3[i] = charNumber(rot3string.charAt(i));
      }
      
      rotor1 = new Rotor(rot1, 0);
      rotor2 = new Rotor(rot2, 0);
      rotor3 = new Rotor(rot3, 0);
      //plugboard setup
      for (int x = 0; x < 26; x++){
         this.plugboard[x] = x;
         
      }
      /*for (int x = 0; x < 2; x++){
         for (int y = 0; y < 26; y++){
            this.reflector[x][y] = (char)(y + 97);
         }
      }*/
      this.reflector[0] = charNumber('c');
      this.reflector[1] = charNumber('d');
      this.reflector[2] = charNumber('a');
      this.reflector[3] = charNumber('b');
      this.reflector[4] = charNumber('g');
      this.reflector[5] = charNumber('h');
      this.reflector[6] = charNumber('e');
      this.reflector[7] = charNumber('f');
      this.reflector[8] = charNumber('k');
      this.reflector[9] = charNumber('l');
      this.reflector[10] = charNumber('i');
      this.reflector[11] = charNumber('j');
      this.reflector[12] = charNumber('o');
      this.reflector[13] = charNumber('p');
      this.reflector[14] = charNumber('m');
      this.reflector[15] = charNumber('n');
      this.reflector[16] = charNumber('s');
      this.reflector[17] = charNumber('t');
      this.reflector[18] = charNumber('q');
      this.reflector[19] = charNumber('r');
      this.reflector[20] = charNumber('w');
      this.reflector[21] = charNumber('x');
      this.reflector[22] = charNumber('u');
      this.reflector[23] = charNumber('v');
      this.reflector[24] = charNumber('z');
      this.reflector[25] = charNumber('y');
      
      
   }
   
   /*public String printString(){
      return this.string1;
   }*/
   
   public String enigmaEncryptor(String input){
      String finalstring = "";
      
      for (int i = 0; i < input.length(); i++){
         
         if (rotor1.returnOffset() >= 26) {
            rotor1.changeOffset(0);
            rotor2.rotate();
         }
         if (rotor2.returnOffset() >= 26) {
            rotor2.changeOffset(0);
            rotor3.rotate();
         }
         if (rotor3.returnOffset() >= 26) {
            rotor3.changeOffset(0);
         }
         char thisChar = input.charAt(i);
         //plugboard
         //if ((int)thisChar )
         int pluggedChar = this.plugboard[charNumber(thisChar)];
         //rotor 1
         int rot1char = rotor1.inToOut(pluggedChar);
         //CaesarCipher.EncryptChar(pluggedChar, rotor1.returnOffset());
         //
         //rotor 2
         int rot2char = rotor2.inToOut(rot1char);
         //rotor 3
         int rot3char = rotor3.inToOut(rot2char);
         //reflector
         int reflectedChar = this.reflector[rot3char];
         
         int rot4char = rotor3.outToIn(reflectedChar);
         //CaesarCipher.EncryptChar(pluggedChar, rotor1.returnOffset());
         //
         //rotor 2
         int rot5char = rotor2.outToIn(rot4char);
         //rotor 3
         int rot6char = rotor1.outToIn(rot5char);
         
         finalstring += (char)(rot6char + 97);
         //rotor1.rotate();
      }
      return finalstring;
   }/*
   public String enigmaDecryptor(String input){
      String finalstring = "";
      
      for (int i = 0; i < input.length(); i++){
         if (this.rotor1Offset >= 26) {
            this.rotor1Offset = 0;
            this.rotor2Offset++;
         }
         if (this.rotor2Offset >= 26) {
            this.rotor2Offset = 0;
            this.rotor3Offset++;
         }
         if (this.rotor3Offset >= 26) {
            this.rotor3Offset = 0;
         }
         char thisChar = input.charAt(i);
         //plugboard
         
         char char1 = this.plugboard[1][charNumber(thisChar)];
         //rotor 1
         char char2 = CaesarCipher.EncryptChar(char1, this.rotor1Offset);
         //
         //rotor 2
         char char3 = CaesarCipher.EncryptChar(char2, this.rotor2Offset);
         //rotor 3
         char char4 = CaesarCipher.EncryptChar(char3, this.rotor3Offset);
         //reflector
         char reflectedChar = this.reflector[1][charNumber(thisChar)];
         
         char char5 = CaesarCipher.EncryptChar(reflectedChar, this.rotor3Offset);
         char char6 = CaesarCipher.EncryptChar(char5, this.rotor2Offset);
         char char7 = CaesarCipher.EncryptChar(char6, this.rotor1Offset);
         char char8 = this.plugboard[1][charNumber(char7)];
         
         finalstring += char8;
         this.rotor1Offset--;
      }
      return finalstring;
   }*/
   public static int charNumber(char a){
      if (a <= 'z' && a >= 'a'){
         return a - 97;
      }
      
      if (a <= 'Z' && a >= 'A'){
         return a - 65;
      }
      else return 0;
   }
   public static void main(String args[]){
      EnigmaMachine enigma = new EnigmaMachine();
      String testInput = "hellomynameisalex";
      StdOut.println(testInput);
      StdOut.println(enigma.enigmaEncryptor(testInput));
      StdOut.println(enigma.enigmaEncryptor(enigma.enigmaEncryptor(testInput)));
      //StdOut.println(enigma.enigmaEncryptor(""));
      //StdOut.println(enigma.enigmaEncryptor(""));
      //StdOut.println(enigma.enigmaDecryptor(enigma.enigmaEncryptor("g")));
      //EnigmaMachine enigma2 = new EnigmaMachine(0, 0, 0, "sfvy");
      //StdOut.println(enigma.enigmaEncryptor("sfvy"));

   }
}