package game;

public class Can {

    // chip size
	// must be bigger than 5 or risk out of bounds error
    final int canSize = 10;

    private int chipCount;
    private char[] chipStack;
    private int luckyChip;
    // chip stack chars:
    // 'p' = pickle or poison chip
    // 'c' = chip

    public Can() {
        // generate chip stack with randomness
        // default size & default count
        // allow for resizing

        chipCount = 0;
        chipStack = new char[canSize];

        for (int i = 0; i < canSize; i++) {
            this.push('c');
        } 

        // find the "lucky chip" to replace with the pickle chip.
        luckyChip = (int)(Math.random() * 5);
        chipStack[luckyChip] = 'p';
    }

    public char pop() {
        char grabbedChip = this.peek();
        chipStack[chipCount - 1] = ' ';
        --chipCount;
        return grabbedChip;
    }

    public char peek() {
        return chipStack[chipCount - 1];
    }

    public int getChipCount() {
        return chipCount;
    }

    // used exclusivly for setup
    private void push(char chip) {
        chipStack[chipCount] = chip;
        ++chipCount;
    }

    public boolean eatChips(int x) {
        // eat x amount of chips
        for (int i = 0; i < x; i++) {
            if (this.pop() == 'p')
                return true;
        }

        return false;
    }

    public boolean shuffle() {

        boolean isPickleChipfound = false;
            
        for (int i = 0; i < 3; i++) {
            final int o = (chipCount - 1) - i; // find the location to check from the top of the stack
            
            // replace pickle chip with normal chip if found.
            if ( chipStack[o] == 'p') {
                chipStack[o] = 'c';
                isPickleChipfound = true;
                break;
            }
        }

	
        if (isPickleChipfound) {
			// make sure we dont replace chips that are out of bounds
			int randomChipRange = 3; // default is 0-2

			if (chipCount <= 3) {
				randomChipRange = chipCount - 1;
			}
			
			// make a random choice
			
            chipStack[(chipCount - 1) - (int)(Math.random() * randomChipRange)] = 'p';
        }

        return this.eatChips(1);
    }

    public String checkDistance() {
        // this seems to be currently checking for the distance from the bottom of the stack ?
        int distance = chipCount - (luckyChip);

        if (distance > 20) 
            return "over 20 chips away.";
        else if (distance <= 5)
            return "within the next 5 chips!!!";
        else if (distance <= 10)
            return "within the next 10 chips.";
        else 
            return "within the next 20 chips.";
    }
}
