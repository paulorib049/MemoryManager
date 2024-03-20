package so;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import so.memory.AdressMemory;

public class Process {
	private String id;
	private int sizeInMemory;
	//private List<Process> process;
	//private int timeToExecution;
	private AdressMemory adressMemory;
	
	public Process(int sizeInMemory) {
        this.id = UUID.randomUUID().toString();
        this.sizeInMemory = sizeInMemory;
    }
	
	public Process() {
		Random rand = new Random();
		this.id = UUID.randomUUID().toString();
		List<Integer> givenList = Arrays.asList(1,2,4,5,8,10,20,50,100);
		this.sizeInMemory = givenList.get(rand.nextInt(givenList.size()));
		this.adressMemory = adressMemory;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSizeInMemory() {
		return sizeInMemory;
	}
	public void setSizeInMemory(int sizeInMemory) {
		this.sizeInMemory = sizeInMemory;
	}
	public AdressMemory getAdressMemory() {
		return adressMemory;
	}
	public void setAdressMemory(AdressMemory adressMemory) {
		this.adressMemory = adressMemory;
	}
}