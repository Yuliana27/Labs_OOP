package lab1;

public class Function {
    static int l = 0;

    public void Code(String input, int count_sentences, StringBuilder sentences[],boolean debug) {
        if (input != null) {
          for (int k = 0; k < count_sentences; k++) {
              String new_sent = sentences[k].toString() + ' ';


              int amount = new_sent.length();
                int minof = Integer.MAX_VALUE;
                int wordLength = 0;
                StringBuffer minWord = new StringBuffer();
                int pointerOfMin = 0;

                for (int i = l; i < amount; i++) {
                    if (new_sent.charAt(i) != ' ') {
                        wordLength += 1;}




                     else {
                        if (minof > wordLength) {
                            minof = wordLength;
                            //System.out.println("minof = " + minof);
                            pointerOfMin = i - wordLength;
                        }
                        wordLength = 0;
                    }
                }

                for (int i = pointerOfMin; i < pointerOfMin + minof; i++) {
                    minWord.append(new_sent.charAt(i));
                }

                int maxof = 0;
                StringBuffer maxWord = new StringBuffer();
                int pointerOfMax = 0;

                for (int i = l; i < amount; i++) {
                    if (new_sent.charAt(i) != ' ') {
                        wordLength += 1;
                    } else {
                        if (maxof < wordLength) {
                            maxof = wordLength;
                            //System.out.println("maxof = " + maxof);
                            pointerOfMax = i - wordLength;
                        }
                        // звертаємось до debug
                        if(debug){
                        System.out.println(wordLength);}
                        wordLength = 0;
                    }
                }

                for (int i = pointerOfMax; i < pointerOfMax + maxof; i++) {
                    maxWord.append(new_sent.charAt(i));
                }

                System.out.println("In sentence " + "\"" + k + "\"" + " min_word is " + "\"" + minWord + "\"" + " and max_word is " + "\"" + maxWord + "\"");
            }
        } else {
            System.out.println("Відсутні вхідні дані");
        }
    }
}

