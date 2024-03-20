package so.memory;

import so.Process;

public class MemoryManager {
    private Strategy strategy;
    private String[] physicMemory;

    public MemoryManager(Strategy strategy) {
        this.strategy = strategy;
        this.physicMemory = new String[128];

    }

    public void write(Process p) {
        if (this.strategy.equals(Strategy.FIRST_FIT)) {
            this.writeUsingFirstFit(p);
        }
        if (this.strategy.equals(Strategy.BEST_FIT)) {
            this.writeUsingBestFit(p);
        }
        if (this.strategy.equals(Strategy.WORST_FIT)) {
            this.writeUsingWorstFit(p);
        }
        if (this.strategy.equals(Strategy.PAGING)) {
            this.writeUsingPaging(p);
        }

    }

    private void writeUsingPaging(Process p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeUsingPaging'");
    }

    private void writeUsingBestFit(Process p) {
    	int actualSize = 0;
        int spaces = 0;
        Integer start = null;
    	for (int i = 0; i < physicMemory.length; i++) {
            if (i == (physicMemory.length - 1)) {
            	if(start==null) {
            		start = i - actualSize;
                    
            	}
                
            } else if (physicMemory[i] == null) {
                actualSize++;
            } else {
                if (spaces < actualSize) {
                	int result1 = p.getSizeInMemory() - actualSize;
                	int result2 = p.getSizeInMemory() - spaces;
                	if(result1<=result2) {
                		start = i - actualSize;
                	}else {
                		start = i- spaces;
                	}
                    
                    spaces = actualSize;
                }
                actualSize = 0;
            }
        }
        int end = (start + p.getSizeInMemory());
        AdressMemory address = new AdressMemory(start, end);
        if (p.getSizeInMemory() <= address.getSize()) {
            insertProcessInMemory(p, address);
        }

 
    
    	
    }

    private void writeUsingWorstFit(Process p) {
    	int actualSize = 0;
        int spaces = 0;
        Integer start = null;

        for (int i = 0; i < physicMemory.length; i++) {
            if (i == (physicMemory.length - 1)) {
                start = i - actualSize;
                spaces = actualSize;
            } else if (physicMemory[i] == null) {
                actualSize++;
            } else {
                if (spaces < actualSize) {
                    spaces = actualSize;
                    start = i - actualSize;
                }
                actualSize = 0;
            }
        }
        int end = (start + p.getSizeInMemory());
        AdressMemory address = new AdressMemory(start, end);
        if (p.getSizeInMemory() <= address.getSize()) {
            insertProcessInMemory(p, address);
        }

 
    }
    private void writeUsingFirstFit(Process p) {
        int actualSize = 0;
        for (int i = 0; i < physicMemory.length; i++) {
            if (i == (physicMemory.length - 1)) {
                if (actualSize > 0) {
                    int start = (i - actualSize);
                    int end = start + p.getSizeInMemory();
                    AdressMemory address = new AdressMemory(start, end);
                    if (p.getSizeInMemory() <= address.getSize()) {
                        insertProcessInMemory(p, address);
                        break;
                    }
                }
            } else if (physicMemory[i] == null) {
                actualSize++;
            } else {
                if (actualSize > 0) {
                    int start = (i - actualSize);
                    int end = start + p.getSizeInMemory();
                    AdressMemory address = new AdressMemory(start, end);
                    if (p.getSizeInMemory() <= address.getSize()) {
                        insertProcessInMemory(p, address);
                        break;
                    }
                    actualSize = 0;
                }

            }

        }

    }

    public void delete(Process p) {
        for (int i = 0; i < physicMemory.length; i++) {
            if (p.getId().equals(physicMemory[i])) {
                physicMemory[i] = null;
            }
        }
        System.out.println("");
        System.out
                .println("Process: " + p.getId() + " deleted");
    }

    private void printMemoryStatus(Process p, AdressMemory address) {
        System.out.println("");
        System.out
                .println("Process: " + p.getId() + " insert in index: : " + address.getStart() + " ao "
                        + address.getEnd());
    }

    private void insertProcessInMemory(Process p, AdressMemory address) {
        for (int i = address.getStart(); i <= address.getEnd(); i++) {
            this.physicMemory[i] = p.getId();
        }

        printMemoryStatus(p, address);
    }



    private int comparator(int comp, int num1, int num2) {
        int result1 = comp - num1;
        int result2 = comp - num2;
        if (result1 <= result2) {
            return num1;
        } else {
            return num2;
        }
    }
}