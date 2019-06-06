package lab22;

public class Command {
    static int l = 1;

    public void Function(String text) {

        int count_sentences = 0;
        StringBuilder []sentences = new StringBuilder[10];

        StringBuilder one_sent = new StringBuilder();
        for(int i = 0; i<text.length(); i++){
            //(всі елементи до крапки вважаються реченням і їх додаємо в масив)
            if(text.charAt(i) != '.') one_sent.append(text.charAt(i));

            else if(text.charAt(i) == '.') {
                sentences[count_sentences] = one_sent;
                count_sentences++;
                one_sent = new StringBuilder();
            }
        }
        if(text != null){
        for(int k=0; k<count_sentences; k++){
            System.out.println(sentences[k].toString());
        }}



        if(text != null) {

            for (int k = 0; k < count_sentences; k++) {
                String new_sent = sentences[k].toString() + ' ';

                int amount = new_sent.length();
                int minof = Integer.MAX_VALUE;
                int wordLength = 0;
                StringBuffer minWord = new StringBuffer();
                int pointerOfMin = 0;

                for (int r = l; r < amount; r++) {
                    //if(Character.isLetter(new_sent.charAt(i))){
                    if (new_sent.charAt(r) != ' ') {
                        wordLength += 1;
                    } else {
                        if (minof > wordLength) {
                            minof = wordLength;
                            //System.out.println("minof = " + minof);
                            pointerOfMin = r - wordLength;
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
                   // if(Character.isLetter(new_sent.charAt(i))){
                    if (new_sent.charAt(i) != ' ') {
                        wordLength += 1;
                    } else {
                        if (maxof < wordLength) {
                            maxof = wordLength;
                            //System.out.println("maxof = " + maxof);
                            pointerOfMax = i - wordLength;
                        }

                        wordLength = 0;
                    }
                }

                for (int i = pointerOfMax; i < pointerOfMax + maxof; i++) {
                    maxWord.append(new_sent.charAt(i));
                }

                System.out.println("In sentence " + "\"" + k + "\"" + " min_word is " + "\"" + minWord + "\"" + " and max_word is " + "\"" + maxWord + "\"");
            }
        }
    }
}
