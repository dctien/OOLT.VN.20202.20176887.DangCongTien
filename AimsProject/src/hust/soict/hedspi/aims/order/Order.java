package hust.soict.hedspi.aims.order;

import java.util.ArrayList;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.book.Book;
import hust.soict.hedspi.aims.media.disc.CompactDisc;
import hust.soict.hedspi.aims.media.disc.DigitalVideoDisc;
import hust.soict.hedspi.aims.utils.MyDate;
import hust.soict.hedspi.aims.exceptions.AddMediaException;
import hust.soict.hedspi.aims.exceptions.CreateOrderException;
import hust.soict.hedspi.aims.exceptions.InputException;

public class Order {
	public static final int MAX_NUMBER_ORDERED =10;
	public static final int MAX_LIMITTED_ORDERS = 5;
	private static int nbOrders = 0;
	
	private MyDate dateOrdered = new MyDate();
	
	public ArrayList<Media> itemsOrdered = new ArrayList<Media>();
	
	public MyDate getDateOrdered() {
		return dateOrdered;
	}

	private Order() {
		nbOrders++;
	}
	
	// call constructor 
	public static Order createdOrder() throws CreateOrderException{
		if(nbOrders < MAX_LIMITTED_ORDERS) {
			System.out.println("thread order");
			Order objOrder = new Order();
			return objOrder;
		}else {
			System.err.println("The limitted orders is almost full!");
			throw new CreateOrderException("The limitted orders is almost full!");
		}
	}
	
//	public int getQtyOrdered() {
//		return qtyOrdered;
//	}

	public void addMedia(Media media) throws AddMediaException {
		if(itemsOrdered.contains(media)) {
			System.err.println("The media with title: " + media.getTitle() + " is existed!");
			throw new AddMediaException("The media with title: " + media.getTitle() + " is existed!\nCan't add into Order");
		}else {
			itemsOrdered.add(media);
			System.out.println("***The media with title: " + media.getTitle() + " has been added");
		}
	}
	public void addMedia(Media... mediaList) {
		for(int i = 0; i < mediaList.length; i++) {
			try {
				addMedia(mediaList[i]);
			} catch (AddMediaException e) {
				System.err.println(e.getMessage());
			}
		}
	}
	public void removeMedia(Media media) {
		if(itemsOrdered.contains(media)) {
			itemsOrdered.remove(media);
			System.out.println("***Media with ID: " + media.getId() + " has been deleted!");
		}else {
			System.out.println("Can't not find!");
		}
	}
	public boolean removeMedia(String id) {
		boolean mark =false;
		for(Media media: itemsOrdered) {
			if(media.getId().equalsIgnoreCase(id)) {
				removeMedia(media);
				mark = true;
				break;
			}
		}
		if(mark == false) {
			System.err.println("Can't find id");
		}
		return mark;
	}
	
	
	public Media getALuckyItem() {
		double rand = Math.random();
		rand *= itemsOrdered.size();
		int item = (int)rand;
		System.out.println( itemsOrdered.get(item).getCost());
		return itemsOrdered.get(item);
	}
	
	public float totalCost() {
		float money = 0.0f;
		for (int i=0 ; i < itemsOrdered.size() ; i++) {
			money += itemsOrdered.get(i).getCost();
		}
		return money;
	}
	
	public void printListOfOrdered() {
		System.out.println("********************************ORDER******************************");
		dateOrdered.print();
		int i = 1;
		System.out.printf("%-3s | %-6s | %-5s | %-15s | %-10s : %-12s| %-5s | %-5s%n", "STT", "Type", "ID", "Title", "Category", "Total", "Number", "Length");
		for(Media media: itemsOrdered) {
			if(media instanceof Book) {
				System.out.printf("%-3s | %-6s | %-5s | %-15s | %-10s : %-10s$ | %n", i, "Book", media.getId(), media.getTitle(), media.getCategory(), media.getCost());
			}else if(media instanceof DigitalVideoDisc) {
				System.out.printf("%-3s | %-6s | %-5s | %-15s | %-10s : %-10s$ |%n", i, "DVD", media.getId(), media.getTitle(), media.getCategory(), media.getCost());
			}else if(media instanceof CompactDisc) {
				System.out.printf("%-3s | %-6s | %-5s | %-15s | %-10s : %-10s$ | %-5s | %-5s%n", i, "CD", media.getId(), media.getTitle(), media.getCategory(), media.getCost(), ((CompactDisc)media).getTracks().size(), ((CompactDisc) media).getLength());
			}
			i++;
		}
		System.out.println("Total cost: " + totalCost());
		java.util.Collections.sort(itemsOrdered);
		System.out.println("*******************************************************************");
		i = 1;
		System.out.printf("%-3s | %-6s | %-5s | %-15s | %-10s : %-12s| %-5s | %-5s%n", "STT", "Type", "ID", "Title", "Category", "Total", "Number", "Length");
		for(Media media: itemsOrdered) {
			if(media instanceof Book) {
				System.out.printf("%-3s | %-6s | %-5s | %-15s | %-10s : %-10s$ |%n", i, "Book", media.getId(), media.getTitle(), media.getCategory(), media.getCost());
			}else if(media instanceof DigitalVideoDisc) {
				System.out.printf("%-3s | %-6s | %-5s | %-15s | %-10s : %-10s$ |%n", i, "DVD", media.getId(), media.getTitle(), media.getCategory(), media.getCost());
			}else if(media instanceof CompactDisc) {
				System.out.printf("%-3s | %-6s | %-5s | %-15s | %-10s : %-10s$ | %-5s | %-5s%n", i, "CD", media.getId(), media.getTitle(), media.getCategory(), media.getCost(), ((CompactDisc)media).getTracks().size(), ((CompactDisc) media).getLength());
			}
			i++;
		}
		System.out.println("Total cost: " + totalCost());
		System.out.println("*******************************************************************");
	}

	public boolean checkId(String id) {
		for(Media media: itemsOrdered) {
			if(media.getId().equalsIgnoreCase(id)) {
				return true;
			}
		}
		return false;
	}

}
