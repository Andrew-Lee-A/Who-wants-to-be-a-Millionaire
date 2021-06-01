package enums;

/**
 * This class is used to represent the amount won, based on the questions 
 * that the player has got correct.
 * Example: if you had $200, the attached number of questions correct is 2
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public enum WinningAmount {
    $0(0), $100(1), $200(2), $300(3), $500(4), $1000(5), $2000(6), $4000(7), $8000(8), $16000(9), $32000(10),
    $64000(11), $125000(12), $250000(13), $500000(14), $1000000(15);
    
    private final int questionsCorrect;
    
    private WinningAmount(int questionsCorrect) {
        this.questionsCorrect = questionsCorrect;
    }
    
    public int getQuestionsCorrect() {
        return this.questionsCorrect;
    }
    
    @Override
    public String toString() {
        String winnings = "";
        
        switch(questionsCorrect) {
            case 0:
                winnings += "$0";
                break;
            case 1:
                winnings += "$100";
                break;
            case 2:
                winnings += "$200";
                break;
            case 3:
                winnings += "$300";
                break;
            case 4:
                winnings += "$500";
                break;
            case 5:
                winnings += "$1,000";
                break;
            case 6:
                winnings += "$2,000";
                break;
            case 7:
                winnings += "$4,000";
                break;
            case 8:
                winnings += "$8,000";
                break;
            case 9:
                winnings += "$16,000";
                break;
            case 10:
                winnings += "$32,000";
                break;
            case 11:
                winnings += "$64,000";
                break;
            case 12:
                winnings += "$125,000";
                break;
            case 13:
                winnings += "$250,000";
                break;
            case 14:
                winnings += "$500,000";
                break;
            case 15:
                winnings += "$1,000,000";
                break;   
        }
        
        return winnings;
    }
}
